import java.util.*;

public class RegistroCola {

    // Múltiples colas agrupadas por "periodo|carrera|asignatura"
    private Map<String, Queue<Estudiante>> colasPorGrupo;
    private final int LIMITE_POR_ASIGNATURA = 30;
    final String RESET = "\u001B[0m";
    final String CYAN = "\u001B[36m";
    final String WHITE = "\u001B[37m";
    final String BLUE = "\u001B[34m";
    final String RED = "\u001B[31m";
    
    public RegistroCola() {
        colasPorGrupo = new HashMap<>();
    }
 
    private static String completarCadena(String texto, int anchoTotal, char caracterRelleno, boolean alFinal) {
        if (texto.length() >= anchoTotal) {
            return texto.substring(0, anchoTotal);
        }

        int cantidadRelleno = anchoTotal - texto.length();
        String relleno = String.valueOf(caracterRelleno).repeat(cantidadRelleno);

        return alFinal ? texto + relleno : relleno + texto;
    }    

    public void mostrarResumenCuposPorGrupo() {
        System.out.println(CYAN + "╔" + completarCadena("",127, '═', true) + "╗");
        String header =   "║" + completarCadena(" Periodo",10, ' ', true) +
                          "║" + completarCadena(" Carrera",30, ' ', true) +
                          "║" + completarCadena(" Asignatura",50, ' ', true) +
                          "║" + completarCadena(" Cupo",6, ' ', true) +
                          "║" + completarCadena(" Registrados",13, ' ', true) +
                          "║" + completarCadena(" Disponibles",13, ' ', true) + CYAN + "║";
        System.out.println(header);
        System.out.println(CYAN + "╠" + completarCadena("",127, '═', true) + "╣" + RESET);
        if (colasPorGrupo.isEmpty()) {
            System.out.println(CYAN + "║" + RED + completarCadena("                                                 No hay registros disponibles",127, ' ', true) + CYAN + "║");
        } else {
            for (Map.Entry<String, Queue<Estudiante>> entry : colasPorGrupo.entrySet()) {
                String[] partes = entry.getKey().split("\\|");
                String periodo = partes[0];
                String carrera = partes[1];
                String asignatura = partes[2];
                int ocupados = entry.getValue().size();
                int disponibles = LIMITE_POR_ASIGNATURA - ocupados;
                String fila=CYAN +  "║" + WHITE + completarCadena(" "+ periodo,10, ' ', true) + WHITE + 
                            CYAN +  "║" + WHITE + completarCadena(" " + carrera,30, ' ', true) + WHITE +
                            CYAN +  "║" + WHITE + completarCadena(" " + asignatura,50, ' ', true) + WHITE +
                            CYAN +  "║" + WHITE + completarCadena("  " + LIMITE_POR_ASIGNATURA,6, ' ', true) +
                            CYAN +  "║" + WHITE + completarCadena("      " + ocupados,13, ' ', true) +
                            CYAN +  "║" + WHITE + completarCadena("      " + disponibles,13, ' ', true) + CYAN + "║";
                System.out.println(WHITE + fila + RESET);
            }
        }
        System.out.println(CYAN + "╚" + completarCadena("",127, '═', true) + "╝");
    }
    // Clave única por grupo
    private String obtenerClave(Estudiante e) {
        return e.getPeriodo() + "|" + e.getCarrera() + "|" + e.getAsignatura();
    }

    // Registrar estudiante en su cola correspondiente
    public boolean registrarEstudiante(Estudiante e) {
        String clave = obtenerClave(e);
        colasPorGrupo.putIfAbsent(clave, new LinkedList<>());
        Queue<Estudiante> cola = colasPorGrupo.get(clave);
        if (cola.size() >= LIMITE_POR_ASIGNATURA) {
                String mensaje = " NO HAY MAS CUPO PARA: " + clave + " ";
                int ancho = mensaje.length();
                String bordeSup = RED + "╔" + "═".repeat(ancho) + "╗" + RESET;
                String linea = RED + "║" + WHITE + mensaje + RED + "║" + RESET;
                String bordeInf = RED + "╚" + "═".repeat(ancho) + "╝" + RESET;
                System.out.println(bordeSup);
                System.out.println(linea);
                System.out.println(bordeInf);
            return false;
        }

        cola.add(e);
        return true;
    }

    // Mostrar primer estudiante del grupo
    public void mostrarPrimero(String periodo, String carrera, String asignatura) {
        String clave = periodo + "|" + carrera + "|" + asignatura;
        Queue<Estudiante> cola = colasPorGrupo.get(clave);
        if (cola == null || cola.isEmpty()) {
            System.out.println(CYAN + "╔" + completarCadena("",127, '═', true) + "╗");
            String linea = CYAN + "║" + RED + " Cola Vacia para " + 
                           CYAN + "║" + RESET + " " + completarCadena(periodo,10, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(carrera,47, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(asignatura,47, ' ', true) +
                           CYAN + "║";
            System.out.println(linea);
            System.out.println(CYAN + "╚" + completarCadena("",127, '═', true) + "╝");

        } else {
            System.out.println(CYAN + "╔" + completarCadena("",127, '═', true) + "╗");
            String linea = CYAN + "║" + WHITE + " Primer Registro " + 
                           CYAN + "║" + RESET + " " + completarCadena(periodo,10, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(carrera,47, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(asignatura,47, ' ', true) +
                           CYAN + "║";
            System.out.println(linea);
            System.out.println(CYAN + "╚" + completarCadena("",127, '═', true) + "╝");
            System.out.print(cola.peek().toStringRectangular());
        }
    }

    // Mostrar último estudiante del grupo
    public void mostrarUltimo(String periodo, String carrera, String asignatura) {
        String clave = periodo + "|" + carrera + "|" + asignatura;
        Queue<Estudiante> cola = colasPorGrupo.get(clave);
        if (cola == null || cola.isEmpty()) {
            System.out.println(CYAN + "╔" + completarCadena("",127, '═', true) + "╗");
            String linea = CYAN + "║" + RED + " Cola Vacia para " + 
                           CYAN + "║" + RESET + " " + completarCadena(periodo,10, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(carrera,47, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(asignatura,47, ' ', true) +
                           CYAN + "║";
            System.out.println(linea);
            System.out.println(CYAN + "╚" + completarCadena("",127, '═', true) + "╝");
        } else {
            Estudiante ultimo = ((LinkedList<Estudiante>) cola).peekLast(); // Acceso directo al último
            System.out.println(CYAN + "╔" + completarCadena("",127, '═', true) + "╗");
            String linea = CYAN + "║" + WHITE + " Ultimo Registro " + 
                           CYAN + "║" + RESET + " " + completarCadena(periodo,10, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(carrera,47, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(asignatura,47, ' ', true) +
                           CYAN + "║";
            System.out.println(linea);
            System.out.println(CYAN + "╚" + completarCadena("",127, '═', true) + "╝");
            System.out.print(ultimo.toStringRectangular());
        }
    }


    // Eliminar primer estudiante de un grupo específico
    public void eliminarPrimero(String periodo, String carrera, String asignatura) {
        String clave = periodo + "|" + carrera + "|" + asignatura;

        Queue<Estudiante> cola = colasPorGrupo.get(clave);
        if (cola == null || cola.isEmpty()) {
            System.out.println(CYAN + "╔" + completarCadena("",127, '═', true) + "╗");
            String linea = CYAN + "║" + RED + " Cola Vacia para " + 
                           CYAN + "║" + RESET + " " + completarCadena(periodo,10, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(carrera,47, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(asignatura,47, ' ', true) +
                           CYAN + "║";
            System.out.println(linea);
            System.out.println(CYAN + "╚" + completarCadena("",127, '═', true) + "╝");
            return;
        }
            System.out.println(CYAN + "╔" + completarCadena("",127, '═', true) + "╗");
            String linea = CYAN + "║" + WHITE + " Primer Registro Eliminado para " + 
                           CYAN + "║" + RESET + " " + completarCadena(periodo,9, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(carrera,40, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(asignatura,40, ' ', true) +
                           CYAN + "║";
            System.out.println(linea);
            System.out.println(CYAN + "╚" + completarCadena("",127, '═', true) + "╝");

        Estudiante eliminado = cola.poll();
        System.out.println(" Estudiante eliminado:\n" + eliminado.toStringRectangular());
    }

    // Mostrar todos los estudiantes de un grupo en un cuadro decorado
    public void mostrarGrupo(String periodo, String carrera, String asignatura) {
        String clave = periodo + "|" + carrera + "|" + asignatura;
        Queue<Estudiante> cola = colasPorGrupo.get(clave);

        if (cola == null || cola.isEmpty()) {
            System.out.println(CYAN + "╔" + completarCadena("",127, '═', true) + "╗");
            String linea = CYAN + "║" + RED + " No hay registros en cola " + 
                           CYAN + "║" + RESET + " " + completarCadena(periodo,9, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(carrera,43, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(asignatura,43, ' ', true) +
                           CYAN + "║";
            System.out.println(linea);
            System.out.println(CYAN + "╚" + completarCadena("",127, '═', true) + "╝");
            return;
        }
            System.out.println(CYAN + "╔" + completarCadena("",127, '═', true) + "╗");
            String linea = CYAN + "║" + WHITE + " Listado de Registros " + 
                           CYAN + "║" + RESET + " " + completarCadena(periodo,9, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(carrera,45, ' ', true) + 
                           CYAN + "║" + RESET + " " + completarCadena(asignatura,45, ' ', true) +
                           CYAN + "║";
            System.out.println(linea);
            System.out.println(CYAN + "╚" + completarCadena("",127, '═', true) + "╝");

        // Mostrar los estudiantes con sus cuadros rectangulares
        for (Estudiante e : cola) {
            System.out.print(e.toStringRectangular());
        }
    }
    public void mostrarTodosLosRegistros() {
        if (colasPorGrupo.isEmpty()) {
            System.out.println(CYAN + "╔" + completarCadena("",127, '═', true) + "╗");
            String linea = CYAN + "║" + RED + completarCadena("",50,' ', true) + completarCadena("No hay registros cargados",77, ' ', true) + CYAN + "║";
            System.out.println(linea);
            System.out.println(CYAN + "╚" + completarCadena("",127, '═', true) + "╝");
            return;
        }

        for (Map.Entry<String, Queue<Estudiante>> entry : colasPorGrupo.entrySet()) {
            String grupo = entry.getKey();
            Queue<Estudiante> estudiantes = entry.getValue();
            System.out.println(CYAN + "╔" + completarCadena("",93, '═', true) + "╗");
            System.out.println(CYAN + "║" + completarCadena(" Grupo: " + grupo,93, ' ', true) + "║");
            System.out.println(CYAN + "╚" + completarCadena("",93, '═', true) + "╝");

            if (estudiantes.isEmpty()) {
                System.out.println("  (Sin estudiantes registrados en este grupo)");
            } else {
                for (Estudiante e : estudiantes) {
                    System.out.print(e.toStringRectangular());
                }
            }

            System.out.println(); // Espacio entre grupos
        }
    }

    // Vaciar todos los registros
    public void vaciarTodo() {
        colasPorGrupo.clear();
        System.out.println(CYAN + "╔" + completarCadena("",127, '═', true) + "╗");
        String linea = CYAN + "║" + RESET + completarCadena("",40, ' ', true) + completarCadena("Se ha realizado la limpieza de las colas ",87, ' ', true) + CYAN + "║";
        System.out.println(linea);
        System.out.println(CYAN + "╚" + completarCadena("",127, '═', true) + "╝");        
    }

}
