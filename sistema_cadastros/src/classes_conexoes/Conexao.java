package classes_conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private  static String caminho = "jdbc:mysql://localhost/db_senhas";
	private static String usuario = "root";
	private static String senha = "123456dt";
	
	public static Connection faz_conexao() throws SQLException {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection(caminho, usuario, senha);
		
	} catch (ClassNotFoundException e) {
		
		throw new SQLException("Driver não encontrado: " + e.getMessage(), e);
	}
		
		
	}
		 
	
}
