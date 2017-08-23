package com.cuellojuan.dao.impl;

import com.cuellojuan.dao.GenericDAO;
import com.cuellojuan.dao.UsuariosDAO;
import com.cuellojuan.entity.*;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static com.cuellojuan.App.context;

@Repository
public class GenericDAOImpl<E>
        implements GenericDAO<E> {

    private DataSource dataSource;
    private String id;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private String tabla;

    private Field[] todasLasVariables;

    private List listaDeValoresDeVariables;

    private List<String> nombreDeLasVariables;

    private StringBuilder totalDeVariables;

    private static final String SET = "set";

    private static final String IGUAL = "=";

    private static final String ID = "id";

    private static final String ESPACIO = ", ";


    private Class retornaInstanciaDeLaClase(E entity) throws NoSuchMethodException, IllegalAccessException {
        return entity.getClass();
    }

    private void instanciarVariables(E entity) throws NoSuchMethodException, IllegalAccessException{
        tabla = retornaInstanciaDeLaClase(entity).getSimpleName().toLowerCase();
        todasLasVariables = retornaInstanciaDeLaClase(entity).getDeclaredFields();

        listaDeValoresDeVariables = new ArrayList();
        nombreDeLasVariables = new ArrayList<>();
        totalDeVariables = new StringBuilder();

    }

    private void rellenarListaDeNombresDeVariables() {
        int i = 0;
        do {
            todasLasVariables[i].setAccessible(true);

            nombreDeLasVariables.add(todasLasVariables[i].getName());
            i = i + 1;
        } while (i < todasLasVariables.length);
    }

    private String devuelveID (E entity) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {

        for (int i = 0 ; i <= todasLasVariables.length ; i++){
            todasLasVariables[i].setAccessible(true);
            if (retornaInstanciaDeLaClase(entity).getDeclaredField(todasLasVariables[i].getName()).getName().equals(ID)) {
                todasLasVariables[i].setAccessible(true);

                Field f = retornaInstanciaDeLaClase(entity).getDeclaredField(todasLasVariables[i].getName());
                f.setAccessible(true);


                id = f.getName().toString().concat(IGUAL).concat( f.get(entity).toString());
                break;
            }
        }

        return id;
    }

    private void ejecutaSentencia(Connection conn, String sqlFinal) throws SQLException {

        PreparedStatement ps = conn.prepareCall(sqlFinal);

        try {
            ps.execute();
            System.out.println("successful operation: " + sqlFinal);
        } catch (Exception e) {
            System.out.println("failed operation: " + sqlFinal);
        }
    }

/*

    private Object invocarSetters(Object ob, ResultSet rs) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {

        Class clase = ob.getClass();
        rs.next();

          for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
              Field campoParaSetear = clase.getDeclaredField(rs.getMetaData().getColumnName(i).toLowerCase());

                int tipodatoRS = rs.getMetaData().getColumnType(i);

                switch (tipodatoRS) {
                    case 4:
                        devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getInt(campoParaSetear.getName().toLowerCase()));
                        break;
                    case -1:
                        devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getLong(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 12:
                        devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getString(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 16:
                        devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getBoolean(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 91:
                        devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getDate(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 8:
                        devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getDouble(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 6:
                        devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getFloat(campoParaSetear.getName().toLowerCase()));
                        break;
                    default:
                        devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getObject(campoParaSetear.getName().toLowerCase()));
                        break;
                }
            }
        return ob;
    }


*/




    private Object invocarSetters(Object ob, ResultSet rs) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException, InstantiationException, ClassNotFoundException {

        Class clase = ob.getClass();
        rs.next();

        for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {


            Field campoParaSetear = clase.getDeclaredField(rs.getMetaData().getColumnName(i).toLowerCase());

            Usuario usuario = null;
            Cliente cliente = null;
            ProvReserva proveedorReserva = null;

            switch (clase.getDeclaredField(rs.getMetaData().getColumnName(i).toLowerCase()).getType().getSimpleName()){

                case "Usuario":
                    /*
                    Connection conn;
                    conn = dataSource.getConnection();
                    ResultSet rs2;


                    String nombrePaqueteYCLase = "com.cuellojuan.entity.";
                    nombrePaqueteYCLase = nombrePaqueteYCLase.concat("Usuario");

                    Object usuarioInstancia = Class.forName(nombrePaqueteYCLase).newInstance();

                    String nombreParaTablita = usuarioInstancia.getClass().getSimpleName().toLowerCase();

                    Class usuarioClase = Class.forName(nombrePaqueteYCLase);


                                       Field[] usuarioFields = usuarioClase.getDeclaredFields();

                                        Method[] usuarioMetodos = usuarioClase.getDeclaredMethods();


                                        Method setIdMetodo = usuarioClase.getMethod("setId", int.class);
                                        Method setNombreMetodo = usuarioClase.getMethod("setNombre", String.class);
                                        Method setApellidoMetodo = usuarioClase.getMethod("setApellido", String.class);
                    */





                          //  usuario = new Usuario();


                    //  String nombreTablaUsuario = usuario.getClass().getSimpleName().toLowerCase();


                    //      String SqlSelectUsuario =  "SELECT id, nombre, apellido FROM #nombreTabla WHERE id= "+ rs.getInt(rs.getMetaData().getColumnName(i).toLowerCase()) ;

                    //      SqlSelectUsuario = SqlSelectUsuario.replace("#nombreTabla", nombreTablaUsuario);

                    //                    PreparedStatement st = conn.prepareCall(SqlSelectUsuario);
                    //                    rs2 = st.executeQuery();


                    //      while (rs2.next()) {


                    //          usuario.setId(rs2.getInt("id"));
                    //          usuario.setNombre(rs2.getString("nombre"));
                    //          usuario.setApellido(rs2.getString("apellido"));


                                /*
                                setIdMetodo.invoke(usuarioInstancia, rs2.getInt("id"));
                                setNombreMetodo.invoke(usuarioInstancia, rs2.getString("nombre"));
                                setApellidoMetodo.invoke(usuarioInstancia, rs2.getString("apellido") );
                                */
                     //       }

                    UsuariosDAO usuarioDAO = (UsuariosDAO)   context.getBean("usuariosDAO");

                    usuario = new Usuario();
                            usuario.setId(rs.getInt(rs.getMetaData().getColumnName(i).toLowerCase()));

                    usuario = usuarioDAO.find(usuario);


                    break;

                case "Cliente":

                    Connection conn3;
                    conn3 = dataSource.getConnection();
                    ResultSet rs3;
                    cliente = new Cliente();

                    String nombreTabla = cliente.getClass().getSimpleName().toLowerCase();

                   // int id = rs.getInt(rs.getMetaData().getColumnName(i).toLowerCase());

                    String SqlSelect =  "SELECT id, apellido, nombre, fecha_nacimiento, tel_fijo, tel_celular, email, comentario,  fecha_insercion FROM #nombreTabla WHERE id= "+ rs.getInt(rs.getMetaData().getColumnName(i).toLowerCase()) ;
                    SqlSelect = SqlSelect.replace("#nombreTabla",nombreTabla);



                    PreparedStatement st3 = conn3.prepareCall(SqlSelect);
                    //st3.setInt(1, rs.getInt(rs.getMetaData().getColumnName(i).toLowerCase()));
                    rs3 = st3.executeQuery();

                    while (rs3.next()) {
                        cliente.setId(rs3.getInt("id"));
                        cliente.setApellido(rs3.getString("apellido"));
                        cliente.setNombre(rs3.getString("nombre"));
                        cliente.setFecha_nacimiento(rs3.getString("fecha_nacimiento"));
                        cliente.setTel_fijo(rs3.getString("tel_fijo"));
                        cliente.setTel_celular(rs3.getString("tel_celular"));
                        cliente.setEmail(rs3.getString("email"));
                        cliente.setComentario(rs3.getString("comentario"));
                        cliente.setFecha_insercion(rs3.getString("fecha_insercion"));
                    }

                    ResultSet rs5;

                    PreparedStatement st5 = conn3.prepareCall("SELECT usuarioquerecibio FROM cliente WHERE id= ?");
                    st5.setInt(1, rs.getInt(rs.getMetaData().getColumnName(i).toLowerCase()));
                    rs5 = st5.executeQuery();
                    int idUsuarioBuscado = 0;

                    while (rs5.next()) {
                        idUsuarioBuscado =  rs5.getInt(1);
                    }


                        ResultSet rs4;

                    PreparedStatement st4 = conn3.prepareCall("SELECT id, nombre, apellido FROM usuario WHERE id= ?");
                    st4.setInt(1, idUsuarioBuscado);
                    rs4 = st4.executeQuery();
                    while (rs4.next()) {
                        usuario = new Usuario();
                        usuario.setId(rs.getInt("id"));
                        usuario.setNombre(rs4.getString("nombre"));
                        usuario.setApellido(rs4.getString("apellido"));

                    }
                    cliente.setUsuarioquerecibio(usuario);


                    break;

                case "ProvReserva":
                    Connection conn4;
                    conn4 = dataSource.getConnection();

                    ResultSet rs8;

                    proveedorReserva = new ProvReserva();

                    String nombreTablaproveedor = proveedorReserva.getClass().getSimpleName().toLowerCase();


                    String SqlSelectProv =  "SELECT id, nombre, descripcion FROM #nombreTabla WHERE id="+ rs.getInt(rs.getMetaData().getColumnName(i).toLowerCase()) ;
                    SqlSelectProv = SqlSelectProv.replace("#nombreTabla",nombreTablaproveedor);


                    PreparedStatement st8 = conn4.prepareCall(SqlSelectProv);
                    //st8.setInt(1, rs.getInt(rs.getMetaData().getColumnName(i).toLowerCase()));

                    rs8 = st8.executeQuery();
                    while (rs8.next()) {
                        proveedorReserva.setId(rs8.getInt("id"));
                        proveedorReserva.setNombre(rs8.getString("nombre"));
                        proveedorReserva.setDescripcion(rs8.getString("descripcion"));

                    }

                    break;

                default:
                    break;
            }



            int tipodatoRS = rs.getMetaData().getColumnType(i);

            switch (tipodatoRS) {
                case 4:

                    switch (clase.getDeclaredField(rs.getMetaData().getColumnName(i).toLowerCase()).getType().getSimpleName()){

                        case "Usuario":
                            devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, usuario);

                        break;

                        case "Cliente":
                            devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, cliente);
                            break;

                        case "ProvReserva":
                            devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, proveedorReserva);
                            break;

                        default:
                            devuelveMetodo(clase, rs, i, campoParaSetear).invoke(ob, rs.getInt(campoParaSetear.getName().toLowerCase()));
                            break;
                    }

                    break;
                case -1:
                    devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getLong(campoParaSetear.getName().toLowerCase()));
                    break;
                case 12:
                    devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getString(campoParaSetear.getName().toLowerCase()));
                    break;
                case 16:
                    devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getBoolean(campoParaSetear.getName().toLowerCase()));
                    break;
                case 91:
                    devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getDate(campoParaSetear.getName().toLowerCase()));
                    break;
                case 8:
                    devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getDouble(campoParaSetear.getName().toLowerCase()));
                    break;
                case 6:
                    devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getFloat(campoParaSetear.getName().toLowerCase()));
                    break;
                default:
                    devuelveMetodo(clase, rs, i,campoParaSetear).invoke(ob, rs.getObject(campoParaSetear.getName().toLowerCase()));
                    break;
            }
        }
        return ob;
    }





     private Method devuelveMetodo(Class clase, ResultSet rs, int i, Field campoParaSetear) throws SQLException, NoSuchMethodException {

        String nombreColumna = new String(rs.getMetaData().getColumnName(i));
        String nombreColumnaMiniscula = new String(nombreColumna.toLowerCase());

        String primeraLetraMayuscula = String.valueOf(Character.toUpperCase(nombreColumnaMiniscula.charAt(0)));

        String nombreMetodo = primeraLetraMayuscula.concat(nombreColumnaMiniscula.substring(1,nombreColumnaMiniscula.length()));

        return clase.getDeclaredMethod(SET.concat(nombreMetodo), campoParaSetear.getType());
    }




    public void insert(E entity) throws SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException {
        instanciarVariables(entity);

        Connection conn;
        conn = dataSource.getConnection();

        for( int i=0;i<todasLasVariables.length;i++) {

            //retornaInstanciaDeLaClase(entity).getDeclaredField(todasLasVariables[i].getName()).setAccessible(true);
            Field f = retornaInstanciaDeLaClase(entity).getDeclaredField(todasLasVariables[i].getName());
            f.setAccessible(true);

            Object variable = f.get(entity);


            if (todasLasVariables[i].getType().getName().equals(String.class.getName())){
                todasLasVariables[i].setAccessible(true);

                StringBuffer variableconComillas = new StringBuffer(variable.toString());
                    variableconComillas.insert(0,'\'').insert(variableconComillas.length(),'\'');
                    variable = variableconComillas;
            }
            else {
                if (todasLasVariables[i].getType().getName().equals(Date.class.getName())){
                    todasLasVariables[i].setAccessible(true);

                    StringBuffer variableconComillas = new StringBuffer(variable.toString());
                    variableconComillas.insert(0,'\'').insert(variableconComillas.length(),'\'');
                    variable = variableconComillas;
                }
            }
            todasLasVariables[i].setAccessible(true);

            switch (variable.getClass().getSimpleName()){

                case "Usuario":
                    variable = ((Usuario) variable).getId();
                    break;
                case "Cliente":
                    variable = ((Cliente) variable).getId();
                    break;
                case "ProvReserva":
                    variable = ((ProvReserva) variable).getId();
                    break;
                case "ElementoInventario":
                    variable = ((ElementoInventario) variable).getId();
                    break;
                case "Apartamento":
                    variable = ((Apartamento) variable).getId();
                    break;
                case "Tarea":
                    variable = ((Tarea) variable).getId();
                    break;
                case "Estado":
                    variable = ((Estado) variable).getId();
                    break;

                default:
                    break;

            }


            listaDeValoresDeVariables.add(variable);

            totalDeVariables.append(todasLasVariables[i].getName()).append(ESPACIO);

        }

        String totalDeVariablesFinal;
        totalDeVariablesFinal = totalDeVariables.substring(0, totalDeVariables.length() - 2);

        String sqlFinal = "Insert into #TABLA ( #TOTALDEVARIABLES ) VALUES ( #VALORES );";
        sqlFinal = sqlFinal.replace("#TABLA",tabla);
        sqlFinal = sqlFinal.replace("#TOTALDEVARIABLES",totalDeVariablesFinal.toString());
        sqlFinal = sqlFinal.replace("#VALORES",listaDeValoresDeVariables.toString());
        sqlFinal = sqlFinal.replaceAll("[>\\[\\]-]", "");
        ejecutaSentencia(conn, sqlFinal);

    }


    public void update(E entity) throws SQLException,  NoSuchFieldException, NoSuchMethodException, IllegalAccessException {
        instanciarVariables(entity);

        Connection conn;
        conn = dataSource.getConnection();

        HashMap columnaValor = new HashMap();

        for(int i = 0; i< todasLasVariables.length;i++){


//            Object variable = retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).get(entity);

            Field f = retornaInstanciaDeLaClase(entity).getDeclaredField(todasLasVariables[i].getName());
            f.setAccessible(true);

            Object variable = f.get(entity);



            if (todasLasVariables[i].getType().getName().equals(String.class.getName())){
            StringBuffer variableconComillas = new StringBuffer(variable.toString());
            variableconComillas.insert(0,'\'').insert(variableconComillas.length(),'\'');
            variable = variableconComillas;

            }

            todasLasVariables[i].setAccessible(true);

            switch (variable.getClass().getSimpleName()){

                case "Usuario":
                    variable = ((Usuario) variable).getId();
                    break;
                case "Cliente":
                    variable = ((Cliente) variable).getId();
                    break;
                case "ProvReserva":
                    variable = ((ProvReserva) variable).getId();
                    break;
                case "ElementoInventario":
                    variable = ((ElementoInventario) variable).getId();
                    break;
                case "Apartamento":
                    variable = ((Apartamento) variable).getId();
                    break;
                case "Tarea":
                    variable = ((Tarea) variable).getId();
                    break;
                case "Estado":
                    variable = ((Estado) variable).getId();
                    break;

                default:
                    break;

            }


                columnaValor.put(todasLasVariables[i].getName(), variable);
        }






        String sqlFinal = "UPDATE #TABLA SET #COLUMNAVALOR WHERE #ID";
        sqlFinal = sqlFinal.replace("#TABLA",tabla);
        sqlFinal = sqlFinal.replace("#ID",devuelveID(entity));
        sqlFinal = sqlFinal.replace("#COLUMNAVALOR",columnaValor.toString());
        sqlFinal = sqlFinal.replaceAll("[>\\[\\]\\{\\}-]","");

        ejecutaSentencia(conn, sqlFinal);
    }


    @Override
    public void remove(E entity) throws SQLException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException {

        instanciarVariables(entity);
        rellenarListaDeNombresDeVariables();

        Connection conn;
        conn = dataSource.getConnection();

        String sql = "DELETE FROM #TABLA WHERE #ID";
        sql = sql.replace("#TABLA",tabla);
        sql = sql.replace("#ID",devuelveID(entity));
        ejecutaSentencia(conn, sql);
    }


    public E find(E entity) throws SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {

        instanciarVariables(entity);
        rellenarListaDeNombresDeVariables();

        Connection conn;
        conn = dataSource.getConnection();

        String sql = "SELECT * FROM #TABLA WHERE #ID";
        sql = sql.replace("#TABLA",tabla);
        sql = sql.replace("#ID", devuelveID(entity));

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        Object objetoClase = retornaInstanciaDeLaClase(entity).newInstance();
        invocarSetters(objetoClase,rs);

        rs.close();
        ps.close();

        return (E) objetoClase;
        }
    }










