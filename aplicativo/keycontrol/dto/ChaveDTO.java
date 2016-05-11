package aplicativo.keycontrol.dto;

/*
 * @author Edu
 */
public class ChaveDTO {
    private int     id;
    private String  cod;
    private String  sala;
    private int     capacidade;
    private String  andar;
    private char    tipo;
    private String  estado;
    

    public ChaveDTO(int id, String cod, 
                    String Sala, int capacidade, 
                    String andar, char tipo, 
                    String estado) {
        this.id = id;
        this.sala = sala;
        this.andar = andar;
        this.capacidade = capacidade;
        this.cod = cod;
        this.tipo = tipo;
        this.estado = estado;
    }
    
    public ChaveDTO(int id) {
        this(id, "", "", 0, "", '\0', "");
    }
    
    public ChaveDTO(String cod, String sala) {
        this(0, cod, sala, 0, "", '\0', "");
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
