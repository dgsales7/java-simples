package classes_conexoes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfUsuario;
	private JTextField tfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadastro frame = new Tela_cadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela_cadastro() {
		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(10, 27, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setBounds(10, 68, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setBounds(10, 110, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(77, 24, 66, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfUsuario = new JTextField();
		tfUsuario.setColumns(10);
		tfUsuario.setBounds(77, 65, 119, 20);
		contentPane.add(tfUsuario);
		
		tfSenha = new JTextField();
		tfSenha.setColumns(10);
		tfSenha.setBounds(77, 107, 119, 20);
		contentPane.add(tfSenha);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 196, 403, 54);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				  try {
					Connection con = Conexao.faz_conexao();
					String sql = "insert into dados_senhas(usuario,senha) value(?,?) ";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, tfSenha.getText());
					
					stmt.execute();
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "CADASTRADOOOOO!!!");
					
					tfUsuario.setText("");
					tfSenha.setText("");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
					  
				  
				
				  
				  
			}
		});
		btnNewButton.setBounds(10, 20, 89, 23);
		panel.add(btnNewButton);
	}
}
