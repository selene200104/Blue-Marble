import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


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
	JPanel rideAirplaneScene = new JPanel();
	
	//플레이어
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
	int Player1forcedRest = 0; //플레이어가 강제로 쉬어야 하는 날짜
	int Player2forcedRest = 0;
	
	
	//부루마블 판 
	static JLabel[] topLine = new JLabel[8];
	static JLabel[] bottomLine = new JLabel[8];
	static JLabel[] leftLine = new JLabel[7];
	static JLabel[] rightLine = new JLabel[7];

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

	//주사위 
	JButton diceThrowButton = new JButton();
	JLabel diceNumberText = new JLabel();
	
	int diceNum = 0;
	
	//공항 
	JLabel chooseRideAirplane = new JLabel();
	JLabel cautionPointText = new JLabel();
	JButton okButton = new JButton();
	JButton noButton = new JButton();
	
	int airplaneFee = 20000;
	String player1Flying = ""; 
	String player2Flying = "";
	String whoRideAirplane = "";
	
	//그 외
	JLabel playSituation = new JLabel();//플레이 상황을 보여주는 text
	int socialWelfareCost = 10000; //사회복지기금 비용
	int collectedSocialWelfare = 0; //사회복지기금에 모인 돈
	int luckeyCardNum = 0; //행운카드 번호
	
	PlayerMove player1Move = new PlayerMove(player1, player1Image, playSituation, player1moneyText);
	PlayerMove player2Move = new PlayerMove(player2, player2Image, playSituation, player2moneyText);
	
	public BlueMarble() {

		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("blueMarble");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		rideAirplaneScene.setBounds(180, 100, 400, 300);
		rideAirplaneScene.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(rideAirplaneScene);
		rideAirplaneScene.setLayout(null);
		rideAirplaneScene.setVisible(false);
		
		blueMarbleScene.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(blueMarbleScene);
		blueMarbleScene.setLayout(null);
		
		//플레이어
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
		playSituation.setFont(new Font("굴림", Font.BOLD, 16));
		playSituation.setBounds(350, 110, 500, 80);
		blueMarbleScene.add(playSituation);
		
		//부루마블 판
		//위쪽 줄
		for (int i = 0; i < topLine.length; i++) {

			blueMarbleScene.add(topLine[i] = new JLabel());

			topLine[i].setBounds(topLineHorizontalLength, topLineVerticalLength, lineWidth, lineHeight);
			
			topLineHorizontalLength = topLineHorizontalLength + HorizontalLineInterval;
			
			topLine[i].setIcon(new ImageIcon("./images/Line.png"));
		}
		
		//아래쪽 줄
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
		

		
		//주사위 던지기
		diceThrowButton.setText("주사위 돌리기");
		diceThrowButton.setBounds(500, 300, 110, 60);
		diceThrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 랜덤으로 나온 수가 주사위의 수가 된다.
				diceNum = ramdom.nextInt(6) + 1;
				diceNumberText.setText("주사위 수 : " + diceNum);

				playSituation.setText("");
				
				//player1의 차례일 때
				if(whosTurn == 1) {
					
					//비행기를 타게 되면 주사위 버튼을 눌러도 말이 움직이면 안되기 때문
					if (player1Flying == "비행기 타기") {
						player1Flying = "";

					} else if (Player1forcedRest == 0) {
						player1leftdayOfisland.setVisible(false);

						player1.previousLocation = player1.location;
						player1.location = player1.location + diceNum;
						synchronized (player1Move) {
							player1Move.notify();
						}
						player(player1, player1Image);

					} else if (Player1forcedRest > 0) {
						Player1forcedRest--;
						player1leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : " + (Player1forcedRest + 1) + " 일");

					}
					
					//player1이 움직인 후 player2가 주사위 버튼을 누르지 않고 바로 비행기를 탈 수 있도록 하기 위함
					if (player2Flying == "비행기 타기") {
						airport();
						playSituation.setText("원하는 도시를 클릭 후 주사위 버튼을 눌러주세요");
					}
					
					//플레이어을 움직인 후 차례를 바꾼다
					whosTurnText.setText("Player 2 순서");
					whosTurn = 2;
					
				//player2의 차례일 때
				}else if(whosTurn == 2) {
					
					if (player2Flying == "비행기 타기") {
						player2Flying = "";
						
					} else if (Player2forcedRest == 0) {
						player2leftdayOfisland.setVisible(false);

						player2.previousLocation = player2.location;
						player2.location = player2.location + diceNum;
						synchronized (player2Move) {
							player2Move.notify();
						}

						player(player2, player2Image);
						
					} else {
						Player2forcedRest--;
						player2leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : " + (Player2forcedRest+1) +" 일");
					}
					
					if (player1Flying == "비행기 타기") {
						airport();
						playSituation.setText("원하는 도시를 클릭 후 주사위 버튼을 눌러주세요");
					}
					
					//플레이어을 움직인 후 차례를 바꾼다
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
		
		//공항(비행기)
		chooseRideAirplane.setText("비행기를 타시겠습니까?");
		chooseRideAirplane.setFont(new Font("굴림", Font.BOLD, 25));
		chooseRideAirplane.setForeground(Color.white);
		chooseRideAirplane.setBounds(55, 0, 400, 100);
		rideAirplaneScene.add(chooseRideAirplane);
		
		cautionPointText.setText("주의! 다음 턴에 이용할 수 있습니다");
		cautionPointText.setFont(new Font("굴림", Font.BOLD, 15));
		cautionPointText.setForeground(Color.white);
		cautionPointText.setBounds(65, 50, 300, 100);
		rideAirplaneScene.add(cautionPointText);

		okButton.setText("네");
		okButton.setBounds(65, 200, 100, 50);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (whoRideAirplane.equals("player1")) {
					if (player1.money > airplaneFee) {
						player1.money = player1.money - airplaneFee;
						rideAirplaneScene.setVisible(false);
						diceThrowButton.setVisible(true);
						player1moneyText.setText("money : " + player1.money);
						player1Flying = "비행기 타기";
					} else {
						cautionPointText.setText("돈이 모자랍니다!!");
					}
				} else if (whoRideAirplane.equals("player2")) {
					if (player2.money > airplaneFee) {
						player2.money = player2.money - airplaneFee;
						rideAirplaneScene.setVisible(false);
						diceThrowButton.setVisible(true);
						player2moneyText.setText("money : " + player2.money);
						player2Flying = "비행기 타기";
						
					} else {
						cautionPointText.setText("돈이 모자랍니다!!");
					}
				}

			}
		});
		rideAirplaneScene.add(okButton);

		noButton.setText("아니요");
		noButton.setBounds(205, 200, 100, 50);
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diceThrowButton.setVisible(true);
				rideAirplaneScene.setVisible(false);
			}
		});
		rideAirplaneScene.add(noButton);
		
		player1Move.start();
		player2Move.start();
	}
	
	
	public void player(Player player, JLabel playerImage) {

		if (player.location == 4 || player.location == 19) {
			if (player == player1) {
				
			}else if(player == player2) {
				
			}

			//플레이어가 공항에 도착했을 때
		}else if (player.location == 9) {
			if (player == player1) {
				whoRideAirplane = "player1";
			} else if (player == player2) {
				whoRideAirplane = "player2";
			}
			diceThrowButton.setVisible(false);
			rideAirplaneScene.setVisible(true);
			chooseRideAirplane.setText("비행기를 타시겠습니까?");
			playSituation.setText("공항에 도착했습니다");

		//플레이어가 무인도에 도착했을 때
		} else if (player.location == 15) {
			if(player == player1) {
				player1leftdayOfisland.setVisible(true);
				Player1forcedRest = 3;
				
			}else if(player == player2) {
				player2leftdayOfisland.setVisible(true);
				Player2forcedRest = 3;
			}
			playSituation.setText("무인도에 갇혔습니다");
		
		//플레이어가 사회복지기금(돈 얻음)에 도착했을 때
		} else if (player.location == 24) {
			player.money = player.money + collectedSocialWelfare;

			if (player == player1) {
				player1moneyText.setText("money : " + player.money);
			} else if (player == player2) {
				player2moneyText.setText("money : " + player.money);
			}
			playSituation.setText("사회복지기금에서 돈을 얻었습니다 +" + collectedSocialWelfare + "원");
			collectedSocialWelfare = 0;
			
		//플레이어가 사회복지기금(돈 지불)에 도착했을 때
		} else if (player.location == 27) {
			
			player.money = player.money - socialWelfareCost;
			collectedSocialWelfare = collectedSocialWelfare + socialWelfareCost;
			
			if (player == player1) {
				player1moneyText.setText("money : " + player.money);
			} else if (player == player2) {
				player2moneyText.setText("money : " + player.money);
			}
			playSituation.setText("사회복지기금에서 돈을 기부했습니다 -" + socialWelfareCost + "원");
		}
	}
	
	public void airport() {
	
		playSituation.setText("가고 싶은 지역을 선택해주세요");
		
		for (int i = 0; i < bottomLine.length; i++) {
			bottomLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (player1Flying.equals("비행기 타기")) {
						for (int i = 0; i < bottomLine.length; i++) {
							if (e.getSource() == bottomLine[i]) {
								player1Image.setLocation(bottomLine[i].getX() + 10, bottomLine[i].getY() + 10);
								player1.location = i + 1;
								//player1Flying = "";
							}
						}
					}else if(player2Flying.equals("비행기 타기")) {
						for (int i = 0; i < bottomLine.length; i++) {
							if (e.getSource() == bottomLine[i]) {
								player2Image.setLocation(bottomLine[i].getX() + 10, bottomLine[i].getY() + 10);
								player2.location = i + 1;
								//player2Flying = "";
							}
						}
					}
				}
			});
		}

		for (int i = 0; i < leftLine.length; i++) {
			leftLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (player1Flying.equals("비행기 타기")) {
						for (int i = 0; i < leftLine.length; i++) {
							if (e.getSource() == leftLine[i]) {
								player1Image.setLocation(leftLine[i].getX() + 10, leftLine[i].getY() + 10);
								player1.location = i + 9;
								//player1Flying = "";
							}
						}
					}else if (player2Flying.equals("비행기 타기")) {
						for (int i = 0; i < leftLine.length; i++) {
							if (e.getSource() == leftLine[i]) {
								player2Image.setLocation(leftLine[i].getX() + 10, leftLine[i].getY() + 10);
								player2.location = i + 9;
								//player2Flying = "";
							}
						}
					}
				}
			});
		}

		for (int i = 0; i < topLine.length; i++) {
			topLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (player1Flying.equals("비행기 타기")) {
						for (int i = 0; i < topLine.length; i++) {
							if (e.getSource() == topLine[i]) {
								player1Image.setLocation(topLine[i].getX() + 10, topLine[i].getY() + 10);
								player1.location = i + 16;
								//player1Flying = "";
							}
						}
					}else if (player2Flying.equals("비행기 타기")) {
						for (int i = 0; i < topLine.length; i++) {
							if (e.getSource() == topLine[i]) {
								player2Image.setLocation(topLine[i].getX() + 10, topLine[i].getY() + 10);
								player2.location = i + 16;
								//player2Flying = "";
							}
						}
					}
				}
			});
		}
		
		for (int i = 0; i < rightLine.length; i++) {
			rightLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (player1Flying.equals("비행기 타기")) {
						for (int i = 0; i < rightLine.length; i++) {
							if (e.getSource() == rightLine[i]) {
								player1Image.setLocation(rightLine[i].getX() + 10, rightLine[i].getY() + 10);
								player1.location = i + 24;
								//player1Flying = "";
							}
						}
					}else if (player2Flying.equals("비행기 타기")) {
						for (int i = 0; i < rightLine.length; i++) {
							if (e.getSource() == rightLine[i]) {
								player2Image.setLocation(rightLine[i].getX() + 10, rightLine[i].getY() + 10);
								player2.location = i + 24;
								//player2Flying = "";
							}
						}
					}
				}
			});
		}
	}
	
	public void luckyCard(Player player) {
		luckeyCardNum = ramdom.nextInt(6);
		
		if(luckeyCardNum == 0) {
			
			
		}else if(luckeyCardNum == 1) {
			
			
		}else if(luckeyCardNum == 2) {
			
			
		}else if(luckeyCardNum == 3) {
			
			
		}else if(luckeyCardNum == 4) {
			
			
		}else if(luckeyCardNum == 5) {
			
			
		}else if(luckeyCardNum == 6) {
			
			
		}else if(luckeyCardNum == 7) {
			
			
		}
		/*
		(1) 여행보내주는 카드
		(2) 사회복지기구로 가는 카드
		(3) 출발지로 이동하는 카드
		(4) 무인도로 가는 카드
		(5) 복권당첨 카드 (+건물값에 따라 금액 정하기)
		(6) 돈 도난당한 카드 (-건물값에 따라 금액 정하기)
		(7) 무인도 탈출 카드
		(8) 통행료 무료로 하는 카드
		*/
	}
}
