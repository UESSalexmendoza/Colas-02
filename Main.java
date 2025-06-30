import java.util.Scanner;

public class Main {
    private static String completarCadena(String texto, int anchoTotal, char caracterRelleno, boolean alFinal) {
        if (texto.length() >= anchoTotal) {
            return texto.substring(0, anchoTotal);
        }

        int cantidadRelleno = anchoTotal - texto.length();
        String relleno = String.valueOf(caracterRelleno).repeat(cantidadRelleno);

        return alFinal ? texto + relleno : relleno + texto;
    }    
    public static void main(String[] args) {
        RegistroCola registro = new RegistroCola();
        Scanner scanner = new Scanner(System.in);
        int opcion;
        String id = "";
        String nombres = "";
        String correo = "";
        String periodo = "";
        String carrera = "";
        String asignatura = "";

        do {
            // Codigos de color ANSI
            final String RESET = "\u001B[0m";
            final String CYAN = "\u001B[36m";
            final String YELLOW = "\u001B[33m";
            final String WHITE = "\u001B[37m";
            final String BLUE_BG = "\u001B[44m";


            System.out.println();
            System.out.println(CYAN + "╔" + completarCadena("",60,'═',true) + "╗" + RESET);
            System.out.println(CYAN + "║" + YELLOW + completarCadena("             MENU DE OPERACIONES DE COLA (FIFO)", 60,' ', true) + CYAN + "║" + RESET);
            System.out.println(CYAN + "╠" + completarCadena("",60,'═',true) + "╣" + RESET);
            System.out.println(CYAN + "║" + WHITE + completarCadena(" 1. Cargar registros de ejemplo",60,' ',true) + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + WHITE + completarCadena(" 2. Mostrar Resumen",60,' ',true) + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + WHITE + completarCadena(" 3. Agregar nuevo registro (Add)",60,' ',true) + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + WHITE + completarCadena(" 4. Mostrar Primer Registro (First)",60,' ',true) + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + WHITE + completarCadena(" 5. Mostrar Ultimo Registro (Last)",60,' ',true) + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + WHITE + completarCadena(" 6. Eliminar Primer Registro (Poll)",60,' ',true) + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + WHITE + completarCadena(" 7. Mostrar Registros por Grupo",60,' ',true) + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + WHITE + completarCadena(" 8. Mostrar Todos los Registros",60,' ',true) + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + WHITE + completarCadena(" 9. Limpiar Informacion",60,' ',true) + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + WHITE + completarCadena(" 0. Salir",60,' ',true) + CYAN + "║" + RESET);
            System.out.println(CYAN + "╚" + completarCadena("",60,'═',true) + "╝" + RESET);
            System.out.print(BLUE_BG + " Seleccione una opcion: " + RESET);

            while (!scanner.hasNextInt()) {
                System.out.print(BLUE_BG + "Por favor, ingrese un número valido." + RESET);
                scanner.next(); // limpiar entrada invalida
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    cargarEjemplo(registro);
                    registro.mostrarResumenCuposPorGrupo();
                    registro.mostrarTodosLosRegistros();
                    break;
                case 2:
                    registro.mostrarResumenCuposPorGrupo();
                    break;
                 case 3:
                    System.out.println("\nIngrese los datos del estudiante:" + RESET + RESET);
                    System.out.print(BLUE_BG + "Identificacion: " + RESET); id = scanner.nextLine();
                    System.out.print(BLUE_BG + "Nombres: " + RESET); nombres = scanner.nextLine();
                    System.out.print(BLUE_BG + "Correo: " + RESET); correo = scanner.nextLine();
                    System.out.print(BLUE_BG + "Periodo: " + RESET); periodo = scanner.nextLine();
                    System.out.print(BLUE_BG + "Carrera: " + RESET); carrera = scanner.nextLine();
                    System.out.print(BLUE_BG + "Asignatura: " + RESET); asignatura = scanner.nextLine();
                    Estudiante nuevo = new Estudiante(id, nombres, periodo, carrera, asignatura, correo);
                    if (registro.registrarEstudiante(nuevo))
                    {
                        System.out.println("Datos registrados:");
                    }
                    else
                    {
                        System.out.println("No se registro la informacion....");
                    }
                    System.out.print(nuevo.toStringRectangular());
                    break;
                case 4:
                    System.out.println("Ingrese los datos del grupo:");
                    System.out.print(BLUE_BG + "Periodo: " + RESET); periodo = scanner.nextLine();
                    System.out.print(BLUE_BG + "Carrera: " + RESET); carrera = scanner.nextLine();
                    System.out.print(BLUE_BG + "Asignatura: " + RESET); asignatura = scanner.nextLine();
                    registro.mostrarPrimero(periodo, carrera, asignatura);
                    break;
                case 5:
                    System.out.println("Ingrese los datos del grupo:");
                    System.out.print(BLUE_BG + "Periodo: " + RESET); periodo = scanner.nextLine();
                    System.out.print(BLUE_BG + "Carrera: " + RESET ); carrera = scanner.nextLine();
                    System.out.print(BLUE_BG + "Asignatura: " + RESET); asignatura = scanner.nextLine();
                    registro.mostrarUltimo(periodo, carrera, asignatura);
                    break;
                case 6:
                    System.out.print(BLUE_BG + "Periodo: " + RESET); periodo = scanner.nextLine();
                    System.out.print(BLUE_BG + "Carrera: " + RESET ); carrera = scanner.nextLine();
                    System.out.print(BLUE_BG + "Asignatura: " + RESET); asignatura = scanner.nextLine();
                    registro.eliminarPrimero(periodo, carrera, asignatura);
                    break;
                case 7:
                    System.out.println("Ingrese los datos del grupo:");
                    System.out.print("Periodo: "); periodo = scanner.nextLine();
                    System.out.print("Carrera: "); carrera = scanner.nextLine();
                    System.out.print("Asignatura: "); asignatura = scanner.nextLine();
                    registro.mostrarGrupo(periodo, carrera, asignatura);
                    break;
                case 8:
                    registro.mostrarTodosLosRegistros();
                    break;
                case 9:
                    registro.vaciarTodo();
                    break;
                case 0:
                    System.out.println("Gracias por usar el sistema.");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }

        } while (opcion != 0);
        scanner.close();
    }

    public static void cargarEjemplo(RegistroCola registro) {
        registro.registrarEstudiante(new Estudiante("1000000001", "Laura Herrera", "2025B", "Administracion de Empresas", "Finanzas I", "laura.herrera@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("1000000002", "Carlos Benitez", "2025B", "Administracion de Empresas", "Finanzas I", "carlos.benitez@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("1000000003", "Andrea Guzman", "2025B", "Administracion de Empresas", "Finanzas I", "andrea.guzman@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("1000000004", "Jose Mendoza", "2025B", "Administracion de Empresas", "Finanzas I", "jose.mendoza@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("1000000005", "Maria Castro", "2025B", "Administracion de Empresas", "Finanzas I", "maria.castro@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("1000000006", "Luis Torres", "2025B", "Administracion de Empresas", "Finanzas I", "luis.torres@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("1000000007", "Diana Paredes", "2025B", "Administracion de Empresas", "Finanzas I", "diana.paredes@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("1000000008", "Santiago Velez", "2025B", "Administracion de Empresas", "Finanzas I", "santiago.velez@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("1000000009", "Isabel Munoz", "2025B", "Administracion de Empresas", "Finanzas I", "isabel.munoz@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("1000000010", "Jorge Cevallos", "2025B", "Administracion de Empresas", "Finanzas I", "jorge.cevallos@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("999000001", "Nicolas Torres", "2025A", "Ingenieria Civil", "Topografia", "nicolas.torres@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("999000002", "Elena Lema", "2023B", "Ingenieria Ambiental", "Ecologia Aplicada", "elena.lema@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("0102030405", "Ana Reyes", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "ana.reyes@universidad.edu.ec"));
        registro.registrarEstudiante(new Estudiante("0605040302", "Pedro Lopez", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "pedro.lopez@universidad.edu.ec"));
        registro.registrarEstudiante(new Estudiante("1122334455", "Camila Vega", "2025A", "Ingenieria Industrial", "Investigacion de Operaciones", "camila.torres@universidad.edu.ec"));
        registro.registrarEstudiante(new Estudiante("2233445566", "Diego Gomez", "2025A", "Ingenieria Civil", "Resistencia de Materiales", "diego.gomez@universidad.edu.ec"));
        registro.registrarEstudiante(new Estudiante("3344556677", "Valeria Salinas", "2025A", "Ingenieria en Sistemas", "Programacion Avanzada", "valeria.munoz@universidad.edu.ec"));
        registro.registrarEstudiante(new Estudiante("4455667788", "Kevin Rosero", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "kevin.rosero@universidad.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500001", "Sofia Guerrero", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "sofia.guerrero@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500002", "Mateo Vargas", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "mateo.vargas@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500003", "Valentina Herrera", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "valentina.herrera@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500004", "Lucas Rojas", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "lucas.rojas@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500005", "Isabella Molina", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "isabella.molina@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500006", "Emiliano Torres", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "emiliano.torres@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500007", "Camila Salazar", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "camila.salazar@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500008", "Sebastian Chavez", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "sebastian.chavez@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500009", "Renata Espinoza", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "renata.espinoza@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500010", "Thiago Paredes", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "thiago.paredes@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500011", "Antonella Perez", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "antonella.perez@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500012", "Benjamin Cedeno", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "benjamin.cedeno@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500013", "Emma Loor", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "emma.loor@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500014", "Dylan Pacheco", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "dylan.pacheco@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500015", "Abril Mendoza", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "abril.mendoza@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500016", "Alejandro Vera", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "alejandro.vera@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500017", "Mariana Jacome", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "mariana.jacome@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500018", "Gael Hidalgo", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "gael.hidalgo@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500019", "Zoe Aguirre", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "zoe.aguirre@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500020", "Samuel Navarrete", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "samuel.navarrete@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500021", "Josefa Ordonez", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "josefa.ordonez@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500023", "Ximena Araujo", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "ximena.araujo@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500024", "Tomas Cordero", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "tomas.cordero@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500025", "Victoria Palacios", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "victoria.palacios@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500026", "Andres Molina", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "andres.molina@univ.edu.ec"));
        registro.registrarEstudiante(new Estudiante("202500027", "Julieta Ruiz", "2025A", "Ingenieria en Sistemas", "Estructura de Datos", "julieta.ruiz@univ.edu.ec"));
    }
}
