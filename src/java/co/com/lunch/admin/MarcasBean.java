/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.admin;

import co.com.lunch.logic.admin.MarcaLogic;
import co.com.lunch.persistencia.admin.MarcaEntity;
import java.io.Serializable;
import java.util.ArrayList;
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
@ManagedBean(name = "Marcas")
@ViewScoped
public class MarcasBean implements Serializable{
    private String marca;
    private MarcaEntity objetoMarca;
    private ArrayList<MarcaEntity> lista;
    private ArrayList<MarcaEntity> listaFiltro;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public MarcaEntity getObjetoMarca() {
        return objetoMarca;
    }

    public void setObjetoMarca(MarcaEntity objetoMarca) {
        this.objetoMarca = objetoMarca;
    }
    
    public ArrayList<MarcaEntity> getLista() {
        return lista;
    }

    public void setLista(ArrayList<MarcaEntity> lista) {
        this.lista = lista;
    }

    public ArrayList<MarcaEntity> getListaFiltro() {
        return listaFiltro;
    }

    public void setListaFiltro(ArrayList<MarcaEntity> listaFiltro) {
        this.listaFiltro = listaFiltro;
    }
    
    
    @PostConstruct
    public void init(){
        objetoMarca=new MarcaEntity();
        consulta();
    }
    
    public void insertarMarca(){
        objetoMarca.setMarca(marca);
        MarcaLogic marcaLogic=new MarcaLogic();
        FacesMessage message;
        if(marcaLogic.ingresaMarca(objetoMarca)==null){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Inserción", null);
            System.out.println("Marca NO Guardado");
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción Correcta", null);
            System.out.println("Marca Guardado");
            lista.add(objetoMarca);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        //consulta();
        
        limpia();
    }
    
    public void consulta(){
        try {
            MarcaLogic marcaLogic=new MarcaLogic();
            lista=marcaLogic.listaMarca();
        } catch (Exception e) {
        }
    }
    
    public void editar(RowEditEvent event){
        objetoMarca=(MarcaEntity)event.getObject();
        MarcaLogic marcaLogic=new MarcaLogic();
        FacesMessage message;
        if(marcaLogic.actualizaMarca(objetoMarca)==null){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Actualización", null);
            System.out.println("Marca NO Actuazlizado");
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización Correcta", null);
            System.out.println("Marca Actualizado");
            
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        limpia();
        
    }
    
    public void eliminar(){
        if(objetoMarca==null){
            System.out.println("Objeto Nulo");
        }else{
            MarcaLogic marcaLogic=new MarcaLogic();
            FacesMessage message;
            if(marcaLogic.eliminaMarca(objetoMarca)==null){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Eliminación", null);
                System.out.println("Marca NO Eliminado");
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminación Correcta", null);
                System.out.println("Marca Eliminada");
                lista.remove(objetoMarca);
            }
            FacesContext.getCurrentInstance().addMessage(null, message);
            limpia();
        }
    }
    
    public void limpia(){
        objetoMarca=new MarcaEntity();
        marca="";
    }

    
}
