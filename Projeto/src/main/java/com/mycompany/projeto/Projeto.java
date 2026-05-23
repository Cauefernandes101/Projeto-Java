/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto;

import Controller.Controller;
import Model.dao.ControllerDAO;
import View.Streaming;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author feispcaquino
 */
public class Projeto {

    public static void main(String[] args) throws SQLException {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                //  Cria a tela 
                Streaming streaming = new Streaming();
                
                // Define painéis escondidos ou visíveis
                streaming.getjPanel1().setVisible(false);
                streaming.getjPanel3().setVisible(false);
                streaming.getjPanel7().setVisible(false);
                streaming.getjPanel4().setVisible(false);

                
                // a tela principal do sistema
                streaming.setVisible(true);
                
            } catch (SQLException ex) {
                // Captura falhas de conexão com o banco
                JOptionPane.showMessageDialog(null, 
                    "Erro crítico ao iniciar conexão com o banco de dados:\n" + ex.getMessage(), 
                    "Erro de Inicialização", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    
        
    }
}
