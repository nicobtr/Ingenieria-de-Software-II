// Sistema de empleados de una empresa

//-- SIN ISP --

// Una interfaz muy grande obliga a implementar cosas que a algunos empleados no les corresponden 
public interface Empleado {
    void trabajar();
    void cobrarSueldo();
    void gestionarEquipo();  // Un empleado en prueba no gestiona equipos
    void aprobarVacaciones(); // Un empleado prueba o no aprueba vacaciones
}

// EmpleadoEnPrueba se ve obligado a implementar métodos que no tiene sentido que tenga
public class EmpleadoEnPrueba implements Empleado {
    public void trabajar() { System.out.println("Trabajando..."); }
    public void cobrarSueldo() { System.out.println("Cobrando sueldo..."); }
    public void gestionarEquipo() { /* no puede, pero toca implementarlo igual */ }
    public void aprobarVacaciones() { /* no puede, pero toca implementarlo igual */ }
}

//-- CON ISP -- 

//Interfaces pequeñas y especificas 
public interface Trabajador {
    void trabajar();
    void cobrarSueldo();
}

public interface Gestor {
    void gestionarEquipo();
    void aprobarVacaciones();
}

// Cada clase implementa solo lo que le corresponde
public class EmpleadoEnPrueba implements Trabajador {
    public void trabajar() { System.out.println("Trabajando..."); }
    public void cobrarSueldo() { System.out.println("Cobrando sueldo..."); }
}

public class Gerente implements Trabajador, Gestor {
    public void trabajar() { System.out.println("Trabajando..."); }
    public void cobrarSueldo() { System.out.println("Cobrando sueldo..."); }
    public void gestionarEquipo() { System.out.println("Gestionando equipo..."); }
    public void aprobarVacaciones() { System.out.println("Aprobando vacaciones..."); }
}
