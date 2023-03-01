package modelo;

public enum Categoria {

    PROFESIONAL(1, "PROFESIONAL"),
    JUVENIL(2, "JUVENIL"),
    INFANTIL(3, "INFANTIL");

    private int id;
    private String nombre;

    private Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}
