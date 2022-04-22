package spring.autoMapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.autoMapping.dto.GameInfoDTO;
import spring.autoMapping.dto.GameValidationDTO;
import spring.autoMapping.dto.UserLogInDTO;
import spring.autoMapping.dto.UserValidationDTO;
import spring.autoMapping.entities.Game;
import spring.autoMapping.entities.User;
import spring.autoMapping.services.GameService;
import spring.autoMapping.services.UserService;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;
    private UserLogInDTO userLogInDTO = null;

    @Autowired
    public ConsoleRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Enter Command");
        Scanner scanner = new Scanner(System.in);
        String commandString = scanner.nextLine();
        while (!commandString.equals("End")) {
            String[] commandData = commandString.split("\\|");
            String command = commandData[0];
            ModelMapper mapper = new ModelMapper();
            switch (command) {
                case "RegisterUser":
                    registerUser(commandData, mapper);
                    break;
                case "LoginUser":
                    loginUser(commandData, mapper);
                    break;
                case "Logout":
                    logout();
                    break;
                case "AddGame":
                    addGame(commandData, mapper);
                    break;
                case "EditGame":
                    editGame(commandData, mapper);
                    break;
                case "DeleteGame":
                    deleteGame(commandData[1]);
                    break;
                case "AllGames":
                    allGames(mapper);
                    break;
                case "DetailsGame":
                    detailsGame(commandData[1], mapper);
                    break;
                case "OwnedGames":
                    ownedGames();
                    break;
                case "AddItem":
                    addItem(commandData[1], mapper);
                    break;
                case "RemoveItem":
                    removeItem(commandData[1]);
                    break;
                case "BuyItem":
                    buyItem();
                    break;
            }
            commandString = scanner.nextLine();
        }
    }

    private void registerUser(String[] commandData, ModelMapper mapper) {
        String email = commandData[1];
        String password = commandData[2];
        String passwordConfirm = commandData[3];
        String fullName = commandData[4];
        if (!password.equals(passwordConfirm)) {
            throw new IllegalArgumentException("Passwords must match");
        }
        User userByEmail = this.userService.getByEmail(email);
        if (userByEmail != null) {
            throw new IllegalArgumentException("Email already registered");
        }
        User user = new User(email, password, fullName);
        mapper.map(user, UserValidationDTO.class);

        this.userService.register(user);
        System.out.printf("%s was registered%n", fullName);
        User registeredUser = this.userService.getByEmail(email);
        int id = registeredUser.getId();
        if (id == 1) {
            registeredUser.setAdministrator(true);
            this.userService.register(registeredUser);
        }
    }

    private void loginUser(String[] commandData, ModelMapper mapper) {
        String emailLogIn = commandData[1];
        String passwordLogIn = commandData[2];
        User userByEmailToLogIn = this.userService.getByEmail(emailLogIn);
        if (userByEmailToLogIn == null) {
            throw new NullPointerException("No user with given email");
        }
        String passwordFromUser = userByEmailToLogIn.getPassword();
        if (!passwordFromUser.equals(passwordLogIn)) {
            throw new IllegalArgumentException("Incorrect username / password");
        }
        userLogInDTO = mapper.map(userByEmailToLogIn, UserLogInDTO.class);
        System.out.printf("Successfully logged in %s%n", userLogInDTO.getFullName());
    }

    private void logout() {
        if (userLogInDTO == null) {
            throw new NullPointerException("Cannot log out. No user was logged in.");
        }
        System.out.printf("User %s successfully logged out%n", userLogInDTO.getFullName());
        userLogInDTO = null;
    }

    private void addGame(String[] commandData, ModelMapper mapper) {
        if (userLogInDTO == null) {
            throw new NullPointerException("Not Logged in");
        }
        if (!userLogInDTO.isAdministrator()) {
            throw new IllegalArgumentException("Administrator privilege needed");
        }
        String title = commandData[1];
        double price = Double.parseDouble(commandData[2]);
        double size = Double.parseDouble(commandData[3]);
        String trailer = commandData[4];
        String thumbnail = commandData[5];
        String description = commandData[6];
        int day = Integer.parseInt(commandData[7].split("-")[0]);
        int month = Integer.parseInt(commandData[7].split("-")[1]);
        int year = Integer.parseInt(commandData[7].split("-")[2]);
        LocalDate releaseDate = LocalDate.of(year, month, day);

        Game game = new Game(title, trailer, thumbnail, size, price, description, releaseDate);
        mapper.map(game, GameValidationDTO.class);
        this.gameService.register(game);
        System.out.printf("Added %s%n", title);
    }

    private void editGame(String[] commandData, ModelMapper mapper) {
        if (userLogInDTO == null) {
            throw new NullPointerException("Not Logged in");
        }
        if (!userLogInDTO.isAdministrator()) {
            throw new IllegalArgumentException("Administrator privilege needed");
        }
        int idEditGame = Integer.parseInt(commandData[1]);
        Game gameById = this.gameService.getById(idEditGame);
        if (gameById == null) {
            throw new NullPointerException("No Game Found");
        }
        for (int i = 2; i < commandData.length; i++) {
            String[] commandDataSplit = commandData[i].split("=");
            String key = commandDataSplit[0];
            String value = commandDataSplit[1];
            switch (key) {
                case "title":
                    gameById.setTitle(value);
                    break;
                case "price":
                    gameById.setPrice(Double.parseDouble(value));
                    break;
                case "size":
                    gameById.setSize(Double.parseDouble(value));
                    break;
                case "trailer":
                    gameById.setTrailer(value);
                    break;
                case "thumbnail":
                    gameById.setThumbnail(value);
                    break;
                case "description":
                    gameById.setDescription(value);
                    break;
                case "releaseDate":
                    String[] newDate = value.split("-");
                    int dayNew = Integer.parseInt(newDate[0]);
                    int monthNew = Integer.parseInt(newDate[1]);
                    int yearNew = Integer.parseInt(newDate[2]);
                    gameById.setReleaseDate(LocalDate.of(yearNew, monthNew, dayNew));
                    break;
            }
        }
        mapper.map(gameById, GameValidationDTO.class);
        this.gameService.register(gameById);
        System.out.printf("Edited %s%n", gameById.getTitle());
    }

    private void deleteGame(String commandDatum) {
        if (userLogInDTO == null) {
            throw new NullPointerException("Not Logged in");
        }
        if (!userLogInDTO.isAdministrator()) {
            throw new IllegalArgumentException("Administrator privilege needed");
        }
        int idGameToDelete = Integer.parseInt(commandDatum);
        Game gameToDelete = this.gameService.getById(idGameToDelete);
        if (gameToDelete == null) {
            throw new IllegalArgumentException("No Game with given ID");
        }
        this.gameService.delete(gameToDelete);
        System.out.printf("Deleted %s%n", gameToDelete.getTitle());
    }

    private void allGames(ModelMapper mapper) {
        List<Game> gamesInDB = this.gameService.getAll();
        List<GameInfoDTO> gamesInfoDTO = gamesInDB.stream()
                .map(g -> mapper.map(g, GameInfoDTO.class)).collect(Collectors.toList());
        gamesInfoDTO.forEach(g -> System.out.printf("%s %.2f%n", g.getTitle(), g.getPrice()));
    }

    private void detailsGame(String gameTitle, ModelMapper mapper) {
        Game gameInDB = this.gameService.getByTitle(gameTitle);
        GameInfoDTO gameInfo = mapper.map(gameInDB, GameInfoDTO.class);
        System.out.println(gameInfo);
    }

    private void ownedGames() {
        if (userLogInDTO == null) {
            throw new NullPointerException("No user Currently logged in");
        }
        int idLoggedInUser = this.userLogInDTO.getId();
        User userOwnedGames = this.userService.getById(idLoggedInUser);
        userOwnedGames.getGames().forEach(g -> System.out.printf("%s%n", g.getTitle()));
    }

    private void addItem(String titleGameToAdd, ModelMapper mapper) {
        if (userLogInDTO == null) {
            throw new NullPointerException("No User Currently logged in");
        }
        Game gameInDBToAdd = this.gameService.getByTitle(titleGameToAdd);
        GameInfoDTO gameDTOToAdd = mapper.map(gameInDBToAdd, GameInfoDTO.class);
        GameInfoDTO gameInfoDTOFromCart = this.userLogInDTO.getGames().stream()
                .filter(g -> g.getTitle().equals(titleGameToAdd)).findFirst().orElse(null);
        if (gameInfoDTOFromCart != null) {
            throw new IllegalArgumentException("Game already in Shopping Cart");
        }
        this.userLogInDTO.addGame(gameDTOToAdd);
        System.out.printf("%s added to cart.%n", titleGameToAdd);
    }

    private void removeItem(String titleGameToRemove) {
        if (userLogInDTO == null) {
            throw new NullPointerException("No User Currently logged in");
        }
        GameInfoDTO gameInfoDTOFromCartToRemove = this.userLogInDTO.getGames().stream()
                .filter(g -> g.getTitle().equals(titleGameToRemove)).findFirst().orElse(null);
        if (gameInfoDTOFromCartToRemove == null) {
           throw new IllegalArgumentException("Game is not in Shopping Cart");
        }
        this.userLogInDTO.removeGame(gameInfoDTOFromCartToRemove);
        System.out.printf("%s removed from cart.%n", titleGameToRemove);
    }

    private void buyItem() {
        if (userLogInDTO == null) {
            throw new NullPointerException("No User Currently logged in");
        }
        Set<GameInfoDTO> gamesInCart = userLogInDTO.getGames();
        Set<String> gamesTitle = gamesInCart.stream()
                .map(GameInfoDTO::getTitle).collect(Collectors.toSet());
        Set<Game> gamesToBuy = gamesTitle.stream()
                .map(this.gameService::getByTitle)
                .collect(Collectors.toSet());
        User userToBuy = this.userService.getById(userLogInDTO.getId());
        userToBuy.setGames(gamesToBuy);
        this.userService.register(userToBuy);
        System.out.printf("Successfully bought games:%n%s",
                gamesToBuy.stream().map(g -> " -" + g.getTitle()).collect(Collectors.joining("\n")));
        this.userLogInDTO.setGames(new LinkedHashSet<>());
    }
}
