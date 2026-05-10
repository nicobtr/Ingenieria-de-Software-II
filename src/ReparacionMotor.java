public class ReparacionMotor extends Reparacion {

    public ReparacionMotor() {
        super("Revision de motor");
    }

    @Override
    public void ejecutar() {
        System.out.println("Ejecutando: " + descripcion);
    }
}
