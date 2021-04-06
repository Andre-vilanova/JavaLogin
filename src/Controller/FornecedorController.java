/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FornecedorDAO;
import Model.Fornecedor;
import View.FormFornecedor;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre
 */
public class FornecedorController {

    private ArrayList<Fornecedor> ListaFornecedores;
    private final FormFornecedor view;
    private final FornecedorDAO dao;
    int index = 0;

    // Constructor
    public FornecedorController(FormFornecedor principal) {
        this.view = principal;
        this.dao = new FornecedorDAO();
    }

    // cadastrar fornecedor
    public void cadastrarFornecedor() {
        Fornecedor fornecedor = this.obterModeloFornecedor("cadastrar");
        this.mostrarDados(fornecedor);

        if (dao.adicionar(fornecedor)) {
            System.out.println("Fornecedor adicionado a lista!");
        } else {
            System.err.println("Não foi possivel adicionar Fornecedor");
        }
        this.ListaFornecedores.clear();
        this.CarregaTabelaFornecedores();
        this.limparCampos();
    }

    public void EditarFornecedor() {
        this.index = this.view.getTabelaFornecedor().getSelectedRow();
        this.SetarModeloFornecedor(this.dao.Listar().get(this.index));
        this.view.getBntForEditar().setEnabled(false);
        this.view.getBntForExc().setEnabled(false);

    }

    //Atualizar fornecedor

    public void AtualizarFornecedor() {
        Fornecedor novoFornecedor = this.obterModeloFornecedor("atualizar");
        this.mostrarDados(novoFornecedor);
        this.dao.alterar(novoFornecedor.getCodigo(), novoFornecedor);
        this.ListaFornecedores.clear();
        this.CarregaTabelaFornecedores();
        this.limparCampos();
        this.view.getBntForEditar().setEnabled(true);
        this.view.getBntForExc().setEnabled(true);
    }

    public void ExcluirFornecedor() {
        this.index = this.view.getTabelaFornecedor().getSelectedRow();
        this.SetarModeloFornecedor(this.dao.Listar().get(this.index));
        Fornecedor novofornecedor = this.obterModeloFornecedor("atualizar");
        int Sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir"
                + "Esse Registro??", "Atenção", JOptionPane.YES_OPTION);

        if (Sair == 0) {
            this.dao.excluir(novofornecedor.getCodigo());
            this.ListaFornecedores.clear();
            this.CarregaTabelaFornecedores();
            this.limparCampos();
        }
    }

    //Metodos Auxiliares
    public Fornecedor obterModeloFornecedor(String destino) {
        String codigo = this.view.getCampoForCod().getText();
        String nome = this.view.getCampoForNome().getText();
        String CNPJ = this.view.getCampoForCNPJ().getText();
        String telefone = this.view.getCampoForTel().getText();
        String fax = this.view.getCampoForFax().getText();
        String email = this.view.getCampoForEma().getText();
        String site = this.view.getCampoForSit().getText();

        if ("cadastrar".equals(destino)) {
            codigo = "0";
        }
        Fornecedor modelo = new Fornecedor(Integer.parseInt(codigo), nome, site, telefone, fax, email, site);
        return modelo;
    }

    // Mostrar Fornecedores
    public void mostrarDados(Fornecedor modelo) {
        System.out.println("Dados dos Fornecedor");
        System.out.println(modelo.getCodigo());
        System.out.println(modelo.getNome());
        System.out.println(modelo.getCnpj());
        System.out.println(modelo.getTelefone());
        System.out.println(modelo.getFax());
        System.out.println(modelo.getEmail());
        System.out.println(modelo.getSite());
    }

    // Carrega Tabela Fornecedores
    public void CarregaTabelaFornecedores() {
        System.out.println("Carregando tabela!");
        this.ListaFornecedores = this.dao.Listar();  // busca no banco
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"Codigo", "Nome", "CNPJ", "Telefone"}, 0);
        for (int i = 0; i < this.ListaFornecedores.size(); i++) {
            Object linha[] = new Object[]{ // criar um linha
                this.ListaFornecedores.get(i).getCodigo(),
                this.ListaFornecedores.get(i).getNome(),
                this.ListaFornecedores.get(i).getCnpj(),
                this.ListaFornecedores.get(i).getTelefone()
            };
            modelo.addRow(linha); // adiciona linha ao modelo
        }
        this.view.getTabelaFornecedor().setModel(modelo); //insere modelo 
        this.view.getBntForAtu().setEnabled(false); // desabilitar
    }

    public void limparCampos() {
        this.view.getCampoForCod().setText("");
        this.view.getCampoForNome().setText("");
        this.view.getCampoForCNPJ().setText("");
        this.view.getCampoForTel().setText("");
        this.view.getCampoForFax().setText("");
        this.view.getCampoForEma().setText("");
        this.view.getCampoForSit().setText("");
        this.view.getBntForCad().setEnabled(true);
        this.view.getBntForLim().setEnabled(true);
        this.view.getBntForAtu().setEnabled(false);

    }

    public void SetarModeloFornecedor(Fornecedor modelo) {
        this.view.getCampoForCod().setText(Integer.toString(modelo.getCodigo()));
        this.view.getCampoForNome().setText(modelo.getNome());
        this.view.getCampoForCNPJ().setText(modelo.getCnpj());
        this.view.getCampoForTel().setText(modelo.getTelefone());
        this.view.getCampoForFax().setText(modelo.getFax());
        this.view.getCampoForEma().setText(modelo.getEmail());
        this.view.getCampoForSit().setText(modelo.getSite());

        //desabilita botoes que não é pra usar
        this.view.getBntForCad().setEnabled(false);
        this.view.getBntForLim().setEnabled(false);
        this.view.getBntForAtu().setEnabled(true);

    }

    public void CancelarFornecedor() {
        this.limparCampos();
        this.view.getBntForEditar().setEnabled(true);
        this.view.getBntForExc().setEnabled(true);
    }

}
