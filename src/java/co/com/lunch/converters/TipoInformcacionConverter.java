/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.converters;

import co.com.lunch.admin.TipoInformacionBean;
import co.com.lunch.logic.admin.TipoInformacionLogic;
import co.com.lunch.persistencia.admin.TipoInformacionEntity;
import java.util.ArrayList;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author oscarramirez
 */
@FacesConverter("TipoInformacionConverter")
public class TipoInformcacionConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string != null && string.trim().length() > 0) {
            TipoInformacionBean tipoInformcacionBean=(TipoInformacionBean) fc.getExternalContext().getApplicationMap().get("TipoInformacion");
            
            
            return tipoInformcacionBean.getLista().get(Integer.parseInt(string));
                    
        }else{
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o != null) {
            return String.valueOf(((TipoInformacionEntity) o).getIdTinfo());
        }
        else {
            return null;
        }
    }
    
}
