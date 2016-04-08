/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.admin;

import co.com.lunch.logic.admin.TipoInformacionLogic;
import co.com.lunch.persistencia.admin.TipoInformacionEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author oscarramirez
 */
@ManagedBean(name = "TipoInformacion")
@ViewScoped
public class TipoInformacionBean implements Serializable{
    private String tipoInformacion;
    private TipoInformacionEntity objetoTipoInformacion;
    private ArrayList<TipoInformacionEntity> lista;
    private ArrayList<TipoInformacionEntity> listaFiltro;
    

    public String getTipoInformacion() {
        return tipoInformacion;
    }

    public void setTipoInformacion(String tipoInformacion) {
        this.tipoInformacion = tipoInformacion;
    }

    public TipoInformacionEntity getObjetoTipoInformacion() {
        return objetoTipoInformacion;
    }

    public void setObjetoTipoInformacion(TipoInformacionEntity objetoTipoInformacion) {
        this.objetoTipoInformacion = objetoTipoInformacion;
    }

    public ArrayList<TipoInformacionEntity> getLista() {
        consulta();
        return lista;
    }

    public void setLista(ArrayList<TipoInformacionEntity> lista) {
        this.lista = lista;
    }

    public ArrayList<TipoInformacionEntity> getListaFiltro() {
        return listaFiltro;
    }

    public void setListaFiltro(ArrayList<TipoInformacionEntity> listaFiltro) {
        this.listaFiltro = listaFiltro;
    }

    public TipoInformacionBean() {
        consulta();
    }

    
    
    @PostConstruct
    public void init(){
        limpia();
        
        
    }
    
    public void insertarMarca(){
        objetoTipoInformacion.setTipo(tipoInformacion);
        TipoInformacionLogic tipoInformacionLogic=new TipoInformacionLogic();
        FacesMessage message;
        if(tipoInformacionLogic.ingresaTipoInformacion(objetoTipoInformacion)==null){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Inserción", null);
            System.out.println("Tipo Informacion NO Guardado");
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción Correcta", null);
            System.out.println("Tipo Informacion Guardado");
            lista.add(objetoTipoInformacion);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        //consulta();
        
        limpia();
    }
    
    public void consulta(){
        try {
            TipoInformacionLogic tipoInformacionLogic=new TipoInformacionLogic();
            lista=tipoInformacionLogic.listaTipoInformacion();
        } catch (Exception e) {
        }
    }
    
    public void editar(RowEditEvent event){
        objetoTipoInformacion=(TipoInformacionEntity)event.getObject();
        TipoInformacionLogic tipoInformacionLogic=new TipoInformacionLogic();
        FacesMessage message;
        if(tipoInformacionLogic.actualizaTipoInformacion(objetoTipoInformacion)==null){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Actualización", null);
            System.out.println("Tipo Informacion NO Actuazlizado");
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización Correcta", null);
            System.out.println("Tipo Informacion Actualizado");
            
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        limpia();
        
    }
    
    public void eliminar(){
        if(objetoTipoInformacion==null){
            System.out.println("Objeto Nulo");
        }else{
            TipoInformacionLogic tipoInformacionLogic=new TipoInformacionLogic();
            FacesMessage message;
            if(tipoInformacionLogic.eliminaTipoInformacion(objetoTipoInformacion)==null){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Eliminación", null);
                System.out.println("Tipo Informacion NO Eliminado");
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminación Correcta", null);
                System.out.println("Tipo Informacion Eliminada");
                lista.remove(objetoTipoInformacion);
            }
            FacesContext.getCurrentInstance().addMessage(null, message);
            limpia();
        }
    }
    
    public void limpia(){
        objetoTipoInformacion=new TipoInformacionEntity();
        tipoInformacion="";
    }

}
