package test.com.cuellojuan.entity;

import com.cuellojuan.dao.HerramientasDAO;
import com.cuellojuan.entity.Herramientas;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class HerramientasTest {



    @Test
    public void testHerramientasInsertAndFindById() throws InvocationTargetException, SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        HerramientasDAO herramientasDAO = (HerramientasDAO) context.getBean("herramientasDAO");


        com.cuellojuan.entity.Herramientas herramienta = new Herramientas();

        herramienta.setId(999);
        herramienta.setNombre("martillo");
        herramienta.setUbicacion("hostería la porteña");
        herramienta.setAloja("casa cba");


        herramienta.setFechacompra(Calendar.getInstance().getTime().toString());


        herramientasDAO.insert(herramienta);

        assertEquals(999,herramientasDAO.find(herramienta).getId());

        herramientasDAO.remove(herramienta);
    }

}
