package web;
import io.javalin.Javalin;
import io.javalin.Javalin;
import knowledge.KnowledgeBase;
import mape.*;
import model.DemandLevel;
import model.PresentationMode;
import java.util.Map;

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
        Javalin app = Javalin.create().start(port);

        // 1. Endpoint principal: http://localhost:7000/content
        app.get("/content", ctx -> {
            // ==========================================
            // EJECUCIÓN EN CADENA DEL CICLO MAPE-K
            // ==========================================

            // M - Monitor: Registra la solicitud entrante (+1 al contador)
            monitor.registerRequest();

            // A - Analyze: Evalúa la cantidad de solicitudes contra los umbrales
            DemandLevel demand = analyzer.analyzeDemand();

            // P - Plan: Decide qué modo de presentación corresponde activar
            PresentationMode plannedMode = planner.planAdaptation(demand);

            // E - Execute: Aplica el cambio en el sistema si el modo planificado difiere del actual
            executor.executeAdaptation(plannedMode);

            // K - Knowledge: Consultamos el modo final activo para renderizar la respuesta al usuario
            PresentationMode currentMode = knowledge.getCurrentMode();

            // 2. Renderizar contenido adaptativo según el modo activo según los requerimientos
            if (currentMode == PresentationMode.MULTIMEDIA) {
                ctx.html("<h1>Plataforma Educativa</h1>" +
                        "<p>Bienvenido al curso. Aqui tienes el material completo:</p>" +
                        "<div><b>Texto:</b> Introducción a Patrones de Software.</div>" +
                        "<div><b>Imagen:</b> <p>[Imagen conceptual del ciclo MAPE-K]</p></div>" +
                        "<div><b>Video:</b> <p>[Video tutorial de la arquitectura]</p></div>");
            }
            else if (currentMode == PresentationMode.RESTRICTED) {
                ctx.html("<h1>Plataforma Educativa</h1>" +
                        "<p>Bienvenido al curso. (Modo de ahorro de recursos activo):</p>" +
                        "<div><b>Texto:</b> Introducción a Patrones de Software.</div>" +
                        "<div><b>Imagen:</b> <p>[Imagen conceptual del ciclo MAPE-K]</p></div>" +
                        "<div><i><small>Los videos y enlaces multimedia se han desactivado para garantizar la estabilidad.</small></i></div>");
            }
            else { // Modo TEXT
                ctx.html("<h1>Plataforma Educativa</h1>" +
                        "<p><b>Contenido en versión resumida:</b> Introducción a Patrones de Software.</p>" +
                        "<p style='color:red;'><b>Mensaje: Las imágenes, videos y enlaces multimedia fueron desactivados temporalmente debido a alta demanda.</b></p>");
            }
        });

        // 2. Endpoint de estado: http://localhost:7000/status
        app.get("/status", ctx -> {
            // Retorna un JSON estructurado con los valores actuales almacenados en Knowledge
            ctx.json(Map.of(
                    "mode", knowledge.getCurrentMode().name(),
                    "requests", knowledge.getRequestCount(),
                    "restrictedThreshold", knowledge.getRestrictedThreshold(),
                    "textThreshold", knowledge.getTextThreshold()
            ));
        });

        // 3. Endpoint de reinicio: http://localhost:7000/reset
        app.get("/reset", ctx -> {
            // Reinicia los contadores y vuelve al modo inicial
            knowledge.reset();
            System.out.println("[SYSTEM] Simulación reiniciada manualmente.");
            ctx.result("Simulación reiniciada. Contador en cero y sistema en modo MULTIMEDIA.");
        });
    }
}