
package DAO;
import Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Andre
 */
public class ClienteDao implements InterfaceDao<Cliente> {

    private ArrayList<Cliente> ListaClientes;
        Connection conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

    public ClienteDao() {
        this.ListaClientes = new ArrayList();
    }
    //Criar
    @Override
    public boolean adicionar (Cliente clienteobj){
        String sql = "INSERT INTO cliente (clinome, clicpf, clitelefone, cliemail, "
                + "clicep, cliendlogradouro, cliendnumero, cliendcomplemento, "
                + "cliendbairro, cliendmunicipio, clienduf)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        try {
            conexao = ConexaoFactory.getconexao();// Abre conexao
            pst = conexao.prepareStatement(sql);
            pst.setString(1, clienteobj.getNome());
            pst.setString(2, clienteobj.getCpf());
            pst.setString(3, clienteobj.getTelefone());
            pst.setString(4, clienteobj.getEmail());
            pst.setString(5, clienteobj.getCep());
            pst.setString(6, clienteobj.getLogradouro());
            pst.setString(7, clienteobj.getNumero());
            pst.setString(8, clienteobj.getComplemento());
            pst.setString(9, clienteobj.getBairro());
            pst.setString(10, clienteobj.getMunicipio());
            pst.setString(11, clienteobj.getUf());
            pst.execute();
            pst.close();
            ConexaoFactory.closeconexao();
            return true;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
       @Override     
    public ArrayList<Cliente> Listar(){
        String sql = "SELECT * FROM cliente;";
        try{
            conexao = ConexaoFactory.getconexao();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                Cliente clienteobj = new Cliente();
                clienteobj.setCod(rs.getInt(1));
                clienteobj.setNome(rs.getString(2));
                clienteobj.setCpf(rs.getString(3));
                clienteobj.setTelefone(rs.getString(4));
                clienteobj.setEmail(rs.getString(5));
                clienteobj.setCep(rs.getString(6));
                clienteobj.setLogradouro(rs.getString(7));
                clienteobj.setNumero(rs.getString(8));
                clienteobj.setComplemento(rs.getString(9));
                clienteobj.setBairro(rs.getString(10));
                clienteobj.setMunicipio(rs.getString(11));
                clienteobj.setUf(rs.getString(12));
                this.ListaClientes.add(clienteobj); //Adicionando o objeto a lista
            } 
            rs.close();
            pst.close();
            ConexaoFactory.closeconexao();
        }catch (SQLException e){
            throw new RuntimeException();
            }
        return ListaClientes;
        
    }
    @Override
    public void alterar(int index,Cliente  clienteobj){
        String sql = "UPDATE  cliente SET clinome= ?, clicpf=?, "
                + "clitelefone = ?, cliemail = ?, clicep = ?,"
                + "cliendlogradouro = ?, cliendnumero = ?, cliendcomplemento = ?,"
                +"cliendbairro = ?, cliendmunicipio = ?, "
                + "clienduf = ? WHERE clicod = ? ; "; 

        try {
            conexao = ConexaoFactory.getconexao();// Abre conexao
            pst = conexao.prepareStatement(sql);
            pst.setString(1, clienteobj.getNome());
            pst.setString(2, clienteobj.getCpf());
            pst.setString(3, clienteobj.getTelefone());
            pst.setString(4, clienteobj.getEmail());
            pst.setString(5, clienteobj.getCep());
            pst.setString(6, clienteobj.getLogradouro());
            pst.setString(7, clienteobj.getNumero());
            pst.setString(8, clienteobj.getComplemento());
            pst.setString(9, clienteobj.getBairro());
            pst.setString(10, clienteobj.getMunicipio());
            pst.setString(11, clienteobj.getUf());
            pst.setInt(12, index);
            pst.execute();
            pst.close();
            ConexaoFactory.closeconexao();
            System.out.println("CLIENTE utualizado !!");
    }catch (SQLException e){
            System.out.println("Não foi possivel atualizar cliente !!");
            throw new RuntimeException(e);
    }
    }
        
    @Override
    public void excluir (int index){
        String sql = "DELETE FROM `sispedido`. `cliente` WHERE `clicod` = ?;";
        try {
            conexao  = ConexaoFactory.getconexao();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1,index);
            pst.execute();
            pst.close();
            System.out.println("Cliente" + index + "deletado !!");
            ConexaoFactory.closeconexao();
        } catch (Exception e) {
            System.out.println("Não foi possivel deletar cliente!!");
            throw new RuntimeException(e);
        }
        }   
}
