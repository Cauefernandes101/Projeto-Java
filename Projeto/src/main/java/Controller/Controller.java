/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.dao.ControllerDAO;
import Model.Usuario;
import View.Streaming;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
    private DefaultListModel<String> modeloLista ;
    private Map<String, String> mapaAnimes;
    
    /**
     * 
     * @param view que sera modificado por esse controler
     * @param dao do Usuario para conectar com banco
     * @throws SQLException caso de erro na conexao ou ao realizar comando no banco de dados
     */
    public Controller( Streaming view) throws SQLException {
        this.view = view;
        this.modeloFavoritos = new DefaultListModel<>();
        this.modeloLista = new DefaultListModel<>();
        this.mapaAnimes = new HashMap<>();
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

    public DefaultListModel<String> getModeloLista() {
        return modeloLista;
    }
    public void setDao(ControllerDAO dao) {
        this.dao=dao;
    }
    public void filtragem() {
        view.getjTextField2().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filtrarAnimes(); }

            @Override
            public void removeUpdate(DocumentEvent e) { filtrarAnimes(); }

            @Override
            public void changedUpdate(DocumentEvent e) { filtrarAnimes(); } 
        });
    }
    public void filtrarAnimes(){
    
        String termoBusca = view.getjTextField2().getText().trim().toLowerCase();
        modeloLista.clear();
        
        if (termoBusca.isEmpty()) {
            redefinirListaCompleta();
            return;
        }
        
        for (String nomeAnime : mapaAnimes.keySet()) {
            if (nomeAnime.toLowerCase().contains(termoBusca)) {
                modeloLista.addElement(nomeAnime);
            }
        }
        
    }
    
    /**
     *
     */
    public void redefinirListaCompleta() {
        modeloLista.clear();
        for (String nomeAnime : mapaAnimes.keySet()) {
            modeloLista.addElement(nomeAnime);
        }
    }

    public void controleClique() {
         view.getjList1().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String animeSelecionado = view.getjList1().getSelectedValue();
                    
                    if (animeSelecionado != null) {
                        String descricao = mapaAnimes.get(animeSelecionado);
                        
                        // Atualiza as JLabels do painel de conteúdo
                        view.getjLabel2().setText(animeSelecionado);
                        view.getjLabel14().setText("<html><body style='width: 200px'>" + descricao + "</body></html>");
                        
                        // Alterna a exibição dos painéis
                        view.getjPanel4().setVisible(true);
                        view.getjPanel3().setVisible(false);
                        
                        // Força a renderização visual do painel atualizado
                        view.getjPanel4().revalidate();
                        view.getjPanel4().repaint();
                    }
                }
            }
        });

    }

    public void carregarDadosDoArquivo() {
        
        String caminhoArquivo = "animes.txt"; 
        
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    String nome = partes[0].trim();
                    String descricao = partes[1].trim();
                    mapaAnimes.put(nome, descricao);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this.view, 
                "Erro ao carregar o arquivo animes.txt na raiz do projeto.\n" + e.getMessage(), 
                "Erro de Leitura", 
                JOptionPane.ERROR_MESSAGE);
        }
    
    }


    
    
}
