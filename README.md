# Taller Mecánico - Patrones de Diseño en Java

## ¿De qué trata el programa?

La idea es simular el sistema básico de un taller mecánico. El taller tiene un registro donde anota las reparaciones del día, atiende distintos tipos de trabajo (frenos, motor, etc.), usa un escáner de diagnóstico viejo que compraron de segunda y tiene dos formas de cobrarle al cliente: por hora o precio fijo según el trabajo.

Con ese contexto se aplican los cuatro patrones.

---

## Cómo correr el código

```bash
cd src
javac *.java
java Main
```

---

## Resultado de ejecutar Main

```
=== Singleton: Registro ===
Son el mismo objeto: true
Reparaciones registradas hoy: 2

=== Factory Method: Reparaciones ===
Ejecutando: Cambio de frenos
Ejecutando: Revision de motor

=== Adapter: Sistema de facturacion viejo ===
[Sistema viejo] Factura generada por: $75000.0
Total: $75000.0

=== Strategy: Cobro ===
Cobro por hora (3h): $75000.0
Cobro precio fijo: $80000.0
```

---

## Patrones usados

### 1. Singleton (Creacional) — `Registro.java`

El registro de reparaciones del día tiene que ser único. No tiene sentido que existan dos registros distintos al mismo tiempo porque las reparaciones se irían a lugares diferentes y se perdería información. Con el patrón Singleton se garantiza que sin importar cuántas veces se pida la instancia del registro, siempre es el mismo objeto.

El truco está en que el constructor es `private`, entonces nadie puede hacer `new Registro()` desde afuera. La única forma de obtenerlo es con `getInstancia()`, que la primera vez lo crea y las siguientes veces devuelve el mismo.

```java
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
```

En el Main se puede ver que `r1` y `r2` son el mismo objeto, y que lo que se agrega desde uno se ve en el otro:

```java
Registro r1 = Registro.getInstancia();
Registro r2 = Registro.getInstancia();
System.out.println("Son el mismo objeto: " + (r1 == r2)); // true
r1.agregarReparacion();
r1.agregarReparacion();
r2.mostrar(); // muestra 2 aunque se llamó desde r2
```

---

### 2. Factory Method (Creacional) — `FabricaReparacion.java`

El taller hace distintos tipos de reparación. En vez de crear cada una directamente con `new ReparacionFreno()` o `new ReparacionMotor()` desde el main, se usa una fábrica que se encarga de eso. La clase abstracta `FabricaReparacion` define el método `crear()`, y cada subclase concreta (`FabricaFreno`, `FabricaMotor`) lo implementa y devuelve el tipo de reparación que le corresponde.

Esto sirve porque si mañana el taller empieza a hacer también cambios de aceite, solo se crea `ReparacionAceite` y `FabricaAceite` sin tocar nada más del sistema.

```java
public abstract class FabricaReparacion {
    public abstract Reparacion crear();
}

public class FabricaFreno extends FabricaReparacion {
    @Override
    public Reparacion crear() {
        return new ReparacionFreno();
    }
}

public class FabricaMotor extends FabricaReparacion {
    @Override
    public Reparacion crear() {
        return new ReparacionMotor();
    }
}
```

Uso en el Main:

```java
FabricaReparacion fabrica1 = new FabricaFreno();
FabricaReparacion fabrica2 = new FabricaMotor();

Reparacion rep1 = fabrica1.crear();
Reparacion rep2 = fabrica2.crear();

rep1.ejecutar(); // "Ejecutando: Cambio de frenos"
rep2.ejecutar(); // "Ejecutando: Revision de motor"
```

---

### 3. Adapter (Estructural) — `AdaptadorFacturacion.java`

El taller tiene un sistema de facturación viejo que hizo alguien que ya no trabaja ahí. Nadie lo quiere tocar porque es delicado y funciona, pero el problema es que su método se llama `generarFactura()` mientras que el sistema nuevo espera `calcular()` de la interfaz `EstrategiaCobro`. Son incompatibles.

El Adapter soluciona eso sin tocar ninguno de los dos lados: crea una clase que implementa `EstrategiaCobro` (lo que el sistema nuevo entiende), y por dentro lo que hace es llamar a `generarFactura()` del sistema viejo. El resto del código ni se entera de que hay un sistema viejo atrás.

```java
// el sistema viejo que no podemos modificar
public class SistemaFacturacionViejo {
    public void generarFactura(double monto) {
        System.out.println("[Sistema viejo] Factura generada por: $" + monto);
    }
}

// el adapter que lo hace compatible con EstrategiaCobro
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
```

Uso en el Main:

```java
SistemaFacturacionViejo sistemaViejo = new SistemaFacturacionViejo();
EstrategiaCobro adaptado = new AdaptadorFacturacion(sistemaViejo);
adaptado.calcular(3); // por dentro llama a generarFactura()
```

---

### 4. Strategy (Comportamiento) — `EstrategiaCobro.java`

El taller no siempre cobra igual. A veces cobra por hora trabajada y a veces negocia un precio fijo por el trabajo completo. La forma de cobrar puede cambiar sin que el resto del sistema tenga que cambiar también.

Con Strategy se define una interfaz `EstrategiaCobro` con el método `calcular()`, y cada clase concreta implementa su propia lógica. Si en algún momento el taller quiere agregar descuento para clientes frecuentes, solo crea una nueva estrategia.

```java
public interface EstrategiaCobro {
    double calcular(int horas);
}

public class CobrarPorHora implements EstrategiaCobro {
    @Override
    public double calcular(int horas) {
        return horas * 25000;
    }
}

public class CobrarPrecioFijo implements EstrategiaCobro {
    private double precioFijo;

    public CobrarPrecioFijo(double precioFijo) {
        this.precioFijo = precioFijo;
    }

    @Override
    public double calcular(int horas) {
        return precioFijo;
    }
}
```

Uso en el Main:

```java
EstrategiaCobro porHora = new CobrarPorHora();
EstrategiaCobro precioFijo = new CobrarPrecioFijo(80000);

System.out.println("Cobro por hora (3h): $" + porHora.calcular(3));   // $75000
System.out.println("Cobro precio fijo: $" + precioFijo.calcular(3));  // $80000
```
