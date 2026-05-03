package template;

import model.CitaMedica;

/** ConcreteClass: Proceso de Consulta Externa — Módulo: Consultorios Ambulatorios */
public class ProcesoConsultaExterna extends AbstractProcesoCita {

    @Override
    protected void prepararDocumentacion(CitaMedica cita) {
        System.out.println("  [3] Preparando: Carnet EPS, orden médica (si aplica), triage inicial.");
    }

    @Override
    protected void realizarAtencion(CitaMedica cita) {
        System.out.println("  [4] CONSULTA EXTERNA: Anamnesis, examen físico dirigido. " +
                           "Dr. " + cita.getMedico());
        cita.setDiagnostico("Consulta externa atendida - pendiente resultados");
    }

    @Override
    protected void registrarHistoriaClinica(CitaMedica cita) {
        System.out.println("  [5] HC actualizada: Nota de consulta externa registrada.");
    }
}
