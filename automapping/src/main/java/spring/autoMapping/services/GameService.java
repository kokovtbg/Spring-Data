package spring.autoMapping.services;

import spring.autoMapping.dto.GameInfoDTO;
import spring.autoMapping.entities.Game;

import java.util.List;

public interface GameService {
    void register(Game game);

    Game getById(int id);

    void delete(Game game);

    List<Game> getAll();

    Game getByTitle(String title);
}
