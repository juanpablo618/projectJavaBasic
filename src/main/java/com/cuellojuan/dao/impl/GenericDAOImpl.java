package com.cuellojuan.dao.impl;

import com.cuellojuan.dao.GenericDAO;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

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
        tabla = retornaInstanciaDeLaClase(entity).getSimpleName();
        todasLasVariables = retornaInstanciaDeLaClase(entity).getFields();

        listaDeValoresDeVariables = new ArrayList();
        nombreDeLasVariables = new ArrayList<>();
        totalDeVariables = new StringBuilder();

    }

    private void rellenarListaDeNombresDeVariables() {
        int i = 0;
        do {
            nombreDeLasVariables.add(todasLasVariables[i].getName());
            i = i + 1;
        } while (i < todasLasVariables.length);
    }

    private String devuelveID (E entity) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {

        for (int i = 0 ; i <= todasLasVariables.length ; i++){

            if (retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).getName().equals(ID)) {
                id = retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).getName().toString().concat(IGUAL).concat( retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).get(entity).toString());
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

    private Method devuelveMetodo(Class clase, ResultSet rs, int i, Field campoParaSetear) throws SQLException, NoSuchMethodException {

        String nombreColumna = new String(rs.getMetaData().getColumnName(i));
        String nombreColumnaMiniscula = new String(nombreColumna.toLowerCase());

        String primeraLetraMayuscula = String.valueOf(Character.toUpperCase(nombreColumnaMiniscula.charAt(0)));

        String nombreMetodo = primeraLetraMayuscula.concat(nombreColumnaMiniscula.substring(1,nombreColumnaMiniscula.length()));

        return clase.getDeclaredMethod(SET.concat(nombreMetodo), campoParaSetear.getType());
    }


    public void insert(E entity) throws SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException {
        instanciarVariables(entity);
        //String espacio = ", ";

        Connection conn;
        conn = dataSource.getConnection();

        for( int i=0;i<todasLasVariables.length;i++) {
            Object variable = retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).get(entity);

            if (todasLasVariables[i].getType().getName().equals(String.class.getName())){
                StringBuffer variableconComillas = new StringBuffer(variable.toString());
                    variableconComillas.insert(0,'\'').insert(variableconComillas.length(),'\'');
                    variable = variableconComillas;
            }
                    listaDeValoresDeVariables.add(variable);
                    totalDeVariables.append(todasLasVariables[i].getName()).append(ESPACIO);
        }

        String totalDeVariablesFinal;
        totalDeVariablesFinal = totalDeVariables.substring(0, totalDeVariables.length() - 2);

        String sqlFinal = "Insert into #TABLA ( #TOTALDEVARIABLES ) VALUES ( #VALORES )";
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
            Object variable = retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).get(entity);

            if (todasLasVariables[i].getType().getName().equals(String.class.getName())){
            StringBuffer variableconComillas = new StringBuffer(variable.toString());
            variableconComillas.insert(0,'\'').insert(variableconComillas.length(),'\'');
            variable = variableconComillas;

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


    public E find(E entity) throws SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

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










