package aplicativo.keycontrol.dto;

import java.util.Date;

/*
 * @author Edu
 */
public class HistoricoDTO {
    Integer id;
    ChaveDTO chave;
    IBeneficiarioDTO benef;
    Date data;
    Integer tipo;

    public HistoricoDTO(Integer id, ChaveDTO chave, IBeneficiarioDTO benef, Date data, Integer tipo) {
        this.id = id;
        this.chave = chave;
        this.benef = benef;
        this.data = data;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ChaveDTO getChave() {
        return chave;
    }

    public void setChave(ChaveDTO chave) {
        this.chave = chave;
    }

    public IBeneficiarioDTO getBenef() {
        return benef;
    }

    public void setBenef(IBeneficiarioDTO benef) {
        this.benef = benef;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}
