// Patron Adapter
// Hace que el EscanerAntiguo funcione como una Reparacion normal
// sin tener que cambiar el codigo del escaner
public class AdaptadorEscaner extends Reparacion {

    private EscanerAntiguo escaner;

    public AdaptadorEscaner(EscanerAntiguo escaner) {
        super("Diagnostico electronico (adaptado)");
        this.escaner = escaner;
    }

    @Override
    public void ejecutar() {
        escaner.runScan(); // traduce ejecutar() al metodo del escaner viejo
    }
}
