package service;

import model.Content;
import model.ContentResource;
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
                "Introducción a sistemas autoadaptativos basados en MAPE-K. El contenido completo incluye una imagen principal, un video principal, tres imágenes complementarias y tres videos complementarios.",
                "Introducción breve a MAPE-K y a la adaptación del contenido según demanda.",
                new ContentResource("Imagen principal", "Imagen conceptual del ciclo MAPE-K", "/images/mape-k.svg"),
                new ContentResource("Video principal", "Video introductorio disponible en el proyecto", "/videos/UceninHacker.mp4"),
                java.util.List.of(
                        new ContentResource("Imagen complementaria 1", "Recurso visual complementario img1.png", "/images/img1.png"),
                        new ContentResource("Imagen complementaria 2", "Recurso visual complementario img2.png", "/images/img2.png"),
                        new ContentResource("Imagen complementaria 3", "Recurso visual complementario img3.png", "/images/img3.png")
                ),
                java.util.List.of(
                        new ContentResource("Video complementario 1", "Recurso audiovisual complementario video1.mp4", "/videos/video1.mp4"),
                        new ContentResource("Video complementario 2", "Recurso audiovisual complementario video2.mp4", "/videos/video2.mp4"),
                        new ContentResource("Video complementario 3", "Recurso audiovisual complementario video3.mp4", "/videos/video3.mp4")
                ),
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