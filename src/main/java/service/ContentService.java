package service;

import model.Content;
import model.PresentationMode;
import service.strategy.ContentPresentationStrategy;
import service.strategy.MultimediaPresentationStrategy;
import service.strategy.RestrictedPresentationStrategy;
import service.strategy.TextPresentationStrategy;

/**
 * Servicio que obtiene el contenido educativo y lo renderiza segun el modo activo.
 */
public class ContentService {
    private final ContentPresentationStrategy multimediaStrategy;
    private final ContentPresentationStrategy restrictedStrategy;
    private final ContentPresentationStrategy textStrategy;

    /**
     * Crea el servicio con las estrategias de presentacion disponibles.
     */
    public ContentService() {
        this.multimediaStrategy = new MultimediaPresentationStrategy();
        this.restrictedStrategy = new RestrictedPresentationStrategy();
        this.textStrategy = new TextPresentationStrategy();
    }

    /**
     * Renderiza el contenido educativo usando la estrategia asociada al modo activo.
     */
    public String renderContent(PresentationMode mode) {
        Content content = getEducationalContent();
        return selectStrategy(mode).render(content);
    }

    /**
     * Entrega el contenido base usado por las distintas estrategias.
     */
    private Content getEducationalContent() {
        return new Content(
                "Plataforma Educativa",
                "Introducción a Patrones de Software.",
                "Introducción a Patrones de Software.",
                "Imagen conceptual del ciclo MAPE-K",
                "Video tutorial de la arquitectura",
                "Material complementario sobre sistemas autoadaptativos"
        );
    }

    /**
     * Selecciona la estrategia que corresponde al modo de presentacion activo.
     */
    private ContentPresentationStrategy selectStrategy(PresentationMode mode) {
        return switch (mode) {
            case RESTRICTED -> restrictedStrategy;
            case TEXT -> textStrategy;
            case MULTIMEDIA -> multimediaStrategy;
        };
    }
}
