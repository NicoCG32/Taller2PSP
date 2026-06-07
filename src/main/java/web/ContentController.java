package web;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.Map;
import knowledge.KnowledgeBase;
import mape.MapeCycleFacade;
import model.PresentationMode;
import service.ContentService;

public class ContentController {

    private static final String HTML_UTF8 = "text/html; charset=UTF-8";
    private static final String TEXT_UTF8 = "text/plain; charset=UTF-8";

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
        app.get("/", this::getHome);
        app.get("/content", this::getContent);
        app.get("/status", this::getStatus);
        app.get("/reset", this::resetSimulation);
    }

    /**
     * Muestra una pagina inicial con accesos a los endpoints principales.
     */
    private void getHome(Context ctx) {
        sendHtml(ctx, """
                <!doctype html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Sistema MAPE-K</title>
                    <style>
                        body {
                            margin: 0;
                            background: #f4f6f8;
                            color: #1f2933;
                            font-family: Arial, Helvetica, sans-serif;
                        }

                        main {
                            max-width: 760px;
                            margin: 0 auto;
                            padding: 48px 20px;
                        }

                        h1 {
                            margin: 0 0 12px;
                            font-size: 32px;
                        }

                        p {
                            line-height: 1.6;
                        }

                        nav {
                            display: grid;
                            gap: 12px;
                            margin-top: 24px;
                        }

                        a {
                            display: block;
                            border: 1px solid #d8dee6;
                            border-radius: 8px;
                            background: #ffffff;
                            color: #12624f;
                            padding: 14px 16px;
                            font-weight: 700;
                            text-decoration: none;
                        }

                        a:hover {
                            border-color: #27856f;
                            background: #e4f4ee;
                        }
                    </style>
                </head>
                <body>
                    <main>
                        <h1>Sistema web autoadaptativo MAPE-K</h1>
                        <p>Selecciona un endpoint para probar la adaptación del contenido y consultar el estado actual del sistema.</p>
                        <nav>
                            <a href="/content">Ver contenido adaptativo</a>
                            <a href="/status">Consultar estado del sistema</a>
                            <a href="/reset">Reiniciar simulación</a>
                        </nav>
                    </main>
                </body>
                </html>
                """);
    }

    /**
     * Atiende la solicitud principal ejecutando el ciclo MAPE-K antes de responder.
     */
    private void getContent(Context ctx) {
        PresentationMode currentMode = mapeCycleFacade.runCycle();
        sendHtml(ctx, contentService.renderContent(currentMode));
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
        ctx.header("Content-Type", TEXT_UTF8);
        ctx.result("Simulación reiniciada. Contador en cero y sistema en modo MULTIMEDIA.");
    }

    /**
     * Envia HTML indicando explicitamente UTF-8 para conservar tildes y caracteres en español.
     */
    private void sendHtml(Context ctx, String html) {
        ctx.header("Content-Type", HTML_UTF8);
        ctx.result(html);
    }
}