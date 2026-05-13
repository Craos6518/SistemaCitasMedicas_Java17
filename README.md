# Proyecto: Sistema de Citas Médicas — Patrones de Comportamiento (Java 17)

Estructura actual del proyecto (paquetes y responsabilidades):

```
src/
├── model/
│   ├── Paciente.java
│   ├── CitaMedica.java
│   └── TipoCita.java
├── template/
│   ├── AbstractProcesoCita.java
│   ├── ProcesoConsultaInterna.java
│   ├── ProcesoConsultaExterna.java
│   ├── ProcesoEspecialista.java
│   ├── ProcesoHistoriaClinica.java
│   └── ProcesoRecetaMedica.java
├── memento/
│   ├── CitaMemento.java
│   ├── CitaOriginator.java
│   └── HistorialCaretaker.java
├── visitor/
│   ├── CitaVisitor.java
│   ├── Visitable.java
│   ├── CalculadorCostosVisitor.java
│   ├── GeneradorReporteVisitor.java
│   └── citas/
│       ├── ConsultaExternaElement.java
│       ├── ConsultaInternaElement.java
│       ├── EspecialistaElement.java
│       ├── HistoriaClinicaElement.java
│       └── RecetaMedicaElement.java
├── prototype/
│   ├── CitaPrototype.java
│   └── RegistroPrototipos.java
├── state/
│   ├── EstadoCita.java
│   ├── ContextoCita.java
│   └── Estados.java
└── main/
    └── SistemaCitasDemo.java

Notas:
- `model`: Entidades del dominio.
- `template`: Implementa Template Method (flujo de atención).
- `memento`: Guardado/restauración de estados de `CitaMedica`.
- `visitor`: Visitors y elementos concretos por tipo de cita.
- `prototype`: Plantillas clonables de citas.
- `state`: Máquina de estados para el ciclo de vida de una cita.
- `main`: Demo y puntos de entrada para pruebas y ejemplos.
