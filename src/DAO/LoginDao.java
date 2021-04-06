
package DAO;

import Model.Usuario;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Andre
 */
public class LoginDao {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public LoginDao() {
    }
    public String Conectar() {
        conexao = ConexaoFactory.getconexao();
        System.out.println(conexao);
        if (conexao != null) {
            return "/Imagens/Imagens/icon/IconBDconectado.png";
        }else{
            return "/Imagens/Imagens/icon/IconBDerror.png";
        }
    }
    
    public boolean logarUsuario (Usuario usuarioLogado) {
        String sql = "Select * from usuario where usulogin=? and ususenha=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usuarioLogado.getLogin());
            pst.setString(2, usuarioLogado.getSenha());
            rs = pst.executeQuery();
            return rs.next();
            
        }catch (SQLException e) {
            return false;
        }
    }
    
}
