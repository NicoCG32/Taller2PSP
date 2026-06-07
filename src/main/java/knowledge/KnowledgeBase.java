package knowledge;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import model.PresentationMode;

/**
 * Base de conocimiento compartida por los componentes del ciclo MAPE-K.
 */
public class KnowledgeBase {
    private static KnowledgeBase instance;

    // Estado actual del sistema
    private int requestCount = 0;
    private PresentationMode currentMode = PresentationMode.MULTIMEDIA;

    // Umbrales de configuración
    private int restrictedThreshold;
    private int textThreshold;

    /**
     * Crea la base de conocimiento y carga sus parametros de configuracion.
     */
    private KnowledgeBase() {
        cargarConfiguracion();
    }

    /**
     * Entrega la unica instancia compartida de la base de conocimiento.
     *
     * @return instancia singleton de {@link KnowledgeBase}
     */
    public static synchronized KnowledgeBase getInstance() {
        if (instance == null) {
            instance = new KnowledgeBase();
        }
        return instance;
    }

    /**
     * Carga los umbrales y parámetros desde el archivo `config.properties`.
     * Si no es posible leer el archivo, se aplican valores por defecto.
     */
    private void cargarConfiguracion() {
        Properties propiedades = new Properties();
        try (FileInputStream entrada = new FileInputStream("config.properties")) {
            propiedades.load(entrada);

            // Leemos los valores del archivo. Si algo falla, usamos 10 y 20 por defecto.
            this.restrictedThreshold = Integer.parseInt(propiedades.getProperty("restricted.threshold", "10"));
            this.textThreshold = Integer.parseInt(propiedades.getProperty("text.threshold", "20"));

            System.out.println("[KNOWLEDGE] Configuración cargada: Restringido=" + restrictedThreshold + ", Texto=" + textThreshold);
        } catch (IOException e) {
            System.err.println("[KNOWLEDGE] Error leyendo config.properties. Usando valores por defecto.");
            this.restrictedThreshold = 10;
            this.textThreshold = 20;
        }
    }

    // Métodos para alterar el estado
    /**
     * Incrementa el contador de solicitudes registradas.
     * Este contador es usado por el Analyzer para estimar la demanda.
     */
    public synchronized void addRequest() {
        this.requestCount++;
    }
    /**
     * Reinicia el estado de la base de conocimiento a valores iniciales.
     * Reinicia el contador de solicitudes y vuelve al modo MULTIMEDIA.
     */
    public synchronized void reset() {
        this.requestCount = 0;
        this.currentMode = PresentationMode.MULTIMEDIA;
    }
    // Getters y Setters
    /**
     * Retorna el número de solicitudes registradas hasta el momento.
     *
     * @return contador de solicitudes
     */
    public synchronized int getRequestCount() { return requestCount; }

    /**
     * Retorna el modo de presentación actualmente activo.
     *
     * @return modo actual de presentación
     */
    public synchronized PresentationMode getCurrentMode() { return currentMode; }

    /**
     * Establece el modo de presentación actual en la base de conocimiento.
     *
     * @param mode nuevo modo de presentación
     */
    public synchronized void setCurrentMode(PresentationMode mode) { this.currentMode = mode; }

    /**
     * Retorna el umbral configurado para activar el modo RESTRICTED.
     *
     * @return umbral para modo restringido
     */
    public int getRestrictedThreshold() { return restrictedThreshold; }

    /**
     * Retorna el umbral configurado para activar el modo TEXT.
     *
     * @return umbral para modo texto
     */
    public int getTextThreshold() { return textThreshold; }
}