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
     */
    public static synchronized KnowledgeBase getInstance() {
        if (instance == null) {
            instance = new KnowledgeBase();
        }
        return instance;
    }

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
    public synchronized void addRequest() {
        this.requestCount++;
    }

    public synchronized void reset() {
        this.requestCount = 0;
        this.currentMode = PresentationMode.MULTIMEDIA;
    }

    // Getters y Setters
    public synchronized int getRequestCount() { return requestCount; }

    public synchronized PresentationMode getCurrentMode() { return currentMode; }
    public synchronized void setCurrentMode(PresentationMode mode) { this.currentMode = mode; }

    public int getRestrictedThreshold() { return restrictedThreshold; }
    public int getTextThreshold() { return textThreshold; }
}
