package visitor;

import visitor.citas.*;

/**
 * PATRÓN VISITOR - ConcreteVisitor #2: Generador de Reportes
 * 
 * Función: Genera un resumen textual de cada tipo de cita para
 * producir informes administrativos o de auditoría.
 * 
 * Módulo: Gerencia / Auditoría / Reportes de Gestión
 * VENTAJA: Se añade esta nueva operación SIN modificar ninguna clase de cita.
 */
public class GeneradorReporteVisitor implements CitaVisitor {

    private final StringBuilder reporte = new StringBuilder();
    private int contadorCitas = 0;

    public GeneradorReporteVisitor() {
        reporte.append("═══════════════════════════════════════════════\n");
        reporte.append("       REPORTE DE ATENCIÓN MÉDICA\n");
        reporte.append("═══════════════════════════════════════════════\n");
    }

    @Override
    public void visitarConsultaInterna(ConsultaInternaElement cita) {
        contadorCitas++;
        reporte.append(String.format("\n[%d] CONSULTA INTERNA%n", contadorCitas));
        reporte.append(String.format("    Paciente : %s%n", cita.getCita().getPaciente().getNombre()));
        reporte.append(String.format("    Médico   : Dr. %s%n", cita.getCita().getMedico()));
        reporte.append(String.format("    Sala     : %s%n", cita.getSala()));
        reporte.append(String.format("    Costo    : $%.0f%n", cita.getCita().getCosto()));
    }

    @Override
    public void visitarConsultaExterna(ConsultaExternaElement cita) {
        contadorCitas++;
        reporte.append(String.format("\n[%d] CONSULTA EXTERNA%n", contadorCitas));
        reporte.append(String.format("    Paciente : %s%n", cita.getCita().getPaciente().getNombre()));
        reporte.append(String.format("    Médico   : Dr. %s%n", cita.getCita().getMedico()));
        reporte.append(String.format("    C/Orden  : %s%n", cita.isRequiereOrden() ? "Sí" : "No"));
        reporte.append(String.format("    Costo    : $%.0f%n", cita.getCita().getCosto()));
    }

    @Override
    public void visitarEspecialista(EspecialistaElement cita) {
        contadorCitas++;
        reporte.append(String.format("\n[%d] ESPECIALISTA - %s%n", contadorCitas, cita.getEspecialidad()));
        reporte.append(String.format("    Paciente : %s%n", cita.getCita().getPaciente().getNombre()));
        reporte.append(String.format("    Médico   : Dr. %s%n", cita.getCita().getMedico()));
        reporte.append(String.format("    Autoriza.: %s%n", cita.isRequiereAutorizacion() ? "Requerida" : "No requerida"));
        reporte.append(String.format("    Costo    : $%.0f%n", cita.getCita().getCosto()));
    }

    @Override
    public void visitarHistoriaClinica(HistoriaClinicaElement cita) {
        contadorCitas++;
        reporte.append(String.format("\n[%d] HISTORIA CLÍNICA%n", contadorCitas));
        reporte.append(String.format("    Paciente : %s%n", cita.getCita().getPaciente().getNombre()));
        reporte.append(String.format("    N° HC    : %s%n", cita.getNumeroHistoria()));
        reporte.append(String.format("    Tipo     : %s%n", cita.isEsPrimerIngreso() ? "APERTURA" : "ACTUALIZACIÓN"));
        reporte.append(String.format("    Costo    : $%.0f%n", cita.getCita().getCosto()));
    }

    @Override
    public void visitarRecetaMedica(RecetaMedicaElement cita) {
        contadorCitas++;
        reporte.append(String.format("\n[%d] RECETA MÉDICA%n", contadorCitas));
        reporte.append(String.format("    Paciente : %s%n", cita.getCita().getPaciente().getNombre()));
        reporte.append(String.format("    Médico   : Dr. %s%n", cita.getCita().getMedico()));
        reporte.append(String.format("    Medicam. : %d%n", cita.getCantidadMedicamentos()));
        reporte.append(String.format("    Psicotr. : %s%n", cita.isContienePsicotropicos() ? "SÍ ⚠" : "No"));
        reporte.append(String.format("    Costo    : $%.0f%n", cita.getCita().getCosto()));
    }

    public String getReporte() {
        reporte.append("\n───────────────────────────────────────────────\n");
        reporte.append(String.format("Total citas procesadas: %d\n", contadorCitas));
        reporte.append("═══════════════════════════════════════════════\n");
        return reporte.toString();
    }
}
