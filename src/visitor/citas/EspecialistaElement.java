package visitor.citas;

import model.CitaMedica;
import visitor.CitaVisitor;
import visitor.Visitable;

/** Elemento: Cita con Especialista — Módulo: Referencia y Contrarreferencia */
public class EspecialistaElement implements Visitable {
    private final CitaMedica cita;
    private final String especialidad;
    private final boolean requiereAutorizacion;

    public EspecialistaElement(CitaMedica cita, String especialidad, boolean requiereAutorizacion) {
        this.cita = cita;
        this.especialidad = especialidad;
        this.requiereAutorizacion = requiereAutorizacion;
    }

    public CitaMedica getCita() { return cita; }
    public String getEspecialidad() { return especialidad; }
    public boolean isRequiereAutorizacion() { return requiereAutorizacion; }

    @Override
    public void accept(CitaVisitor visitor) {
        visitor.visitarEspecialista(this);
    }
}
