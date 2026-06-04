package knowledge;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import model.PresentationMode;

public class KnowledgeBase {
    // Estado actual del sistema
    private int requestCount = 0;
    private PresentationMode currentMode = PresentationMode.MULTIMEDIA;

    // Umbrales de configuración
    private int restrictedThreshold;
    private int textThreshold;

    public KnowledgeBase() {
        cargarConfiguracion();
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
    public void addRequest() {
        this.requestCount++;
    }

    public void reset() {
        this.requestCount = 0;
        this.currentMode = PresentationMode.MULTIMEDIA;
    }

    // Getters y Setters
    public int getRequestCount() { return requestCount; }

    public PresentationMode getCurrentMode() { return currentMode; }
    public void setCurrentMode(PresentationMode mode) { this.currentMode = mode; }

    public int getRestrictedThreshold() { return restrictedThreshold; }
    public int getTextThreshold() { return textThreshold; }
}