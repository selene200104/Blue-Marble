import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	JPanel luckeyCardScene = new JPanel();

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

	// 주사위
	JButton diceThrowButton = new JButton();
	JLabel diceNumberText = new JLabel();

	int diceNum = 0;

	// 공항
	JLabel chooseRideAirplane = new JLabel();
	JLabel cautionPointText = new JLabel();
	JButton okButton = new JButton();
	JButton noButton = new JButton();

	int airplaneFee = 20000;
	String player1Flying = "";
	String player2Flying = "";
	String whoRideAirplane = "";

	// 행운 카드
	JLabel cardNameText = new JLabel();
	JLabel cardContentText = new JLabel();
	JButton useButton = new JButton();

	boolean isPlayer1hasCard = false;
	boolean isPlayer2hasCard = false;

	// 그 외
	JLabel playSituation = new JLabel();// 플레이 상황을 보여주는 text
	JLabel[] uninhabitedEmoticon = new JLabel[2]; //무인도 탈출 카드 이미지
	JLabel[] uninhabitedEmoticonExplanation = new JLabel[2]; //무인도 카드 이미지
	int socialWelfareCost = 10000; // 사회복지기금 비용
	int collectedSocialWelfare = 0; // 사회복지기금에 모인 돈
	int luckeyCardNum = 0; // 행운카드 번호

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

		luckeyCardScene.setBounds(280, 120, 250, 300);
		luckeyCardScene.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(luckeyCardScene);
		luckeyCardScene.setLayout(null);
		luckeyCardScene.setVisible(false);

		blueMarbleScene.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(blueMarbleScene);
		blueMarbleScene.setLayout(null);

		// 플레이어
		player1Image.setIcon(new ImageIcon("./images/Player1.png"));
		player1Image.setBounds(715, 495, 60, 60);
		blueMarbleScene.add(player1Image);

		player2Image.setIcon(new ImageIcon("./images/Player2.png"));
		player2Image.setBounds(715, 495, 60, 60);
		blueMarbleScene.add(player2Image);

		player1moneyText.setText("money : 30000");
		player1moneyText.setFont(new Font("굴림", Font.BOLD, 13));
		player1moneyText.setBounds(130, 190, 110, 60);
		blueMarbleScene.add(player1moneyText);

		player2moneyText.setText("money : 30000");
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

		for (int i = 0; i < uninhabitedEmoticon.length; i++) {
			blueMarbleScene.add(uninhabitedEmoticon[i] = new JLabel());
			blueMarbleScene.add(uninhabitedEmoticonExplanation[i] = new JLabel());
			
			uninhabitedEmoticon[i].setIcon(new ImageIcon("./images/uninhabitedCardEmoticon.png"));
			uninhabitedEmoticonExplanation[i].setText("무인도 탈출 카드 소유중");
			
			uninhabitedEmoticon[i].setVisible(false);
			uninhabitedEmoticonExplanation[i].setVisible(false);
			
			uninhabitedEmoticon[i].addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					for (int i = 0; i < uninhabitedEmoticon.length; i++) {
						if (e.getSource() == uninhabitedEmoticon[i]) {
							uninhabitedEmoticonExplanation[i].setVisible(true);
						}
					}
				}
				
				public void mouseExited(MouseEvent e) {
					for (int i = 0; i < uninhabitedEmoticon.length; i++) {
						if (e.getSource() == uninhabitedEmoticon[i]) {
							uninhabitedEmoticonExplanation[i].setVisible(false);
						}
					}
			    }
			});
		}
		uninhabitedEmoticon[0].setBounds(260, 200, 50, 50);
		uninhabitedEmoticon[1].setBounds(260, 400, 50, 50);
		uninhabitedEmoticonExplanation[0].setBounds(310, 200, 250, 50);
		uninhabitedEmoticonExplanation[1].setBounds(310, 400, 250, 50);
		
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
				diceNum = ramdom.nextInt(6) + 1;
				diceNumberText.setText("주사위 수 : " + diceNum);

				playSituation.setText("");

				// player1의 차례일 때
				if (whosTurn == 1) {

					// 비행기를 탔을 땐 주사위 버튼을 눌러도 말이 움직이면 안된다
					if (player1Flying == "비행기 타기") {
						System.out.println("비행기를 탐");

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
					player1Flying = "";

					// player1이 움직인 후 player2가 주사위 버튼을 누르지 않고 바로 비행기를 탈 수 있도록 하기 위함
					if (player2Flying == "비행기 타기") {
						airport();
						playSituation.setText("원하는 도시를 클릭 후 주사위 버튼을 눌러주세요");
					}

					// 플레이어을 움직인 후 차례를 바꾼다
					whosTurnText.setText("Player 2 순서");
					whosTurn = 2;

					// player2의 차례일 때
				} else if (whosTurn == 2) {

					if (player2Flying == "비행기 타기") {
						System.out.println("비행기를 탐");

					} else if (Player2forcedRest == 0) {
						player2leftdayOfisland.setVisible(false);

						player2.previousLocation = player2.location;
						player2.location = player2.location + diceNum;
						synchronized (player2Move) {
							player2Move.notify();
						}
						
						System.out.println(player2.previousLocation);
						System.out.println(player2.previousLocation);

						player(player2, player2Image);

					} else {
						Player2forcedRest--;
						player2leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : " + (Player2forcedRest + 1) + " 일");
					}

					if (player1Flying == "비행기 타기") {
						airport();
						playSituation.setText("원하는 도시를 클릭 후 주사위 버튼을 눌러주세요");
					}
					player2Flying = "";

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

		// 공항(비행기)
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

		// 행운카드
		cardNameText.setText("");
		cardNameText.setFont(new Font("굴림", Font.BOLD, 17));
		cardNameText.setForeground(Color.white);
		cardNameText.setBounds(70, 10, 200, 60);
		luckeyCardScene.add(cardNameText);

		cardContentText.setText("");
		cardContentText.setFont(new Font("굴림", Font.BOLD, 13));
		cardContentText.setForeground(Color.white);
		cardContentText.setBounds(20, 50, 250, 60);
		luckeyCardScene.add(cardContentText);

		useButton.setText("사용하기");
		useButton.setBounds(80, 200, 100, 50);
		useButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//세계여행
				if (luckeyCardNum == 0) {
					if (isPlayer1hasCard == true) {
						player1Flying = "비행기 타기(카드)";
						airport();
						playSituation.setText("원하는 도시를 클릭 후 주사위 버튼을 눌러주세요");
						isPlayer1hasCard = false;
						
					} else if (isPlayer2hasCard == true) {
						player2Flying = "비행기 타기(카드)";
						airport();
						playSituation.setText("원하는 도시를 클릭 후 주사위 버튼을 눌러주세요");
						isPlayer2hasCard = false;
					}

				//사회복지기금배당
				} else if (luckeyCardNum == 1) {

					if (isPlayer1hasCard == true) {
						//사회복지기금 수령처로 이동한다.
						player1.location = 24;
						player1Image.setLocation(rightLine[0].getX() + 10, rightLine[0].getY() + 10);
						//사회복지기금에 모인 돈을 받는다.
						player1.money = player1.money + collectedSocialWelfare;
						player1moneyText.setText("money : " + player1.money);
						isPlayer1hasCard = false;
						
					} else if (isPlayer2hasCard == true) {
						//사회복지기금 수령처로 이동한다.
						player2.location = 24;
						player2Image.setLocation(rightLine[0].getX() + 10, rightLine[0].getY() + 10);
						//사회복지기금에 모인 돈을 받는다.
						player2.money = player2.money + collectedSocialWelfare;
						player2moneyText.setText("money : " + player2.money);
						isPlayer2hasCard = false;
					}
					playSituation.setText("사회복지기금에서 돈을 얻었습니다 +" + collectedSocialWelfare + "원");
					collectedSocialWelfare = 0;
					
				//고속도로
				} else if (luckeyCardNum == 2) {

					if (isPlayer1hasCard == true) {
						//출발점으로 이동한다.
						player1.location = 0;
						player1Image.setLocation(rightLine[6].getX() + 10, rightLine[6].getY() + 10);
						//출발점을 지나 월급을 받는다
						player1.money = player1.money + 10000;
						player1moneyText.setText("money : " + player1.money);
						isPlayer1hasCard = false;
						
					} else if (isPlayer2hasCard == true) {
						//출발점으로 이동한다.
						player2.location = 0;
						player2Image.setLocation(rightLine[6].getX() + 10, rightLine[6].getY() + 10);
						//출발점을 지나 월급을 받는다
						player2.money = player2.money + 10000;
						player2moneyText.setText("money : " + player2.money);
						isPlayer2hasCard = false;
					}
					playSituation.setText("출발점을 지나 월급 10000원을 얻었습니다");
					
				//무인도로 가시오
				} else if (luckeyCardNum == 3) {

					if (isPlayer1hasCard == true) {
						//무인도로 이동한다.
						player1.location = 15;
						player1Image.setLocation(leftLine[6].getX() + 10, leftLine[6].getY() + 10);
						player1leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : 3 일");
						player1leftdayOfisland.setVisible(true);
						Player1forcedRest = 3;
						isPlayer1hasCard = false;
						
					} else if (isPlayer2hasCard == true) {
						//무인도로 이동한다.
						player2.location = 15;
						player2Image.setLocation(leftLine[6].getX() + 10, leftLine[6].getY() + 10);
						player2leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : 3 일");
						player2leftdayOfisland.setVisible(true);
						Player2forcedRest = 3;
						isPlayer2hasCard = false;
					}
					
				//복권당첨
				} else if (luckeyCardNum == 4) {

					if (isPlayer1hasCard == true) {
						player1.money = player1.money + 100000;
						player1moneyText.setText("money : " + player1.money);
						isPlayer1hasCard = false;

					} else if (isPlayer2hasCard == true) {
						player2.money = player2.money + 100000;
						player2moneyText.setText("money : " + player2.money);
						isPlayer2hasCard = false;
					}
					
				//지갑도난
				} else if (luckeyCardNum == 5) {

					if (isPlayer1hasCard == true) {
						player1.money = player1.money - 50000;
						player1moneyText.setText("money : " + player1.money);
						isPlayer1hasCard = false;

					} else if (isPlayer2hasCard == true) {
						player2.money = player2.money - 50000;
						player2moneyText.setText("money : " + player2.money);
						isPlayer2hasCard = false;
					}

				//무인도 탈출
				} else if (luckeyCardNum == 6) {

					if (isPlayer1hasCard == true) {
						uninhabitedEmoticon[0].setVisible(true);

					} else if (isPlayer2hasCard == true) {
						uninhabitedEmoticon[1].setVisible(true);
					}

				//우대권
				} else if (luckeyCardNum == 7) {

					if (isPlayer1hasCard == true) {


					} else if (isPlayer2hasCard == true) {

					}
				}

				diceThrowButton.setVisible(true);
				luckeyCardScene.setVisible(false);
			}
		});
		luckeyCardScene.add(useButton);

		player1Move.start();
		player2Move.start();
	}

	public void player(Player player, JLabel playerImage) {

		if (player.location == 4 || player.location == 19) {

			if (player == player1) {
				isPlayer1hasCard = true;
			} else if (player == player2) {
				isPlayer2hasCard = true;
			}

			luckyCard(player);
			playSituation.setText("행운카드에 도착했습니다");

			// 플레이어가 공항에 도착했을 때
		} else if (player.location == 9) {
			if (player == player1) {
				whoRideAirplane = "player1";
			} else if (player == player2) {
				whoRideAirplane = "player2";
			}
			diceThrowButton.setVisible(false);
			rideAirplaneScene.setVisible(true);
			chooseRideAirplane.setText("비행기를 타시겠습니까?");
			playSituation.setText("공항에 도착했습니다");

			// 플레이어가 무인도에 도착했을 때
		} else if (player.location == 15) {
			if (player == player1) {
				player1leftdayOfisland.setVisible(true);
				Player1forcedRest = 3;

			} else if (player == player2) {
				player2leftdayOfisland.setVisible(true);
				Player2forcedRest = 3;
			}
			playSituation.setText("무인도에 갇혔습니다");

			// 플레이어가 사회복지기금(돈 얻음)에 도착했을 때
		} else if (player.location == 24) {
			player.money = player.money + collectedSocialWelfare;

			if (player == player1) {
				player1moneyText.setText("money : " + player.money);
			} else if (player == player2) {
				player2moneyText.setText("money : " + player.money);
			}
			playSituation.setText("사회복지기금에서 돈을 얻었습니다 +" + collectedSocialWelfare + "원");
			collectedSocialWelfare = 0;

			// 플레이어가 사회복지기금(돈 지불)에 도착했을 때
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
					if (player1Flying.contains("비행기 타기")) {
						for (int i = 0; i < bottomLine.length; i++) {
							if (e.getSource() == bottomLine[i]) {
								player1Image.setLocation(bottomLine[i].getX() + 10, bottomLine[i].getY() + 10);
								player1.location = i + 1;
							}
						}
					} else if (player2Flying.contains("비행기 타기")) {
						for (int i = 0; i < bottomLine.length; i++) {
							if (e.getSource() == bottomLine[i]) {
								player2Image.setLocation(bottomLine[i].getX() + 10, bottomLine[i].getY() + 10);
								player2.location = i + 1;
							}
						}
					}
				}
			});
		}

		for (int i = 0; i < leftLine.length; i++) {
			leftLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (player1Flying.contains("비행기 타기")) {
						for (int i = 0; i < leftLine.length; i++) {
							if (e.getSource() == leftLine[i]) {
								player1Image.setLocation(leftLine[i].getX() + 10, leftLine[i].getY() + 10);
								player1.location = i + 9;
							}
						}
					} else if (player2Flying.contains("비행기 타기")) {
						for (int i = 0; i < leftLine.length; i++) {
							if (e.getSource() == leftLine[i]) {
								player2Image.setLocation(leftLine[i].getX() + 10, leftLine[i].getY() + 10);
								player2.location = i + 9;
							}
						}
					}
				}
			});
		}

		for (int i = 0; i < topLine.length; i++) {
			topLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (player1Flying.contains("비행기 타기")) {
						for (int i = 0; i < topLine.length; i++) {
							if (e.getSource() == topLine[i]) {
								player1Image.setLocation(topLine[i].getX() + 10, topLine[i].getY() + 10);
								player1.location = i + 16;
							}
						}
					} else if (player2Flying.contains("비행기 타기")) {
						for (int i = 0; i < topLine.length; i++) {
							if (e.getSource() == topLine[i]) {
								player2Image.setLocation(topLine[i].getX() + 10, topLine[i].getY() + 10);
								player2.location = i + 16;
							}
						}
					}
				}
			});
		}

		for (int i = 0; i < rightLine.length; i++) {
			rightLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (player1Flying.contains("비행기 타기")) {
						for (int i = 0; i < rightLine.length; i++) {
							if (e.getSource() == rightLine[i]) {
								player1Image.setLocation(rightLine[i].getX() + 10, rightLine[i].getY() + 10);
								player1.location = i + 24;
							}
						}
					} else if (player2Flying.contains("비행기 타기")) {
						for (int i = 0; i < rightLine.length; i++) {
							if (e.getSource() == rightLine[i]) {
								player2Image.setLocation(rightLine[i].getX() + 10, rightLine[i].getY() + 10);
								player2.location = i + 24;
							}
						}
					}
				}
			});
		}
	}

	public void luckyCard(Player player) {

		luckeyCardScene.setVisible(true);
		diceThrowButton.setVisible(false);

		luckeyCardNum = ramdom.nextInt(7);
		luckeyCardNum = 6;
		
		if (luckeyCardNum == 0) {
			cardNameText.setText("세계여행");
			cardContentText.setText("선택한 지역으로 이동할 수 있다");

		} else if (luckeyCardNum == 1) {
			cardNameText.setText("사회복지기금배당");
			cardContentText.setText("사회복지기금 수령처로 바로 이동한다");

		} else if (luckeyCardNum == 2) {
			cardNameText.setText("고속도로");
			cardContentText.setText("출발지로 바로 이동한다");

		} else if (luckeyCardNum == 3) {
			cardNameText.setText("무인도로 가시오");
			cardContentText.setText("무인도로 바로 이동한다");

		} else if (luckeyCardNum == 4) {
			cardNameText.setText("복권당첨");
			cardContentText.setText("복권이 당첨되어 10만원을 받는다");

		} else if (luckeyCardNum == 5) {
			cardNameText.setText("지갑 도난");
			cardContentText.setText("지갑이 도난당하여 5만원을 잃는다");

		} else if (luckeyCardNum == 6) {
			cardNameText.setText("무인도 탈출 카드");
			cardContentText.setText("무인도에서 빠져나올 수 있는 카드");

		} else if (luckeyCardNum == 7) {
			cardNameText.setText("우대권");
			cardContentText.setText("통행료가 무료가 되는 카드");
		}
	}
}
