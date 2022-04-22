package spring.autoMapping.dto;


public class GameValidationDTO {
    private String title;
    private String trailer;
    private String thumbnail;
    private double size;
    private double price;
    private String description;

    public GameValidationDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (!Character.isUpperCase(title.charAt(0)) || title.length() < 3 || title.length() > 100) {
            throw new IllegalArgumentException("Title must start with Upper case and must be between 3 and 100 symbols");
        }
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        if (trailer.length() != 11) {
            throw new IllegalArgumentException("Trailer URL must be exactly 11 symbols");
        }
        this.trailer = trailer;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        if (!thumbnail.startsWith("http://") && !thumbnail.startsWith("https://")) {
            throw new IllegalArgumentException("Invalid Thumbnail URL");
        }
        this.thumbnail = thumbnail;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() < 20) {
            throw new IllegalArgumentException("Description must be al least 20 symbols");
        }
        this.description = description;
    }
}
