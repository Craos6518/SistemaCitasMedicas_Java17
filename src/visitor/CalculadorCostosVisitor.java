package visitor;

import visitor.citas.*;

/**
 * PATRÓN VISITOR - ConcreteVisitor #1: Calculador de Costos
 * 
 * Función: Recorre los distintos tipos de cita y calcula el costo
 * correspondiente según tarifas del sistema de salud colombiano.
 * 
 * Módulo: Facturación y Tesorería
 * VENTAJA del patrón: Si las tarifas cambian, solo se modifica ESTE visitor,
 * sin tocar las clases de cita.
 */
public class CalculadorCostosVisitor implements CitaVisitor {

    private double totalCalculado = 0.0;

    @Override
    public void visitarConsultaInterna(ConsultaInternaElement cita) {
        double costo = 45000.0; // tarifa base consulta interna
        System.out.printf("  [Visitor-Costos] ConsultaInterna | Sala:%s | Costo: $%.0f%n",
                cita.getSala(), costo);
        cita.getCita().setCosto(costo);
        totalCalculado += costo;
    }

    @Override
    public void visitarConsultaExterna(ConsultaExternaElement cita) {
        double costo = 30000.0;
        if (cita.isRequiereOrden()) costo += 5000.0; // recargo por tramite de orden
        System.out.printf("  [Visitor-Costos] ConsultaExterna | OrdenRequerida:%s | Costo: $%.0f%n",
                cita.isRequiereOrden(), costo);
        cita.getCita().setCosto(costo);
        totalCalculado += costo;
    }

    @Override
    public void visitarEspecialista(EspecialistaElement cita) {
        double costo = 80000.0;
        if (cita.isRequiereAutorizacion()) costo += 15000.0; // tramite autorizacion EPS
        System.out.printf("  [Visitor-Costos] Especialista | %s | AutorizRequerida:%s | Costo: $%.0f%n",
                cita.getEspecialidad(), cita.isRequiereAutorizacion(), costo);
        cita.getCita().setCosto(costo);
        totalCalculado += costo;
    }

    @Override
    public void visitarHistoriaClinica(HistoriaClinicaElement cita) {
        double costo = cita.isEsPrimerIngreso() ? 20000.0 : 8000.0; // apertura vs actualización
        System.out.printf("  [Visitor-Costos] HistoriaClinica | HC:%s | PrimerIngreso:%s | Costo: $%.0f%n",
                cita.getNumeroHistoria(), cita.isEsPrimerIngreso(), costo);
        cita.getCita().setCosto(costo);
        totalCalculado += costo;
    }

    @Override
    public void visitarRecetaMedica(RecetaMedicaElement cita) {
        double costo = cita.getCantidadMedicamentos() * 3000.0;
        if (cita.isContienePsicotropicos()) costo += 25000.0; // control especial
        System.out.printf("  [Visitor-Costos] RecetaMedica | Medicamentos:%d | Psicotropicos:%s | Costo: $%.0f%n",
                cita.getCantidadMedicamentos(), cita.isContienePsicotropicos(), costo);
        cita.getCita().setCosto(costo);
        totalCalculado += costo;
    }

    public double getTotalCalculado() { return totalCalculado; }
    public void resetTotal() { totalCalculado = 0.0; }
}
