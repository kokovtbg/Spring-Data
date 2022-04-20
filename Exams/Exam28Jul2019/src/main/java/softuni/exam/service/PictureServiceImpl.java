package softuni.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.PictureImportDTO;
import softuni.exam.domain.dto.PicturesRootImportDTO;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final Path path = Paths.get("src/main/resources/files/xml/pictures.xml");

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public String importPictures() throws JAXBException, IOException {
        List<String> message = new ArrayList<>();

        JAXBContext context = JAXBContext.newInstance(PicturesRootImportDTO.class);
        Reader reader = Files.newBufferedReader(path);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PicturesRootImportDTO picturesRootImportDTO = (PicturesRootImportDTO) unmarshaller.unmarshal(reader);

        List<PictureImportDTO> pictureImportDTOs = picturesRootImportDTO.getPictures();
        for (PictureImportDTO pictureDTO : pictureImportDTOs) {
            if (!pictureDTO.isValid()) {
                message.add("Invalid picture");
                continue;
            }
            Picture picture = new Picture();
            picture.setUrl(pictureDTO.getUrl());
            this.pictureRepository.save(picture);
            message.add(String.format("Successfully imported picture - %s", picture.getUrl()));
        }

        return String.join("\n", message);
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }

}
