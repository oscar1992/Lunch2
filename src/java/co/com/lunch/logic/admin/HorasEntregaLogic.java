/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.admin;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.admin.HorasEntregaEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author oscarramirez
 */

public class HorasEntregaLogic implements  AutoCloseable{
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
     * Método que ingresa un rango de horas de entrega nuevo
     * @param hora
     * @return 
     */
    public HorasEntregaEntity ingresaHora(HorasEntregaEntity hora){
        HorasEntregaEntity horas = null;
        try {
            if(initOperation()){
                sesion.save(hora);
                horas = hora;
            }else{
                System.out.println("Error en conexión a la BD");
            }
        } catch (Exception e) {
            System.out.println("Error en el save de horasEntrega: "+e);
        }
        return horas;
    }
    
    /**
     * Método que permite cambiar un rango de horas
     * @param hora
     * @return 
     */
    public HorasEntregaEntity actualizaHora(HorasEntregaEntity hora){
        
        HorasEntregaEntity horas = null;
        try {
            if(initOperation()){
                sesion.update(hora);
                horas = hora;
            }else{
                System.out.println("Error en conexión a la BD");
            }
        } catch (Exception e) {
            System.out.println("Error en el update de horasEntrega");
        }
        return horas;
    }
    
    /**
     * Método que trae la lista de las horas disponibles
     * @return 
     */
    public ArrayList<HorasEntregaEntity> listaHoras(){
        ArrayList<HorasEntregaEntity> horas = null;
        try {
            if(initOperation()){
                Criteria query = sesion.createCriteria(HorasEntregaEntity.class);
                horas=(ArrayList<HorasEntregaEntity>) query.list();
            }else{
                System.out.println("Error en conexión a la BD");
            }
        } catch (Exception e) {
            System.out.println("Error en el select de horasEntrega");
        }
        return horas;
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
