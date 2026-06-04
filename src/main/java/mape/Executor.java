package mape;

import knowledge.KnowledgeBase;
import model.PresentationMode;

//EJECUTA LA ADAPTACION CAMBIANDO EL MODO DE FUNCIONAMIENTO DEL SISTEMA
public class Executor {
    private KnowledgeBase knowledge;

    public Executor(KnowledgeBase knowledge) {
        this.knowledge = knowledge;
    }

    public void executeAdaptation(PresentationMode plannedMode) {
        // Solo cambiamos y avisamos si el modo realmente es diferente al actual
        if (knowledge.getCurrentMode() != plannedMode) {
            knowledge.setCurrentMode(plannedMode);
            System.out.println("[EXECUTE] El sistema cambio a modo " + plannedMode);
        }
    }
}