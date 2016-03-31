/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.persistencia.admin;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author oscarramirez
 */
@Entity
@Table(name = "ADM_TGRPR")
public class GrupoAlimenticioProductoEntity implements Serializable{
    @Id
    @Column(name = "GRPR_PRGR")
    private Integer id;
    @JoinColumn(name = "GRPR_PROD")
    @ManyToOne
    private ProductoEntity pruducto;
    @JoinColumn(name = "GRPR_GRUP")
    @ManyToOne
    private GrupoAlimenticioEntity grpoAlimenticio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductoEntity getPruducto() {
        return pruducto;
    }

    public void setPruducto(ProductoEntity pruducto) {
        this.pruducto = pruducto;
    }

    public GrupoAlimenticioEntity getGrpoAlimenticio() {
        return grpoAlimenticio;
    }

    public void setGrpoAlimenticio(GrupoAlimenticioEntity grpoAlimenticio) {
        this.grpoAlimenticio = grpoAlimenticio;
    }
    
}
