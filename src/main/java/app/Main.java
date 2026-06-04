package app;

import knowledge.KnowledgeBase;
import mape.*;
import web.WebServer;

public class Main {
    public static void main(String[] args) {
        // 1. Inicializar el componente de Conocimiento (Knowledge)
        KnowledgeBase knowledge = new KnowledgeBase();

        // 2. Inicializar los componentes del ciclo MAPE compartiendo la misma base de conocimiento
        Monitor monitor = new Monitor(knowledge);
        Analyzer analyzer = new Analyzer(knowledge);
        Planner planner = new Planner();
        Executor executor = new Executor(knowledge);

        // 3. Crear e iniciar el servidor web inyectando las dependencias
        WebServer webServer = new WebServer(knowledge, monitor, analyzer, planner, executor);
        webServer.start(7000);
    }
}