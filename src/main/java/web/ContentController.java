package web;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.Map;
import knowledge.KnowledgeBase;
import mape.Analyzer;
import mape.Executor;
import mape.Monitor;
import mape.Planner;
import model.DemandLevel;
import model.PresentationMode;

public class ContentController {
    private final KnowledgeBase knowledge;
    private final Monitor monitor;
    private final Analyzer analyzer;
    private final Planner planner;
    private final Executor executor;

    /**
     * Crea el controlador responsable de exponer los endpoints HTTP del sistema.
     */
    public ContentController(KnowledgeBase knowledge, Monitor monitor, Analyzer analyzer, Planner planner, Executor executor) {
        this.knowledge = knowledge;
        this.monitor = monitor;
        this.analyzer = analyzer;
        this.planner = planner;
        this.executor = executor;
    }

    /**
     * Registra las rutas web asociadas al contenido adaptativo y al estado del sistema.
     */
    public void registerRoutes(Javalin app) {
        app.get("/content", this::getContent);
        app.get("/status", this::getStatus);
        app.get("/reset", this::resetSimulation);
    }

    /**
     * Atiende la solicitud principal ejecutando el ciclo MAPE-K antes de responder.
     */
    private void getContent(Context ctx) {
        monitor.registerRequest();

        DemandLevel demand = analyzer.analyzeDemand();
        PresentationMode plannedMode = planner.planAdaptation(demand);
        executor.executeAdaptation(plannedMode);

        PresentationMode currentMode = knowledge.getCurrentMode();
        ctx.html(renderContent(currentMode));
    }

    /**
     * Entrega el estado actual almacenado en Knowledge.
     */
    private void getStatus(Context ctx) {
        ctx.json(Map.of(
                "mode", knowledge.getCurrentMode().name(),
                "requests", knowledge.getRequestCount(),
                "restrictedThreshold", knowledge.getRestrictedThreshold(),
                "textThreshold", knowledge.getTextThreshold()
        ));
    }

    /**
     * Reinicia la simulacion de carga y restaura el modo multimedia.
     */
    private void resetSimulation(Context ctx) {
        knowledge.reset();
        System.out.println("[SYSTEM] Simulación reiniciada manualmente.");
        ctx.result("Simulación reiniciada. Contador en cero y sistema en modo MULTIMEDIA.");
    }

    /**
     * Construye la respuesta HTML segun el modo de presentacion activo.
     */
    private String renderContent(PresentationMode currentMode) {
        if (currentMode == PresentationMode.MULTIMEDIA) {
            return "<h1>Plataforma Educativa</h1>" +
                    "<p>Bienvenido al curso. Aqui tienes el material completo:</p>" +
                    "<div><b>Texto:</b> Introducción a Patrones de Software.</div>" +
                    "<div><b>Imagen:</b> <p>[Imagen conceptual del ciclo MAPE-K]</p></div>" +
                    "<div><b>Video:</b> <p>[Video tutorial de la arquitectura]</p></div>";
        }

        if (currentMode == PresentationMode.RESTRICTED) {
            return "<h1>Plataforma Educativa</h1>" +
                    "<p>Bienvenido al curso. (Modo de ahorro de recursos activo):</p>" +
                    "<div><b>Texto:</b> Introducción a Patrones de Software.</div>" +
                    "<div><b>Imagen:</b> <p>[Imagen conceptual del ciclo MAPE-K]</p></div>" +
                    "<div><i><small>Los videos y enlaces multimedia se han desactivado para garantizar la estabilidad.</small></i></div>";
        }

        return "<h1>Plataforma Educativa</h1>" +
                "<p><b>Contenido en versión resumida:</b> Introducción a Patrones de Software.</p>" +
                "<p style='color:red;'><b>Mensaje: Las imágenes, videos y enlaces multimedia fueron desactivados temporalmente debido a alta demanda.</b></p>";
    }
}
