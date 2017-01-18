/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.Admon;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


/**
 *
 * @author oscarramirez
 */
@ManagedBean(name = "Reporte")
@ViewScoped
public class Reportes implements Serializable {

    private Connection conn;
    private StreamedContent file;
    private StreamedContent file2;
    private StreamedContent file3;
    private StreamedContent file4;
    private StreamedContent file5;
    private StreamedContent file6;
    
    
    public void init() throws JRException, SQLException, ClassNotFoundException {
        
        ResourceBundle rb = ResourceBundle.getBundle("co.com.lunch.config.RUTAS");
        String jrxmlFileName = rb.getString("RUTARPORTES").trim() + "/Pedidos.jrxml";
        String jrxmlFileName2 = rb.getString("RUTARPORTES").trim() + "/Tipos.jrxml";
        String jrxmlFileName3 = rb.getString("RUTARPORTES").trim() + "/ListaItems.jrxml";
        String jrxmlFileName4 = rb.getString("RUTARPORTES").trim() + "/Lista de Productos.jrxml";
        String jrxmlFileName5 = rb.getString("RUTARPORTES").trim() + "/ListaBusquedas.jrxml";
        String jrxmlFileName6 = rb.getString("RUTARPORTES").trim() + "/ListaUsuarios.jrxml";
        String outFileName1 = rb.getString("RUTARPORTES").trim() + "/Pedidos.xls";
        String outFileName2 = rb.getString("RUTARPORTES").trim() + "/Tipos.xls";
        String outFileName3 = rb.getString("RUTARPORTES").trim() + "/ListaItems.xls";
        String outFileName4 = rb.getString("RUTARPORTES").trim() + "/Lista de Productos.xls";
        String outFileName5 = rb.getString("RUTARPORTES").trim() + "/ListaBusquedas.xls";
        String outFileName6 = rb.getString("RUTARPORTES").trim() + "/ListaUsuarios.xls";
        HashMap parametros = new HashMap();
        JasperReport report = JasperCompileManager.compileReport(jrxmlFileName);
        reporta(report, parametros, outFileName1);
        JasperReport report2 = JasperCompileManager.compileReport(jrxmlFileName2);
        reporta(report2, parametros, outFileName2);
        JasperReport report3 = JasperCompileManager.compileReport(jrxmlFileName3);
        reporta(report3, parametros, outFileName3);
        JasperReport report4 = JasperCompileManager.compileReport(jrxmlFileName4);
        reporta(report4, parametros, outFileName4);
        JasperReport report5 = JasperCompileManager.compileReport(jrxmlFileName5);
        reporta(report5, parametros, outFileName5);
        JasperReport report6 = JasperCompileManager.compileReport(jrxmlFileName6);
        reporta(report6, parametros, outFileName6);
    }

    public void reporta(JasperReport report, HashMap parametros, String outputFile) throws JRException, SQLException, ClassNotFoundException{
        conexion();
        JasperPrint print = JasperFillManager.fillReport(report, parametros, conn);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputFile);
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.exportReport();
        cierraConexxion();
        FileDownloadView();
        
    }
    
    public void conexion() throws SQLException, ClassNotFoundException {
        String userName = "ulunch";
        String password = "alunch";
        Class.forName("org.postgresql.Driver");
        //String url = "jdbc:postgresql://93.188.163.97:5432/LUNCH1";
        String url = "jdbc:postgresql://localhost:5432/LUNCH1";
        Connection conn = DriverManager.getConnection(url, userName, password);
        this.conn=conn;
    }
    
    public void cierraConexxion() throws SQLException{
        if(conn != null){
            System.out.println("Cierra conexión");
            conn.close();
        }
    }
    
    public void FileDownloadView() {        
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/Reportes/Pedidos.xls");
        file = new DefaultStreamedContent(stream, "application/excel", "Pedidos.xls");
        InputStream stream2 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/Reportes/Tipos.xls");
        file2 = new DefaultStreamedContent(stream2, "application/excel", "Tipos.xls");
        InputStream stream3 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/Reportes/ListaItems.xls");
        file3 = new DefaultStreamedContent(stream3, "application/excel", "ListaItems.xls");
        InputStream stream4 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/Reportes/Lista de Productos.xls");
        file4 = new DefaultStreamedContent(stream4, "application/excel", "Lista de Productos.xls");
        InputStream stream5 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/Reportes/ListaBusquedas.xls");
        file5 = new DefaultStreamedContent(stream5, "application/excel", "Lista de Busquedas.xls");
        InputStream stream6 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/Reportes/ListaUsuarios.xls");
        file6 = new DefaultStreamedContent(stream6, "application/excel", "Lista de Usuarios.xls");
    }
 
    public StreamedContent getFile() {
        return file;
    }
    
    public StreamedContent getFile2() {
        return file2;
    }
    
    public StreamedContent getFile3() {
        return file3;
    }
    
    public StreamedContent getFile4() {
        return file4;
    }
    
    public StreamedContent getFile5() {
        return file5;
    }
    
    public StreamedContent getFile6() {
        return file6;
    }
}
