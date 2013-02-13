package entidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import tela.App;
import conexao.Conexao;

public class Grcli {
	Connection vmd = Conexao.getSqlConnection();
	App a = new App();
	
	private FileReader fr;
	private BufferedReader br;
	
	@SuppressWarnings("unused")
	private String[] cabecalho;
	private String[] linha;
	
	public void importa(JProgressBar progressBar2, JLabel lblNewLabel_5) throws Exception {
		String vGrCli = "Insert Into GRCLI (Cod_Grpcli, Des_Grpcli) Values (?,?)";
		try {
			PreparedStatement pVmd = vmd.prepareStatement(vGrCli);
			File file = new File("C:/CONVERSOR/Tabelas/CadgrcliData.txt");
				// Abrir
				fr = new FileReader(file); 
				br = new BufferedReader(fr);
		
				// Armazena Cabeçalho
				cabecalho = br.readLine().replace("\"", "").split(";");
				
				// contar a qtde de registros
				int registros = a.contaRegistros("C:/CONVERSOR/Tabelas/CadgrcliData.txt");
				int total = registros;
				progressBar2.setMaximum(registros);
				registros = 0;
				
				// grava no varejo
				while (br.ready()) {
					linha = br.readLine().replace("\"", "").split(";");
					
					pVmd.setInt(1, Integer.parseInt(linha[1]));
					
					String nome = linha[2];
					if (nome != null) {
						pVmd.setString(2, nome.length() > 25 ? nome.substring(0,25) : nome);
					}
					
					pVmd.executeUpdate();
					
					registros++;
					lblNewLabel_5.setText(registros+"/"+total);
					progressBar2.setValue(registros);
				}
			    
				System.out.println("Funcionou GRCLI");
				pVmd.close();
				
			}catch (Exception e) {
				System.out.println("ERRO = " +e.getMessage());
			}
		
			progressBar2.setValue(0);

		}
	}