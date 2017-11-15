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

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private String tabla;

    private Field[] todasLasVariables;

    private List listaDeValoresDeVariables;

    private List<String> nombreDeLasVariables;

    private StringBuilder totalDeVariables;


    private static final String WHERE = " WHERE ";

    private static final String SELECT_FROM = "select * from ";

    private static final String SELECT_MAX_ID_FROM = "SELECT MAX(id) AS id FROM ";

    private static final String DELETE_FROM = "DELETE FROM ";

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

    private static final String ESPACIO_IGUAL_ESPACIO = " = ";

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


    private void ejecutaSentencia(Connection conn, String sqlFinal) throws SQLException {
        PreparedStatement ps = conn.prepareCall(sqlFinal);
        try {
            ps.execute();
            System.out.println(SUCCESSFUL_OPERATION.concat(sqlFinal));
        } catch (Exception e) {
            System.out.println(FAILED_OPERATION.concat(sqlFinal));
        }
    }


    private Method devuelveMetodoParaInvocar(Class clase, ResultSet rs, int i, Field campoParaSetear) throws SQLException, NoSuchMethodException {

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

    public void insertarEnTablaAuxiliar(E primerObj, E segundoObj) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException, SQLException {
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

        nombreTabla = nombreTabla.concat(POR).concat(segundoObj.getClass().getSimpleName().toLowerCase());

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


    public void actualizarEnTablaAuxiliar(E primerObj, E segundoObj) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {
        Connection conn;
        conn = dataSource.getConnection();

        Field variablePrimerObj = retornaInstanciaDeLaClase(primerObj).getDeclaredField(ID);
        Field variableSegundoObj = retornaInstanciaDeLaClase(segundoObj).getDeclaredField(ID);

        variablePrimerObj.setAccessible(true);
        variableSegundoObj.setAccessible(true);

        //Por que podría variar el tipo de dato
        Object valorVarPrimerObj = variablePrimerObj.get(primerObj);
        Object valorVarSegundoObj = variableSegundoObj.get(segundoObj);

        String nombreTabla = primerObj.getClass().getSimpleName().toLowerCase().concat(POR).concat(segundoObj.getClass().getSimpleName().toLowerCase());
        String idPrimerObjeto = ID_.concat(primerObj.getClass().getSimpleName().toLowerCase()).concat(ESPACIO_IGUAL_ESPACIO).concat(invocarGetID(primerObj).toString());
        String idSegundoObjeto = ID_.concat(segundoObj.getClass().getSimpleName().toLowerCase()).concat(ESPACIO_IGUAL_ESPACIO).concat(invocarGetID(segundoObj).toString());

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


    public void insert(E entity) throws SQLException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {

        Connection conn;
        conn = dataSource.getConnection();

        E idDelObj = invocarGetID(entity);
        if(idDelObj.equals(0)){

            String tablaInsertadaAnterior = entity.getClass().getSimpleName().toLowerCase();

            PreparedStatement st2 = conn.prepareStatement(SELECT_MAX_ID_FROM.concat(tablaInsertadaAnterior));
            ResultSet rs;
            int ultimoIdInsertadoMasUno = 0;

            rs = st2.executeQuery();

            while(rs.next()){
                ultimoIdInsertadoMasUno = rs.getInt(1);
            }
            rs.close();
            ultimoIdInsertadoMasUno = ultimoIdInsertadoMasUno +1;
            String nombrePaqueteYCLase = NOMBRE_PAQUETE_DE_CLASES;
            nombrePaqueteYCLase = nombrePaqueteYCLase.concat(".").concat(entity.getClass().getSimpleName());
            Class claseDelObjParaInstanciar = Class.forName(nombrePaqueteYCLase);

            Method setMetodoDelObjeto = claseDelObjParaInstanciar.getMethod(SET_ID, int.class);
            setMetodoDelObjeto.invoke(entity, ultimoIdInsertadoMasUno);
        }

        instanciarVariables(entity);

        //por que la Clase puede tener más de una variable de que sea una Lista de Objetos.
        listaDeVariablesTipoList = new ArrayList();

        try {

            for (int i = 0; i < todasLasVariables.length; i++) {

                Field f = retornaInstanciaDeLaClase(entity).getDeclaredField(todasLasVariables[i].getName());
                f.setAccessible(true);

                Object variable = f.get(entity);

                //cuando la variable no tiene ref a otro objeto, ejemplo en clase cliente sin reserva. En la bd escribiría "null"
                if (variable == null ) variable = NULL;

                if (todasLasVariables[i].getType() == String.class || todasLasVariables[i].getType() == Date.class) variable = colocarComillasSimples(variable);

                if (variable.getClass().getPackage().getName().equals(NOMBRE_PAQUETE_DE_CLASES)) variable = invocarGetID((E) variable);

                if (variable.getClass() == ArrayList.class) {
                    listaDeVariablesTipoList.add(variable);
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

            for (int g = 0; g < listaDeVariablesTipoList.size(); g++) {

                Object variable = listaDeVariablesTipoList.get(g);
                int tamaño = ((ArrayList) variable).size();

                for (int i = 0; i < tamaño; i++) {
                    insertarEnTablaAuxiliar((E) ((ArrayList) variable).get(i), entity);
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

                    if (todasLasVariables[i].getType() == String.class || todasLasVariables[i].getType() == Date.class) variable = colocarComillasSimples(variable);

                    if (variable == null) variable = NULL;

                    if (variable.getClass().getPackage().getName().equals(NOMBRE_PAQUETE_DE_CLASES)) variable = invocarGetID((E) variable);

                    if (variable.getClass() != ArrayList.class && variable !=null) columnaValor.put(todasLasVariables[i].getName(), variable);

                    if (variable.getClass() == ArrayList.class) listaDeVariablesTipoList.add(variable);

                }

                String sqlFinal = "UPDATE #TABLA SET #COLUMNAVALOR WHERE #ID";
                sqlFinal = sqlFinal.replace("#TABLA", tabla);
                sqlFinal = sqlFinal.replace("#ID", ID_conIgual.concat(invocarGetID(entity).toString())  );
                sqlFinal = sqlFinal.replace("#COLUMNAVALOR", columnaValor.toString());
                sqlFinal = sqlFinal.replaceAll("[>\\[\\]\\{\\}-]", "");

                ejecutaSentencia(conn, sqlFinal);


            for (int g = 0; g < listaDeVariablesTipoList.size(); g++) {

                ArrayList variable = (ArrayList) listaDeVariablesTipoList.get(g);
                int tamaño = ( variable.size());

                for (int i = 0; i < tamaño; i++) {
                    actualizarEnTablaAuxiliar((E) (variable).get(i), entity);
                }
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

        String sql = "DELETE FROM #TABLA WHERE #ID";
        sql = sql.replace("#TABLA", tabla);
        sql = sql.replace("#ID", ID_conIgual.concat(invocarGetID(entity).toString()));

          ejecutaSentencia(conn, sql);

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

                        ejecutaSentencia(conn, sqlFinal);

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

        String sql = "SELECT * FROM #TABLA WHERE #ID";
        sql = sql.replace("#TABLA", tabla);

        sql = sql.replace("#ID", ID_conIgual.concat(invocarGetID( entity).toString()));

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        Object objetoADevolver = retornaInstanciaDeLaClase(entity).newInstance();
        Object daoDeObjAInsertarEnListas;

        String idDelEntity = ID_conIgual.concat(invocarGetID(entity).toString());

        idDelEntity = idDelEntity.replace(ID_conIgual,"");

        try {

            Class claseDelObjADevolver = objetoADevolver.getClass();
            rs.next();

            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {

                Field campoAEstablecer = claseDelObjADevolver.getDeclaredField(rs.getMetaData().getColumnName(i).toLowerCase());

                Object objetoParaInstanciar;

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
                            int idDelObjeto = (int) getMetodoDelObjeto.invoke(objetoParaInstanciar, null);

                            if (idDelObjeto != 0) {
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

                            daoDeObjAInsertarEnListas = Class.forName(nombrePaqueteYCLaseParaDAO.concat(DAO)).newInstance();
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
}
