/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.Admon;

import co.com.lunch.logic.admin.ProductoLogic;
import co.com.lunch.persistencia.admin.ProductoEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author oscarramirez
 */
@ManagedBean(name = "Lista")
@ViewScoped
public class Lista implements Serializable{
    private ProductoEntity seleccion;
    private ArrayList<ProductoEntity> listaProductos;

    public ProductoEntity getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(ProductoEntity seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<ProductoEntity> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<ProductoEntity> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    @PostConstruct
    public void init(){
        iniciaLista();
    }
    
    //Método que inicia la lista de productos
    public void iniciaLista(){
        try(ProductoLogic productoLogic=new ProductoLogic()){
            listaProductos = productoLogic.listaProductosTodos();
        } catch (Exception ex) {
            Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void envia(SelectEvent event){
        System.out.println("Selec: "+seleccion.getNombre());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("producto", seleccion);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("ActualizaProducto.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
