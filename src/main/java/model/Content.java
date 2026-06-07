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
    
    /**
     * Retorna el título del contenido.
     *
     * @return título del contenido
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retorna la descripción completa del contenido.
     *
     * @return descripción
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retorna un resumen breve del contenido.
     *
     * @return resumen
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Retorna una descripción alternativa de la imagen asociada.
     *
     * @return texto alternativo de la imagen
     */
    public String getImageDescription() {
        return imageDescription;
    }

    /**
     * Entrega la ruta publica de la imagen asociada al contenido.
     * 
     * @return ruta de la imagen
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Retorna una descripción del video asociado.
     *
     * @return descripción del video
     */
    public String getVideoDescription() {
        return videoDescription;
    }

    /**
     * Entrega la ruta publica del video asociado al contenido.
     *
     * @return ruta del video
     */
    public String getVideoPath() {
        return videoPath;
    }

    /**
     * Retorna el enlace a material complementario.
     *
     * @return enlace de recurso
     */
    public String getResourceLink() {
        return resourceLink;
    }
}