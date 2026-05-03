package model;

public class Paciente {
    private String id;
    private String nombre;
    private String documento;
    private int edad;
    private String eps;

    public Paciente(String id, String nombre, String documento, int edad, String eps) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.eps = eps;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
    public int getEdad() { return edad; }
    public String getEps() { return eps; }

    @Override
    public String toString() {
        return "Paciente{nombre='" + nombre + "', doc=" + documento + ", edad=" + edad + ", eps=" + eps + "}";
    }
}
