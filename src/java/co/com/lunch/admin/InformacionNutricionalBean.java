/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.admin;

import co.com.lunch.logic.admin.InformacionNutricionalLogic;
import co.com.lunch.logic.admin.TipoInformacionLogic;
import co.com.lunch.persistencia.admin.InformacionNutricionalEntity;
import co.com.lunch.persistencia.admin.ProductoEntity;
import co.com.lunch.persistencia.admin.TipoInformacionEntity;
import java.util.ArrayList;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author oscarramirez
 */
@ManagedBean(name = "InformacionNutricional")
public class InformacionNutricionalBean {
    
    private TipoInformacionEntity tipo;
    private ArrayList<TipoInformacionEntity> listaTipo;
    private String cantidad;
    private ProductoEntity producto;
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
    
    public TipoInformacionEntity getTipo() {
        return tipo;
    }

    public void setTipo(TipoInformacionEntity tipo) {
        this.tipo = tipo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
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
    
    @PostConstruct
    public void init(){
        System.out.println("construye");
        limpia();
        consulta();
    }
    
    public void insertarInformacion(){
        objetoInformacion.setTipo(tipo);
        objetoInformacion.setItem(producto);
        objetoInformacion.setValor(Double.parseDouble(cantidad));
        InformacionNutricionalLogic marcaLogic=new InformacionNutricionalLogic();
        FacesMessage message;
        if(marcaLogic.ingresaInformacionNutricional(objetoInformacion)==null){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Inserción", null);
            System.out.println("Informacion Nutricional NO Guardado");
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción Correcta", null);
            System.out.println("Informacion Nutricional Guardado");
            lista.add(objetoInformacion);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        //consulta();
        
        limpia();
    }
    
    public void consulta(){
        try {
            InformacionNutricionalLogic marcaLogic=new InformacionNutricionalLogic();
            lista=marcaLogic.listaInformacionNutricional();
            TipoInformacionLogic tipoInformacionLogic=new TipoInformacionLogic();
            ArrayList<TipoInformacionEntity>listaTipos=tipoInformacionLogic.listaTipoInformacion();
            for(TipoInformacionEntity obj: listaTipos){
                listaMenu.put(obj.getTipo(), obj.getId());
            }
        } catch (Exception e) {
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
        tipo=new TipoInformacionEntity();
        producto=new ProductoEntity();
        listaMenu=new HashMap<String, Integer>();
    }

    public void anadirLista(){
        System.out.println("añade");
        
            if(lista==null){
                lista=new ArrayList<>();
            }
            TipoInformacionLogic tipoInformacionLogic=new TipoInformacionLogic();
            listaTipo=tipoInformacionLogic.listaTipoInformacion();
            TipoInformacionEntity tipoRet=new TipoInformacionEntity();
            for(TipoInformacionEntity tipo: listaTipo){
                if(tipo.getId()==tipo.getId()){
                    tipoRet=tipo;
                }
            }
            objetoInformacion.setTipo(tipoRet);
            objetoInformacion.setValor(Double.parseDouble(cantidad));
            lista.add(objetoInformacion);
        
        
    }

    
}
