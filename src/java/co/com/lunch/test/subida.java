/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.test;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author oscarramirez
 */

@ManagedBean(name = "test")
@ViewScoped
public class subida implements Serializable{
    private UploadedFile file;
    private String nombre;
    private String precio;
    private byte[] imagenSerial;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
    
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() throws IOException {
        if(file != null) {
            
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println("nomb: "+file.getFileName());
            System.out.println("tama: "+file.getSize());
            imagenSerial=new byte[(int)file.getSize()];
            file.getInputstream().read(imagenSerial);
            for(byte bb:imagenSerial){
                System.out.println(""+String.valueOf(bb));
            }
        }
    }
}
