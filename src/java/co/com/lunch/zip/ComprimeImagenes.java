/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.zip;

import co.com.lunch.logic.admin.ProductoLogic;
import co.com.lunch.persistencia.admin.ProductoEntity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author oscarramirez
 */
public class ComprimeImagenes {

    private List<String> lista;
    private String rutaEntrada;
    private String rutaSalida;
    private List<ProductoEntity> listaProducto;
    private ArrayList<ArrayList<ProductoEntity>> partes;

    public ComprimeImagenes() {
        partes = new ArrayList<>();
        
        listaProducto = new ArrayList<ProductoEntity>();
        llenaProductos();
        reparte();
        int i = 0;
        for (ArrayList<ProductoEntity> parte : partes) {
            lista = new ArrayList<String>();
            listaArchivos(i);
            for(ProductoEntity prod: parte){
                String ruta = rutaEntrada+"/"+prod.getNombreImagen();
                agregaLista(new File(ruta));
            }
            //llenaLista(new File(rutaEntrada), parte);
            if (lista != null) {
                comprime();
            } else {
                System.out.println("Sin archivos");
            }
            i++;
        }

    }

    private void reparte() {
        int p = 0;
        int q = 0;
        int tot = 0;
        int porcion = (listaProducto.size() / 10);

        ArrayList<ProductoEntity> parte = new ArrayList<>();
        for (ProductoEntity prod : listaProducto) {
            parte.add(prod);
            //System.out.println("IDS: "+prod.getIdProducto());
            if (p >= porcion || esUltimo(listaProducto.size(), tot)) {
                if (esUltimo(listaProducto.size(), tot)) {
                    partes.get(partes.size() - 1).add(prod);
                } else {
                    partes.add(parte);
                    /*
                    for (ProductoEntity prod2 : parte) {
                        System.out.println("PROD2: " + prod2.getNombre() + " id: " + prod2.getIdProducto());
                    }
                    System.out.println("PARTES: " + partes.size());*/
                    parte = new ArrayList<>();
                }

                p = 0;
                q++;
            }
            tot++;
            p++;
        }
        if (listaProducto.size() % 10 != 0) {
            //System.out.println("Sobra");

        }
        /*
        //System.out.println("tot final: " + tot);
        //System.out.println("Reparte: " + partes.size());
        //System.out.println("Procion: " + porcion);
        for (ArrayList<ProductoEntity> listaP : partes) {
            System.out.println("PARTE: ");
            for (ProductoEntity prod : listaP) {
                System.out.println("Prod: " + prod.getNombre());
            }
            System.out.println("_______FIN PARTE_______");
        }*/
    }

    private void llenaProductos() {
        ProductoLogic productoLogic = new ProductoLogic();
        listaProducto = productoLogic.listaProducto();
    }

    private void listaArchivos(Integer i) {
        ResourceBundle rb = ResourceBundle.getBundle("co.com.lunch.config.RUTAS");
        rutaEntrada = rb.getString("IMAGENES").trim();
        System.out.println("Ruta entrada" + rutaEntrada);
        rutaSalida = rb.getString("ZIP").trim();
        rutaSalida = rutaSalida.concat(""+i+".zip");
    }
    
    private void agregaLista(File nodo){
        if(nodo.isFile()){
            lista.add(generaEntradaZip(""+nodo.getAbsoluteFile()));
        }
    }
    
    private void llenaLista(File nodo, ArrayList<ProductoEntity> parte) {
        //System.out.println("Inicia LLena Lista");
        //System.out.println("NODO: " + nodo.getAbsoluteFile());
        try {
            if (nodo.isFile()) {
                for (ProductoEntity prod : parte) {
                    //System.out.println("llega: " + generaEntradaZip("" + nodo.getAbsoluteFile())+ " trae: "+prod.getNombreImagen());
                    if (("/" + prod.getNombreImagen()).equals(generaEntradaZip("" + nodo.getAbsoluteFile())) && prod.isDisponible()) {
                        lista.add(generaEntradaZip("" + nodo.getAbsoluteFile()));
                        System.out.println("Lista tama: " + nodo.getName());
                    }
                }

            } else {
                System.out.println("No es un archivo: "+nodo.getName());
            }
            if (nodo.isDirectory()) {
                String[] subLista = nodo.list();
                for (String nombreArchivo : subLista) {
                    //llenaLista(new File(nodo, nombreArchivo), parte);
                }
            
            } else {
                System.out.println("No es un directorio");
            }
        } catch (Exception e) {
            //System.out.println("Nodo NUlo: " + nodo + " err: " + e);
        }
        System.out.println("____________Lista Fin tama: " + lista.size());
    }

    public String generaEntradaZip(String archivo) {
        String retorna = archivo.substring(rutaEntrada.length() - 1, archivo.length());
        //System.out.println("Retorna: " + retorna);
        return retorna;
    }

    private void comprime() {
        System.out.println("Inicia Compresion");
        byte[] buffer = new byte[1024];
        try {
            FileOutputStream fos = new FileOutputStream(rutaSalida);
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.setLevel(9);
            System.out.println("Archivo de salida: " + rutaSalida);
            for (String archivo : this.lista) {
                ZipEntry ze = new ZipEntry(archivo);
                zos.putNextEntry(ze);
                FileInputStream in = new FileInputStream(rutaEntrada + File.separator + archivo);
                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
            }
            zos.closeEntry();
            zos.close();
            System.out.println("Fin");
        } catch (IOException ex) {
            System.out.println("Error Compresion imagenes: " + ex);
        }
    }

    public boolean esUltimo(Integer lista, Integer itera) {
        int por = lista / 10;
        if (itera > (por * 10)) {
            //System.out.println("Ultimo");
            return true;
        } else {
            return false;
        }
    }
}
