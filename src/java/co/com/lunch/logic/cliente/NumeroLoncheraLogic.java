/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.cliente;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.cliente.NumeroLoncheraEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author oscarramirez
 */
public class NumeroLoncheraLogic implements AutoCloseable {

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
     * Método que permite ingresar un regitro de NumeroLonchera nuevo
     *
     * @param info
     * @return
     */
    public NumeroLoncheraEntity ingresaNumeroLonchera(NumeroLoncheraEntity info) {
        NumeroLoncheraEntity infoRetorno = null;
        try {
            if (initOperation()) {
                info.setIdNumeroLonchera(maxId());
                sesion.save(info);
                tx.commit();
                infoRetorno = info;
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("ERROR en el save del objeto");
        }
        return infoRetorno;
    }

    /**
     * Métood que permite actualizar un registro de NumeroLonchera existente
     *
     * @param info
     * @return
     */
    public NumeroLoncheraEntity actualizaNumeroLonchera(NumeroLoncheraEntity info) {
        NumeroLoncheraEntity infoRetorno = null;
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
     * Método que trae toda la lista de registros de la NumeroLonchera
     *
     * @return
     */
    public ArrayList<NumeroLoncheraEntity> listaNumeroLonchera() {
        ArrayList<NumeroLoncheraEntity> lista = new ArrayList<>();
        try {
            if (initOperation()) {
                Criteria criteria = sesion.createCriteria(NumeroLoncheraEntity.class);
                lista = (ArrayList<NumeroLoncheraEntity>) criteria.list();
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("ERROR en el selectAll del objeto");
        }
        return lista;
    }

    /**
     * Método que trae una lonchera favorita de un padre
     *
     * @param idPadre
     * @return
     */
    public ArrayList<NumeroLoncheraEntity> favoritosPorPadre(Integer idPadre) {
        ArrayList<NumeroLoncheraEntity> lista = new ArrayList<>();
        try {
            if (initOperation()) {
                Query query = sesion.createQuery("SELECT n FROM NumeroLoncheraEntity n WHERE n.padre.idPadre = :idP");
                query.setParameter("idP", idPadre);
                lista = (ArrayList<NumeroLoncheraEntity>) query.list();
            }
        } catch (Exception e) {
            System.out.println("Error en favoritos por padre");
        }
        return lista;
    }

    public NumeroLoncheraEntity ingresaFavorita(NumeroLoncheraEntity favo) {
        NumeroLoncheraEntity exito = null;
        try {
            if(initOperation()){
                favo.setIdNumeroLonchera(maxId());
                sesion.save(favo);
                tx.commit();
                exito = favo;
            }
        } catch (Exception e) {
            System.out.println("Error en el save de nlonchera");
        }
        return exito;
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
                Query query = sesion.createQuery("SELECT MAX(id) FROM NumeroLoncheraEntity");
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
     * Método que permite eliminar los items de una lochera favorita de un padre
     *
     * @param idLonchera
     * @param idPadre
     * @return
     */
    public boolean eliminaLoncheraFav(Integer idLonchera, Integer idPadre) {
        boolean retorna = false;
        try {
            if (initOperation()) {
                Query query = sesion.createQuery("DELETE FROM FavoritoItemEntity I WHERE I.nlonchera.idNumeroLonchera = :idL");
                query.setParameter("idL", idLonchera);
                System.out.println("ejecuta? "+query.executeUpdate());
                
            
                //tx.commit();
               
               // retorna = eliminaNlonchera(idLonchera, idPadre);
               retorna = true;
            } else {
                System.out.println("Error en conexion de la base de datos");
                retorna = false;
            }
        } catch (Exception e) {
            retorna = false;
            System.out.println("Error en eliminaLoncheraFav: " + e);
        }
        return retorna;
    }

    /**
     * Método que elimina la lonchera favorita del usuario
     *
     * @param idLonchera
     * @param idPadre
     * @return
     */
    public boolean eliminaNlonchera(Integer idLonchera, Integer idPadre) {
        boolean retorno = false;
        try {
            if (initOperation()) {
                Query query2 = sesion.createQuery("DELETE FROM NumeroLoncheraEntity N WHERE N.padre.idPadre = :idP AND N.idNumeroLonchera = :idL");
                query2.setParameter("idL", idLonchera);
                query2.setParameter("idP", idPadre);
                System.out.println("ejecuta? "+query2.executeUpdate());
                if (!tx.wasCommitted()) {
                    tx.commit();
                    close();
                }else{
                    System.out.println("Ya cometida");
                }
                
                retorno = true;
            } else {
                System.out.println("Error en conexion de la base de datos");
                retorno = false;
            }
        } catch (Exception e) {
            retorno = false;
            System.out.println("Error en eliminaNlonchera: " + e);
        }
        return retorno;
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
            System.out.println("AUTO CLOSEABLE FAILLL NLO: " + e);
        }
    }

}
