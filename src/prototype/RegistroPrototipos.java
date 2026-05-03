package prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * PATRÓN PROTOTYPE - Registro de Plantillas
 * 
 * Función: Almacena prototipos (plantillas) de citas frecuentes,
 * listos para ser clonados sin conocer su implementación concreta.
 * 
 * Módulo: Agenda Médica — catálogo de tipos de cita predefinidos
 * que los recepcionistas pueden duplicar rápidamente.
 */
public class RegistroPrototipos {

    private final Map<String, CitaPrototype> prototipos = new HashMap<>();

    public void registrar(String clave, CitaPrototype prototipo) {
        prototipos.put(clave, prototipo);
        System.out.println("  [Registro] Prototipo registrado: " + clave);
    }

    public CitaPrototype obtener(String clave) {
        CitaPrototype proto = prototipos.get(clave);
        if (proto == null) {
            throw new IllegalArgumentException("Prototipo no encontrado: " + clave);
        }
        return proto;
    }

    public void listar() {
        System.out.println("  [Registro] Prototipos disponibles:");
        prototipos.forEach((k, v) -> System.out.println("    - " + k + " → " + v.getCita().getTipo()));
    }
}
