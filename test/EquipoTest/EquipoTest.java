package test.EquipoTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import controlador.Controlador_equipo;
import modelo.Equipo_futbol;

public class EquipoTest {

    Controlador_equipo controlador_equipo = new Controlador_equipo();

    /**
     * 
     */
    @Test
    public void agregarEquipo() {
        Equipo_futbol equipo_futbol = new Equipo_futbol();

        equipo_futbol.setNombre_equipo("Manchester City");
        equipo_futbol.setDirector("El Cholo");
        equipo_futbol.setEstado("A");

        String mensaje = controlador_equipo.guardarEquipo(equipo_futbol);

        assertEquals("Equipo registrado correctamente!", mensaje);
    }

    /**
     * 
     */
    @Test
    public void actualizarEquipo() {
        Equipo_futbol equipo_futbol = new Equipo_futbol();

        equipo_futbol.setId_equipo(407);
        equipo_futbol.setNombre_equipo("Manchester United");
        equipo_futbol.setDirector("Diego");

        String mensaje = controlador_equipo.actualizarEquipo(equipo_futbol);

        assertEquals("Equipo actualizado correctamente", mensaje);
    }

    /**
     * 
     */
    @Test
    public void eliminarEquipo() {

        String mensaje = controlador_equipo.eliminarEquipo(407);
        assertEquals("Equipo eliminado correctamente", mensaje);
    }

    /**
     * 
     */
    @Test
    public void ListarEquipos() {

        List<Equipo_futbol> tmp = controlador_equipo.listarEquipos();

        assertEquals("Manchester United", tmp.get(7).getNombre_equipo());
        assertEquals("Diego", tmp.get(7).getDirector());
    }
}