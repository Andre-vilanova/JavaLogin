/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author Andre
 * @param <T>
 */
public interface InterfaceDao <T>{
    public boolean adicionar (T t);
    public ArrayList<T> Listar();
    public void alterar(int index,T t);
    public void excluir (int index);
    
    
}
