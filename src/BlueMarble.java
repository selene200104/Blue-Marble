import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.JOptionPane;
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
	JPanel rideAirplaneScene = new JPanel();
	JPanel luckeyCardScene = new JPanel();
	JPanel gameEndingScene = new JPanel() {
		public void paintComponent(Graphics g) {
			Dimension d = getSize();
			ImageIcon image = new ImageIcon("./images/finalBackground.jpg");
			g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		}
	};
	JPanel landPanel = new JPanel() {
		public void paintComponent(Graphics g) {
			Dimension d = getSize();
			ImageIcon image = new ImageIcon("./images/land.png");
			g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		}
	};

	JLabel landName = new JLabel();
	JLabel[] constructionPrice = new JLabel[4];
	JLabel constructionCostText = new JLabel();

	int nameHorizontalLength = 200;
	int nameVerticalLength = 20;
	int nameLineWidth = 100;
	int nameLineHeight = 20;

	int islandButtonHorizontalLength = 7;
	int islandButtonVerticalLength = 60;
	int islandButtonWidth = 100;
	int islandButtonHeight = 97;

	int constructionPriceHorizontalLength = 40;
	int constructionPriceVerticalLength = 160;
	int constructionPriceWidth = 100;
	int constructionPriceHeight = 20;

	int constructionCost = 0;

	int villaCheckCount = 0;
	int buildingCheckCount = 0;
	int hotelCheckCount = 0;

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

	Land[] land = new Land[30];
	JLabel[] landLabel = new JLabel[30];

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
	boolean isPlayer1hasuninhabitedCard = true;
	boolean isPlayer2hasuninhabitedCard = true;
	// 엔딩
	JLabel gameEndingText = new JLabel();
	JLabel winnerText = new JLabel();

	// 그 외
	JLabel playSituation = new JLabel();// 플레이 상황을 보여주는 text
	JLabel[] uninhabitedEmoticon = new JLabel[2]; // 무인도 탈출 카드 이미지
	JLabel[] uninhabitedEmoticonExplanation = new JLabel[2]; // 무인도 카드 이미지

	int socialWelfareCost = 10000; // 사회복지기금 비용
	int collectedSocialWelfare = 0; // 사회복지기금에 모인 돈
	int luckeyCardNum = 0; // 행운카드 번호

	// PlayerMove player1Move = new PlayerMove(player1, player1Image, playSituation,
	// player1moneyText);
	// PlayerMove player2Move = new PlayerMove(player2, player2Image, playSituation,
	// player2moneyText);
	PlayerMoves player1Move = new PlayerMoves(player1, player1Image, playSituation, player1moneyText);
	PlayerMoves player2Move = new PlayerMoves(player2, player2Image, playSituation, player2moneyText);
	GameEnding gameEnding = new GameEnding(player1, player2, gameEndingScene, blueMarbleScene, gameEndingText,
			winnerText);

	public BlueMarble() {

		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("blueMarble");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		gameEndingScene.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(gameEndingScene);
		gameEndingScene.setLayout(null);
		gameEndingScene.setVisible(false);

		luckeyCardScene.setBounds(280, 120, 250, 300);
		luckeyCardScene.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(luckeyCardScene);
		luckeyCardScene.setLayout(null);
		luckeyCardScene.setVisible(false);

		rideAirplaneScene.setBounds(180, 100, 400, 300);
		rideAirplaneScene.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(rideAirplaneScene);
		rideAirplaneScene.setLayout(null);
		rideAirplaneScene.setVisible(false);

		blueMarbleScene.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(blueMarbleScene);
		blueMarbleScene.setLayout(null);

		landPanel.setLayout(null);
		landPanel.setBounds(200, 100, 500, 300);
		blueMarbleScene.add(landPanel);
		landPanel.setVisible(false);

		for (int i = 0; i < landLabel.length; i++) {
			blueMarbleScene.add(landLabel[i] = new JLabel());
			landLabel[i].setBounds(200, 100, 450, 300);
			landLabel[i].setIcon(new ImageIcon("./images/land.png"));
			landLabel[i].setVisible(false);
		}

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

		landName.setLayout(null);
		landName.setText("");
		landName.setFont(new Font("굴림", Font.BOLD, 20));
		landName.setBounds(nameHorizontalLength, nameVerticalLength, nameLineWidth, nameLineHeight);

		// 땅 객체 배열 생성(위치 지역명 , 빌라 값, 빌딩 값, 호텔 값, 랜드마크 값, 빌라 선택 횟수, 빌딩 선택 횟수, 호텔 선택 횟수,
		// 랜드마크 선택 횟수, 땅 총 가격, 땅 주인, 빌라 갯수, 빌딩 갯수, 호텔 갯수, 랜드마크 갯수)
		land[0] = new Land("출발지", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);

		land[1] = new Land("서귀포", 10000, 30000, 50000, 50000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[2] = new Land("제주", 10000, 30000, 50000, 50000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[3] = new Land("독도", 10000, 30000, 50000, 50000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[4] = new Land("행운카드", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[5] = new Land("경주", 20000, 60000, 100000, 100000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[6] = new Land("안동", 20000, 60000, 100000, 100000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[7] = new Land("통영", 20000, 60000, 100000, 100000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[8] = new Land("창원", 20000, 60000, 100000, 100000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);

		land[9] = new Land("공항", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[10] = new Land("강릉", 30000, 90000, 150000, 150000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[11] = new Land("원주", 30000, 90000, 150000, 150000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[12] = new Land("춘천", 30000, 90000, 150000, 150000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[13] = new Land("청주", 40000, 120000, 200000, 200000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[14] = new Land("세종", 40000, 120000, 200000, 200000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);

		land[15] = new Land("무인도", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[16] = new Land("목포", 50000, 150000, 250000, 250000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[17] = new Land("여수", 50000, 150000, 250000, 250000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[18] = new Land("군산", 50000, 150000, 250000, 250000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[19] = new Land("전주", 50000, 150000, 250000, 250000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[20] = new Land("행운카드", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[21] = new Land("포항", 60000, 180000, 300000, 300000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[22] = new Land("울산", 60000, 180000, 300000, 300000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[23] = new Land("대구", 60000, 180000, 300000, 300000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);

		land[24] = new Land("국제기구?", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[25] = new Land("인천", 70000, 210000, 350000, 350000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[26] = new Land("광주", 70000, 210000, 350000, 350000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[27] = new Land("세금내는곳?", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[28] = new Land("부산", 80000, 240000, 400000, 400000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		land[29] = new Land("서울", 80000, 240000, 400000, 400000, 0, 0, 0, 0, 0, "", 0, 0, 0, 0);
		// land[30] = new Land("출발지", 0, 0, 0, 0, 0, 0, 0);

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
				// diceNum = 31;
				diceNumberText.setText("주사위 수 : " + diceNum);

				playSituation.setText("");

				// player1의 차례일 때
				if (whosTurn == 1) {

					// 비행기를 탔을 땐 주사위 버튼을 눌러도 말이 움직이면 안된다
					if (player1Flying == "비행기 타기") {
						// System.out.println("비행기를 탐");
						System.out.println("비행기 탄 후 플레이어 위치: " + player1.location);
					} else if (Player1forcedRest == 0) {
						player1leftdayOfisland.setVisible(false);

						player1.previousLocation = player1.location;
						player1.location = player1.location + diceNum;
						synchronized (player1Move) {
							player1Move.notify();
						}
						// playerMove(player1, player1Image);
						// player(player1, player1Image);

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
					System.out.println("isPlayer1hasuninhabitedCard" + isPlayer1hasuninhabitedCard);
					System.out.println("isPlayer2hasuninhabitedCard" + isPlayer2hasuninhabitedCard);

					// player2의 차례일 때
				} else if (whosTurn == 2) {

					if (player2Flying == "비행기 타기") {
						// System.out.println("비행기를 탐");

					} else if (Player2forcedRest == 0) {
						player2leftdayOfisland.setVisible(false);

						player2.previousLocation = player2.location;
						player2.location = player2.location + diceNum;
						synchronized (player2Move) {
							player2Move.notify();

						}
						// player(player2, player2Image);
						// playerMove(player2, player2Image);

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

				// 세계여행
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

					// 사회복지기금배당
				} else if (luckeyCardNum == 1) {

					if (isPlayer1hasCard == true) {
						// 사회복지기금 수령처로 이동한다.
						player1.location = 24;
						player1Image.setLocation(rightLine[0].getX() + 10, rightLine[0].getY() + 10);
						// 사회복지기금에 모인 돈을 받는다.
						player1.money = player1.money + collectedSocialWelfare;
						player1moneyText.setText("money : " + player1.money);
						isPlayer1hasCard = false;

					} else if (isPlayer2hasCard == true) {
						// 사회복지기금 수령처로 이동한다.
						player2.location = 24;
						player2Image.setLocation(rightLine[0].getX() + 10, rightLine[0].getY() + 10);
						// 사회복지기금에 모인 돈을 받는다.
						player2.money = player2.money + collectedSocialWelfare;
						player2moneyText.setText("money : " + player2.money);
						isPlayer2hasCard = false;
					}
					playSituation.setText("사회복지기금에서 돈을 얻었습니다 +" + collectedSocialWelfare + "원");
					collectedSocialWelfare = 0;

					// 고속도로
				} else if (luckeyCardNum == 2) {

					if (isPlayer1hasCard == true) {
						// 출발점으로 이동한다.
						player1.location = 0;
						player1Image.setLocation(rightLine[6].getX() + 10, rightLine[6].getY() + 10);
						// 출발점을 지나 월급을 받는다
						player1.money = player1.money + 10000;
						player1moneyText.setText("money : " + player1.money);
						isPlayer1hasCard = false;

					} else if (isPlayer2hasCard == true) {
						// 출발점으로 이동한다.
						player2.location = 0;
						player2Image.setLocation(rightLine[6].getX() + 10, rightLine[6].getY() + 10);
						// 출발점을 지나 월급을 받는다
						player2.money = player2.money + 10000;
						player2moneyText.setText("money : " + player2.money);
						isPlayer2hasCard = false;
					}
					playSituation.setText("출발점을 지나 월급 10000원을 얻었습니다");

					// 무인도로 가시오
				} else if (luckeyCardNum == 3) {

					if (isPlayer1hasCard == true) {
						// 무인도로 이동한다.
						player1.location = 15;
						player1Image.setLocation(leftLine[6].getX() + 10, leftLine[6].getY() + 10);
						player1leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : 3 일");
						player1leftdayOfisland.setVisible(true);
						Player1forcedRest = 3;

						if (isPlayer1hasuninhabitedCard == true) {
							luckeyCardScene.setVisible(true);
							diceThrowButton.setVisible(false);
							cardNameText.setText("무인도 탈출 카드");
							cardContentText.setText("사용하시겠습니까?");
						}
						isPlayer1hasCard = false;

					} else if (isPlayer2hasCard == true) {
						// 무인도로 이동한다.
						player2.location = 15;
						player2Image.setLocation(leftLine[6].getX() + 10, leftLine[6].getY() + 10);
						player2leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : 3 일");
						player2leftdayOfisland.setVisible(true);
						Player2forcedRest = 3;

						if (isPlayer2hasuninhabitedCard == true) {
							luckeyCardScene.setVisible(true);
							diceThrowButton.setVisible(false);
							cardNameText.setText("무인도 탈출 카드");
							cardContentText.setText("사용하시겠습니까?");
						}
						isPlayer2hasCard = false;
					}

					// 복권당첨
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

					// 지갑도난
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

					// 무인도 탈출
				} else if (luckeyCardNum == 6) {

					if (isPlayer1hasCard == true) {
						uninhabitedEmoticon[0].setVisible(true);
						isPlayer1hasuninhabitedCard = true;

					} else if (isPlayer2hasCard == true) {
						uninhabitedEmoticon[1].setVisible(true);
						isPlayer2hasuninhabitedCard = true;
					}

					// 우대권
				} else if (luckeyCardNum == 7) {

					if (isPlayer1hasCard == true) {

					} else if (isPlayer2hasCard == true) {

					}

				} else {
					// 무인도 탈출 카드를 가지고 있을 때 (무인도에 도착했을 때 luckeyCardScene이 보이도록했다) 탈출할 수 있다
					if (player1.location == 15) {
						if (isPlayer1hasuninhabitedCard == true) {
							uninhabitedEmoticon[0].setVisible(false);
							player1leftdayOfisland.setVisible(false);
							Player1forcedRest = 0;
							isPlayer1hasuninhabitedCard = false;
						}
					} else if (player2.location == 15) {
						if (isPlayer2hasuninhabitedCard == true) {
							uninhabitedEmoticon[1].setVisible(false);
							player2leftdayOfisland.setVisible(false);
							Player2forcedRest = 0;
							isPlayer2hasuninhabitedCard = false;

						}
					}
				}
				diceThrowButton.setVisible(true);
				luckeyCardScene.setVisible(false);
				// luckeyCardNum 초기화
				luckeyCardNum = 10;
			}
		});
		luckeyCardScene.add(useButton);

		// 게임 엔딩
		gameEndingText.setText("");
		gameEndingText.setHorizontalAlignment(SwingConstants.CENTER);
		gameEndingText.setFont(new Font("굴림", Font.BOLD, 40));
		gameEndingText.setBounds(0, 80, 800, 60);
		gameEndingScene.add(gameEndingText);

		winnerText.setText("");
		winnerText.setFont(new Font("굴림", Font.BOLD, 20));
		winnerText.setBounds(300, 300, 200, 60);
		gameEndingScene.add(winnerText);

		player1Move.start();
		player2Move.start();
		gameEnding.start();

	}

	public void playerMove(Player player, JLabel playerImage) {

		System.out.println("플레이어 움직임");

		// 건물 선택 버튼 배열 초기화
		JButton[] islandBulidingButton = new JButton[4];

		islandBulidingButton[0] = new JButton((new ImageIcon("./images/villa.png")));
		islandBulidingButton[1] = new JButton((new ImageIcon("./images/building.png")));
		islandBulidingButton[2] = new JButton((new ImageIcon("./images/hotel.png")));
		islandBulidingButton[3] = new JButton((new ImageIcon("./images/landmark.png")));

		/*
		 * if (player == player1) { if (player.round == 0) {
		 * islandBulidingButton[0].setEnabled(true);
		 * islandBulidingButton[1].setEnabled(false);
		 * islandBulidingButton[2].setEnabled(false);
		 * islandBulidingButton[3].setEnabled(false); } else if (player.round == 1) {
		 * islandBulidingButton[0].setEnabled(true);
		 * islandBulidingButton[1].setEnabled(true);
		 * islandBulidingButton[2].setEnabled(false);
		 * islandBulidingButton[3].setEnabled(false); } else if (player.round == 2) {
		 * islandBulidingButton[0].setEnabled(true);
		 * islandBulidingButton[1].setEnabled(true);
		 * islandBulidingButton[2].setEnabled(true);
		 * islandBulidingButton[3].setEnabled(false); } else {
		 * islandBulidingButton[0].setEnabled(true);
		 * islandBulidingButton[1].setEnabled(true);
		 * islandBulidingButton[2].setEnabled(true);
		 * islandBulidingButton[3].setEnabled(true); } } else { if (player.round == 0) {
		 * islandBulidingButton[0].setEnabled(true);
		 * islandBulidingButton[1].setEnabled(false);
		 * islandBulidingButton[2].setEnabled(false);
		 * islandBulidingButton[3].setEnabled(false); } else if (player.round == 1) {
		 * islandBulidingButton[0].setEnabled(true);
		 * islandBulidingButton[1].setEnabled(true);
		 * islandBulidingButton[2].setEnabled(false);
		 * islandBulidingButton[3].setEnabled(false); } else if (player.round == 2) {
		 * islandBulidingButton[0].setEnabled(true);
		 * islandBulidingButton[1].setEnabled(true);
		 * islandBulidingButton[2].setEnabled(true);
		 * islandBulidingButton[3].setEnabled(false); } else {
		 * islandBulidingButton[0].setEnabled(true);
		 * islandBulidingButton[1].setEnabled(true);
		 * islandBulidingButton[2].setEnabled(true);
		 * islandBulidingButton[3].setEnabled(true); } }
		 */

		// 각각의 건물 가격 배열 초기화
		constructionPrice[0] = new JLabel("" + land[player.location].villaPrice);
		constructionPrice[1] = new JLabel("" + land[player.location].buildingPrice);
		constructionPrice[2] = new JLabel("" + land[player.location].hotelPrice);
		constructionPrice[3] = new JLabel("" + land[player.location].landmarkPrice);
		
		// 총 건설 비용
		constructionCostText.setLayout(null);
		constructionCostText.setText("건설비용: " + land[player.location].constructionCost);
		constructionCostText.setBounds(10, 200, nameLineWidth, nameLineHeight);
		landLabel[player.location].add(constructionCostText);

		landLabel[player.location].add(landName);

		if (player.location >= 30) {
			player.location = player.location - 30;
		}

		if (player.location == 0) {
			playerImage.setLocation(rightLine[6].getX() + 10, rightLine[6].getY() + 10);
		} else {
			if (player.location != 4) {
				if (player.location != 9) {
					if (player.location != 15) {
						if (player.location != 20) {
							if (player.location != 24) {
								if (player.location != 27) {
									if (player == player1) {
										System.out.println("플레이어 1이 움직였습니다. 현재 플레이어는 " + player.round + "바퀴째 입니다.");
										System.out.println("지역이름 : " + land[player.location].landName + " | 땅 주인: "
												+ land[player.location].landowner + " | 도착 당시 | 빌라 선택횟수: "
												+ land[player.location].villaCheckCount + " | 빌딩 선택 횟수: "
												+ land[player.location].buildingCheckCount + " | 호텔 선택 횟수: "
												+ land[player.location].hotelCheckCount + " | 랜드마크 선택 횟수: "
												+ land[player.location].landmarkCheckCount + " | 가격 : "
												+ land[player.location].constructionCost);

										if (land[player.location].landowner == ""
												|| land[player.location].landowner == "player1") {
											landLabel[player.location].setVisible(true);
											diceThrowButton.setVisible(false);

											landName.setText("" + land[player.location].landName);

											System.out.println("지역이름 : " + land[player.location].landName + " | 땅 주인: "
													+ land[player.location].landowner + " | 도착 당시 | 빌라 선택횟수: "
													+ land[player.location].villaCheckCount + " | 빌딩 선택 횟수: "
													+ land[player.location].buildingCheckCount + " | 호텔 선택 횟수: "
													+ land[player.location].hotelCheckCount + " | 랜드마크 선택 횟수: "
													+ land[player.location].landmarkCheckCount + " | 가격 : "
													+ land[player.location].constructionCost);

											int islandButtonHorizontalLength = 7;

											// 버튼 위치 구성
											for (int k = 0; k < islandBulidingButton.length; k++) {
												landLabel[player.location].add(islandBulidingButton[k]);
												islandBulidingButton[k].setBounds(islandButtonHorizontalLength,
														islandButtonVerticalLength, islandButtonWidth,
														islandButtonHeight);
												islandButtonHorizontalLength = islandButtonHorizontalLength
														+ islandButtonWidth + 10;
											}

											/*
											 * if (player.round == 0) { System.out.println("플레이어1의 라운드 수 : " +
											 * player.round); islandBulidingButton[0].setEnabled(true);
											 * islandBulidingButton[1].setEnabled(false);
											 * islandBulidingButton[2].setEnabled(false);
											 * islandBulidingButton[3].setEnabled(false); } else if (player.round == 1)
											 * { System.out.println("플레이어1의 라운드 수 : " + player.round);
											 * islandBulidingButton[0].setEnabled(true);
											 * islandBulidingButton[1].setEnabled(true);
											 * islandBulidingButton[2].setEnabled(false);
											 * islandBulidingButton[3].setEnabled(false); } else if (player.round == 2)
											 * { System.out.println("플레이어1의 라운드 수 : " + player.round);
											 * islandBulidingButton[0].setEnabled(true);
											 * islandBulidingButton[1].setEnabled(true);
											 * islandBulidingButton[2].setEnabled(true);
											 * islandBulidingButton[3].setEnabled(false); } else {
											 * System.out.println("플레이어1의 라운드 수 : " + player.round);
											 * islandBulidingButton[0].setEnabled(true);
											 * islandBulidingButton[1].setEnabled(true);
											 * islandBulidingButton[2].setEnabled(true);
											 * islandBulidingButton[3].setEnabled(true); }
											 */

											int constructionPriceHorizontalLength = 40;

											// 가격 위치 구성
											for (int j = 0; j < constructionPrice.length; j++) {
												landLabel[player.location].add(constructionPrice[j]);
												constructionPrice[j].setBounds(constructionPriceHorizontalLength,
														constructionPriceVerticalLength, constructionPriceWidth,
														constructionPriceHeight);
												constructionPriceHorizontalLength = constructionPriceHorizontalLength
														+ constructionPriceWidth + 10;
											}
										}

									} else {
										System.out.println("플레이어 2이 움직였습니다. 현재 플레이어는 " + player.round + "바퀴째 입니다.");
										System.out.println("지역이름 : " + land[player.location].landName + " | 땅 주인: "
												+ land[player.location].landowner + " | 도착 당시 | 빌라 선택횟수: "
												+ land[player.location].villaCheckCount + " | 빌딩 선택 횟수: "
												+ land[player.location].buildingCheckCount + " | 호텔 선택 횟수: "
												+ land[player.location].hotelCheckCount + " | 랜드마크 선택 횟수: "
												+ land[player.location].landmarkCheckCount + " | 가격 : "
												+ land[player.location].constructionCost);

										if (land[player.location].landowner == ""
												|| land[player.location].landowner == "player2") {
											landLabel[player.location].setVisible(true);
											diceThrowButton.setVisible(false);

											// System.out.println("플레이어 2이 움직였습니다. 현재 플레이어는 " + player.round + "바퀴째
											// 입니다.");
											landName.setText("" + land[player.location].landName);

											System.out.println("지역이름 : " + land[player.location].landName + " | 땅 주인: "
													+ land[player.location].landowner + " | 도착 당시 | 빌라 선택횟수: "
													+ land[player.location].villaCheckCount + " | 빌딩 선택 횟수: "
													+ land[player.location].buildingCheckCount + " | 호텔 선택 횟수: "
													+ land[player.location].hotelCheckCount + " | 랜드마크 선택 횟수: "
													+ land[player.location].landmarkCheckCount + " | 가격 : "
													+ land[player.location].constructionCost);

											int islandButtonHorizontalLength = 7;

											// 버튼 위치 구성
											for (int k = 0; k < islandBulidingButton.length; k++) {
												landLabel[player.location].add(islandBulidingButton[k]);
												islandBulidingButton[k].setBounds(islandButtonHorizontalLength,
														islandButtonVerticalLength, islandButtonWidth,
														islandButtonHeight);
												islandButtonHorizontalLength = islandButtonHorizontalLength
														+ islandButtonWidth + 10;
											}

											/*
											 * if (player.round == 0) { System.out.println("플레이어2의 라운드 수 : " +
											 * player.round); islandBulidingButton[0].setEnabled(true);
											 * islandBulidingButton[1].setEnabled(false);
											 * islandBulidingButton[2].setEnabled(false);
											 * islandBulidingButton[3].setEnabled(false); } else if (player.round == 1)
											 * { System.out.println("플레이어2의 라운드 수 : " + player.round);
											 * islandBulidingButton[0].setEnabled(true);
											 * islandBulidingButton[1].setEnabled(true);
											 * islandBulidingButton[2].setEnabled(false);
											 * islandBulidingButton[3].setEnabled(false); } else if (player.round == 2)
											 * { System.out.println("플레이어1의 라운드 수 : " + player.round);
											 * islandBulidingButton[0].setEnabled(true);
											 * islandBulidingButton[1].setEnabled(true);
											 * islandBulidingButton[2].setEnabled(true);
											 * islandBulidingButton[3].setEnabled(false); } else {
											 * System.out.println("플레이어2의 라운드 수 : " + player.round);
											 * islandBulidingButton[0].setEnabled(true);
											 * islandBulidingButton[1].setEnabled(true);
											 * islandBulidingButton[2].setEnabled(true);
											 * islandBulidingButton[3].setEnabled(true); }
											 */

											int constructionPriceHorizontalLength = 40;

											// 가격 위치 구성
											for (int j = 0; j < constructionPrice.length; j++) {
												landLabel[player.location].add(constructionPrice[j]);
												constructionPrice[j].setBounds(constructionPriceHorizontalLength,
														constructionPriceVerticalLength, constructionPriceWidth,
														constructionPriceHeight);
												constructionPriceHorizontalLength = constructionPriceHorizontalLength
														+ constructionPriceWidth + 10;
											}
										}

									}
								}
							}
						}
					}
				}
			}
		}

		// 빌라 버튼 클릭했을 때
		islandBulidingButton[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (land[player.location].amountVilla != 1) {
					System.out.println("빌라 선택함");
					if (land[player.location].villaCheckCount == 0) {
						land[player.location].villaCheckCount++;
						System.out.println("지역이름 : " + land[player.location].landName + " 빌라 선택횟수: "
								+ land[player.location].villaCheckCount);
						islandBulidingButton[0].setIcon(new ImageIcon("./images/villaCheck.png"));

						land[player.location].constructionCost += land[player.location].villaPrice;
						constructionCostText.setText("건설비용: " + land[player.location].constructionCost);
					} else {
						land[player.location].villaCheckCount--;
						System.out.println("지역이름 : " + land[player.location].landName + " 빌라 선택횟수: "
								+ land[player.location].villaCheckCount);
						islandBulidingButton[0].setIcon(new ImageIcon("./images/villa.png"));

						land[player.location].constructionCost -= land[player.location].villaPrice;
						constructionCostText.setText("건설비용: " + land[player.location].constructionCost);
					}
				}
			}
		});

		// 빌딩 버튼 클릭했을 때
		islandBulidingButton[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (land[player.location].amountBuilding != 1) {
					System.out.println("빌딩 선택함");
					if (land[player.location].buildingCheckCount == 0) {
						land[player.location].buildingCheckCount++;
						System.out.println("지역이름 : " + land[player.location].landName + " 빌딩 선택횟수: "
								+ land[player.location].buildingCheckCount);
						islandBulidingButton[1].setIcon(new ImageIcon("./images/buildingCheck.png"));

						land[player.location].constructionCost += land[player.location].buildingPrice;
						constructionCostText.setText("건설비용: " + land[player.location].constructionCost);
					} else {
						land[player.location].buildingCheckCount--;
						System.out.println("지역이름 : " + land[player.location].landName + " 빌딩 선택횟수: "
								+ land[player.location].buildingCheckCount);
						islandBulidingButton[1].setIcon(new ImageIcon("./images/building.png"));

						land[player.location].constructionCost -= land[player.location].buildingPrice;
						constructionCostText.setText("건설비용: " + land[player.location].constructionCost);
					}
				}
			}
		});

		// 호텔 버튼 클릭했을 때
		islandBulidingButton[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (land[player.location].amountHotel != 1) {
					System.out.println("호텔 선택함");
					if (land[player.location].hotelCheckCount == 0) {
						land[player.location].hotelCheckCount++;
						System.out.println("지역이름 : " + land[player.location].landName + " 호텔 선택횟수: "
								+ land[player.location].hotelCheckCount);
						islandBulidingButton[2].setIcon(new ImageIcon("./images/hotelCheck.png"));

						land[player.location].constructionCost += land[player.location].hotelPrice;
						constructionCostText.setText("건설비용: " + land[player.location].constructionCost);
					} else {
						land[player.location].hotelCheckCount--;
						System.out.println("지역이름 : " + land[player.location].landName + " 호텔 선택횟수: "
								+ land[player.location].hotelCheckCount);
						islandBulidingButton[2].setIcon(new ImageIcon("./images/hotel.png"));

						land[player.location].constructionCost -= land[player.location].hotelPrice;
						constructionCostText.setText("건설비용: " + land[player.location].constructionCost);
					}
				}
			}
		});

		// 랜드마크 버튼 클릭했을 때
		islandBulidingButton[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (land[player.location].amountLandmark != 1) {
					System.out.println("랜드마크 선택함");
					if (land[player.location].landmarkCheckCount == 0) {
						land[player.location].landmarkCheckCount++;
						System.out.println("지역이름 : " + land[player.location].landName + " 랜드마크 선택횟수: "
								+ land[player.location].landmarkCheckCount);
						islandBulidingButton[3].setIcon(new ImageIcon("./images/landmarkCheck.png"));

						land[player.location].constructionCost += land[player.location].landmarkPrice;
						constructionCostText.setText("건설비용: " + land[player.location].constructionCost);
					} else {
						land[player.location].landmarkCheckCount--;
						System.out.println("지역이름 : " + land[player.location].landName + " 랜드마크 선택횟수: "
								+ land[player.location].landmarkCheckCount);
						islandBulidingButton[3].setIcon(new ImageIcon("./images/landmark.png"));

						land[player.location].constructionCost -= land[player.location].landmarkPrice;
						constructionCostText.setText("건설비용: " + land[player.location].constructionCost);
					}
				}
			}
		});

		JButton CloseButton = new JButton((new ImageIcon("./images/closeButton.png")));
		CloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < landLabel.length; i++) {
					landLabel[i].setVisible(false);
				}
				diceThrowButton.setVisible(true);

				// 나중에 구입과 관련된 기능 구현시 수정해야함
				if (land[player.location].amountVilla != 1) {
					land[player.location].villaCheckCount -= land[player.location].villaCheckCount;
				}
				if (land[player.location].amountBuilding != 1) {
					land[player.location].buildingCheckCount -= land[player.location].buildingCheckCount;
				}
				if (land[player.location].amountHotel != 1) {
					land[player.location].hotelCheckCount -= land[player.location].hotelCheckCount;
				}
				if (land[player.location].amountLandmark != 1) {
					land[player.location].landmarkCheckCount -= land[player.location].landmarkCheckCount;
				}
				land[player.location].constructionCost -= land[player.location].constructionCost;
				constructionCostText.setText("건설비용: " + land[player.location].constructionCost);

				// 취소할때, 버튼 이미지를 다시 원래대로 수정한다.
				if (land[player.location].amountVilla != 1) {
					islandBulidingButton[0].setIcon(new ImageIcon("./images/villa.png"));
				} else {
					islandBulidingButton[0].setIcon(new ImageIcon("./images/villaCheck.png"));
				}

				if (land[player.location].amountBuilding != 1) {
					islandBulidingButton[1].setIcon(new ImageIcon("./images/building.png"));
				} else {
					islandBulidingButton[1].setIcon(new ImageIcon("./images/buildingCheck.png"));
				}

				if (land[player.location].amountHotel != 1) {
					islandBulidingButton[2].setIcon(new ImageIcon("./images/hotel.png"));
				} else {
					islandBulidingButton[2].setIcon(new ImageIcon("./images/hotelCheck.png"));
				}

				if (land[player.location].amountLandmark != 1) {
					islandBulidingButton[3].setIcon(new ImageIcon("./images/landmark.png"));
				} else {
					islandBulidingButton[3].setIcon(new ImageIcon("./images/landmarkCheck.png"));
				}

				System.out.println("빌라 체크: " + land[player.location].villaCheckCount + "빌딩 체크: "
						+ land[player.location].buildingCheckCount + "호텔 체크: " + land[player.location].hotelCheckCount
						+ "랜드마크 체크: " + land[player.location].landmarkCheckCount);
				// 나중에 구입과 관련된 기능 구현시 수정해야함
			}
		});
		CloseButton.setBounds(400, 0, 50, 50);
		landLabel[player.location].add(CloseButton);
		CloseButton.setVisible(true);
		CloseButton.setBorderPainted(false);
		CloseButton.setFocusPainted(false);
		CloseButton.setContentAreaFilled(false);

		JButton buyButton = new JButton("구매하기");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//땅을 구입할때 건축비용이 0원 이상이고 , 플레이어가 소지한 돈이 건축비용보다 많아야만 구입할 수 있다.
				if(land[player.location].constructionCost > 0) {
					if (player.money >= land[player.location].constructionCost) {
						landLabel[player.location].setVisible(false);
						diceThrowButton.setVisible(true);
						
						if (player == player1) {
							land[player.location].landowner = "player1";
							if (land[player.location].villaCheckCount == 1) {
								land[player.location].amountVilla = 1;
							}
							if (land[player.location].buildingCheckCount == 1) {
								land[player.location].amountBuilding = 1;
							}
							if (land[player.location].hotelCheckCount == 1) {
								land[player.location].amountHotel = 1;
								//land[player.location].hotelCheckCount = 2;
							}
							if (land[player.location].landmarkCheckCount == 1) {
								land[player.location].amountLandmark = 1;
								//land[player.location].landmarkCheckCount = 2;
							}

							//플레이어1이 구입한 땅에 플레이어1의 색으로 덮힌다.
							if (player.location >= 1 && player.location <= 8) {
								bottomLine[player.location - 1].setIcon(new ImageIcon("./images/BlueLine.png"));
							} else if (player.location >= 9 && player.location <= 15) {
								leftLine[player.location - 9].setIcon(new ImageIcon("./images/BlueLine.png"));
							} else if (player.location >= 16 && player.location <= 23) {
								topLine[player.location - 16].setIcon(new ImageIcon("./images/BlueLine.png"));
							} else if (player.location >= 24 && player.location <= 29) {
								rightLine[player.location - 24].setIcon(new ImageIcon("./images/BlueLine.png"));
							}

							player1.money = player1.money - land[player.location].constructionCost;
							player1moneyText.setText("money : " + player1.money);
							land[player.location].constructionCost = 0;

						} else {
							land[player.location].landowner = "player2";
							if (land[player.location].villaCheckCount == 1) {
								land[player.location].amountVilla = 1;
							}
							if (land[player.location].buildingCheckCount == 1) {
								land[player.location].amountBuilding = 1;
							}
							if (land[player.location].hotelCheckCount == 1) {
								land[player.location].amountHotel = 1;
								//land[player.location].hotelCheckCount = 2;
							}
							if (land[player.location].landmarkCheckCount == 1) {
								land[player.location].amountLandmark = 1;
							}

							//플레이어2가 구입한 땅에 플레이어2의 색으로 덮힌다.
							if (player.location >= 1 && player.location <= 8) {
								bottomLine[player.location - 1].setIcon(new ImageIcon("./images/RedLine.png"));
							} else if (player.location >= 9 && player.location <= 15) {
								leftLine[player.location - 9].setIcon(new ImageIcon("./images/RedLine.png"));
							} else if (player.location >= 16 && player.location <= 23) {
								topLine[player.location - 16].setIcon(new ImageIcon("./images/RedLine.png"));
							} else if (player.location >= 24 && player.location <= 29) {
								rightLine[player.location - 24].setIcon(new ImageIcon("./images/RedLine.png"));
							}

							player2.money = player2.money - land[player.location].constructionCost;
							player2moneyText.setText("money : " + player2.money);
							land[player.location].constructionCost = 0;
						}
					} else {
						JOptionPane.showMessageDialog(frame, "돈이 부족하여 살 수 없어요.", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		buyButton.setBounds(160, 250, 150, 20);
		landLabel[player.location].add(buyButton);
		buyButton.setVisible(true);
	}

	public void player(Player player, JLabel playerImage) {

		if (player.location == 4 || player.location == 20) {

			if (player == player1) {
				isPlayer1hasCard = true;
			} else if (player == player2) {
				isPlayer2hasCard = true;

			} else if (player == player2) {

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

				if (isPlayer1hasuninhabitedCard == true) {
					luckeyCardScene.setVisible(true);
					cardNameText.setText("무인도 탈출 카드");
					cardContentText.setText("사용하시겠습니까?");
				}

			} else if (player == player2) {
				playSituation.setText("Player2이 무인도에 갇혔습니다");
				player2leftdayOfisland.setVisible(true);
				Player2forcedRest = 3;

				if (isPlayer2hasuninhabitedCard == true) {
					luckeyCardScene.setVisible(true);
					cardNameText.setText("무인도 탈출 카드");
					cardContentText.setText("사용하시겠습니까?");
				}
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
		// playSituation.setVisible(false);

		luckeyCardNum = ramdom.nextInt(7);
		// luckeyCardNum = 7;
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

	class PlayerMoves extends Thread {

		Player player = new Player();
		JLabel playerImage = new JLabel();
		JLabel playSituation = new JLabel();
		JLabel playermoneyText = new JLabel();

		boolean running = true;

		public PlayerMoves(Player player, JLabel playerImage, JLabel playSituation, JLabel playermoneyText) {
			this.player = player;
			this.playerImage = playerImage;
			this.playSituation = playSituation;
			this.playermoneyText = playermoneyText;
		}

		@Override
		public synchronized void run() {
			while (running) {
				try {

					if (player.previousLocation <= player.location) {

						if (player.location >= 30) {
							if (player.previousLocation >= 30) {
								player.previousLocation = player.previousLocation - 30;
								player.location = player.location - 30;
								// 월급기능
								player.money = player.money + 10000;
								playermoneyText.setText("money : " + player.money);
								playSituation.setText("출발점을 지나 월급 10000원을 얻었습니다");

								// 한바퀴를 돌면 round가 1씩 늘어남
								if (player == player1) {
									player1.round++;
									System.out.println("player1은 지금 " + player1.round + "바퀴째 입니다.");
								} else {
									player2.round++;
									System.out.println("player2는 지금 " + player2.round + "바퀴째 입니다.");
								}
							}
						}

						if (player.previousLocation == 0) {
							playerImage.setLocation(BlueMarble.rightLine[6].getX() + 10,
									BlueMarble.rightLine[6].getY() + 10);

						} else if (player.previousLocation >= 1 && player.previousLocation <= 8) {
							for (int i = 0; i <= player.previousLocation - 1; i++) {
								playerImage.setLocation(BlueMarble.bottomLine[i].getX() + 10,
										BlueMarble.bottomLine[i].getY() + 10);
							}

						} else if (player.previousLocation >= 9 && player.previousLocation <= 15) {
							for (int i = 0; i <= player.previousLocation - 9; i++) {
								playerImage.setLocation(BlueMarble.leftLine[i].getX() + 10,
										BlueMarble.leftLine[i].getY() + 10);
							}

						} else if (player.previousLocation >= 16 && player.previousLocation <= 23) {
							for (int i = 0; i <= player.previousLocation - 16; i++) {
								playerImage.setLocation(BlueMarble.topLine[i].getX() + 10,
										BlueMarble.topLine[i].getY() + 10);
							}

						} else if (player.previousLocation >= 24 && player.previousLocation <= 29) {
							for (int i = 0; i <= player.previousLocation - 24; i++) {
								playerImage.setLocation(BlueMarble.rightLine[i].getX() + 10,
										BlueMarble.rightLine[i].getY() + 10);
							}
						}

						player.previousLocation++;
						Thread.sleep(150);

					} else {
						playerMove(player, playerImage);
						player(player, playerImage);
						this.wait();

					}

				} catch (InterruptedException e) {
					running = false;
				}
			}
		}
	}
}