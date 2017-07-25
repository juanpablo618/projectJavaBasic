package test.com.cuellojuan;

import com.cuellojuan.dao.CustomerDAO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.com.cuellojuan.entity.*;


@RunWith(Suite.class)
@Suite.SuiteClasses({ UsuariosTest.class, CompraTest.class, DataBaseTestMySql.class, HerramientasTest.class, ProductosTest.class, ProveedoresTest.class})
public class MySuite {

}
