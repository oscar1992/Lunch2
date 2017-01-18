/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.Admon;

import co.com.lunch.logic.admin.TagsLogic;
import co.com.lunch.persistencia.admin.MarcaEntity;
import co.com.lunch.persistencia.admin.ProductoEntity;
import co.com.lunch.persistencia.admin.TagsEntity;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author oscarramirez
 */

@ManagedBean(name = "Etiq")
@ViewScoped
public class Etiquetas implements Serializable{
    
    private TagsEntity etiqueta;
    private ProductoEntity producto;
    private ArrayList<TagsEntity> listaEtiquetas;

    public TagsEntity getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(TagsEntity etiqueta) {
        this.etiqueta = etiqueta;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public ArrayList<TagsEntity> getListaEtiquetas() {
        return listaEtiquetas;
    }

    public void setListaEtiquetas(ArrayList<TagsEntity> listaEtiquetas) {
        this.listaEtiquetas = listaEtiquetas;
    }
    
    @PostConstruct
    public void init(){
        iniciaEtiqueta();
        iniciaProducto();
        traeProducto();
        cargaEtiquteas();
    }
    
    public void iniciaEtiqueta(){
        etiqueta = new TagsEntity();
    }
    
    public void iniciaProducto(){
        producto = new ProductoEntity();
    }
    
    //Método que trae el producto de la sesion
    public void traeProducto(){
        System.out.println("Carga de sesión");
        producto = (ProductoEntity)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("producto");
    }
    
    public void cargaEtiquteas(){
        listaEtiquetas = new ArrayList<>();
        try(TagsLogic tagsLogic=new TagsLogic()){
            System.out.println("idp: "+producto.getIdProducto());
            listaEtiquetas=tagsLogic.TagsPorProducto(producto.getIdProducto());
            for(TagsEntity tag: listaEtiquetas){
                System.out.println("ETIQ: "+tag.getNombreTag());
            }
        }catch(Exception e){
            System.out.println("Error consulta de etiquetas: "+e);
        }
    }
    
    public void ingresaEtiqueta(){
        try(TagsLogic tagsLogic=new TagsLogic()){
            System.out.println("etiq OK: ["+tagsLogic.insertaTag(producto, etiqueta.getNombreTag())+")");
        }catch(Exception e){
            System.out.println("Error inserción etiqueta: "+e);
        }
        cargaEtiquteas();
    }
    
    public void actua(RowEditEvent event){
        TagsEntity tag = (TagsEntity)event.getObject();
        try(TagsLogic tagsLogic=new TagsLogic()){
           if(tagsLogic.actualizaTag(tag)){
               System.out.println("Cambio Tag OK");
           }else{
               System.out.println("Error Cambio Tag");
           }
        }catch(Exception e){
            System.out.println("Error en actualiza tag - Bean: "+e);
        }
    }
    
    public void cancela(RowEditEvent event){
        
    }
}
