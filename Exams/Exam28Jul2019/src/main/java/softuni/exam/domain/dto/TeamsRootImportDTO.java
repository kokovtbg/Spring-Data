package softuni.exam.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamsRootImportDTO {
    @XmlElement(name = "team")
    private List<TeamImportDTO> teams;

    public TeamsRootImportDTO() {
    }

    public List<TeamImportDTO> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamImportDTO> teams) {
        this.teams = teams;
    }
}
