/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author User
 */
public class UsuarioLogado {
    
    // Variável estática que guardará o nome do usuário na memória do sistema
    private static String usuarioLogado;

    // Método para salvar o usuário após o login correto
    public static void setUsuarioLogado(String usuario) {
        usuarioLogado = usuario;
    }

    // Método para recuperar o nome do usuário em qualquer outra tela
    public static String getUsuarioLogado() {
        return usuarioLogado;
    }

    // Método para limpar a sessão quando o usuário fizer logout
    public static void encerrarSessao() {
        usuarioLogado = null;
    }

}
