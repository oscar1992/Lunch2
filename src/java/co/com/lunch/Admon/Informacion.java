/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.Admon;

import co.com.lunch.logic.admin.InformacionNutricionalLogic;
import co.com.lunch.logic.admin.TipoInformacionLogic;
import co.com.lunch.persistencia.admin.InformacionNutricionalEntity;
import co.com.lunch.persistencia.admin.MarcaEntity;
import co.com.lunch.persistencia.admin.ProductoEntity;
import co.com.lunch.persistencia.admin.TipoInformacionEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author oscarramirez
 */
@ManagedBean(name = "Info")
@ViewScoped
public class Informacion implements Serializable {

    private ArrayList<InformacionNutricionalEntity> listaInfo;
    private InformacionNutricionalEntity ingresa;
    private ProductoEntity producto;
    private HashMap<String, Integer> listaTipos;
    private Integer idTipo;

    public ArrayList<InformacionNutricionalEntity> getListaInfo() {
        return listaInfo;
    }

    public void setListaInfo(ArrayList<InformacionNutricionalEntity> listaInfo) {
        this.listaInfo = listaInfo;
    }

    public InformacionNutricionalEntity getIngresa() {
        return ingresa;
    }

    public void setIngresa(InformacionNutricionalEntity ingresa) {
        this.ingresa = ingresa;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public HashMap<String, Integer> getListaTipos() {
        return listaTipos;
    }

    public void setListaTipos(HashMap<String, Integer> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }
    

    @PostConstruct
    public void init() {
        iniciaIngresa();
        traeProducto();
        listaInfo();
        iniciaListaTipos();
    }

    //Método que trae el producto de la sesion
    public void traeProducto() {
        producto = (ProductoEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("producto");
        if (producto.getMarca() == null) {
            MarcaEntity marcaEntity = new MarcaEntity();
            producto.setMarca(marcaEntity);
        }
        ingresa.setItem(producto);
    }

    //Método que carga los datos de un producto
    public void listaInfo() {
        try (InformacionNutricionalLogic informacionNutricionalLogic = new InformacionNutricionalLogic()) {
            System.out.println("Prod: "+ producto.getIdProducto());
            listaInfo = informacionNutricionalLogic.infoPorProducto(producto);
        } catch (Exception ex) {
            Logger.getLogger(Informacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Método que inicia la lista de los tipos
    public void iniciaListaTipos() {
        listaTipos = new HashMap<String, Integer>();
        try (TipoInformacionLogic tipoInformacionLogic = new TipoInformacionLogic()) {
            ArrayList<TipoInformacionEntity> listaaux = tipoInformacionLogic.listaTipoInformacion();
            for (TipoInformacionEntity tipo : listaaux) {
                listaTipos.put(tipo.getTipoNombre(), tipo.getIdTinfo());
            }
        } catch (Exception ex) {
            Logger.getLogger(Informacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void iniciaIngresa() {
        ingresa = new InformacionNutricionalEntity();
        TipoInformacionEntity tipoaux = new TipoInformacionEntity();
        ingresa.setTipo(tipoaux);
        
    }

    //Método que permite agregar la información del producto
    public void agergaInfo() {
        System.out.println("Ingresa: "+ idTipo);
        ingresa.getTipo().setIdTinfo(idTipo);
        try (InformacionNutricionalLogic informacionNutricionalLogic = new InformacionNutricionalLogic()) {
            InformacionNutricionalEntity retorna = informacionNutricionalLogic.ingresaInformacionNutricional(ingresa);
            if (retorna.getId() != null) {
                System.out.println("Ingreso Exitoso");
            } else {
                System.out.println("Ingreso Falido");
            }
        } catch (Exception ex) {
            Logger.getLogger(Informacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaInfo();
    }

    //Método que elimina la información de un producto
    public void cancela(RowEditEvent event) {
        System.out.println("Cancela");
    }

    public void activa(RowEditEvent event) {
        System.out.println("Activa: " + event.getObject());
        InformacionNutricionalEntity actua = (InformacionNutricionalEntity) event.getObject();
        try (InformacionNutricionalLogic informacionNutricionalLogic = new InformacionNutricionalLogic()) {
            informacionNutricionalLogic.actualizaInformacionNutricional(actua);
        } catch (Exception ex) {
            Logger.getLogger(Informacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Método que edita un valor
    public void edita(CellEditEvent cell) {
        Object oldValue = cell.getOldValue();
        Object newValue = cell.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            System.out.println("nuevo: " + newValue);
        }
    }

}
