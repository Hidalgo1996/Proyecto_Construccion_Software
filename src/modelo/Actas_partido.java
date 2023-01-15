package modelo;

public class Actas_partido {

    //Atributos de clase
    private int id_acta_partido;
    private String hora_inicio_partido;
    private String hora_fin_partido;
    private String nombre_equipo_rival;
    private String nombre_equipo_local;
    private int duracion_partido;
    private int goles_equipo_local;
    private int goles_equipo_rival;
    private String equipo_ganador;
    private int id_arbitro;

    //Constructor vacio
    public Actas_partido() {
        this.id_acta_partido = 0;
        this.hora_inicio_partido = "";
        this.hora_fin_partido = "";
        this.nombre_equipo_rival = "";
        this.nombre_equipo_local = "";
        this.duracion_partido = 0;
        this.goles_equipo_local = 0;
        this.goles_equipo_rival = 0;
        this.equipo_ganador = "";
        this.id_arbitro = 0;
    }

    //Constructor sobrecargado
    public Actas_partido(int id_acta_partido, String hora_inicio_partido, String hora_fin_partido, String nombre_equipo_rival, String nombre_equipo_local,
            int duracion_partido, int goles_equipo_local, int goles_equipo_rival, String equipo_ganador, int id_arbitro) {

        this.id_acta_partido = id_acta_partido;
        this.hora_inicio_partido = hora_inicio_partido;
        this.hora_fin_partido = hora_fin_partido;
        this.nombre_equipo_rival = nombre_equipo_rival;
        this.nombre_equipo_local = nombre_equipo_local;
        this.duracion_partido = duracion_partido;
        this.goles_equipo_local = goles_equipo_local;
        this.goles_equipo_rival = goles_equipo_rival;
        this.equipo_ganador = equipo_ganador;
        this.id_arbitro = id_arbitro;
    }

    //Getter y setters
    public int getId_acta_partido() {
        return id_acta_partido;
    }

    public void setId_acta_partido(int id_acta_partido) {
        this.id_acta_partido = id_acta_partido;
    }

    public String getHora_inicio_partido() {
        return hora_inicio_partido;
    }

    public void setHora_inicio_partido(String hora_inicio_partido) {
        this.hora_inicio_partido = hora_inicio_partido;
    }

    public String getHora_fin_partido() {
        return hora_fin_partido;
    }

    public void setHora_fin_partido(String hora_fin_partido) {
        this.hora_fin_partido = hora_fin_partido;
    }

    public String getNombre_equipo_rival() {
        return nombre_equipo_rival;
    }

    public void setNombre_equipo_rival(String nombre_equipo_rival) {
        this.nombre_equipo_rival = nombre_equipo_rival;
    }

    public String getNombre_equipo_local() {
        return nombre_equipo_local;
    }

    public void setNombre_equipo_local(String nombre_equipo_local) {
        this.nombre_equipo_local = nombre_equipo_local;
    }

    public int getDuracion_partido() {
        return duracion_partido;
    }

    public void setDuracion_partido(int duracion_partido) {
        this.duracion_partido = duracion_partido;
    }

    public int getGoles_equipo_local() {
        return goles_equipo_local;
    }

    public void setGoles_equipo_local(int goles_equipo_local) {
        this.goles_equipo_local = goles_equipo_local;
    }

    public int getGoles_equipo_rival() {
        return goles_equipo_rival;
    }

    public void setGoles_equipo_rival(int goles_equipo_rival) {
        this.goles_equipo_rival = goles_equipo_rival;
    }

    public String getEquipo_ganador() {
        return equipo_ganador;
    }

    public void setEquipo_ganador(String equipo_ganador) {
        this.equipo_ganador = equipo_ganador;
    }

    public int getId_arbitro() {
        return id_arbitro;
    }

    public void setId_arbitro(int id_arbitro) {
        this.id_arbitro = id_arbitro;
    }

}
