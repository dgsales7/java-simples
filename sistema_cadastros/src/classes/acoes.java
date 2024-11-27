package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class acoes {
	private int id;
	private String usuario;
	private String senha;
	
	public acoes(int id_p) {
		this.id= id_p; 
	}
	
	public acoes(String usuario_p, String senha_p) {
		this.usuario = usuario_p;
		this.senha = senha_p;
	}
	
	public acoes(int id_p, String usuario_p, String senha_p) {
		this.id = id_p;
		this.usuario = usuario_p;
		this.senha = senha_p;
	}
	
	public void salvar() {
		
		Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            // Estabelecer a conexão
            con = Conexao.faz_conexao();
            
            // Comando SQL para inserir dados
            String sql = "INSERT INTO dados_senhas (usuario, senha) VALUES (?, ?)";
            stmt = con.prepareStatement(sql);

            // Configurar os valores nos placeholders
            stmt.setString(1, this.usuario);
            stmt.setString(2, this.senha);

            // Executar o comando
            stmt.executeUpdate();

            // Mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            
        } catch (SQLException e) {
            // Mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados: " + e.getMessage());
        } finally {
            // Fechar recursos
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
	