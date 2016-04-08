/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.test;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author oscarramirez
 */
@ManagedBean(name = "inicio")
@ViewScoped
public class init implements Serializable{
    
    public void ruta(){
        String ruta=FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        System.out.println("CONTEXT: "+ruta);
    }
    
}
