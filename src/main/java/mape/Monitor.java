package mape;

import knowledge.KnowledgeBase;
//REGISTRA INFORMACION SOBRE EL ESTADO DEL SISTEMA
public class Monitor {
    private KnowledgeBase knowledge;

    public Monitor(KnowledgeBase knowledge) {
        this.knowledge = knowledge;
    }

    public void registerRequest() {
        knowledge.addRequest();
        System.out.println("[MONITOR] Solicitud registrada. Total actual: " + knowledge.getRequestCount());
    }
}