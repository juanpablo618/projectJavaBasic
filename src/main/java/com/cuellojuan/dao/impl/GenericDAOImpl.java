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

            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {

                Field campoParaSetear = clase.getDeclaredField(rs.getMetaData().getColumnName(i).toLowerCase());

                String tipoDatoRS = rs.getMetaData().getColumnTypeName(i);

                switch (tipoDatoRS) {
                    case "INT":
                        clase.getDeclaredMethod("set" + rs.getMetaData().getColumnName(i).toLowerCase().substring(0, 1).toUpperCase()
                                + rs.getMetaData().getColumnName(i).substring(1).toLowerCase(), campoParaSetear.getType()).invoke(ob, rs.getInt(campoParaSetear.getName().toLowerCase()));
                        break;
                    case "LONG":

                        clase.getDeclaredMethod("set" + rs.getMetaData().getColumnName(i).toLowerCase().substring(0, 1).toUpperCase()
                                + rs.getMetaData().getColumnName(i).substring(1).toLowerCase(), campoParaSetear.getType()).invoke(ob, rs.getLong(campoParaSetear.getName().toLowerCase()));
                        break;
                    case "VARCHAR":

                        clase.getDeclaredMethod("set" + rs.getMetaData().getColumnName(i).toLowerCase().substring(0, 1).toUpperCase()
                                + rs.getMetaData().getColumnName(i).substring(1).toLowerCase(), campoParaSetear.getType()).invoke(ob, rs.getString(campoParaSetear.getName().toLowerCase()));
                        break;
                    case "BOOLEAN":

                        clase.getDeclaredMethod("set" + rs.getMetaData().getColumnName(i).toLowerCase().substring(0, 1).toUpperCase()
                                + rs.getMetaData().getColumnName(i).substring(1).toLowerCase(), campoParaSetear.getType()).invoke(ob, rs.getBoolean(campoParaSetear.getName().toLowerCase()));
                        break;
                    case "DATE":
                        clase.getDeclaredMethod("set" + rs.getMetaData().getColumnName(i).toLowerCase().substring(0, 1).toUpperCase()
                                + rs.getMetaData().getColumnName(i).substring(1).toLowerCase(), campoParaSetear.getType()).invoke(ob, rs.getDate(campoParaSetear.getName().toLowerCase()));
                        break;
                    case "DOUBLE":

                        clase.getDeclaredMethod("set" + rs.getMetaData().getColumnName(i).toLowerCase().substring(0, 1).toUpperCase()
                                + rs.getMetaData().getColumnName(i).substring(1).toLowerCase(), campoParaSetear.getType()).invoke(ob, rs.getDouble(campoParaSetear.getName().toLowerCase()));
                        break;
                    case "FLOAT":

                        clase.getDeclaredMethod("set" + rs.getMetaData().getColumnName(i).toLowerCase().substring(0, 1).toUpperCase()
                                + rs.getMetaData().getColumnName(i).substring(1).toLowerCase(), campoParaSetear.getType()).invoke(ob, rs.getFloat(campoParaSetear.getName().toLowerCase()));
                        break;
                    default:
                        clase.getDeclaredMethod("set" + rs.getMetaData().getColumnName(i).toLowerCase().substring(0, 1).toUpperCase()
                        + rs.getMetaData().getColumnName(i).substring(1).toLowerCase(), campoParaSetear.getType()).invoke(ob, rs.getObject(campoParaSetear.getName().toLowerCase()));
                        break;
                }
            }
        return ob;
    }


    public void insert(E entity) throws SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException {

        instanciarVariables(entity);
        String espacio = ", ";

        Connection conn;
        conn = dataSource.getConnection();


        for( int i=0;i<todasLasVariables.length;i++) {

                if (todasLasVariables[i].getType().getName().equals("java.lang.String")){
                    listaDeValoresDeVariables.add( "'" + retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).get(entity) + "'");
                    totalDeVariables.append(todasLasVariables[i].getName()).append(espacio);
                }else {
                    listaDeValoresDeVariables.add(retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).get(entity));
                    totalDeVariables.append(todasLasVariables[i].getName()).append(espacio);
                }
        }

        String totalDeVariablesFinal;
        totalDeVariablesFinal = totalDeVariables.substring(0, totalDeVariables.length() - 2);

        StringBuilder preSql = new StringBuilder();
        preSql.append("Insert into #TABLA (");
        preSql.append(totalDeVariablesFinal.toString());
        preSql.append(") VALUES (");
        preSql.append(listaDeValoresDeVariables.toString());
        preSql.append(")");
        String sqlFinal = preSql.toString();
        sqlFinal = sqlFinal.replace("#TABLA",tabla);
        sqlFinal = sqlFinal.replaceAll("[>\\[\\]-]", "");
       ejecutaSentencia(conn, sqlFinal);


    }


    public void update(E entity) throws SQLException,  NoSuchFieldException, NoSuchMethodException, IllegalAccessException {

        instanciarVariables(entity);

        Connection conn;
        conn = dataSource.getConnection();

        HashMap columnaValor = new HashMap();

        for(int i = 0; i< todasLasVariables.length;i++){

            if (todasLasVariables[i].getType().getName().equals("java.lang.String")){
                columnaValor.put(todasLasVariables[i].getName(), "'"+retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).get(entity)+"'");

            }else{
                columnaValor.put(todasLasVariables[i].getName(), retornaInstanciaDeLaClase(entity).getField(todasLasVariables[i].getName()).get(entity));

            }

        }

        StringBuilder preSql = new StringBuilder();
        preSql.append("UPDATE #TABLA SET ");
        preSql.append(columnaValor.toString());
        preSql.append(" WHERE ");
        preSql.append(devuelveID(entity));

        String sqlFinal = preSql.toString();
        sqlFinal = sqlFinal.replace("#TABLA",tabla);
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
        preSql.append("DELETE FROM #TABLA WHERE ");
        StringBuilder append = preSql.append(devuelveID(entity));

        String sql = preSql.toString();
        sql = sql.replace("#TABLA",tabla);
        ejecutaSentencia(conn, sql);
    }


    public E find(E entity) throws SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        instanciarVariables(entity);

        rellenarListaDeNombresDeVariables();

        Connection conn;
        conn = dataSource.getConnection();

        StringBuilder preSql = new StringBuilder();
        preSql.append("SELECT * FROM #TABLA WHERE ");

        StringBuilder append = preSql.append(devuelveID(entity));

        String sql = preSql.toString();

        sql = sql.replace("#TABLA",tabla);

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        Object objetoClase = retornaInstanciaDeLaClase(entity).newInstance();

        invocarSetters(objetoClase,rs);

        rs.close();
        ps.close();
        return (E) objetoClase;

        }

    }










