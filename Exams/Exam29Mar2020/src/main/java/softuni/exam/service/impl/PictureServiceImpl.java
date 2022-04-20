package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.dto.PicturesImportDTO;
import softuni.exam.models.Car;
import softuni.exam.models.Picture;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final CarRepository carRepository;
    private final Path path = Paths.get("src/main/resources/files/json/pictures.json");
    private final Gson gson;
    private final ModelMapper mapper;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, CarRepository carRepository) {
        this.pictureRepository = pictureRepository;
        this.carRepository = carRepository;
        this.gson = new GsonBuilder().create();
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }

    @Override
    public String importPictures() throws IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        PicturesImportDTO[] picturesImportDTOs = this.gson.fromJson(reader, PicturesImportDTO[].class);

        for (PicturesImportDTO pictureDTO : picturesImportDTOs) {
            if (!pictureDTO.isValid()) {
                message.add("Invalid picture");
                continue;
            }
            Picture pictureByName = this.pictureRepository.findByName(pictureDTO.getName());
            if (pictureByName != null) {
                message.add("Invalid picture");
                continue;
            }
            Picture picture = this.mapper.map(pictureDTO, Picture.class);
            String[] dateTimeData = pictureDTO.getDateAndTime().split(" ");
            String[] dateData = dateTimeData[0].split("-");
            int year = Integer.parseInt(dateData[0]);
            int month = Integer.parseInt(dateData[1]);
            int day = Integer.parseInt(dateData[2]);
            String[] timeData = dateTimeData[1].split(":");
            int hour = Integer.parseInt(timeData[0]);
            int minute = Integer.parseInt(timeData[1]);
            int second = Integer.parseInt(timeData[2]);
            LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute, second);
            picture.setDateAndTime(dateTime);
            Car car = this.carRepository.findById(pictureDTO.getCar()).get();
            picture.setCar(car);
            this.pictureRepository.save(picture);
            message.add(String.format("Successfully import picture - %s", picture.getName()));
        }

        return String.join("\n", message);
    }
}
