/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.dao.ControllerDAO;
import Model.Usuario;
import View.Streaming;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author feispcaquino
 *//*  
faz a conexão com o view, banco de dados para operar os favoritos 

*/
public class Controller {
    private ControllerDAO dao;
    private Streaming view;
    private DefaultListModel<String> modeloFavoritos;
    
    /**
     * 
     * @param view que sera modificado por esse controler
     * @param dao do Usuario para conectar com banco
     * @throws SQLException caso de erro na conexao ou ao realizar comando no banco de dados
     */
    public Controller( Streaming view, ControllerDAO dao) throws SQLException {
        this.view = view;
        this.dao = new ControllerDAO(this.view);
        this.modeloFavoritos = new DefaultListModel<>();
    }
    
    // Consulta os animes salvos no banco e atualiza a JList3
    public void atualizarListaVisualFavoritos() {
        modeloFavoritos.clear();
        try {
            java.util.List<String> favoritados = this.dao.buscarTodosFavoritos(UsuarioLogado.getUsuarioLogado());
            for (String anime : favoritados) {
                modeloFavoritos.addElement(anime);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar painel de favoritos: " + ex.getMessage());
        } 
    }
    
    /**
     * Retorno do atributo modeloFavoritos
     * @return lista com favoitos
     */
    public DefaultListModel<String> getModeloFavoritos() {
        return modeloFavoritos;
    }
    
    
}
