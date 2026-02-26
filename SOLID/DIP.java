// Registro de pintores y herramientas

//-- SIN DIP --

// Cada pintor está atado a una herramienta concreta 
public class Brocha {
    public void pintar() {
        System.out.println("Pintando con brocha...");
    }
}

public class Pintor {
    // Si se quiere que con rodillo o spray, hay que modificar esta clase
    private Brocha herramienta = new Brocha();

    public void pintar() {
        herramienta.pintar();
    }
}

//-- CON DIP --

// El pintor no depende de una herramienta concreta
public interface HerramientaDePintura {
    void pintar();
}

public class Brocha implements HerramientaDePintura {
    public void pintar() {
        System.out.println("Pintando con brocha...");
    }
}

public class Rodillo implements HerramientaDePintura {
    public void pintar() {
        System.out.println("Pintando con rodillo...");
    }
}

public class Spray implements HerramientaDePintura {
    public void pintar() {
        System.out.println("Pintando con spray...");
    }
}

// El pintor no sabe ni le importa con qué herramienta pinta, puede ser cualquiera
public class Pintor {
    private HerramientaDePintura herramienta;

    public Pintor(HerramientaDePintura herramienta) {
        this.herramienta = herramienta;
    }

    public void pintar() {
        herramienta.pintar();
    }
}

// Uso
Pintor juan = new Pintor(new Brocha());
juan.pintar(); // Pintando con brocha...

Pintor carlos = new Pintor(new Rodillo());
carlos.pintar(); // Pintando con rodillo...

Pintor maria = new Pintor(new Spray());
maria.pintar(); // Pintando con spray...
