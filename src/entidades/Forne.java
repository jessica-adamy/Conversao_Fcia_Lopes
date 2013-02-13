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

public class Forne {
	Connection vmd = Conexao.getSqlConnection();
	App a = new App();
	
	private FileReader fr;
	private BufferedReader br;
	
	@SuppressWarnings("unused")
	private String[] cabecalho;
	private String[] linha;
	
	public void importa(JProgressBar progressBar2, JLabel lblNewLabel_5, String arquivo) throws Exception {
		System.out.println(arquivo);
		String vFORNE = "Insert Into FORNE (Cod_Fornec, Des_RazSoc, Des_Fantas, Num_CgcCpf, Num_CgfRg, Des_Cidade, Des_Estado, Cod_RegTri, Cod_IBGE, Des_Endere, Des_Bairro, Num_Cep, Num_Fone, Num_Fax, Nom_Contat, Des_Observ, Flg_Bloque) Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0)";
		try {
			PreparedStatement pVmd = vmd.prepareStatement(vFORNE);
			File file = new File(arquivo);
				// Abrir
				fr = new FileReader(file); 
				br = new BufferedReader(fr);
		
				// Armazena Cabeçalho
				cabecalho = br.readLine().replace("\"", "").split(";");
				
				// contar a qtde de registros
				int registros = a.contaRegistros(arquivo);
				int total = registros;
				progressBar2.setMaximum(registros);
				registros = 0;
				
				// grava no varejo
				while(br.ready()) {
					linha = br.readLine().replace("\"", "").split(";");
					pVmd.setInt(1, Integer.parseInt(linha[1]));
					
					String nome = linha[2];
					if (nome != null) {
						pVmd.setString(2, nome.length() > 35 ? nome.substring(0,35) : nome);
						pVmd.setString(3, nome.length() > 25 ? nome.substring(0,25) : nome);
					}
					
					String cnpj = linha[3];
					if (cnpj != null) {
						cnpj = cnpj.replaceAll("\\D", "");
					}
					pVmd.setString(4, cnpj);
					
					String insc_est = linha[4];
					if (insc_est != null) {
						insc_est = insc_est.replaceAll("\\D", "");
					}
					pVmd.setString(5, insc_est);
					
					String cidade = linha[6];
					if (cidade != null) {
						pVmd.setString(6, cidade.length() > 25 ? cidade.substring(0,15) : cidade);
					}
					
					String uf = linha[7];
					if (uf != null) {
						pVmd.setString(7, uf.length() > 2 ? uf.substring(0,2) : uf);
					}
					
					if (uf.equals("CE")) {
						pVmd.setInt(8, 4);
					} else {
						if(uf.equals("AC") || uf.equals("AL") || uf.equals("AP") ||
						   uf.equals("AM") || uf.equals("BA") || uf.equals("DF") ||
						   uf.equals("GO") || uf.equals("MA") || uf.equals("MT") ||
						   uf.equals("MS") || uf.equals("PB") || uf.equals("PA") ||
						   uf.equals("PE") || uf.equals("PI") || uf.equals("RN") ||
						   uf.equals("RO") || uf.equals("RR") || uf.equals("SE") || uf.equals("TO")){
							pVmd.setInt(8, 1);
						} else {
							if(uf.equals("ES") || uf.equals("MG") ||
							   uf.equals("PR") || uf.equals("RJ") || 
							   uf.equals("RS") || uf.equals("SC") || 
							   uf.equals("SP")){
								pVmd.setInt(8, 9);
							}else {
								pVmd.setString(8, null);
							}
					    }
					 } 
					
					pVmd.setString(9, linha[62]);
					
					String endereco = linha[5];
					if (endereco != null) {
						pVmd.setString(10, endereco.length() > 35 ? endereco.substring(0,35) : endereco);
					}
					
					String bairro = linha[65];
					if (bairro != null) {
						pVmd.setString(11, bairro.length() > 25 ? bairro.substring(0,25) : bairro);
					}
					
					String cep = linha[8];
					if (cep != null) {
						cep = cep.replaceAll("\\D", "");
					}
					pVmd.setString(12, cep);
					
					String tel = linha[9];
					if (tel != null) {
						tel = tel.replaceAll("\\D", "");
						pVmd.setString(13, tel.length() > 11 ? tel.substring(0,11) : tel);
					}
					
					String fax = linha[10];
					if (fax != null) {
						fax = fax.replaceAll("\\D", "");
						pVmd.setString(14, fax.length() > 11 ? fax.substring(0,11) : fax);
					}
					
					String contato = linha[21];
					if (contato != null) {
						pVmd.setString(15, contato.length() > 25 ? contato.substring(0,25) : contato);
					}
					
					String obs = linha[20];
					if (obs != null) {
						pVmd.setString(16, obs.length() > 16 ? obs.substring(0,16) : obs);
						;
					}
					
					pVmd.executeUpdate();
					
					registros++;
					lblNewLabel_5.setText(registros+"/"+total);
					progressBar2.setValue(registros);
				}
			    
				System.out.println("Funcionou FORNE");
				pVmd.close();
				
			}catch (Exception e) {
				System.out.println("ERRO = " +e.getMessage());
			}
		
			progressBar2.setValue(0);

		}
	}