package spring.autoMapping.dto;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class UserLogInDTO {
    private int id;
    private String fullName;
    private Set<GameInfoDTO> games;
    private boolean isAdministrator;

    public UserLogInDTO() {
        this.games = new LinkedHashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<GameInfoDTO> getGames() {
        return Collections.unmodifiableSet(games);
    }

    public void setGames(Set<GameInfoDTO> games) {
        this.games = games;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }

    public void addGame(GameInfoDTO gameInfoDTO) {
        this.games.add(gameInfoDTO);
    }

    public void removeGame(GameInfoDTO gameInfoDTO) {
        this.games.remove(gameInfoDTO);
    }
}
