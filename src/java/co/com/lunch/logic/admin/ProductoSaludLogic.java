/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.admin;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.admin.ProductoSaludEntity;
import co.com.lunch.persistencia.admin.SaludEntity;
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
public class ProductoSaludLogic implements AutoCloseable{
    
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
     * Método que permite ingresar un item a una caja saludable
     * @param info
     * @return 
     */
    public ProductoSaludEntity ingresaProductoSalud(ProductoSaludEntity info){
        ProductoSaludEntity infoRetorno=null;
        try{
            if(initOperation()){
                info.setIdProductosalud(maxId());
                System.out.println("id prod"+ info.getProducto().getIdProducto());
                System.out.println("id salud: "+info.getSalud().getIdSalud());
                System.out.println("id self: "+info.getIdProductosalud());
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
     * Método que permite sumar al indice de la inserción
     * @return 
     */
    private Integer maxId(){
        Integer retorna=-1;
        try{
            if(initOperation()){
                Query query=sesion.createQuery("SELECT MAX(id) FROM ProductoSaludEntity");
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
    
    /**
     * Método que permite cambiar un item de una lonchera saludable
     * @param info
     * @return 
     */
    public ProductoSaludEntity actualizaProductoSalud(ProductoSaludEntity info){
        ProductoSaludEntity infoRetorno=null;
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
     * Método que permite traer toda la lista de los productos-salud
     * @return 
     */
    public ArrayList<ProductoSaludEntity> listaProductoSalud(){
        ArrayList<ProductoSaludEntity>lista=new ArrayList<>();
        try{
            if(initOperation()){
                Criteria criteria=sesion.createCriteria(ProductoSaludEntity.class);
                lista=(ArrayList<ProductoSaludEntity>)criteria.list();
                
            }else{
                System.out.println("ERROR de validación al conectar");
            }
        }catch(Exception e){
            System.out.println("ERROR en el selectAll del producto-salud");
        }
        return lista;
    }
    
    /**
     * Método que trae la lista los productos de las cajas saludables
     * @param salud
     * @return 
     */
    public ArrayList<ProductoSaludEntity> productoPorcaja(SaludEntity salud){
        ArrayList<ProductoSaludEntity>lista=new ArrayList<>();
        
        try{
            if(initOperation()){
                Criteria criteria=sesion.createCriteria(ProductoSaludEntity.class);
                System.out.println("id: "+salud.getIdSalud());
                criteria.add(Restrictions.eq("salud.id", salud.getIdSalud()));
                lista=(ArrayList<ProductoSaludEntity>)criteria.list();
                System.out.println("Consuta");
            }else{
                System.out.println("ERROR de validación al conectar");
            }
        }catch(Exception e){
            System.out.println("ERROR en el select de un producto-salud");
        }
        return lista;
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
                System.out.println("cerró");
            }

        } catch (Exception e) {
            System.out.println("ERROR CLOSEABLE: "+e.getMessage());
        }
    }
}
