package app;

import knowledge.KnowledgeBase;
import mape.*;
import service.ContentService;
import web.WebServer;

public class Main {
    public static void main(String[] args) {
        // 1. Inicializar el componente de Conocimiento (Knowledge)
        KnowledgeBase knowledge = KnowledgeBase.getInstance();

        // 2. Inicializar los componentes del ciclo MAPE compartiendo la misma base de conocimiento
        Monitor monitor = new Monitor(knowledge);
        Analyzer analyzer = new Analyzer(knowledge);
        Planner planner = new Planner();
        Executor executor = new Executor(knowledge);
        MapeCycleFacade mapeCycleFacade = new MapeCycleFacade(knowledge, monitor, analyzer, planner, executor);
        ContentService contentService = new ContentService();

        // 3. Crear e iniciar el servidor web inyectando las dependencias
        WebServer webServer = new WebServer(knowledge, mapeCycleFacade, contentService);
        webServer.start(7000);
    }
}
