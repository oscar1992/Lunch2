/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.cliente;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.cliente.FeedbackEntity;
import co.com.lunch.persistencia.admin.ProductoEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author oscarramirez
 */
public class FeedbackLogic {
    private Session sesion;
    private Transaction tx;
    /**
     * Método que establece la conexión a la base de datos luego de validar de que la sesion
     * esté nula
     * @return 
     */
    private boolean initOperation(){
        boolean retorno=false;
        try{
            if(sesion==null){
                sesion=HibernateUtil.getSessionFactory().openSession();
                tx=sesion.beginTransaction();
            }
            retorno=true;
        }catch(Error e){
            System.out.println("ERROR: HibernateUtil en logic");
            retorno=false;
        }
        return retorno;
    }
    /**
     * Método que permite ingresar un regitro de Feedback nuevo
     * @param info
     * @return 
     */
    public FeedbackEntity ingresaFeedback(FeedbackEntity info){
        FeedbackEntity infoRetorno=null;
        try{
            if(initOperation()){
                info.setId(maxId());
                sesion.save(info);
                tx.commit();
                infoRetorno=info;
            }else{
                System.out.println("ERROR de validación al conectar");
            }
        }catch(Exception e){
            System.out.println("ERROR en el save del objeto");
        }
        return infoRetorno;
    }
    /**
     * Métood que permite actualizar un registro de Feedback existente
     * @param info
     * @return 
     */
    public FeedbackEntity actualizaFeedback(FeedbackEntity info){
        FeedbackEntity infoRetorno=null;
        try{
            if(initOperation()){
                sesion.update(info);
                tx.commit();
                infoRetorno=info;
            }else{
                System.out.println("ERROR de validación al conectar");
            }
        }catch(Exception e){
            System.out.println("ERROR en el update del objeto");
        }
        return infoRetorno;
    }
    /**
     * Método que trae toda la lista de registros de la Feedback
     * @return 
     */
    public ArrayList<FeedbackEntity> listaFeedback(){
        ArrayList<FeedbackEntity>lista=new ArrayList<>();
        try{
            if(initOperation()){
                Criteria criteria=sesion.createCriteria(FeedbackEntity.class);
                lista=(ArrayList<FeedbackEntity>)criteria.list();
            }else{
                System.out.println("ERROR de validación al conectar");
            }
        }catch(Exception e){
            System.out.println("ERROR en el selectAll del objeto");
        }
        return lista;
    }
 
    /**
     * Método que reemplaza el autoincrementable de la base de datos, se deja manual para
     * la interacción entre varios BDR
     * @return 
     */
    private Integer maxId(){
        Integer retorna=-1;
        try{
            if(initOperation()){
                Query query=sesion.createQuery("SELECT MAX(id) FROM FeedbackEntity");
                retorna =(Integer)query.uniqueResult();
                retorna++;
            }else{
                System.out.println("ERROR de validación al conectar");
            }
        }catch(Exception e){
            retorna=1;
        }
        return retorna;
    }
    
    
}
