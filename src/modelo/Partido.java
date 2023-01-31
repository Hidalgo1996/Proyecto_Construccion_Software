package modelo;

public class Partido {
    
    protected Integer id_partido;
    protected Integer id_club_local;
    protected Integer id_club_rival;
    protected String estado;

    public Partido() {
    }


    public Partido(Integer id_partido, Integer id_club_local, Integer id_club_rival, String estado) {
        this.id_partido = id_partido;
        this.id_club_local = id_club_local;
        this.id_club_rival = id_club_rival;
        this.estado = estado;
    }


    public Integer getId_partido() {
        return id_partido;
    }
    public void setId_partido(Integer id_partido) {
        this.id_partido = id_partido;
    }
    public Integer getId_club_local() {
        return id_club_local;
    }
    public void setId_club_local(Integer id_club_local) {
        this.id_club_local = id_club_local;
    }
    public Integer getId_club_rival() {
        return id_club_rival;
    }
    public void setId_club_rival(Integer id_club_rival) {
        this.id_club_rival = id_club_rival;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
