// Patron Factory Method
// cada subclase decide que tipo de reparacion crear
public abstract class FabricaReparacion {
    public abstract Reparacion crear();
}
