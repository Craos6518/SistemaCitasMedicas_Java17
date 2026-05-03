package template;

import model.CitaMedica;

/** ConcreteClass: Historia Clínica — Módulo: Archivo Clínico */
public class ProcesoHistoriaClinica extends AbstractProcesoCita {

    @Override
    protected void prepararDocumentacion(CitaMedica cita) {
        System.out.println("  [3] Preparando: Solicitud de HC, documento de identidad, formulario de apertura.");
    }

    @Override
    protected void realizarAtencion(CitaMedica cita) {
        System.out.println("  [4] HISTORIA CLÍNICA: Recolección de antecedentes personales, " +
                           "familiares, alergias, medicamentos actuales.");
        cita.setDiagnostico("Historia clínica abierta/actualizada");
    }

    // No sobreescribe registrarHistoriaClinica() — usa el hook por defecto (ya es la HC misma)
}
