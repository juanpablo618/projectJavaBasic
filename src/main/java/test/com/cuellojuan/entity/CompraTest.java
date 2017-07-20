package test.com.cuellojuan.entity;

import com.cuellojuan.dao.ComprainfoDAO;
import com.cuellojuan.dao.ProductosDAO;
import com.cuellojuan.dao.ProveedoresDAO;
import com.cuellojuan.entity.Comprainfo;
import com.cuellojuan.entity.Productos;
import com.cuellojuan.entity.Proveedores;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CompraTest {


//solo inserta 2 registros en comprainfo
    @Test
    public void testInsertCompraConDosProductosMismoProveedor() throws SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        ProveedoresDAO proveedoresDAO = (ProveedoresDAO) context.getBean("proveedoresDAO");
        ProductosDAO productosDAO = (ProductosDAO) context.getBean("productosDAO");
        ComprainfoDAO comprainfoDAO = (ComprainfoDAO) context.getBean("comprainfoDAO");


        Proveedores proveedor = new Proveedores();
        proveedor.setId(41);
        proveedor.setCodigoproveedor("codigoProveedor31");
        proveedor.setNombrecompleto("juan proveedor");
        proveedor.setUbicacion("ubicacion");
        proveedor.setTelefono("2134124");


        List<Productos> listaDeProductosEnCompra = new ArrayList<Productos>();

        Productos producto1 = new Productos();
        producto1.setId(999);
        producto1.setProductocodigo("producto codigo 1");
        producto1.setProductonombre("producto nombre 1");
        producto1.setPreciocosto(45.2);
        producto1.setPrecioventa(55);
        producto1.setMarca("marca marca 1");

        listaDeProductosEnCompra.add(producto1);

        Productos producto2 = new Productos();

        producto2.setId(1000);
        producto2.setProductocodigo("producto codigo");
        producto2.setProductonombre("producto nombre");
        producto2.setPreciocosto(43.2);
        producto2.setPrecioventa(50);
        producto2.setMarca("marca marca");

    listaDeProductosEnCompra.add(producto2);


        Comprainfo comprainfo = new Comprainfo();


    for(Productos producto : listaDeProductosEnCompra){

        comprainfo.setCodigoproveedor(proveedor.getCodigoproveedor());
        comprainfo.setCodigoproducto(producto.getProductocodigo());
        comprainfo.setFecha("Fri Jan 16 23:12:40 NPT 2016");
        comprainfo.setCantidad(2);
        comprainfo.setCostototal(producto.getPrecioventa());

        comprainfoDAO.insert(comprainfo);

    }


    }



}
