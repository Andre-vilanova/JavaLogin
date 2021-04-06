/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import Model.Fornecedor;
import Model.Produto;
import View.FormProdutos;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andre
 */
public class ProdutoController {

    private ArrayList<Produto> ListaProduto;
    private final FormProdutos view;
    private final ProdutoDAO dao;
    private final FornecedorDAO daoFornecedor;
    int index = 0;

    public ProdutoController(FormProdutos produtoForm) {
        this.view = produtoForm;
        this.dao = new ProdutoDAO();
        this.daoFornecedor = new FornecedorDAO();
    }

    // Cadastrar produto
    public void cadastrarProduto() {
        Produto produto = this.obterModeloProduto("cadastrar");
        this.mostrarDados(produto);

        if (dao.adicionar(produto)) {
            System.out.println("Produto adicionado a lista!");
        } else {
            System.err.println("Não foi possivel adicionar produtos");
        }
        //limpa lista produtos
        this.ListaProduto.clear();
        //carrega nova tabela clientes
        this.CarregaTabelaProduto();
        this.limparProdutos();
    }

    //Editar Produto
    public void EditarProduto() {
        this.index = this.view.getTabelaProdutosT().getSelectedRow();
        this.SetarModeloProduto(this.dao.Listar().get(this.index));
        this.view.getBntProEdi().setEnabled(false);
        this.view.getBntProExc().setEnabled(false);

    }
    
    // Atualizar Produto
    public void AtualizarProduto(){
        Produto novoproduto = this.obterModeloProduto("atualizar");
        this.mostrarDados(novoproduto);
        this.dao.alterar(this.index, novoproduto);
        this.ListaProduto.clear();
        this.CarregaTabelaProduto();
        this.limparProdutos();
        this.view.getBntProEdi().setEnabled(true);
        this.view.getBntProExc().setEnabled(true);
    }
    
    //Excluir Produto
    public void ExcluirProduto(){
        this.index = this.view.getTabelaProdutosT().getSelectedRow();
        int Sair = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir esse registro"
                + "??", "Atenção", JOptionPane.YES_OPTION);
        if(Sair ==0){
            this.dao.excluir(index);
            this.ListaProduto.clear();
            this.CarregaTabelaProduto();
            this.limparProdutos();
        }
                
    }



    // Metdodos adicionais
    public Produto obterModeloProduto(String destino) {
        String codigo = this.view.getCampoProCod().getText();
        String codigoFornecedor = this.view.getCampoProCodFor().getText();
        String nome = this.view.getCampoProNome().getText();
        String descricao = this.view.getCampoProDesc().getText();
        String unidade = this.view.getCampoProUN().getText();
        String valorproduto = this.view.getCampoProVal().getText();

        if ("cadastrar".equals(destino)) {
            codigo = "0";
        }
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCodigo(Integer.parseInt(codigoFornecedor));
        Produto modelo = new Produto(Integer.parseInt(codigo), fornecedor, nome, descricao, unidade, Float.parseFloat(valorproduto));
        return modelo;
    }

    //Mostrar no console
    public void mostrarDados(Produto modelo) {
        System.out.println("Dados do Produto!");
        System.out.println(modelo.getCodigo());
        System.out.println(modelo.getFornecedor().getCodigo());
        System.out.println(modelo.getNome());
        System.out.println(modelo.getDescricao());
        System.out.println(modelo.getUnMedida());
        System.out.println(modelo.getValorUnitario());
    }

    //Carrega Tabela produtos

    public void CarregaTabelaProduto() {
        System.out.println("Carregando tabela!");
        this.ListaProduto = this.dao.Listar(); // busca no banco
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"Código", "Nome", "Unidade", "Valor unitario "}, 0);
        for (int i = 0; i < this.ListaProduto.size(); i++) {
            Object linha[] = new Object[]{ 
                this.ListaProduto.get(i).getCodigo(),
                this.ListaProduto.get(i).getNome(),
                this.ListaProduto.get(i).getUnMedida(),
                this.ListaProduto.get(i).getValorUnitario()};
                modelo.addRow(linha); 
            };
            this.view.getTabelaProdutosT().setModel(modelo);
            this.view.getBnbProAtu().setEnabled(false);
            
            // adiciona linha ao modelo
        }


    public void limparProdutos() {
        this.view.getCampoProCod().setText("");
        this.view.getCampoProCodFor().setText("");
        this.view.getCampoProNome().setText("");
        this.view.getCampoProDesc().setText("");
        this.view.getCampoProUN().setText("");
        this.view.getCampoProVal().setText("");
        this.view.getjFormattedCampoProCNPJ().setText("");

        this.view.getBntProCad().setEnabled(true);
        this.view.getBntProLim().setEnabled(true);
        this.view.getBnbProAtu().setEnabled(false);

    }
    
    public void atualizarFornecedor(){
        ArrayList<Fornecedor> listaFornecedor= this.daoFornecedor.Listar();
        DefaultComboBoxModel combooxBoxModel = (DefaultComboBoxModel)
        this.view.getjComboBoxProFor().getModel();
        for (Fornecedor fornecedor: listaFornecedor){
            combooxBoxModel.addElement(fornecedor);
        }
    }
    
    public void ObterFornecedor(){
        Fornecedor fornecedor = (Fornecedor)
        this.view.getjComboBoxProFor().getSelectedItem();
        this.view.getjFormattedCampoProCNPJ().setText(fornecedor.getCnpj());
        this.view.getCampoProCodFor().setText(Integer.toString(fornecedor.getCodigo()));
    }
    
    public void SetarModeloProduto(Produto modelo){
        this.view.getCampoProCodFor().setText(Integer.toString(modelo.getCodigo()));
        this.view.getCampoProCodFor().setText(Integer.toString(modelo.getFornecedor().getCodigo()));
        this.view.getCampoProNome().setText(modelo.getNome());
        this.view.getCampoProDesc().setText(modelo.getDescricao());
        this.view.getCampoProUN().setText(modelo.getUnMedida());
        this.view.getCampoProVal().setText(String.valueOf(modelo.getValorUnitario()));
        this.view.getBntProCad().setEnabled(false);
        this.view.getBntProLim().setEnabled(false);
        this.view.getBnbProAtu().setEnabled(true);
    }
    
    public void CancelarProduto(){
        this.limparProdutos();
        this.view.getBntProEdi().setEnabled(true);
        this.view.getBntProExc().setEnabled(true);
    }
}
