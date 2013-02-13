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

public class Ender {
	Connection vmd = Conexao.getSqlConnection();
	App a = new App();
	
	private FileReader fr;
	private BufferedReader br;
	
	@SuppressWarnings("unused")
	private String[] cabecalho;
	private String[] linha;
	
	public void importa(JProgressBar progressBar2, JLabel lblNewLabel_5) throws Exception {
		String vEnder = "Insert Into Ender (Cod_EndFon, Des_Endere, Des_Bairro, Num_Cep, Des_Cidade, Des_Estado, Nom_Contat, Dat_Cadast) Values (?,?,?,?,?,?,?,?)";
		String vCLXED = "Insert Into CLXED (Cod_Client, Cod_EndFon) Values (?,?)";
		try {
			PreparedStatement pVmd = vmd.prepareStatement(vEnder);
			PreparedStatement pVmd2 = vmd.prepareStatement(vCLXED);
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

					// grava no varejo
//					String codigo = linha[1];
					String tel = linha[9];
//					if(tel != null && !tel.replaceAll("\\D", "").equals("")) {
//						tel = tel.replaceAll("\\D", "");
//						pVmd.setString(1, tel);
//					}else{
//						pVmd.setString(1, tel);
//					}
					
					System.out.println("codigo = "+linha[1]);
					String codigo = linha[1];
					if (codigo != null) {
						codigo = codigo.replaceAll("\\D", "");
						pVmd.setString(1, codigo);
					}
					
					String endereco = linha[5];
					if(endereco != null) {
						pVmd.setString(2, endereco.length() > 45 ? endereco.substring(0, 45) : endereco);
					}else{
						pVmd.setString(2, endereco);
					}
					
					String bairro = linha[29];
					if(bairro != null) {
						pVmd.setString(3, bairro.length() > 25 ? bairro.substring(0, 25) : bairro);
					}else{
						pVmd.setString(3, bairro);
					}
					
					String cep = linha[7];
					if(cep != null) {
						cep = cep.replaceAll("\\D", "");
					}
					pVmd.setString(4, cep);
					
					String cidade = linha[6];
					if(cidade != null) {
						pVmd.setString(5, cidade.length() > 25 ? cidade.substring(0, 25) : cidade);
					}else{
						pVmd.setString(5, cidade);
					}
					
					String estado = linha[31];
					if(estado != null) {
						pVmd.setString(6, estado.length() > 2 ? estado.substring(0, 2) : estado);
					}else{
						pVmd.setString(6, estado);
					}
					
					String contato = linha[2];
					if(contato != null) {
						pVmd.setString(7, contato.length() > 35 ? contato.substring(0, 35) : contato);
					}else{
						pVmd.setString(7, contato);
					}
					
					String data_cadastro = linha[42];
					if (data_cadastro != null && !data_cadastro.replaceAll(" ", "").equals("")) {
						try{
						DateFormat forma = new SimpleDateFormat("dd/MM/yyyy");  
				        java.sql.Date data = new java.sql.Date(forma.parse(data_cadastro).getTime());
				        pVmd.setDate(8, data);
						} catch (Exception e) {
							pVmd.setString(8, null);
						}
					} else
						pVmd.setString(8, null);
					
					pVmd.executeUpdate();
					
					//CLXED
					pVmd2.setString(1, codigo);
					
					if(tel != null) {
						pVmd2.setString(2, codigo);
					}else{
						pVmd2.setString(2, codigo);
					}
					
					pVmd2.executeUpdate();
					
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