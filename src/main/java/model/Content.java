package model;

/**
 * Representa el contenido educativo base que sera adaptado segun la demanda.
 */
public class Content {
    private final String title;
    private final String description;
    private final String summary;
    private final String imageDescription;
    private final String videoDescription;
    private final String resourceLink;

    /**
     * Crea un contenido educativo con sus variantes textuales y multimedia.
     */
    public Content(String title, String description, String summary, String imageDescription, String videoDescription, String resourceLink) {
        this.title = title;
        this.description = description;
        this.summary = summary;
        this.imageDescription = imageDescription;
        this.videoDescription = videoDescription;
        this.resourceLink = resourceLink;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSummary() {
        return summary;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public String getResourceLink() {
        return resourceLink;
    }
}
