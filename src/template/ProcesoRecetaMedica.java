package template;

import model.CitaMedica;

/** ConcreteClass: Receta Médica — Módulo: Farmacia */
public class ProcesoRecetaMedica extends AbstractProcesoCita {

    @Override
    protected void prepararDocumentacion(CitaMedica cita) {
        System.out.println("  [3] Preparando: Formulario de receta oficial, verificación de alergias.");
    }

    @Override
    protected void realizarAtencion(CitaMedica cita) {
        System.out.println("  [4] RECETA MÉDICA: Prescripción de medicamentos, indicaciones de uso, " +
                           "dosis y duración. Dr. " + cita.getMedico());
        cita.setObservaciones("Receta emitida - dispensar en farmacia autorizada");
    }
    // No actualiza HC — usa el hook por defecto
}
