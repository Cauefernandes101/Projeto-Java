/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.dao;

import Controller.Controller;
import Controller.UsuarioLogado;
import Model.Usuario;
import Model.dao.Conexao;
import Model.dao.UsuarioDAO;
import View.Streaming;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/*
Classe que faz o controle da conexão com o banco de dados e 
os metodos do streaming que requerem salvar ,buscar ou apagar informações
*/
public class ControllerDAO {
    private UsuarioDAO model;
    private Streaming view;
    private Controller controller;

    public ControllerDAO( Streaming view ,Controller controller)throws SQLException {
        Conexao conexao = new Conexao();
        this.model = new UsuarioDAO(conexao.getConnection());
        this.view = view;
        this.controller=controller;
        
    }


    // insere no banco os dados do usuario
    public void cadastro(){
        String nomeUsuario = this.view.getTfnomeUsuario().getText();
        String nome = this.view.getTfNome().getText();
        int nascimento = Integer.parseInt(this.view.getTfNascimento().getText());
        String email = this.view.getTfEmail().getText();
        String senha = this.view.getTfSenha().getText();
        Usuario usuario = new Usuario(nomeUsuario,nome,nascimento,email,senha);
        try{
            this.model.inserir(usuario);
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Erro ao inserir");
        }
    }
    // compara no banco os dados para verificar o cadastro
    public boolean login() throws SQLException{
        boolean resultado= true;
        String nomeUsuario = this.view.getUsuarioLogin().getText();
        String senha = this.view.getSenhaLogin().getText();
        resultado=this.model.retornarLogin(nomeUsuario,senha);
        return resultado; 
    }
      // MÁTODO 1: Dar Like (Inserir favorito)
    public void adicionarFavorito(String usuario, String anime) throws SQLException {
        model.inserirFavorito(usuario,anime);
        }
    

    // MÉTODO 2: Tirar Like (Remover favorito)
        public void removerFavorito(String usuario, String anime) throws SQLException {
        
    int confirmacao = JOptionPane.showConfirmDialog(this.view, 
            "Deseja remover " + anime + " dos seus favoritos?", 
            "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
            
    if (confirmacao == JOptionPane.YES_OPTION) {
        try {
            // Remove do banco de dados
            model.retirarFavorito(usuario, anime);
            
            JOptionPane.showMessageDialog(this.view, "Anime removido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this.view, "Erro ao remover: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
      
    }
    public void removerFavoritoC(String usuario) throws SQLException {
        
       String animeSelecionado = view.getjList3().getSelectedValue();
    
    if (animeSelecionado == null) {
        JOptionPane.showMessageDialog(this.view, "Selecione um anime da lista de favoritos para remover!");
        return;
    }
    
    int confirmacao = JOptionPane.showConfirmDialog(this.view, 
            "Deseja remover " + animeSelecionado + " dos seus favoritos?", 
            "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
            
    if (confirmacao == JOptionPane.YES_OPTION) {
        try {
            // Remove do banco de dados
            model.retirarFavorito(usuario, animeSelecionado);
            
            // Atualiza a lista visual do painel de favoritos
            controller.atualizarListaVisualFavoritos();
           
            
            JOptionPane.showMessageDialog(this.view, "Anime removido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this.view, "Erro ao remover: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
      
    }
    // limpa os favoritos, remove todas as rows do usuario logado
    public void limparFavoritos(String usuario) throws SQLException {
                if (this.controller.getModeloFavoritos().isEmpty()) {
        JOptionPane.showMessageDialog(this.view, "Sua lista de favoritos já está vazia!");
        return;
    }
    
    int confirmacao = JOptionPane.showConfirmDialog(this.view, 
            "Tem certeza que deseja apagar TODOS os seus favoritos de forma permanente?", 
            "Aviso Crítico", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
    if (confirmacao == JOptionPane.YES_OPTION) {
        try {
            // Executa a remoção em massa no banco de dados
            model.limparTodosFavoritos(usuario);
            
            // Atualiza a tela
            controller.atualizarListaVisualFavoritos();
            

            
            JOptionPane.showMessageDialog(this.view, "Todos os favoritos foram apagados!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this.view, "Erro ao limpar favoritos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
        
        
    }
    public java.util.List<String> buscarTodosFavoritos(String usuario) throws SQLException {
        
        return model.buscarTodosFavoritosM(usuario);

    }
 
    
}
