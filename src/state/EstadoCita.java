package state;

/**
 * PATRÓN STATE - "El Estado"
 * 
 * Función: Modela el ciclo de vida completo de una cita médica.
 * Cada estado encapsula las transiciones válidas, evitando
 * condiciones if/else distribuidas por todo el código.
 * 
 * Módulo: Gestión de Agenda / Workflow de Citas
 * 
 * Flujo válido:
 * PROGRAMADA → CONFIRMADA → EN_ATENCION → COMPLETADA
 *      ↓            ↓
 * CANCELADA    CANCELADA
 *      ↓
 * REPROGRAMADA → (vuelve a PROGRAMADA)
 */
public interface EstadoCita {
    void confirmar(ContextoCita contexto);
    void iniciarAtencion(ContextoCita contexto);
    void completar(ContextoCita contexto);
    void cancelar(ContextoCita contexto);
    void reprogramar(ContextoCita contexto);
    String getNombre();
}
