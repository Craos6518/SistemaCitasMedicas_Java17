package main;

import memento.*;
import model.*;
import prototype.*;
import state.*;
import template.*;
import visitor.*;
import visitor.citas.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DEMO COMPLETO — Sistema de Atención de Citas Médicas
 * Universidad Tecnológica de Pereira — Patrones de Diseño de Software
 * 
 * Patrones demostrados:
 *   1. Template Method — flujo de atención médica
 *   2. Memento         — deshacer cambios en una cita
 *   3. Visitor         — calcular costos y generar reportes
 *   4. Prototype       — clonar citas recurrentes
 *   5. State           — ciclo de vida de una cita
 */
public class SistemaCitasDemo {

    public static void main(String[] args) {

        // ═══════════════════════════════════════════════════════════════════
        // DATOS BASE
        // ═══════════════════════════════════════════════════════════════════
        Paciente p1 = new Paciente("P001", "Ana García", "1090123456", 45, "Sura EPS");
        Paciente p2 = new Paciente("P002", "Carlos Ríos", "10234567",  62, "Nueva EPS");
        Paciente p3 = new Paciente("P003", "Luz Martínez","52345678",  30, "Sanitas EPS");

        CitaMedica cita1 = new CitaMedica("C001", p1, TipoCita.CONSULTA_INTERNA,
                "Rodríguez", LocalDateTime.now());
        CitaMedica cita2 = new CitaMedica("C002", p2, TipoCita.ESPECIALISTA,
                "Vargas", LocalDateTime.now().plusDays(1));
        CitaMedica cita3 = new CitaMedica("C003", p3, TipoCita.RECETA_MEDICA,
                "Gómez", LocalDateTime.now().plusHours(2));
        CitaMedica cita4 = new CitaMedica("C004", p1, TipoCita.HISTORIA_CLINICA,
                "Rodríguez", LocalDateTime.now().plusDays(2));
        CitaMedica cita5 = new CitaMedica("C005", p2, TipoCita.CONSULTA_EXTERNA,
                "Vargas", LocalDateTime.now().plusDays(3));

        separador("1. PATRÓN TEMPLATE METHOD — Flujo de Atención");
        demoTemplateMethod(cita1, cita2, cita3);

        separador("2. PATRÓN MEMENTO — Deshacer Cambios en Cita");
        demoMemento(cita1);

        separador("3. PATRÓN VISITOR — Calcular Costos y Generar Reporte");
        demoVisitor(cita1, cita2, cita3, cita4, cita5);

        separador("4. PATRÓN PROTOTYPE — Clonar Citas Recurrentes");
        demoPrototype(cita2);

        separador("5. PATRÓN STATE — Ciclo de Vida de una Cita");
        demoState(cita3);
    }

    // ─────────────────────────────────────────────────────────────────────────
    static void demoTemplateMethod(CitaMedica c1, CitaMedica c2, CitaMedica c3) {
        System.out.println("Template Method garantiza el mismo protocolo para todos los tipos:");

        AbstractProcesoCita procesoInterno    = new ProcesoConsultaInterna();
        AbstractProcesoCita procesoEspecialista = new ProcesoEspecialista("Cardiología");
        AbstractProcesoCita procesoReceta     = new ProcesoRecetaMedica();

        procesoInterno.procesarCita(c1);
        procesoEspecialista.procesarCita(c2);
        procesoReceta.procesarCita(c3);
    }

    // ─────────────────────────────────────────────────────────────────────────
    static void demoMemento(CitaMedica cita) {
        CitaOriginator originator = new CitaOriginator(cita);
        HistorialCaretaker caretaker = new HistorialCaretaker(5);

        System.out.println("Estado inicial: " + originator);

        // Guardar estado antes de editar
        caretaker.guardar(originator);

        // Recepcionista cambia médico y costo
        originator.setMedico("Restrepo");
        originator.setCosto(75000);
        System.out.println("Después de edición: " + originator);

        // Guardar de nuevo
        caretaker.guardar(originator);

        // Se aplica descuento — guardamos antes
        caretaker.guardar(originator);
        originator.setCosto(50000);
        System.out.println("Con descuento: " + originator);

        caretaker.mostrarHistorial();

        // Deshacer hasta llegar al estado original
        System.out.println("\nDeshaciendo cambios:");
        caretaker.deshacer(originator);
        System.out.println("Restaurado: " + originator);
        caretaker.deshacer(originator);
        System.out.println("Restaurado: " + originator);
    }

    // ─────────────────────────────────────────────────────────────────────────
    static void demoVisitor(CitaMedica c1, CitaMedica c2, CitaMedica c3,
                            CitaMedica c4, CitaMedica c5) {

        // Crear los elementos visitables
        ConsultaInternaElement  ei = new ConsultaInternaElement(c1, "Sala 3-B");
        EspecialistaElement     ee = new EspecialistaElement(c2, "Cardiología", true);
        RecetaMedicaElement     er = new RecetaMedicaElement(c3, 3, false);
        HistoriaClinicaElement  eh = new HistoriaClinicaElement(c4, "HC-2024-001", true);
        ConsultaExternaElement  ex = new ConsultaExternaElement(c5, true);

        List<Visitable> citas = List.of(ei, ee, er, eh, ex);

        // Visitor 1: Calcular costos
        System.out.println("─ Calculando costos con CalculadorCostosVisitor:");
        CalculadorCostosVisitor calculador = new CalculadorCostosVisitor();
        citas.forEach(c -> c.accept(calculador));
        System.out.printf("  TOTAL facturado: $%.0f%n", calculador.getTotalCalculado());

        // Visitor 2: Generar reporte (opera sobre los mismos elementos, cero cambios en las clases)
        System.out.println("\n─ Generando reporte con GeneradorReporteVisitor:");
        GeneradorReporteVisitor reporteVisitor = new GeneradorReporteVisitor();
        citas.forEach(c -> c.accept(reporteVisitor));
        System.out.println(reporteVisitor.getReporte());
    }

    // ─────────────────────────────────────────────────────────────────────────
    static void demoPrototype(CitaMedica plantilla) {
        RegistroPrototipos registro = new RegistroPrototipos();

        // Registrar plantilla de cita con especialista
        CitaPrototype protoEspecialista = new CitaPrototype(plantilla);
        registro.registrar("control-cardio", protoEspecialista);
        registro.listar();

        // Clonar para el mismo paciente en fechas de control (cada 3 meses)
        CitaPrototype control1 = registro.obtener("control-cardio")
                .clonar("C010", LocalDateTime.now().plusMonths(3));
        CitaPrototype control2 = registro.obtener("control-cardio")
                .clonar("C011", LocalDateTime.now().plusMonths(6));
        CitaPrototype control3 = registro.obtener("control-cardio")
                .clonar("C012", LocalDateTime.now().plusMonths(9));

        System.out.println("Citas de control programadas:");
        System.out.println("  " + control1.getCita());
        System.out.println("  " + control2.getCita());
        System.out.println("  " + control3.getCita());
    }

    // ─────────────────────────────────────────────────────────────────────────
    static void demoState(CitaMedica cita) {
        ContextoCita contexto = new ContextoCita(cita);

        System.out.println("Ciclo normal de atención:");
        contexto.confirmar();
        contexto.iniciarAtencion();
        contexto.completar();

        System.out.println("\nIntento de acción inválida en estado COMPLETADA:");
        contexto.cancelar();

        System.out.println("\nCiclo con cancelación y reprogramación:");
        CitaMedica otraCita = new CitaMedica("C099", cita.getPaciente(),
                TipoCita.CONSULTA_EXTERNA, "Vargas", LocalDateTime.now().plusWeeks(1));
        ContextoCita ctx2 = new ContextoCita(otraCita);
        ctx2.confirmar();
        ctx2.cancelar();
        ctx2.reprogramar();
        ctx2.confirmar();
        ctx2.iniciarAtencion();
        ctx2.completar();
        System.out.println("Estado final: " + ctx2.getEstadoActual());
    }

    // ─────────────────────────────────────────────────────────────────────────
    static void separador(String titulo) {
        System.out.println("\n");
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.printf ("║  %-60s║%n", titulo);
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
