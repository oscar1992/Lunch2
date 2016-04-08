/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.admin;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.admin.InformacionNutricionalEntity;
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
public class InformacionNutricionalLogic {
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
            sesion=HibernateUtil.getSessionFactory().openSession();
            tx=sesion.beginTransaction();
            /*if(sesion==null){
                sesion=HibernateUtil.getSessionFactory().openSession();
                tx=sesion.beginTransaction();
            }else{
                System.out.println("Sesión existente");
            }*/
            retorno=true;
        }catch(Error e){
            System.out.println("ERROR: HibernateUtil en logic");
            retorno=false;
        }
        return retorno;
    }
    /**
     * Método que permite ingresar un regitro de información nutriocional nuevo
     * @param info
     * @return 
     */
    public InformacionNutricionalEntity ingresaInformacionNutricional(InformacionNutricionalEntity info){
        InformacionNutricionalEntity infoRetorno=null;
        try{
            if(initOperation()){
                info.setId(maxId());
                sesion.save(info);
                System.out.println("info: "+info.getId());
                tx.commit();
                infoRetorno=info;
            }else{
                System.out.println("ERROR de validación al conectar");
            }
        }catch(Exception e){
            System.out.println("ERROR en el save del objeto info Nutricional: "+e);
        }
        return infoRetorno;
    }
    
    /**
     * Métood que permite actualizar un registro de información nutricional existente
     * @param info
     * @return 
     */
    public InformacionNutricionalEntity actualizaInformacionNutricional(InformacionNutricionalEntity info){
        InformacionNutricionalEntity infoRetorno=null;
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
     * Método que trae toda la lista de registros de la información nutricional
     * @return 
     */
    public ArrayList<InformacionNutricionalEntity> listaInformacionNutricional(){
        ArrayList<InformacionNutricionalEntity>lista=new ArrayList<>();
        try{
            if(initOperation()){
                Criteria criteria=sesion.createCriteria(InformacionNutricionalEntity.class);
                lista=(ArrayList<InformacionNutricionalEntity>)criteria.list();
            }else{
                System.out.println("ERROR de validación al conectar");
            }
        }catch(Exception e){
            System.out.println("ERROR en el selectAll del objeto");
        }
        return lista;
    }
    /**
     * Método que trae una lista de información nutricional asociada a un producto
     * @param producto
     * @return 
     */
    public ArrayList<InformacionNutricionalEntity> infoPorProducto(ProductoEntity producto){
        ArrayList<InformacionNutricionalEntity>lista=new ArrayList<>();
        try{
            if(initOperation()){
                Query query=sesion.createQuery("FROM InformacionNutricionalEntity I WHERE I.item=:OBJ");
                query.setParameter("OBJ", producto);
                lista=(ArrayList<InformacionNutricionalEntity>)query.list();
            }else{
                System.out.println("ERROR de validación al conectar");
            }
        }catch(Exception e){
            System.out.println("ERROR en el selectAll del objeto");
        }
        return lista;
    }
    
    /**
     * Métood que permite eliminar un registro de información nutricional existente
     * @param info
     * @return 
     */
    public InformacionNutricionalEntity eliminaInformacionNutricional(InformacionNutricionalEntity info){
        InformacionNutricionalEntity infoRetorno=null;
        try{
            if(initOperation()){
                sesion.delete(info);
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
     * Método que reemplaza el autoincrementable de la base de datos, se deja manual para
     * la interacción entre varios BDR
     * @return 
     */
    private Integer maxId(){
        Integer retorna=-1;
        try{
            if(initOperation()){
                Query query=sesion.createQuery("SELECT MAX(id) FROM InformacionNutricionalEntity");
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
