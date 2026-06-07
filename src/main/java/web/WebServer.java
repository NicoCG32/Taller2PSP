package web;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import knowledge.KnowledgeBase;
import mape.MapeCycleFacade;
import service.ContentService;

/**
 * Servidor web encargado de exponer los endpoints de la aplicación.
 *
 * <p>Configura Javalin, registra rutas y sirve recursos estáticos (imágenes/videos)
 * necesarios para las vistas adaptadas.</p>
 */
public class WebServer {

    private final KnowledgeBase knowledge;
    private final MapeCycleFacade mapeCycleFacade;
    private final ContentService contentService;

    // El constructor recibe todas las instancias creadas en el Main
    /**
     * Construye el servidor web con las dependencias necesarias para atender
     * las rutas de contenido y el ciclo MAPE-K.
     *
     * @param knowledge instancia compartida de KnowledgeBase
     * @param mapeCycleFacade fachada que ejecuta el ciclo MAPE-K
     * @param contentService servicio para renderizar contenido adaptado
     */
    public WebServer(KnowledgeBase knowledge, MapeCycleFacade mapeCycleFacade, ContentService contentService) {
        this.knowledge = knowledge;
        this.mapeCycleFacade = mapeCycleFacade;
        this.contentService = contentService;
    }

    /**
     * Inicia el servidor HTTP en el puerto especificado y registra las rutas.
     *
     * @param port puerto donde arrancará el servidor
     */
    public void start(int port) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/images";
                staticFiles.directory = "/images";
                staticFiles.location = Location.CLASSPATH;
            });
            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/videos";
                staticFiles.directory = "/videos";
                staticFiles.location = Location.CLASSPATH;
            });
        });
        ContentController contentController = new ContentController(knowledge, mapeCycleFacade, contentService);

        contentController.registerRoutes(app);
        app.start(port);
    }
}