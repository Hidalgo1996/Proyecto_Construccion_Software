package test.AgendaTest;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.Test;

import controlador.Controlador_agenda;
import modelo.Agenda;
import modelo.Partido;

public class AgendaTest {

    Controlador_agenda controlador_agenda = new Controlador_agenda();

    /**
     * 
     */
    @Test
    public void agregarAgenda() {
        Agenda agenda = new Agenda();
        agenda.setPartido_id(new Partido(505));
        agenda.setFecha_partido(Date.valueOf(LocalDate.now()));
        agenda.setLugar_partido("Machala");
        agenda.setHora_partido(Time.valueOf(LocalTime.now()));

        String mensaje = controlador_agenda.guardarAgenda(agenda);
        assertEquals("Agenda registrada correctamente", mensaje);
    }

    /**
     * 
     */
    @Test
    public void actualizarAgenda() {
        Agenda agenda = new Agenda();
        agenda.setId_agenda(712);
        agenda.setPartido_id(null);
        agenda.setFecha_partido(Date.valueOf(LocalDate.now()));
        agenda.setLugar_partido("El Cairo");
        agenda.setHora_partido(Time.valueOf(LocalTime.now()));

        String mensaje = controlador_agenda.actualizarAgenda(agenda);
        assertEquals("Agenda actualizada correctamente", mensaje);
    }

    /**
     * 
     */
    @Test
    public void listarAgendas() {

        List<Agenda> tmp = controlador_agenda.listarAgendas();

        assertEquals("Machala", tmp.get(4).getLugar_partido());
    }

    /**
     * 
     */
    @Test
    public void eliminarAgenda() {

        String mensaje = controlador_agenda.eliminarAgenda(712);
        assertEquals("Agenda eliminada correctamente", mensaje);
    }
}
