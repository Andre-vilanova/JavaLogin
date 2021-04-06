
package Controller;

import DAO.LoginDao;
import Model.Usuario;
import View.TelaLogin;

/**
 *
 * @author Andre
 */
public class LoginController {
    private final TelaLogin view;
    private final LoginDao dao;

    public LoginController(TelaLogin viewLogin) {
        this.view = viewLogin;
        this.dao = new LoginDao();
    }
    public boolean efetuarLogin(){
        Usuario usuario = this.obterModelo();
        return dao.logarUsuario(usuario);
        
    }
    public String statusConexao() {
        return dao.Conectar();
    }
    public Usuario obterModelo(){
        String login = view.getCampoUsuario().getText();
        String senha = view.getCampoSenha().getText();
        Usuario modelo = new Usuario(login, senha);
        return modelo;
    }
    
    
    
}
