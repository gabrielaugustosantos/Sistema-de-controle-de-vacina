package vacinahub.domain;

/**
 * Enumeração que define os estados possíveis de uma dose no ciclo vacinal.
 * Utilizado para facilitar filtros e regras de negócio no sistema.
 */
public enum DoseStatus {
    PENDENTE, 
    APLICADA, 
    ATRASADA
}