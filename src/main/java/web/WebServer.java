package web;
import io.javalin.Javalin;
import knowledge.KnowledgeBase;
import mape.MapeCycleFacade;
import service.ContentService;

public class WebServer {
    private final KnowledgeBase knowledge;
    private final MapeCycleFacade mapeCycleFacade;
    private final ContentService contentService;

    // El constructor recibe todas las instancias creadas en el Main
    public WebServer(KnowledgeBase knowledge, MapeCycleFacade mapeCycleFacade, ContentService contentService) {
        this.knowledge = knowledge;
        this.mapeCycleFacade = mapeCycleFacade;
        this.contentService = contentService;
    }

    public void start(int port) {
        Javalin app = Javalin.create();
        ContentController contentController = new ContentController(knowledge, mapeCycleFacade, contentService);

        contentController.registerRoutes(app);
        app.start(port);
    }
}
