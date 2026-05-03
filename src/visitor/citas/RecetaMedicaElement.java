package visitor.citas;

import model.CitaMedica;
import visitor.CitaVisitor;
import visitor.Visitable;

/** Elemento: Receta Médica — Módulo: Farmacia / Dispensación */
public class RecetaMedicaElement implements Visitable {
    private final CitaMedica cita;
    private final int cantidadMedicamentos;
    private final boolean contienePsicotropicos;

    public RecetaMedicaElement(CitaMedica cita, int cantidadMedicamentos, boolean contienePsicotropicos) {
        this.cita = cita;
        this.cantidadMedicamentos = cantidadMedicamentos;
        this.contienePsicotropicos = contienePsicotropicos;
    }

    public CitaMedica getCita() { return cita; }
    public int getCantidadMedicamentos() { return cantidadMedicamentos; }
    public boolean isContienePsicotropicos() { return contienePsicotropicos; }

    @Override
    public void accept(CitaVisitor visitor) {
        visitor.visitarRecetaMedica(this);
    }
}
