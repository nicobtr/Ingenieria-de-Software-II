public class Main {

    public static void main(String[] args) {

        // SINGLETON 
        System.out.println("--- Singleton: Registro ---");
        Registro r1 = Registro.getInstancia();
        Registro r2 = Registro.getInstancia();
        System.out.println("Son el mismo objeto: " + (r1 == r2));
        r1.agregarReparacion();
        r1.agregarReparacion();
        r2.mostrar(); // muestra 2 aunque lo llamamos desde r2

        // FACTORY METHOD 
        System.out.println("\n--- Factory Method: Reparaciones ---");
        FabricaReparacion fabrica1 = new FabricaFreno();
        FabricaReparacion fabrica2 = new FabricaMotor();

        Reparacion rep1 = fabrica1.crear();
        Reparacion rep2 = fabrica2.crear();

        rep1.ejecutar();
        rep2.ejecutar();

        // --- ADAPTER ---
        System.out.println("\n--- Adapter: Escaner antiguo ---");
        EscanerAntiguo escanerViejo = new EscanerAntiguo();
        Reparacion adaptado = new AdaptadorEscaner(escanerViejo);
        adaptado.ejecutar(); // funciona igual que cualquier otra Reparacion

        // --- STRATEGY ---
        System.out.println("\n--- Strategy: Cobro ---");
        EstrategiaCobro porHora = new CobrarPorHora();
        EstrategiaCobro precioFijo = new CobrarPrecioFijo(80000);

        int horasTrabajadas = 3;
        System.out.println("Cobro por hora (" + horasTrabajadas + "h): $" + porHora.calcular(horasTrabajadas));
        System.out.println("Cobro precio fijo: $" + precioFijo.calcular(horasTrabajadas));
    }
}
