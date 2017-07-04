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

            if (retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).getName().equals("id")) {
                id = retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).getName().toString() + "=" + retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).get(entity).toString();
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
        StringBuffer set = new StringBuffer("set");

            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {

                Field campoParaSetear = clase.getDeclaredField(rs.getMetaData().getColumnName(i).toLowerCase());

                int tipodatoRS = rs.getMetaData().getColumnType(i);

                switch (tipodatoRS) {
                    case 4:
                        devuelveMetodo(clase, set, rs, i,campoParaSetear).invoke(ob, rs.getInt(campoParaSetear.getName().toLowerCase()));

                        break;
                    case -1:

                        devuelveMetodo(clase, set, rs, i,campoParaSetear).invoke(ob, rs.getLong(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 12:

                        devuelveMetodo(clase, set, rs, i,campoParaSetear).invoke(ob, rs.getString(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 16:

                        devuelveMetodo(clase, set, rs, i,campoParaSetear).invoke(ob, rs.getBoolean(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 91:
                        devuelveMetodo(clase, set, rs, i,campoParaSetear).invoke(ob, rs.getDate(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 8:

                        devuelveMetodo(clase, set, rs, i,campoParaSetear).invoke(ob, rs.getDouble(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 6:

                        devuelveMetodo(clase, set, rs, i,campoParaSetear).invoke(ob, rs.getFloat(campoParaSetear.getName().toLowerCase()));
                        break;
                    default:
                        devuelveMetodo(clase, set, rs, i,campoParaSetear).invoke(ob, rs.getObject(campoParaSetear.getName().toLowerCase()));
                        break;
                }
            }
        return ob;
    }

    private Method devuelveMetodo(Class clase, StringBuffer set, ResultSet rs, int i, Field campoParaSetear) throws SQLException, NoSuchMethodException {

        return clase.getDeclaredMethod(set + rs.getMetaData().getColumnName(i).toLowerCase().substring(0, 1).toUpperCase()
                + rs.getMetaData().getColumnName(i).substring(1).toLowerCase(), campoParaSetear.getType());
    }


    public void insert(E entity) throws SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException {
        instanciarVariables(entity);
        String espacio = ", ";

        Connection conn;
        conn = dataSource.getConnection();

        for( int i=0;i<todasLasVariables.length;i++) {

                if (todasLasVariables[i].getType().getName().equals(String.class.getName())){
                    StringBuffer variableconComillas = new StringBuffer(retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).get(entity).toString());
                    variableconComillas.insert(0,'\'').insert(variableconComillas.length(),'\'');
                    listaDeValoresDeVariables.add(variableconComillas);
                    totalDeVariables.append(todasLasVariables[i].getName()).append(espacio);
                }else {
                    listaDeValoresDeVariables.add(retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).get(entity));
                    totalDeVariables.append(todasLasVariables[i].getName()).append(espacio);
                }
        }

        String totalDeVariablesFinal;
        totalDeVariablesFinal = totalDeVariables.substring(0, totalDeVariables.length() - 2);

        StringBuilder preSql = new StringBuilder();
        preSql.append("Insert into #TABLA ( #TOTALDEVARIABLES ) VALUES ( #VALORES )");

        String sqlFinal = preSql.toString();
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

            StringBuffer variableconComillas = new StringBuffer(retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).get(entity).toString());
            variableconComillas.insert(0,'\'').insert(variableconComillas.length(),'\'');

            if (todasLasVariables[i].getType().getName().equals(String.class.getName())){
                columnaValor.put(todasLasVariables[i].getName(), variableconComillas);

            }else{
                columnaValor.put(todasLasVariables[i].getName(), retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).get(entity));

            }
        }

        StringBuilder preSql = new StringBuilder();
        preSql.append("UPDATE #TABLA SET #COLUMNAVALOR WHERE #ID");

        String sqlFinal = preSql.toString();
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

        StringBuilder preSql = new StringBuilder();
        preSql.append("DELETE FROM #TABLA WHERE #ID");

        String sql = preSql.toString();
        sql = sql.replace("#TABLA",tabla);
        sql = sql.replace("#ID",devuelveID(entity));
        ejecutaSentencia(conn, sql);
    }


    public E find(E entity) throws SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        instanciarVariables(entity);
        rellenarListaDeNombresDeVariables();

        Connection conn;
        conn = dataSource.getConnection();

        StringBuilder preSql = new StringBuilder();
        preSql.append("SELECT * FROM #TABLA WHERE #ID");

        String sql = preSql.toString();
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










