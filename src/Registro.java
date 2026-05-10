// Patron Singleton
// El registro de reparaciones de la mecánica es único
public class Registro {

    private static Registro instancia = null;
    private int totalReparaciones;

    private Registro() {
        totalReparaciones = 0;
    }

    public static Registro getInstancia() {
        if (instancia == null) {
            instancia = new Registro();
        }
        return instancia;
    }

    public void agregarReparacion() {
        totalReparaciones++;
    }

    public void mostrar() {
        System.out.println("Reparaciones registradas hoy: " + totalReparaciones);
    }
}
