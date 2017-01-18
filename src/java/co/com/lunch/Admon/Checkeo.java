/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.Admon;

import co.com.lunch.logic.cliente.PedidosLogic;
import co.com.lunch.persistencia.cliente.PedidoEntity;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author oscarramirez
 */
@ManagedBean(name = "Check")
@ViewScoped
public class Checkeo implements Serializable {

    private ArrayList<PedidoEntity> listaPedidos;
    private ArrayList<PedidoEntity> listaPedidosFiltrados;
    private PedidoEntity pedido;
    private Date lim;
    private Date desde;
    private Date hasta;

    public ArrayList<PedidoEntity> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(ArrayList<PedidoEntity> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    public Date getLim() {
        return lim;
    }

    public void setLim(Date lim) {
        this.lim = lim;
    }

    public Date getDesde() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (desde != null) {
            String ssf = df.format(desde);
            return df.parse(ssf);
        } else {
            return desde;
        }

    }

    public ArrayList<PedidoEntity> getListaPedidosFiltrados() {
        return listaPedidosFiltrados;
    }

    public void setListaPedidosFiltrados(ArrayList<PedidoEntity> listaPedidosFiltrados) {
        this.listaPedidosFiltrados = listaPedidosFiltrados;
    }

    public void setDesde(Date desde) throws ParseException {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /*
        this.desde = df.parse(desde.toString());
         */
        String ssf = df.format(desde);
        System.out.println("FF: " + ssf);
        this.desde = df.parse(ssf);
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    @PostConstruct
    public void init() {
        diaMax();
        iniciaPedidoNuevo();
        cargaPedidos();
    }

    public void iniciaPedidoNuevo() {
        pedido = new PedidoEntity();
        
    }

    public void cargaPedidos() {
        try (PedidosLogic pedidosLogic = new PedidosLogic()) {
            listaPedidos = pedidosLogic.listaPedidosTodos();
        } catch (Exception e) {
            System.out.println("Error cargando lista Productos");
        }
    }

    public void actuaPedido(PedidoEntity pedido) {
        System.out.println("Pedi: " + pedido.getIdPedido());
        System.out.println("Pedi: " + pedido.isEntregado());
        try (PedidosLogic pedidosLogic = new PedidosLogic()) {
            pedidosLogic.actuaPedido(pedido);
        } catch (Exception e) {
            System.out.println("Error en actualizar: " + e);
        }
    }

    public void diaMax() {
        lim = new Date();
        System.out.println("Fecha: " + lim.toString());
    }

    /**
     * Método que permite filtrar los pedidos que están después de una fecha
     */
    public void filtraDesde(SelectEvent event) {
        listaPedidosFiltrados = new ArrayList<>();
        System.out.println("Filtra Desde");
        if (hasta == null && desde == null) {
            listaPedidosFiltrados = listaPedidos;
        } else if (desde == null && hasta != null) {
            for (int i = 0; i < listaPedidos.size(); i++) {
                //System.out.println("Fecha: " + hasta.toString() + " fecha Ent: " + listaPedidos.get(i).getFechaEntregaDate().toString());
                if (hasta.before(listaPedidos.get(i).getFechaEntregaDate())) {
                    //System.out.println("Se pasó: " + listaPedidos.get(i).getFechaEntrega());
                } else {
                    listaPedidosFiltrados.add(listaPedidos.get(i));
                }
            }
            
        } else if (desde != null && hasta == null) {
            for (int i = 0; i < listaPedidos.size(); i++) {
                //System.out.println("Fecha: " + desde.toString() + " fecha Ent: " + listaPedidos.get(i).getFechaEntregaDate().toString());
                if (desde.after(listaPedidos.get(i).getFechaEntregaDate())) {
                    //System.out.println("Se pasó: " + listaPedidos.get(i).getFechaEntrega());
                } else {
                    listaPedidosFiltrados.add(listaPedidos.get(i));
                }
            }
            
        } else if (desde != null && hasta != null){
            for (int i = 0; i < listaPedidos.size(); i++) {
                //System.out.println("Fecha: " + desde.toString() + " fecha Ent: " + listaPedidos.get(i).getFechaEntregaDate().toString()+" Hasta: "+ hasta.toString());
                if (desde.after(listaPedidos.get(i).getFechaEntregaDate())||hasta.before(listaPedidos.get(i).getFechaEntregaDate())) {
                    //System.out.println("Se pasó: " + listaPedidos.get(i).getFechaEntrega());
                } else {
                    listaPedidosFiltrados.add(listaPedidos.get(i));
                }
            }
        }
        System.out.println("N tama: " + listaPedidosFiltrados.size());
        RequestContext.getCurrentInstance().update("pedidos");
    }

}
