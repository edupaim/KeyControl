/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativo.keycontrol.dto;

import java.util.Date;

/**
 *
 * @author Vax
 */
public class ReservaDTO {

    private Integer id;
    private Integer id_beneficiario;
    private Date date_in;
    private Date date_out;
    private Integer horario;

    public ReservaDTO(Integer id, Integer id_beneficiario, Integer id_chave, Date date_in, Date date_out, Integer horario) {
        this.id = id;
        this.id_beneficiario = id_beneficiario;
        this.date_in = date_in;
        this.date_out = date_out;
        this.horario = horario;
        this.id_chave = id_chave;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    private Integer id_chave;

    public Integer getId_chave() {
        return id_chave;
    }

    public void setId_chave(Integer id_chave) {
        this.id_chave = id_chave;
    }

    public Integer getId_beneficiario() {
        return id_beneficiario;
    }

    public void setId_beneficiario(Integer id_beneficiario) {
        this.id_beneficiario = id_beneficiario;
    }

    public Date getDate_in() {
        return date_in;
    }

    public void setDate_in(Date date_in) {
        this.date_in = date_in;
    }

    public Date getDate_out() {
        return date_out;
    }

    public void setDate_out(Date date_out) {
        this.date_out = date_out;
    }

    public Integer getHorario() {
        return horario;
    }

    public void setHorario(Integer horario) {
        this.horario = horario;
    }
}
