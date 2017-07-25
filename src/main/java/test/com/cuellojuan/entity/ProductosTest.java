package test.com.cuellojuan.entity;


import com.cuellojuan.dao.ProductosDAO;
import com.cuellojuan.entity.Productos;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ProductosTest {


    @Test
    public void testProductosInsertAndFindById() throws InvocationTargetException, SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        ProductosDAO productosDAO = (ProductosDAO) context.getBean("productosDAO");

        Productos producto = new Productos();
        producto.setId(999);
        producto.setProductocodigo("producto codigo");
        producto.setProductonombre("producto nombre");
        producto.setPreciocosto(43.2);
        producto.setPrecioventa(50);
        producto.setMarca("marca marca");
        producto.setStockactual(40);
        productosDAO.insert(producto);

        assertEquals(999,(int)productosDAO.find(producto).getId());

        productosDAO.remove(producto);

    }



}
