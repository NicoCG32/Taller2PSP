package mape;

import knowledge.KnowledgeBase;
import model.PresentationMode;

/**
 * Componente Executor del ciclo MAPE-K.
 *
 * <p>Aplica el plan decidido por el Planner modificando el estado en la
 * {@link knowledge.KnowledgeBase}, p. ej. cambiando el modo de presentación.</p>
 */
public class Executor {
    
    private KnowledgeBase knowledge;

    /**
     * Crea un ejecutor que aplicará las adaptaciones modificando la KnowledgeBase.
     *
     * @param knowledge instancia compartida de KnowledgeBase
     */
    public Executor(KnowledgeBase knowledge) {
        this.knowledge = knowledge;
    }

    /**
     * Aplica el modo planificado en la KnowledgeBase si difiere del modo actual.
     *
     * @param plannedMode modo de presentación planificado por el Planner
     */
    public void executeAdaptation(PresentationMode plannedMode) {
        // Solo cambiamos y avisamos si el modo realmente es diferente al actual
        if (knowledge.getCurrentMode() != plannedMode) {
            knowledge.setCurrentMode(plannedMode);
            System.out.println("[EXECUTE] El sistema cambio a modo " + plannedMode);
        }
    }
}