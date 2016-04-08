/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.test;

import co.com.lunch.logic.admin.InformacionNutricionalLogic;
import co.com.lunch.logic.admin.ProductoLogic;
import co.com.lunch.logic.admin.TipoInformacionLogic;
import co.com.lunch.persistencia.admin.InformacionNutricionalEntity;
import co.com.lunch.persistencia.admin.ProductoEntity;
import co.com.lunch.persistencia.admin.TipoInformacionEntity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author oscarramirez
 */
@ManagedBean(name = "test")
@ViewScoped
public class subida implements Serializable {

    private UploadedFile file;
    private ProductoEntity producto;
    private String ruta;
    private String nombre;
    private String valor;
    private String tipo;
    private TipoInformacionEntity tipoInfo;
    private ArrayList<TipoInformacionEntity> listaTipo;
    private String cantidad;
    private InformacionNutricionalEntity objetoInformacion;
    private ArrayList<InformacionNutricionalEntity> lista;
    private ArrayList<TipoInformacionEntity> listaFiltro;
    private HashMap<String, Integer> listaMenu;

    public ArrayList<TipoInformacionEntity> getListaTipo() {
        return listaTipo;
    }

    public void setListaTipo(ArrayList<TipoInformacionEntity> listaTipo) {
        this.listaTipo = listaTipo;
    }
    
    public TipoInformacionEntity getTipoInfo() {
        return tipoInfo;
    }

    public void setTipoInfo(TipoInformacionEntity tipo) {
        this.tipoInfo = tipo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public InformacionNutricionalEntity getObjetoInformacion() {
        return objetoInformacion;
    }

    public void setObjetoInformacion(InformacionNutricionalEntity objetoInformacion) {
        this.objetoInformacion = objetoInformacion;
    }

    public ArrayList<InformacionNutricionalEntity> getLista() {
        return lista;
    }

    public void setLista(ArrayList<InformacionNutricionalEntity> lista) {
        this.lista = lista;
    }

    public ArrayList<TipoInformacionEntity> getListaFiltro() {
        return listaFiltro;
    }

    public void setListaFiltro(ArrayList<TipoInformacionEntity> listaFiltro) {
        this.listaFiltro = listaFiltro;
    }

    public HashMap<String, Integer> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(HashMap<String, Integer> listaMenu) {
        this.listaMenu = listaMenu;
    }
    
    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

  
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    @PostConstruct
    public void init(){
        
        //System.out.println("construye");
        limpia();
        listaMenu=new HashMap<String, Integer>();
        consulta();
    }
    
    public void seteaNombres(){
        System.out.println("nombre sin objeto: "+nombre+" tipo: "+tipo);
        producto.setNombre(nombre);
        producto.setPrecio(valor);
        producto.setTipo(Integer.parseInt(tipo));
        RequestContext.getCurrentInstance().execute("PF('subir').show()");
    }
    
    public void almacena() throws IOException{
        
            producto.setImagen(ruta);
            System.out.println("nombre: "+producto.getNombre()+" precio: "+producto.getPrecio());
            
            ProductoLogic productoLogic = new ProductoLogic();
            FacesMessage message;
            if ((producto=productoLogic.ingresaProducto(producto)) == null) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Inserción", null);
                System.out.println("Archivo NO Guardado");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción Correcta", null);
                System.out.println("Archivo Guardado");
                
                for(InformacionNutricionalEntity info:lista){
                    info.setItem(producto);
                    
                }
                
                
            }
            ingresaInformacionNutricional(lista);
            FacesContext.getCurrentInstance().addMessage(null, message);
            RequestContext.getCurrentInstance().execute("PF('subir').hide()");
    }
    
    public void subriArchivo(FileUploadEvent event) {
        if (event.getFile().getSize() > 0) {
            try {
                producto.setNombreImagen(event.getFile().getFileName());
                alamcenarArchivo(event.getFile().getFileName(), event.getFile().getInputstream());
                
            } catch (Exception iOException) {
                System.out.println("ERROR DE SUBIDA: "+iOException.getMessage());
            }
        }
    }


    private void alamcenarArchivo(String fileName, InputStream inputstream) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("co.com.lunch.config.RUTAS");
            ruta = rb.getString("IMAGENES").trim();
            ruta +=fileName;
            try (OutputStream out = new FileOutputStream(new File(ruta))) {
                int read = 0;
                byte[] bytes = new byte[2048];
                while ((read = inputstream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                inputstream.close();
                out.flush();
            }
            almacena();
            
            

        } catch (Exception e) {
            System.out.println("ERROR: "+e);
        }
    }
    
    public void editar(RowEditEvent event){
        objetoInformacion=(InformacionNutricionalEntity)event.getObject();
        InformacionNutricionalLogic marcaLogic=new InformacionNutricionalLogic();
        FacesMessage message;
        if(marcaLogic.actualizaInformacionNutricional(objetoInformacion)==null){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Actualización", null);
            System.out.println("Informacion Nutricional NO Actuazlizado");
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización Correcta", null);
            System.out.println("Informacion Nutricional Actualizado");
            
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        limpia();
        
    }
    
    public void eliminar(){
        if(objetoInformacion==null){
            System.out.println("Objeto Nulo");
        }else{
            InformacionNutricionalLogic marcaLogic=new InformacionNutricionalLogic();
            FacesMessage message;
            if(marcaLogic.eliminaInformacionNutricional(objetoInformacion)==null){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Eliminación", null);
                System.out.println("Informacion Nutricional NO Eliminado");
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminación Correcta", null);
                System.out.println("Informacion Nutricional Eliminada");
                lista.remove(objetoInformacion);
            }
            FacesContext.getCurrentInstance().addMessage(null, message);
            limpia();
        }
    }
    
    public void limpia(){
        objetoInformacion=new InformacionNutricionalEntity();
        tipoInfo=new TipoInformacionEntity();
        producto=new ProductoEntity();
        //
    }

    public void anadirLista(){
        //System.out.println("añade");
            if(lista==null){
                lista=new ArrayList<>();
                //System.out.println("se reinicia");
            }else{
                lista.stream().forEach((tt) -> {
                    //System.out.println("- "+tt.getTipo().getTipo()+" - "+tt.getValor());
                });
            }
            TipoInformacionLogic tipoInformacionLogic=new TipoInformacionLogic();
            listaTipo=tipoInformacionLogic.listaTipoInformacion();
            TipoInformacionEntity tipoRet=new TipoInformacionEntity();
            for(TipoInformacionEntity tipoP: listaTipo){
                
                if(tipoP.getId()==tipoInfo.getId()){
                    tipoRet=tipoP;
                }
            }
            
            objetoInformacion.setTipo(tipoRet);
            objetoInformacion.setValor(Double.parseDouble(cantidad));
            anadeLista(objetoInformacion);
            limpia();
    }
    
    public void anadeLista(InformacionNutricionalEntity tipo){
        lista.add(tipo);
    }
    
    public boolean ingresaInformacionNutricional(ArrayList<InformacionNutricionalEntity> lista){
        boolean retorna=false;
        InformacionNutricionalLogic informacionNutricionalLogic=new InformacionNutricionalLogic();
        for(InformacionNutricionalEntity info: lista){
            if(informacionNutricionalLogic.ingresaInformacionNutricional(info)==null){
                System.out.println("Información no ingresada");
            }else{
                System.out.println("Información ingresada");
            }
        }
        return retorna;
    }
    
    public void consulta(){
        try {
            
            TipoInformacionLogic tipoInformacionLogic=new TipoInformacionLogic();
            ArrayList<TipoInformacionEntity>listaTipos=tipoInformacionLogic.listaTipoInformacion();
            for(TipoInformacionEntity obj: listaTipos){
                listaMenu.put(obj.getTipo(), obj.getId());
            }
        } catch (Exception e) {
        }
    }
}
