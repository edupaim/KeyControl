package aplicativo.keycontrol.dto;

/*
 * @author Edu
 */
public class ChaveDTO {

    private Integer id;
    private String sala;
    private Integer capacidade;
    private Integer tipo;
    private Integer beneficiario_id;

    public ChaveDTO(Integer id,
            String sala, Integer capacidade, Integer tipo,
            Integer beneficiario_id) {
        this.id = id;
        this.sala = sala;
        this.capacidade = capacidade;
        this.tipo = tipo;
        this.beneficiario_id = beneficiario_id;
    }

    public ChaveDTO() {
        this(null, null, null, null, null);
    }

    public Integer getBeneficiario_id() {
        return beneficiario_id;
    }

    public void setBeneficiario_id(Integer beneficiario_id) {
        this.beneficiario_id = beneficiario_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}
