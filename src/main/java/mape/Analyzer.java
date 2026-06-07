package mape;

import knowledge.KnowledgeBase;
import model.DemandLevel;

/**
 * Componente Analyzer del ciclo MAPE-K.
 *
 * <p>Consulta la {@link knowledge.KnowledgeBase} para evaluar la demanda actual
 * y clasificarla en niveles (LOW, MEDIUM, HIGH) usados por el Planner.</p>
 */
public class Analyzer {
    
    private KnowledgeBase knowledge;

    /**
     * Crea un analizador que consulta la {@link KnowledgeBase} para determinar demanda.
     *
     * @param knowledge instancia compartida de KnowledgeBase
     */
    public Analyzer(KnowledgeBase knowledge) {
        this.knowledge = knowledge;
    }

    /**
     * Analiza la demanda actual basándose en el contador de solicitudes
     * y los umbrales definidos en la KnowledgeBase.
     *
     * @return el nivel de demanda calculado
     */
    public DemandLevel analyzeDemand() {
        int currentRequests = knowledge.getRequestCount();

        if (currentRequests >= knowledge.getTextThreshold()) {
            System.out.println("[ANALYZE] Alta demanda detectada.");
            return DemandLevel.HIGH;
        } else if (currentRequests >= knowledge.getRestrictedThreshold()) {
            System.out.println("[ANALYZE] Demanda media detectada.");
            return DemandLevel.MEDIUM;
        } else {
            System.out.println("[ANALYZE] Demanda baja (normal) detectada.");
            return DemandLevel.LOW;
        }
    }
}