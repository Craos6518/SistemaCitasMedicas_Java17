package visitor.citas;

import model.CitaMedica;
import visitor.CitaVisitor;
import visitor.Visitable;

/**
 * Elemento concreto: Consulta Externa
 * Módulo: Consultorios ambulatorios
 */
public class ConsultaExternaElement implements Visitable {
    private final CitaMedica cita;
    private final boolean requiereOrden;

    public ConsultaExternaElement(CitaMedica cita, boolean requiereOrden) {
        this.cita = cita;
        this.requiereOrden = requiereOrden;
    }

    public CitaMedica getCita() { return cita; }
    public boolean isRequiereOrden() { return requiereOrden; }

    @Override
    public void accept(CitaVisitor visitor) {
        visitor.visitarConsultaExterna(this);
    }
}
