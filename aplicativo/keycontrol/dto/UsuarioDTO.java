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
       switch (tipo){
           case 0:
               return "Administrador";
           case 1:
               return "Funcionario";
           default:
               return "Tipo nao identificado";
       }
    }

    public UsuarioDTO(Integer id, String nome, String login, String senha, Integer tipo) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    public UsuarioDTO() {
        this(null, null, null, null, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
    
}
