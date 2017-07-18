package test.com.cuellojuan.entity;


import com.cuellojuan.dao.ProveedoresDAO;
import org.junit.Test;
import com.cuellojuan.entity.Proveedores;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ProveedoresTest {

    @Test
    public void testGettersFromProveedores(){
        com.cuellojuan.entity.Proveedores proveedor = new Proveedores();

        proveedor.setId(1);
        proveedor.setTelefono("212121");
        proveedor.setNombrecompleto("juanjuanjuan");

        assertEquals(1,(int)proveedor.getId());
        assertEquals("212121",proveedor.getTelefono());
        assertEquals("juanjuanjuan",proveedor.getNombrecompleto());
    }


    @Test
    public void testProveedorInsertAndFindById() throws InvocationTargetException, SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        ProveedoresDAO proveedoresDAO = (ProveedoresDAO) context.getBean("proveedoresDAO");

        Proveedores proveedor = new Proveedores();
        proveedor.setId(999);
        proveedor.setNombrecompleto("juan test unitario");
        proveedor.setTelefono("2121");
        proveedor.setCodigoproveedor("codigo prov");
        proveedor.setUbicacion("ubicacion 1201");




        proveedoresDAO.insert(proveedor);

        assertEquals(999,(int)proveedoresDAO.find(proveedor).getId());

        proveedoresDAO.remove(proveedor);
    }





    @Test
    public void testProveedorInsertAndUpdate() throws InvocationTargetException, SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        ProveedoresDAO proveedoresDAO = (ProveedoresDAO) context.getBean("proveedoresDAO");

        Proveedores proveedor = new Proveedores();
        proveedor.setId(999);
        proveedor.setCodigoproveedor("codigo proveedor");
        proveedor.setNombrecompleto("juan proveedor");
        proveedor.setUbicacion("ubicacion");
        proveedor.setTelefono("2134124");

        proveedoresDAO.insert(proveedor);

        assertEquals("codigo proveedor",proveedoresDAO.find(proveedor).getCodigoproveedor());


        String nombreCompleto="robertoCarlosTest";

        proveedor.setNombrecompleto(nombreCompleto);

        proveedoresDAO.update(proveedor);

        assertEquals(nombreCompleto,proveedoresDAO.find(proveedor).getNombrecompleto());
        assertEquals("2134124",proveedoresDAO.find(proveedor).getTelefono());
        assertEquals("ubicacion",proveedoresDAO.find(proveedor).getUbicacion() );

        proveedoresDAO.remove(proveedor);

    }


}
