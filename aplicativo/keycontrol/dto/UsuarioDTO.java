package aplicativo.keycontrol.dto;

/*
 * @author Edu
 */
public class UsuarioDTO {
    Integer id;
    String nome;
    String login;
    String senha;
    Integer tipo;
    
    public String getTipoString(){
        if(this.tipo==0){
            return "Administrador";
        } else {
            return "Funcionário";
        }
    }

    public UsuarioDTO(int id, String nome, String login, String senha, int tipo) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }
    
    public UsuarioDTO(String nome, String login, String senha, int tipo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    public UsuarioDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
}
