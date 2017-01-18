/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.admin;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.admin.ProductoEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.util.Pair;
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
public class ProductoLogic implements AutoCloseable {

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
            } else {
                System.out.println("Sesion ya iniciada");
            }
            retorno = true;
        } catch (Error e) {
            System.out.println("ERROR: HibernateUtil en logic: " + e);
            retorno = false;
        }
        return retorno;
    }

    /**
     * Método que permite ingresar un regitro de Producto nuevo
     *
     * @param info
     * @return
     */
    public ProductoEntity ingresaProducto(ProductoEntity info) {
        ProductoEntity infoRetorno = info;
        try {
            if (initOperation()) {
                info.setIdProducto(maxId());
                sesion.save(info);
                //tx.commit();
                //sesion.close();
            } else {
                System.out.println("ERROR de validación al conectar: ");
                infoRetorno = null;
            }
        } catch (Exception e) {
            System.out.println("ERROR en el save del objeto PRODUCTO: " + e.getMessage());
            infoRetorno = null;
        }
        return infoRetorno;
    }

    /**
     * Métood que permite actualizar un registro de Producto existente
     *
     * @param info
     * @return
     */
    public ProductoEntity actualizaProducto(ProductoEntity info) {
        ProductoEntity infoRetorno = null;
        try {
            if (initOperation()) {
                info.setUltimaActualizacion(new Date(System.currentTimeMillis()));
                infoRetorno = info;
                sesion.update(info);
                //tx.commit();

            } else {
                System.out.println("ERROR de validación al conectar");
                infoRetorno = null;
            }
        } catch (Exception e) {
            System.out.println("ERROR en el update del objeto");
            infoRetorno = null;
        }
        return infoRetorno;
    }

    /**
     * Método que trae toda la lista de registros de la tabla Producto si el
     * producto está disponible
     *
     * @return
     */
    public ArrayList<ProductoEntity> listaProducto() {
        ArrayList<ProductoEntity> lista = new ArrayList<>();
        try {
            if (initOperation()) {
                Criteria criteria = sesion.createCriteria(ProductoEntity.class);
                criteria.add(Restrictions.eq("disponible", true));
                criteria.addOrder(Order.desc("idProducto"));
                lista = (ArrayList<ProductoEntity>) criteria.list();
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("ERROR en el selectAll del objeto: " + e);
        }
        return lista;
    }

    /**
     * Método que trae todo lo que se halle en la tabla de los productos
     *
     * @return
     */
    public ArrayList<ProductoEntity> listaProductosTodos() {
        ArrayList<ProductoEntity> lista = new ArrayList<>();
        try {
            if (initOperation()) {
                Criteria criteria = sesion.createCriteria(ProductoEntity.class);
                lista = (ArrayList<ProductoEntity>) criteria.list();
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("ERROR en el selectAll del objeto: " + e);
        }
        return lista;
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
                Query query = sesion.createQuery("SELECT MAX(id) FROM ProductoEntity");
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
     * Método que trae un producto por su id
     *
     * @param id
     * @return
     */
    public ProductoEntity productoPorId(int id) {
        ProductoEntity retorna = null;
        try {
            if (initOperation()) {
                Query query = sesion.createQuery("FROM ProductoEntity p WHERE p.idProducto=:ID");
                query.setParameter("ID", id);
                retorna = (ProductoEntity) query.uniqueResult();
            }
        } catch (Exception e) {
            System.out.println("Error en la consulta de un único Producto por ID: " + e);
        }
        return retorna;
    }

    /**
     * Método que trae una lista de productos por tipo
     *
     * @param tipo
     * @return
     */
    public ArrayList<ProductoEntity> productosPorTipo(Integer tipo) {
        ArrayList<ProductoEntity> retorna = new ArrayList<>();
        try {
            if (initOperation()) {
                Criteria criteria = sesion.createCriteria(ProductoEntity.class);
                criteria.add(Restrictions.eq("tipo", tipo));
                retorna = (ArrayList<ProductoEntity>) criteria.list();
            } else {
                System.out.println("Error en la validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("Error en PrductoLogic.productosPorTipo: " + e);
        }
        return retorna;
    }

    /**
     * Método que compara las fechas de los productos que vienen del app, con
     * las fechas de los productos en el servidor
     *
     * @param lista
     * @return
     */
    public ArrayList<ProductoEntity> listaProductosNuevos(HashMap<Integer, Date> lista) {
        ArrayList<ProductoEntity> retorna = new ArrayList<>();
        ArrayList<ProductoEntity> listaTodos = listaProductosTodos();
        System.out.println("lista: " + lista.size());
        System.out.println("lista T: " + listaTodos.size());
        int p = 0;
        for (ProductoEntity prod : listaTodos) {
            if (!lista.containsKey(prod.getIdProducto())) {
                if (prod.isDisponible()) {
                    retorna.add(prod);
                }
            }
        }
        for (ProductoEntity prod : listaTodos) {
            if (lista.containsKey(prod.getIdProducto())) {
                if (prod.getUltimaActualizacion().after(lista.get(prod.getIdProducto()))) {
                    System.out.println("PROD: "+ prod.getIdProducto());
                    System.out.println("Fecha serv: "+prod.getUltimaActualizacion());
                    System.out.println("Fecha app: "+lista.get(prod.getIdProducto()));
                    if (prod.isDisponible()) {
                        retorna.add(prod);
                    }
                }
            }
        }

        System.out.println("Retor: " + retorna.size());
        return retorna;
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
            System.out.println("ERROR CLOSEABLE PRODUCTO LOGIC: " + e.getMessage());
        }
    }

}
