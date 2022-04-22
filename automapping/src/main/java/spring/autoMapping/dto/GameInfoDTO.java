package spring.autoMapping.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GameInfoDTO {
    private String title;
    private double price;
    private String description;
    private LocalDate releaseDate;

    public GameInfoDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return String.format("Title: %s\n" +
                "Price: %.2f \n" +
                "Description: %s \n" +
                "Release date: %s\n", this.getTitle(), this.getPrice(), this.getDescription(),
                DateTimeFormatter.ofPattern("dd-MM-yyyy").format(this.getReleaseDate()));
    }
}
