// clase base para los tipos de reparación que hace la mecánica
public abstract class Reparacion {

    protected String descripcion;

    public Reparacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public abstract void ejecutar();

    public String getDescripcion() {
        return descripcion;
    }
}
