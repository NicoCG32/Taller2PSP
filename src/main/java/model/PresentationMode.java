package model;

/**
 * Modos de presentación que el sistema puede activar como resultado
 * del proceso de adaptación.
 */
public enum PresentationMode {
    /** Muestra todo: texto, imágenes y video. */
    MULTIMEDIA,
    /** Conserva texto e imagen, desactiva videos pesados. */
    RESTRICTED,
    /** Modo mínimo: sólo texto resumido. */
    TEXT
}