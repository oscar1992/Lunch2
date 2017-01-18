/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.enpoint;

import co.com.lunch.correos.EnviaCorreo;
import co.com.lunch.correos.EnviaCorreo2;
import co.com.lunch.logic.admin.ProductoLogic;
import co.com.lunch.logic.admin.ProductoSaludLogic;
import co.com.lunch.logic.cliente.BusquedasLogic;
import co.com.lunch.logic.cliente.FavoritoItemLogic;
import co.com.lunch.logic.cliente.ListaLogic;
import co.com.lunch.logic.cliente.NinoLogic;
import co.com.lunch.logic.cliente.NumeroLoncheraLogic;
import co.com.lunch.logic.cliente.PadreLogic;
import co.com.lunch.logic.cliente.PedidosLogic;
import co.com.lunch.logic.cliente.TipoLoncheraLogic;
import co.com.lunch.persistencia.admin.ProductoEntity;
import co.com.lunch.persistencia.admin.ProductoSaludEntity;
import co.com.lunch.persistencia.cliente.FavoritoItemEntity;
import co.com.lunch.persistencia.cliente.ListaEntity;
import co.com.lunch.persistencia.cliente.NinoEntity;
import co.com.lunch.persistencia.cliente.NumeroLoncheraEntity;
import co.com.lunch.persistencia.cliente.PadreEntity;
import co.com.lunch.persistencia.cliente.PedidoEntity;
import co.com.lunch.persistencia.cliente.TipoLoncheraEntity;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
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
     *
     * @param padre
     * @return
     */
    @WebMethod(operationName = "ninosPorPadre")
    public ArrayList<NinoEntity> ninosPorPadre(@WebParam(name = "padre") PadreEntity padre) {
        ArrayList<NinoEntity> listaRet = null;
        try (NinoLogic ninoLogic = new NinoLogic()) {
            listaRet = ninoLogic.ninosPorPadre(padre);
        } catch (Exception e) {
            System.out.println("Error en el Webservice de ninosPorPadre");
        }
        return listaRet;
    }

    /**
     * Método que trae las loncheras favoritas por padre
     *
     * @param idPadre
     * @return
     */
    @WebMethod(operationName = "listaFavoritasPorPadre")
    public ArrayList<NumeroLoncheraEntity> listaFavoritasPorPadre(@WebParam(name = "padre") Integer idPadre) {
        ArrayList<NumeroLoncheraEntity> listaRet = null;
        try (NumeroLoncheraLogic numeroLoncheraLogic = new NumeroLoncheraLogic()) {
            listaRet = numeroLoncheraLogic.favoritosPorPadre(idPadre);
        } catch (Exception e) {
            System.out.println("Error en el Web Service de Favoritos por padre");
        }
        return listaRet;
    }

    /**
     * Método que trae los productos dentro se una lonchera favorita
     *
     * @param idLonchera
     * @return
     */
    @WebMethod(operationName = "favoritosPorLonchera")
    public ArrayList<FavoritoItemEntity> favoritosPorLonchera(@WebParam(name = "lonchera") Integer idLonchera) {
        ArrayList<FavoritoItemEntity> listaRet = null;
        try (FavoritoItemLogic favoritoItemLogic = new FavoritoItemLogic()) {
            listaRet = favoritoItemLogic.favoritoPorLonchera(idLonchera);
        } catch (Exception e) {
            System.out.println("Error en el Web Service de favoritosPorLonchera");
        }
        return listaRet;
    }

    /**
     * Método que permite ingresar varios items favoritos
     *
     * @param ingresa
     * @return
     */
    @WebMethod(operationName = "ingresaFavoritos")
    public boolean ingresaFavoritos(@WebParam(name = "lista") ArrayList<FavoritoItemEntity> ingresa) {
        boolean retorna = false;
        try (FavoritoItemLogic favoritoItemLogic = new FavoritoItemLogic()) {
            retorna = favoritoItemLogic.ingresaFavoritos(ingresa);

        } catch (Exception e) {
            System.out.println("Error en el web service de ingresaFavoritos");
        }
        return retorna;
    }

    /**
     * Método que permitirá ingresar un niño de un padre
     */
    @WebMethod(operationName = "ingresaNino")
    public boolean ingresaNino(@WebParam(name = "nino") NinoEntity nino) {
        boolean retorna = false;
        try (NinoLogic ninoLogic = new NinoLogic()) {
            if (ninoLogic.ingresaNino(nino).getIdNino() != null) {
                retorna = true;
            } else {
                System.out.println("ERROR en el guardadado del nino, está sin ID");
            }
        } catch (Exception e) {
            System.out.println("Error en el Web service de ingresaNino: " + e);
        }
        return retorna;
    }

    /**
     * Método que permite eliminar una lonchera favorita de un padre
     *
     * @param idL
     * @param idP
     * @return
     */
    @WebMethod(operationName = "EliminaFavorito")
    public boolean eliminaFavorito(@WebParam(name = "idLonchera") Integer idL, @WebParam(name = "idPadre") Integer idP) {
        boolean retorna = false;
        try (NumeroLoncheraLogic numeroLoncheraLogic = new NumeroLoncheraLogic()) {

            retorna = numeroLoncheraLogic.eliminaLoncheraFav(idL, idP);
            retorna = numeroLoncheraLogic.eliminaNlonchera(idL, idP);
        } catch (Exception e) {
            System.out.println("Error en el Web service de EliminaFavorito: " + e);
            retorna = false;
        }
        return retorna;
    }

    /**
     * Métdodo que permite ingresar una lonchera favorita nueva
     *
     * @param nlon
     * @return
     */
    @WebMethod(operationName = "IngresaFavorito")
    public NumeroLoncheraEntity ingresaFavorito(@WebParam(name = "Lonchera") NumeroLoncheraEntity nlon) {
        NumeroLoncheraEntity retorna = null;
        try (NumeroLoncheraLogic numeroLoncheraLogic = new NumeroLoncheraLogic()) {
            retorna = numeroLoncheraLogic.ingresaFavorita(nlon);

        } catch (Exception e) {
            System.out.println("Error en el Web service de IngresaFavoritos: " + e);
        }
       

        return retorna;
    }
    
    /**
     * Método que trae todos los items de los favoritos
     * @return 
     */
    @WebMethod(operationName = "listaItemsFavoritos")
    public ArrayList<FavoritoItemEntity> listaItemsFavoritos(){
        ArrayList<FavoritoItemEntity> listaRet = null;
        try (FavoritoItemLogic favoritoItemLogic = new FavoritoItemLogic()) {
            listaRet = favoritoItemLogic.favoritosTodos();
        } catch (Exception e) {
            System.out.println("Error en el Web Service de favoritosPorLonchera: "+e);
        }
        return listaRet;
    }
    
    @WebMethod(operationName = "listaItemsFavoritosPorPadre")
    public ArrayList<FavoritoItemEntity> listaItemsFavoritosPorPadre(@WebParam(name = "idPadre")Integer idPadre){
        ArrayList<FavoritoItemEntity> listaRet = null;
        try (FavoritoItemLogic favoritoItemLogic = new FavoritoItemLogic()) {
            listaRet = favoritoItemLogic.favoritoPorLonchera(idPadre);
        } catch (Exception e) {
            System.out.println("Error en el Web Service de listaItemsFavoritosPorPadre: "+e);
        }
        return listaRet;
    }
    /**
     * Método que permite ingresar un padre nuevo
     * @param pad
     * @return 
     */
    @WebMethod(operationName = "ingresaPadre")
    public PadreEntity ingresaPadre(@WebParam(name = "Padre")PadreEntity pad){
        PadreEntity padre = null;
        System.out.println("fec: "+pad.getTerminoFecha());
        try (PadreLogic padreLogic=new PadreLogic()){
            
            padre=padreLogic.ingresaPadre(pad);
        } catch (Exception e) {
            System.out.println("Error en el Web Service de ingresaPadre; "+e);
        }
        return padre;
    }
    
    /**
     * Método que permite comprobar la existencia de un correo
     * @param email
     * @return 
     */
    @WebMethod(operationName = "validaEmail")
    public boolean validaEmail(@WebParam(name = "email")String email){
        boolean exx = false;
        try (PadreLogic padre = new PadreLogic()){
            exx = padre.compruebaEmail(email);
        } catch (Exception e) {
            System.out.println("Error en el Web Service comprueba Email");
        }
        return exx;
    }
    /**
     * Método que permite recuperar una contraseña de un correo
     * @param email
     * @return 
     */
    @WebMethod(operationName = "enviaRecuperacion")
    public boolean enviaRecuperacion (@WebParam(name = "email")String email){
        boolean enviado = false;
        try {
            EnviaCorreo2 enviaCorreo=new EnviaCorreo2();
            enviado=enviaCorreo.envia(email);
        } catch (Exception e) {
            System.out.println("error en el servicio web de recuperar");
        }
        return enviado;
    }
    
    /**
     * Método que permite actualizar un padre
     * @param padre
     * @return 
     */
    @WebMethod(operationName = "actualizaPadre")
    public PadreEntity actualizaPadre(@WebParam(name = "padre")PadreEntity padre){
        PadreEntity pad = null;
        try (PadreLogic padreLogic=new PadreLogic()){
            pad=padreLogic.actualizaPadre(padre);
        } catch (Exception e) {
            System.out.println("Error en el WebService actualizaPadre: "+e);
        }
        return pad;
    }
    
    /**
     * Método que permite ingresar un pedido
     * @param pedido
     * @return 
     */
    @WebMethod(operationName = "ingresaPedido")
    public PedidoEntity ingresaPedido(@WebParam(name = "Pedido")PedidoEntity pedido){
        PedidoEntity ret = null;
        try(PedidosLogic pedidosLogic=new PedidosLogic()){
            ret = pedidosLogic.ingresaPedido(pedido);
        }catch(Exception e){
            System.out.println("Error en el WebService ingresaPedido: "+e);
        }
        return ret;
    }
    
    /**
     * Método que permite ingresar un tipo de lonchera al pedido
     * @param tipo
     * @return 
     */
    @WebMethod(operationName = "ingresaTipoLonchera")
    public ArrayList<Integer> ingresaTipoLonchera(@WebParam(name = "TipoLonchera")ArrayList<TipoLoncheraEntity> tipo){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        try(TipoLoncheraLogic tipoLoncheraLogic=new TipoLoncheraLogic()){
            for(TipoLoncheraEntity elem: tipo){
                if(ret.add(tipoLoncheraLogic.ingresaTipoLonchera(elem))){
                    System.out.println("Tipo agregado: "+ret.get(ret.size()-1));
                }else{
                    System.out.println("Tipo No agregado: "+ret.get(ret.size()-1));
                }
            }
        }catch(Exception e){
            System.out.println("Error en el WebService ingresaTipoLonchera: "+e);
        }
        return ret;
    }
    
    /**
     * Método que permite ingresar una lista
     * @param lista
     * @return 
     */
    @WebMethod(operationName = "ingresaLista")
    public ListaEntity ingresaLista(@WebParam(name = "listaProds")ArrayList<ListaEntity> lista){
        ListaEntity ret = null;
        try(ListaLogic listaLogic=new ListaLogic()){
            for(ListaEntity item: lista){
                ret = listaLogic.ingresaLista(item);
            }
            
        }catch(Exception e){
            System.out.println("Error en el WebService ingresa ListaEntity: "+e);
        }
        return ret;
    }
    
    /**
     * Método que trae una lista de pedidos por padre
     * @param idPadre
     * @return 
     */
    @WebMethod(operationName = "pedidosPorPadre")
    public ArrayList<PedidoEntity> pedidosPorPadre(@WebParam(name = "idPadre")Integer idPadre){
        ArrayList<PedidoEntity> listaRet=new ArrayList<>();
        try(PedidosLogic pedidosLogic=new PedidosLogic()){
            listaRet=pedidosLogic.listaPedidos(idPadre);
        }catch(Exception e){
            System.out.println("Error en el WebService Pedidos por Padre");
        }
        return listaRet;
    }
    
    /**
     * Método que trae una lista de tipos por pedido
     * @param idPedido
     * @return 
     */
    @WebMethod(operationName = "tiposPorPedido")
    public ArrayList<TipoLoncheraEntity> tiposPorPedido(@WebParam(name = "idPedido")Integer idPedido){
        ArrayList<TipoLoncheraEntity> listaRet = new ArrayList<>();
        try(TipoLoncheraLogic tipoLoncheraLogic=new TipoLoncheraLogic()){
            listaRet=tipoLoncheraLogic.tiposPorPedido(idPedido);
        }catch(Exception e){
            System.out.println("Error en el web sevice tipos por pedido: "+e);
        }
        return listaRet;
    }
    
    /**
     * Método que permite recuperar una lista de productos de un tipo de lonchera
     * @param idTipo
     * @return 
     */
    @WebMethod(operationName = "productosPorTipo")
    public ArrayList<ListaEntity> productosPorTipo(@WebParam(name = "idTipo")Integer idTipo){
        ArrayList<ListaEntity> listaRet = new ArrayList<>();
        try(ListaLogic listaLogic= new ListaLogic()){
            listaRet=listaLogic.listaPorTipo(idTipo);
        }catch(Exception e){
            System.out.println("Error en el WebService productosPorTipo");
        }
        return listaRet;
    }
    
    /**
     * Método que permite cancelar un pedido
     * @param pedido
     * @return 
     */
    @WebMethod(operationName = "cancelaPedido")
    public boolean cancelaPedido(@WebParam(name = "pedido")PedidoEntity pedido){
        
        try(PedidosLogic pedidosLogic=new PedidosLogic()){
            System.out.println("Pedido: "+pedido.isCancelado());
            return pedidosLogic.cancelaPedido(pedido);
        }catch(Exception e){
            System.out.println("Error Web service cancela Pedido: "+e);
            System.out.println("Pedido: "+pedido);
            return false;
        }
    }
    
    /**
     * Métodoque permite ingresar una busqueda de un usuario
     * @param idPadre
     * @param busqueda 
     */
    @WebMethod(operationName = "ingresaBusqueda")
    public void ingresaBusqueda(@WebParam(name = "idPadre")Integer idPadre, @WebParam(name = "texto")String busqueda){
        try(BusquedasLogic busquedasLogic=new BusquedasLogic()){
            busquedasLogic.ingresaBusqueda(idPadre, busqueda);
        }catch(Exception e){
            System.out.println("Error Web Service ingresaBusqueda");
        }
    }
    
    /**
     * Método que recibe una lista de fechas y devuelve productos nuevos
     * @param listaIds
     * @param listaIds
     * @param lista
     * @return 
     */
    @WebMethod(operationName = "productosNuevos")
    public ArrayList<ProductoEntity> productosNuevos(@WebParam(name = "ids")ArrayList<Integer> listaIds, @WebParam(name = "fecha")ArrayList<String> listaFecha){
        ArrayList<ProductoEntity> retorna = new ArrayList<>();
        HashMap<Integer, Date> lista = new HashMap<>();
        int p = 0;
        for(Integer id: listaIds){
            //System.out.println("FF: "+listaFecha.get(p));
            
            lista.put(id, convierteFecha(listaFecha.get(p)));
            p ++;   
        }
        System.out.println("Lista: "+ lista.size());
        try(ProductoLogic productoLogic=new ProductoLogic()){
            retorna=productoLogic.listaProductosNuevos(lista);
        }catch(Exception e){
            System.out.println("Error en web service productosNuevos: "+e);
        }
        return retorna;
    }
    
    /**
     * Método que recibe una lista de fechas y devuelve productos Saludables nuevos
     * @param listaIds
     * @param listaFecha
     * @return 
     */
    @WebMethod(operationName = "productosSNuevos")
    public ArrayList<ProductoSaludEntity> productosSNuevos(@WebParam(name = "ids")ArrayList<Integer> listaIds, @WebParam(name = "fecha")ArrayList<String> listaFecha){
        ArrayList<ProductoSaludEntity> retorna = null;
        HashMap<Integer, Date> lista = new HashMap<>();
        int p = 0;
        for(Integer id: listaIds){
            lista.put(id, convierteFecha(listaFecha.get(p)));
            p ++;   
        }
        try(ProductoSaludLogic productoSaludLogic = new ProductoSaludLogic()){
            retorna = productoSaludLogic.productosSNuevos(lista);
        }catch(Exception e){
            System.out.println("Error en web service productosSaludalesNuevos: "+e);
        }
        return retorna;
    }
    
    private Date convierteFecha(String entrada){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ZZ");
        try {
            Date fecha = df.parse(entrada);
            System.out.println("Fecha: "+fecha);
            return fecha;
        } catch (ParseException ex) {
            Logger.getLogger(clienteEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
}
