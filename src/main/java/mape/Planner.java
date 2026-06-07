package mape;

import model.DemandLevel;
import model.PresentationMode;

/**
 * Planner del ciclo MAPE-K.
 *
 * <p>Convierte un {@link model.DemandLevel} en un {@link model.PresentationMode}
 * que represente el plan de adaptación a aplicar.</p>
 */
public class Planner {
    
    /**
     * Decide el modo de presentación a aplicar según el nivel de demanda.
     *
     * @param demand nivel de demanda detectado por el Analyzer
     * @return modo de presentación seleccionado
     */
    public PresentationMode planAdaptation(DemandLevel demand) {
        switch (demand) {
            case HIGH:
                System.out.println("[PLAN] Activar modo texto.");
                return PresentationMode.TEXT;
            case MEDIUM:
                System.out.println("[PLAN] Activar modo restringido.");
                return PresentationMode.RESTRICTED;
            case LOW:
            default:
                System.out.println("[PLAN] Mantener/Activar modo multimedia.");
                return PresentationMode.MULTIMEDIA;
        }
    }
}