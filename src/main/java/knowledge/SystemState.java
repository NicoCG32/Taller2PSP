package knowledge;

import model.PresentationMode;

/**
 * Representa el estado dinamico observado y modificado durante el ciclo MAPE-K.
 *
 * <p>Contiene los datos que cambian durante la ejecucion de la aplicacion:
 * contador de solicitudes y modo de presentacion activo.</p>
 */
public class SystemState {

    private int requestCount;
    private PresentationMode currentMode;

    /**
     * Crea un estado inicial en modo multimedia y sin solicitudes registradas.
     */
    public SystemState() {
        reset();
    }

    /**
     * Incrementa el contador de solicitudes observadas por el Monitor.
     */
    public void incrementRequestCount() {
        requestCount++;
    }

    /**
     * Reinicia el estado dinamico del sistema.
     */
    public void reset() {
        requestCount = 0;
        currentMode = PresentationMode.MULTIMEDIA;
    }

    /**
     * Retorna la cantidad de solicitudes registradas.
     *
     * @return contador de solicitudes
     */
    public int getRequestCount() {
        return requestCount;
    }

    /**
     * Retorna el modo de presentacion actualmente activo.
     *
     * @return modo activo
     */
    public PresentationMode getCurrentMode() {
        return currentMode;
    }

    /**
     * Actualiza el modo de presentacion activo.
     *
     * @param currentMode nuevo modo activo
     */
    public void setCurrentMode(PresentationMode currentMode) {
        this.currentMode = currentMode;
    }
}