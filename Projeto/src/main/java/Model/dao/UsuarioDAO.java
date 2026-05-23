/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.dao;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *classe que faz a conexão com o banco de dados para realizar os inserts de usuario
 assim como o controle dos favoritos no banco de dados removendo ou adicionando
 * @author feispcaquino
 */
public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }
    public Connection getConn() {
        return conn;
    }
    /metodo que insere no banco de dados o Usuario com construtor definido na classe model
    public void inserir(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO usuario(nome, usuario, senha, email, nascimento)"
                + "values('"  + usuario.getNome() +"', '" +
                usuario.getNomeUsuario() + "', '" +
                usuario.getSenha() + "', '" +
                usuario.getEmail() + "', '" + usuario.getNascimento() + "')";
        System.out.println(sql);
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        
    }
    /pesquisa para ver se o login corresponde com um usuario cadastro no banco de dados
    public boolean retornarLogin(String usuario,String senha) throws SQLException{
        try{        
        getConn();
        String sql = "SELECT usuario,senha FROM usuario";
        PreparedStatement statement =this.conn.prepareStatement(sql);
        
        statement.execute();
        ResultSet resultado=statement.getResultSet();
        System.out.println(resultado);
        //  procura na lista de usuários vindos do SELECT  uma combinação válida
        while (resultado.next()) {
            String usuarioBanco = resultado.getString("usuario");
            String senhaBanco = resultado.getString("senha");

            // Compara os dados digitados com os dados do banco atual
            if (usuarioBanco.equals(usuario) && senhaBanco.equals(senha)) {
                
                return true;// Usuário encontrado, interrompe o laço de repetição
            }
        }
        return false;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Erro ao inserir");
            return false;
        }

        finally {
        // Garante que a conexão feche mesmo se o usuário não for encontrado
        if (this.conn != null && !this.conn.isClosed()) {
            
        }
        }
    }
    // metodo que inseri o favorito no banco de dados com o usuario chave estrangeira
    //  e primaria de outra tabela para evitar duplicidade
    public void inserirFavorito(String usuario, String anime) throws SQLException{
        String sql = "INSERT INTO favoritos (usuario, anime)"
                + "values('"  + usuario +"', '" +
                anime + "')";
        System.out.println(sql);
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        
    }
    //retira o favorito do banco de dados
    public void retirarFavorito(String usuario, String anime) throws SQLException{
        String sql = "DELETE FROM favoritos WHERE usuario ="
               +"'" + usuario +"'"+ " AND anime =" +"'"+ anime+ "'";
        System.out.println(sql);
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        
    }
       
    // metodo para pegar do banco os favoritos do usuario e apresentar na lista de favoritos para a remoção
    public java.util.List<String> buscarTodosFavoritosM(String usuario) throws SQLException {
        java.util.List<String> lista = new java.util.ArrayList<>();
        String sql = "SELECT anime FROM favoritos WHERE usuario = "+"'"+usuario+"'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                lista.add(rs.getString("anime"));
               
                }
            }
        }
        System.out.println(lista);
    return lista;
    }
    //limpa todos os favoritos do banco de dados
    public void limparTodosFavoritos(String usuario) throws SQLException { //para tela da aba favoritos
        String sql = "DELETE FROM favoritos WHERE usuario = "+"'"+ usuario+"'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.executeUpdate();
        }
    }
    
}
