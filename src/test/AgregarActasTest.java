package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import controlador.Controlador_actas_partido;
import modelo.Actas_partido;

public class AgregarActasTest {

    private Controlador_actas_partido controller = new Controlador_actas_partido();
    private Actas_partido acta = new Actas_partido();

    @Test
    public void guardarActa(){
        
        Actas_partido acta = new Actas_partido(
            522,
            "14:00",
            "16:00",
            "Los juanes",
            "paquitos",
            50,
            3,
            2,
            "paquitos",
            500
        );
        /* acta.setEquipo_ganador("Los juanes");
        acta.setDuracion_partido(50);
        acta.setGoles_equipo_local(2);
        acta.setGoles_equipo_rival(1);
        acta.setHora_inicio_partido("14:00");
        acta.setHora_fin_partido("16:00");
        acta.setId_acta_partido(520);
        acta.setId_arbitro(501); */

        boolean passed = controller.guardar(acta);
        assertTrue("Se ha agregado acta: " + acta.getId_acta_partido(), passed);
        assertEquals(true, passed);
        assertEquals("paquitos", acta.getEquipo_ganador());
    }
}
