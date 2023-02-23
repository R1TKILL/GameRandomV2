package GameRandom;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Game {

	private JFrame frame;
	private JTextField Insert;
	
	static String path = "points.txt";	
	static File arqu = new File(path);
	
	Random random = new Random();
	
	short victories;
	short defeats;
	
	short value_random = (short) random.nextLong();
	int lifes = 20;
	boolean attempt = false;
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					if (!arqu.exists()){
						try (PrintWriter pw = new PrintWriter(arqu)) {
							arqu.createNewFile();
							pw.print("0;0;Developed by R1TKILL");
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					}					
					Game window = new Game();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Game() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 690, 424);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		
		Panel panelPrincipal = new Panel();
		panelPrincipal.setBounds(0, 0, 690, 396);
		frame.getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JLabel lblWelcomeToGame = new JLabel("Welcome to game");
		lblWelcomeToGame.setFont(new Font("Dialog", Font.BOLD, 18));
		lblWelcomeToGame.setBounds(255, 53, 189, 22);
		panelPrincipal.add(lblWelcomeToGame);
		
		JLabel lblrandomNunber = new JLabel("***Random Number***");
		lblrandomNunber.setFont(new Font("Dialog", Font.BOLD, 20));
		lblrandomNunber.setBounds(222, 85, 267, 22);
		panelPrincipal.add(lblrandomNunber);
		
		JLabel lblDevelopedByRtkill = new JLabel("Developed by R1TKILL");
		lblDevelopedByRtkill.setFont(new Font("Dialog", Font.BOLD, 17));
		lblDevelopedByRtkill.setBounds(22, 159, 222, 22);
		panelPrincipal.add(lblDevelopedByRtkill);
		
		JLabel lblVersion = new JLabel("Version: 1.0.9");
		lblVersion.setFont(new Font("Dialog", Font.BOLD, 17));
		lblVersion.setBounds(500, 159, 143, 22);
		panelPrincipal.add(lblVersion);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Panel panelInformativo = new Panel();
				panelInformativo.setBounds(0, 0, 690, 396);
				frame.getContentPane().add(panelInformativo);
				panelInformativo.setLayout(null);
				panelPrincipal.setVisible(false);
				
				JLabel lblUmNmeroAleatrio = new JLabel("Um número aleatório foi gerado");
				lblUmNmeroAleatrio.setFont(new Font("Dialog", Font.BOLD, 18));
				lblUmNmeroAleatrio.setBounds(165, 112, 344, 22);
				panelInformativo.add(lblUmNmeroAleatrio);
				
				JLabel lblTenteAdivinharQual = new JLabel("Tente Adivinhar qual");
				lblTenteAdivinharQual.setFont(new Font("Dialog", Font.BOLD, 18));
				lblTenteAdivinharQual.setBounds(224, 146, 231, 22);
				panelInformativo.add(lblTenteAdivinharQual);

				JButton btnOk = new JButton("Ok");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						Panel panelVerificador = new Panel();
						panelVerificador.setBounds(0, 0, 690, 396);
						frame.getContentPane().add(panelVerificador);
						panelVerificador.setLayout(null);
						panelInformativo.setVisible(false);

						JLabel lblInsiraUmValor = new JLabel("Insira um valor:");
						lblInsiraUmValor.setFont(new Font("Dialog", Font.BOLD, 18));
						lblInsiraUmValor.setBounds(76, 157, 173, 22);
						panelVerificador.add(lblInsiraUmValor);

						Insert = new JTextField();
						Insert.setBounds(253, 157, 147, 25);
						panelVerificador.add(Insert);
						Insert.setColumns(10);

						JButton btnVerificar = new JButton("Verificar");
						btnVerificar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								try {
									int chosen_value = Integer.parseInt(Insert.getText());

									if (chosen_value == value_random) {
										Panel panelVitoria = new Panel();
										panelVitoria.setBounds(0, 0, 690, 396);
										frame.getContentPane().add(panelVitoria);
										panelVitoria.setLayout(null);
										panelVerificador.setVisible(false);

										JLabel labelVitoria = new JLabel("Parabéns você acertou!!!");
										labelVitoria.setFont(new Font("Dialog", Font.BOLD, 18));
										labelVitoria.setBounds(195, 155, 264, 22);
										panelVitoria.add(labelVitoria);

										JButton btnOk_1 = new JButton("Ok");
										btnOk_1.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												try {
													victories++;
													write(arqu);
													panelPrincipal.setVisible(true);
													panelVitoria.setVisible(false);
												} catch (IOException e) {
													System.out.print("Erro do tipo: " + e.getMessage());
												}
											}
										});
										btnOk_1.setBounds(268, 217, 117, 25);
										panelVitoria.add(btnOk_1);
									} else if (chosen_value > value_random) {

										if (lifes == 1) {
											Panel panelDerrota = new Panel();
											panelDerrota.setBounds(0, 0, 690, 396);
											frame.getContentPane().add(panelDerrota);
											panelDerrota.setLayout(null);
											panelVerificador.setVisible(false);

											JLabel labelDerrota = new JLabel("Que pena você Perdeu.");
											labelDerrota.setFont(new Font("Dialog", Font.BOLD, 18));
											labelDerrota.setBounds(195, 155, 264, 22);
											panelDerrota.add(labelDerrota);

											JButton btnOk_1 = new JButton("Ok");
											btnOk_1.addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent arg0) {
													try {
														defeats++;
														write(arqu);
														//panelPrincipal.setVisible(true);
														panelDerrota.setVisible(false);
														Game.main(null);
													} catch (IOException e) {
														System.out.print("Erro do tipo: " + e.getMessage());
													}
												}
											});
											btnOk_1.setBounds(268, 217, 117, 25);
											panelDerrota.add(btnOk_1);
										}

										Panel panelMaiorValor = new Panel();
										panelMaiorValor.setBounds(0, 0, 690, 396);
										frame.getContentPane().add(panelMaiorValor);
										panelMaiorValor.setLayout(null);
										panelVerificador.setVisible(false);

										JLabel lblOValor = new JLabel("O valor " + chosen_value + " é maior que o numero escolhido");
										lblOValor.setFont(new Font("Dialog", Font.BOLD, 18));
										lblOValor.setBounds(116, 90, 534, 25);
										panelMaiorValor.add(lblOValor);
										lifes--;
										JLabel lblAgoraLheResta = new JLabel("Agora lhe resta apenas " + lifes + " vidas");
										lblAgoraLheResta.setFont(new Font("Dialog", Font.BOLD, 18));
										lblAgoraLheResta.setBounds(155, 127, 436, 25);
										panelMaiorValor.add(lblAgoraLheResta);

										JButton btnOk_2 = new JButton("Ok");
										btnOk_2.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												panelVerificador.setVisible(true);
												panelMaiorValor.setVisible(false);
											}
										});
										btnOk_2.setBounds(272, 246, 117, 25);
										panelMaiorValor.add(btnOk_2);

									} else {

										if (lifes == 1) {
											Panel panelDerrota = new Panel();
											panelDerrota.setBounds(0, 0, 690, 396);
											frame.getContentPane().add(panelDerrota);
											panelDerrota.setLayout(null);
											panelVerificador.setVisible(false);

											JLabel labelDerrota = new JLabel("Que pena você Perdeu.");
											labelDerrota.setFont(new Font("Dialog", Font.BOLD, 18));
											labelDerrota.setBounds(195, 155, 264, 22);
											panelDerrota.add(labelDerrota);

											JButton btnOk_1 = new JButton("Ok");
											btnOk_1.addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent arg0) {
													try {
														defeats++;
														write(arqu);
														//panelPrincipal.setVisible(true);
														panelDerrota.setVisible(false);
														Game.main(null);
													} catch (IOException e) {
														System.out.print("Erro do tipo: " + e.getMessage());
													}
												}
											});
											btnOk_1.setBounds(268, 217, 117, 25);
											panelDerrota.add(btnOk_1);
										}

										Panel panelMenorValor = new Panel();
										panelMenorValor.setBounds(0, 0, 690, 396);
										frame.getContentPane().add(panelMenorValor);
										panelMenorValor.setLayout(null);
										panelVerificador.setVisible(false);

										JLabel lblOValor = new JLabel("O valor " + chosen_value + " é menor que o numero escolhido");
										lblOValor.setFont(new Font("Dialog", Font.BOLD, 18));
										lblOValor.setBounds(116, 90, 534, 25);
										panelMenorValor.add(lblOValor);
										lifes--;
										JLabel lblAgoraLheResta = new JLabel("Agora lhe resta apenas " + lifes + " vidas");
										lblAgoraLheResta.setFont(new Font("Dialog", Font.BOLD, 18));
										lblAgoraLheResta.setBounds(155, 127, 436, 25);
										panelMenorValor.add(lblAgoraLheResta);

										JButton btnOk_2 = new JButton("Ok");
										btnOk_2.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												panelVerificador.setVisible(true);
												panelMenorValor.setVisible(false);
											}
										});
										btnOk_2.setBounds(272, 246, 117, 25);
										panelMenorValor.add(btnOk_2);
									}
								}
								catch(NumberFormatException nfe){
									//É pra acontecer nada mesmo kkk.
								}
							}
						});
						btnVerificar.setBounds(425, 157, 127, 25);
						panelVerificador.add(btnVerificar);

					}
				});
				btnOk.setBounds(269, 221, 117, 25);
				panelInformativo.add(btnOk);
			}
		});
		btnPlay.setBounds(286, 203, 117, 25);
		panelPrincipal.add(btnPlay);
		
		JButton btnRecords = new JButton("Records");
		btnRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String contents = read(arqu);
					Panel panelRecords = new Panel();
					panelRecords.setBounds(0, 0, 690, 396);
					frame.getContentPane().add(panelRecords);
					panelRecords.setLayout(null);
					panelPrincipal.setVisible(false);
					
					JLabel lblVitorias = new JLabel("Vitorias: " + contents.split(";")[0]);
					lblVitorias.setFont(new Font("Dialog", Font.BOLD, 18));
					lblVitorias.setBounds(104, 113, 169, 25);
					panelRecords.add(lblVitorias);
					
					JLabel lblDerrotas = new JLabel("Derrotas: " + contents.split(";")[1]);
					lblDerrotas.setFont(new Font("Dialog", Font.BOLD, 18));
					lblDerrotas.setBounds(104, 195, 169, 25);
					panelRecords.add(lblDerrotas);
					
					JButton btnVoltar = new JButton("Voltar");
					btnVoltar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							panelPrincipal.setVisible(true);
							panelRecords.setVisible(false);
						}
					});
					btnVoltar.setBounds(270, 276, 124, 31);
					panelRecords.add(btnVoltar);
			    }
				catch (ArrayIndexOutOfBoundsException | IOException aioobe){		
					System.out.println("Leitor não conseguiu ler do arquivo!!! \n\n Erro do tipo: " + aioobe.getMessage());				
					System.exit(0);
				}
			}
		});
		btnRecords.setBounds(286, 246, 117, 25);
		panelPrincipal.add(btnRecords);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Panel panelReset = new Panel();
				panelReset.setBounds(0, 0, 690, 396);
				frame.getContentPane().add(panelReset);
				panelReset.setLayout(null);
				
				JLabel lblTemCertezaQue = new JLabel("Tem certeza que deseja resetar todo o placar?");
				lblTemCertezaQue.setFont(new Font("Dialog", Font.BOLD, 18));
				lblTemCertezaQue.setBounds(95, 78, 480, 22);
				panelReset.add(lblTemCertezaQue);
				
				JLabel lblEstaAoNo = new JLabel("Esta ação não pode ser desfeita!");
				lblEstaAoNo.setFont(new Font("Dialog", Font.BOLD, 18));
				lblEstaAoNo.setBounds(165, 112, 337, 22);
				panelReset.add(lblEstaAoNo);
				panelPrincipal.setVisible(false);
				
				JButton btnSim = new JButton("Sim");
				btnSim.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							arqu.delete();
							arqu.createNewFile();
							FileWriter fwrite = new FileWriter(arqu, true);
							PrintWriter pwrite = new PrintWriter(fwrite);
							pwrite.print(0 + ";" + 0 + ";" + "Developed by RITKILL" + "\n");
							fwrite.close();
							pwrite.close();
						} catch (Exception e) {
							System.out.println("\n\n\t\t\tErro ao Resetar o arquivo!!!");
							System.out.println("\n\t\t\tErro do tipo: " + e.getMessage());
							System.exit(0);
						}
						panelPrincipal.setVisible(true);
						panelReset.setVisible(false);
					}
				});
				btnSim.setBounds(125, 209, 128, 33);
				panelReset.add(btnSim);
				
				JButton btnNo = new JButton("Não");
				btnNo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						panelPrincipal.setVisible(true);
						panelReset.setVisible(false);
					}
				});
				btnNo.setBounds(413, 209, 128, 33);
				panelReset.add(btnNo);	
			}
		});
		btnReset.setBounds(286, 291, 117, 25);
		panelPrincipal.add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(286, 335, 117, 25);
		panelPrincipal.add(btnExit);
	}
	
	public void write(File file) throws IOException {
		try {
			List<String> list = Files.readAllLines(file.toPath());
			file.delete();
			file.createNewFile();
			try {
				FileWriter fwrite = new FileWriter(file, true);
				PrintWriter pwrite = new PrintWriter(fwrite);
				int concat1 = (victories + Integer.parseInt(list.get(0).split(";")[0]));
				int concat2 = (defeats + Integer.parseInt(list.get(0).split(";")[1]));
				pwrite.print(concat1 + ";" + concat2 + ";" + "Developed by RITKILL" + "\n");
				victories = 0;
				defeats = 0;
				fwrite.close();
				pwrite.close();
			} catch (Exception e) {
				System.out.println("\n\n\t\t\tErro ao Gravar arquivo!!!");
				System.out.println("\n\t\t\tErro do tipo: " + e.getMessage());
				System.in.read();
				System.exit(0);
			}
		}
		catch (FileNotFoundException fnfe){
			System.out.println("\n\n\t\t\tArquivo não existe!!!");
			System.exit(0);
		}
		catch (NumberFormatException nfe){
			System.out.println("\n\n\t\t\tProblemas na conversão do arquivo txt para o formato numerico!!");
			System.out.println("\n\t\t\tErro do tipo: " + nfe.getMessage());
			System.exit(0);
		}
	}
	
	
	public String read(File arqu) throws IOException {
		String contents = " ";
		try {
			FileReader fread = new FileReader(arqu);
			BufferedReader bread = new BufferedReader(fread);
			String line = " ";
			try {
				line = bread.readLine();
				while (line != null){
					contents += line;
					line = bread.readLine();
				}
			}
			catch (Exception e) {
				bread.close();
				System.out.println("\n\n\n\n\n\n\t\tErro ao ler o arquivo!!! \n\n Erro do tipo: " + e.getMessage());
				System.in.read();
				System.exit(0);
			}
			fread.close();
			bread.close();
		}
		catch (FileNotFoundException fnfe){
			System.out.println("\n\n\n\n\n\n\n\t\tArquivo não existe!!! \n\n Erro do tipo: " + fnfe.getMessage());
			System.in.read();
			System.exit(0);
		}
		return contents;
	}
}
