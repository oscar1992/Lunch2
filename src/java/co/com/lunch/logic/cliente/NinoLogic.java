/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.cliente;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.cliente.NinoEntity;
import co.com.lunch.persistencia.cliente.PadreEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author oscarramirez
 */
public class NinoLogic implements AutoCloseable{
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
     * Método que permite ingresar un regitro de Nino nuevo
     * @param info
     * @return 
     */
    public NinoEntity ingresaNino(NinoEntity info){
        NinoEntity infoRetorno=null;
        try{
            if(initOperation()){
                info.setIdNino(maxId());
                sesion.save(info);
                if (!tx.wasCommitted()) {
                    tx.commit();
                    close();
                }else{
                    System.out.println("Ya cometida");
                }
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
     * Métood que permite actualizar un registro de Nino existente
     * @param info
     * @return 
     */
    public NinoEntity actualizaNino(NinoEntity info){
        NinoEntity infoRetorno=null;
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
     * Método que trae toda la lista de registros de la Nino
     * @return 
     */
    public ArrayList<NinoEntity> listaNino(){
        ArrayList<NinoEntity>lista=new ArrayList<>();
        try{
            if(initOperation()){
                Criteria criteria=sesion.createCriteria(NinoEntity.class);
                lista=(ArrayList<NinoEntity>)criteria.list();
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
                Query query=sesion.createQuery("SELECT MAX(id) FROM NinoEntity");
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
    
    public ArrayList<NinoEntity> ninosPorPadre(PadreEntity padre){
        ArrayList<NinoEntity> listaRet=null;
        try{
            if(initOperation()){
                Query query = sesion.createQuery("SELECT n FROM NinoEntity n WHERE n.padre.id =:id");
                query.setParameter("id", padre.getIdPadre());
                listaRet=(ArrayList<NinoEntity>) query.list();
            }else{
                System.out.println("Error en la conexión, en ninosPorPadre");
            }
        }catch(Exception e){
            System.out.println("Error general en ninosPorPadre: "+e);
        }
        return listaRet;
    }
    
    @Override
    public void close() throws Exception {
        try {
            if (!tx.wasCommitted()) {
                try{
                tx.commit();
                }catch(Exception e){
                    System.out.println("????: "+e);
                }
            }else{
                initOperation();
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
