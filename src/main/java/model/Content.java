package model;

import java.util.List;

/**
 * Representa el contenido educativo base que sera adaptado segun la demanda.
 */
public class Content {

    private final String title;
    private final String description;
    private final String summary;
    private final ContentResource mainImage;
    private final ContentResource mainVideo;
    private final List<ContentResource> images;
    private final List<ContentResource> videos;
    private final String resourceLink;

    /**
     * Crea un contenido educativo con sus variantes textuales y multimedia.
     */
    public Content(String title, String description, String summary, ContentResource mainImage, ContentResource mainVideo, List<ContentResource> images, List<ContentResource> videos, String resourceLink) {
        this.title = title;
        this.description = description;
        this.summary = summary;
        this.mainImage = mainImage;
        this.mainVideo = mainVideo;
        this.images = List.copyOf(images);
        this.videos = List.copyOf(videos);
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
        return mainImage.getDescription();
    }

    /**
     * Entrega la ruta publica de la imagen asociada al contenido.
     * 
     * @return ruta de la imagen
     */
    public String getImagePath() {
        return mainImage.getPath();
    }

    /**
     * Retorna una descripción del video asociado.
     *
     * @return descripción del video
     */
    public String getVideoDescription() {
        return mainVideo.getDescription();
    }

    /**
     * Entrega la ruta publica del video asociado al contenido.
     *
     * @return ruta del video
     */
    public String getVideoPath() {
        return mainVideo.getPath();
    }

    /**
     * Retorna la imagen principal del contenido.
     *
     * @return imagen principal
     */
    public ContentResource getMainImage() {
        return mainImage;
    }

    /**
     * Retorna el video principal del contenido.
     *
     * @return video principal
     */
    public ContentResource getMainVideo() {
        return mainVideo;
    }

    /**
     * Retorna las imagenes complementarias del contenido.
     *
     * @return imagenes complementarias
     */
    public List<ContentResource> getImages() {
        return images;
    }

    /**
     * Retorna los videos complementarios del contenido.
     *
     * @return videos complementarios
     */
    public List<ContentResource> getVideos() {
        return videos;
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