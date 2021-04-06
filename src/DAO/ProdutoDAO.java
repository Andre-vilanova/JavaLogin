/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Andre
 */
public class ProdutoDAO implements InterfaceDao<Produto> {

    private ArrayList<Produto> ListaProduto;
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ProdutoDAO() {
        this.ListaProduto = new ArrayList();
    }

    @Override
    public boolean adicionar(Produto Produtoobj) {
        String sql = "INSERT INTO produto (forcod , pronome, prodescrição, prounimedida, "
                + "provalunitario, "
                + "VALUES (?,?,?,?,?);";
        try {
            conexao = ConexaoFactory.getconexao();// Abre conexao
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, Produtoobj.getFornecedor().getCodigo());
            pst.setString(2, Produtoobj.getNome());
            pst.setString(3, Produtoobj.getDescricao());
            pst.setString(4, Produtoobj.getUnMedida());
            pst.setFloat(5, Produtoobj.getValorUnitario());

            pst.execute();
            pst.close();
            ConexaoFactory.closeconexao();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<Produto> Listar() {
        String sql = "SELECT * FROM produto;";
        try {
            conexao = ConexaoFactory.getconexao();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Produto Produtoobj = new Produto();
                Produtoobj.setCodigo(rs.getInt(1));
                Produtoobj.setFornecedor(rs.getInt(2));
                Produtoobj.setNome(rs.getString(3));
                Produtoobj.setDescricao(rs.getString(4));
                Produtoobj.setUnMedida(rs.getString(5));
                Produtoobj.setValorUnitario(rs.getFloat(6));

                this.ListaProduto.add(Produtoobj); //Adicionando o objeto a lista
            }
            rs.close();
            pst.close();
            ConexaoFactory.closeconexao();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return ListaProduto;

    }
    @Override
    public void alterar(int index, Produto produto) {
        String sql = "UPDATE produto SET forcod = ?, pronome=?,"
                + "prodescricao=?, prounimedida=?, provalunitario=?"
                + "WHERE procod = ?;";
        try {
            conexao = ConexaoFactory.getconexao();// Abre conexao
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, produto.getFornecedor().getCodigo());
            pst.setString(2, produto.getNome());
            pst.setString(3, produto.getDescricao());
            pst.setString(4, produto.getUnMedida());
            pst.setFloat(5, produto.getValorUnitario());
            pst.setInt(6, produto.getCodigo());
            pst.execute();
            pst.close();
            ConexaoFactory.closeconexao();
            System.out.println("CLIENTE utualizado !!");

        } catch (SQLException e) {
            System.out.println("Não foi possivel atualizar cliente !!");
            throw new RuntimeException(e);
        }
    }

    // DELETE
    @Override
    public void excluir(int index) {
        int identificador = this.ListaProduto.get(index).getCodigo();
        String sql = "DELETE FROM `sispedido`. `produto` WHERE `procod` = ?;";
        try {
            conexao = ConexaoFactory.getconexao();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, identificador);
            pst.execute();
            pst.close();
            System.out.println("Produto" + identificador + "deletado !!");
            ConexaoFactory.closeconexao();
        } catch (Exception e) {
            System.out.println("Não foi possivel deletar produto!!");
            throw new RuntimeException(e);
        }
        this.ListaProduto.remove(index);
    }



}
