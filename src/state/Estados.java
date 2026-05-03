package state;

/**
 * PATRÓN STATE - ConcreteStates
 * Cada clase representa un estado del ciclo de vida de la cita.
 * Las transiciones inválidas muestran un mensaje de error en lugar de lanzar excepciones.
 */

// ── Estado: PROGRAMADA ────────────────────────────────────────────────────────
class EstadoProgramada implements EstadoCita {

    @Override
    public void confirmar(ContextoCita ctx) {
        System.out.println("  [State-PROGRAMADA] Cita confirmada por el paciente.");
        ctx.cambiarEstado(new EstadoConfirmada());
    }

    @Override
    public void iniciarAtencion(ContextoCita ctx) {
        System.out.println("  [State-PROGRAMADA] ✗ No se puede iniciar: la cita no ha sido confirmada.");
    }

    @Override
    public void completar(ContextoCita ctx) {
        System.out.println("  [State-PROGRAMADA] ✗ No se puede completar desde este estado.");
    }

    @Override
    public void cancelar(ContextoCita ctx) {
        System.out.println("  [State-PROGRAMADA] Cita cancelada (aún no confirmada).");
        ctx.cambiarEstado(new EstadoCancelada());
    }

    @Override
    public void reprogramar(ContextoCita ctx) {
        System.out.println("  [State-PROGRAMADA] Cita reprogramada.");
        ctx.cambiarEstado(new EstadoReprogramada());
    }

    @Override public String getNombre() { return "PROGRAMADA"; }
}

// ── Estado: CONFIRMADA ────────────────────────────────────────────────────────
class EstadoConfirmada implements EstadoCita {

    @Override
    public void confirmar(ContextoCita ctx) {
        System.out.println("  [State-CONFIRMADA] La cita ya está confirmada.");
    }

    @Override
    public void iniciarAtencion(ContextoCita ctx) {
        System.out.println("  [State-CONFIRMADA] Paciente en consulta — iniciando atención.");
        ctx.cambiarEstado(new EstadoEnAtencion());
    }

    @Override
    public void completar(ContextoCita ctx) {
        System.out.println("  [State-CONFIRMADA] ✗ Debe iniciar la atención primero.");
    }

    @Override
    public void cancelar(ContextoCita ctx) {
        System.out.println("  [State-CONFIRMADA] Cita cancelada (paciente no asistió o decisión médica).");
        ctx.cambiarEstado(new EstadoCancelada());
    }

    @Override
    public void reprogramar(ContextoCita ctx) {
        System.out.println("  [State-CONFIRMADA] Cita reprogramada.");
        ctx.cambiarEstado(new EstadoReprogramada());
    }

    @Override public String getNombre() { return "CONFIRMADA"; }
}

// ── Estado: EN_ATENCION ───────────────────────────────────────────────────────
class EstadoEnAtencion implements EstadoCita {

    @Override
    public void confirmar(ContextoCita ctx) {
        System.out.println("  [State-EN_ATENCION] ✗ La cita ya está en curso.");
    }

    @Override
    public void iniciarAtencion(ContextoCita ctx) {
        System.out.println("  [State-EN_ATENCION] ✗ La atención ya está en curso.");
    }

    @Override
    public void completar(ContextoCita ctx) {
        System.out.println("  [State-EN_ATENCION] ✓ Atención finalizada. Cita completada.");
        ctx.cambiarEstado(new EstadoCompletada());
    }

    @Override
    public void cancelar(ContextoCita ctx) {
        System.out.println("  [State-EN_ATENCION] ✗ No se puede cancelar una cita en curso.");
    }

    @Override
    public void reprogramar(ContextoCita ctx) {
        System.out.println("  [State-EN_ATENCION] ✗ No se puede reprogramar durante la atención.");
    }

    @Override public String getNombre() { return "EN_ATENCION"; }
}

// ── Estado: COMPLETADA ────────────────────────────────────────────────────────
class EstadoCompletada implements EstadoCita {

    private void accionNoPermitida(String accion) {
        System.out.println("  [State-COMPLETADA] ✗ No se puede '" + accion + "': cita ya finalizada.");
    }

    @Override public void confirmar(ContextoCita ctx) { accionNoPermitida("confirmar"); }
    @Override public void iniciarAtencion(ContextoCita ctx) { accionNoPermitida("iniciarAtencion"); }
    @Override public void completar(ContextoCita ctx) { accionNoPermitida("completar"); }
    @Override public void cancelar(ContextoCita ctx) { accionNoPermitida("cancelar"); }
    @Override public void reprogramar(ContextoCita ctx) { accionNoPermitida("reprogramar"); }

    @Override public String getNombre() { return "COMPLETADA"; }
}

// ── Estado: CANCELADA ─────────────────────────────────────────────────────────
class EstadoCancelada implements EstadoCita {

    @Override
    public void confirmar(ContextoCita ctx) {
        System.out.println("  [State-CANCELADA] ✗ No se puede confirmar una cita cancelada.");
    }
    @Override public void iniciarAtencion(ContextoCita ctx) {
        System.out.println("  [State-CANCELADA] ✗ Cita cancelada. Debe crear una nueva.");
    }
    @Override public void completar(ContextoCita ctx) {
        System.out.println("  [State-CANCELADA] ✗ No se puede completar una cita cancelada.");
    }
    @Override public void cancelar(ContextoCita ctx) {
        System.out.println("  [State-CANCELADA] Ya está cancelada.");
    }
    @Override
    public void reprogramar(ContextoCita ctx) {
        System.out.println("  [State-CANCELADA] Reprogramando cita cancelada...");
        ctx.cambiarEstado(new EstadoReprogramada());
    }

    @Override public String getNombre() { return "CANCELADA"; }
}

// ── Estado: REPROGRAMADA ──────────────────────────────────────────────────────
class EstadoReprogramada implements EstadoCita {

    @Override
    public void confirmar(ContextoCita ctx) {
        System.out.println("  [State-REPROGRAMADA] Nueva fecha confirmada.");
        ctx.cambiarEstado(new EstadoConfirmada());
    }

    @Override public void iniciarAtencion(ContextoCita ctx) {
        System.out.println("  [State-REPROGRAMADA] ✗ Confirme la nueva fecha primero.");
    }
    @Override public void completar(ContextoCita ctx) {
        System.out.println("  [State-REPROGRAMADA] ✗ Confirme la nueva fecha primero.");
    }
    @Override
    public void cancelar(ContextoCita ctx) {
        System.out.println("  [State-REPROGRAMADA] Cita cancelada definitivamente.");
        ctx.cambiarEstado(new EstadoCancelada());
    }
    @Override
    public void reprogramar(ContextoCita ctx) {
        System.out.println("  [State-REPROGRAMADA] Reprogramando nuevamente...");
    }

    @Override public String getNombre() { return "REPROGRAMADA"; }
}
