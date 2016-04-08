/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.test;

import co.com.lunch.logic.admin.ProductoLogic;
import co.com.lunch.persistencia.admin.ProductoEntity;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



/**
 *
 * @author oscarramirez
 */
@ManagedBean(name = "bajada")
@ViewScoped
public class bajada implements Serializable{
    public ArrayList<ProductoEntity> listaProducto;
    public ArrayList<BufferedImage>listaImagenes;

    public ArrayList<ProductoEntity> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(ArrayList<ProductoEntity> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public ArrayList<BufferedImage> getListaImagenes() {
        return listaImagenes;
    }

    public void setListaImagenes(ArrayList<BufferedImage> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }
    
    
    
    @PostConstruct
    public void init(){
        listaProducto=new ArrayList<>();
        carga();
    }
    
    public void carga(){
        ProductoLogic productoLogic=new ProductoLogic();
        listaProducto=productoLogic.listaProducto();
    }
    
    
    
}
