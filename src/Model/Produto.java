/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Andre
 */
public class Produto {
    private int codigo;
    private Fornecedor fornecedor;
    private String nome;
    private String descricao;
    private String unMedida; 
    private float valorUnitario;

    public Produto() {
        this.fornecedor = new Fornecedor();
    }

    public Produto(int codigo) {
        this.codigo = codigo;
    }


    

    public Produto(int codigo, Fornecedor fornecedor, String nome, String descricao, String unMedida, float valorUnitario) {
        this.codigo = codigo;
        this.fornecedor = fornecedor;
        this.nome = nome;
        this.descricao = descricao;
        this.unMedida = unMedida;
        this.valorUnitario = valorUnitario;
    }

    public Produto(int codigo, String nome, String descricao, String unMedida, float valorUnitario) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.unMedida = unMedida;
        this.valorUnitario = valorUnitario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(int codigo) {
        this.fornecedor =  new Fornecedor(codigo);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnMedida() {
        return unMedida;
    }

    public void setUnMedida(String unMedida) {
        this.unMedida = unMedida;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    
    
    
    
    
}
