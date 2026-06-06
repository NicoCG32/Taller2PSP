package service.strategy;

import model.Content;

/**
 * Estrategia que conserva texto e imagen, pero desactiva recursos multimedia pesados.
 */
public class RestrictedPresentationStrategy implements ContentPresentationStrategy {

    /**
     * Renderiza una version intermedia del contenido para demanda media.
     */
    @Override
    public String render(Content content) {
        return "<h1>" + content.getTitle() + "</h1>" +
                "<p>Bienvenido al curso. (Modo de ahorro de recursos activo):</p>" +
                "<div><b>Texto:</b> " + content.getDescription() + "</div>" +
                "<div><b>Imagen:</b> <p>[" + content.getImageDescription() + "]</p></div>" +
                "<div><i><small>Los videos y enlaces multimedia se han desactivado para garantizar la estabilidad.</small></i></div>";
    }
}
