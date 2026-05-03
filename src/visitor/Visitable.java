package visitor;

/**
 * PATRÓN VISITOR - "La Operación Externa"
 * 
 * Rol: Element (Visitable)
 * Función: Interfaz que deben implementar todos los tipos de cita
 * para aceptar visitantes. El método accept() es el "gancho" que
 * permite al visitante operar sobre el elemento sin modificar su clase.
 * 
 * Módulo: Facturación, Reportes, Auditoría — cualquier módulo que
 * necesite recorrer distintos tipos de cita y aplicar lógica diferente
 * para cada uno, sin tocar las clases de cita.
 */
public interface Visitable {
    void accept(CitaVisitor visitor);
}
