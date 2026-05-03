package visitor.citas;

import model.CitaMedica;
import visitor.CitaVisitor;
import visitor.Visitable;

/**
 * Elemento concreto: Consulta Interna
 * Módulo: Hospitalización / Medicina Interna
 */
public class ConsultaInternaElement implements Visitable {
    private final CitaMedica cita;
    private final String sala;

    public ConsultaInternaElement(CitaMedica cita, String sala) {
        this.cita = cita;
        this.sala = sala;
    }

    public CitaMedica getCita() { return cita; }
    public String getSala() { return sala; }

    @Override
    public void accept(CitaVisitor visitor) {
        visitor.visitarConsultaInterna(this);
    }
}
