package entidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import tela.App;
import conexao.Conexao;

public class Clien {
	Connection vmd = Conexao.getSqlConnection();
	App a = new App();
	
	private FileReader fr;
	private BufferedReader br;
	
	@SuppressWarnings("unused")
	private String[] cabecalho;
	private String[] linha;
	
	public void importa(JProgressBar progressBar2, JLabel lblNewLabel_5) throws Exception {
		String vClien = "Insert Into CLIEN (Cod_Client, Nom_Client, Dat_Cadast, Cod_GrpCli, Sex_Client, Num_CpfCgc, Num_RgCgf, Num_FonCel, Des_Email, Cod_EndRes, Cod_RegTri) Values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pVmd = vmd.prepareStatement(vClien);
			File file = new File("C:/CONVERSOR/Tabelas/CadclienData.txt");
				// Abrir
				fr = new FileReader(file); 
				br = new BufferedReader(fr);
		
				// Armazena Cabeçalho
				cabecalho = br.readLine().replace("\"", "").split(";");
				
				// contar a qtde de registros
				int registros = a.contaRegistros("C:/CONVERSOR/Tabelas/CadclienData.txt");
				int total = registros;
				progressBar2.setMaximum(registros);
				registros = 0;
				
				// grava no varejo
				while (br.ready()) {
					linha = br.readLine().replace("\"", "").split(";");
					
					System.out.println("codigo = "+linha[1]);
					String codigo = linha[1];
					if (codigo != null) {
						codigo = codigo.replaceAll("\\D", "");
						pVmd.setString(1, codigo);
					}
					
					System.out.println("nome = "+linha[2]);
					String nome = linha[2];
					if(nome != null) {
						pVmd.setString(2, nome.length() > 35 ? nome.substring(0, 35) : nome);
					}else
						pVmd.setString(2, nome);
					
					System.out.println("cadastro = "+linha[42]);
					String data_cadastro = linha[42];
					if (data_cadastro != null && !data_cadastro.replaceAll(" ", "").equals("")) {
						try{
						DateFormat forma = new SimpleDateFormat("dd/MM/yyyy");  
				        java.sql.Date data = new java.sql.Date(forma.parse(data_cadastro).getTime());
				        pVmd.setDate(3, data);
						} catch (Exception e) {
							pVmd.setString(3, null);
						}
					} else
						pVmd.setString(3, null);
					
					String cod_grpcli = linha[13];
					if (cod_grpcli != null && !cod_grpcli.replaceAll("\\D","").equals("")) {
						cod_grpcli = cod_grpcli.replaceAll("\\D","");
						System.out.println("grupo = "+linha[13]);
						int grupo = Integer.parseInt(cod_grpcli);
						pVmd.setInt(4, grupo <= 0 || grupo > 6 ? 1 : grupo);
					} else
						pVmd.setInt(4, 1);
					
					pVmd.setString(5, null);
					
					System.out.println("cnpj = "+linha[3]);
					String cnpj = linha[3];
					if(cnpj != null) {
						cnpj = cnpj.replaceAll("\\D", "");
						pVmd.setString(6, cnpj.length() > 14 ? cnpj.substring(0,14) : cnpj);
					} else 
						pVmd.setString(6, cnpj);
					
					System.out.println("rg = "+linha[4]);
					String rg = linha[4];
					if(rg != null) {
						rg = rg.replaceAll("\\D", "");
						pVmd.setString(7, rg.length() > 15 ? rg.substring(0,15) : rg);
					} else
						pVmd.setString(7, rg);
					
					System.out.println("cel = "+linha[48]);
					String cel = linha[58];
					if(cel != null) {
						cel = cel.replaceAll("\\D", "");
						pVmd.setString(8, cel.length() > 50 ? cel.substring(0,50) : cel);
					} else
						pVmd.setString(8, cel);
					
					System.out.println("email = "+linha[63]);
					String email = linha[63];
					if (email != null) {
						pVmd.setString(9, email.length() > 50 ? email.substring(0,50) : email);	
					} else
						pVmd.setString(10, email);
					
					System.out.println("fone = "+linha[9]);
					String fone = linha[9];
					if(fone != null) {
						fone = fone.replaceAll("\\D", "");
						pVmd.setString(10, fone.length() > 15 ? fone.substring(0,15) : fone); 
					} else
						pVmd.setString(10, fone);
				   
					System.out.println("uf = "+linha[31]);
					String uf = linha[31];
					if(uf != null){
						if (uf.length() > 2) {
							uf = uf.substring(0,2);
						}
//						Região tributártia
					    if(uf.equals("CE")){
					    	pVmd.setInt(11, 4);
					    }else{
					    	if(uf.equals("AC") || uf.equals("AL") || uf.equals("AP") ||
									   uf.equals("AM") || uf.equals("BA") || uf.equals("DF") ||
									   uf.equals("GO") || uf.equals("MA") || uf.equals("MT") ||
									   uf.equals("MS") || uf.equals("PB") || uf.equals("PA") ||
									   uf.equals("PE") || uf.equals("PI") || uf.equals("RN") ||
									   uf.equals("RO") || uf.equals("RR") || uf.equals("SE") || uf.equals("TO")){
								       		pVmd.setInt(11, 1);
							}else{
								if(uf.equals("ES") || uf.equals("MG") ||
								   uf.equals("PR") || uf.equals("RJ") || 
								   uf.equals("RS") || uf.equals("SC") || 
								   uf.equals("SP")){
										pVmd.setInt(11, 9);
								}else{
									pVmd.setInt(11, 4);
								}
							}
					    }
					 }
					pVmd.executeUpdate();
					
					registros++;
					lblNewLabel_5.setText(registros+"/"+total);
					progressBar2.setValue(registros);
				}
			    
				System.out.println("Funcionou PRODU");
				pVmd.close();
				
			}catch (SQLException e) {
				System.out.println("ERRO = " +e.getMessage());
			}
		
			progressBar2.setValue(0);

		}
	}