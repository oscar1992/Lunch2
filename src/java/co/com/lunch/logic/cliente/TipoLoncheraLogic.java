/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.cliente;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.cliente.TipoLoncheraEntity;
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
public class TipoLoncheraLogic implements AutoCloseable{
    
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

    public Integer ingresaTipoLonchera(TipoLoncheraEntity tipo){
        //TipoLoncheraEntity infoRetorno = null;
        Integer ids = null;
        try {
            if (initOperation()) {
                System.out.println("Tipos NOmb: "+tipo.getNombreTipo());
                tipo.setIdTipoLonchera(maxId());
                sesion.save(tipo);
                //tx.commit();
                ids = tipo.getIdTipoLonchera();
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("ERROR en el save de ingresa Tipo Lonchera: "+e);
        }
        return ids;
    }
    
    /**
     * Método que permite traer un lista de tipos de lomcheras de un pedido
     * @param idPedido
     * @return 
     */
    public ArrayList<TipoLoncheraEntity> tiposPorPedido(Integer idPedido){
        ArrayList<TipoLoncheraEntity> listaRet = new ArrayList<>();
        try{
            if(initOperation()){
                Criteria criteria = sesion.createCriteria(TipoLoncheraEntity.class);
                criteria.add(Restrictions.eq("pedido.idPedido", idPedido));
                listaRet = (ArrayList<TipoLoncheraEntity>) criteria.list();
            }else{
                System.out.println("Error de validacion al conectar");
            }
        } catch (Exception e) {
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
                Query query = sesion.createQuery("SELECT MAX(id) FROM TipoLoncheraEntity");
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
            if (!tx.wasCommitted()) {
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
