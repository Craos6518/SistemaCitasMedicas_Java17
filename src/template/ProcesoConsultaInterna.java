package template;

import model.CitaMedica;

/**
 * TEMPLATE METHOD - ConcreteClass: Proceso de Consulta Interna
 * Módulo: Hospitalización / Urgencias Internas
 */
public class ProcesoConsultaInterna extends AbstractProcesoCita {

    @Override
    protected void prepararDocumentacion(CitaMedica cita) {
        System.out.println("  [3] Preparando: Orden de hospitalización, historia clínica completa, " +
                           "consentimiento informado.");
    }

    @Override
    protected void realizarAtencion(CitaMedica cita) {
        System.out.println("  [4] CONSULTA INTERNA: Examen físico completo, evolución, " +
                           "revisión de exámenes previos. Dr. " + cita.getMedico());
        cita.setDiagnostico("Evaluación interna completada");
    }

    @Override
    protected void registrarHistoriaClinica(CitaMedica cita) {
        System.out.println("  [5] HC actualizada: Evolución de consulta interna registrada.");
    }
}
