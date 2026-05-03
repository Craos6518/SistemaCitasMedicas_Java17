package memento;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * PATRÓN MEMENTO - "La Cápsula de Estado"
 * 
 * Rol: Caretaker
 * Función: Gestiona la pila de Mementos (historial de versiones).
 * NO conoce el contenido interno del Memento.
 * 
 * Módulo: Recepción / Agenda — habilita deshacer/rehacer en la programación de citas.
 */
public class HistorialCaretaker {

    private final Deque<CitaMemento> historial = new ArrayDeque<>();
    private final int maxHistorial;

    public HistorialCaretaker(int maxHistorial) {
        this.maxHistorial = maxHistorial;
    }

    public void guardar(CitaOriginator originator) {
        if (historial.size() >= maxHistorial) {
            // Eliminar el más antiguo si se supera el límite
            ((ArrayDeque<CitaMemento>) historial).removeLast();
        }
        historial.push(originator.guardarEstado());
        System.out.println("  [Caretaker] Estados guardados: " + historial.size());
    }

    public void deshacer(CitaOriginator originator) {
        if (historial.isEmpty()) {
            System.out.println("  [Caretaker] No hay estados previos para restaurar.");
            return;
        }
        CitaMemento memento = historial.pop();
        originator.restaurarEstado(memento);
    }

    public boolean tieneHistorial() {
        return !historial.isEmpty();
    }

    public int cantidadEstados() {
        return historial.size();
    }

    public void mostrarHistorial() {
        System.out.println("  [Caretaker] Historial (" + historial.size() + " estados):");
        historial.forEach(m -> System.out.println("    -> " + m));
    }
}
