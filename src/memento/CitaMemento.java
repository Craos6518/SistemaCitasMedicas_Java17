package memento;

import model.TipoCita;
import java.time.LocalDateTime;

/**
 * PATRÓN MEMENTO - "La Cápsula de Estado"
 * 
 * Rol: Memento
 * Función: Almacena una fotografía inmutable del estado de una cita médica
 * en un momento dado. Solo el Originator puede leer su contenido interno.
 * 
 * Módulo: Gestión de Citas (permite deshacer cambios en una cita antes de confirmarla)
 */
public class CitaMemento {

    // Estado capturado (todos privados — solo el Originator accede via métodos del paquete)
    private final String medico;
    private final TipoCita tipo;
    private final LocalDateTime fechaHora;
    private final String diagnostico;
    private final String observaciones;
    private final double costo;
    private final LocalDateTime momentoGuardado;

    // Constructor accesible solo desde el paquete memento
    CitaMemento(String medico, TipoCita tipo, LocalDateTime fechaHora,
                String diagnostico, String observaciones, double costo) {
        this.medico = medico;
        this.tipo = tipo;
        this.fechaHora = fechaHora;
        this.diagnostico = diagnostico;
        this.observaciones = observaciones;
        this.costo = costo;
        this.momentoGuardado = LocalDateTime.now();
    }

    // Accesores del paquete (solo el Originator los usa)
    String getMedico() { return medico; }
    TipoCita getTipo() { return tipo; }
    LocalDateTime getFechaHora() { return fechaHora; }
    String getDiagnostico() { return diagnostico; }
    String getObservaciones() { return observaciones; }
    double getCosto() { return costo; }

    // Este sí es público: el Caretaker necesita saber cuándo fue guardado
    public LocalDateTime getMomentoGuardado() { return momentoGuardado; }

    @Override
    public String toString() {
        return String.format("Memento[guardado=%s | Dr.%s | %s | $%.2f]",
                momentoGuardado, medico, tipo, costo);
    }
}
