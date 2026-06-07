package mape;

import knowledge.KnowledgeBase;

/**
 * Monitor del sistema — registra eventos y métricas relevantes.
 *
 * <p>Este componente centraliza información sobre las solicitudes entrantes
 * y la almacena en la {@link knowledge.KnowledgeBase} para que el resto del
 * ciclo MAPE-K la utilice.</p>
 */
public class Monitor {

    private KnowledgeBase knowledge;

    /**
     * Crea un monitor que registra eventos en la KnowledgeBase.
     *
     * @param knowledge instancia compartida de KnowledgeBase
     */
    public Monitor(KnowledgeBase knowledge) {
        this.knowledge = knowledge;
    }

    /**
     * Registra una nueva petición en la KnowledgeBase incrementando el contador.
     */
    public void registerRequest() {
        knowledge.addRequest();
        System.out.println("[MONITOR] Solicitud registrada. Total actual: " + knowledge.getRequestCount());
    }
}