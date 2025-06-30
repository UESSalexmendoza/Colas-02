public class Estudiante {
    private String identificacion;
    private String nombres;
    private String periodo;
    private String carrera;
    private String asignatura;
    private String correo;

    public Estudiante(String identificacion, String nombres, String periodo, String carrera, String asignatura, String correo) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.periodo = periodo;
        this.carrera = carrera;
        this.asignatura = asignatura;
        this.correo = correo;
    }
    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public String getPeriodo() {
        return periodo;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public String getCorreo() {
        return correo;
    }
    public String getClaveAsignacion() {
        return carrera + "-" + asignatura;
    }

    private static String completarCadena(String texto, int anchoTotal, char caracterRelleno, boolean alFinal) {
        if (texto.length() >= anchoTotal) {
            return texto.substring(0, anchoTotal);
        }

        int cantidadRelleno = anchoTotal - texto.length();
        String relleno = String.valueOf(caracterRelleno).repeat(cantidadRelleno);

        return alFinal ? texto + relleno : relleno + texto;
    }    

    public String toStringRectangular() {
        final String RESET = "\u001B[0m";
        final String CYAN = "\u001B[36m";
        StringBuilder sb = new StringBuilder();
        sb.append(CYAN + "╔" + completarCadena("",127, '═', true) + "╗\n");
        sb.append(CYAN + "║" + RESET + completarCadena(" Identificación",21, ' ', true) + ": " + completarCadena(identificacion,40, ' ', true) + 
                CYAN + "║" + RESET + completarCadena(" Nombres",21, ' ', true) + ": " + completarCadena(nombres,40, ' ', true) + CYAN  + "║\n");
        sb.append(CYAN + "║" + RESET + completarCadena(" Correo",21, ' ', true) + ": " + completarCadena(correo,40, ' ', true) + 
                CYAN + "║" + RESET + completarCadena(" Periodo",21, ' ', true) + ": " + completarCadena(periodo,40, ' ', true) + CYAN  + "║\n");
        sb.append(CYAN + "║" + RESET + completarCadena(" Carrera",21, ' ', true) + ": " + completarCadena(carrera,40, ' ', true) + 
                CYAN + "║" + RESET + completarCadena(" Asignatura",21, ' ', true) + ": " + completarCadena(asignatura,40, ' ', true) + CYAN  + "║\n");
        sb.append(CYAN + "╚" + completarCadena("",127, '═', true) + "╝\n");
        return sb.toString();
    }
}
