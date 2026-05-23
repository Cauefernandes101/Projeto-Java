/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.Date;

/**
 *
 * @author feispcaquino
 * Classe que define as entradas do cadastro de usuario
 */
public class Usuario {
    private String nomeUsuario;
    private String nome;
    private int nascimento;
    private String email;
    private String senha;

    public Usuario(String nomeUsuario, String nome, int nascimento, String email, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.nome = nome;
        this.nascimento = nascimento;
        this.email = email;
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getNome() {
        return nome;
    }

    public int getNascimento() {
        return nascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNascimento(int nascimento) {
        this.nascimento = nascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}

