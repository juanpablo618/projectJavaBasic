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





    private Class retornaInstanciaDeLaClase(E entity) {
        Class instanceClass = entity.getClass();
        return instanceClass;
    }

    private void instanciarVariables(E entity) {
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

    private String devuelveID (E entity) throws NoSuchFieldException, IllegalAccessException {

        if ( todasLasVariables.length == 0){
            return null;
        }
        for (int i = 0 ; i <= todasLasVariables.length ; i++){

            if (retornaInstanciaDeLaClase(entity).getField(nombreDeLasVariables.get(i)).getName().equals("id")) {
                return id = retornaInstanciaDeLaClase(entity).getField(nombreDeLasVariables.get(i)).getName().toString() + "=" + retornaInstanciaDeLaClase(entity).getField(nombreDeLasVariables.get(i)).get(entity).toString();
            }else{
                return null;
            }
        }
        return null;
    }

    private void ejecutaSentencia(Connection conn, String sqlFinal) throws SQLException {

        PreparedStatement ps = conn.prepareCall(sqlFinal);

        if (ps.execute()) {
            System.out.println("failed operation: " + sqlFinal);
        } else {
            System.out.println("successful operation: " + sqlFinal);
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


    public void insert(E entity) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        instanciarVariables(entity);
        String espacio = ", ";

        Connection conn;
        conn = dataSource.getConnection();

        int i = 0;

        do {
            nombreDeLasVariables.add(todasLasVariables[i].getName());
            listaDeValoresDeVariables.add(retornaInstanciaDeLaClase(entity).getField(nombreDeLasVariables.get(i)).get(entity));

            //No funciona para otras DB's
            listaDeValoresDeVariables.set(i, " ' " + listaDeValoresDeVariables.get(i).toString() + " ' ");

            totalDeVariables.append(nombreDeLasVariables.get(i)).append(espacio);

            if (i == todasLasVariables.length - 1) {
                break;
            }

            i = i + 1;
        } while (i <= todasLasVariables.length);

        String totalDeVariablesFinal;
        totalDeVariablesFinal = totalDeVariables.substring(0, totalDeVariables.length() - 2);

        String sqlFinal = "Insert into " + tabla + " (" + totalDeVariablesFinal + ") VALUES (" + listaDeValoresDeVariables.toString() + ")";

        sqlFinal = sqlFinal.replace("[", " ");
        sqlFinal = sqlFinal.replace("]", " ");

        ejecutaSentencia(conn, sqlFinal);


    }


    public void update(E entity) throws SQLException, IllegalAccessException, NoSuchFieldException {

        instanciarVariables(entity);

        Connection conn;
        conn = dataSource.getConnection();

        int i = 0;
        HashMap columnaValor = new HashMap();

        do {
            nombreDeLasVariables.add(todasLasVariables[i].getName());
            listaDeValoresDeVariables.add(" ' "+retornaInstanciaDeLaClase(entity).getField(nombreDeLasVariables.get(i)).get(entity)+" ' ");

            columnaValor.put(nombreDeLasVariables.get(i), listaDeValoresDeVariables.get(i));

            if (i == todasLasVariables.length - 1) {
                break;
            }
            i = i + 1;

        } while (i < todasLasVariables.length);

        String sqlFinal = "UPDATE " + tabla + " SET " + columnaValor.toString() + " WHERE " + devuelveID(entity).toString();
        sqlFinal = sqlFinal.replace("[", " ");
        sqlFinal = sqlFinal.replace("]", " ");
        sqlFinal = sqlFinal.replace("{", " ");
        sqlFinal = sqlFinal.replace("}", " ");

        ejecutaSentencia(conn, sqlFinal);

    }


    @Override
    public void remove(E entity) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, SQLException {

        instanciarVariables(entity);
        rellenarListaDeNombresDeVariables();

        Connection conn;
        conn = dataSource.getConnection();

        String sql = "DELETE FROM " + tabla + " WHERE " + devuelveID(entity);

        ejecutaSentencia(conn, sql);


    }


    public E find(E entity) throws SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        instanciarVariables(entity);

        rellenarListaDeNombresDeVariables();

        Connection conn;
        conn = dataSource.getConnection();

        String sql = "SELECT * FROM " + tabla + " WHERE " + devuelveID(entity);

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        Class instanceClass = retornaInstanciaDeLaClase(entity);
        Object objetoClase = instanceClass.newInstance();

        invocarSetters(objetoClase,rs);

        rs.close();
        ps.close();
        return (E) objetoClase;

        }

    }










