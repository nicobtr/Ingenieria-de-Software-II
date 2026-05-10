public class FabricaFreno extends FabricaReparacion {

    @Override
    public Reparacion crear() {
        return new ReparacionFreno();
    }
}
