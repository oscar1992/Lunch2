/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.enpoint;

import co.com.lunch.logic.cliente.FavoritoItemLogic;
import co.com.lunch.logic.cliente.NinoLogic;
import co.com.lunch.logic.cliente.NumeroLoncheraLogic;
import co.com.lunch.persistencia.cliente.FavoritoItemEntity;
import co.com.lunch.persistencia.cliente.NinoEntity;
import co.com.lunch.persistencia.cliente.NumeroLoncheraEntity;
import co.com.lunch.persistencia.cliente.PadreEntity;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author oscarramirez
 */
@WebService(serviceName = "clienteEndpoint")
public class clienteEndpoint {
    
    /**
     * Método que consulta los niños asociados a un padre
     * @param padre
     * @return 
     */
    @WebMethod(operationName = "ninosPorPadre")
    public ArrayList<NinoEntity> ninosPorPadre(@WebParam(name = "padre")PadreEntity padre){
        ArrayList<NinoEntity> listaRet = null;
        try(NinoLogic ninoLogic=new NinoLogic()){
            listaRet=ninoLogic.ninosPorPadre(padre);
        }catch(Exception e){
            System.out.println("Error en el Webservice de ninosPorPadre");
        }
        return listaRet;
    }
    /**
     * Método que trae las loncheras favoritas por padre
     * @param idPadre
     * @return 
     */
    @WebMethod(operationName = "listaFavoritasPorPadre")
    public ArrayList<NumeroLoncheraEntity> listaFavoritasPorPadre(@WebParam(name = "padre")Integer idPadre){
        ArrayList<NumeroLoncheraEntity> listaRet = null;
        try(NumeroLoncheraLogic numeroLoncheraLogic = new NumeroLoncheraLogic()){
            listaRet = numeroLoncheraLogic.favoritosPorPadre(idPadre);
        }catch(Exception e){
            System.out.println("Error en el Web Service de Favoritos por padre");
        }
        return listaRet;
    }
    
    /**
     * Método que trae los productos dentro se una lonchera favorita
     * @param idLonchera
     * @return 
     */
    @WebMethod(operationName = "favoritosPorLonchera")
    public ArrayList<FavoritoItemEntity> favoritosPorLonchera(@WebParam(name = "padre")Integer idPadre){
        ArrayList<FavoritoItemEntity> listaRet =  null;
        try(FavoritoItemLogic favoritoItemLogic = new FavoritoItemLogic()){
            listaRet = favoritoItemLogic.favoritoPorLonchera(idPadre);
        }catch(Exception e){
            System.out.println("Error en el Web Service de favoritosPorLonchera");
        }
        return listaRet;
    }
    
    /**
     * Método que permite ingresar varios items favoritos
     * @param ingresa
     * @return 
     */
    @WebMethod(operationName = "ingresaFavoritos")
    public boolean ingresaFavoritos(@WebParam(name = "lista")ArrayList<FavoritoItemEntity> ingresa){
        boolean retorna= false;
        try(FavoritoItemLogic favoritoItemLogic = new FavoritoItemLogic()){
            retorna = favoritoItemLogic.ingresaFavoritos(ingresa);
            
        }catch(Exception e){
            System.out.println("Error en el web service de ingresaFavoritos");
        }
        return retorna;
    }
    
    /**
     * Método que permitirá ingresar un niño de un padre
     */
    
    @WebMethod(operationName = "ingresaNino")
    public boolean ingresaNino(@WebParam(name = "nino")NinoEntity nino){
        boolean retorna = false;
        try(NinoLogic ninoLogic = new NinoLogic()){
            if(ninoLogic.ingresaNino(nino).getIdNino() != null){
                retorna = true;
            }else{
                System.out.println("ERROR en el guardadado del nino, está sin ID");
            }
        }catch(Exception e){
            System.out.println("Error en el Web service de ingresaNino: "+e);
        }
        return retorna;
    }
    
    /**
     * Método que permite eliminar una lonchera favorita de un padre
     * @param idL
     * @param idP
     * @return 
     */
    @WebMethod(operationName = "EliminaFavorito")
    public boolean eliminaFavorito(@WebParam(name = "idLonchera")Integer idL, @WebParam(name = "idPadre")Integer idP){
        boolean retorna = false;
        try(NumeroLoncheraLogic numeroLoncheraLogic= new NumeroLoncheraLogic()){
            
            retorna = numeroLoncheraLogic.eliminaLoncheraFav(idL, idP);
            retorna = numeroLoncheraLogic.eliminaNlonchera(idL, idP);
        }catch(Exception e){
            System.out.println("Error en el Web service de EliminaFavorito: "+e);
            retorna = false;
        }
        
            
        
        
        
        return retorna;
    }
}
