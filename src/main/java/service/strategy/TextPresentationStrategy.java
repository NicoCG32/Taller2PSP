package service.strategy;

import model.Content;

/**
 * Estrategia que muestra una version textual liviana durante alta demanda.
 */
public class TextPresentationStrategy implements ContentPresentationStrategy {

    /**
     * Renderiza solo el resumen y un aviso de desactivacion temporal de multimedia.
     */
    @Override
    public String render(Content content) {
        return "<h1>" + content.getTitle() + "</h1>" +
                "<p><b>Contenido en versión resumida:</b> " + content.getSummary() + "</p>" +
                "<p style='color:red;'><b>Mensaje: Las imágenes, videos y enlaces multimedia fueron desactivados temporalmente debido a alta demanda.</b></p>";
    }
}
