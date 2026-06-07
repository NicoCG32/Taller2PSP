package app;

import knowledge.KnowledgeBase;
import mape.*;
import service.ContentService;
import web.WebServer;

/**
 * Punto de arranque de la aplicación que instancia los componentes
 * del ciclo MAPE-K y el servidor web.
 */
public class Main {

    /**
     * Punto de entrada de la aplicación.
     * Inicializa la base de conocimiento, los componentes MAPE-K, el servicio de contenido
     * y arranca el servidor web en el puerto indicado.
     *
     * @param args parámetros de línea de comandos (no usados)
     */
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