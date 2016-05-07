package aplicativo.keycontrol.dto;

/*
 * @author Edu
 */
public class SalaDTO {
    Integer id;
    Integer numero;
    String pavilhao;

    public SalaDTO(Integer id, Integer numero, String pavilhao) {
        this.id = id;
        this.numero = numero;
        this.pavilhao = pavilhao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getPavilhao() {
        return pavilhao;
    }

    public void setPavilhao(String pavilhao) {
        this.pavilhao = pavilhao;
    }
    
    

}
