/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.admin;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.admin.EnvioEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author oscarramirez
 */


public class EnvioLogic implements  AutoCloseable{
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
     * Método que permite ingresar el valor del envio
     * @param envio
     * @return 
     */
    public EnvioEntity ingresaEnvio(EnvioEntity envio){
        EnvioEntity ret = null;
        try{
            if(initOperation()){
                envio.setIdEnvio(1);
                System.out.println("ENVIA PRE: "+envio.getEnvio());
                sesion.save(envio);
                tx.commit();
                ret = envio;
            }else{
                System.out.println("Error de conexión");
            }
        }catch(Exception e){
            System.out.println("Error ingreso envio: "+e);
        }
        return ret;
    }
    
    /**
     * Método que permite cambiar el valor del envío
     * @param envio
     * @return 
     */
    public EnvioEntity actualizaEnvio(EnvioEntity envio){
        EnvioEntity ret = null;
        try{
            if(initOperation()){
                sesion.update(envio);
                tx.commit();
                ret = envio;
            }else{
                System.out.println("Error de conexión");
            }
        }catch(Exception e){
            System.out.println("Error ingreso envio");
        }
        return  ret;
    }
    
    /**
     * Método que retorna el envio
     * @return 
     */
    public EnvioEntity consultaEnvio(){
        EnvioEntity ret = null;
        try{
            if(initOperation()){
                Query query = sesion.createQuery("FROM EnvioEntity e WHERE e.idEnvio = 1");
                ret = (EnvioEntity) query.uniqueResult();
            }else{
                System.out.println("Error de conexión");
            }
        }catch(Exception e){
            System.out.println("Error ingreso envio");
        }
        return ret;
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