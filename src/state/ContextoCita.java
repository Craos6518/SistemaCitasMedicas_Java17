package state;

import model.CitaMedica;

/**
 * PATRÓN STATE - Context
 * 
 * Función: Es la fachada que el cliente usa para interactuar con
 * la cita. Delega todas las acciones al estado actual.
 * El estado puede cambiar en cualquier momento.
 * 
 * Módulo: Recepción / Agenda — los recepcionistas interactúan
 * con este objeto para gestionar el ciclo de vida de la cita.
 */
public class ContextoCita {

    private EstadoCita estadoActual;
    private final CitaMedica cita;

    public ContextoCita(CitaMedica cita) {
        this.cita = cita;
        this.estadoActual = new EstadoProgramada(); // estado inicial
        System.out.println("  [State] Cita creada en estado: " + estadoActual.getNombre());
    }

    // ── Cambio de estado (llamado desde los ConcreteStates) ──────────────────
    public void cambiarEstado(EstadoCita nuevoEstado) {
        System.out.printf("  [State] Transición: %s → %s%n",
                estadoActual.getNombre(), nuevoEstado.getNombre());
        this.estadoActual = nuevoEstado;
    }

    // ── Acciones que el cliente puede invocar ─────────────────────────────────
    public void confirmar()       { estadoActual.confirmar(this); }
    public void iniciarAtencion() { estadoActual.iniciarAtencion(this); }
    public void completar()       { estadoActual.completar(this); }
    public void cancelar()        { estadoActual.cancelar(this); }
    public void reprogramar()     { estadoActual.reprogramar(this); }

    public String getEstadoActual() { return estadoActual.getNombre(); }
    public CitaMedica getCita() { return cita; }
}
