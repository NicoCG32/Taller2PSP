package model;

/**
 * Representa un recurso multimedia asociado a un contenido educativo.
 */
public class ContentResource {

    private final String title;
    private final String description;
    private final String path;

    /**
     * Crea un recurso con titulo, descripcion y ruta publica.
     *
     * @param title titulo visible del recurso
     * @param description descripcion breve del recurso
     * @param path ruta publica del recurso
     */
    public ContentResource(String title, String description, String path) {
        this.title = title;
        this.description = description;
        this.path = path;
    }

    /**
     * Retorna el titulo visible del recurso.
     *
     * @return titulo del recurso
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retorna la descripcion breve del recurso.
     *
     * @return descripcion del recurso
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retorna la ruta publica del recurso.
     *
     * @return ruta del recurso
     */
    public String getPath() {
        return path;
    }
}