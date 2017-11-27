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

    private static DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private String tabla;

    private Field[] todasLasVariables;

    private List listaDeValoresDeVariables;

    private List<String> nombreDeLasVariables;

    private StringBuilder totalDeVariables;

    private static final String WHERE = " WHERE ";

    private static final String WHERE_CLAUSE = "#WHERE_CLAUSE";

    private static final String INSERT_INTO = "INSERT INTO";

    private static final String TABLA = "#TABLA";

    private static final String SELECT_FROM = "select * from ";

    private static final String SELECT_FROM_TABLA_WHERE = "SELECT * FROM #TABLA WHERE ";

    private static final String DELETE_FROM = "DELETE FROM ";

    private static final String DELETE_FROM_TABLA_WHERE = "DELETE FROM #TABLA WHERE ";

    private static final String INSERT_INTO_TABLA_TOTALDEVALORES_VALUES_VALORES = "INSERT INTO #TABLA ( #TOTALDEVARIABLES ) VALUES ( #VALORES );";

    private static final String NUMERAL_ID = "#ID";

    private static final String TOTAL_DE_VALORES = "#TOTALDEVARIABLES";

    private static final String VALORES = "#VALORES";

    private static final String COMILLA_SIMPLE = "'";

    private static final String SET = "set";

    private static final String GET = "get";

    private static final String GET_ID = "getId";

    private static final String SET_ID = "setId";

    private static final String IGUAL = "=";

    private static final String ID = "id";

    private static final String ID_ = "id_";

    private static final String ID_conIgual =  "id=";

    private static final String ESPACIO = ", ";

    private static final String NOMBRE_PAQUETE_DE_CLASES = "com.cuellojuan.entity";

    private static final String NOMBRE_PAQUETE_DE_CLASES_PARA_DAO = "com.cuellojuan.dao";

    private static final String POR = "_por_";

    private static final String NULL = "null";

    private static final String SUCCESSFUL_OPERATION = "successful operation: ";

    private static final String FAILED_OPERATION = "failed operation: ";

    private static final String FIND = "find";

    private static final String DAO = "DAO";

    private List listaDeVariablesTipoList;

    private int totalVariablesFinal;

    private Class retornaInstanciaDeLaClase(E entity) throws NoSuchMethodException, IllegalAccessException {
        return entity.getClass();
    }

    private void instanciarVariables(E entity) throws NoSuchMethodException, IllegalAccessException {

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


    private int ejecutarSentencias(Connection conn, String sqlFinal) throws SQLException {
        int idAutoincrementable = 0;
        PreparedStatement ps = conn.prepareCall(sqlFinal);

        if(sqlFinal.contains(INSERT_INTO)){
            try {
                ps.execute();
                System.out.println(SUCCESSFUL_OPERATION.concat(sqlFinal));
                ResultSet rs = ps.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    idAutoincrementable = rs.getInt(1);
                }

            } catch (Exception e) {
                System.out.println(FAILED_OPERATION.concat(sqlFinal));
            }

            return  idAutoincrementable;

        }else{
            try {
                ps.execute();
                System.out.println(SUCCESSFUL_OPERATION.concat(sqlFinal));
            } catch (Exception e) {
                System.out.println(FAILED_OPERATION.concat(sqlFinal));
            }
            return  idAutoincrementable;
        }
    }

    private Method devuelveMetodoParaInvocar(Class clase, ResultSet rs, int i, Field campoParaSetear) throws SQLException, NoSuchMethodException {

        String nombreColumna = new String(rs.getMetaData().getColumnName(i).toLowerCase());

        String primeraLetraMayuscula = String.valueOf(Character.toUpperCase(nombreColumna.charAt(0)));

        String nombreMetodo = primeraLetraMayuscula.concat(nombreColumna.substring(1, nombreColumna.length()));

        return clase.getDeclaredMethod(SET.concat(nombreMetodo), campoParaSetear.getType());
    }


    public Object colocarComillasSimples(Object variable) {
        StringBuffer variableconComillas = new StringBuffer(variable.toString());
        variableconComillas.insert(0, '\'').insert(variableconComillas.length(), '\'');
        variable = variableconComillas;
        return variable;
    }

    public E invocarGetID(E variable) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String nombrePaqueteYCLase = NOMBRE_PAQUETE_DE_CLASES;

        Class claseGenerica;
        Method metodoGenerico;

        nombrePaqueteYCLase = nombrePaqueteYCLase.concat(".");
        nombrePaqueteYCLase = nombrePaqueteYCLase.concat(variable.getClass().getSimpleName());
        claseGenerica = Class.forName(nombrePaqueteYCLase);
        metodoGenerico = claseGenerica.getMethod(GET_ID, null);

        variable = (E) metodoGenerico.invoke(variable, null);

        return variable;
    }

    private void actualizarEnTablaAuxiliar(ArrayList variableLista, E entity) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchFieldException {

        Connection conn;
        conn = dataSource.getConnection();

        String nombrePrimeraTabla = variableLista.get(0).getClass().getSimpleName().toLowerCase();
        String nombreDeSegundaTabla = entity.getClass().getSimpleName().toLowerCase();

        String valorIdDeEntity = invocarGetID(entity).toString();

        String idParaBuscar = ID_.concat(nombreDeSegundaTabla).concat(IGUAL).concat(valorIdDeEntity);

        String nombreTablaRelacional = nombrePrimeraTabla.concat(POR).concat(nombreDeSegundaTabla);

            String sql = DELETE_FROM_TABLA_WHERE.concat(NUMERAL_ID);
            sql = sql.replace(TABLA, nombreTablaRelacional);
            sql = sql.replace(NUMERAL_ID, idParaBuscar);

            ejecutarSentencias(conn, sql);

        String nombreDeColumnas = ID_.concat(variableLista.get(0).getClass().getSimpleName().toLowerCase()).concat(ESPACIO).concat(ID_).concat(nombreDeSegundaTabla);

                    for (Object i: variableLista) {

                        String valores = invocarGetID((E) i).toString().concat(ESPACIO).concat(valorIdDeEntity);

                        String sqlFinal = INSERT_INTO_TABLA_TOTALDEVALORES_VALUES_VALORES;
                        ejecutarSentencias(conn, reemplazarValores(sqlFinal,nombreTablaRelacional,nombreDeColumnas, valores));

                    }
        }

    private void insertarEnTablaDeRelacion(String tablaDelPrimerObj, String tablaDelSegundoObj, String idDelObjDentroDeLista, String idAutogenerado) throws SQLException {

        Connection conn;
        conn = dataSource.getConnection();

        String ids = ID_.concat(tablaDelPrimerObj.concat(" , ").concat(ID_).concat(tablaDelSegundoObj));

        String nombreTablaRelacional = tablaDelPrimerObj.concat(POR).concat(tablaDelSegundoObj);

        List valores = new ArrayList();

        valores.add(idDelObjDentroDeLista);
        valores.add(idAutogenerado);

        String sqlFinal = INSERT_INTO_TABLA_TOTALDEVALORES_VALUES_VALORES;

        ejecutarSentencias(conn, reemplazarValores(sqlFinal,nombreTablaRelacional,ids,valores.toString()));

    }

    private String reemplazarValores(String sqlFinal,String tabla, String totalDeVariablesFinal, String listaDeValoresDeVariables) {

        sqlFinal = sqlFinal.replace(TABLA, tabla);
        sqlFinal = sqlFinal.replace(TOTAL_DE_VALORES, totalDeVariablesFinal.toString());
        sqlFinal = sqlFinal.replace(VALORES, listaDeValoresDeVariables.toString());
        sqlFinal = sqlFinal.replaceAll("[>\\[\\]\\{\\}-]", "");
        return sqlFinal;
    }

    public void insert(E entity) throws SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {

        Connection conn;
        conn = dataSource.getConnection();

        instanciarVariables(entity);

        //por que la Clase puede tener más de una variable de que sea una Lista de Objetos.
        listaDeVariablesTipoList = new ArrayList();
        int idAutoGenerado;
        try {

            for (int i = 0; i < todasLasVariables.length; i++) {

                Field f = retornaInstanciaDeLaClase(entity).getDeclaredField(todasLasVariables[i].getName());
                f.setAccessible(true);

                String nombreDelCampo = todasLasVariables[i].getName();
                Object variable = f.get(entity);

                    if(variable != null){

                        switch (variable.getClass().getSimpleName()){

                            case "String":
                                variable = colocarComillasSimples(variable);
                                listaDeValoresDeVariables.add(variable);
                                totalDeVariables.append(nombreDelCampo).append(ESPACIO);
                                break;

                            case "Date":
                                variable = colocarComillasSimples(variable);
                                listaDeValoresDeVariables.add(variable);
                                totalDeVariables.append(nombreDelCampo).append(ESPACIO);
                                break;

                            case "Integer":
                                if(!(variable.equals(0) && f.getName().equals(ID)))
                                    listaDeValoresDeVariables.add(variable);
                                if(!(variable.equals(0) && f.getName().equals(ID)))
                                    totalDeVariables.append(nombreDelCampo).append(ESPACIO);
                                break;
                            case "Double":
                                listaDeValoresDeVariables.add(variable);
                                totalDeVariables.append(nombreDelCampo).append(ESPACIO);
                                break;

                            case "ArrayList":
                                listaDeVariablesTipoList.add(variable);
                                break;

                            default:
                                if (variable.getClass().getPackage().getName().equals(NOMBRE_PAQUETE_DE_CLASES)) variable = invocarGetID((E) variable);
                                listaDeValoresDeVariables.add(variable);
                                totalDeVariables.append(todasLasVariables[i].getName()).append(ESPACIO);
                        }
                    }else{
                        variable = NULL;
                        listaDeValoresDeVariables.add(variable);
                        totalDeVariables.append(nombreDelCampo).append(ESPACIO);
                    }

            }

            String totalDeVariablesFinal = totalDeVariables.substring(0, totalDeVariables.length() - 2);

            String sqlFinal = INSERT_INTO_TABLA_TOTALDEVALORES_VALUES_VALORES;

            idAutoGenerado = ejecutarSentencias(conn,reemplazarValores(sqlFinal,tabla, totalDeVariablesFinal, listaDeValoresDeVariables.toString()));

            String nombrePaqueteYCLase = NOMBRE_PAQUETE_DE_CLASES;
            nombrePaqueteYCLase = nombrePaqueteYCLase.concat(".").concat(entity.getClass().getSimpleName());
            Class claseDelObjParaInstanciar = Class.forName(nombrePaqueteYCLase);

            Method setMetodoDelObjeto = claseDelObjParaInstanciar.getMethod(SET_ID, int.class);
            setMetodoDelObjeto.invoke(entity, idAutoGenerado);

            for (int g = 0; g < listaDeVariablesTipoList.size(); g++) {

                Object variable = listaDeVariablesTipoList.get(g);
                int tamaño = ((ArrayList) variable).size();

                for (int i = 0; i < tamaño; i++) {
                    String tablaDelPrimerObj = ((ArrayList) variable).get(i).getClass().getSimpleName().toLowerCase();
                    String tablaDelSegundoObj = entity.getClass().getSimpleName().toLowerCase();
                    String idDelObjDentroDeLista;

                    E objDentroDeLaLista = (E) ((ArrayList) variable).get(i);
                    idDelObjDentroDeLista = invocarGetID(objDentroDeLaLista).toString();

                    insertarEnTablaDeRelacion(tablaDelPrimerObj,tablaDelSegundoObj, idDelObjDentroDeLista, String.valueOf(idAutoGenerado));
                }
            }

        }catch (Exception e) {
            System.out.println("failed method Insert");
        }finally {
           conn.close();
        }
    }

    public void update(E entity) throws SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, InstantiationException {
        instanciarVariables(entity);

        Connection conn;
        conn = dataSource.getConnection();

        listaDeVariablesTipoList = new ArrayList();

        try {
                HashMap columnaValor = new HashMap();

                for (int i = 0; i < todasLasVariables.length; i++) {

                    Field f = retornaInstanciaDeLaClase(entity).getDeclaredField(todasLasVariables[i].getName());
                    f.setAccessible(true);

                    Object variable = f.get(entity);

                    if(variable != null){
                        switch (variable.getClass().getSimpleName()){

                            case "String":
                                variable = colocarComillasSimples(variable);
                                columnaValor.put(todasLasVariables[i].getName(), variable);
                                break;

                            case "Date":
                                variable = colocarComillasSimples(variable);
                                columnaValor.put(todasLasVariables[i].getName(), variable);
                                break;

                            case "Integer":
                                columnaValor.put(todasLasVariables[i].getName(), variable);
                                break;

                            case "Double":
                                columnaValor.put(todasLasVariables[i].getName(), variable);
                                break;

                            default:
                                if (variable.getClass() == ArrayList.class) {
                                    listaDeVariablesTipoList.add(variable);
                                }else{
                                    variable = invocarGetID((E) variable);
                                    columnaValor.put(todasLasVariables[i].getName(), variable);
                                }
                                break;
                        }
                    }else{
                        variable = NULL;
                        columnaValor.put(todasLasVariables[i].getName(), variable);
                    }
                }

                String sqlFinal = "UPDATE #TABLA SET #COLUMNAVALOR WHERE #ID";

                sqlFinal = sqlFinal.replace(TABLA, tabla);
                sqlFinal = sqlFinal.replace(NUMERAL_ID, ID_conIgual.concat(invocarGetID(entity).toString())  );
                sqlFinal = sqlFinal.replace("#COLUMNAVALOR", columnaValor.toString());
                sqlFinal = sqlFinal.replaceAll("[>\\[\\]\\{\\}-]", "");

                ejecutarSentencias(conn, sqlFinal);

            for (int g = 0; g < listaDeVariablesTipoList.size(); g++) {

                ArrayList variableLista = (ArrayList) listaDeVariablesTipoList.get(g);

                   if(variableLista.size()>0) actualizarEnTablaAuxiliar(variableLista, entity);
            }
        }catch (Exception e) {
            System.out.println("failed method Update");
        }finally {
            conn.close();
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

        String sql = DELETE_FROM_TABLA_WHERE.concat(NUMERAL_ID);
        sql = sql.replace(TABLA, tabla);
        sql = sql.replace(NUMERAL_ID, ID_conIgual.concat(invocarGetID(entity).toString()));

            ejecutarSentencias(conn, sql);

            for (int i = 0; i < entity.getClass().getDeclaredFields().length; i++) {

                if (entity.getClass().getDeclaredFields()[i].getType() == List.class) {

                    Field ListaDelEntity = entity.getClass().getDeclaredFields()[i];
                    ListaDelEntity.setAccessible(true);

                    String nombreDeLista = ListaDelEntity.getName();

                    String primeraLetraMayuscula = String.valueOf(Character.toUpperCase(nombreDeLista.charAt(0)));

                    String nombreMetodo = primeraLetraMayuscula.concat(nombreDeLista.substring(1, nombreDeLista.length()));

                    nombreMetodo = GET.concat(nombreMetodo);

                    Method metodoDeGetListaParaRemover = entity.getClass().getDeclaredMethod(nombreMetodo);

                    List listaInvokada = (List) metodoDeGetListaParaRemover.invoke(entity,null);

                    if(listaInvokada.size()!=0 && listaInvokada != null) {

                        String idPrimeraTabla = ID_.concat(entity.getClass().getSimpleName());

                        String sqlFinal = DELETE_FROM.concat(listaInvokada.get(0).getClass().getSimpleName().toLowerCase().concat(POR).concat(entity.getClass().getSimpleName().toLowerCase()).concat(WHERE).concat(idPrimeraTabla.toString()).concat(IGUAL).concat(idDelEntity));

                        ejecutarSentencias(conn, sqlFinal);

                    }
                }
            }

        }catch (Exception e) {
            System.out.println("failed method Remove");
        }
        finally {
            conn.close();
        }

    }

    public E find(E entity) throws SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {

        instanciarVariables(entity);
        rellenarListaDeNombresDeVariables();

        Connection conn;
        conn = dataSource.getConnection();

        String sql = SELECT_FROM_TABLA_WHERE.concat(NUMERAL_ID);

        sql = sql.replace(TABLA, tabla);

        sql = sql.replace(NUMERAL_ID, ID_conIgual.concat(invocarGetID( entity).toString()));

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        Object objetoADevolver = retornaInstanciaDeLaClase(entity).newInstance();

        String idDelEntity = ID_conIgual.concat(invocarGetID(entity).toString());

        idDelEntity = idDelEntity.replace(ID_conIgual,"");

        try {

            Class claseDelObjADevolver = objetoADevolver.getClass();
            rs.next();

            invocarCamposDelObjetoADevolver(rs, claseDelObjADevolver, objetoADevolver);

            rs.close();
            ps.close();


            for (int i = 0; i < objetoADevolver.getClass().getDeclaredFields().length; i++) {

                if (objetoADevolver.getClass().getDeclaredFields()[i].getType() == List.class) {

                    Field ListaDelEntity = objetoADevolver.getClass().getDeclaredFields()[i];
                    ListaDelEntity.setAccessible(true);

                    String nombreDeLaLista = ListaDelEntity.getName();

                    String primeraLetraMayuscula = String.valueOf(Character.toUpperCase(nombreDeLaLista.charAt(0)));

                    String nombreMetodo = primeraLetraMayuscula.concat(nombreDeLaLista.substring(1, nombreDeLaLista.length()));

                    String nombreMetodoSet = SET.concat(nombreMetodo);
                    nombreMetodo = GET.concat(nombreMetodo);

                    Method metodoDeGetLista = objetoADevolver.getClass().getDeclaredMethod(nombreMetodo);

                    List listaInvocada = (List) metodoDeGetLista.invoke(entity,null);

                    if(listaInvocada.size()!=0 && listaInvocada != null) {
                        List listaParaInsertarEnNuevaInstancia = new ArrayList();


                        String idPrimeraTabla = ID_.concat(objetoADevolver.getClass().getSimpleName());
                        String idSegundaTabla = ID_.concat(listaInvocada.get(0).getClass().getSimpleName().toLowerCase());

                        System.out.println(SELECT_FROM.concat(listaInvocada.get(0).getClass().getSimpleName().toLowerCase().concat(POR).concat(entity.getClass().getSimpleName().toLowerCase()).concat(WHERE).concat(idPrimeraTabla.toString()).concat(IGUAL).concat(idDelEntity)));

                        PreparedStatement st = conn.prepareCall(SELECT_FROM.concat(listaInvocada.get(0).getClass().getSimpleName().toLowerCase().concat(POR).concat(entity.getClass().getSimpleName().toLowerCase()).concat(WHERE).concat(idPrimeraTabla.toString()).concat(IGUAL).concat(idDelEntity)));
                        rs = st.executeQuery();
                        Object objetoAInsertarEnLista ;

                        for (int g=1; g<=listaInvocada.size();g++){
                            rs.next();

                            String nombrePaqueteYCLase = NOMBRE_PAQUETE_DE_CLASES;
                            String nombrePaqueteYCLaseParaDAO = NOMBRE_PAQUETE_DE_CLASES_PARA_DAO;

                            nombrePaqueteYCLase = nombrePaqueteYCLase.concat(".").concat(listaInvocada.get(0).getClass().getSimpleName());
                            nombrePaqueteYCLaseParaDAO = nombrePaqueteYCLaseParaDAO.concat(".").concat(listaInvocada.get(0).getClass().getSimpleName());

                            objetoAInsertarEnLista = Class.forName(nombrePaqueteYCLase).newInstance();

                            Method metodoSetDeObjetoAInsertar = Class.forName(nombrePaqueteYCLase).getDeclaredMethod(SET_ID, int.class);
                            metodoSetDeObjetoAInsertar.invoke(objetoAInsertarEnLista,rs.getInt(idSegundaTabla));

                            Object daoDeObjAInsertarEnListas = Class.forName(nombrePaqueteYCLaseParaDAO.concat(DAO)).newInstance();
                            Method findDeObjetoAInsertar = Class.forName(nombrePaqueteYCLaseParaDAO.concat(DAO)).getDeclaredMethod(FIND, objetoAInsertarEnLista.getClass());

                            objetoAInsertarEnLista =   findDeObjetoAInsertar.invoke(daoDeObjAInsertarEnListas, objetoAInsertarEnLista);
                            listaParaInsertarEnNuevaInstancia.add(objetoAInsertarEnLista);
                        }

                        Method setListaCompletaANuevaInstancia = objetoADevolver.getClass().getDeclaredMethod(nombreMetodoSet, List.class);

                        setListaCompletaANuevaInstancia.invoke(objetoADevolver,listaParaInsertarEnNuevaInstancia);
                        rs.close();
                    }
                }

            }

        }catch (Exception e){
            System.out.println(e);
        }
        return (E) objetoADevolver;
    }

    private void invocarCamposDelObjetoADevolver(ResultSet rs, Class claseDelObjADevolver, Object objetoADevolver) throws SQLException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {

            Field campoAEstablecer = claseDelObjADevolver.getDeclaredField(rs.getMetaData().getColumnName(i).toLowerCase());

            Object objetoParaInstanciar;
            Object daoDeObjAInsertarEnListas;

            String nombrePaqueteYCLase = NOMBRE_PAQUETE_DE_CLASES;

            int tipodatoRS = rs.getMetaData().getColumnType(i);

            switch (tipodatoRS) {
                case 4:

                    if (claseDelObjADevolver.getDeclaredField(rs.getMetaData().getColumnName(i).toLowerCase()).getType() == int.class) {

                        devuelveMetodoParaInvocar(claseDelObjADevolver, rs, i, campoAEstablecer).invoke(objetoADevolver, rs.getInt(campoAEstablecer.getName().toLowerCase()));
                    }else {

                        nombrePaqueteYCLase = nombrePaqueteYCLase.concat(".").concat(campoAEstablecer.getType().getSimpleName());
                        Class claseDelObjParaInstanciar = Class.forName(nombrePaqueteYCLase);

                        objetoParaInstanciar = Class.forName(nombrePaqueteYCLase).newInstance();

                        Method setMetodoDelObjeto = claseDelObjParaInstanciar.getMethod(SET_ID, int.class);
                        setMetodoDelObjeto.invoke(objetoParaInstanciar, rs.getInt(rs.getMetaData().getColumnName(i).toLowerCase()));

                        Method getMetodoDelObjeto = claseDelObjParaInstanciar.getMethod(GET_ID, null);
                        int idDelObjetoParaInstanciar = (int) getMetodoDelObjeto.invoke(objetoParaInstanciar, null);

                        if (idDelObjetoParaInstanciar != 0) {
                            String nombrePaqueteYCLaseParaDAO = NOMBRE_PAQUETE_DE_CLASES_PARA_DAO;

                            nombrePaqueteYCLaseParaDAO = nombrePaqueteYCLaseParaDAO.concat(".").concat(objetoParaInstanciar.getClass().getSimpleName());

                            daoDeObjAInsertarEnListas = Class.forName(nombrePaqueteYCLaseParaDAO.concat(DAO)).newInstance();

                            Method metodoFindDelObjAInsertar = Class.forName(nombrePaqueteYCLaseParaDAO.concat(DAO)).getDeclaredMethod(FIND, objetoParaInstanciar.getClass());

                            objetoParaInstanciar = metodoFindDelObjAInsertar.invoke(daoDeObjAInsertarEnListas, objetoParaInstanciar);
                        }
                        devuelveMetodoParaInvocar(claseDelObjADevolver, rs, i, campoAEstablecer).invoke(objetoADevolver, objetoParaInstanciar);
                    }
                    break;
                case -1:
                    devuelveMetodoParaInvocar(claseDelObjADevolver, rs, i, campoAEstablecer).invoke(objetoADevolver, rs.getLong(campoAEstablecer.getName().toLowerCase()));
                    break;
                case 12:
                    devuelveMetodoParaInvocar(claseDelObjADevolver, rs, i, campoAEstablecer).invoke(objetoADevolver, rs.getString(campoAEstablecer.getName().toLowerCase()));
                    break;
                case 16:
                    devuelveMetodoParaInvocar(claseDelObjADevolver, rs, i, campoAEstablecer).invoke(objetoADevolver, rs.getBoolean(campoAEstablecer.getName().toLowerCase()));
                    break;
                case 91:
                    devuelveMetodoParaInvocar(claseDelObjADevolver, rs, i, campoAEstablecer).invoke(objetoADevolver, rs.getDate(campoAEstablecer.getName().toLowerCase()));
                    break;
                case 8:
                    devuelveMetodoParaInvocar(claseDelObjADevolver, rs, i, campoAEstablecer).invoke(objetoADevolver, rs.getDouble(campoAEstablecer.getName().toLowerCase()));
                    break;
                case 6:
                    devuelveMetodoParaInvocar(claseDelObjADevolver, rs, i, campoAEstablecer).invoke(objetoADevolver, rs.getFloat(campoAEstablecer.getName().toLowerCase()));
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public E findByProperty(String field, String value) throws SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {

        Connection conn;
        conn = dataSource.getConnection();

        String sql = SELECT_FROM_TABLA_WHERE.concat(WHERE_CLAUSE);
        String ClaseDeObjetoParaDevolver = this.getClass().getSimpleName().replace(DAO,"");

        sql = sql.replace(TABLA, ClaseDeObjetoParaDevolver.toLowerCase());

        sql = sql.replace(WHERE_CLAUSE, field.toString().concat(IGUAL).concat(COMILLA_SIMPLE).concat(value).concat(COMILLA_SIMPLE));

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        String nombrePaqueteYCLase = NOMBRE_PAQUETE_DE_CLASES;

        nombrePaqueteYCLase = nombrePaqueteYCLase.concat(".").concat(ClaseDeObjetoParaDevolver);

        Object objetoADevolver = Class.forName(nombrePaqueteYCLase).newInstance();

        try {
            rs.next();
            invocarCamposDelObjetoADevolver(rs, objetoADevolver.getClass(), objetoADevolver);
            rs.close();
            ps.close();

        }catch (Exception e){
            System.out.println(e);
        }
        return (E) objetoADevolver;
    }
}
