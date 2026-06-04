package mape;

import knowledge.KnowledgeBase;
import model.DemandLevel;

// DETERMINA EL NIVEL DE DEMANDA A PARTIR DE LOS DATOS RECOPILADOS POR EL MONITOR, Y LO DEFINIDO EN KNOWLEDGE
public class Analyzer {
    private KnowledgeBase knowledge;

    public Analyzer(KnowledgeBase knowledge) {
        this.knowledge = knowledge;
    }

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