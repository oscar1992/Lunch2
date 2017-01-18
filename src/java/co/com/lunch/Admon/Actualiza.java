/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.Admon;

import co.com.lunch.logic.admin.ProductoLogic;
import co.com.lunch.persistencia.admin.MarcaEntity;
import co.com.lunch.persistencia.admin.ProductoEntity;
import co.com.lunch.zip.ComprimeImagenes;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author oscarramirez
 */

@ManagedBean(name = "Actua")
@ViewScoped
public class Actualiza implements Serializable{
    private ProductoEntity producto;
    private UploadedFile sostiene2;

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public UploadedFile getSostiene2() {
        return sostiene2;
    }

    public void setSostiene2(UploadedFile sostiene2) {
        this.sostiene2 = sostiene2;
    }
    
    
    
    @PostConstruct
    public void init(){
        traeProducto();
    }
    
    //Método que trae el producto de la sesion
    public void traeProducto(){
        System.out.println("Carga de sesión");
        producto = (ProductoEntity)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("producto");
        if(producto.getMarca() == null){
            MarcaEntity marcaEntity=new MarcaEntity();
            producto.setMarca(marcaEntity);
        }
    }
    
    //Método que permite actualizar el producto
    public void actuaProducto(){
        RequestContext.getCurrentInstance().update("tablaInfo");
        System.out.println("Actua");
        
        if (sostiene2 != null) {
            System.out.println("Actua Imagen");
            if (sostiene2.getSize() > 0) {
                System.out.println("Imagen LLenita: "+ sostiene2.getFileName());
                producto.setNombreImagen(sostiene2.getFileName());
                try {
                    subeImagen(sostiene2.getFileName(), sostiene2.getInputstream());
                    
                } catch (IOException ex) {
                    Logger.getLogger(Actualiza.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try(ProductoLogic productoLogic=new ProductoLogic()){
            productoLogic.actualizaProducto(producto);
        } catch (Exception ex) {
            Logger.getLogger(Actualiza.class.getName()).log(Level.SEVERE, null, ex);
        }
        generaZip();
                
    }
    
    //Método que sube la imagen al disco
    public void subeImagen(String nombre, InputStream fuente) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("co.com.lunch.config.RUTAS");
        String ruta = rb.getString("IMAGENES").trim();
        ruta += nombre;
        try (OutputStream out = new FileOutputStream(new File(ruta))) {
            int read = 0;
            byte[] bytes = new byte[2048];
            while ((read = fuente.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            fuente.close();
            out.flush();
        } catch (Exception e) {
            System.out.println("ERROR de ALMACENAMIENTO: " + e);
        }
        
        producto.setImagen(ruta);
        
    }
    
    //Método que oermite volver a la lista
    public void volver(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("ListProducto.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Actualiza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Método que inicia el proceso de comprimir las imagenes guardadas en el servidor
    public void generaZip(){
        ComprimeImagenes comprime = new ComprimeImagenes();
    }
}
