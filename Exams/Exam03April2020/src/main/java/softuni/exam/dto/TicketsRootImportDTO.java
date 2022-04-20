package softuni.exam.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketsRootImportDTO {
    @XmlElement(name = "ticket")
    private List<TicketImportDTO> tickets;

    public TicketsRootImportDTO() {
    }

    public List<TicketImportDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketImportDTO> tickets) {
        this.tickets = tickets;
    }
}
