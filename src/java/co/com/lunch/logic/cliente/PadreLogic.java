/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.cliente;

import co.com.lunch.conexion.HibernateUtil;
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
public class PadreLogic implements AutoCloseable {

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
     * Método que permite ingresar un regitro de Padre nuevo
     *
     * @param info
     * @return
     */
    public PadreEntity ingresaPadre(PadreEntity info) {
        PadreEntity infoRetorno = null;
        System.out.println("fecha: "+info.getTerminoFecha());
        try {
            if (initOperation()) {
                
                info.setIdPadre(maxId());
                sesion.save(info);
                tx.commit();
                infoRetorno = info;
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("ERROR en el save del objeto: "+e);
        }
        return infoRetorno;
    }

    /**
     * Métood que permite actualizar un registro de Padre existente
     *
     * @param info
     * @return
     */
    public PadreEntity actualizaPadre(PadreEntity info) {
        PadreEntity infoRetorno = null;
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
     * Método que trae toda la lista de registros de la Padre
     *
     * @return
     */
    public ArrayList<PadreEntity> listaPadre() {
        ArrayList<PadreEntity> lista = new ArrayList<>();
        try {
            if (initOperation()) {
                Criteria criteria = sesion.createCriteria(PadreEntity.class);
                lista = (ArrayList<PadreEntity>) criteria.list();
            } else {
                System.out.println("ERROR de validación al conectar");
            }
        } catch (Exception e) {
            System.out.println("ERROR en el selectAll del objeto");
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
                Query query = sesion.createQuery("SELECT MAX(id) FROM PadreEntity");
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

    //Método usado por el login para validar la existencia del padre junto con su contraseña
    public PadreEntity existePadre(String email, String pass) {

        PadreEntity padre = null;
        try {
            if (initOperation()) {
                Query query = sesion.createQuery("SELECT p FROM PadreEntity p WHERE p.email = :ema AND p.contrasena = :pass");
                query.setParameter("ema", email);
                query.setParameter("pass", pass);
                padre = (PadreEntity) query.uniqueResult();
            }
        } catch (Exception e) {
            System.out.println("Error Login: " + e);
        }
        if (padre == null) {
            System.out.println("Usuario no Existe");
        } else {
            System.out.println("Autenticación de: " + padre.getEmail());

        }
        return padre;
    }

    public Boolean compruebaEmail(String email) {
        Boolean exx = false;
        try {
            if (initOperation()) {
                Query query = sesion.createQuery("SELECT p FROM PadreEntity p WHERE p.email = :ema");
                query.setParameter("ema", email);
                PadreEntity padre = (PadreEntity) query.uniqueResult();
                if (padre != null) {
                    if (padre.getEmail().equals(email)) {
                        exx = true;
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("Error PadreLogic Valida Email");
        }
        return exx;
    }
    
    public String recupera(String email){
        String pass = null;
        try {
            if (initOperation()) {
                Query query = sesion.createQuery("SELECT p.contrasena FROM PadreEntity p WHERE p.email = :ema");
                query.setParameter("ema", email);
                pass = (String) query.uniqueResult();
        
            }
        } catch (Exception e) {
            System.out.println("Error PadreLogic Valida Email");
        }
        
        return pass;
    }

    /**
     * Método que trae un padre por su Id
     *
     * @param id
     * @return
     */
    public PadreEntity padrePorId(Integer id) {
        PadreEntity retorna = null;
        try {
            if (initOperation()) {
                Query query = sesion.createQuery("SELECT p FROM PadreEntity p WHERE p.idPadre = :idP");
                query.setParameter("idP", id);
                retorna = (PadreEntity) query.uniqueResult();

            }
        } catch (Exception e) {
            System.out.println("Error en la consulta de padrePorId");
        }
        return retorna;
    }
    
    public PadreEntity padrePorEmail(String email){
        PadreEntity retorna = null;
        try {
            if (initOperation()) {
                Query query = sesion.createQuery("SELECT p FROM PadreEntity p WHERE p.email = :ema");
                query.setParameter("ema",email);
                retorna = (PadreEntity) query.uniqueResult();
            }
        } catch (Exception e) {
            System.out.println("Error en la consulta de padrePorId");
        }
        return retorna;
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
