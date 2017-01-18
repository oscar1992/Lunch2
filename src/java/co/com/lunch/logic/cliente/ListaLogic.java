/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.cliente;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.cliente.ListaEntity;
import co.com.lunch.persistencia.admin.ProductoEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author oscarramirez
 */
public class ListaLogic implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Método que establece la conexión a la base de datos luego de validar de
     * que la sesion esté nula
     *
     * @return
     */
    private boolean initOperation() {
        boolean retorno = false;
        try {
            if (sesion == null) {
                sesion = HibernateUtil.getSessionFactory().openSession();
                tx = sesion.beginTransaction();
            }
            retorno = true;
        } catch (Error e) {
            System.out.println("ERROR: HibernateUtil en logic");
            retorno = false;
        }
        return retorno;
    }

    /**
     * Método que permite ingresar un regitro de Lista nuevo
     *
     * @param info
     * @return
     */
    public ListaEntity ingresaLista(ListaEntity info) {
        ListaEntity infoRetorno = null;
        try {
            if (initOperation()) {
                info.setIdLista(maxId());
                sesion.save(info);
                
                infoRetorno = info;
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("ERROR en el save del objeto");
        }
        return infoRetorno;
    }

    /**
     * Métood que permite actualizar un registro de Lista existente
     *
     * @param info
     * @return
     */
    public ListaEntity actualizaLista(ListaEntity info) {
        ListaEntity infoRetorno = null;
        try {
            if (initOperation()) {
                sesion.update(info);
                tx.commit();
                infoRetorno = info;
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("ERROR en el update del objeto");
        }
        return infoRetorno;
    }

    /**
     * Método que trae toda la lista de registros de la Lista
     *
     * @return
     */
    public ArrayList<ListaEntity> listaLista() {
        ArrayList<ListaEntity> lista = new ArrayList<>();
        try {
            if (initOperation()) {
                Criteria criteria = sesion.createCriteria(ListaEntity.class);
                lista = (ArrayList<ListaEntity>) criteria.list();
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("ERROR en el selectAll del objeto");
        }
        return lista;
    }
    
    /**
     * Método que permite recuperar una lista de productos pertenecientes a un tipo de lonchera
     * @param idTipo
     * @return 
     */
    public ArrayList<ListaEntity> listaPorTipo(Integer idTipo){
        ArrayList<ListaEntity> listaRet = new ArrayList<>();
        try{
            if(initOperation()){
                Criteria criteria = sesion.createCriteria(ListaEntity.class);
                criteria.add(Restrictions.eq("tipoLonchera.idTipoLonchera", idTipo));
                listaRet = (ArrayList<ListaEntity>) criteria.list();
            }else{
                System.out.println("Error de validación al conectar");
            }
        }catch(Exception e){
            System.out.println("Error en la consulta de listaPor tipo");
        }
        return listaRet;
    }

    /**
     * Método que reemplaza el autoincrementable de la base de datos, se deja
     * manual para la interacción entre varios BDR
     *
     * @return
     */
    private Integer maxId() {
        Integer retorna = -1;
        try {
            if (initOperation()) {
                Query query = sesion.createQuery("SELECT MAX(id) FROM ListaEntity");
                retorna = (Integer) query.uniqueResult();
                retorna++;
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            retorna = 1;
        }
        return retorna;
    }

    @Override
    public void close() throws Exception {
        try {
            if (tx != null) {
                tx.commit();
            }
            if (sesion != null) {
                sesion.close();
                sesion = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
