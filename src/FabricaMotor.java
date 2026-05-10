public class FabricaMotor extends FabricaReparacion {

    @Override
    public Reparacion crear() {
        return new ReparacionMotor();
    }
}
