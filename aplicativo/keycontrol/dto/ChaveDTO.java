package aplicativo.keycontrol.dto;

/*
 * @author Edu
 */
public class ChaveDTO {
    private Integer id;
    private String  cod;
    private String  sala;
    private Integer capacidade;
    private String  andar;
    private String  tipo;
    private String  estado;
    

    public ChaveDTO(Integer id, String cod, 
                    String sala, Integer capacidade, 
                    String andar, String tipo, 
                    String estado) {
        this.id = id;
        this.sala = sala;
        this.andar = andar;
        this.capacidade = capacidade;
        this.cod = cod;
        this.tipo = tipo;
        this.estado = estado;
    }
    
    public ChaveDTO() {
        this(null, null, null, null, null, null, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
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

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
