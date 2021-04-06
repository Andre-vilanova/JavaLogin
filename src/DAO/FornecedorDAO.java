/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Andre
 */
public class FornecedorDAO implements InterfaceDao<Fornecedor> {

    private ArrayList<Fornecedor> ListaFornecedores;
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public FornecedorDAO() {
        this.ListaFornecedores = new ArrayList();
    }

    @Override
    public boolean adicionar(Fornecedor fornecedorobj) {
        String sql = "INSERT INTO fornecedor (forcod, fornome, forcnpj, fortelefone,"
                + "forfax, foremail, forwebsiste) VALUES (?,?,?,?,?,?,?);";
        try {
            conexao = ConexaoFactory.getconexao();      // Abre conexao
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, fornecedorobj.getCodigo());
            pst.setString(2, fornecedorobj.getNome());
            pst.setString(3, fornecedorobj.getCnpj());
            pst.setString(4, fornecedorobj.getTelefone());
            pst.setString(5, fornecedorobj.getFax());
            pst.setString(6, fornecedorobj.getEmail());
            pst.setString(7, fornecedorobj.getSite());
            pst.execute();
            pst.close();
            ConexaoFactory.closeconexao();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<Fornecedor> Listar() {
        String sql = "SELECT * FROM fornecedor;";
        try {
            conexao = ConexaoFactory.getconexao();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Fornecedor fornecedorobj = new Fornecedor();
                fornecedorobj.setCodigo(rs.getInt(1));
                fornecedorobj.setNome(rs.getString(2));
                fornecedorobj.setCnpj(rs.getString(3));
                fornecedorobj.setTelefone(rs.getString(4));
                fornecedorobj.setFax(rs.getString(5));
                fornecedorobj.setEmail(rs.getString(6));
                fornecedorobj.setSite(rs.getString(7));

                this.ListaFornecedores.add(fornecedorobj); //Adicionando o objeto a lista
            }
            rs.close();
            pst.close();
            ConexaoFactory.closeconexao();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return ListaFornecedores;

    }

    // Implemente o restante
    @Override
    public void alterar(int index, Fornecedor fornecedorobj) {
        String sql = "UPDATE  fornecedor SET fornome= ?, forcnpj=?, "
                + "fortelefone = ?, forfax = ?, foremail = ?,"
                + "forwebsiste = ? WHERE forcod = ? ; ";

        try {
            conexao = ConexaoFactory.getconexao();// Abre conexao
            pst = conexao.prepareStatement(sql);
            pst.setString(1, fornecedorobj.getNome());
            pst.setString(2, fornecedorobj.getCnpj());
            pst.setString(3, fornecedorobj.getTelefone());
            pst.setString(4, fornecedorobj.getFax());
            pst.setString(5, fornecedorobj.getEmail());
            pst.setString(6, fornecedorobj.getSite());
            pst.setInt(7, index);
            pst.execute();
            pst.close();
            ConexaoFactory.closeconexao();
            System.out.println("Fornecedor utualizado !!");
        } catch (SQLException e) {
            System.out.println("Não foi possivel atualizar fornecedor !!");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excluir(int index) {
        String sql = "DELETE FROM `sispedido`. `fornecedor` WHERE `forcod` = ?;";
        try {
            conexao = ConexaoFactory.getconexao();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, index);
            pst.execute();
            pst.close();
            System.out.println("Fornecedor" + index + "deletado !!");
            ConexaoFactory.closeconexao();
        } catch (Exception e) {
            System.out.println("Não foi possivel deletar fornecedro!!");
            throw new RuntimeException(e);
        }
    }

}
