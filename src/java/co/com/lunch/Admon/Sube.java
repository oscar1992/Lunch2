/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.Admon;

import co.com.lunch.logic.admin.CategoriaLogic;
import co.com.lunch.logic.admin.MarcaLogic;
import co.com.lunch.logic.admin.ProductoLogic;
import co.com.lunch.persistencia.admin.CategoriaEntity;
import co.com.lunch.persistencia.admin.MarcaEntity;
import co.com.lunch.persistencia.admin.ProductoEntity;
import co.com.lunch.zip.ComprimeImagenes;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author oscarramirez
 */
@ManagedBean(name = "Sube")
@ViewScoped
public class Sube implements Serializable {

    private ProductoEntity producto;
    private HashMap<String, Integer> listaTipo;
    private HashMap<String, Integer> listaCate;
    private HashMap<String, Integer> listaMarca;
    ArrayList<CategoriaEntity> listaCateBD;
    private UploadedFile sostiene2;
    private boolean exito;
    private boolean muestraMSG;

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public HashMap<String, Integer> getListaTipo() {
        return listaTipo;
    }

    public void setListaTipo(HashMap<String, Integer> listaTipo) {
        this.listaTipo = listaTipo;
    }

    public HashMap<String, Integer> getListaCate() {
        return listaCate;
    }

    public void setListaCate(HashMap<String, Integer> listaCate) {
        this.listaCate = listaCate;
    }

    public HashMap<String, Integer> getListaMarca() {
        return listaMarca;
    }

    public void setListaMarca(HashMap<String, Integer> listaMarca) {
        this.listaMarca = listaMarca;
    }

    public UploadedFile getSostiene2() {
        return sostiene2;
    }

    public void setSostiene2(UploadedFile sostiene2) {
        this.sostiene2 = sostiene2;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public boolean isMuestraMSG() {
        return muestraMSG;
    }

    public void setMuestraMSG(boolean muestraMSG) {
        this.muestraMSG = muestraMSG;
    }

    @PostConstruct
    public void init() {
        iniciaProducto();
        iniciaListaTipo();
        iniciaListaCategoria();
        iniciaListaMarca();

    }

    public void subeImagen(FileUploadEvent event) {
        if (event.getFile().getSize() > 0) {
            System.out.println("sube: " + event.getFile().getSize());
            sostiene2 = event.getFile();
            producto.setNombreImagen(event.getFile().getFileName());
        }

    }

    //Método que inicia la lista de los tipos disponibles
    public void iniciaListaTipo() {
        System.out.println("IniciaListaTipo");
        String qq = "";
        listaTipo = new HashMap<String, Integer>();
        for (int i = 1; i < 5; i++) {
            switch (i) {
                case 1:
                    qq = "Energia";
                    break;
                case 2:
                    qq = "Vitaminas";
                    break;
                case 3:
                    qq = "Crecimiento";
                    break;
                case 4:
                    qq = "Bebidas";
                    break;
            }
            listaTipo.put(qq, i);
        }
        /*
        for (Map.Entry<String, Integer> entry : listaTipo.entrySet()) {
            Integer key = entry.getValue();
            String value = entry.getKey();
            System.out.println("llave: " + key + " valor: " + value);
        }*/
    }

    //Método que inicia la lista de las categorías
    public void iniciaListaCategoria() {
        try (CategoriaLogic categoriaLogic = new CategoriaLogic();) {
            listaCateBD = categoriaLogic.listaCategoria();
            listaCate = new HashMap<String, Integer>();
            for (CategoriaEntity cate : listaCateBD) {
                listaCate.put(cate.getNombreCategoria(), cate.getIdCategoria());
            }
        } catch (Exception ex) {
            Logger.getLogger(Sube.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Método que inicia el objeto del producto + subObjetos
    public void iniciaProducto() {
        producto = new ProductoEntity();
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        producto.setCategoria(categoriaEntity);
        MarcaEntity marcaEntity = new MarcaEntity();
        producto.setMarca(marcaEntity);
    }

    //Método que inicia la lista de las Marcas
    public void iniciaListaMarca() {
        System.out.println("Inicia Marca");
        listaMarca = new HashMap<String, Integer>();
        try (MarcaLogic marcaLogic = new MarcaLogic()) {
            ArrayList<MarcaEntity> listaMarcaBD = marcaLogic.listaMarca();
            for (MarcaEntity marc : listaMarcaBD) {
                System.out.println("ana: " + marc.getMarca());
                listaMarca.put(marc.getMarca(), marc.getIdMarca());
            }
        } catch (Exception ex) {
            Logger.getLogger(Sube.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Método que filtra la categoría dependiendo del tipo seleccionado
    public void filtraCategoria(Integer idTipo) {
        System.out.println("Actua: " + idTipo);
        HashMap<String, Integer> aux = new HashMap<String, Integer>();
        for (CategoriaEntity cate : listaCateBD) {

            Integer cateTipo = cate.getTipo();
            System.out.println("cate: " + cateTipo);
            if (Objects.equals(idTipo, cateTipo)) {
                System.out.println("Añade");
                aux.put(cate.getNombreCategoria(), cate.getIdCategoria());
            }
        }
        listaCate = aux;
        System.out.println("cate: " + listaCate.size());
        for (Map.Entry<String, Integer> rr : listaCate.entrySet()) {
            System.out.println("QQ: " + rr.getValue());
        }

    }

    //Método que sube todo el producto a la base de datos
    public void persisteProducto() {

        FacesMessage message;
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "pp", null);
        //System.out.println("sos: "+ sostiene.getFile().getFileName());
        if (sostiene2 != null) {
            System.out.println("sos: " + sostiene2.getSize());
            if (sostiene2.getSize() > 0) {
                System.out.println("SUBE");
                producto.setNombreImagen(sostiene2.getFileName());

                try {
                    subeImagen(sostiene2.getFileName(), sostiene2.getInputstream());
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción Correcta", null);
                    
                } catch (IOException ex) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Inserción", null);
                    Logger.getLogger(Sube.class.getName()).log(Level.SEVERE, null, ex);
                }
                try (ProductoLogic productoLogic = new ProductoLogic()) {
                    productoLogic.ingresaProducto(producto);
                } catch (Exception ex) {
                    Logger.getLogger(Sube.class.getName()).log(Level.SEVERE, null, ex);
                }

                exito = true;

            }
        } else {
            System.out.println("NULO");
            exito = false;
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        limpia();
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

    public String msgIngreso() {
        String ret = "";
        if (exito) {
            ret = "Producto Ingresado Correctamente";
        } else {
            ret = "Producto No Ingresado";
        }
        return ret;
    }

    //Método que limpia los datos del bean
    public void limpia() {
        iniciaProducto();
        muestraMSG = true;
    }
    
    
}
