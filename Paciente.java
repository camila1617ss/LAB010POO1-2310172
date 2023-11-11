public class Paciente {
    private int DNI;
    private String Nombre;
    private String Direccion;
    private double Peso;
    private double Temperatura;
    private Medico MedicoAtendio;

    public Paciente(int DNI, String nombre, String direccion, double peso, double temperatura, Medico medicoAtendio) {
        this.DNI = DNI;
        this.Nombre = nombre;
        this.Direccion = direccion;
        this.Peso = peso;
        this.Temperatura = temperatura;
        this.MedicoAtendio = medicoAtendio;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }

    public double getPeso() {
        return Peso;
    }

    public void setPeso(double peso) {
        this.Peso = peso;
    }

    public double getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.Temperatura = temperatura;
    }

    public Medico getMedicoAtendio() {
        return MedicoAtendio;
    }

    public void setMedicoAtendio(Medico medicoAtendio) {
        this.MedicoAtendio = medicoAtendio;
    }
}
