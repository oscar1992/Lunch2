/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.enpoint;

import co.com.lunch.logic.admin.CajaPredeterminadaLogic;
import co.com.lunch.logic.admin.CategoriaLogic;
import co.com.lunch.logic.admin.CombinacionesLogic;
import co.com.lunch.logic.admin.EnvioLogic;
import co.com.lunch.logic.admin.FechaEntregaLogic;
import co.com.lunch.logic.admin.GrupoAlimenticioLogic;
import co.com.lunch.logic.admin.GrupoAlimenticioProductoLogic;
import co.com.lunch.logic.admin.HorasEntregaLogic;
import co.com.lunch.logic.admin.InformacionNutricionalLogic;
import co.com.lunch.logic.admin.ItemLogic;
import co.com.lunch.logic.admin.MarcaLogic;
import co.com.lunch.logic.admin.ProductoLogic;
import co.com.lunch.logic.admin.ProductoSaludLogic;
import co.com.lunch.logic.admin.SaludLogic;
import co.com.lunch.logic.admin.TagsLogic;
import co.com.lunch.logic.admin.TipoInformacionLogic;
import co.com.lunch.logic.cliente.PadreLogic;
import co.com.lunch.persistencia.admin.CajaPredeterminadaEntity;
import co.com.lunch.persistencia.admin.CategoriaEntity;
import co.com.lunch.persistencia.admin.CombinacionesEntity;
import co.com.lunch.persistencia.admin.EnvioEntity;
import co.com.lunch.persistencia.admin.FechaEntregaEntity;
import co.com.lunch.persistencia.admin.GrupoAlimenticioEntity;
import co.com.lunch.persistencia.admin.GrupoAlimenticioProductoEntity;
import co.com.lunch.persistencia.admin.HorasEntregaEntity;
import co.com.lunch.persistencia.admin.InformacionNutricionalEntity;
import co.com.lunch.persistencia.admin.ItemEntity;
import co.com.lunch.persistencia.admin.MarcaEntity;
import co.com.lunch.persistencia.admin.ProductoEntity;
import co.com.lunch.persistencia.admin.ProductoSaludEntity;
import co.com.lunch.persistencia.admin.SaludEntity;
import co.com.lunch.persistencia.admin.TagsEntity;
import co.com.lunch.persistencia.admin.TipoInformacionEntity;
import co.com.lunch.persistencia.cliente.PadreEntity;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author oscarramirez
 */
@WebService(serviceName = "adminEndpoint")
public class adminEndpoint {

    
    /**
     * Método que permite insertar un registro de la Información nutricional
     * @param info
     * @return 
     */
    @WebMethod(operationName = "ingresaInformacionNutricionalEntity")
    public InformacionNutricionalEntity ingresaInformacionNutricionalEntity(@WebParam(name = "info")InformacionNutricionalEntity info){
        InformacionNutricionalEntity infoRetorna=null;
        try(InformacionNutricionalLogic logic=new InformacionNutricionalLogic()){
            infoRetorna=logic.ingresaInformacionNutricional(info);
        }catch(Exception e){
            System.out.println("ERROR en la inserción del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que permite actualizar un registro de la información Nutricional
     * @param info
     * @return 
     */
    @WebMethod(operationName = "actualizaInformacionNutricionalEntity")
    public InformacionNutricionalEntity actualizaInformacionNutricionalEntity(@WebParam(name = "info")InformacionNutricionalEntity info){
        InformacionNutricionalEntity infoRetorna=null;
        try(InformacionNutricionalLogic logic=new InformacionNutricionalLogic()){
            infoRetorna=logic.actualizaInformacionNutricional(info);
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que trae toda la lista de las informaciones nutricionales
     * @return 
     */
    @WebMethod(operationName = "listaInformacionNutricionalEntity")
    public ArrayList<InformacionNutricionalEntity> listaInformacionNutricionalEntity(){
        ArrayList<InformacionNutricionalEntity> infoRetorna=null;
        try(InformacionNutricionalLogic logic=new InformacionNutricionalLogic()){
            infoRetorna=logic.listaInformacionNutricional();
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice listaInformación");
        }
        return infoRetorna;
    }
	
	/**
     * Método que permite insertar un registro de la Caja Predeterminada
     * @param info
     * @return 
     */
    @WebMethod(operationName = "ingresaCajaPredeterminadaEntity")
    public CajaPredeterminadaEntity ingresaCajaPredeterminadaEntity(@WebParam(name = "info")CajaPredeterminadaEntity info){
        CajaPredeterminadaEntity infoRetorna=null;
        try(CajaPredeterminadaLogic logic=new CajaPredeterminadaLogic()){;
            infoRetorna=logic.ingresaCajaPredeterminada(info);
        }catch(Exception e){
            System.out.println("ERROR en la inserción del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que permite actualizar un registro de la Caja Predeterminada
     * @param info
     * @return 
     */
    @WebMethod(operationName = "actualizaCajaPredeterminadaEntity")
    public CajaPredeterminadaEntity actualizaCajaPredeterminadaEntity(@WebParam(name = "info")CajaPredeterminadaEntity info){
        CajaPredeterminadaEntity infoRetorna=null;
        try(CajaPredeterminadaLogic logic=new CajaPredeterminadaLogic()){
            infoRetorna=logic.actualizaCajaPredeterminada(info);
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que trae toda la lista de las informaciones nutricionales
     * @return 
     */
    @WebMethod(operationName = "listaCajaPredeterminadaEntity")
    public ArrayList<CajaPredeterminadaEntity> listaCajaPredeterminadaEntity(){
        ArrayList<CajaPredeterminadaEntity> infoRetorna=null;
        try(CajaPredeterminadaLogic logic=new CajaPredeterminadaLogic()){
            infoRetorna=logic.listaCajaPredeterminada();
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
	
	/**
     * Método que permite insertar un registro de la Categoria
     * @param info
     * @return 
     */
    @WebMethod(operationName = "ingresaCategoriaEntity")
    public CategoriaEntity ingresaCategoriaEntity(@WebParam(name = "info")CategoriaEntity info){
        CategoriaEntity infoRetorna=null;
        try(CategoriaLogic logic=new CategoriaLogic()){
            infoRetorna=logic.ingresaCategoria(info);
        }catch(Exception e){
            System.out.println("ERROR en la inserción del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que permite actualizar un registro de la Categoria
     * @param info
     * @return 
     */
    @WebMethod(operationName = "actualizaCategoriaEntity")
    public CategoriaEntity actualizaCategoriaEntity(@WebParam(name = "info")CategoriaEntity info){
        CategoriaEntity infoRetorna=null;
        try(CategoriaLogic logic=new CategoriaLogic()){
            infoRetorna=logic.actualizaCategoria(info);
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que trae toda la lista de las informaciones nutricionales
     * @return 
     */
    @WebMethod(operationName = "listaCategoriaEntity")
    public ArrayList<CategoriaEntity> listaCategoriaEntity(){
        ArrayList<CategoriaEntity> infoRetorna=null;
        try(CategoriaLogic logic=new CategoriaLogic()){
            infoRetorna=logic.listaCategoria();
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
	/**
     * Método que permite insertar un registro de la Combinaciones
     * @param info
     * @return 
     */
    @WebMethod(operationName = "ingresaCombinacionesEntity")
    public CombinacionesEntity ingresaCombinacionesEntity(@WebParam(name = "info")CombinacionesEntity info){
        CombinacionesEntity infoRetorna=null;
        try(CombinacionesLogic logic=new CombinacionesLogic()){
            infoRetorna=logic.ingresaCombinaciones(info);
        }catch(Exception e){
            System.out.println("ERROR en la inserción del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que permite actualizar un registro de la Combinaciones
     * @param info
     * @return 
     */
    @WebMethod(operationName = "actualizaCombinacionesEntity")
    public CombinacionesEntity actualizaCombinacionesEntity(@WebParam(name = "info")CombinacionesEntity info){
        CombinacionesEntity infoRetorna=null;
        try(CombinacionesLogic logic=new CombinacionesLogic()){
            infoRetorna=logic.actualizaCombinaciones(info);
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que trae toda la lista de las informaciones nutricionales
     * @return 
     */
    @WebMethod(operationName = "listaCombinacionesEntity")
    public ArrayList<CombinacionesEntity> listaCombinacionesEntity(){
        ArrayList<CombinacionesEntity> infoRetorna=null;
        try(CombinacionesLogic logic=new CombinacionesLogic()){
            infoRetorna=logic.listaCombinaciones();
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
	
	/**
     * Método que permite insertar un registro de la Grupo Alimenticio
     * @param info
     * @return 
     */
    @WebMethod(operationName = "ingresaGrupoAlimenticioEntity")
    public GrupoAlimenticioEntity ingresaGrupoAlimenticioEntity(@WebParam(name = "info")GrupoAlimenticioEntity info){
        GrupoAlimenticioEntity infoRetorna=null;
        try(GrupoAlimenticioLogic logic=new GrupoAlimenticioLogic()){
            infoRetorna=logic.ingresaGrupoAlimenticio(info);
        }catch(Exception e){
            System.out.println("ERROR en la inserción del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que permite actualizar un registro de la Grupo Alimenticio
     * @param info
     * @return 
     */
    @WebMethod(operationName = "actualizaGrupoAlimenticioEntity")
    public GrupoAlimenticioEntity actualizaGrupoAlimenticioEntity(@WebParam(name = "info")GrupoAlimenticioEntity info){
        GrupoAlimenticioEntity infoRetorna=null;
        try(GrupoAlimenticioLogic logic=new GrupoAlimenticioLogic()){
            infoRetorna=logic.actualizaGrupoAlimenticio(info);
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que trae toda la lista de las informaciones nutricionales
     * @return 
     */
    @WebMethod(operationName = "listaGrupoAlimenticioEntity")
    public ArrayList<GrupoAlimenticioEntity> listaGrupoAlimenticioEntity(){
        ArrayList<GrupoAlimenticioEntity> infoRetorna=null;
        try(GrupoAlimenticioLogic logic=new GrupoAlimenticioLogic()){
            infoRetorna=logic.listaGrupoAlimenticio();
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
	/**
     * Método que permite insertar un registro de la Grupo_Alimenticio_Producto
     * @param info
     * @return 
     */
    @WebMethod(operationName = "ingresaGrupoAlimenticioProductoEntity")
    public GrupoAlimenticioProductoEntity ingresaGrupoAlimenticioProductoEntity(@WebParam(name = "info")GrupoAlimenticioProductoEntity info){
        GrupoAlimenticioProductoEntity infoRetorna=null;
        try(GrupoAlimenticioProductoLogic logic=new GrupoAlimenticioProductoLogic()){
            infoRetorna=logic.ingresaGrupoAlimenticioProducto(info);
        }catch(Exception e){
            System.out.println("ERROR en la inserción del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que permite actualizar un registro de la Grupo_Alimenticio_Producto
     * @param info
     * @return 
     */
    @WebMethod(operationName = "actualizaGrupoAlimenticioProductoEntity")
    public GrupoAlimenticioProductoEntity actualizaGrupoAlimenticioProductoEntity(@WebParam(name = "info")GrupoAlimenticioProductoEntity info){
        GrupoAlimenticioProductoEntity infoRetorna=null;
        try(GrupoAlimenticioProductoLogic logic=new GrupoAlimenticioProductoLogic()){
            infoRetorna=logic.actualizaGrupoAlimenticioProducto(info);
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que trae toda la lista de las informaciones nutricionales
     * @return 
     */
    @WebMethod(operationName = "listaGrupoAlimenticioProductoEntity")
    public ArrayList<GrupoAlimenticioProductoEntity> listaGrupoAlimenticioProductoEntity(){
        ArrayList<GrupoAlimenticioProductoEntity> infoRetorna=null;
        try(GrupoAlimenticioProductoLogic logic=new GrupoAlimenticioProductoLogic()){
            infoRetorna=logic.listaGrupoAlimenticioProducto();
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
	
	/**
     * Método que permite insertar un registro de la Item
     * @param info
     * @return 
     */
    @WebMethod(operationName = "ingresaItemEntity")
    public ItemEntity ingresaItemEntity(@WebParam(name = "info")ItemEntity info){
        ItemEntity infoRetorna=null;
        try(ItemLogic logic=new ItemLogic()){
            infoRetorna=logic.ingresaItem(info);
        }catch(Exception e){
            System.out.println("ERROR en la inserción del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que permite actualizar un registro de la Item
     * @param info
     * @return 
     */
    @WebMethod(operationName = "actualizaItemEntity")
    public ItemEntity actualizaItemEntity(@WebParam(name = "info")ItemEntity info){
        ItemEntity infoRetorna=null;
        try(ItemLogic logic=new ItemLogic()){
            infoRetorna=logic.actualizaItem(info);
        }catch(Exception e){
            System.out.println("ERROR en la actualización del Item");
        }
        return infoRetorna;
    }
    /**
     * Método que trae toda la lista de las informaciones nutricionales
     * @return 
     */
    @WebMethod(operationName = "listaItemEntity")
    public ArrayList<ItemEntity> listaItemEntity(){
        ArrayList<ItemEntity> infoRetorna=null;
        try(ItemLogic logic=new ItemLogic()){
            infoRetorna=logic.listaItem();
        }catch(Exception e){
            System.out.println("ERROR en la lista del item");
        }
        return infoRetorna;
    }
	
	/**
     * Método que permite insertar un registro de la Marca
     * @param info
     * @return 
     */
    @WebMethod(operationName = "ingresaMarcaEntity")
    public MarcaEntity ingresaMarcaEntity(@WebParam(name = "info")MarcaEntity info){
        MarcaEntity infoRetorna=null;
        try(MarcaLogic logic=new MarcaLogic()){
            infoRetorna=logic.ingresaMarca(info);
        }catch(Exception e){
            System.out.println("ERROR en la inserción del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que permite actualizar un registro de la Marca
     * @param info
     * @return 
     */
    @WebMethod(operationName = "actualizaMarcaEntity")
    public MarcaEntity actualizaMarcaEntity(@WebParam(name = "info")MarcaEntity info){
        MarcaEntity infoRetorna=null;
        try{
            MarcaLogic logic=new MarcaLogic();
            infoRetorna=logic.actualizaMarca(info);
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que trae toda la lista de las informaciones nutricionales
     * @return 
     */
    @WebMethod(operationName = "listaMarcaEntity")
    public ArrayList<MarcaEntity> listaMarcaEntity(){
        ArrayList<MarcaEntity> infoRetorna=null;
        try(MarcaLogic logic=new MarcaLogic()){
            infoRetorna=logic.listaMarca();
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
	
    /**
     * Método que permite insertar un registro de la Producto
     * @param info
     * @return 
     */
    @WebMethod(operationName = "ingresaProductoEntity")
    public ProductoEntity ingresaProductoEntity(@WebParam(name = "info")ProductoEntity info){
        ProductoEntity infoRetorna=null;
        try(ProductoLogic logic=new ProductoLogic()){
            infoRetorna=logic.ingresaProducto(info);
        }catch(Exception e){
            System.out.println("ERROR en la inserción del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que permite actualizar un registro de la Producto
     * @param info
     * @return 
     */
    @WebMethod(operationName = "actualizaProductoEntity")
    public ProductoEntity actualizaProductoEntity(@WebParam(name = "info")ProductoEntity info){
        ProductoEntity infoRetorna=null;
        try(ProductoLogic logic=new ProductoLogic()){
            infoRetorna=logic.actualizaProducto(info);
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que trae toda la lista de las informaciones nutricionales
     * @return 
     */
    @WebMethod(operationName = "listaProductoEntity")
    public ArrayList<ProductoEntity> listaProductoEntity(){
        ArrayList<ProductoEntity> infoRetorna=null;
        try(ProductoLogic logic=new ProductoLogic()){
            infoRetorna=logic.listaProducto();
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que permite insertar un registro de la Producto
     * @param info
     * @return 
     */
    @WebMethod(operationName = "ingresaTipoInformacionEntity")
    public TipoInformacionEntity ingresaTipoInformacionEntity(@WebParam(name = "info")TipoInformacionEntity info){
        TipoInformacionEntity infoRetorna=null;
        try(TipoInformacionLogic logic=new TipoInformacionLogic()){
            infoRetorna=logic.ingresaTipoInformacion(info);
        }catch(Exception e){
            System.out.println("ERROR en la inserción del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que permite actualizar un registro de la Producto
     * @param info
     * @return 
     */
    @WebMethod(operationName = "actualizaTipoInformacionEntity")
    public TipoInformacionEntity actualizaTipoInformacionEntity(@WebParam(name = "info")TipoInformacionEntity info){
        TipoInformacionEntity infoRetorna=null;
        try(TipoInformacionLogic logic=new TipoInformacionLogic()){
            infoRetorna=logic.actualizaTipoInformacion(info);
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
    /**
     * Método que trae toda la lista de las informaciones nutricionales
     * @return 
     */
    @WebMethod(operationName = "listaTipoInformacionEntity")
    public ArrayList<TipoInformacionEntity> listaTipoInformacionEntity(){
        ArrayList<TipoInformacionEntity> infoRetorna=null;
        try(TipoInformacionLogic logic=new TipoInformacionLogic()){
            infoRetorna=logic.listaTipoInformacion();
        }catch(Exception e){
            System.out.println("ERROR en la actualización del webservice");
        }
        return infoRetorna;
    }
    
    @WebMethod(operationName = "infoPorProducto")
    public ArrayList<InformacionNutricionalEntity> infoPorProducto(@WebParam(name = "idProducto")int idProd){
        ArrayList<InformacionNutricionalEntity> infoRetorna=null;
        try(InformacionNutricionalLogic logic = new InformacionNutricionalLogic()){
            ProductoEntity producto=null;
            try(ProductoLogic logic2=new ProductoLogic()){
                producto=logic2.productoPorId(idProd);
                infoRetorna=logic.infoPorProducto(producto);
            }catch(Exception e2){
                System.out.println("Error en la carga del producto: "+e2);
            }
        }catch(Exception e){
            System.out.println("Error en la carga de Logic de Información nutriciona: "+e);
        }
        return infoRetorna;
    }
    
    //Método que permite hacer login al sistema
    @WebMethod(operationName = "login")
    public PadreEntity login(@WebParam(name = "email")String email, @WebParam(name = "pass") String pass){
        PadreEntity ret = null;
        try(PadreLogic padreLogic = new PadreLogic()){
            ret = padreLogic.existePadre(email, pass);
        }catch(Exception e){
            System.out.println("Error en el acceso del login");
        }
        return ret;
    }
    
    //Método que permite poner tags a un proucto
    @WebMethod(operationName = "insertaTagsPorProducto")
    public boolean insertaTagsPorProducto(@WebParam(name = "lista")ArrayList<TagsEntity> lista,@WebParam(name = "producto") Integer idProd){
        boolean retorna = false;
        
        try(TagsLogic tag = new TagsLogic()){
            retorna=tag.insertaTags(lista, idProd);
        }catch(Exception e ){
            System.out.println("Error de web service (insertaTagsPorProducto)"+e);
        }
        return retorna;
    }
    
    //Método que trae la lista de tags por producto
    @WebMethod(operationName = "tagsPorProducto")
    public ArrayList<TagsEntity> TagsPorProdcuto(@WebParam(name="idProducto")Integer idProd){
        ArrayList<TagsEntity> lista=null;
        try(TagsLogic tagsLogic=new TagsLogic()){
            lista = tagsLogic.TagsPorProducto(idProd);
        }catch(Exception e){
            System.out.println("Error de web service (tagsPorProducto)"+e);
        }
        return lista;
    }
    
    /**
     * Método que trae la lista de tags
     * @return 
     */
    @WebMethod(operationName = "listaTags")
    public ArrayList<TagsEntity> listaTags(){
        ArrayList<TagsEntity> lista = null;
        try(TagsLogic tagsLogic=new TagsLogic()){
            lista = tagsLogic.listaTags();
        }catch(Exception e){
            System.out.println("Error de web service (listaTags)");
        }
        return lista;
    }
    
    /**
     * Método que permite ingresar una caja saludable nueva
     * @param salud
     * @return 
     */
    @WebMethod(operationName = "ingresaSalud")
    public SaludEntity ingresaSalud(@WebParam(name = "Salud") SaludEntity salud){
        SaludEntity retorna = null;
        try(SaludLogic saludLogic=new SaludLogic()){
            retorna = saludLogic.ingresaSalud(salud);
        }catch(Exception e){
            System.out.println("Error de web service (ingresaSalud)"+e);
        }
        return retorna;
    }
    /**
     * Método que permite cambiar una caja saludable
     * @param salud
     * @return 
     */
    @WebMethod(operationName = "actualizaSalud")
    public SaludEntity actualizaSalud(@WebParam(name = "Salud") SaludEntity salud){
        SaludEntity retorna = null;
        try(SaludLogic saludLogic=new SaludLogic()){
            retorna = saludLogic.actualizaSalud(salud);
        }catch(Exception e){
            System.out.println("Error de web service (actualizaSalud)"+e);
        }
        return retorna;
    }
    
    /**
     * Método que trae todas las cajas saludables
     * @return 
     */
    @WebMethod(operationName = "listaSalud")
    public ArrayList<SaludEntity> listaSalud(){
        ArrayList<SaludEntity> listaR= null;
        try(SaludLogic saludLogic=new SaludLogic()){
            listaR=saludLogic.listaItem();
        }catch(Exception e){
            System.out.println("Error de web service (listaSalud)"+e);
        }
        return listaR;
    }
    
    /**
     * Métoso que permite ingresar un producto a una caja saludable
     * @param prsa
     * @return 
     */
    @WebMethod(operationName = "ingresaProductoSalud")
    public ProductoSaludEntity ingresaProductoSalud(@WebParam(name = "prsa") ProductoSaludEntity prsa){
        ProductoSaludEntity retorna = null;
        try(ProductoSaludLogic productoSaludLogic=new ProductoSaludLogic()){
            retorna = productoSaludLogic.ingresaProductoSalud(prsa);
        }catch(Exception e){
            System.out.println("Error de web service (ingresaProductoSalud)"+e);
        }
        return retorna;
    }
    
    /**
     * Método que permite cambiar un producto de una caja saludable
     * @param prsa
     * @return 
     */
    @WebMethod(operationName = "actualizaProductoSalud")
    public ProductoSaludEntity actualizaProductoSalud(@WebParam(name = "prsa") ProductoSaludEntity prsa){
        ProductoSaludEntity retorna = null;
        try(ProductoSaludLogic productoSaludLogic=new ProductoSaludLogic()){
            retorna = productoSaludLogic.actualizaProductoSalud(prsa);
        }catch(Exception e){
            System.out.println("Error de web service (actualizaProductoSalud)"+e);
        }
        return retorna;
    }
    
    /**
     * Método que trae todas los productos de una caja saludable
     * @param salud
     * @return 
     */
    @WebMethod(operationName = "listaProductoSalud")
    public ArrayList<ProductoSaludEntity> listaProductoSalud(@WebParam(name = "Salud") SaludEntity salud){
        ArrayList<ProductoSaludEntity> listaR= null;
        try(ProductoSaludLogic productosaludLogic=new ProductoSaludLogic()){
            listaR=productosaludLogic.productoPorcaja(salud);
        }catch(Exception e){
            System.out.println("Error de web service (listaProductoSalud)"+e);
        }
        return listaR;
    }
    
    /**
     * Método que trae todos los items favosaludabkesritos
     * @return 
     */
    @WebMethod(operationName = "listaProductoSaludTodos")
    public  ArrayList<ProductoSaludEntity> listaFavoritos(){
        ArrayList<ProductoSaludEntity> listaR= null;
        try(ProductoSaludLogic productosaludLogic=new ProductoSaludLogic()){
            listaR=productosaludLogic.listaProductoSalud();
        }catch(Exception e){
            System.out.println("Error de web service (listaProductoSalud)"+e);
        }
        return listaR;
    }
    
    
    /**
     * Método que ingresa el valor del envio
     * @param envio
     * @return 
     */
    @WebMethod(operationName = "ingresaEnvio")
    public EnvioEntity ingresaEnvio(@WebParam(name = "EnvioO")EnvioEntity envio){
        EnvioEntity ret = null;
        try(EnvioLogic envioLogic = new EnvioLogic()){
            System.out.println("env: "+envio.getEnvio());
            ret=envioLogic.ingresaEnvio(envio);
        }catch(Exception e){
            System.out.println("Error en el webservice de ingresa envio: "+e);
        }
        return ret;
    }
    
    /**
     * Método que actualiza el valor del envio
     * @param envio
     * @return 
     */
    @WebMethod(operationName = "ActualizaEnvio")
    public EnvioEntity actualizaEnvio(@WebParam(name = "Envio")EnvioEntity envio){
        EnvioEntity ret = null;
        try(EnvioLogic envioLogic = new EnvioLogic()){
            ret=envioLogic.actualizaEnvio(envio);
        }catch(Exception e){
            System.out.println("Error en el webservice de actualiza envio");
        }
        return ret;
    }
    /**
     * Método que recupera el valor del envio
     * @return 
     */
    @WebMethod(operationName = "TraeEnvio")
    public EnvioEntity traeEnvio(){
        EnvioEntity ret = null;
        try(EnvioLogic envioLogic = new EnvioLogic()){
            ret=envioLogic.consultaEnvio();
        }catch(Exception e){
            System.out.println("Error en el webservice de trae envio");
        }
        return ret;
    }
    
    /**
     * Método que permite ingresar una fecha de entrega nueva
     * @param fecha
     * @return 
     */
    @WebMethod(operationName = "ingresaFechaEntrega")
    public FechaEntregaEntity ingresaFechaEntrega(@WebParam(name = "fecha") FechaEntregaEntity fecha){
        FechaEntregaEntity ret=null;
        try(FechaEntregaLogic fechaEntregaLogic = new FechaEntregaLogic()){
            ret=fechaEntregaLogic.ingresaFecha(fecha);
        }catch(Exception e){
            System.out.println("Error webService ingresaFechaEntrega");
        }
        return ret;
    }
    
    /**
     * Método que permite modificar una fecha de entrega
     * @param fecha
     * @return 
     */
    @WebMethod(operationName = "actualizaFechaEntrega")
    public FechaEntregaEntity actualizaFechaEntrega(@WebParam(name = "fecha") FechaEntregaEntity fecha){
        FechaEntregaEntity ret=null;
        try(FechaEntregaLogic fechaEntregaLogic = new FechaEntregaLogic()){
            ret=fechaEntregaLogic.actualizaFecha(fecha);
        }catch(Exception e){
            System.out.println("Error webService actualizaFechaEntrega");
        }
        return ret;
    }
    
    /**
     * Método que permite trear la lista de las fechas de entrega disponibles
     * @return 
     */
    @WebMethod(operationName = "listaFechaEntrega")
    public ArrayList<FechaEntregaEntity> listaFechaEntrega(){
        ArrayList<FechaEntregaEntity> ret=null;
        try(FechaEntregaLogic fechaEntregaLogic = new FechaEntregaLogic()){
            ret=fechaEntregaLogic.listaFechas();
        }catch(Exception e){
            System.out.println("Error webService listaFechaEntrega");
        }
        return ret;
    }
    
    /**
     * Método que permite ingresar una hora
     * @param hora
     * @return 
     */
    @WebMethod(operationName = "ingresaHora")
    public HorasEntregaEntity ingresaHora(@WebParam(name = "hora")HorasEntregaEntity hora){
        HorasEntregaEntity ret = null;
        try (HorasEntregaLogic horasEntregaLogic=new HorasEntregaLogic()){
            ret = horasEntregaLogic.ingresaHora(hora);
        } catch (Exception e) {
            System.out.println("Error en el webservice ingresa hora: "+e);
        }
        return  ret;
    }
    
    /**
     * Método que permite actualziar una hora
     * @param hora
     * @return 
     */
    @WebMethod(operationName = "actualizaHora")
    public HorasEntregaEntity actualizaHora(@WebParam(name = "hora")HorasEntregaEntity hora){
        HorasEntregaEntity ret = null;
        try (HorasEntregaLogic horasEntregaLogic=new HorasEntregaLogic()){
            ret = horasEntregaLogic.actualizaHora(hora);
        } catch (Exception e) {
            System.out.println("Error en el webservice actualiza hora: "+e);
        }
        return  ret;
    }
    
    /**
     * Método que trae una lista de horas
     * @return 
     */
    @WebMethod(operationName = "listaHora")
    public ArrayList<HorasEntregaEntity> listaHora(){
        ArrayList<HorasEntregaEntity> ret = null;
        try (HorasEntregaLogic horasEntregaLogic=new HorasEntregaLogic()){
            ret = horasEntregaLogic.listaHoras();
        } catch (Exception e) {
            System.out.println("Error en el webservice lista hora: "+e);
        }
        return  ret;
    }
    
    @WebMethod(operationName = "test")
    public int test(){
        return 58;
    }
}
