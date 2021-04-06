/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.*;

/**
 *
 * @author Andre
 */
public class ConexaoFactory {
    private static java.sql.Connection conexao = null;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/sispedido";
    private static String user = "root";
    private static String passoword = "";
    
    public static Connection getconexao(){
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url ,user,passoword);
            System.out.println("Conexão aberta! "+ conexao);
            return conexao;
        }catch (ClassNotFoundException | SQLException e){
            System.out.println("Ocorreu um erro ao acessar o" + 
                    "Banco de dados!!!" + e);
            return null;
        }
    }
    
    public static void closeconexao(){
        if(conexao != null){
            try{ 
                conexao.close();
                System.out.println("Conexao Fechada! ");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    
}

