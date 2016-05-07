package aplicativo.keycontrol.dto;

/*
 * @author Edu
 */
public class ChaveDTO {
    Integer id;
    SalaDTO sala;

    public ChaveDTO(Integer id, SalaDTO sala) {
        this.id = id;
        this.sala = sala;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SalaDTO getSala() {
        return sala;
    }

    public void setSala(SalaDTO sala) {
        this.sala = sala;
    }

}
