/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.cliente;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.cliente.FavoritoItemEntity;
import co.com.lunch.persistencia.cliente.NumeroLoncheraEntity;
import co.com.lunch.persistencia.cliente.PadreEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

/**
 *
 * @author oscarramirez
 */
public class FavoritoItemLogic implements AutoCloseable {

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
     * Método que permite ingresar un regitro de Favorito Item nuevo
     *
     * @param info
     * @return
     */
    public FavoritoItemEntity ingresaFavoritoItem(FavoritoItemEntity info) {
        FavoritoItemEntity infoRetorno = null;
        try {
            if (initOperation()) {
                int id = maxId();
                info.setIdFavorito(id);
                System.out.println("info: " + info.getIdFavorito());
                sesion.save(info);
                /*
                if (!tx.wasCommitted()) {
                    tx.commit();
                    close();
                } else {
                    System.out.println("Ya cometida");
                }*/
                infoRetorno = info;
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("ERROR en el save del objeto  " + e);
        }
        return infoRetorno;
    }

    /**
     * Métood que permite actualizar un registro de Favorito Item existente
     *
     * @param info
     * @return
     */
    public FavoritoItemEntity actualizaFavoritoItem(FavoritoItemEntity info) {
        FavoritoItemEntity infoRetorno = null;
        try {
            if (initOperation()) {
                sesion.update(info);
                tx.commit();
                infoRetorno = info;
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("ERROR en el update del objeto");
        }
        return infoRetorno;
    }

    /**
     * Método que trae toda la lista de registros de la Favorito Item
     *
     * @return
     */
    public ArrayList<FavoritoItemEntity> listaFavoritoItem() {
        ArrayList<FavoritoItemEntity> lista = new ArrayList<>();
        try {
            if (initOperation()) {
                Criteria criteria = sesion.createCriteria(FavoritoItemEntity.class);
                lista = (ArrayList<FavoritoItemEntity>) criteria.list();
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("ERROR en el selectAll del objeto");
        }
        return lista;
    }

    /**
     * Método que trae los items favoritos por padre
     *
     * @param idLon
     * @return
     */
    public ArrayList<FavoritoItemEntity> favoritoPorLonchera(Integer idLon) {
        ArrayList<FavoritoItemEntity> lista = new ArrayList<>();
        try {
            if (initOperation()) {
                System.out.println("idLon: "+idLon);
                //Query query = sesion.createQuery("SELECT f FROM FavoritoItemEntity f WHERE f.nlonchera.id IN (SELECT n.id FROM NumeroLoncheraEntity n WHERE n.padre.id = :idL) ORDER BY f.id");
                Query query = sesion.createQuery("SELECT f FROM FavoritoItemEntity f WHERE f.nlonchera.idNumeroLonchera = :idL");
                query.setParameter("idL", idLon);
                lista = (ArrayList<FavoritoItemEntity>) query.list();
                System.out.println("Lista: "+lista.size());
            }
        } catch (Exception e) {
            System.out.println("Error en la consulta de los favorios Por Lonchera");
        }
        return lista;
    }

    /**
     * Método que permite ingresar una lonchera favorita a través de sus
     * productos
     *
     * @param listaI
     * @return
     */
    public boolean ingresaFavoritos(ArrayList<FavoritoItemEntity> listaI) {
        boolean exito = false;
        boolean exito2 = false;
        try {
            if (initOperation()) {

                for (FavoritoItemEntity item : listaI) {
                    FavoritoItemEntity ingresa = new FavoritoItemEntity();
                    System.out.println("NLONID: " + item.getNlonchera().getIdNumeroLonchera());
                    ingresa.setNlonchera(item.getNlonchera());
                    ingresa.setProducto(item.getProducto());
                    ingresa = ingresaFavoritoItem(ingresa);
                    if (ingresa != null) {
                        exito2 = true;
                    } else {
                        exito2 = false;
                    }
                    System.out.println("item: " + item.getProducto().getIdProducto() + " nlon: " + item.getNlonchera().getIdNumeroLonchera());
                }

            }

        } catch (Exception e) {

        }
        return exito && exito2;
    }

    /**
     * Método que trae todos los items de los favoritos
     * @return 
     */
    public ArrayList<FavoritoItemEntity> favoritosTodos() {
        ArrayList<FavoritoItemEntity> lista = new ArrayList<>();
        try {
            if (initOperation()) {
                Query query = sesion.createQuery("SELECT f FROM FavoritoItemEntity f ");
                lista = (ArrayList<FavoritoItemEntity>) query.list();
                System.out.println("Lista: "+lista.size());
            }
        } catch (Exception e) {
            System.out.println("Error en la consulta de los favorios todos");
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
                Query query = sesion.createQuery("SELECT MAX(id) FROM FavoritoItemEntity");
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
                try {
                    tx.commit();
                } catch (Exception e) {
                    System.out.println("????: " + e);
                }
            } else {
                initOperation();
            }
            if (sesion != null) {
                sesion.close();
                sesion = null;
            }

        } catch (Exception e) {
            System.out.println("AUTOCLOSEABLE FAIL: " + e);
            //e.printStackTrace();
        }
    }

}
