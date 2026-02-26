// Sistema de descuentos de una tienda

//-- SIN OCP --

// Cada nuevo tipo de descuento obliga a modificar el método y por lo tanto la clase
public class CalculadorDeDescuentos {
    public double calcular(String tipoCliente, double precio) {
        if (tipoCliente.equals("VIP")) {
            return precio * 0.8;
        } else if (tipoCliente.equals("Estudiante")) {
            return precio * 0.9;
        }
        return precio;
    }
}

//-- CON OCP --
public interface EstrategiaDeDescuento {
    double aplicar(double precio);
}

public class DescuentoVIP implements EstrategiaDeDescuento {
    public double aplicar(double precio) { return precio * 0.8; }
}

public class DescuentoEstudiante implements EstrategiaDeDescuento {
    public double aplicar(double precio) { return precio * 0.9; }
}

// Si se quiere poner un nuevo tipo de descuento se implementa como una nueva clase que valida sin necesidad de tocar nada más
public class DescuentoEmpleado implements EstrategiaDeDescuento {
    public double aplicar(double precio) { return precio * 0.85; }
}

public class CalculadorDeDescuentos {
    private EstrategiaDeDescuento estrategia;

    public CalculadorDeDescuentos(EstrategiaDeDescuento estrategia) {
        this.estrategia = estrategia;
    }

    public double calcular(double precio) {
        return estrategia.aplicar(precio);
    }
}

// Uso
CalculadorDeDescuentos calc = new CalculadorDeDescuentos(new DescuentoVIP());
calc.calcular(100); 
