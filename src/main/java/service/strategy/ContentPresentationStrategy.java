package service.strategy;

import model.Content;

/**
 * Define una estrategia de presentacion para adaptar visualmente un contenido.
 */
public interface ContentPresentationStrategy {

    /**
     * Renderiza el contenido educativo segun una variante de presentacion.
     */
    String render(Content content);
}
