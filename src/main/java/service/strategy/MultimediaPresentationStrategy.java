package service.strategy;

import model.Content;

/**
 * Estrategia que muestra la version completa del contenido educativo.
 */
public class MultimediaPresentationStrategy implements ContentPresentationStrategy {

    /**
     * Renderiza titulo, descripcion, imagen y recurso multimedia.
     */
    @Override
    public String render(Content content) {
        return "<h1>" + content.getTitle() + "</h1>" +
                "<p>Bienvenido al curso. Aqui tienes el material completo:</p>" +
                "<div><b>Texto:</b> " + content.getDescription() + "</div>" +
                "<div><b>Imagen:</b> <p>[" + content.getImageDescription() + "]</p></div>" +
                "<div><b>Video:</b> <p>[" + content.getVideoDescription() + "]</p></div>" +
                "<div><b>Recurso:</b> <p>" + content.getResourceLink() + "</p></div>";
    }
}
