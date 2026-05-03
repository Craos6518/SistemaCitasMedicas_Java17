package model;

import java.time.LocalDateTime;

public class CitaMedica {
    private String id;
    private Paciente paciente;
    private TipoCita tipo;
    private String medico;
    private LocalDateTime fechaHora;
    private String diagnostico;
    private String observaciones;
    private double costo;

    public CitaMedica(String id, Paciente paciente, TipoCita tipo,
                      String medico, LocalDateTime fechaHora) {
        this.id = id;
        this.paciente = paciente;
        this.tipo = tipo;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.diagnostico = "";
        this.observaciones = "";
        this.costo = 0.0;
    }

    // Getters y setters
    public String getId() { return id; }
    public Paciente getPaciente() { return paciente; }
    public TipoCita getTipo() { return tipo; }
    public void setTipo(TipoCita tipo) { this.tipo = tipo; }
    public String getMedico() { return medico; }
    public void setMedico(String medico) { this.medico = medico; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    @Override
    public String toString() {
        return String.format("Cita[%s | %s | %s | Dr.%s | %s | $%.2f]",
                id, tipo, paciente.getNombre(), medico, fechaHora, costo);
    }
}
