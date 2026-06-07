package mape;

import knowledge.KnowledgeBase;
import model.DemandLevel;
import model.PresentationMode;

/**
 * Fachada que encapsula una iteracion completa del ciclo MAPE-K.
 */
public class MapeCycleFacade {
    
    private final KnowledgeBase knowledge;
    private final Monitor monitor;
    private final Analyzer analyzer;
    private final Planner planner;
    private final Executor executor;

    /**
     * Crea la fachada con los componentes que participan en el ciclo MAPE-K.
     */
    public MapeCycleFacade(KnowledgeBase knowledge, Monitor monitor, Analyzer analyzer, Planner planner, Executor executor) {
        this.knowledge = knowledge;
        this.monitor = monitor;
        this.analyzer = analyzer;
        this.planner = planner;
        this.executor = executor;
    }

    /**
     * Ejecuta Monitor, Analyze, Plan y Execute, y retorna el modo activo final.
     */
    public PresentationMode runCycle() {
        monitor.registerRequest();

        DemandLevel demand = analyzer.analyzeDemand();
        PresentationMode plannedMode = planner.planAdaptation(demand);
        executor.executeAdaptation(plannedMode);

        return knowledge.getCurrentMode();
    }
}