import java.util.Scanner;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
public class Sistema {
    private Vector<Paciente> ListaPacientes = new Vector<Paciente>();
    private Vector<Medico> ListaMedicos = new Vector<Medico>();
    private Map<String, List<Medico>> MedicosPorEspecialidad = new HashMap<>();

    public void MenuPrincipal() {
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("1. Registrar paciente");
            System.out.println("2. Eliminar paciente");
            System.out.println("3. Modificar paciente");
            System.out.println("4. Mostrar peso más repetido");
            System.out.println("5. Mostrar cantidad de pacientes por peso");
            System.out.println("6. Mostrar peso mayor y menor");
            System.out.println("7. Mostrar cantidad de pacientes por rango de peso");
            System.out.println("8. Mostrar lista de pacientes ordenados por apellidos");
            System.out.println("9. Mostrar médico que atendió a un paciente");
            System.out.println("10. Buscar doctores por especialidad");
            System.out.println("0. Salir");

            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    RegistrarPaciente();
                    break;
                case 2:
                    EliminarPaciente();
                    break;
                case 3:
                    ModificarPaciente();
                    break;
                case 4:
                    MostrarPesoMasRepetido();
                    break;
                case 5:
                    MostrarCantidadPorPeso();
                    break;
                case 6:
                    MostrarPesoMayorYMenor();
                    break;
                case 7:
                    MostrarCantidadPorRangoDePeso();
                    break;
                case 8:
                    MostrarListaPacientesOrdenados();
                    break;
                case 9:
                    MostrarMedicoQueAtendio();
                    break;
                case 10:
                    BuscarDoctoresPorEspecialidad();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }

        } while (opcion != 0);
    }

    private void RegistrarPaciente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nIngrese el DNI del paciente:");
        int dni = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nombre del paciente:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la dirección del paciente:");
        String direccion = scanner.nextLine();

        System.out.println("Ingrese el peso del paciente:");
        double peso = scanner.nextDouble();

        System.out.println("Ingrese la temperatura del paciente:");
        double temperatura = scanner.nextDouble();

        Medico medicoAtendio = ObtenerMedico();

        Paciente nuevoPaciente = new Paciente(dni, nombre, direccion, peso, temperatura, medicoAtendio);
        ListaPacientes.add(nuevoPaciente);

        System.out.println("Paciente registrado con éxito.");
    }

    private void EliminarPaciente() {
        Scanner scanner = new Scanner(System.in);

        if (ListaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        System.out.println("Lista de pacientes:");
        MostrarListaPacientes();

        System.out.println("Ingrese la posición del paciente que desea eliminar:");
        int posicionEliminar = scanner.nextInt();

        if (posicionEliminar < 1 || posicionEliminar > ListaPacientes.size()) {
            System.out.println("Posición no válida. Intente nuevamente.");
            return;
        }

        Paciente pacienteEliminado = ListaPacientes.remove(posicionEliminar - 1);

        System.out.println("Paciente eliminado con éxito:");
        MostrarDatosPaciente(pacienteEliminado);
    }

    private void ModificarPaciente() {
        Scanner scanner = new Scanner(System.in);

        if (ListaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados para modificar.");
            return;
        }

        System.out.println("Lista de pacientes:");
        MostrarListaPacientes();

        System.out.println("Ingrese la posición del paciente que desea modificar:");
        int posicionModificar = scanner.nextInt();

        if (posicionModificar < 1 || posicionModificar > ListaPacientes.size()) {
            System.out.println("Posición no válida. Intente nuevamente.");
            return;
        }

        Paciente pacienteModificar = ListaPacientes.get(posicionModificar - 1);

        System.out.println("Ingrese el nuevo DNI del paciente:");
        int nuevoDNI = scanner.nextInt();
        pacienteModificar.setDNI(nuevoDNI);
        scanner.nextLine();

        System.out.println("Ingrese el nuevo nombre del paciente:");
        String nuevoNombre = scanner.nextLine();
        pacienteModificar.setNombre(nuevoNombre);

        System.out.println("Ingrese la nueva dirección del paciente:");
        String nuevaDireccion = scanner.nextLine();
        pacienteModificar.setDireccion(nuevaDireccion);

        System.out.println("Ingrese el nuevo peso del paciente:");
        double nuevoPeso = scanner.nextDouble();
        pacienteModificar.setPeso(nuevoPeso);

        System.out.println("Ingrese la nueva temperatura del paciente:");
        double nuevaTemperatura = scanner.nextDouble();
        pacienteModificar.setTemperatura(nuevaTemperatura);

            Medico nuevoMedicoAtendio = ObtenerNuevoMedico();
        pacienteModificar.setMedicoAtendio(nuevoMedicoAtendio);

        System.out.println("Paciente modificado con éxito:");
        MostrarDatosPaciente(pacienteModificar);
    }

    private void MostrarPesoMasRepetido() {
        if (ListaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        Map<Double, Integer> PromedioPesos = new HashMap<>();

        for (Paciente paciente : ListaPacientes) {
            double peso = paciente.getPeso();
            PromedioPesos.put(peso, PromedioPesos.getOrDefault(peso, 0) + 1);
        }

        double pesoMasRepetido = 0;
        int maxFrecuencia = 0;

        for (Map.Entry<Double, Integer> entry : PromedioPesos.entrySet()) {
            double peso = entry.getKey();
            int frecuencia = entry.getValue();

            if (frecuencia > maxFrecuencia) {
                maxFrecuencia = frecuencia;
                pesoMasRepetido = peso;
            }
        }

        System.out.println("El peso que más se repite es: " + pesoMasRepetido + " kg");
    }

    private void MostrarCantidadPorPeso() {
        if (ListaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        Map<Double, Integer> cantidadPorPeso = new HashMap<>();

        for (Paciente paciente : ListaPacientes) {
            double peso = paciente.getPeso();
            cantidadPorPeso.put(peso, cantidadPorPeso.getOrDefault(peso, 0) + 1);
        }

        System.out.println("Cantidad de pacientes por peso:");

        for (Map.Entry<Double, Integer> entry : cantidadPorPeso.entrySet()) {
            double peso = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println(peso + " kg: " + cantidad + " pacientes");
        }
    }

    private void MostrarPesoMayorYMenor() {
        if (ListaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        double pesoMayor = Double.MIN_VALUE;
        double pesoMenor = Double.MAX_VALUE;

        for (Paciente paciente : ListaPacientes) {
            double peso = paciente.getPeso();
            if (peso > pesoMayor) {
                pesoMayor = peso;
            }
            if (peso < pesoMenor) {
                pesoMenor = peso;
            }
        }

        System.out.println("Peso mayor: " + pesoMayor + " kg");
        System.out.println("Peso menor: " + pesoMenor + " kg");
    }

    private void MostrarCantidadPorRangoDePeso() {
        if (ListaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        double PesoMinimo = Double.MAX_VALUE;
        double PesoMaximo = Double.MIN_VALUE;

        for (Paciente paciente : ListaPacientes) {
            double Peso = paciente.getPeso();
            if (Peso < PesoMinimo) {
                PesoMinimo = Peso;
            }
            if (Peso > PesoMaximo) {
                PesoMaximo = Peso;
            }
        }

        double rango = 10;
        PesoMinimo = Math.floor(PesoMinimo / 10) * 10;
        PesoMaximo = Math.ceil(PesoMaximo / 10) * 10;

        Map<String, Integer> cantidadPorRango = new HashMap<>();

        for (double i = PesoMinimo; i <= PesoMaximo; i += rango) {
            double RangoInferior = i;
            double RangoSuperior = i + rango;
            String Clave = String.format("%.0f - %.0f kg", RangoInferior, RangoSuperior);
            cantidadPorRango.put(Clave, 0);
        }

        for (Paciente paciente : ListaPacientes) {
            double Peso = paciente.getPeso();
            for (double i = PesoMinimo; i <= PesoMaximo; i += rango) {
                double RangoInferior = i;
                double RangoSuperior = i + rango;
                if (Peso >= RangoInferior && Peso < RangoSuperior) {
                    String clave = String.format("%.0f - %.0f kg", RangoInferior, RangoSuperior);
                    cantidadPorRango.put(clave, cantidadPorRango.get(clave) + 1);
                    break;
                }
            }
        }

        System.out.println("Cantidad de pacientes por rango de peso:");

        for (Map.Entry<String, Integer> entry : cantidadPorRango.entrySet()) {
            String rangoPeso = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println(rangoPeso + ": " + cantidad + " pacientes");
        }
    }

    private void MostrarListaPacientesOrdenados() {
        if (ListaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        Collections.sort(ListaPacientes, Comparator.comparing(Paciente::getNombre));

        System.out.println("Lista de pacientes ordenada por nombres:");

        for (Paciente paciente : ListaPacientes) {
            MostrarDatosPaciente(paciente);
            System.out.println("----------------------");
        }
    }

    private void MostrarMedicoQueAtendio() {
        Scanner scanner = new Scanner(System.in);

        if (ListaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        System.out.println("Ingrese el DNI del paciente:");
        int dniBuscar = scanner.nextInt();

        Paciente pacienteEncontrado = BuscarPacientePorDNI(dniBuscar);

        if (pacienteEncontrado != null) {
            Medico medicoAtendio = pacienteEncontrado.getMedicoAtendio();
            if (medicoAtendio != null) {
                System.out.println("El paciente fue atendido por el médico:");
                MostrarDatosMedico(medicoAtendio);
            } else {
                System.out.println("El paciente no tiene asignado un médico.");
            }
        } else {
            System.out.println("No se encontró un paciente con el DNI ingresado.");
        }
    }

    private void BuscarDoctoresPorEspecialidad() {
        Scanner scanner = new Scanner(System.in);

        if (ListaMedicos.isEmpty()) {
            System.out.println("No hay doctores registrados.");
            return;
        }

        System.out.println("Especialidades disponibles:");

        int contador = 1;
        for (String especialidad : MedicosPorEspecialidad.keySet()) {
            System.out.println(contador + ". " + especialidad);
            contador++;
        }

        System.out.println("Ingrese el número de la especialidad que desea buscar:");
        int numeroEspecialidad = scanner.nextInt();

        List<Medico> medicosEspecialidad = ObtenerMedicosPorNumeroEspecialidad(numeroEspecialidad);

        if (!medicosEspecialidad.isEmpty()) {
            System.out.println("Doctores con la especialidad seleccionada:");
            for (Medico medico : medicosEspecialidad) {
                MostrarDatosMedico(medico);
            }
        } else {
            System.out.println("No se encontraron doctores con la especialidad seleccionada.");
            System.out.println("----------------------");
        }
    }

    private void MostrarListaPacientes() {
        if (ListaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        for (int i = 0; i < ListaPacientes.size(); i++) {
            System.out.println("Posición " + (i + 1) + ":");
            MostrarDatosPaciente(ListaPacientes.get(i));
            System.out.println("----------------------");
        }
    }

    private void MostrarDatosPaciente(Paciente paciente) {
        System.out.println("DNI: " + paciente.getDNI());
        System.out.println("Nombre: " + paciente.getNombre());
        System.out.println("Dirección: " + paciente.getDireccion());
        System.out.println("Peso: " + paciente.getPeso() + " kg");
        System.out.println("Temperatura: " + paciente.getTemperatura() + " °C");

        Medico medicoAtendio = paciente.getMedicoAtendio();
        if (medicoAtendio != null) {
            System.out.println("Médico que atendió: " + medicoAtendio.getNombre() + " (Colegiatura: " + medicoAtendio.getNumeroColegiatura() + ")");
        } else {
            System.out.println("Médico que atendió: No especificado");
        }
    }

    private void MostrarDatosMedico(Medico medico) {
        System.out.println("Nombre: " + medico.getNombre());
        System.out.println("Especialidad: " + medico.getEspecialidad());
        System.out.println("Número de colegiatura: " + medico.getNumeroColegiatura());
        System.out.println("----------------------");
    }


    private List<Medico> ObtenerMedicosPorNumeroEspecialidad(int numeroEspecialidad) {
        int contador = 1;
        for (String especialidad : MedicosPorEspecialidad.keySet()) {
            if (contador == numeroEspecialidad) {
                return MedicosPorEspecialidad.get(especialidad);
            }
            contador++;
        }
        return Collections.emptyList();
    }
    private Medico ObtenerNuevoMedico() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del nuevo médico:");
        String nuevoNombre = scanner.nextLine();

        System.out.println("Ingrese la especialidad del nuevo médico:");
        String nuevaEspecialidad = scanner.nextLine();

        System.out.println("Ingrese el número de colegiatura del nuevo médico:");
        int nuevoNumeroColegiatura = scanner.nextInt();
        scanner.nextLine();

        Medico nuevoMedico = new Medico(nuevoNumeroColegiatura, nuevoNombre, nuevaEspecialidad);
        ListaMedicos.add(nuevoMedico);

        System.out.println("Médico registrado con éxito.");

        return nuevoMedico;
    }
    private Medico ObtenerMedico() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del médico:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la especialidad del médico:");
        String especialidad = scanner.nextLine();

        System.out.println("Ingrese el número de colegiatura del médico:");
        int numeroColegiatura = scanner.nextInt();
        scanner.nextLine();

        Medico nuevoMedico = new Medico(numeroColegiatura, nombre, especialidad);
        ListaMedicos.add(nuevoMedico);

        MedicosPorEspecialidad.computeIfAbsent(especialidad, k -> new ArrayList<>()).add(nuevoMedico);

        return nuevoMedico;
    }
    private Paciente BuscarPacientePorDNI(int dniBuscar) {
        for (Paciente paciente : ListaPacientes) {
            if (paciente.getDNI() == dniBuscar) {
                return paciente;
            }
        }
        return null;
    }
}
