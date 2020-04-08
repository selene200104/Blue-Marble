import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BlueMarble {

	private JFrame frame;
	Random ramdom = new Random();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlueMarble window = new BlueMarble();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JPanel blueMarbleScene = new JPanel();

	// 플레이어
	JLabel player1Image = new JLabel();
	JLabel player2Image = new JLabel();
	JLabel player1infomationBoard = new JLabel();
	JLabel player2infomationBoard = new JLabel();
	JLabel player1moneyText = new JLabel();
	JLabel player2moneyText = new JLabel();
	JLabel player1leftdayOfisland = new JLabel();
	JLabel player2leftdayOfisland = new JLabel();
	JLabel whosTurnText = new JLabel();

	Player player1 = new Player();
	Player player2 = new Player();

	int whosTurn = 1;
	int Player1forcedRest = 0; // 플레이어가 강제로 쉬어야 하는 날짜
	int Player2forcedRest = 0;

	// 부루마블 판
	JLabel[] topLine = new JLabel[8];
	JLabel[] bottomLine = new JLabel[8];
	JLabel[] leftLine = new JLabel[7];
	JLabel[] rightLine = new JLabel[7];

	int topLineHorizontalLength = 82;
	int topLineVerticalLength = 3;
	int bottomLineHorizontalLength = 628;
	int bottomLineVerticalLength = 482;
	int leftLineHorizontalLength = 2;
	int leftLineVerticalLength = 482;
	int rightLineHorizontalLength = 708;
	int rightLineVerticalLength = 3;
	int HorizontalLineInterval = 78;
	int VerticalLineInterval = 80;
	int lineWidth = 77;
	int lineHeight = 79;

	// 주사위
	JButton diceThrowButton = new JButton();
	JLabel diceNumberText = new JLabel();

	int diceNum = 0;

	// 그 외
	JLabel playSituation = new JLabel();// 플레이 상황을 보여주는 text
	int socialWelfareCost = 10000; // 사회복지기금 비용
	int collectedSocialWelfare = 0; // 사회복지기금에 모인 돈
	int luckeyCardNum = 0; // 행운카드 번호

	/* 코드 추가된 곳 */
	//섬 패널
	JPanel islandPanel = new JPanel() {
		public void paintComponent(Graphics g) {
			Dimension d = getSize();
			ImageIcon image = new ImageIcon("./images/island.png");
			g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		}
	};
	
	JLabel[] islandName = new JLabel[3];
	JButton[] islandBulidingButton = new JButton[3];
	
	int nameHorizontalLength = 150;
	int nameVerticalLength = 20;
	int nameLineWidth = 100;
	int nameLineHeight = 20;

	int islandButtonHorizontalLength = 7;
	int islandButtonVerticalLength = 60;
	int islandButtonWidth = 100;
	int islandButtonHeight = 130;
	
	int constructionCost = 0;
	int landCost = 130000;
	int villaCost = 50000;
	int buildingCost = 150000;
	int hotelCost = 250000;
	int landCount = 0;
	int villaCount = 0;
	int buildingCount = 0;
	int hotelCount = 0;

	public BlueMarble() {

		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("blueMarble");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		blueMarbleScene.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(blueMarbleScene);
		blueMarbleScene.setLayout(null);

		/* 코드 추가된 곳 */
		islandPanel.setLayout(null);
		islandPanel.setBounds(200,100,340,300);
		blueMarbleScene.add(islandPanel);
		islandPanel.setVisible(false);
		
		// 플레이어
		player1Image.setIcon(new ImageIcon("./images/Player1.png"));
		player1Image.setBounds(715, 495, 60, 60);
		blueMarbleScene.add(player1Image);

		player2Image.setIcon(new ImageIcon("./images/Player2.png"));
		player2Image.setBounds(715, 495, 60, 60);
		blueMarbleScene.add(player2Image);

		player1moneyText.setText("money : 30000원");
		player1moneyText.setFont(new Font("굴림", Font.BOLD, 13));
		player1moneyText.setBounds(130, 190, 110, 60);
		blueMarbleScene.add(player1moneyText);

		player2moneyText.setText("money : 30000원");
		player2moneyText.setFont(new Font("굴림", Font.BOLD, 13));
		player2moneyText.setBounds(130, 390, 110, 60);
		blueMarbleScene.add(player2moneyText);

		player1leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : 3일");
		player1leftdayOfisland.setFont(new Font("굴림", Font.BOLD, 13));
		player1leftdayOfisland.setBounds(100, 210, 220, 80);
		blueMarbleScene.add(player1leftdayOfisland);
		player1leftdayOfisland.setVisible(false);

		player2leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : 3일");
		player2leftdayOfisland.setFont(new Font("굴림", Font.BOLD, 13));
		player2leftdayOfisland.setBounds(100, 410, 220, 80);
		blueMarbleScene.add(player2leftdayOfisland);
		player2leftdayOfisland.setVisible(false);

		player1infomationBoard.setIcon(new ImageIcon("./images/Player1Board.png"));
		player1infomationBoard.setBounds(83, 87, 250, 190);
		blueMarbleScene.add(player1infomationBoard);

		player2infomationBoard.setIcon(new ImageIcon("./images/Player2Board.png"));
		player2infomationBoard.setBounds(83, 285, 250, 190);
		blueMarbleScene.add(player2infomationBoard);

		whosTurnText.setText("Player 1 순서");
		whosTurnText.setFont(new Font("굴림", Font.BOLD, 13));
		whosTurnText.setBounds(350, 90, 220, 80);
		blueMarbleScene.add(whosTurnText);

		playSituation.setText("");
		playSituation.setFont(new Font("굴림", Font.BOLD, 13));
		playSituation.setBounds(350, 110, 350, 80);
		blueMarbleScene.add(playSituation);
		
		/* 코드 추가된 곳 */	
		
		islandName[0] = new JLabel("서귀포");
		islandName[1] = new JLabel("제주");
		islandName[2] = new JLabel("독도");
		
		islandBulidingButton[0] = new JButton((new ImageIcon("./images/villa.png")));
		islandBulidingButton[1] = new JButton((new ImageIcon("./images/building.png")));
		islandBulidingButton[2] = new JButton((new ImageIcon("./images/hotel.png")));

		//int location = 0;
		
		// 부루마블 판
		// 위쪽 줄
		for (int i = 0; i < topLine.length; i++) {

			blueMarbleScene.add(topLine[i] = new JLabel());

			topLine[i].setBounds(topLineHorizontalLength, topLineVerticalLength, lineWidth, lineHeight);

			topLineHorizontalLength = topLineHorizontalLength + HorizontalLineInterval;

			topLine[i].setIcon(new ImageIcon("./images/Line.png"));
		}

		// 아래쪽 줄
		for (int i = 0; i < topLine.length; i++) {

			blueMarbleScene.add(bottomLine[i] = new JLabel());

			bottomLine[i].setBounds(bottomLineHorizontalLength, bottomLineVerticalLength, lineWidth, lineHeight);

			bottomLineHorizontalLength = bottomLineHorizontalLength - HorizontalLineInterval;

			bottomLine[i].setIcon(new ImageIcon("./images/Line.png"));
		}

		// 왼쪽 줄
		for (int i = 0; i < leftLine.length; i++) {

			blueMarbleScene.add(leftLine[i] = new JLabel());

			leftLine[i].setBounds(leftLineHorizontalLength, leftLineVerticalLength, lineWidth, lineHeight);

			leftLineVerticalLength = leftLineVerticalLength - VerticalLineInterval;

			leftLine[i].setIcon(new ImageIcon("./images/Line.png"));
		}

		// 오른쪽 줄
		for (int i = 0; i < rightLine.length; i++) {

			blueMarbleScene.add(rightLine[i] = new JLabel());

			rightLine[i].setBounds(rightLineHorizontalLength, rightLineVerticalLength, lineWidth, lineHeight);

			rightLineVerticalLength = rightLineVerticalLength + VerticalLineInterval;

			rightLine[i].setIcon(new ImageIcon("./images/Line.png"));
		}

		// 주사위 던지기
		diceThrowButton.setText("주사위 돌리기");
		diceThrowButton.setBounds(500, 300, 110, 60);
		diceThrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 랜덤으로 나온 수가 주사위의 수가 된다.
				// diceNum = ramdom.nextInt(6) + 1;
				diceNum = 1;
				diceNumberText.setText("주사위 수 : " + diceNum);

				playSituation.setText("");

				// player1의 차례일 때
				if (whosTurn == 1) {

					if (Player1forcedRest == 0) {
						player1leftdayOfisland.setVisible(false);

						player1.location = player1.location + diceNum;
						playerMove(player1, player1Image);
						player(player1);
					} else {
						Player1forcedRest--;
						player1leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : " + (Player1forcedRest + 1) + " 일");
					}

					// 플레이어을 움직인 후 차례를 바꾼다
					whosTurnText.setText("Player 2 순서");
					whosTurn = 2;

					// player2의 차례일 때
				} else if (whosTurn == 2) {

					if (Player2forcedRest == 0) {
						player2leftdayOfisland.setVisible(false);

						player2.location = player2.location + diceNum;
						playerMove(player2, player2Image);
						player(player2);
					} else {
						Player2forcedRest--;
						player2leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : " + (Player2forcedRest + 1) + " 일");
					}

					// 플레이어을 움직인 후 차례를 바꾼다
					whosTurnText.setText("Player 1 순서");
					whosTurn = 1;
				}
			}
		});
		blueMarbleScene.add(diceThrowButton);

		diceNumberText.setText("주사위를 던지시오");
		diceNumberText.setFont(new Font("굴림", Font.BOLD, 13));
		diceNumberText.setBounds(510, 360, 110, 60);
		blueMarbleScene.add(diceNumberText);
		
	}

	public void playerMove(Player player, JLabel playerImage) {

		if (player.location >= 30) {
			player.location = player.location - 30;
		}

		if (player.location == 0) {
			playerImage.setLocation(rightLine[6].getX() + 10, rightLine[6].getY() + 10);

		} else if (player.location >= 1 && player.location <= 8) {
			for (int i = 0; i <= player.location - 1; i++) {
				playerImage.setLocation(bottomLine[i].getX() + 10, bottomLine[i].getY() + 10);
				
				/* 여기까지 섬패널 관련된거 시작*/
				//플레이어 위치가 섬과 관련된 땅(서귀포/제주/독도)에 있을 경우
				for(int j = 0; j < islandName.length; j++) {
					int islandButtonHorizontalLength = 7;
					
					for(int k = 0; k <islandBulidingButton.length; k++) {
						
						if(player.location == j+1) {
							
							if(player == player1) {
								diceThrowButton.setVisible(false);
								islandPanel.setVisible(true);
								
								JLabel constructionCostText = new JLabel();
								constructionCostText.setText("건설비용 : " + constructionCost);
								constructionCostText.setFont(new Font("굴림", Font.BOLD, 15));
								constructionCostText.setBounds(10, 180, 450, 100);
								islandPanel.add(constructionCostText);
								constructionCostText.setVisible(true);
								
								//islandName[0]의 위치에 있지 않으면 j-1값을 한 이름레이블은 숨기게 함 
								if(j != 0) {
									islandName[j-1].setVisible(false);
								}
								islandPanel.add(islandName[j]);
								islandName[j].setBounds(nameHorizontalLength, nameVerticalLength, nameLineWidth, nameLineHeight);
								islandName[j].setFont((new Font("굴림체", Font.BOLD, 15)));
							
								//섬 건물 가격
								islandPanel.add(islandBulidingButton[k]);
								islandBulidingButton[k].setBounds(islandButtonHorizontalLength, islandButtonVerticalLength, islandButtonWidth, islandButtonHeight);
								islandButtonHorizontalLength = islandButtonHorizontalLength + islandButtonWidth + 10;
								
								islandBulidingButton[0].addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										villaCount++;
										if (villaCount % 2 == 0) {
											islandBulidingButton[0].setIcon(new ImageIcon("./images/villa.png"));
											constructionCost -= villaCost;
											constructionCostText.setText("건설비용 : " + constructionCost);
										} else {
											islandBulidingButton[0].setIcon(new ImageIcon("./images/villaCheck.png"));
											constructionCost += villaCost;
											constructionCostText.setText("건설비용 : " + constructionCost);
										}
									}
								});
								
								islandBulidingButton[1].addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										buildingCount++;
										if (buildingCount % 2 == 0) {
											islandBulidingButton[1].setIcon(new ImageIcon("./images/building.png"));
											constructionCost -= buildingCost;
											constructionCostText.setText("건설비용 : " + constructionCost);
										} else {
											islandBulidingButton[1].setIcon(new ImageIcon("./images/buildingCheck.png"));
											constructionCost += buildingCost;
											constructionCostText.setText("건설비용 : " + constructionCost);
										}
									}
								});
								
								islandBulidingButton[2].addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										hotelCount++;
										if (hotelCount % 2 == 0) {
											islandBulidingButton[2].setIcon(new ImageIcon("./images/hotel.png"));
											constructionCost -= hotelCost;
											constructionCostText.setText("건설비용 : " + constructionCost);
										} else {
											islandBulidingButton[2].setIcon(new ImageIcon("./images/hotelCheck.png"));
											constructionCost += hotelCost;
											constructionCostText.setText("건설비용 : " + constructionCost);
										}
									}
								});
							}
						}
					}
				}
				
				JButton BuyButton = new JButton();
				BuyButton.setText("구매하기");
				BuyButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						islandPanel.setVisible(false);
						diceThrowButton.setVisible(true);
						/*if(player.money > constructionCost) {
							if(landCount % 2 != 0) {
								player.money -= constructionCost;
								System.out.println("구입 후 남은 돈 : "+player.money);
							}else {
								System.out.println("구매할 수 없습니다.");
							}
						}else {
							System.out.println("돈이 부족합니다.");
						}*/
						
					}			
				});
				BuyButton.setBounds(120,250,100,20);
				islandPanel.add(BuyButton);
				BuyButton.setVisible(true);
				
				JButton islandPanelClose = new JButton((new ImageIcon("./images/closeButton.png")));
				islandPanelClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						islandPanel.setVisible(false);
						diceThrowButton.setVisible(true);
					}
				});
				islandPanelClose.setBounds(280, 0, 50, 50);
				islandPanel.add(islandPanelClose);
				islandPanelClose.setVisible(true);
				islandPanelClose.setBorderPainted(false);
				islandPanelClose.setFocusPainted(false);
				islandPanelClose.setContentAreaFilled(false);
				/* 여기까지 섬패널 관련된거 끝*/
			}

		} else if (player.location >= 9 && player.location <= 15) {
			for (int i = 0; i <= player.location - 9; i++) {
				playerImage.setLocation(leftLine[i].getX() + 10, leftLine[i].getY() + 10);
			}

		} else if (player.location >= 16 && player.location <= 23) {
			for (int i = 0; i <= player.location - 16; i++) {
				playerImage.setLocation(topLine[i].getX() + 10, topLine[i].getY() + 10);
			}

		} else if (player.location >= 24 && player.location <= 29) {
			for (int i = 0; i <= player.location - 24; i++) {
				playerImage.setLocation(rightLine[i].getX() + 10, rightLine[i].getY() + 10);
			}
		}
	}

	public void player(Player player) {

		if (player.location == 4 || player.location == 19) {
			if (player == player1) {

			} else if (player == player2) {

			}
			System.out.println("행운카드!!");

			// 플레이어가 공항에 도착했을 때
		} else if (player.location == 9) {
			if (player == player1) {
				playSituation.setText("Player1이 공항에 도착했습니다");
				airport(player, player1Image);

			} else if (player == player2) {
				playSituation.setText("Player2이 공항에 도착했습니다");
				airport(player, player2Image);

			}

			// 플레이어가 무인도에 도착했을 때
		} else if (player.location == 15) {
			if (player == player1) {
				playSituation.setText("Player1이 무인도에 갇혔습니다");
				player1leftdayOfisland.setVisible(true);
				Player1forcedRest = 2;

			} else if (player == player2) {
				playSituation.setText("Player2이 무인도에 갇혔습니다");
				player2leftdayOfisland.setVisible(true);
				Player2forcedRest = 2;
			}

			// 플레이어가 사회복지기금(돈 얻음)에 도착했을 때
		} else if (player.location == 24) {
			player.money = player.money + collectedSocialWelfare;

			if (player == player1) {
				playSituation.setText("Player1이  사회복지기금에서 돈을 얻었습니다 +" + collectedSocialWelfare + "원");
				player1moneyText.setText("money : " + player.money);
			} else if (player == player2) {
				playSituation.setText("Player2이  사회복지기금에서 돈을 얻었습니다 +" + collectedSocialWelfare + "원");
				player2moneyText.setText("money : " + player.money);
			}
			collectedSocialWelfare = 0;

			// 플레이어가 사회복지기금(돈 지불)에 도착했을 때
		} else if (player.location == 27) {

			player.money = player.money - socialWelfareCost;
			collectedSocialWelfare = collectedSocialWelfare + socialWelfareCost;

			if (player == player1) {
				playSituation.setText("Player1이  사회복지기금에서 돈을 기부했습니다 -" + socialWelfareCost + "원");
				player1moneyText.setText("money : " + player.money);
			} else if (player == player2) {
				playSituation.setText("Player2이  사회복지기금에서 돈을 기부했습니다 -" + socialWelfareCost + "원");
				player2moneyText.setText("money : " + player.money);
			}
		}
	}

	public void airport(Player player, JLabel playerImage) {

		for (int i = 0; i < leftLine.length; i++) {
			leftLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					for (int i = 0; i < leftLine.length; i++) {
						if (e.getSource() == leftLine[i]) {
							playerImage.setLocation(leftLine[i].getX() + 10, leftLine[i].getY() + 10);

						}
					}
				}
			});
		}

		for (int i = 0; i < rightLine.length; i++) {
			rightLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					for (int i = 0; i < rightLine.length; i++) {
						if (e.getSource() == rightLine[i]) {
							playerImage.setLocation(rightLine[i].getX() + 10, rightLine[i].getY() + 10);
							player.location = 16 + i;
							System.out.println("player.location" + player.location);
						}
					}
				}
			});
		}

		for (int i = 0; i < topLine.length; i++) {
			topLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					for (int i = 0; i < topLine.length; i++) {
						if (e.getSource() == topLine[i]) {
							playerImage.setLocation(topLine[i].getX() + 10, topLine[i].getY() + 10);
							player.location = 24 + i;
							System.out.println("player.location" + player.location);
						}
					}
				}
			});
		}

		for (int i = 0; i < bottomLine.length; i++) {
			bottomLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					for (int i = 0; i < bottomLine.length; i++) {
						if (e.getSource() == bottomLine[i]) {
							playerImage.setLocation(bottomLine[i].getX() + 10, bottomLine[i].getY() + 10);

						}
					}
				}
			});
		}
	}

	public void luckyCard(Player player) {
		luckeyCardNum = ramdom.nextInt(6);

		if (luckeyCardNum == 0) {

		} else if (luckeyCardNum == 1) {

		} else if (luckeyCardNum == 2) {

		} else if (luckeyCardNum == 3) {

		} else if (luckeyCardNum == 4) {

		} else if (luckeyCardNum == 5) {

		} else if (luckeyCardNum == 6) {

		} else if (luckeyCardNum == 7) {

		}
	
		/*
		 * (1) 여행보내주는 카드 (2) 사회복지기구로 가는 카드 (3) 출발지로 이동하는 카드 (4) 무인도로 가는 카드 (5) 복권당첨 카드
		 * (+건물값에 따라 금액 정하기) (6) 돈 도난당한 카드 (-건물값에 따라 금액 정하기) (7) 무인도 탈출 카드 (8) 통행료 무료로
		 * 하는 카드
		 */
	
	}
}