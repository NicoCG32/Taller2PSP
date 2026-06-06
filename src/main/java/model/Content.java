package model;

/**
 * Representa el contenido educativo base que sera adaptado segun la demanda.
 */
public class Content {
    private final String title;
    private final String description;
    private final String summary;
    private final String imageDescription;
    private final String imagePath;
    private final String videoDescription;
    private final String videoPath;
    private final String resourceLink;

    /**
     * Crea un contenido educativo con sus variantes textuales y multimedia.
     */
    public Content(String title, String description, String summary, String imageDescription, String imagePath, String videoDescription, String videoPath, String resourceLink) {
        this.title = title;
        this.description = description;
        this.summary = summary;
        this.imageDescription = imageDescription;
        this.imagePath = imagePath;
        this.videoDescription = videoDescription;
        this.videoPath = videoPath;
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

    /**
     * Entrega la ruta publica de la imagen asociada al contenido.
     */
    public String getImagePath() {
        return imagePath;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    /**
     * Entrega la ruta publica del video asociado al contenido.
     */
    public String getVideoPath() {
        return videoPath;
    }

    public String getResourceLink() {
        return resourceLink;
    }
}
