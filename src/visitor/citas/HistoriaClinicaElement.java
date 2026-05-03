package visitor.citas;

import model.CitaMedica;
import visitor.CitaVisitor;
import visitor.Visitable;

/** Elemento: Historia Clínica — Módulo: Archivo Clínico / EHR */
public class HistoriaClinicaElement implements Visitable {
    private final CitaMedica cita;
    private final String numeroHistoria;
    private final boolean esPrimerIngreso;

    public HistoriaClinicaElement(CitaMedica cita, String numeroHistoria, boolean esPrimerIngreso) {
        this.cita = cita;
        this.numeroHistoria = numeroHistoria;
        this.esPrimerIngreso = esPrimerIngreso;
    }

    public CitaMedica getCita() { return cita; }
    public String getNumeroHistoria() { return numeroHistoria; }
    public boolean isEsPrimerIngreso() { return esPrimerIngreso; }

    @Override
    public void accept(CitaVisitor visitor) {
        visitor.visitarHistoriaClinica(this);
    }
}
