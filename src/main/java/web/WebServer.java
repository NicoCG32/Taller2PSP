package web;
import io.javalin.Javalin;
import knowledge.KnowledgeBase;
import mape.*;

public class WebServer {
    private final KnowledgeBase knowledge;
    private final Monitor monitor;
    private final Analyzer analyzer;
    private final Planner planner;
    private final Executor executor;

    // El constructor recibe todas las instancias creadas en el Main
    public WebServer(KnowledgeBase knowledge, Monitor monitor, Analyzer analyzer, Planner planner, Executor executor) {
        this.knowledge = knowledge;
        this.monitor = monitor;
        this.analyzer = analyzer;
        this.planner = planner;
        this.executor = executor;
    }

    public void start(int port) {
        Javalin app = Javalin.create();
        ContentController contentController = new ContentController(knowledge, monitor, analyzer, planner, executor);

        contentController.registerRoutes(app);
        app.start(port);
    }
}
