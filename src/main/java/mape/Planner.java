package mape;

import model.DemandLevel;
import model.PresentationMode;

//DECIDE EL PLAN DE ACCION A TOMAR SEGUN EL NIVEL DE DEMANDA DETECTADO
public class Planner {
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