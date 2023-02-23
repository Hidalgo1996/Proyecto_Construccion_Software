package test.ActaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;

import controlador.Controlador_actas_partido;
import excepciones.ActasException;
import modelo.Actas_partido;
import modelo.Partido;

public class ActaTest {

    Controlador_actas_partido controlador_actas_partido = new Controlador_actas_partido();

    /**
     * 
     */
    @Test
    public void agregarActa() {

        Actas_partido acta = new Actas_partido();
        acta.setPartido(new Partido(505));
        acta.setFecha_emision(Date.valueOf(LocalDate.now()));
        acta.setHora_inicio_partido("12:00:00");
        acta.setHora_fin_partido("14:00:00");
        acta.setNombre_equipo_local("");
        acta.setNombre_equipo_rival("");
        acta.setDuracion_partido("2:00:00");
        acta.setGoles_equipo_local(2);
        acta.setGoles_equipo_rival(3);
        acta.setEquipo_ganador("Manchester United");

        String passed;
        try {
            passed = controlador_actas_partido.guardarActa(acta);
            assertEquals("Acta registrada correctamente!", passed);
            assertEquals(true, passed);
            assertEquals("paquitos", acta.getEquipo_ganador());
        } catch (ActasException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 
     */
    @Test
    public void actualizarActa() {

    }

    /**
     * 
     */
    @Test
    public void eliminarActa() {

    }

    /**
     * 
     */
    @Test
    public void listarActas() {

    }
}
