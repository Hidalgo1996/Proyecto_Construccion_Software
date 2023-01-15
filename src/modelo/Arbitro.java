
package modelo;


public class Arbitro {
    
    //Atributos de clase
    private int id_arbitro;
    private String peso;
    private String categoria;
    private String altura;
    private String nombre;
    private String apellido;
    private String nombre_usuario;
    private String contrasenia;
    private String email;
    private String fecha_nacimiento;
    private String nacionalidad;
    private String partidos;
    private String anio_debut;
    private int id_administrador;
    private int id_secretaria;
    private int id_presidente;
    
    
    
    //Constructor de la clase
    public Arbitro() {
        
        this.id_arbitro = 0;
        this.peso = "";
        this.categoria = "";
        this.altura = "";
        this.nombre = "";
        this.apellido = "";
        this.nombre_usuario = "";
        this.contrasenia = "";
        this.email = "";
        this.fecha_nacimiento = "";
        this.nacionalidad = "";
        this.partidos = "";
        this.anio_debut = "";
        this.id_administrador = 0;
        this.id_secretaria = 0;
        this.id_presidente = 0;
    }
    
    
    
    //Getter y setter
    //Son metodoa para obtener y modificar las variables de clase

    public int getId_arbitro() {
        return id_arbitro;
    }

    public void setId_arbitro(int id_arbitro) {
        this.id_arbitro = id_arbitro;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getPartidos() {
        return partidos;
    }

    public void setPartidos(String partidos) {
        this.partidos = partidos;
    }

    public String getAnio_debut() {
        return anio_debut;
    }

    public void setAnio_debut(String anio_debut) {
        this.anio_debut = anio_debut;
    }

    public int getId_administrador() {
        return id_administrador;
    }

    public void setId_administrador(int id_administrador) {
        this.id_administrador = id_administrador;
    }

    public int getId_secretaria() {
        return id_secretaria;
    }

    public void setId_secretaria(int id_secretaria) {
        this.id_secretaria = id_secretaria;
    }

    public int getId_presidente() {
        return id_presidente;
    }

    public void setId_presidente(int id_presidente) {
        this.id_presidente = id_presidente;
    }
    
    
    
    
    
}
