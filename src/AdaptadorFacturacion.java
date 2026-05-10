public class AdaptadorFacturacion implements EstrategiaCobro {

    private SistemaFacturacionViejo sistemaViejo;

    public AdaptadorFacturacion(SistemaFacturacionViejo sistemaViejo) {
        this.sistemaViejo = sistemaViejo;
    }

    @Override
    public double calcular(int horas) {
        double monto = horas * 25000;
        sistemaViejo.generarFactura(monto); // traduce calcular() a generarFactura()
        return monto;
    }
}