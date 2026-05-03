package template;

import model.CitaMedica;

/** ConcreteClass: Especialista */
public class ProcesoEspecialista extends AbstractProcesoCita {
    private final String especialidad;

    public ProcesoEspecialista(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    protected void prepararDocumentacion(CitaMedica cita) {
        System.out.println("  [3] Preparando: Remisión médica, resultados previos, " +
                           "autorización EPS para " + especialidad + ".");
    }

    @Override
    protected void realizarAtencion(CitaMedica cita) {
        System.out.println("  [4] ESPECIALISTA " + especialidad.toUpperCase() +
                           ": Valoración especializada. Dr. " + cita.getMedico());
        cita.setDiagnostico("Valoración por " + especialidad + " completada");
    }

    @Override
    protected void registrarHistoriaClinica(CitaMedica cita) {
        System.out.println("  [5] HC actualizada: Concepto de especialista (" + especialidad + ") registrado.");
    }
}
