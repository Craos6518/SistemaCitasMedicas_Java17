package visitor;

import visitor.citas.*;

/**
 * PATRÓN VISITOR - "La Operación Externa"
 * 
 * Rol: Visitor (interfaz)
 * Función: Declara un método visit() para cada tipo concreto de cita.
 * Nuevas operaciones se añaden creando nuevos ConcreteVisitors,
 * sin modificar las clases de cita existentes.
 * 
 * Módulo: Core del sistema — define el contrato de extensión.
 */
public interface CitaVisitor {
    void visitarConsultaInterna(ConsultaInternaElement cita);
    void visitarConsultaExterna(ConsultaExternaElement cita);
    void visitarEspecialista(EspecialistaElement cita);
    void visitarHistoriaClinica(HistoriaClinicaElement cita);
    void visitarRecetaMedica(RecetaMedicaElement cita);
}
