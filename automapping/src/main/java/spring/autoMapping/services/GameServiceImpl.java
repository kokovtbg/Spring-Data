package spring.autoMapping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.autoMapping.entities.Game;
import spring.autoMapping.repositories.GameRepository;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void register(Game game) {
        this.gameRepository.save(game);
    }

    @Override
    public Game getById(int id) {
        return this.gameRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Game game) {
        this.gameRepository.delete(game);
    }

    @Override
    public List<Game> getAll() {
        return this.gameRepository.findAll();
    }

    @Override
    public Game getByTitle(String title) {
        return this.gameRepository.findByTitle(title);
    }
}
