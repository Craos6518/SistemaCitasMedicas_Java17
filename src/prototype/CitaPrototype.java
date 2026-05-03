package prototype;

import model.CitaMedica;
import model.Paciente;
import model.TipoCita;

import java.time.LocalDateTime;

/**
 * PATRÓN PROTOTYPE - "El Clon"
 * 
 * Función: Permite crear nuevas citas clonando una cita existente
 * (plantilla), modificando solo los campos necesarios. Evita
 * reconstruir el objeto desde cero cada vez.
 * 
 * Módulo: Programación de Citas de Control — cuando un paciente crónico
 * (ej. diabético, hipertenso) tiene citas recurrentes con el mismo médico
 * y tipo, se clona la cita anterior y solo se ajusta la fecha.
 * 
 * También útil en: Citas de seguimiento post-quirúrgico, controles
 * periódicos de especialista.
 */
public class CitaPrototype implements Cloneable {

    private CitaMedica cita;

    public CitaPrototype(CitaMedica cita) {
        this.cita = cita;
    }

    /**
     * Clona la cita y genera un nuevo ID y fecha para la copia.
     * El paciente, médico y tipo se conservan del original.
     */
    public CitaPrototype clonar(String nuevoId, LocalDateTime nuevaFecha) {
        try {
            CitaPrototype clon = (CitaPrototype) this.clone();

            // Crear nueva cita con los mismos datos base pero nuevo ID y fecha
            CitaMedica citaClonada = new CitaMedica(
                    nuevoId,
                    this.cita.getPaciente(),   // mismo paciente
                    this.cita.getTipo(),        // mismo tipo
                    this.cita.getMedico(),      // mismo médico
                    nuevaFecha                  // nueva fecha
            );
            citaClonada.setObservaciones("Cita de control - clonada desde: " + this.cita.getId());

            clon.cita = citaClonada;

            System.out.printf("  [Prototype] Cita clonada: %s → %s | Fecha: %s%n",
                    this.cita.getId(), nuevoId, nuevaFecha.toLocalDate());

            return clon;

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar la cita", e);
        }
    }

    public CitaMedica getCita() { return cita; }

    @Override
    public String toString() {
        return "Prototype[" + cita + "]";
    }
}
