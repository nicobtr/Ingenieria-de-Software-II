public class CobrarPrecioFijo implements EstrategiaCobro {

    private double precioFijo;

    public CobrarPrecioFijo(double precioFijo) {
        this.precioFijo = precioFijo;
    }

    @Override
    public double calcular(int horas) {
        return precioFijo; // no importa cuantas horas siempre cobra lo mismo
    }
}
