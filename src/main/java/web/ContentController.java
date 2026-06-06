package web;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.Map;
import knowledge.KnowledgeBase;
import mape.MapeCycleFacade;
import model.PresentationMode;
import service.ContentService;

public class ContentController {
    private final KnowledgeBase knowledge;
    private final MapeCycleFacade mapeCycleFacade;
    private final ContentService contentService;

    /**
     * Crea el controlador responsable de exponer los endpoints HTTP del sistema.
     */
    public ContentController(KnowledgeBase knowledge, MapeCycleFacade mapeCycleFacade, ContentService contentService) {
        this.knowledge = knowledge;
        this.mapeCycleFacade = mapeCycleFacade;
        this.contentService = contentService;
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
        PresentationMode currentMode = mapeCycleFacade.runCycle();
        ctx.html(contentService.renderContent(currentMode));
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
}
