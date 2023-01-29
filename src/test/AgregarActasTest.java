package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import controlador.Controlador_actas_partido;
import modelo.Actas_partido;
<<<<<<< HEAD

public class AgregarActasTest {

    private Controlador_actas_partido controller = new Controlador_actas_partido();
    private Actas_partido acta = new Actas_partido();

=======
/**
 * Test Agregar actas - Caso de Prueba
 */
public class AgregarActasTest {
    
    private Controlador_actas_partido controller = new Controlador_actas_partido();

    /*
     * CP002
     */
>>>>>>> 4755b255dea05b45b450871f4e30e29174a577e2
    @Test
    public void guardarActa(){
        
        Actas_partido acta = new Actas_partido(
<<<<<<< HEAD
            522,
=======
            20,
>>>>>>> 4755b255dea05b45b450871f4e30e29174a577e2
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
<<<<<<< HEAD
        /* acta.setEquipo_ganador("Los juanes");
        acta.setDuracion_partido(50);
        acta.setGoles_equipo_local(2);
        acta.setGoles_equipo_rival(1);
        acta.setHora_inicio_partido("14:00");
        acta.setHora_fin_partido("16:00");
        acta.setId_acta_partido(520);
        acta.setId_arbitro(501); */
=======
>>>>>>> 4755b255dea05b45b450871f4e30e29174a577e2

        boolean passed = controller.guardar(acta);
        assertTrue("Se ha agregado acta: " + acta.getId_acta_partido(), passed);
        assertEquals(true, passed);
        assertEquals("paquitos", acta.getEquipo_ganador());
    }
<<<<<<< HEAD
=======

>>>>>>> 4755b255dea05b45b450871f4e30e29174a577e2
}
