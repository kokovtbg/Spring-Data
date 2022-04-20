package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.dto.TicketImportDTO;
import softuni.exam.dto.TicketsRootImportDTO;
import softuni.exam.models.Passenger;
import softuni.exam.models.Plane;
import softuni.exam.models.Ticket;
import softuni.exam.models.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TownRepository townRepository;
    private final PassengerRepository passengerRepository;
    private final PlaneRepository planeRepository;
    private final Path path = Paths.get("src/main/resources/files/xml/tickets.xml");
    private ModelMapper mapper;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository,
                             TownRepository townRepository,
                             PassengerRepository passengerRepository,
                             PlaneRepository planeRepository) {
        this.ticketRepository = ticketRepository;
        this.townRepository = townRepository;
        this.passengerRepository = passengerRepository;
        this.planeRepository = planeRepository;
        this.mapper = new ModelMapper();
    }


    @Override
    public boolean areImported() {
        return this.ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        List<String> list = Files.readAllLines(path);
        return String.join("\n", list);
    }

    @Override
    public String importTickets() throws IOException, JAXBException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        JAXBContext context = JAXBContext.newInstance(TicketsRootImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        TicketsRootImportDTO ticketsRootImportDTO = (TicketsRootImportDTO) unmarshaller.unmarshal(reader);

        List<TicketImportDTO> ticketImportDTOs = ticketsRootImportDTO.getTickets();
        for (TicketImportDTO ticketDTO : ticketImportDTOs) {
            if (!ticketDTO.isValid()) {
                message.add("Invalid Ticket");
                continue;
            }
            Ticket ticketBySerialNumber = this.ticketRepository.findBySerialNumber(ticketDTO.getSerialNumber());
            if (ticketBySerialNumber != null) {
                message.add("Invalid Ticket");
                continue;
            }
            String[] takeOffData = ticketDTO.getTakeOff().split(" ");
            String[] dateData = takeOffData[0].split("-");
            int year = Integer.parseInt(dateData[0]);
            int month = Integer.parseInt(dateData[1]);
            int day = Integer.parseInt(dateData[2]);
            String[] timeData = takeOffData[1].split(":");
            int hour = Integer.parseInt(timeData[0]);
            int minute = Integer.parseInt(timeData[1]);
            int second = Integer.parseInt(timeData[2]);
            LocalDateTime takeOff = LocalDateTime.of(year, month, day, hour, minute, second);

            Ticket ticket = this.mapper.map(ticketDTO, Ticket.class);
            ticket.setTakeOff(takeOff);
            Town townFrom = this.townRepository.findByName(ticketDTO.getFromTown().getTownName());
            Town townTo = this.townRepository.findByName(ticketDTO.getToTown().getTownName());
            Passenger passengerByEmail = this.passengerRepository.findByEmail(ticketDTO.getPassenger().getEmail());
            Plane planeByRegisterNumber = this.planeRepository.findByRegisterNumber(ticketDTO.getPlane().getRegisterNumber());
            ticket.setFromTown(townFrom);
            ticket.setToTown(townTo);
            ticket.setPassenger(passengerByEmail);
            ticket.setPlane(planeByRegisterNumber);
            this.ticketRepository.save(ticket);
            message.add(String.format("Successfully imported Ticket %s - %s",
                    ticket.getFromTown().getName(), ticket.getToTown().getName()));
        }

        return String.join("\n", message);
    }
}
