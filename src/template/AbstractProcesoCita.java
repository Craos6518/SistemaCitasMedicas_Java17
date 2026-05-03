package template;

import model.CitaMedica;
import model.Paciente;

/**
 * PATRÓN TEMPLATE METHOD - "El Esqueleto"
 * 
 * Rol: AbstractClass
 * Función: Define el flujo FIJO de atención de cualquier cita médica.
 * Los pasos invariantes están implementados aquí; los pasos que varían
 * según el tipo de cita son abstractos y los implementan las subclases.
 * 
 * Módulo: Atención al Paciente — garantiza que todos los tipos de cita
 * sigan el mismo protocolo institucional, sin que ninguna subclase
 * pueda saltarse pasos obligatorios.
 * 
 * Flujo fijo:
 *   1. Verificar cobertura EPS        (común a todos)
 *   2. Registrar llegada              (común a todos)
 *   3. Preparar documentación         (varía por tipo)
 *   4. Realizar atención específica   (varía por tipo)
 *   5. Registrar en historia clínica  (varía por tipo)
 *   6. Generar factura                (común a todos)
 *   7. Cerrar cita                    (común a todos)
 */
public abstract class AbstractProcesoCita {

    // ── MÉTODO PLANTILLA — final para que nadie lo modifique ─────────────────
    public final void procesarCita(CitaMedica cita) {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║  INICIANDO PROCESO: " + cita.getTipo());
        System.out.println("╚══════════════════════════════════════════════╝");

        verificarCoberturaEPS(cita.getPaciente());       // paso 1 - invariante
        registrarLlegadaPaciente(cita);                   // paso 2 - invariante
        prepararDocumentacion(cita);                      // paso 3 - abstracto
        realizarAtencion(cita);                           // paso 4 - abstracto
        registrarHistoriaClinica(cita);                   // paso 5 - hook (opcional)
        generarFactura(cita);                             // paso 6 - invariante
        cerrarCita(cita);                                 // paso 7 - invariante

        System.out.println("  ✓ Proceso completado para: " + cita.getPaciente().getNombre());
    }

    // ── Pasos INVARIANTES (implementados aquí) ───────────────────────────────
    private void verificarCoberturaEPS(Paciente paciente) {
        System.out.println("  [1] Verificando cobertura EPS: " + paciente.getEps() + " ✓");
    }

    private void registrarLlegadaPaciente(CitaMedica cita) {
        System.out.println("  [2] Registrando llegada de: " + cita.getPaciente().getNombre() +
                           " | " + java.time.LocalTime.now());
    }

    private void generarFactura(CitaMedica cita) {
        System.out.printf("  [6] Factura generada: $%.0f | Cita ID: %s%n",
                cita.getCosto(), cita.getId());
    }

    private void cerrarCita(CitaMedica cita) {
        System.out.println("  [7] Cita cerrada en el sistema. ID: " + cita.getId());
    }

    // ── Pasos ABSTRACTOS (cada subclase los implementa) ─────────────────────
    protected abstract void prepararDocumentacion(CitaMedica cita);
    protected abstract void realizarAtencion(CitaMedica cita);

    // ── Hook (paso opcional — por defecto no hace nada) ──────────────────────
    protected void registrarHistoriaClinica(CitaMedica cita) {
        System.out.println("  [5] Registro en HC: (omitido para este tipo de cita)");
    }
}
