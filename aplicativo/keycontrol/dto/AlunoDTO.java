/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativo.keycontrol.dto;

/**
 *
 * @author ramon
 */
public class AlunoDTO implements IBeneficiarioDTO{
    private Integer id;
    private String nome;
    private String matricula;
    
    public Integer getId(){
        return this.id;
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

    public String getMatricula() {
        return matricula;
    }


    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
