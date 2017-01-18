/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.Admon;

import co.com.lunch.logic.admin.ProductoLogic;
import co.com.lunch.logic.admin.ProductoSaludLogic;
import co.com.lunch.logic.admin.SaludLogic;
import co.com.lunch.persistencia.admin.ProductoEntity;
import co.com.lunch.persistencia.admin.ProductoSaludEntity;
import co.com.lunch.persistencia.admin.SaludEntity;
import com.oracle.jrockit.jfr.Producer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author oscarramirez
 */
@ManagedBean(name = "Cajas")
@ViewScoped
public class Cajas implements Serializable {

    private ArrayList<SaludEntity> listaCajas;
    private ArrayList<ProductoSaludEntity> lista1;
    private ArrayList<ProductoSaludEntity> lista2;
    private ArrayList<ProductoSaludEntity> lista3;
    private ArrayList<ProductoSaludEntity> lista4;
    private ArrayList<ProductoSaludEntity> lista5;
    private HashMap<String, Integer> listaEnergia;
    private HashMap<String, Integer> listaVitaminas;
    private HashMap<String, Integer> listaCrecimiento;
    private HashMap<String, Integer> listaBebidas;

    public ArrayList<SaludEntity> getListaCajas() {
        return listaCajas;
    }

    public void setListaCajas(ArrayList<SaludEntity> listaCajas) {
        this.listaCajas = listaCajas;
    }

    public ArrayList<ProductoSaludEntity> getLista1() {
        return lista1;
    }

    public ArrayList<ProductoSaludEntity> getLista2() {
        return lista2;
    }

    public ArrayList<ProductoSaludEntity> getLista3() {
        return lista3;
    }

    public ArrayList<ProductoSaludEntity> getLista4() {
        return lista4;
    }

    public ArrayList<ProductoSaludEntity> getLista5() {
        return lista5;
    }

    public HashMap<String, Integer> getListaEnergia() {
        return listaEnergia;
    }

    public HashMap<String, Integer> getListaVitaminas() {
        return listaVitaminas;
    }

    public HashMap<String, Integer> getListaCrecimiento() {
        return listaCrecimiento;
    }

    public HashMap<String, Integer> getListaBebidas() {
        return listaBebidas;
    }

    @PostConstruct
    public void Cajas() {
        listaCajas = new ArrayList<>();
        lista1 = new ArrayList<>();
        lista2 = new ArrayList<>();
        lista3 = new ArrayList<>();
        lista4 = new ArrayList<>();
        lista5 = new ArrayList<>();
        cargaCajas();
        cargaProductos();
        cargaProductosPorTipo();
    }

    public void cargaCajas() {
        SaludLogic saludLogic = new SaludLogic();
        listaCajas = saludLogic.listaItem();
    }

    public void cargaProductos() {
        try (ProductoSaludLogic productoSaludLogic = new ProductoSaludLogic()) {
            for (SaludEntity caja : listaCajas) {
                switch (caja.getIdSalud()) {
                    case 1:
                        lista1 = productoSaludLogic.productoPorcaja(caja);
                        break;
                    case 2:
                        lista2 = productoSaludLogic.productoPorcaja(caja);
                        break;
                    case 3:
                        lista3 = productoSaludLogic.productoPorcaja(caja);
                        break;
                    case 4:
                        lista4 = productoSaludLogic.productoPorcaja(caja);
                        break;
                    case 5:
                        lista5 = productoSaludLogic.productoPorcaja(caja);
                        break;
                }

            }
        } catch (Exception e) {

        }

    }

    public void cargaProductosPorTipo() {
        listaEnergia = new HashMap<>();
        listaVitaminas = new HashMap<>();
        listaCrecimiento = new HashMap<>();
        listaBebidas = new HashMap<>();
        try (ProductoLogic productoLogic = new ProductoLogic()) {
            productoLogic.productosPorTipo(1).stream().forEach((prod) -> {
                listaEnergia.put(prod.getNombre(), prod.getIdProducto());
            });
            productoLogic.productosPorTipo(2).stream().forEach((prod) -> {
                listaVitaminas.put(prod.getNombre(), prod.getIdProducto());
            });
            productoLogic.productosPorTipo(3).stream().forEach((prod) -> {
                listaCrecimiento.put(prod.getNombre(), prod.getIdProducto());
            });
            productoLogic.productosPorTipo(4).stream().forEach((prod) -> {
                listaBebidas.put(prod.getNombre(), prod.getIdProducto());
            });
        } catch (Exception e) {

        }

    }

    public ArrayList<ProductoSaludEntity> selcionaLista(SaludEntity caja) {

        switch (caja.getIdSalud()) {
            case 1:
                return lista1;

            case 2:
                return lista2;

            case 3:
                return lista3;

            case 4:
                return lista4;

            case 5:
                return lista5;
            default:
                return null;

        }

    }

    public void cambia(ProductoSaludEntity prsa) {
        System.out.println("Idpsa: " + prsa.getIdProductosalud());
        System.out.println("Idp: " + prsa.getProducto().getIdProducto());
        System.out.println("Idp: " + prsa.getProducto().getNombre());
        System.out.println("Idsa: " + prsa.getSalud().getNombreSalud());

        try (ProductoLogic productoLogic = new ProductoLogic()) {
            ProductoEntity prod = productoLogic.productoPorId(prsa.getProducto().getIdProducto());
            prsa.setProducto(prod);
            try (ProductoSaludLogic productoSaludLogic = new ProductoSaludLogic()) {
                ProductoSaludEntity resp = productoSaludLogic.actualizaProductoSalud(prsa);
                //System.out.println("QQ nuevo: "+resp.getProducto().getIdProducto());
            } catch (Exception ee) {
                System.out.println("Error en ProductoSaludLogic: "+ee);
            }
            //System.out.println("Post Producto: " + prsa.getProducto().getIdProducto());
            //System.out.println("POst Producto: " + prsa.getProducto().getNombre());
        } catch (Exception e) {
            System.out.println("Error en ProductoLogic: "+e);
        }

    }
}
