// Sistema de reportes de ventas

//-- SIN SRP - 

// Esta clase hace dos cosas, genera el reporte y lo guarda
public class Reporte {
    private String contenido;

    public Reporte(String contenido) {
        this.contenido = contenido;
    }

    public String generar() {
        return "Contenido del reporte: " + contenido;
    }

    // Esto no debería estar acá, guardar no es responsabilidad del reporte
    public void guardarEnArchivo(String nombreArchivo) throws IOException {
        try (FileWriter archivo = new FileWriter(nombreArchivo)) {
            archivo.write(this.generar());
        }
    }
}

//-- CON SRP --

// Esta clase se encarga solo  de la lógica del reporte
public class Reporte {
    private String contenido;

    public Reporte(String contenido) {
        this.contenido = contenido;
    }

    public String generar() {
        return "Contenido del reporte: " + contenido;
    }
}

// Esta clase se encarga solo de guardar el reporte
public class GuardadorDeReporte {
    public void guardarEnArchivo(Reporte reporte, String nombreArchivo) throws IOException {
        try (FileWriter archivo = new FileWriter(nombreArchivo)) {
            archivo.write(reporte.generar());
        }
    }
}

// Uso
Reporte reporte = new Reporte("Ventas del mes de abril");
GuardadorDeReporte guardador = new GuardadorDeReporte();
guardador.guardarEnArchivo(reporte, "abril.txt");
