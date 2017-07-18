package test.com.cuellojuan.entity;


import com.cuellojuan.dao.UsuariosDAO;
import com.cuellojuan.entity.Usuarios;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class UsuariosTest {
    @Test
    public void testGettersFromUsuarios(){
        com.cuellojuan.entity.Usuarios usuario = new Usuarios();

        usuario.setId(2);
        usuario.setCategoria("ADMINISTRADOR");
        usuario.setContrasena("PASSWORD");
        usuario.setNombrecompleto("juan nombre completo");
        usuario.setTelefono("452234");
        usuario.setUbicacion("cordoba");
        usuario.setUsuarionombre("nombreDeUsuario");



        assertEquals("ADMINISTRADOR",usuario.getCategoria());
        assertEquals("PASSWORD",usuario.getContrasena());
        assertEquals("juan nombre completo",usuario.getNombrecompleto());
        assertEquals("452234",usuario.getTelefono());
        assertEquals("cordoba",usuario.getUbicacion());
        assertEquals("nombreDeUsuario",usuario.getUsuarionombre());
    }


    @Test
    public void testUsuarioInsertAndFindById() throws InvocationTargetException, SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        UsuariosDAO usuariosDAO = (UsuariosDAO) context.getBean("usuariosDAO");

        int id= 999;
        Usuarios usuario = new Usuarios();
        usuario.setId(id);
        usuario.setCategoria("ADMINISTRADOR");
        usuario.setContrasena("holahola");
        usuario.setNombrecompleto("juan insertado");
        usuario.setTelefono("452234");
        usuario.setUbicacion("cordoba");
        usuario.setUsuarionombre("junacito");

        usuariosDAO.insert(usuario);

        assertEquals("ADMINISTRADOR",usuariosDAO.find(usuario).getCategoria());

        usuariosDAO.remove(usuario);
    }


    @Test
    public void testCustomerInsertAndUpdate() throws InvocationTargetException, SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");


        UsuariosDAO usuariosDAO = (UsuariosDAO) context.getBean("usuariosDAO");

        int id= 999;
        Usuarios usuario = new Usuarios();
        usuario.setId(id);
        usuario.setCategoria("ADMINISTRADOR");
        usuario.setContrasena("holahola");
        usuario.setNombrecompleto("juan insertado");
        usuario.setTelefono("452234");
        usuario.setUbicacion("cordoba");
        usuario.setUsuarionombre("junacito");

        usuariosDAO.insert(usuario);

        String nuevaCategoria="Nueva Categoria";
        usuario.setCategoria(nuevaCategoria);

        usuariosDAO.update(usuario);

        assertEquals(nuevaCategoria,usuariosDAO.find(usuario).getCategoria().toString());

        usuariosDAO.remove(usuario);
    }





}
