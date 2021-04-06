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
public class Fornecedor {
    private int codigo;
    private  String nome;
    private  String cnpj;
    private  String telefone;
    private  String fax;
    private  String email;
    private  String site;

    public Fornecedor() {
    }

    public Fornecedor(int codigo) {
        this.codigo = codigo;
    }
    

    public Fornecedor(int codigo, String nome, String cnpj, String telefone, String fax, String email, String site) {
        this.codigo = codigo;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.fax = fax;
        this.email = email;
        this.site = site;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
    
    @Override
    public String toString(){
        return getNome();
    }
    
    
}

