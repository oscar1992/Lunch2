/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.admin;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.admin.MarcaEntity;
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
public class MarcaLogic {
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
     * Método que permite ingresar un regitro de Marca nuevo
     * @param info
     * @return 
     */
    public MarcaEntity ingresaMarca(MarcaEntity info){
        MarcaEntity infoRetorno=null;
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
     * Métood que permite actualizar un registro de Marca existente
     * @param info
     * @return 
     */
    public MarcaEntity actualizaMarca(MarcaEntity info){
        MarcaEntity infoRetorno=null;
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
     * Método que trae toda la lista de registros de la Marca
     * @return 
     */
    public ArrayList<MarcaEntity> listaMarca(){
        ArrayList<MarcaEntity>lista=new ArrayList<>();
        try{
            if(initOperation()){
                Criteria criteria=sesion.createCriteria(MarcaEntity.class);
                lista=(ArrayList<MarcaEntity>)criteria.list();
            }else{
                System.out.println("ERROR de validación al conectar");
            }
        }catch(Exception e){
            System.out.println("ERROR en el selectAll del objeto");
        }
        return lista;
    }
    /**
     * Método que permite eliminar una marca
     * @param info
     * @return 
     */
    public MarcaEntity eliminaMarca(MarcaEntity info){
        MarcaEntity infoRetorno=null;
        try{
            if(initOperation()){
                sesion.delete(info);
                tx.commit();
                infoRetorno=info;
            }else{
                System.out.println("ERROR de validación al conectar");
            }
        }catch(Exception e){
            System.out.println("ERROR en el delete del objeto");
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
                Query query=sesion.createQuery("SELECT MAX(id) FROM MarcaEntity");
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
