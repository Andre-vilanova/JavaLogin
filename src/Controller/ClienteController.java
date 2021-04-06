package Controller;

import DAO.ClienteDao;
import Model.Cliente;
import View.FormCliente;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


// regras de negocio
/**
 *
 * @author Andre
 */
public class ClienteController {
    //Atributos da Classe
    private ArrayList<Cliente> ListaClientes ;
    private final FormCliente view;        
    private final ClienteDao dao;
    int index = 0;

    public ClienteController(FormCliente clienteForm) {
        this.view = clienteForm;
        this.dao = new ClienteDao();
    }
    
    public void cadastrarCliente() {
        Cliente cliente = this.obterModeloCliente("cadastrar");
        this.mostrarDados(cliente);
        
        if (dao.adicionar(cliente)){
            System.out.println("Cliente adicionado a lista!");
        }
        else{
            System.err.println("Não foi possivel adicionar usuario");
        }
        //limpa lista clientes
        this.ListaClientes.clear();
        //carrega nova tabela clientes
        this.CarregaTabelaClientes();
        this.limparCampos();
    }
    
    public void EditarCliente(){
        this.index = this.view.getTabelaClientes().getSelectedRow();
        
        this.SetarModelo(this.dao.Listar().get(this.index));
        
        this.view.getBntEditar().setEnabled(false);
        this.view.getBntExcluir().setEnabled(false);
        
    }
        // Atulaizar Cliente
    public void AtualizarCliente(){
        Cliente novocliente = this.obterModeloCliente("atualizar");
        this.mostrarDados(novocliente);
        this.dao.alterar(novocliente.getCod(), novocliente);
        this.ListaClientes.clear();
        this.CarregaTabelaClientes();
        this.limparCampos();
        this.view.getBntEditar().setEnabled(true);
        this.view.getBntExcluir().setEnabled(true);
    }
    
    
    public void ExcluirCliente(){
        this.index = this.view.getTabelaClientes().getSelectedRow();
        this.SetarModelo(this.dao.Listar().get(this.index));
        Cliente novocliente = this.obterModeloCliente("atualizar");
        int Sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir"
        + "Esse Registro??", "Atenção", JOptionPane.YES_OPTION);
        
       
        if (Sair == 0){
            this.dao.excluir(novocliente.getCod());
            this.ListaClientes.clear();
            this.CarregaTabelaClientes();
            this.limparCampos();
        }
    }
    public void CancelarCliente(){
        this.limparCampos();
        this.view.getBntEditar().setEnabled(true);
        this.view.getBntExcluir().setEnabled(true);
    }
    
    public Cliente obterModeloCliente(String destino){
        String codigo = this.view.getCampoCliCod().getText();
        String nome = this.view.getCampoCliNome().getText();
        String CPF = this.view.getCampoCliCPF().getText();
        String telefone = this.view.getCampoCliTel().getText();
        String email = this.view.getCampoCliEmail().getText();
        String cep = this.view.getCampoCliCep().getText();
        String logradouro = this.view.getCampoCliLogr().getText();
        String numero = this.view.getCampoCliNumero().getText();
        String complemento = this.view.getCampoCliCompl().getText();
        String bairo = this.view.getCampoCliBairro().getText();
        String municipio = this.view.getCampoCliCidade().getText();
        String UF = this.view.getCampoCliEstado().getText();
        
        if("cadastrar".equals (destino)){
            codigo = "0";
        }
        Cliente modelo = new Cliente( Integer.parseInt(codigo), nome, CPF, telefone, 
                email, cep, logradouro, numero, complemento, bairo, municipio, UF);
        return modelo;
    }
    //mostrar no console
    public void mostrarDados(Cliente modelo){
        System.out.println("Dados do usuário!");
        System.out.println(modelo.getCod());
        System.out.println(modelo.getNome());
        System.out.println(modelo.getCpf());
        System.out.println(modelo.getTelefone());
        System.out.println(modelo.getEmail());
        System.out.println(modelo.getCep());
        System.out.println(modelo.getLogradouro());
        System.out.println(modelo.getNumero());
        System.out.println(modelo.getComplemento());
        System.out.println(modelo.getBairro());
        System.out.println(modelo.getMunicipio());
        System.out.println(modelo.getUf());
        
        
    }
    public void CarregaTabelaClientes(){
        System.out.println("Carregando tabela!");
        this.ListaClientes = this.dao.Listar(); // busca no banco
        DefaultTableModel modelo = new DefaultTableModel(
        new Object [] {"Nome", "CPF", "Telefone", "Cidade"},0 );
        for(int i = 0; i < this.ListaClientes.size(); i++){
            Object linha [] = new Object []{ // criar um linha
                this.ListaClientes.get(i).getNome(),
                this.ListaClientes.get(i).getCpf(),
                this.ListaClientes.get(i).getTelefone(),
                this.ListaClientes.get(i).getMunicipio()
            };
            modelo.addRow(linha); // adiciona linha ao modelo
        }
        this.view.getTabelaClientes().setModel(modelo); //insere modelo 
        this.view.getBntAtualizar().setEnabled(false); // desabilitar
    }

    public void limparCampos() {
       this.view.getCampoCliCod().setText("");
       this.view.getCampoCliNome().setText("");
       this.view.getCampoCliCPF().setText("");
       this.view.getCampoCliTel().setText("");
       this.view.getCampoCliEmail().setText("");
       this.view.getCampoCliLogr().setText("");
       this.view.getCampoCliNumero().setText("");
       this.view.getCampoCliCompl().setText("");
       this.view.getCampoCliBairro().setText("");
       this.view.getCampoCliCidade().setText("");
       this.view.getCampoCliEstado().setText("");
       this.view.getBtnCadastrar().setEnabled(true);
       this.view.getBntLimpar().setEnabled(true);
       this.view.getBntAtualizar().setEnabled(false);
         
    }
    
        public void SetarModelo(Cliente modelo ) {
       this.view.getCampoCliCod().setText(Integer.toString(modelo.getCod()));
       this.view.getCampoCliNome().setText(modelo.getNome());
       this.view.getCampoCliCPF().setText(modelo.getCpf());
       this.view.getCampoCliTel().setText(modelo.getTelefone());
       this.view.getCampoCliEmail().setText(modelo.getEmail());
       this.view.getCampoCliCep().setText(modelo.getCep());
       this.view.getCampoCliLogr().setText(modelo.getLogradouro());
       this.view.getCampoCliNumero().setText(modelo.getNumero());
       this.view.getCampoCliCompl().setText(modelo.getComplemento());
       this.view.getCampoCliBairro().setText(modelo.getBairro());
       this.view.getCampoCliCidade().setText(modelo.getMunicipio());
       this.view.getCampoCliEstado().setText(modelo.getUf());
       //desabilita botoes que não é pra usar
       this.view.getBtnCadastrar().setEnabled(false);
       this.view.getBntLimpar().setEnabled(false);
       this.view.getBntAtualizar().setEnabled(true);
    
        }
    
    
}
