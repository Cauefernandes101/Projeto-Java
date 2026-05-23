/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.dao;
//import io.github.cdimascio.dotenv.Dotenv;// conferir como fazer isso
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/***
 
 *
 * @author feispcaquino
 * classe para fazer a conexao com o banco de dados
 * 
 */
public class Conexao {
    public Connection getConnection() throws SQLException{
        //Dotenv dotenv = Dotenv.load();
        Connection conexao = DriverManager.getConnection( //"jdbc:postgresql://localhost:5432/postgres", "postgres","fei"
            "jdbc:postgresql://localhost:5432/Projeto", "postgres","239564941");
                //dotenv.get("SENHA_DB"));
        System.out.println("Conexão bem sucedida"); 
        return conexao;
    }
}
