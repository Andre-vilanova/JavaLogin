/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import DAO.ClienteDao;
import java.util.ArrayList;
import Model.Cliente;
import java.sql.*;
import DAO.ConexaoFactory;

/**
 *
 * @author Andre
 */
public class clienteView {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Cliente> ListaClientes;
        ClienteDao dao = new ClienteDao();
        
        Cliente cliente= new Cliente();/*("Jonas" ,"12345678987", "87999011877",
               "jonas@gamil.com" , "56326541" , "Rua joão Inacio" , "129" , "casa" ,
               "Jardins" , "Aracaju" , "SE");*/
        if (dao.adicionar(cliente)){
            System.out.println("Cliente adicionado a lista!");
        }
        else{
            System.out.println("Não foi possivel adicionar usuario.");
        }
        //Listarclientes
        System.out.println("Carregando Clientes!");
        ListaClientes = dao.Listar();
        for (int i = 0; i < ListaClientes.size();i++){
            System.out.println("\nDados dos usuário");
            System.out.println(ListaClientes.get(i).getCod());
            System.out.println(ListaClientes.get(i).getNome());
            System.out.println(ListaClientes.get(i).getCpf());
            System.out.println(ListaClientes.get(i).getTelefone());
            System.out.println(ListaClientes.get(i).getMunicipio());
        }
        //Atualizar clientes
        Cliente clienteAtualizar = new Cliente("Jonas Ferraz", "12345678987", "87999011877", 
    "jonas@gmail.com", "56326541", "Rua joão Inacio", "129", "casa", "Jardins", "Aracaju", "SE");
        dao.alterar(3, clienteAtualizar);
        
        //Excluir Cliente
        dao.excluir(1);
    }
    
}
