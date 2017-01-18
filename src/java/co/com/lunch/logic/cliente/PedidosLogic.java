/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.cliente;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.cliente.PedidoEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author oscarramirez
 */
public class PedidosLogic implements AutoCloseable{
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
     * Método que permite ingresar un pedido nuevo
     * @param pedido
     * @return 
     */
    public PedidoEntity ingresaPedido(PedidoEntity pedido){
        PedidoEntity infoRetorno = null;
        try {
            if (initOperation()) {
                pedido.setCancelado(false);
                pedido.setIdPedido(maxId());
                sesion.save(pedido);
                tx.commit();
                infoRetorno = pedido;
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("ERROR en el save de ingresa Pedido: "+e);
        }
        return infoRetorno;
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
                Query query = sesion.createQuery("SELECT MAX(id) FROM PedidoEntity");
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
    
    /**
     * Método que trae una lista de pedidos de un cliente
     * @return 
     */
    public ArrayList<PedidoEntity> listaPedidos(Integer idPadre){
        ArrayList<PedidoEntity> listaRet = new ArrayList<>();
        try{
            if(initOperation()){
                Criteria criteria = sesion.createCriteria(PedidoEntity.class);
                criteria.add(Restrictions.eq("padre.idPadre", idPadre));
                criteria.add(Restrictions.eq("cancelado", false));
                criteria.addOrder(Order.asc("fechaPedido"));
                listaRet = (ArrayList<PedidoEntity>) criteria.list();
            }else{
                System.out.println("ERROR de validación al conectar");
            }
        }catch(Exception e){
            System.out.println("Error en la lista de pedidos: "+e);
        }
        return listaRet;
    }

    /**
     * Método que permite cancelar un pedido
     * @param pedido
     * @return 
     */
    public boolean cancelaPedido(PedidoEntity pedido){
        try{
            if(initOperation()){
                pedido.setCancelado(true);
                sesion.update(pedido);
                return true;
            }else{
                System.out.println("Error de valodación al conectar");
                return false;
            }
        }catch(Exception e){
            System.out.println("Error cancelando pedido: "+e);
            return false;
        }
    }
    
    /**
     * Método que trae unalista de pedidos
     * @return 
     */
    public ArrayList<PedidoEntity> listaPedidosTodos(){
        ArrayList<PedidoEntity> retorna = null;
        try{
            if(initOperation()){
                Criteria crit = sesion.createCriteria(PedidoEntity.class);
                crit.add(Restrictions.eq("cancelado", false));
                crit.addOrder(Order.asc("fechaEntrega"));
                retorna = (ArrayList<PedidoEntity>) crit.list();
            }else{
                System.out.println("Error en validación de conectar");
            }
        }catch(Exception e){
            System.out.println("Error en consulta Pedidos Todos");
        }
        return retorna;
    }
    
    /**
     * Método que permite actualizar un pedido
     * @param pedido
     * @return 
     */
    public boolean actuaPedido(PedidoEntity pedido){
        boolean retu = false;
        try {
            if(initOperation()){
                sesion.update(pedido);
                retu = true;
            }else{
                System.out.println("Error en la validación de conectar");
            }
        } catch (Exception e) {
            System.out.println("Error en actua pedido");
        }
        return  retu;
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
