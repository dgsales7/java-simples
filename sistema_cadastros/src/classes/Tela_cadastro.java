package classes;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class Tela_cadastro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfId;
    private JTextField tfUsuario;
    private JTextField tfSenha;
    private JTextField tfBuscar;
    private JTable tbDados;

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
        setBounds(100, 100, 450, 472);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnNewMenu = new JMenu("Ações");
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Salvar");
        mntmNewMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(tfUsuario.getText().equals("") || tfSenha.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Usuario/Senha em branco");
        			
        		} else {
        		
        		acoes ac = new acoes(tfUsuario.getText(), tfSenha.getText());
        		ac.salvar();
        		
        	   }
        		tfUsuario.setText("");
        		tfSenha.setText("");
        		
        	}
        });
        mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        mnNewMenu.add(mntmNewMenuItem);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("ID");
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
        panel.setBorder(new TitledBorder(null, "Ações", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(10, 312, 403, 54);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton btnNewButton = new JButton("Salvar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarDados();
            }
        });
        btnNewButton.setBounds(10, 20, 89, 23);
        panel.add(btnNewButton);
        
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(tfId.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Informe o Id");
        		}else {
        		
        		try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "update dados_senhas set usuario = ?, senha = ? where id=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, tfSenha.getText());
					stmt.setString(3, tfId.getText());
					
					stmt.execute();
					
					stmt.close();
					con.close();	
					JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}        		
        	  }
            } 
        });
        btnAtualizar.setBounds(109, 20, 89, 23);
        panel.add(btnAtualizar);
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(tfId.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Informe o Id!");
        		}else {
        		
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "delete from dados_Senhas  where id = ?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					 
					stmt.setString(1, tfId.getText());
					
					stmt.execute();
					
					con.close();
					stmt.close();
					
					JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
					
					tfId.setText("");
					tfUsuario.setText("");
					tfSenha.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		}
        		
        
        	}
        });
        btnExcluir.setBounds(208, 20, 89, 23);
        panel.add(btnExcluir);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "Abrir Dados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBackground(new Color(0, 128, 128));
        panel_1.setBounds(10, 377, 403, 45);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        tfBuscar = new JTextField();
        tfBuscar.setBounds(90, 15, 86, 20);
        panel_1.add(tfBuscar);
        tfBuscar.setColumns(10);

        JButton btnAbrir = new JButton("Abrir");
        btnAbrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirDados();
            }
        });
        btnAbrir.setBounds(10, 14, 70, 23);
        panel_1.add(btnAbrir);
        
        JButton btnListarDados = new JButton("Listar Dados");
        btnListarDados.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "SELECT *FROM dados_senhas";
					
					PreparedStatement stmt =  con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
					modelo.setNumRows(0); // Limpa a tabela antes de inserir
					
					
					while (rs.next()) {
						modelo.addRow(new Object[] {
								rs.getString("id"), 
								rs.getString("usuario"),
								rs.getString("senha")
						});
						
					}
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        	}
        });
        btnListarDados.setBounds(278, 14, 115, 23);
        panel_1.add(btnListarDados);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 210, 403, 89);
        contentPane.add(scrollPane);
        
        tbDados = new JTable();
        tbDados.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Id", "Usu\u00E1rio", "Senha"
        	}
        ) {
        	boolean[] columnEditables = new boolean[] {
        		false, true, true
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
        scrollPane.setViewportView(tbDados);
    }

    private void salvarDados() {
    	if(tfUsuario.getText().isEmpty() || tfSenha.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Por favor, preencha o usuário e senha para cadastrar.");
    		return; //Interrompe o método se os campos estiverem vazios
    	}
    	
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.faz_conexao();
            String sql = "INSERT INTO dados_senhas(usuario, senha) VALUES(?, ?)";
            stmt = con.prepareStatement(sql);

            stmt.setString(1, tfUsuario.getText());
            stmt.setString(2, tfSenha.getText());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

            tfUsuario.setText("");
            tfSenha.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    private void abrirDados() {
        if (tfBuscar.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o ID");
            return;
        }

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = Conexao.faz_conexao();
            String sql = "SELECT * FROM dados_senhas WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tfBuscar.getText());

            rs = stmt.executeQuery();

            if (rs.next()) {
                tfId.setText(rs.getString("id"));
                tfUsuario.setText(rs.getString("usuario"));
                tfSenha.setText(rs.getString("senha"));
            } else {
                JOptionPane.showMessageDialog(null, "ID não encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir dados: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
