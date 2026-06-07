package knowledge;

/**
 * Representa los umbrales usados para decidir la adaptacion del contenido.
 *
 * <p>Es una configuracion inmutable cargada por {@link KnowledgeBase} desde
 * `config.properties` o desde valores por defecto.</p>
 */
public class AdaptationConfig {

    private final int restrictedThreshold;
    private final int textThreshold;

    /**
     * Crea una configuracion de adaptacion con los umbrales indicados.
     *
     * @param restrictedThreshold umbral para activar modo restringido
     * @param textThreshold umbral para activar modo texto
     */
    public AdaptationConfig(int restrictedThreshold, int textThreshold) {
        this.restrictedThreshold = restrictedThreshold;
        this.textThreshold = textThreshold;
    }

    /**
     * Retorna el umbral de solicitudes para activar modo RESTRICTED.
     *
     * @return umbral de modo restringido
     */
    public int getRestrictedThreshold() {
        return restrictedThreshold;
    }

    /**
     * Retorna el umbral de solicitudes para activar modo TEXT.
     *
     * @return umbral de modo texto
     */
    public int getTextThreshold() {
        return textThreshold;
    }
}