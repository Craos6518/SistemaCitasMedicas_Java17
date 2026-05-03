package memento;

import model.CitaMedica;
import model.TipoCita;
import java.time.LocalDateTime;

/**
 * PATRÓN MEMENTO - "La Cápsula de Estado"
 * 
 * Rol: Originator
 * Función: Es el objeto principal que posee el estado mutable de una cita.
 * Crea y restaura Mementos.
 * 
 * Módulo: Gestión de Citas — permite que el recepcionista edite
 * datos de una cita y pueda deshacer los cambios si el paciente se arrepiente.
 */
public class CitaOriginator {

    private String medico;
    private TipoCita tipo;
    private LocalDateTime fechaHora;
    private String diagnostico;
    private String observaciones;
    private double costo;

    public CitaOriginator(CitaMedica cita) {
        this.medico = cita.getMedico();
        this.tipo = cita.getTipo();
        this.fechaHora = cita.getFechaHora();
        this.diagnostico = cita.getDiagnostico();
        this.observaciones = cita.getObservaciones();
        this.costo = cita.getCosto();
    }

    // ── Creación del Memento ──────────────────────────────────────────────────
    public CitaMemento guardarEstado() {
        System.out.println("  [Memento] Guardando estado actual de la cita...");
        return new CitaMemento(medico, tipo, fechaHora, diagnostico, observaciones, costo);
    }

    // ── Restauración desde Memento ────────────────────────────────────────────
    public void restaurarEstado(CitaMemento memento) {
        this.medico = memento.getMedico();
        this.tipo = memento.getTipo();
        this.fechaHora = memento.getFechaHora();
        this.diagnostico = memento.getDiagnostico();
        this.observaciones = memento.getObservaciones();
        this.costo = memento.getCosto();
        System.out.println("  [Memento] Estado restaurado al: " + memento.getMomentoGuardado());
    }

    // ── Mutadores (simulan ediciones del recepcionista) ───────────────────────
    public void setMedico(String medico) { this.medico = medico; }
    public void setTipo(TipoCita tipo) { this.tipo = tipo; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public void setCosto(double costo) { this.costo = costo; }

    public String getMedico() { return medico; }
    public TipoCita getTipo() { return tipo; }
    public double getCosto() { return costo; }

    @Override
    public String toString() {
        return String.format("Originator[Dr.%s | %s | $%.2f | Dx:%s]",
                medico, tipo, costo, diagnostico);
    }
}
