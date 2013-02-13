package tela;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import net.miginfocom.swing.MigLayout;
import conexao.Conexao;
import entidades.Clien;
import entidades.Ender;
import entidades.Forne;
import entidades.Grcli;
import java.awt.Color;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;

	private JCheckBox cboxPRODU;
	private JCheckBox cboxFABRI;
	private JCheckBox cboxFORNE;
	private JCheckBox cboxCLIEN;
	private JCheckBox cboxENDER;
	private JCheckBox cboxGRCLI;
	private JCheckBox cboxPRXLJ;
	private JTextField txtVmdServidor;
	private JTextField txtVmdBanco;
	private JTextField txtVmdServidorConsulta;
	private JTextField txtVmdBancoConsulta;
	private JButton btn_limpa_dados;
	private JButton btn_processa;
	private JProgressBar progressBar;
	public 	JProgressBar progressBar2;
	private JLabel lblVmdDeConsulta;
	private JLabel lblBanco;
	private JLabel lbl_tabela;
	private JLabel lbl_registros;
	private JLabel lblServidor;
	private JLabel lblServidor_1;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JLabel lblNewLabel_7;
	private JTextField txtVMDUsuario;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JTextField txtVmdUsuarioConsulta;
	private JLabel lblNewLabel_10;
	private JPasswordField txtVMDSenha;
	private JPasswordField txtVmdSenhaConsulta;
	private JLabel lbl_arq_fabri;
	private JLabel lbl_arq_produ;
	private JLabel lbl_arq_prxlj;
	private JLabel lbl_arq_forne;
	private JLabel lbl_arq_grcli;
	private JLabel lbl_arq_clien;
	private JLabel lbl_arq_ender;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
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
	public App() {
		setTitle("inFarma - Conversor de dados");
		setResizable(false);
		setBounds(100, 100, 609, 493);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelTop = new JPanel();
		getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new MigLayout("", "[fill][][grow][grow][grow][][]", "[][][21.00][][][8.00]"));

		JLabel lblNewLabel_1 = new JLabel("VMD");
		panelTop.add(lblNewLabel_1, "cell 0 0,alignx trailing");
		
		lblServidor = new JLabel("Servidor");
		panelTop.add(lblServidor, "cell 1 0,alignx trailing");

		txtVmdServidor = new JTextField();
		txtVmdServidor.setText("localhost");
		panelTop.add(txtVmdServidor, "cell 2 0,growx");
		txtVmdServidor.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Banco");
		panelTop.add(lblNewLabel_2, "cell 3 0,alignx trailing,aligny baseline");

		txtVmdBanco = new JTextField();
		txtVmdBanco.setText("VMD");
		panelTop.add(txtVmdBanco, "cell 4 0,growx");
		txtVmdBanco.setColumns(15);
		
		lblNewLabel_7 = new JLabel("Usu\u00E1rio");
		panelTop.add(lblNewLabel_7, "cell 1 1,alignx trailing");
		
		txtVMDUsuario = new JTextField();
		txtVMDUsuario.setText("sa");
		panelTop.add(txtVMDUsuario, "cell 2 1,growx");
		txtVMDUsuario.setColumns(10);
		
		lblNewLabel_8 = new JLabel("Senha");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		panelTop.add(lblNewLabel_8, "cell 3 1,alignx trailing");
		
		txtVMDSenha = new JPasswordField();
		txtVMDSenha.setText("vls021130");
		panelTop.add(txtVMDSenha, "cell 4 1,growx");
		
		separator_1 = new JSeparator();
		panelTop.add(separator_1, "cell 0 2 7 1");
		
		lblVmdDeConsulta = new JLabel("VMD Consulta");
		panelTop.add(lblVmdDeConsulta, "cell 0 3,alignx trailing");
		
		lblServidor_1 = new JLabel("Servidor");
		panelTop.add(lblServidor_1, "cell 1 3,alignx trailing");
		
		txtVmdServidorConsulta = new JTextField();
		txtVmdServidorConsulta.setText("localhost");
		panelTop.add(txtVmdServidorConsulta, "cell 2 3,growx");
		txtVmdServidorConsulta.setColumns(10);
		
		lblBanco = new JLabel("Banco");
		panelTop.add(lblBanco, "cell 3 3,alignx trailing");
		
		txtVmdBancoConsulta = new JTextField();
		txtVmdBancoConsulta.setText("VMD");
		panelTop.add(txtVmdBancoConsulta, "cell 4 3,growx");
		txtVmdBancoConsulta.setColumns(15);
		
		lblNewLabel_9 = new JLabel("Usu\u00E1rio");
		panelTop.add(lblNewLabel_9, "cell 1 4,alignx trailing");
		
		txtVmdUsuarioConsulta = new JTextField();
		txtVmdUsuarioConsulta.setText("sa");
		panelTop.add(txtVmdUsuarioConsulta, "cell 2 4,growx");
		txtVmdUsuarioConsulta.setColumns(10);
		
		lblNewLabel_10 = new JLabel("Senha");
		panelTop.add(lblNewLabel_10, "cell 3 4,alignx trailing");
		
		txtVmdSenhaConsulta = new JPasswordField();
		txtVmdSenhaConsulta.setText("vls021130");
		panelTop.add(txtVmdSenhaConsulta, "cell 4 4,growx");
		
		separator_2 = new JSeparator();
		panelTop.add(separator_2, "cell 0 5 7 1");

		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);

		class ProcessaWorker extends SwingWorker<Void, Void> {

			@Override
			protected Void doInBackground() throws Exception {
				progressBar.setValue(0);
				progressBar.setMaximum(14);
				btn_limpa_dados.setEnabled(false);
				btn_processa.setEnabled(false);
				
				int resp = JOptionPane.showConfirmDialog(panel, "Confirma?", 
						   "Processar Dados", JOptionPane.YES_NO_OPTION);
				if (resp == 0) {
					Forne forne = new Forne();	
//					Fabri fabri = new Fabri();
					Grcli grcli = new Grcli(); 
					Clien clien = new Clien();	
					Ender ender = new Ender();	
//					Produ produ = new Produ();	
//					Prxlj prxlj = new Prxlj();
					if (cboxFABRI.isSelected() && cboxPRODU.isSelected() 
						 && cboxCLIEN.isSelected() && cboxFORNE.isSelected() 
						 && cboxENDER.isSelected() && cboxGRCLI.isSelected()) {
						
						// APAGANDO DADOS
						progressBar.setValue(progressBar.getValue() + 1);
						deleta("PRXLJ"); deleta("PRODU"); deleta("FABRI"); deleta("FORNE");
						deleta("CLIEN"); deleta("GRCLI"); deleta("CLXED"); deleta("ENDER");
						progressBar.setValue(progressBar.getValue() + 1);
					}

					//IMPORTAÇÃO
					//FABRI
					if (cboxFABRI.isSelected()) {
						System.out.println("COMEÇOU FABRI");
						progressBar.setValue(progressBar.getValue() + 1);
						deleta("FABRI");
						lbl_tabela.setText("Importando FABRI");
//						fabri.importa(progressBar2, txtRegistros);
						progressBar.setValue(progressBar.getValue() + 1);
					}
						
					//PRODU
					if (cboxPRODU.isSelected()) {
						System.out.println("COMEÇOU PRODU");
						progressBar.setValue(progressBar.getValue() + 1);
						deleta("PRXLJ");
						deleta("PRODU");
						lbl_tabela.setText("Importando PRODU");
//						produ.importa(progressBar2, txtRegistros);
						progressBar.setValue(progressBar.getValue() + 1);
					}
					
					//PRXLJ
					if (cboxPRXLJ.isSelected()) {
						System.out.println("COMEÇOU PRXLJ");
						progressBar.setValue(progressBar.getValue() + 1);
						lbl_tabela.setText("Importando PRXLJ");
//						prxlj.importa(progressBar2, txtRegistros);
						progressBar.setValue(progressBar.getValue() + 1);
					}
					
					//FORNE					
					if (cboxFORNE.isSelected()) {
						System.out.println("COMEÇOU FORNE");
						progressBar.setValue(progressBar.getValue() + 1);
						deleta("FORNE");
						lbl_tabela.setText("Importando FORNE");
						forne.importa(progressBar2, lbl_registros, lbl_arq_forne.getText());
						progressBar.setValue(progressBar.getValue() + 1);
					}
					
					// GRCLI
					if (cboxGRCLI.isSelected()) {
						System.out.println("COMEÇOU GRCLI");
						progressBar.setValue(progressBar.getValue() + 1);
						deleta("GRCLI");
						lbl_tabela.setText("Importando GRCLI");
						grcli.importa(progressBar2, lbl_registros);
						progressBar.setValue(progressBar.getValue() + 1);
					}
					
					//CLIEN
					if (cboxCLIEN.isSelected()) {
						System.out.println("COMEÇOU CLIEN");
						progressBar.setValue(progressBar.getValue() + 1);
						deleta("CLIEN");
						lbl_tabela.setText("Importando CLIEN");
						clien.importa(progressBar2, lbl_registros);
						progressBar.setValue(progressBar.getValue() + 1);
					}
					
					//ENDER
					if (cboxENDER.isSelected()) {
						System.out.println("COMEÇOU ENDER");
						progressBar.setValue(progressBar.getValue() + 1);
						deleta("CLXED");
						deleta("ENDER");
						lbl_tabela.setText("Importando ENDER");
						ender.importa(progressBar2, lbl_registros);
						progressBar.setValue(progressBar.getValue() + 1);
					}
					progressBar.setValue(progressBar.getMaximum());
					
					JOptionPane.showMessageDialog(getContentPane(),
							"Processamento de dados realizado com sucesso",
							"Informação", JOptionPane.INFORMATION_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(getContentPane(),
							"Processamento de dados cancelado", "Informação",
							JOptionPane.INFORMATION_MESSAGE);
				}
				return null;
			}
			
			@Override
			protected void done() {
				try {
					progressBar.setValue(0);
					lbl_tabela.setText("");
					lbl_registros.setText("");
					lbl_arq_forne.setText("");
					btn_limpa_dados.setEnabled(true);
					btn_processa.setEnabled(true);
					getContentPane().setCursor(Cursor.getDefaultCursor());
					// Descobre como está o processo. É responsável por lançar
					// as exceptions
					get();
			
				} catch (ExecutionException e) {
					final String msg = String.format(
							"Erro ao exportar dados: %s", e.getCause()
									.toString());
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							JOptionPane.showMessageDialog(getContentPane(),
									"Erro ao exportar: " + msg, "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
					});
				} catch (InterruptedException e) {
					System.out.println("Processo de exportação foi interrompido");
				}
			}
		}

		btn_processa = new JButton("Processa");
		btn_processa.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Conexao.SQL_BANCO = txtVmdBanco.getText();
				Conexao.SQL_SERVIDOR = txtVmdServidor.getText();
				Conexao.SQL_USUARIO = txtVMDUsuario.getText();
				Conexao.SQL_SENHA = txtVMDSenha.getText();
				Conexao.SQL_SERVIDOR_CONSULTA = txtVmdServidorConsulta.getText();
				Conexao.SQL_BANCO_CONSULTA = txtVmdBancoConsulta.getText();
				Conexao.SQL_USUARIO_CONSULTA = txtVmdUsuarioConsulta.getText();
				Conexao.SQL_SENHA_CONSULTA = txtVmdSenhaConsulta.getText();
				new ProcessaWorker().execute();
			}
		});

		class LimpaDadosWorker extends SwingWorker<Void, Void> {

			@Override
			protected Void doInBackground() throws Exception {
				progressBar.setValue(0);
				progressBar.setMaximum(7);
				btn_limpa_dados.setEnabled(false);
				btn_processa.setEnabled(false);
				int resp = JOptionPane.showConfirmDialog(panel, "Confirma?",
						"Limpeza de Dados", JOptionPane.YES_NO_OPTION);

				if(resp == 0){
				
				// APAGANDO DADOS 
				// PRODUTO
				if (cboxPRODU.isSelected()) {
					deleta("PRXLJ");
					deleta("PRODU");
					progressBar.setValue(progressBar.getValue() + 1);
				}
				
				if (cboxPRXLJ.isSelected()) {
					deleta("PRXLJ");
					progressBar.setValue(progressBar.getValue() + 1);
				}

				// FABRI
				if (cboxFABRI.isSelected()) {
					deleta("FABRI");
					progressBar.setValue(progressBar.getValue() + 1);
				}

				//FORNE					
				if (cboxFORNE.isSelected()) {
					deleta("FORNE");
					progressBar.setValue(progressBar.getValue() + 1);
				}
				
				//CLIEN					
				if (cboxCLIEN.isSelected()) {
					deleta("CLIEN");
					progressBar.setValue(progressBar.getValue() + 1);
				}
				
				//GRCLI					
				if (cboxGRCLI.isSelected()) {
					deleta("GRCLI");
					progressBar.setValue(progressBar.getValue() + 1);
				}
				
				//ENDER
				if (cboxENDER.isSelected()) {
					deleta("CLXED");
					deleta("ENDER");
					progressBar.setValue(progressBar.getMaximum());
				}
				
				JOptionPane.showMessageDialog(getContentPane(),
						"Limpeza de dados realizada com sucesso",
						"Informação", JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(getContentPane(),
						"Limpeza de dados cancelada", "Informação",
						JOptionPane.INFORMATION_MESSAGE);
			}
				return null;
			}

			@Override
			protected void done() {
				try {
					progressBar.setValue(0);
					btn_limpa_dados.setEnabled(true);
					btn_processa.setEnabled(true);
					getContentPane().setCursor(Cursor.getDefaultCursor());

					// Descobre como está o processo. É responsável por lançar
					// as exceptions
					get();

				} catch (ExecutionException e) {
					final String msg = String.format("Erro ao limpar dados: %s", e.getCause().toString());
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							JOptionPane.showMessageDialog(getContentPane(),
									"Erro ao limpar: " + msg, "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
					});
				} catch (InterruptedException e) {
					System.out.println("Processo de exportação foi interrompido");
				}
			}
		}

		btn_limpa_dados = new JButton("Limpa Dados");
		btn_limpa_dados.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Conexao.SQL_BANCO = txtVmdBanco.getText();
				Conexao.SQL_SERVIDOR = txtVmdServidor.getText();
				Conexao.SQL_USUARIO = txtVMDUsuario.getText();
				Conexao.SQL_SENHA = txtVMDSenha.getText();
				Conexao.SQL_SERVIDOR_CONSULTA = txtVmdServidorConsulta.getText();
				Conexao.SQL_BANCO_CONSULTA = txtVmdBancoConsulta.getText();
				Conexao.SQL_USUARIO_CONSULTA = txtVmdUsuarioConsulta.getText();
				Conexao.SQL_SENHA_CONSULTA = txtVmdSenhaConsulta.getText();
				new LimpaDadosWorker().execute();
			}
		});
		panel.add(btn_limpa_dados);
		panel.add(btn_processa);

		JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("");
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[][][][][][][][][grow,fill]", "[][][][][][][][][][][][]"));
		
		JLabel lbl_texto_painel_1 = new JLabel("Converte uma base para o Varejo");
		panel_1.add(lbl_texto_painel_1, "cell 0 0 4 1");

		cboxFABRI = new JCheckBox("1-FABRI");
		cboxFABRI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cboxFABRI.isSelected()) {
					JFileChooser abrir = new JFileChooser();  
					int retorno = abrir.showOpenDialog(null);
					if (retorno==JFileChooser.APPROVE_OPTION) {
						File file = new File(abrir.getSelectedFile().getAbsolutePath());
						if (file != null)
							lbl_arq_fabri.setText(file.getPath());
					} else {
						cboxFABRI.setSelected(false);
					}
				} else {
					lbl_arq_fabri.setText("");
					cboxFABRI.setSelected(false);
				}
			}
		});
		panel_1.add(cboxFABRI, "cell 0 1");
		
		lbl_arq_fabri = new JLabel("");
		lbl_arq_fabri.setForeground(Color.BLUE);
		panel_1.add(lbl_arq_fabri, "cell 1 1 7 1");

		cboxPRODU = new JCheckBox("2-PRODU");
		panel_1.add(cboxPRODU, "cell 0 2");
		
		lbl_arq_produ = new JLabel("");
		lbl_arq_produ.setForeground(Color.BLUE);
		panel_1.add(lbl_arq_produ, "cell 1 2 7 1");
		
		cboxPRXLJ = new JCheckBox("3-PRXLJ");
		panel_1.add(cboxPRXLJ, "cell 0 3");
		
		lbl_arq_prxlj = new JLabel("");
		lbl_arq_prxlj.setForeground(Color.BLUE);
		panel_1.add(lbl_arq_prxlj, "cell 1 3 7 1");
		
		cboxFORNE = new JCheckBox("4-FORNE");
		cboxFORNE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cboxFORNE.isSelected()) {
					JFileChooser abrir = new JFileChooser();  
					int retorno = abrir.showOpenDialog(null);
					if (retorno==JFileChooser.APPROVE_OPTION) {
						File file = new File(abrir.getSelectedFile().getAbsolutePath());
						if (file != null)
							lbl_arq_forne.setText(file.getPath());
					} else {
						cboxFABRI.setSelected(false);
					}
				} else {
					lbl_arq_forne.setText("");
					cboxFABRI.setSelected(false);
				}
			}
		});
		panel_1.add(cboxFORNE, "cell 0 4");
		
		lbl_arq_forne = new JLabel("");
		lbl_arq_forne.setForeground(Color.BLUE);
		panel_1.add(lbl_arq_forne, "cell 1 4 7 1");
		
		cboxGRCLI = new JCheckBox("5-GRCLI");
		panel_1.add(cboxGRCLI, "cell 0 5");
		
		lbl_arq_grcli = new JLabel("");
		lbl_arq_grcli.setForeground(Color.BLUE);
		panel_1.add(lbl_arq_grcli, "cell 1 5 7 1");
		
		cboxCLIEN = new JCheckBox("6-CLIEN");
		panel_1.add(cboxCLIEN, "cell 0 6");
		
		lbl_arq_clien = new JLabel("");
		lbl_arq_clien.setForeground(Color.BLUE);
		panel_1.add(lbl_arq_clien, "cell 1 6 7 1");
		
		cboxENDER = new JCheckBox("7-ENDER");
		panel_1.add(cboxENDER, "cell 0 7");
		
		lbl_arq_ender = new JLabel("");
		lbl_arq_ender.setForeground(Color.BLUE);
		lbl_arq_ender.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel_1.add(lbl_arq_ender, "cell 1 7 7 1");
		
		lbl_tabela = new JLabel("");
		panel_1.add(lbl_tabela, "flowx,cell 0 8 2 1");
		
		lbl_registros = new JLabel("");
		panel_1.add(lbl_registros, "cell 2 8 2 1");

		progressBar = new JProgressBar();
		panel_1.add(progressBar, "cell 0 10 9 1,growx");

		progressBar2 = new JProgressBar();
		panel_1.add(progressBar2, "cell 0 11 9 1,growx");

	}

	@SuppressWarnings("unused")
	public int contaRegistros(String tabela) throws FileNotFoundException {
		 LineNumberReader lineCounter = new LineNumberReader(new InputStreamReader(new FileInputStream(tabela)));  
	        String nextLine = null;  
	        try {  
	            while ((nextLine = lineCounter.readLine()) != null) {  
	                if (nextLine == null)
						break;  
	            }  
	        } catch (Exception done) {  
	            System.out.println(done.getMessage());  
	        }
			return lineCounter.getLineNumber() - 1;  
	    }  
	
//	public int contaRegistrosVMD(String tabela) throws SQLException {
//		String sql = "SELECT count(*) qtde FROM " + tabela;
//			try (PreparedStatement ps = Conexao.getSqlConnectionAux().prepareStatement(sql);
//					ResultSet rs = ps.executeQuery()) {
//				if (rs.next()) {
//					return rs.getInt(1);
//			}
//			return 0;
//		}
//	}
	
	public void deleta(String tabela) throws Exception {
		try {
			Statement stmt = Conexao.getSqlConnection().createStatement();
			stmt.executeUpdate("DELETE FROM " +tabela);
			stmt.close();
			System.out.println("Deletou " +tabela);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public JCheckBox getCboxENDER() {
		return cboxENDER;
	}
	public JCheckBox getCboxGRCLI() {
		return cboxGRCLI;
	}
	public JCheckBox getCboxFABRI() {
		return cboxFABRI;
	}
	public JCheckBox getCboxPRODU() {
		return cboxPRODU;
	}
	public JCheckBox getCboxFORNE() {
		return cboxFORNE;
	}
	public JCheckBox getCboxPRXLJ() {
		return cboxPRXLJ;
	}
	public JCheckBox getCboxCLIEN() {
		return cboxCLIEN;
	}
}