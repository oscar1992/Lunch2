/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.admin;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.admin.TagsEntity;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author oscarramirez
 */
public class TagsLogic implements AutoCloseable{
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
            System.out.println("ERROR: HibernateUtil en logic: "+e);
            retorno=false;
        }
        return retorno;
    }
    
    //Método que inserta una lista de Tags a un producto asociado
    public boolean insertaTags(ArrayList<TagsEntity> tags, Integer idProdcuto){
        boolean aprueba = false;
        try{
            if(initOperation()){
                for(TagsEntity tag: tags){
                    tag.setIdTag(maxId());
                    sesion.save(tag);
                    tx.commit();
                    
                }
                sesion.close();
                aprueba = true;
            }else{
               System.out.println("ERROR de validación al conectar(InsertaTags): "); 
            }
        }catch(Exception e){
            System.out.println("Error en la inserción de tags: "+e);
        }
        return aprueba;
    }
    
    //Método que asigna el id de los tags, se usa en vez del autoincrementable para el uso entre varias BDR
    private Integer maxId(){
        Integer retorna=-1;
        try{
            if(initOperation()){
                Query query=sesion.createQuery("SELECT MAX(id) FROM TagsEntity");
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
    
    public ArrayList<TagsEntity> TagsPorProducto(Integer idProd){
        ArrayList<TagsEntity> listaRet = null;
        try{
            if(initOperation()){
                Query query = sesion.createQuery("SELECT t FROM TagsEntity t WHERE t.producto.idProducto =:Idp");
                query.setParameter("Idp", idProd);
                listaRet = (ArrayList<TagsEntity>) query.list();
            }else{
                System.out.println("ERROR de validación al conectar");
            }
        }catch(Exception e){
            System.out.println("Error en la cosulta de tags (TagsPorProducto): "+e);
        }
        return listaRet;
    }

    @Override
    public void close() throws Exception {
        try {
            //System.out.println("cierra?");
            if (tx != null) {
                tx.commit();
            }
            if (sesion != null) {
                sesion.close();
                sesion = null;
                //System.out.println("cerró");
            }

        } catch (Exception e) {
            System.out.println("ERROR CLOSEABLE: "+e.getMessage());
        }
    }
}
