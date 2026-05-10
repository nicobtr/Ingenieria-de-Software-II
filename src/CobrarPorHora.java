public class CobrarPorHora implements EstrategiaCobro {

    @Override
    public double calcular(int horas) {
        return horas * 25000; // 25 mil por hora
    }
}
