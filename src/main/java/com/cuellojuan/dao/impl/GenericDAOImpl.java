package com.cuellojuan.dao.impl;

import com.cuellojuan.dao.GenericDAO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import java.util.Date;


@Repository
public class GenericDAOImpl<E>
        implements GenericDAO<E> {

    private static DataSource dataSource;
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

    private static final String SETID = "setId";

    private static final String WHERE = " WHERE ";

    private static final String SELECTFROM = "select * from ";

    private static final String GET = "get";

    private static final String GETID = "getId";

    private static final String IGUAL = "=";

    private static final String ID = "id";

    private static final String ID_ = "id_";

    private static final String ID_conIgual =  "id=";

    private static final String ESPACIO = ", ";

    private static final String NOMBRE_PAQUETE_DE_CLASES = "com.cuellojuan.entity";

    private static final String POR = "_por_";

    private static final String NULL = "null";

    private static final String ESPACIO_IGUAL_ESPACIO = " = ";

    private static final String SUCCESSFUL_OPERATION = "successful operation: ";

    private static final String FAILED_OPERATION = "failed operation: ";

    private static final String DELETE_FROM = "delete from ";



    private int totalVariablesFinal;

    private Class retornaInstanciaDeLaClase(Object entity) throws NoSuchMethodException, IllegalAccessException {
        return entity.getClass();
    }

    private void instanciarVariables(Object entity) throws NoSuchMethodException, IllegalAccessException {

        tabla = retornaInstanciaDeLaClase(entity).getSimpleName().toLowerCase();

        todasLasVariables = retornaInstanciaDeLaClase(entity).getDeclaredFields();

        //Esto lo realizo por que si la variable es una lista no la cuento como variable de la clase.
        totalVariablesFinal = 0;
        for (Field i : todasLasVariables) {
            if (i.getType() != List.class) totalVariablesFinal = totalVariablesFinal + 1;
        }

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


    private void ejecutaSentencia(Connection conn, String sqlFinal) throws SQLException {
        PreparedStatement ps = conn.prepareCall(sqlFinal);
        try {
            ps.execute();
            System.out.println(SUCCESSFUL_OPERATION.concat(sqlFinal));
        } catch (Exception e) {
            System.out.println(FAILED_OPERATION.concat(sqlFinal));
        }
    }


    private Method devuelveMetodo(Class clase, ResultSet rs, int i, Field campoParaSetear) throws SQLException, NoSuchMethodException {

        String nombreColumna = new String(rs.getMetaData().getColumnName(i));
        String nombreColumnaMiniscula = new String(nombreColumna.toLowerCase());

        String primeraLetraMayuscula = String.valueOf(Character.toUpperCase(nombreColumnaMiniscula.charAt(0)));

        String nombreMetodo = primeraLetraMayuscula.concat(nombreColumnaMiniscula.substring(1, nombreColumnaMiniscula.length()));

        return clase.getDeclaredMethod(SET.concat(nombreMetodo), campoParaSetear.getType());
    }


    public Object colocarComillasSimples(Object variable) {
        StringBuffer variableconComillas = new StringBuffer(variable.toString());
        variableconComillas.insert(0, '\'').insert(variableconComillas.length(), '\'');
        variable = variableconComillas;
        return variable;
    }

    public Object invocarGetID(Object variable) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String nombrePaqueteYCLase = NOMBRE_PAQUETE_DE_CLASES;

        Class claseGenerica;
        Method metodoGenerico;

        nombrePaqueteYCLase = nombrePaqueteYCLase.concat(".");
        nombrePaqueteYCLase = nombrePaqueteYCLase.concat(variable.getClass().getSimpleName());
        claseGenerica = Class.forName(nombrePaqueteYCLase);
        metodoGenerico = claseGenerica.getMethod(GETID, null);

        variable = metodoGenerico.invoke(variable, null);

        return variable;
    }

    public void insertarEnTablaAuxiliar(Object primerObj, Object segundoObj) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException, SQLException {
        Connection conn;
        conn = dataSource.getConnection();

        Field variablePrimerObj = retornaInstanciaDeLaClase(primerObj).getDeclaredField(ID);
        Field variableSegundoObj = retornaInstanciaDeLaClase(segundoObj).getDeclaredField(ID);

        variablePrimerObj.setAccessible(true);
        variableSegundoObj.setAccessible(true);

        //Por que podría variar el tipo de dato
        Object valorVarPrimerObj = variablePrimerObj.get(primerObj);
        Object valorVarSegundoObj = variableSegundoObj.get(segundoObj);

        String nombreTabla = primerObj.getClass().getSimpleName().toLowerCase();

        String ids = ID_.concat(primerObj.getClass().getSimpleName().toLowerCase()).concat(" , ").concat(ID_).concat(segundoObj.getClass().getSimpleName().toLowerCase());

        nombreTabla = nombreTabla.concat(POR);
        nombreTabla = nombreTabla.concat(segundoObj.getClass().getSimpleName().toLowerCase());

        List valores = new ArrayList();

        valores.add(valorVarPrimerObj);
        valores.add(valorVarSegundoObj);

        String sqlFinal = "Insert into #TABLA ( #TOTALDEVARIABLES ) VALUES ( #VALORES );";

        sqlFinal = sqlFinal.replace("#TABLA", nombreTabla);
        sqlFinal = sqlFinal.replace("#TOTALDEVARIABLES", ids);
        sqlFinal = sqlFinal.replace("#VALORES", valores.toString());
        sqlFinal = sqlFinal.replaceAll("[>\\[\\]-]", "");
        ejecutaSentencia(conn, sqlFinal);

    }


    public void actualizarEnTablaAuxiliar(Object primerObj, Object segundoObj) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {
        Connection conn;
        conn = dataSource.getConnection();

        Field variablePrimerObj = retornaInstanciaDeLaClase(primerObj).getDeclaredField(ID);
        Field variableSegundoObj = retornaInstanciaDeLaClase(segundoObj).getDeclaredField(ID);

        variablePrimerObj.setAccessible(true);
        variableSegundoObj.setAccessible(true);

        //Por que podría variar el tipo de dato
        Object valorVarPrimerObj = variablePrimerObj.get(primerObj);
        Object valorVarSegundoObj = variableSegundoObj.get(segundoObj);

        String nombreTabla = primerObj.getClass().getSimpleName().toLowerCase();


        String idPrimerObjeto = ID_conIgual.concat(invocarGetID(primerObj).toString());

        idPrimerObjeto = ID_.concat(primerObj.getClass().getSimpleName().toLowerCase()).concat(ESPACIO_IGUAL_ESPACIO).concat(idPrimerObjeto);

        String idSegundoObjeto = ID_conIgual.concat(invocarGetID(segundoObj).toString());

        idSegundoObjeto = ID_.concat(segundoObj.getClass().getSimpleName().toLowerCase()).concat(ESPACIO_IGUAL_ESPACIO).concat(idSegundoObjeto);

        nombreTabla = nombreTabla.concat(POR).concat(segundoObj.getClass().getSimpleName().toLowerCase());

        List valores = new ArrayList();

        valores.add(valorVarPrimerObj);
        valores.add(valorVarSegundoObj);

        HashMap columnaValor = new HashMap();

        columnaValor.put(ID_.concat(primerObj.getClass().getSimpleName().toLowerCase()), valorVarPrimerObj);
        columnaValor.put(ID_.concat(segundoObj.getClass().getSimpleName().toLowerCase()), valorVarSegundoObj);

        String sqlFinal = "UPDATE #TABLA SET #COLUMNAVALOR WHERE #IDPRIMEROBJ AND #IDSEGUNDOOBJ;";

        sqlFinal = sqlFinal.replace("#TABLA", nombreTabla);
        sqlFinal = sqlFinal.replace("#COLUMNAVALOR", columnaValor.toString());
        sqlFinal = sqlFinal.replace("#IDPRIMEROBJ", idPrimerObjeto);
        sqlFinal = sqlFinal.replace("#IDSEGUNDOOBJ", idSegundoObjeto);

        sqlFinal = sqlFinal.replaceAll("[>\\[\\]\\{\\}-]", "");

        ejecutaSentencia(conn, sqlFinal);

    }


    public void insert(Object entity) throws SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
        instanciarVariables(entity);

        //por que la Clase puede tener más de una variable de que sea una Lista de Objetos.
        List variablesAInsertarDespues = new ArrayList();

        Connection conn;
        conn = dataSource.getConnection();
        try {

            for (int i = 0; i < todasLasVariables.length; i++) {

                Field f = retornaInstanciaDeLaClase(entity).getDeclaredField(todasLasVariables[i].getName());
                f.setAccessible(true);

                //valor de la variable
                Object variable = f.get(entity);

                //cuando la variable no tiene ref a otro objeto, ejemplo en clase cliente sin reserva. En la bd escribiría "null"
                if (variable == null ) variable = NULL;

                if (todasLasVariables[i].getType() == String.class || todasLasVariables[i].getType() == Date.class) variable = colocarComillasSimples(variable);

                if (variable.getClass().getPackage().getName().equals(NOMBRE_PAQUETE_DE_CLASES)) variable = invocarGetID(variable);

                if (variable.getClass() == ArrayList.class) {
                    variablesAInsertarDespues.add(variable);
                } else {
                    listaDeValoresDeVariables.add(variable);
                }

                if (f.getType() != List.class) totalDeVariables.append(todasLasVariables[i].getName()).append(ESPACIO);

            }

            String totalDeVariablesFinal;
            totalDeVariablesFinal = totalDeVariables.substring(0, totalDeVariables.length() - 2);

            String sqlFinal = "Insert into #TABLA ( #TOTALDEVARIABLES ) VALUES ( #VALORES );";
            sqlFinal = sqlFinal.replace("#TABLA", tabla);
            sqlFinal = sqlFinal.replace("#TOTALDEVARIABLES", totalDeVariablesFinal.toString());
            sqlFinal = sqlFinal.replace("#VALORES", listaDeValoresDeVariables.toString());
            sqlFinal = sqlFinal.replaceAll("[>\\[\\]\\{\\}-]", "");
            ejecutaSentencia(conn, sqlFinal);

        } finally {

            for (int g = 0; g < variablesAInsertarDespues.size(); g++) {

                Object variable = variablesAInsertarDespues.get(g);
                int tamaño = ((ArrayList) variable).size();

                for (int i = 0; i < tamaño; i++) {
                    insertarEnTablaAuxiliar(((ArrayList) variable).get(i), entity);
                }
            }

        }
    }


    public void update(E entity) throws SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, InstantiationException {

        instanciarVariables(entity);
        Connection conn;
        conn = dataSource.getConnection();
        //por que la Clase puede tener más de una variable de que sea una Lista de Objetos.
        List variablesParaActualizarDespues = new ArrayList();

        try {
                HashMap columnaValor = new HashMap();

                for (int i = 0; i < todasLasVariables.length; i++) {

                    Field f = retornaInstanciaDeLaClase(entity).getDeclaredField(todasLasVariables[i].getName());
                    f.setAccessible(true);

                    Object variable = f.get(entity);

                    if (todasLasVariables[i].getType() == String.class || todasLasVariables[i].getType() == Date.class) variable = colocarComillasSimples(variable);

                    if (variable == null) variable = NULL;

                    if (variable.getClass().getPackage().getName().equals(NOMBRE_PAQUETE_DE_CLASES)) variable = invocarGetID(variable);

                    if (variable.getClass() != ArrayList.class && variable !=null) columnaValor.put(todasLasVariables[i].getName(), variable);

                    if (variable.getClass() == ArrayList.class) variablesParaActualizarDespues.add(variable);

                }

                String sqlFinal = "UPDATE #TABLA SET #COLUMNAVALOR WHERE #ID";
                sqlFinal = sqlFinal.replace("#TABLA", tabla);
                sqlFinal = sqlFinal.replace("#ID", ID_conIgual.concat(invocarGetID(entity).toString())  );
                sqlFinal = sqlFinal.replace("#COLUMNAVALOR", columnaValor.toString());
                sqlFinal = sqlFinal.replaceAll("[>\\[\\]\\{\\}-]", "");

                ejecutaSentencia(conn, sqlFinal);

        }
        finally {

                for (int g = 0; g < variablesParaActualizarDespues.size(); g++) {

                    Object variable = variablesParaActualizarDespues.get(g);
                    int tamaño = ((ArrayList) variable).size();

                    for (int i = 0; i < tamaño; i++) {
                        actualizarEnTablaAuxiliar(((ArrayList) variable).get(i), entity);
                    }
                }
            }

    }


    @Override
    public void remove(E entity) throws SQLException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, InstantiationException {

        Connection conn;
        conn = dataSource.getConnection();

        String idDelEntity = ID_conIgual.concat(invocarGetID(entity).toString());

        idDelEntity = idDelEntity.replace(ID_conIgual,"");

        try{
        instanciarVariables(entity);
        rellenarListaDeNombresDeVariables();

        String sql = "DELETE FROM #TABLA WHERE #ID";
        sql = sql.replace("#TABLA", tabla);
        sql = sql.replace("#ID", ID_conIgual.concat(invocarGetID(entity).toString()));


            ejecutaSentencia(conn, sql);
        }finally {

            for (int i = 0; i < entity.getClass().getDeclaredFields().length; i++) {

                if (entity.getClass().getDeclaredFields()[i].getType() == List.class) {

                    Field ListaDelEntity = entity.getClass().getDeclaredFields()[i];
                    ListaDelEntity.setAccessible(true);

                    String nombreDeLista = ListaDelEntity.getName();

                    String primeraLetraMayuscula = String.valueOf(Character.toUpperCase(nombreDeLista.charAt(0)));

                    String nombreMetodo = primeraLetraMayuscula.concat(nombreDeLista.substring(1, nombreDeLista.length()));

                    nombreMetodo = GET.concat(nombreMetodo);

                    Method metodoDeGetListaParaRemove = entity.getClass().getDeclaredMethod(nombreMetodo);

                    List listaInvokada = (List) metodoDeGetListaParaRemove.invoke(entity,null);

                    if(listaInvokada.size()!=0 && listaInvokada != null) {

                        String idPrimeraTabla = ID_.concat(entity.getClass().getSimpleName());

                        String sqlFinal = DELETE_FROM.concat(listaInvokada.get(0).getClass().getSimpleName().toLowerCase().concat(POR).concat(entity.getClass().getSimpleName().toLowerCase()).concat(WHERE).concat(idPrimeraTabla.toString()).concat(IGUAL).concat(idDelEntity));

                         ejecutaSentencia(conn, sqlFinal);

                    }
                }

            }
        }

    }


    public E find(Object entity) throws SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {

        instanciarVariables(entity);
        rellenarListaDeNombresDeVariables();

        Connection conn;
        conn = dataSource.getConnection();

        String sql = "SELECT * FROM #TABLA WHERE #ID";
        sql = sql.replace("#TABLA", tabla);

        sql = sql.replace("#ID", ID_conIgual.concat(invocarGetID(entity).toString()));

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

         Object objetoClase = retornaInstanciaDeLaClase(entity).newInstance();

        String idDelEntity = ID_conIgual.concat(invocarGetID(entity).toString());

        idDelEntity = idDelEntity.replace(ID_conIgual,"");

        try {

            Class clase = objetoClase.getClass();
            rs.next();

            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {

                Field campoParaSetear = clase.getDeclaredField(rs.getMetaData().getColumnName(i).toLowerCase());

                Object objetoParaInstanciar;
                String nombrePaqueteYCLase = NOMBRE_PAQUETE_DE_CLASES;

                int tipodatoRS = rs.getMetaData().getColumnType(i);

                switch (tipodatoRS) {
                    case 4:

                        if (clase.getDeclaredField(rs.getMetaData().getColumnName(i).toLowerCase()).getType() == int.class) {
                            devuelveMetodo(clase, rs, i, campoParaSetear).invoke(objetoClase, rs.getInt(campoParaSetear.getName().toLowerCase()));
                        } else {
                            nombrePaqueteYCLase = nombrePaqueteYCLase.concat(".").concat(campoParaSetear.getType().getSimpleName());

                            objetoParaInstanciar = Class.forName(nombrePaqueteYCLase).newInstance();

                            Class claseDelObjParaInstanciar = Class.forName(nombrePaqueteYCLase);

                            Method setMetodoDelObjeto = claseDelObjParaInstanciar.getMethod(SETID, int.class);
                            setMetodoDelObjeto.invoke(objetoParaInstanciar, rs.getInt(rs.getMetaData().getColumnName(i).toLowerCase()));

                            // todo esto es por que si el obj. es null significa, que va a devolver un obj. instanciado pero sin datos.
                            Method getMetodoDelObjeto = claseDelObjParaInstanciar.getMethod(GETID, null);
                            int idDelObjeto = (int) getMetodoDelObjeto.invoke(objetoParaInstanciar, null);
                            if (idDelObjeto == 0)
                            {
                                devuelveMetodo(clase, rs, i, campoParaSetear).invoke(objetoClase, objetoParaInstanciar);
                            }else{
                            objetoParaInstanciar = find(objetoParaInstanciar);
                            devuelveMetodo(clase, rs, i, campoParaSetear).invoke(objetoClase, objetoParaInstanciar);
                            }
                        }
                        break;

                    case -1:
                        devuelveMetodo(clase, rs, i, campoParaSetear).invoke(objetoClase, rs.getLong(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 12:
                        devuelveMetodo(clase, rs, i, campoParaSetear).invoke(objetoClase, rs.getString(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 16:
                        devuelveMetodo(clase, rs, i, campoParaSetear).invoke(objetoClase, rs.getBoolean(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 91:
                        devuelveMetodo(clase, rs, i, campoParaSetear).invoke(objetoClase, rs.getDate(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 8:
                        devuelveMetodo(clase, rs, i, campoParaSetear).invoke(objetoClase, rs.getDouble(campoParaSetear.getName().toLowerCase()));
                        break;
                    case 6:
                        devuelveMetodo(clase, rs, i, campoParaSetear).invoke(objetoClase, rs.getFloat(campoParaSetear.getName().toLowerCase()));
                        break;
                    default:
                        break;
                }

            }

            rs.close();
            ps.close();
        } finally {

            for (int i = 0; i < objetoClase.getClass().getDeclaredFields().length; i++) {

                if (objetoClase.getClass().getDeclaredFields()[i].getType() == List.class) {

                    Field ListaDelEntity = objetoClase.getClass().getDeclaredFields()[i];
                    ListaDelEntity.setAccessible(true);

                    String nombreDeLaLista = ListaDelEntity.getName();

                    String primeraLetraMayuscula = String.valueOf(Character.toUpperCase(nombreDeLaLista.charAt(0)));

                    String nombreMetodo = primeraLetraMayuscula.concat(nombreDeLaLista.substring(1, nombreDeLaLista.length()));

                    String nombreMetodoSet = SET.concat(nombreMetodo);
                    nombreMetodo = GET.concat(nombreMetodo);


                    Method metodoDeGetLista = objetoClase.getClass().getDeclaredMethod(nombreMetodo);

                    List listaInvokada = (List) metodoDeGetLista.invoke(entity,null);


                    if(listaInvokada.size()!=0 && listaInvokada != null) {

                       String idPrimeraTabla = ID_.concat(objetoClase.getClass().getSimpleName());
                       String idSegundaTabla = ID_.concat(listaInvokada.get(0).getClass().getSimpleName().toLowerCase());

                       System.out.println(SELECTFROM+ listaInvokada.get(0).getClass().getSimpleName().toLowerCase().concat(POR).concat(entity.getClass().getSimpleName().toLowerCase()) +  WHERE  + idPrimeraTabla.toString() + "="+ idDelEntity);

                        PreparedStatement st = conn.prepareCall(SELECTFROM+ listaInvokada.get(0).getClass().getSimpleName().toLowerCase().concat(POR).concat(entity.getClass().getSimpleName().toLowerCase()) +  WHERE  + idPrimeraTabla.toString() + "="+ idDelEntity);
                        rs = st.executeQuery();
                        Object objetoAInsertarEnLista ;

                        List listaParaInsertarEnNuevaInstancia = new ArrayList();

                                for (int g=1; g<=listaInvokada.size();g++){
                                       rs.next();

                                             String nombrePaqueteYCLase = NOMBRE_PAQUETE_DE_CLASES;
                                             nombrePaqueteYCLase = nombrePaqueteYCLase.concat(".").concat(listaInvokada.get(0).getClass().getSimpleName());

                                             objetoAInsertarEnLista = Class.forName(nombrePaqueteYCLase).newInstance();

                                         Method metodoSetDeObjetoAInsertar = Class.forName(nombrePaqueteYCLase).getDeclaredMethod(SETID, int.class);

                                         metodoSetDeObjetoAInsertar.invoke(objetoAInsertarEnLista,rs.getInt(idSegundaTabla));

                                       objetoAInsertarEnLista = find(objetoAInsertarEnLista);
                                       listaParaInsertarEnNuevaInstancia.add(objetoAInsertarEnLista);
                                }

                        Method setListaCompletaANuevaInstancia = objetoClase.getClass().getDeclaredMethod(nombreMetodoSet, List.class);

                        setListaCompletaANuevaInstancia.invoke(objetoClase,listaParaInsertarEnNuevaInstancia);
                        rs.close();
                    }
                }

            }

            return (E) objetoClase;
        }
    }
}







