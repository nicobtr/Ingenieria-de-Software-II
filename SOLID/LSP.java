// Sistema de usuarios de una plataforma

//-- SIN LSP --
// UsuarioInvitado hereda de Usuario pero rompe el contrato porque usa métodos que no le corresponden
public class Usuario {
    protected String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public void publicarContenido(String contenido) {
        System.out.println(nombre + " publicó: " + contenido);
    }

    public void enviarMensaje(String mensaje) {
        System.out.println(nombre + " envió: " + mensaje);
    }
}

// Un invitado no puede publicar ni enviar mensajes, pero hereda esos métodos
// Cualquier código que use Usuario espera que funcionen pero fallan
public class UsuarioInvitado extends Usuario {
    public UsuarioInvitado(String nombre) {
        super(nombre);
    }

    public void publicarContenido(String contenido) {
        throw new RuntimeException("Los invitados no pueden publicar"); 
    }

    public void enviarMensaje(String mensaje) {
        throw new RuntimeException("Los invitados no pueden enviar mensajes"); 
    }
}


Usuario u = new UsuarioInvitado("Juan");
u.publicarContenido("Hola"); // RuntimeException

//-- CON LSP --
// Cada clase implementa solo lo que puede hacer
public interface Usuario {
    String getNombre();
}

public interface UsuarioActivo extends Usuario {
    void publicarContenido(String contenido);
    void enviarMensaje(String mensaje);
}

public class UsuarioRegistrado implements UsuarioActivo {
    private String nombre;

    public UsuarioRegistrado(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }

    public void publicarContenido(String contenido) {
        System.out.println(nombre + " publicó: " + contenido);
    }

    public void enviarMensaje(String mensaje) {
        System.out.println(nombre + " envió: " + mensaje);
    }
}

public class UsuarioInvitado implements Usuario {
    private String nombre;

    public UsuarioInvitado(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
    // Solo puede leer, no tiene métodos que no le corresponden
}

// Uso
UsuarioActivo registrado = new UsuarioRegistrado("Juan");
registrado.publicarContenido("Hola mundo"); 

Usuario invitado = new UsuarioInvitado("Invitado");
System.out.println(invitado.getNombre()); 
