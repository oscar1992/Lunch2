/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.admin;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.admin.FechaEntregaEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author oscarramirez
 */
public class FechaEntregaLogic implements AutoCloseable {

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
     * Método que permite ingresar una fecha de entrega nueva
     *
     * @param fecha
     * @return
     */
    public FechaEntregaEntity ingresaFecha(FechaEntregaEntity fecha) {
        FechaEntregaEntity fechaRet = null;
        try {
            if (initOperation()) {

                sesion.save(fecha);
                //tx.commit();
                fechaRet = fecha;
            } else {
                System.out.println("Error conexion fecha entrega");
            }
        } catch (Exception e) {
            System.out.println("Error save fecha entrega: " + e);
        }
        return fecha;
    }

    /**
     * Método que permite actualizar una fecha de entrega
     *
     * @param fecha
     * @return
     */
    public FechaEntregaEntity actualizaFecha(FechaEntregaEntity fecha) {
        FechaEntregaEntity fechaRet = null;
        try {
            if (initOperation()) {
                sesion.update(fecha);
                fechaRet = fecha;
            } else {
                System.out.println("Error en upadte fecha entrega");
            }
        } catch (Exception e) {
            System.out.println("Error en update fecha entrega");
        }
        return fecha;
    }

    /**
     * Método que permite traer una lista de las fechas de entrega
     *
     * @return
     */
    public ArrayList<FechaEntregaEntity> listaFechas() {
        ArrayList<FechaEntregaEntity> lista = null;
        try {
            if (initOperation()) {
                Criteria query = sesion.createCriteria(FechaEntregaEntity.class);
                lista = (ArrayList<FechaEntregaEntity>) query.list();

            } else {
                System.out.println("error iniciando conexión listaFechas");
            }
        } catch (Exception e) {
            System.out.println("Error en el select listaFechas: " + e);
        }
        return lista;
    }

    @Override
    public void close() throws Exception {
        try {

            if (sesion != null) {
                if (tx != null) {
                    tx.commit();
                }
                sesion.close();
                sesion = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
