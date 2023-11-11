public class Medico {
    private String Nombre;
    private String Especialidad;
    private int NumeroColegiatura;

    public int getNumeroColegiatura() {
        return NumeroColegiatura;
    }

    public Medico(int numeroColegiatura, String nombre, String especialidad) {
        this.NumeroColegiatura = numeroColegiatura;
        this.Nombre = nombre;
        this.Especialidad = especialidad;
    }

    public void setNumeroColegiatura(int numeroColegiatura) {
        this.NumeroColegiatura = numeroColegiatura;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.Especialidad = especialidad;
    }
}
