/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.logic.cliente;

import co.com.lunch.conexion.HibernateUtil;
import co.com.lunch.persistencia.cliente.BusquedasEntity;
import co.com.lunch.persistencia.cliente.PadreEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author oscarramirez
 */
public class BusquedasLogic implements AutoCloseable{
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
    
    
    public void ingresaBusqueda(Integer idPadre, String Busqueda){
        BusquedasEntity busqueda = new BusquedasEntity();
        PadreEntity padre = new PadreEntity();
        try{
            if(initOperation()){
                try(PadreLogic padreLogic=new PadreLogic()){
                    padre = padreLogic.padrePorId(idPadre);
                    busqueda.setIdBusqueda(maxID());
                    busqueda.setPadre(padre);
                    busqueda.setBusqueda(Busqueda);
                    sesion.save(busqueda);
                }catch(Exception ee){
                    System.out.println("Error en busqueda - padre: "+ee);
                }
                
            }else{
                System.out.println("Error de validación al conectar");
            }
        }catch(Exception e){
            System.out.println("Error en busqueda - Insercion: "+e);
        }
    }
    
    
    /**
     * Método que reemplaza el autoincrementable de la base de datos, se deja
     * manual para la interacción entre varios BDR
     *
     * @return
     */
    public Integer maxID(){
        Integer retorna = -1;
        try {
            if (initOperation()) {
                Query query = sesion.createQuery("SELECT MAX(id) FROM BusquedasEntity");
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
