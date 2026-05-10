public class ReparacionFreno extends Reparacion {

    public ReparacionFreno() {
        super("Cambio de frenos");
    }

    @Override
    public void ejecutar() {
        System.out.println("Ejecutando: " + descripcion);
    }
}
