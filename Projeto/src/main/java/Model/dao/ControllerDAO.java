/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.dao;

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

/*
Classe que faz o controle da conexão com o banco de dados e 
os metodos do streaming que requerem salvar ,buscar ou apagar informações
*/
public class ControllerDAO {
    private UsuarioDAO model;
    private Streaming view;

    public ControllerDAO( Streaming view)throws SQLException {
        Conexao conexao = new Conexao();
        this.model = new UsuarioDAO(conexao.getConnection());
        this.view = view;
        
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
    public void removerFavoritoC(String usuario, String anime) throws SQLException {
       model.retirarFavorito(usuario,anime);
    }
    // limpa os favoritos, remove todas as rows do usuario logado
    public void limparFavoritos(String usuario) throws SQLException {
        model.limparTodosFavoritos(usuario);
        
    }
    public java.util.List<String> buscarTodosFavoritos(String usuario) throws SQLException {
        
        return model.buscarTodosFavoritosM(usuario);

    }
    /**
    // MÉTODO 3: Verificar se o anime já tem Like (Para mudar o texto do botão)
    public boolean Favorito(String usuario, String anime) throws SQLException {      
    return model.ehFavorito(usuario,anime);
    }**/
    
}
