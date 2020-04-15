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
	JPanel luckeyCardScene = new JPanel();
	JPanel rideAirplaneScene = new JPanel();
	JPanel acquisitionScene = new JPanel(); // 인수 창
	JPanel fineScene = new JPanel(); // 벌금 창 (상대방의 땅에 걸렸을 때)
	// JPanel sellLandPanel = new JPanel();

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

	JLabel[] constructionPrice = new JLabel[4]; // 건물 건설 비용
	JLabel constructionCostText = new JLabel(); // 건물 건설 텍스트

	JLabel landName = new JLabel();

	int nameHorizontalLength = 200;
	int nameVerticalLength = 20;
	int nameLineWidth = 100;
	int nameLineHeight = 20;

	int landButtonHorizontalLength = 7;
	int landButtonVerticalLength = 60;
	int landButtonWidth = 100;
	int landButtonHeight = 97;

	int constructionPriceHorizontalLength = 40;
	int constructionPriceVerticalLength = 160;
	int constructionPriceWidth = 100;
	int constructionPriceHeight = 20;

	int constructionCost = 0; // 총 건물 비용

	int villaCheckCount = 0;
	int buildingCheckCount = 0;
	int hotelCheckCount = 0;

	// 플레이어
	JLabel player1Image = new JLabel();
	JLabel player2Image = new JLabel();
	JLabel player1informationBoard = new JLabel();
	JLabel player2informationBoard = new JLabel();
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
	static JLabel[] landTopLine = new JLabel[8];
	static JLabel[] landBottomLine = new JLabel[8];
	static JLabel[] landLeftLine = new JLabel[7];
	static JLabel[] landRightLine = new JLabel[7];

	JLabel[] cityNameText = new JLabel[30];
	JLabel[] villaImage = new JLabel[30];
	JLabel[] buildingImage = new JLabel[30];
	JLabel[] hotelImage = new JLabel[30];
	JLabel[] landmarkImage = new JLabel[30];

	Land[] land = new Land[30];
	JLabel landLabel = new JLabel();

	// 건물 선택 버튼 배열 초기화
	JButton[] landBulidingButton = new JButton[4];

	int landTopLineHorizontalLength = 82;
	int landTopLineVerticalLength = 3;
	int landBottomLineHorizontalLength = 628;
	int landBottomLineVerticalLength = 482;
	int landLeftLineHorizontalLength = 2;
	int landLeftLineVerticalLength = 482;
	int landRightLineHorizontalLength = 708;
	int landRightLineVerticalLength = 3;
	int HorizontalLineInterval = 78;
	int VerticalLineInterval = 80;
	int lineWidth = 77;
	int lineHeight = 79;

	// 벌금내기
	JLabel fineText = new JLabel();
	JLabel amountFineText = new JLabel();
	JButton payCostButton = new JButton();
	JButton useFreePassButton = new JButton(); // 우대권 사용 버튼
	int amountOfFine = 0;
	String caughtPerson = ""; // 누가 땅에 걸렸는지
	int landNumOnArrival = 0;// 걸린 땅의 번호

	int buildingImageHorizontalLength = 0;
	int buildingImageVerticalLength = 50;
	int buildingImageWidth = 26;
	int buildingImageHeight = 30;

	// 인수하기
	JLabel acquisitionText = new JLabel();
	JLabel acquisitionPriceText = new JLabel();
	JButton acquisitionYesButton = new JButton();
	JButton acquisitionNoButton = new JButton();
	int acquisitionPrice = 0; // 인수가격

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
	String player1FlyingStatus = "";
	String player2FlyingStatus = "";
	String whoRideAirplane = "";

	// 행운 카드
	JLabel cardNameText = new JLabel();
	JLabel cardContentText = new JLabel();
	JButton useButton = new JButton();

	boolean isPlayer1hasCard = false;
	boolean isPlayer2hasCard = false;
	boolean isPlayer1hasuninhabitedCard = false;
	boolean isPlayer2hasuninhabitedCard = false;
	boolean isPlayer1hasFreePass = false;
	boolean isPlayer2hasFreePass = false;

	// 엔딩
	JLabel gameEndingText = new JLabel();
	JLabel winnerText = new JLabel();

	// 그 외
	JLabel playSituation = new JLabel();// 플레이 상황을 보여주는 text
	JLabel[] uninhabitedEmoticon = new JLabel[2]; // 무인도 탈출 카드 이미지
	JLabel[] uninhabitedEmoticonExplanation = new JLabel[2]; // 무인도 탈출 카드 설명
	JLabel[] FreePassEmoticon = new JLabel[2]; // 우대권 카드 이미지
	JLabel[] FreePassExplanation = new JLabel[2]; // 우대권 카드 설명

	int socialWelfareCost = 10000; // 사회복지기금 비용
	int collectedSocialWelfare = 0; // 사회복지기금에 모인 돈
	int luckeyCardNum = 0; // 행운카드 번호
	int salary = 10000;
	int lotteryWinningAmount = 100000;
	int lostMoney = 50000;

	// 플레이어무빙 (플레이어, 플레이어 이미지, 플레이상황, 플레이어 돈 텍스트)
	PlayerMoving Player1Moving = new PlayerMoving(player1, player1Image, playSituation, player1moneyText);
	PlayerMoving Player2Moving = new PlayerMoving(player2, player2Image, playSituation, player2moneyText);

	// 게임엔딩 (플레이어1,2 게임엔딩씬, 부루마블씬, 게임엔딩텍스트, 승리텍스트)
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
		blueMarbleScene.add(luckeyCardScene);
		luckeyCardScene.setLayout(null);
		luckeyCardScene.setVisible(false);

		rideAirplaneScene.setBounds(180, 100, 400, 300);
		rideAirplaneScene.setBackground(Color.DARK_GRAY);
		blueMarbleScene.add(rideAirplaneScene);
		rideAirplaneScene.setLayout(null);
		rideAirplaneScene.setVisible(false);

		blueMarbleScene.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(blueMarbleScene);
		blueMarbleScene.setLayout(null);

		// 상대방 지역에 걸렸을 때 벌금 내기
		fineScene.setLayout(null);
		fineScene.setBackground(Color.DARK_GRAY);
		fineScene.setBounds(200, 100, 340, 300);
		blueMarbleScene.add(fineScene);
		fineScene.setVisible(false);

		// 인수하기
		acquisitionScene.setLayout(null);
		acquisitionScene.setBackground(Color.DARK_GRAY);
		acquisitionScene.setBounds(200, 100, 340, 300);
		blueMarbleScene.add(acquisitionScene);
		acquisitionScene.setVisible(false);

		// 땅
		landPanel.setLayout(null);
		landPanel.setBounds(200, 100, 500, 300);
		blueMarbleScene.add(landPanel);
		landPanel.setVisible(false);

		blueMarbleScene.add(landLabel = new JLabel());
		landLabel.setBounds(200, 100, 450, 300);
		landLabel.setIcon(new ImageIcon("./images/land.png"));
		landLabel.setVisible(false);

		landName.setLayout(null);
		landName.setText("");
		landName.setFont(new Font("굴림", Font.BOLD, 20));
		landName.setBounds(nameHorizontalLength, nameVerticalLength, nameLineWidth, nameLineHeight);

		// 플레이어
		player1Image.setIcon(new ImageIcon("./images/Player1.png"));
		player1Image.setBounds(715, 495, 60, 60);
		blueMarbleScene.add(player1Image);

		player2Image.setIcon(new ImageIcon("./images/Player2.png"));
		player2Image.setBounds(715, 495, 60, 60);
		blueMarbleScene.add(player2Image);

		player1moneyText.setText("money : 5000000");
		player1moneyText.setFont(new Font("굴림", Font.BOLD, 13));
		player1moneyText.setBounds(130, 190, 130, 60);
		blueMarbleScene.add(player1moneyText);

		player2moneyText.setText("money : 5000000");
		player2moneyText.setFont(new Font("굴림", Font.BOLD, 13));
		player2moneyText.setBounds(130, 390, 130, 60);
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

		player1informationBoard.setIcon(new ImageIcon("./images/Player1Board.png"));
		player1informationBoard.setBounds(83, 87, 250, 190);
		blueMarbleScene.add(player1informationBoard);

		player2informationBoard.setIcon(new ImageIcon("./images/Player2Board.png"));
		player2informationBoard.setBounds(83, 285, 250, 190);
		blueMarbleScene.add(player2informationBoard);

		playSituation.setText("");
		playSituation.setFont(new Font("굴림", Font.BOLD, 16));
		playSituation.setBounds(350, 110, 500, 80);
		blueMarbleScene.add(playSituation);

		whosTurnText.setText("Player 1 순서");
		whosTurnText.setFont(new Font("굴림", Font.BOLD, 13));
		whosTurnText.setBounds(350, 90, 220, 80);
		blueMarbleScene.add(whosTurnText);

		// 부루마블 판
		// 아래쪽 줄
		for (int i = 0; i < landBottomLine.length; i++) {

			blueMarbleScene.add(cityNameText[i] = new JLabel());
			blueMarbleScene.add(landBottomLine[i] = new JLabel());

			cityNameText[i].setBounds(landBottomLineHorizontalLength, landBottomLineVerticalLength, lineWidth,
					lineHeight);
			landBottomLine[i].setBounds(landBottomLineHorizontalLength, landBottomLineVerticalLength, lineWidth,
					lineHeight);

			landBottomLineHorizontalLength = landBottomLineHorizontalLength - HorizontalLineInterval;

			landBottomLine[i].setIcon(new ImageIcon("./images/Line.png"));
			cityNameText[i].setHorizontalAlignment(SwingConstants.CENTER);
		}

		// 왼쪽 줄
		for (int i = 0; i < landLeftLine.length; i++) {

			blueMarbleScene.add(cityNameText[i + 8] = new JLabel());
			blueMarbleScene.add(landLeftLine[i] = new JLabel());

			cityNameText[i + 8].setBounds(landLeftLineHorizontalLength, landLeftLineVerticalLength, lineWidth,
					lineHeight);
			landLeftLine[i].setBounds(landLeftLineHorizontalLength, landLeftLineVerticalLength, lineWidth, lineHeight);

			landLeftLineVerticalLength = landLeftLineVerticalLength - VerticalLineInterval;

			landLeftLine[i].setIcon(new ImageIcon("./images/Line.png"));
			cityNameText[i + 8].setHorizontalAlignment(SwingConstants.CENTER);

		}

		// 위쪽 줄
		for (int i = 0; i < landTopLine.length; i++) {

			blueMarbleScene.add(cityNameText[i + 15] = new JLabel());
			blueMarbleScene.add(landTopLine[i] = new JLabel());

			cityNameText[i + 15].setBounds(landTopLineHorizontalLength, landTopLineVerticalLength, lineWidth,
					lineHeight);
			landTopLine[i].setBounds(landTopLineHorizontalLength, landTopLineVerticalLength, lineWidth, lineHeight);

			landTopLineHorizontalLength = landTopLineHorizontalLength + HorizontalLineInterval;

			landTopLine[i].setIcon(new ImageIcon("./images/Line.png"));
			cityNameText[i + 15].setHorizontalAlignment(SwingConstants.CENTER);

		}

		// 오른쪽 줄
		for (int i = 0; i < landRightLine.length; i++) {

			blueMarbleScene.add(cityNameText[i + 23] = new JLabel());
			blueMarbleScene.add(landRightLine[i] = new JLabel());

			cityNameText[i + 23].setBounds(landRightLineHorizontalLength, landRightLineVerticalLength, lineWidth,
					lineHeight);
			landRightLine[i].setBounds(landRightLineHorizontalLength, landRightLineVerticalLength, lineWidth,
					lineHeight);

			landRightLineVerticalLength = landRightLineVerticalLength + VerticalLineInterval;

			landRightLine[i].setIcon(new ImageIcon("./images/Line.png"));
			cityNameText[i + 23].setHorizontalAlignment(SwingConstants.CENTER);

		}

		// 땅 객체 배열 생성(위치 지역명 , 빌라 값, 빌딩 값, 호텔 값, 랜드마크 값, 빌라 선택 횟수, 빌딩 선택 횟수, 호텔 선택 횟수,
		// 랜드마크 선택 횟수, 빌라 갯수, 빌딩 갯수, 호텔 갯수, 랜드마크 갯수, 건설비용, 땅 주인)
		land[0] = new Land("출발지", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");

		land[1] = new Land("서귀포", 10000, 30000, 50000, 50000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[2] = new Land("제주", 10000, 30000, 50000, 50000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[3] = new Land("독도", 10000, 30000, 50000, 50000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[4] = new Land("행운카드", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[5] = new Land("경주", 20000, 60000, 100000, 100000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[6] = new Land("안동", 20000, 60000, 100000, 100000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[7] = new Land("통영", 20000, 60000, 100000, 100000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[8] = new Land("창원", 20000, 60000, 100000, 100000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");

		land[9] = new Land("공항", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[10] = new Land("강릉", 30000, 90000, 150000, 150000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[11] = new Land("원주", 30000, 90000, 150000, 150000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[12] = new Land("춘천", 30000, 90000, 150000, 150000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[13] = new Land("청주", 40000, 120000, 200000, 200000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[14] = new Land("세종", 40000, 120000, 200000, 200000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");

		land[15] = new Land("무인도", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[16] = new Land("목포", 50000, 150000, 250000, 250000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[17] = new Land("여수", 50000, 150000, 250000, 250000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[18] = new Land("군산", 50000, 150000, 250000, 250000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[19] = new Land("전주", 50000, 150000, 250000, 250000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[20] = new Land("행운카드", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[21] = new Land("포항", 60000, 180000, 300000, 300000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[22] = new Land("울산", 60000, 180000, 300000, 300000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[23] = new Land("대구", 60000, 180000, 300000, 300000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");

		land[24] = new Land("복지기금", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[25] = new Land("인천", 70000, 210000, 350000, 350000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[26] = new Land("광주", 70000, 210000, 350000, 350000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[27] = new Land("기부센터", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[28] = new Land("부산", 80000, 240000, 400000, 400000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		land[29] = new Land("서울", 80000, 240000, 400000, 400000, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
		// land[30] = new Land("출발지", 0, 0, 0, 0, 0, 0, 0);

		// 칸에 도시 이름을 넣어줌
		for (int i = 0; i < cityNameText.length - 1; i++) {
			cityNameText[i].setText(land[i + 1].landName.toString());
		}
		cityNameText[29].setText(land[0].landName.toString());

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
					if (player1FlyingStatus == "비행기 타기") {
						// System.out.println("비행기를 탐");

					} else if (Player1forcedRest == 0) {
						player1leftdayOfisland.setVisible(false);

						player1.previousLocation = player1.location;
						player1.location = player1.location + diceNum;

						System.out.println("player1" + diceNum);
						synchronized (Player1Moving) {
							Player1Moving.notify();
						}

						// 무인도에 갇혔을 때 탈출할 때까지 쉬어야한다
					} else if (Player1forcedRest > 0) {
						Player1forcedRest--;
						player1leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : " + (Player1forcedRest + 1) + " 일");
					}

					if (player2FlyingStatus == "비행기 타기") {
						airport();
					}
					player1FlyingStatus = "";

					// 플레이어을 움직인 후 차례를 바꾼다
					whosTurn = 2;

					// player2의 차례일 때
				} else if (whosTurn == 2) {

					if (player2FlyingStatus == "비행기 타기") {
						// System.out.println("비행기를 탐");

					} else if (Player2forcedRest == 0) {
						player2leftdayOfisland.setVisible(false);

						player2.previousLocation = player2.location;
						player2.location = player2.location + diceNum;
						System.out.println("player2" + diceNum);
						synchronized (Player2Moving) {
							Player2Moving.notify();

						}

						// 무인도에 갇혔을 때 탈출할 때까지 쉬어야한다
					} else if (Player2forcedRest > 0) {
						Player2forcedRest--;
						player2leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : " + (Player2forcedRest + 1) + " 일");
					}

					if (player1FlyingStatus == "비행기 타기") {
						airport();
					}
					player2FlyingStatus = "";

					// 플레이어을 움직인 후 차례를 바꾼다
					whosTurn = 1;
				}
			}
		});
		blueMarbleScene.add(diceThrowButton);

		diceNumberText.setText("주사위를 던지시오");
		diceNumberText.setFont(new Font("굴림", Font.BOLD, 13));
		diceNumberText.setBounds(510, 360, 110, 60);
		blueMarbleScene.add(diceNumberText);

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
						player1FlyingStatus = "비행기 타기(카드)";
						airport();
						playSituation.setText("원하는 도시를 클릭 후 주사위 버튼을 눌러주세요");
						isPlayer1hasCard = false;

					} else if (isPlayer2hasCard == true) {
						player2FlyingStatus = "비행기 타기(카드)";
						airport();
						playSituation.setText("원하는 도시를 클릭 후 주사위 버튼을 눌러주세요");
						isPlayer2hasCard = false;
					}

					// 사회복지기금배당
				} else if (luckeyCardNum == 1) {

					if (isPlayer1hasCard == true) {
						// 사회복지기금 수령처로 이동한다.
						player1.location = 24;
						player1Image.setLocation(landRightLine[0].getX() + 10, landRightLine[0].getY() + 10);
						// 사회복지기금에 모인 돈을 받는다.
						player1.money = player1.money + collectedSocialWelfare;
						player1moneyText.setText("money : " + player1.money);
						isPlayer1hasCard = false;

					} else if (isPlayer2hasCard == true) {
						// 사회복지기금 수령처로 이동한다.
						player2.location = 24;
						player2Image.setLocation(landRightLine[0].getX() + 10, landRightLine[0].getY() + 10);
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
						player1Image.setLocation(landRightLine[6].getX() + 10, landRightLine[6].getY() + 10);
						// 출발점을 지나 월급을 받는다
						player1.money = player1.money + salary;
						player1moneyText.setText("money : " + player1.money);
						isPlayer1hasCard = false;

					} else if (isPlayer2hasCard == true) {
						// 출발점으로 이동한다.
						player2.location = 0;
						player2Image.setLocation(landRightLine[6].getX() + 10, landRightLine[6].getY() + 10);
						// 출발점을 지나 월급을 받는다
						player2.money = player2.money + salary;
						player2moneyText.setText("money : " + player2.money);
						isPlayer2hasCard = false;
					}
					playSituation.setText("출발점을 지나 월급" + salary + "원을 얻었습니다");

					// 무인도로 가시오
				} else if (luckeyCardNum == 3) {

					if (isPlayer1hasCard == true) {
						// 무인도로 이동한다.
						player1.location = 15;
						player1Image.setLocation(landLeftLine[6].getX() + 10, landLeftLine[6].getY() + 10);
						player1leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : 3 일");
						player1leftdayOfisland.setVisible(true);
						Player1forcedRest = 3;

						// 무인도 탈출권이 있을 경우 아래 코드가 실행된다.
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
						player2Image.setLocation(landLeftLine[6].getX() + 10, landLeftLine[6].getY() + 10);
						player2leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : 3 일");
						player2leftdayOfisland.setVisible(true);
						Player2forcedRest = 3;

						// 무인도 탈출권이 있을 경우 아래 코드가 실행된다.
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
						player1.money = player1.money + lotteryWinningAmount;
						player1moneyText.setText("money : " + player1.money);
						isPlayer1hasCard = false;

					} else if (isPlayer2hasCard == true) {
						player2.money = player2.money + lotteryWinningAmount;
						player2moneyText.setText("money : " + player2.money);
						isPlayer2hasCard = false;
					}

					// 지갑도난
				} else if (luckeyCardNum == 5) {

					if (isPlayer1hasCard == true) {
						player1.money = player1.money - lostMoney;
						player1moneyText.setText("money : " + player1.money);
						isPlayer1hasCard = false;

					} else if (isPlayer2hasCard == true) {
						player2.money = player2.money - lostMoney;
						player2moneyText.setText("money : " + player2.money);
						isPlayer2hasCard = false;
					}

					// 무인도 탈출
				} else if (luckeyCardNum == 6) {

					if (isPlayer1hasCard == true) {
						uninhabitedEmoticon[0].setVisible(true);
						isPlayer1hasuninhabitedCard = true;
						isPlayer1hasCard = false;

					} else if (isPlayer2hasCard == true) {
						uninhabitedEmoticon[1].setVisible(true);
						isPlayer2hasuninhabitedCard = true;
						isPlayer2hasCard = false;
					}

					// 우대권
				} else if (luckeyCardNum == 7) {

					if (isPlayer1hasCard == true) {
						FreePassEmoticon[0].setVisible(true);
						isPlayer1hasFreePass = true;
						isPlayer1hasCard = false;

					} else if (isPlayer2hasCard == true) {
						FreePassEmoticon[1].setVisible(true);
						isPlayer2hasFreePass = true;
						isPlayer2hasCard = false;

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
						player1FlyingStatus = "비행기 타기";
					} else {
						JOptionPane.showMessageDialog(frame, "돈이 부족하여 탈 수 없어요", "SYSTEM",
								JOptionPane.INFORMATION_MESSAGE);
						rideAirplaneScene.setVisible(false);
						diceThrowButton.setVisible(true);
					}

				} else if (whoRideAirplane.equals("player2")) {
					if (player2.money > airplaneFee) {
						player2.money = player2.money - airplaneFee;
						rideAirplaneScene.setVisible(false);
						diceThrowButton.setVisible(true);
						player2moneyText.setText("money : " + player2.money);
						player2FlyingStatus = "비행기 타기";

					} else {
						JOptionPane.showMessageDialog(frame, "돈이 부족하여 탈 수 없어요", "SYSTEM",
								JOptionPane.INFORMATION_MESSAGE);
						rideAirplaneScene.setVisible(false);
						diceThrowButton.setVisible(true);
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

		// 무인도 탈출 카드
		for (int i = 0; i < uninhabitedEmoticon.length; i++) {
			blueMarbleScene.add(uninhabitedEmoticon[i] = new JLabel());
			blueMarbleScene.add(uninhabitedEmoticonExplanation[i] = new JLabel());

			uninhabitedEmoticon[i].setIcon(new ImageIcon("./images/uninhabitedCardEmoticon.png"));
			uninhabitedEmoticonExplanation[i].setText("무인도를 탈출 할 수 있는 카드");

			uninhabitedEmoticon[i].setVisible(false);
			uninhabitedEmoticonExplanation[i].setVisible(false);

			// 마우스를 무인도 이미지에 갖다댔을때 무인도 탈출 카드에 대한 정보가 뜬다.
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

		// 우대권
		for (int i = 0; i < FreePassEmoticon.length; i++) {
			blueMarbleScene.add(FreePassEmoticon[i] = new JLabel());
			blueMarbleScene.add(FreePassExplanation[i] = new JLabel());

			FreePassEmoticon[i].setIcon(new ImageIcon("./images/preferentialrightEmoticon.png"));
			FreePassExplanation[i].setText("상대방 땅 걸렸을 때 벌금 제거");

			FreePassEmoticon[i].setVisible(false);
			FreePassExplanation[i].setVisible(false);

			// 마우스를 우대권 이미지에 갖다댔을때 우대권 카드에 대한 정보가 뜬다.
			FreePassEmoticon[i].addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					for (int i = 0; i < FreePassEmoticon.length; i++) {
						if (e.getSource() == FreePassEmoticon[i]) {
							FreePassExplanation[i].setVisible(true);
						}
					}
				}

				public void mouseExited(MouseEvent e) {
					for (int i = 0; i < FreePassEmoticon.length; i++) {
						if (e.getSource() == FreePassEmoticon[i]) {
							FreePassExplanation[i].setVisible(false);
						}
					}
				}
			});
		}
		FreePassEmoticon[0].setBounds(260, 150, 50, 50);
		FreePassEmoticon[1].setBounds(260, 350, 50, 50);
		FreePassExplanation[0].setBounds(310, 150, 250, 50);
		FreePassExplanation[1].setBounds(310, 350, 250, 50);

		// 빌라, 빌딩, 호텔, 랜드마크 이미지 초기화
		for (int i = 0; i < 30; i++) {
			blueMarbleScene.add(villaImage[i] = new JLabel());
			blueMarbleScene.add(buildingImage[i] = new JLabel());
			blueMarbleScene.add(hotelImage[i] = new JLabel());
			blueMarbleScene.add(landmarkImage[i] = new JLabel());

			villaImage[i].setIcon(new ImageIcon("./images/villaImage.png"));
			buildingImage[i].setIcon(new ImageIcon("./images/buildingImage.png"));
			hotelImage[i].setIcon(new ImageIcon("./images/hotelImage.png"));
			landmarkImage[i].setIcon(new ImageIcon("./images/landmarkImage.png"));
		}

		// 총 건설 비용
		constructionCostText.setLayout(null);
		constructionCostText.setBounds(10, 200, nameLineWidth, nameLineHeight);
		landLabel.add(constructionCostText);

		landLabel.add(landName);

		landBulidingButton[0] = new JButton((new ImageIcon("./images/villa.png")));
		landBulidingButton[1] = new JButton((new ImageIcon("./images/building.png")));
		landBulidingButton[2] = new JButton((new ImageIcon("./images/hotel.png")));
		landBulidingButton[3] = new JButton((new ImageIcon("./images/landmark.png")));

		landBulidingButton[3].setEnabled(false);

		constructionPrice[0] = new JLabel("" + land[player1.location].villaPrice);
		constructionPrice[1] = new JLabel("" + land[player1.location].buildingPrice);
		constructionPrice[2] = new JLabel("" + land[player1.location].hotelPrice);
		constructionPrice[3] = new JLabel("" + land[player1.location].landmarkPrice);

		landBulidingButton[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (whosTurn == 1) {
					// 플레이어가 0바퀴 이상 돌고, 빌라를 구입하지 않았을 경우 빌라버튼을 선택할 수 있다.
					if (player2.round >= 0) {
						if (land[player2.location].amountVilla != 1) {

							if (land[player2.location].villaCheckCount == 0) {
								land[player2.location].villaCheckCount++;

								landBulidingButton[0].setIcon(new ImageIcon("./images/villaCheck.png"));

								land[player2.location].constructionCost += land[player2.location].villaPrice;
								constructionCostText.setText("건설비용: " + land[player2.location].constructionCost);

							} else {
								land[player2.location].villaCheckCount--;
								landBulidingButton[0].setIcon(new ImageIcon("./images/villa.png"));

								land[player2.location].constructionCost -= land[player2.location].villaPrice;
								constructionCostText.setText("건설비용: " + land[player2.location].constructionCost);
							}
						}
					}
				} else if (whosTurn == 2) {
					// 플레이어가 0바퀴 이상 돌고, 빌라를 구입하지 않았을 경우 빌라버튼을 선택할 수 있다.
					if (player1.round >= 0) {
						if (land[player1.location].amountVilla != 1) {
							System.out.println("빌라 선택함");
							if (land[player1.location].villaCheckCount == 0) {
								land[player1.location].villaCheckCount++;

								landBulidingButton[0].setIcon(new ImageIcon("./images/villaCheck.png"));

								land[player1.location].constructionCost += land[player1.location].villaPrice;
								constructionCostText.setText("건설비용: " + land[player1.location].constructionCost);

							} else {
								land[player1.location].villaCheckCount--;

								landBulidingButton[0].setIcon(new ImageIcon("./images/villa.png"));

								land[player1.location].constructionCost -= land[player1.location].villaPrice;
								constructionCostText.setText("건설비용: " + land[player1.location].constructionCost);
							}
						}
					}

				}
			}
		});

		landBulidingButton[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (whosTurn == 1) {
					// 플레이어가 1바퀴 이상 돌고, 빌딩을 구입하지 않았을 경우 빌딩버튼을 선택할 수 있다.
					if (player2.round >= 1) {
						if (land[player2.location].amountBuilding != 1) {
							System.out.println("빌딩 선택함");
							if (land[player2.location].buildingCheckCount == 0) {
								land[player2.location].buildingCheckCount++;

								landBulidingButton[1].setIcon(new ImageIcon("./images/buildingCheck.png"));

								land[player2.location].constructionCost += land[player2.location].buildingPrice;
								constructionCostText.setText("건설비용: " + land[player2.location].constructionCost);

							} else {
								land[player2.location].buildingCheckCount--;

								landBulidingButton[1].setIcon(new ImageIcon("./images/building.png"));

								land[player2.location].constructionCost -= land[player2.location].buildingPrice;
								constructionCostText.setText("건설비용: " + land[player2.location].constructionCost);
							}
						}
					} else {
						JOptionPane.showMessageDialog(frame, "1바퀴 이상 돌아야 구매할 수 있습니다.", "SYSTEM",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else if (whosTurn == 2) {
					// 플레이어가 1바퀴 이상 돌고, 빌딩을 구입하지 않았을 경우 빌딩버튼을 선택할 수 있다.
					if (player1.round >= 1) {
						if (land[player1.location].amountBuilding != 1) {

							if (land[player1.location].buildingCheckCount == 0) {
								land[player1.location].buildingCheckCount++;

								landBulidingButton[1].setIcon(new ImageIcon("./images/buildingCheck.png"));

								land[player1.location].constructionCost += land[player1.location].buildingPrice;
								constructionCostText.setText("건설비용: " + land[player1.location].constructionCost);

							} else {
								land[player1.location].buildingCheckCount--;

								landBulidingButton[1].setIcon(new ImageIcon("./images/building.png"));

								land[player1.location].constructionCost -= land[player1.location].buildingPrice;
								constructionCostText.setText("건설비용: " + land[player1.location].constructionCost);
							}
						}
					} else {
						JOptionPane.showMessageDialog(frame, "1바퀴 이상 돌아야 구매할 수 있습니다.", "SYSTEM",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}
			}
		});

		landBulidingButton[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (whosTurn == 1) {
					// 플레이어가 2바퀴 이상 돌고, 호텔을 구입하지 않았을 경우 호텔버튼을 선택할 수 있다.
					if (player2.round >= 2) {
						if (land[player2.location].amountHotel != 1) {
							System.out.println("호텔 선택함");
							if (land[player2.location].hotelCheckCount == 0) {
								land[player2.location].hotelCheckCount++;

								landBulidingButton[2].setIcon(new ImageIcon("./images/hotelCheck.png"));

								land[player2.location].constructionCost += land[player2.location].hotelPrice;
								constructionCostText.setText("건설비용: " + land[player2.location].constructionCost);

							} else {
								land[player2.location].hotelCheckCount--;

								landBulidingButton[2].setIcon(new ImageIcon("./images/hotel.png"));

								land[player2.location].constructionCost -= land[player2.location].hotelPrice;
								constructionCostText.setText("건설비용: " + land[player2.location].constructionCost);
							}
						}
					} else {
						JOptionPane.showMessageDialog(frame, "2바퀴 이상 돌아야 구매할 수 있습니다.", "SYSTEM",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else if (whosTurn == 2) {
					// 플레이어가 2바퀴 이상 돌고, 호텔을 구입하지 않았을 경우 호텔버튼을 선택할 수 있다.
					if (player1.round >= 2) {
						if (land[player1.location].amountHotel != 1) {
							System.out.println("호텔 선택함");
							if (land[player1.location].hotelCheckCount == 0) {
								land[player1.location].hotelCheckCount++;

								landBulidingButton[2].setIcon(new ImageIcon("./images/hotelCheck.png"));

								land[player1.location].constructionCost += land[player1.location].hotelPrice;
								constructionCostText.setText("건설비용: " + land[player1.location].constructionCost);

							} else {
								land[player1.location].hotelCheckCount--;

								landBulidingButton[2].setIcon(new ImageIcon("./images/hotel.png"));

								land[player1.location].constructionCost -= land[player1.location].hotelPrice;
								constructionCostText.setText("건설비용: " + land[player1.location].constructionCost);
							}
						}
					} else {
						JOptionPane.showMessageDialog(frame, "2바퀴 이상 돌아야 구매할 수 있습니다.", "SYSTEM",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}
			}

		});

		landBulidingButton[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (whosTurn == 1) {
					// 플레이어가 빌라,빌딩,호텔을 사야지 구매할 수 있다.
					if (land[player2.location].amountLandmark != 1) {

						if (land[player2.location].landmarkCheckCount == 0) {
							land[player2.location].landmarkCheckCount++;

							landBulidingButton[3].setIcon(new ImageIcon("./images/landmarkCheck.png"));

							land[player2.location].constructionCost += land[player2.location].landmarkPrice;
							constructionCostText.setText("건설비용: " + land[player2.location].constructionCost);

						} else {
							land[player2.location].landmarkCheckCount--;

							landBulidingButton[3].setIcon(new ImageIcon("./images/landmark.png"));

							land[player2.location].constructionCost -= land[player2.location].landmarkPrice;
							constructionCostText.setText("건설비용: " + land[player2.location].constructionCost);
						}

					} else {
						JOptionPane.showMessageDialog(frame, "빌라,빌딩,호텔을 사야지 구매가 가능합니다.", "SYSTEM",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else if (whosTurn == 2) {
					// 플레이어가 빌라,빌딩,호텔을 사야지 구매할 수 있다.
					if (land[player1.location].amountLandmark != 1) {
						if (land[player1.location].landmarkCheckCount == 0) {
							land[player1.location].landmarkCheckCount++;

							landBulidingButton[3].setIcon(new ImageIcon("./images/landmarkCheck.png"));

							land[player1.location].constructionCost += land[player1.location].landmarkPrice;
							constructionCostText.setText("건설비용: " + land[player1.location].constructionCost);

						} else {
							land[player1.location].landmarkCheckCount--;

							landBulidingButton[3].setIcon(new ImageIcon("./images/landmark.png"));

							land[player1.location].constructionCost -= land[player1.location].landmarkPrice;
							constructionCostText.setText("건설비용: " + land[player1.location].constructionCost);
						}

					} else {
						JOptionPane.showMessageDialog(frame, "빌라,빌딩,호텔을 사야지 구매가 가능합니다.", "SYSTEM",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		JButton CloseButton = new JButton((new ImageIcon("./images/closeButton.png")));
		CloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				landLabel.setVisible(false);
				diceThrowButton.setVisible(true);

				// 닫기버튼을 누르면 구입창에서 선택했던 것들을 초기화 시킨다.
				if (whosTurn == 1) {
					if (land[player2.location].amountVilla != 1) {
						land[player2.location].villaCheckCount -= land[player2.location].villaCheckCount;
					}
					if (land[player2.location].amountBuilding != 1) {
						land[player2.location].buildingCheckCount -= land[player2.location].buildingCheckCount;
					}
					if (land[player2.location].amountHotel != 1) {
						land[player2.location].hotelCheckCount -= land[player2.location].hotelCheckCount;
					}
					if (land[player2.location].amountLandmark != 1) {
						land[player2.location].landmarkCheckCount -= land[player2.location].landmarkCheckCount;
					}
					land[player2.location].constructionCost -= land[player2.location].constructionCost;
					constructionCostText.setText("건설비용: " + land[player2.location].constructionCost);

					// 취소할때, 버튼 이미지를 다시 원래대로 수정한다.
					if (land[player2.location].amountVilla != 1) {
						landBulidingButton[0].setIcon(new ImageIcon("./images/villa.png"));
					} else {
						landBulidingButton[0].setIcon(new ImageIcon("./images/villaCheck.png"));
					}

					if (land[player2.location].amountBuilding != 1) {
						landBulidingButton[1].setIcon(new ImageIcon("./images/building.png"));
					} else {
						landBulidingButton[1].setIcon(new ImageIcon("./images/buildingCheck.png"));
					}

					if (land[player2.location].amountHotel != 1) {
						landBulidingButton[2].setIcon(new ImageIcon("./images/hotel.png"));
					} else {
						landBulidingButton[2].setIcon(new ImageIcon("./images/hotelCheck.png"));
					}

					if (land[player2.location].amountLandmark != 1) {
						landBulidingButton[3].setIcon(new ImageIcon("./images/landmark.png"));
					} else {
						landBulidingButton[3].setIcon(new ImageIcon("./images/landmarkCheck.png"));
					}

				} else if (whosTurn == 2) {
					if (land[player1.location].amountVilla != 1) {
						land[player1.location].villaCheckCount -= land[player1.location].villaCheckCount;
					}
					if (land[player1.location].amountBuilding != 1) {
						land[player1.location].buildingCheckCount -= land[player1.location].buildingCheckCount;
					}
					if (land[player1.location].amountHotel != 1) {
						land[player1.location].hotelCheckCount -= land[player1.location].hotelCheckCount;
					}
					if (land[player1.location].amountLandmark != 1) {
						land[player1.location].landmarkCheckCount -= land[player1.location].landmarkCheckCount;
					}
					land[player1.location].constructionCost -= land[player1.location].constructionCost;
					constructionCostText.setText("건설비용: " + land[player1.location].constructionCost);

					// 취소할때, 버튼 이미지를 다시 원래대로 수정한다.
					if (land[player1.location].amountVilla != 1) {
						landBulidingButton[0].setIcon(new ImageIcon("./images/villa.png"));
					} else {
						landBulidingButton[0].setIcon(new ImageIcon("./images/villaCheck.png"));
					}

					if (land[player1.location].amountBuilding != 1) {
						landBulidingButton[1].setIcon(new ImageIcon("./images/building.png"));
					} else {
						landBulidingButton[1].setIcon(new ImageIcon("./images/buildingCheck.png"));
					}

					if (land[player1.location].amountHotel != 1) {
						landBulidingButton[2].setIcon(new ImageIcon("./images/hotel.png"));
					} else {
						landBulidingButton[2].setIcon(new ImageIcon("./images/hotelCheck.png"));
					}

					if (land[player1.location].amountLandmark != 1) {
						landBulidingButton[3].setIcon(new ImageIcon("./images/landmark.png"));
					} else {
						landBulidingButton[3].setIcon(new ImageIcon("./images/landmarkCheck.png"));
					}
				}

			}
		});
		CloseButton.setBounds(400, 0, 50, 50);

		landLabel.add(CloseButton);

		CloseButton.setVisible(true);
		CloseButton.setBorderPainted(false);
		CloseButton.setFocusPainted(false);
		CloseButton.setContentAreaFilled(false);

		// 건물 버튼(빌라,빌딩,호텔,랜드마크)들의 위치 구성
		for (int k = 0; k < landBulidingButton.length; k++) {
			landLabel.add(landBulidingButton[k]);
			landBulidingButton[k].setBounds(landButtonHorizontalLength, landButtonVerticalLength, landButtonWidth,
					landButtonHeight);
			landButtonHorizontalLength = landButtonHorizontalLength + landButtonWidth + 10;
		}

		JButton buyButton = new JButton("구매하기");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (whosTurn == 2) {

					if (land[player1.location].constructionCost > 0) {

						if (player1.money >= land[player1.location].constructionCost) {
							landLabel.setVisible(false);
							diceThrowButton.setVisible(true);

							land[player1.location].landowner = "player1";
							if (land[player1.location].villaCheckCount == 1) {
								land[player1.location].amountVilla = 1;
							}
							if (land[player1.location].buildingCheckCount == 1) {
								land[player1.location].amountBuilding = 1;
							}
							if (land[player1.location].hotelCheckCount == 1) {
								land[player1.location].amountHotel = 1;
							}
							if (land[player1.location].landmarkCheckCount == 1) {
								land[player1.location].amountLandmark = 1;
							}

							// 빌라,빌딩,호텔이 모두 구입되면 랜드마크 구입창이 활성화된다.
							if (land[player1.location].amountVilla == 1 && land[player1.location].amountBuilding == 1
									&& land[player1.location].amountHotel == 1) {
								landBulidingButton[3].setEnabled(true);
							} else {
								landBulidingButton[3].setEnabled(false);
							}

							// 플레이어1이 구입한 땅에 플레이어1의 색으로 덮힌다.
							if (player1.location >= 1 && player1.location <= 8) {
								landBottomLine[player1.location - 1].setIcon(new ImageIcon("./images/BlueLand.png"));
								landBottomLine[player1.location - 1].add(landmarkImage[player1.location]);
								landBottomLine[player1.location - 1].add(villaImage[player1.location]);
								landBottomLine[player1.location - 1].add(buildingImage[player1.location]);
								landBottomLine[player1.location - 1].add(hotelImage[player1.location]);

							} else if (player1.location >= 9 && player1.location <= 15) {
								landLeftLine[player1.location - 9].setIcon(new ImageIcon("./images/BlueLand.png"));
								landLeftLine[player1.location - 9].add(landmarkImage[player1.location]);
								landLeftLine[player1.location - 9].add(villaImage[player1.location]);
								landLeftLine[player1.location - 9].add(buildingImage[player1.location]);
								landLeftLine[player1.location - 9].add(hotelImage[player1.location]);

							} else if (player1.location >= 16 && player1.location <= 23) {
								landTopLine[player1.location - 16].setIcon(new ImageIcon("./images/BlueLand.png"));
								landTopLine[player1.location - 16].add(landmarkImage[player1.location]);
								landTopLine[player1.location - 16].add(villaImage[player1.location]);
								landTopLine[player1.location - 16].add(buildingImage[player1.location]);
								landTopLine[player1.location - 16].add(hotelImage[player1.location]);

							} else if (player1.location >= 24 && player1.location <= 29) {
								landRightLine[player1.location - 24].setIcon(new ImageIcon("./images/BlueLand.png"));
								landRightLine[player1.location - 24].add(landmarkImage[player1.location]);
								landRightLine[player1.location - 24].add(villaImage[player1.location]);
								landRightLine[player1.location - 24].add(buildingImage[player1.location]);
								landRightLine[player1.location - 24].add(hotelImage[player1.location]);
							}

							villaImage[player1.location].setBounds(0, 50, 26, 30);
							buildingImage[player1.location].setBounds(26, 50, 26, 30);
							hotelImage[player1.location].setBounds(52, 50, 26, 30);
							landmarkImage[player1.location].setBounds(0, 50, 79, 30);

							villaImage[player1.location].setVisible(false);
							buildingImage[player1.location].setVisible(false);
							hotelImage[player1.location].setVisible(false);
							landmarkImage[player1.location].setVisible(false);

							if (land[player1.location].amountLandmark != 1) {
								if (land[player1.location].amountVilla == 1) {
									villaImage[player1.location].setVisible(true);
								}
								if (land[player1.location].amountBuilding == 1) {
									buildingImage[player1.location].setVisible(true);
								}
								if (land[player1.location].amountHotel == 1) {
									hotelImage[player1.location].setVisible(true);
								}
								System.out.println("랜드마크 없음");

							} else {
								villaImage[player1.location].setVisible(false);
								buildingImage[player1.location].setVisible(false);
								hotelImage[player1.location].setVisible(false);
								landmarkImage[player1.location].setVisible(true);
								System.out.println(villaImage[player1.location].isVisible());
								System.out.println("랜드마크 있음");
							}

							player1.money = player1.money - land[player1.location].constructionCost;
							player1moneyText.setText("money : " + player1.money);
							land[player1.location].constructionCost = 0;

						} else {
							JOptionPane.showMessageDialog(frame, "돈이 부족하여 살 수 없어요.", "SYSTEM",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} else if (whosTurn == 1) {

					if (land[player2.location].constructionCost > 0) {
						if (player2.money >= land[player2.location].constructionCost) {
							landLabel.setVisible(false);
							diceThrowButton.setVisible(true);

							land[player2.location].landowner = "player2";
							if (land[player2.location].villaCheckCount == 1) {
								land[player2.location].amountVilla = 1;
							}
							if (land[player2.location].buildingCheckCount == 1) {
								land[player2.location].amountBuilding = 1;
							}
							if (land[player2.location].hotelCheckCount == 1) {
								land[player2.location].amountHotel = 1;
							}
							if (land[player2.location].landmarkCheckCount == 1) {
								land[player2.location].amountLandmark = 1;
							}

							// 빌라,빌딩,호텔이 모두 구입되면 랜드마크 구입창이 활성화된다.
							if (land[player2.location].amountVilla == 1 && land[player2.location].amountBuilding == 1
									&& land[player2.location].amountHotel == 1) {
								landBulidingButton[3].setEnabled(true);
							} else {
								landBulidingButton[3].setEnabled(false);
							}

							// 플레이어2가 구입한 땅에 플레이어2의 색으로 덮힌다.
							if (player2.location >= 1 && player2.location <= 8) {
								landBottomLine[player2.location - 1].setIcon(new ImageIcon("./images/RedLand.png"));
								landBottomLine[player2.location - 1].add(landmarkImage[player2.location]);
								landBottomLine[player2.location - 1].add(villaImage[player2.location]);
								landBottomLine[player2.location - 1].add(buildingImage[player2.location]);
								landBottomLine[player2.location - 1].add(hotelImage[player2.location]);

							} else if (player2.location >= 9 && player2.location <= 15) {
								landLeftLine[player2.location - 9].setIcon(new ImageIcon("./images/RedLand.png"));
								landLeftLine[player2.location - 9].add(landmarkImage[player2.location]);
								landLeftLine[player2.location - 9].add(villaImage[player2.location]);
								landLeftLine[player2.location - 9].add(buildingImage[player2.location]);
								landLeftLine[player2.location - 9].add(hotelImage[player2.location]);

							} else if (player2.location >= 16 && player2.location <= 23) {
								landTopLine[player2.location - 16].setIcon(new ImageIcon("./images/RedLand.png"));
								landTopLine[player2.location - 16].add(landmarkImage[player2.location]);
								landTopLine[player2.location - 16].add(villaImage[player2.location]);
								landTopLine[player2.location - 16].add(buildingImage[player2.location]);
								landTopLine[player2.location - 16].add(hotelImage[player2.location]);

							} else if (player2.location >= 24 && player2.location <= 29) {
								landRightLine[player2.location - 24].setIcon(new ImageIcon("./images/RedLand.png"));
								landRightLine[player2.location - 24].add(landmarkImage[player2.location]);
								landRightLine[player2.location - 24].add(villaImage[player2.location]);
								landRightLine[player2.location - 24].add(buildingImage[player2.location]);
								landRightLine[player2.location - 24].add(hotelImage[player2.location]);
							}
							villaImage[player2.location].setBounds(0, 50, 26, 30);
							buildingImage[player2.location].setBounds(26, 50, 26, 30);
							hotelImage[player2.location].setBounds(52, 50, 26, 30);
							landmarkImage[player2.location].setBounds(0, 50, 79, 30);

							villaImage[player2.location].setVisible(false);
							buildingImage[player2.location].setVisible(false);
							hotelImage[player2.location].setVisible(false);
							landmarkImage[player2.location].setVisible(false);

							if (land[player2.location].amountLandmark != 1) {
								if (land[player2.location].amountVilla == 1) {
									villaImage[player2.location].setVisible(true);
								}
								if (land[player2.location].amountBuilding == 1) {
									buildingImage[player2.location].setVisible(true);
								}
								if (land[player2.location].amountHotel == 1) {
									hotelImage[player2.location].setVisible(true);
								}
								System.out.println("랜드마크 없음");
							} else {
								villaImage[player2.location].setVisible(false);
								buildingImage[player2.location].setVisible(false);
								hotelImage[player2.location].setVisible(false);
								landmarkImage[player2.location].setVisible(true);
								System.out.println(villaImage[player2.location].isVisible());
								System.out.println("랜드마크 있음");
							}

							player2.money = player2.money - land[player2.location].constructionCost;
							player2moneyText.setText("money : " + player2.money);
							land[player2.location].constructionCost = 0;

						} else {
							JOptionPane.showMessageDialog(frame, "돈이 부족하여 살 수 없어요.", "SYSTEM",
									JOptionPane.INFORMATION_MESSAGE);
						}

					}
				}

			}
		});
		buyButton.setBounds(160, 250, 150, 20);
		landLabel.add(buyButton);
		buyButton.setVisible(true);

		// 벌금 내기
		fineText.setText("상대방의 땅에 걸려 벌금을 내야합니다");
		fineText.setForeground(Color.white);
		fineText.setFont(new Font("굴림", Font.BOLD, 17));
		fineText.setBounds(20, 10, 300, 60);
		fineScene.add(fineText);

		amountFineText.setText("벌금가격 : " + amountOfFine);
		amountFineText.setForeground(Color.white);
		amountFineText.setFont(new Font("굴림", Font.BOLD, 15));
		amountFineText.setBounds(0, 100, 340, 60);
		amountFineText.setHorizontalAlignment(SwingConstants.CENTER);
		fineScene.add(amountFineText);

		payCostButton.setText("지불하기");
		payCostButton.setBounds(25, 220, 100, 50);
		payCostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (caughtPerson == "player1") {
					if (player1.money > amountOfFine) {
						player1.money = player1.money - amountOfFine;
						player2.money = player2.money + amountOfFine;
						player1moneyText.setText("money : " + player1.money);
						player2moneyText.setText("money : " + player2.money);
					}

				} else if (caughtPerson == "player2") {
					if (player2.money > amountOfFine) {
						player2.money = player2.money - amountOfFine;
						player1.money = player1.money + amountOfFine;
						player1moneyText.setText("money : " + player1.money);
						player2moneyText.setText("money : " + player2.money);
					}

				}
				fineScene.setVisible(false);
				useFreePassButton.setEnabled(false);
				amountOfFine = 0;

				// 걸린 땅이 랜드마크가 아니라면 인수를 할 수 있는 창을 띄운다
				if (caughtPerson == "player1") {
					if (land[player1.location].amountLandmark != 1) {
						acquisitionScene.setVisible(true);
					} else {
						diceThrowButton.setVisible(true);
						acquisitionPrice = 0;
					}
				} else if (caughtPerson == "player2") {
					if (land[player2.location].amountLandmark != 1) {
						acquisitionScene.setVisible(true);
					} else {
						diceThrowButton.setVisible(true);
						acquisitionPrice = 0;
					}
				}
			}
		});
		fineScene.add(payCostButton);

		useFreePassButton.setText("우대권 사용");
		useFreePassButton.setBounds(225, 220, 100, 50);
		useFreePassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (caughtPerson == "player1") {
					if (isPlayer1hasFreePass == true) {
						FreePassEmoticon[0].setVisible(false);
						isPlayer1hasFreePass = false;
					}

				} else if (caughtPerson == "player2") {
					if (isPlayer2hasFreePass == true) {
						FreePassEmoticon[1].setVisible(false);
						isPlayer2hasFreePass = false;
					}
				}
				fineScene.setVisible(false);
				amountOfFine = 0;

				// 걸린 땅이 랜드마크가 아니라면 인수를 할 수 있는 창을 띄운다
				if (caughtPerson == "player1") {
					if (land[player1.location].amountLandmark != 1) {
						acquisitionScene.setVisible(true);
					} else {
						diceThrowButton.setVisible(true);
						acquisitionPrice = 0;
					}
				} else if (caughtPerson == "player2") {
					if (land[player2.location].amountLandmark != 1) {
						acquisitionScene.setVisible(true);
					} else {
						diceThrowButton.setVisible(true);
						acquisitionPrice = 0;
					}
				}
			}
		});
		fineScene.add(useFreePassButton);
		useFreePassButton.setEnabled(false);

		// 인수하기
		acquisitionText.setText("인수하시겠습니까??");
		acquisitionText.setForeground(Color.white);
		acquisitionText.setFont(new Font("굴림", Font.BOLD, 17));
		acquisitionText.setBounds(100, 10, 200, 60);
		acquisitionScene.add(acquisitionText);

		acquisitionPriceText.setText("인수가격 : " + acquisitionPrice);
		acquisitionPriceText.setForeground(Color.white);
		acquisitionPriceText.setFont(new Font("굴림", Font.BOLD, 15));
		acquisitionPriceText.setBounds(0, 100, 340, 60);
		acquisitionPriceText.setHorizontalAlignment(SwingConstants.CENTER);
		acquisitionScene.add(acquisitionPriceText);

		acquisitionYesButton.setText("네");
		acquisitionYesButton.setBounds(30, 220, 100, 50);
		acquisitionYesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 상대방의 땅이 자신의 소유가 된다.
				acquisitionScene.setVisible(false);

				if (caughtPerson == "player1") {
					if (player1.money > acquisitionPrice) {
						player2.money = player2.money + acquisitionPrice;
						player1.money = player1.money - acquisitionPrice;

						player1moneyText.setText("money : " + player1.money);
						player2moneyText.setText("money : " + player2.money);
						land[landNumOnArrival].landowner = "player1";

						if (landNumOnArrival > 0 && landNumOnArrival <= 8) {
							landBottomLine[landNumOnArrival - 1].setIcon(new ImageIcon("./images/BlueLand.png"));

						} else if (landNumOnArrival >= 9 && landNumOnArrival <= 15) {
							landLeftLine[landNumOnArrival - 9].setIcon(new ImageIcon("./images/BlueLand.png"));

						} else if (landNumOnArrival >= 16 && landNumOnArrival <= 23) {
							landTopLine[landNumOnArrival - 16].setIcon(new ImageIcon("./images/BlueLand.png"));

						} else if (landNumOnArrival >= 24 && landNumOnArrival <= 29) {
							landRightLine[landNumOnArrival - 24].setIcon(new ImageIcon("./images/BlueLand.png"));
						}

						playerMove(player1, player1Image);
					} else {
						JOptionPane.showMessageDialog(frame, "돈이 부족하여 살 수 없어요", "SYSTEM",
								JOptionPane.INFORMATION_MESSAGE);
						diceThrowButton.setVisible(true);
					}

				} else if (caughtPerson == "player2") {
					if (player2.money > acquisitionPrice) {
						player2.money = player2.money - acquisitionPrice;
						player1.money = player1.money + acquisitionPrice;

						player1moneyText.setText("money : " + player1.money);
						player2moneyText.setText("money : " + player2.money);
						land[landNumOnArrival].landowner = "player2";

						if (landNumOnArrival > 0 && landNumOnArrival <= 8) {
							landBottomLine[landNumOnArrival - 1].setIcon(new ImageIcon("./images/RedLand.png"));

						} else if (landNumOnArrival >= 9 && landNumOnArrival <= 15) {
							landLeftLine[landNumOnArrival - 9].setIcon(new ImageIcon("./images/RedLand.png"));

						} else if (landNumOnArrival >= 16 && landNumOnArrival <= 23) {
							landTopLine[landNumOnArrival - 16].setIcon(new ImageIcon("./images/RedLand.png"));

						} else if (landNumOnArrival >= 24 && landNumOnArrival <= 29) {
							landRightLine[landNumOnArrival - 24].setIcon(new ImageIcon("./images/RedLand.png"));
						}
						playerMove(player2, player2Image);
					} else {
						JOptionPane.showMessageDialog(frame, "돈이 부족하여 살 수 없어요", "SYSTEM",
								JOptionPane.INFORMATION_MESSAGE);
						diceThrowButton.setVisible(true);
					}
				}
				acquisitionPrice = 0;
			}
		});
		acquisitionScene.add(acquisitionYesButton);

		acquisitionNoButton.setText("아니요");
		acquisitionNoButton.setBounds(205, 220, 100, 50);
		acquisitionNoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diceThrowButton.setVisible(true);
				acquisitionScene.setVisible(false);
				acquisitionPrice = 0;
			}
		});
		acquisitionScene.add(acquisitionNoButton);

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

		Player1Moving.start();
		Player2Moving.start();
		gameEnding.start();

	}

	public void playerMove(Player player, JLabel playerImage) {

		constructionCostText.setText("건설비용: " + land[player.location].constructionCost);
		
		constructionPrice[0].setText("" + land[player.location].villaPrice);
		constructionPrice[1].setText("" + land[player.location].buildingPrice);
		constructionPrice[2].setText("" + land[player.location].hotelPrice);
		constructionPrice[3].setText("" + land[player.location].landmarkPrice);
		
		if (player.location >= 30) {
			player.location = player.location - 30;
		}

		if (player.location == 0) {
			playerImage.setLocation(landRightLine[6].getX() + 10, landRightLine[6].getY() + 10);
		} else {
			
			//특별칸들이 아닌 경우
			if (player.location != 4) {
				if (player.location != 9) {
					if (player.location != 15) {
						if (player.location != 20) {
							if (player.location != 24) {
								if (player.location != 27) {
									if (player == player1) {

										/*System.out.println("플레이어 1이 움직였습니다. 현재 플레이어는 " + player.round + "바퀴째 입니다.");

										System.out.println("지역이름 : " + land[player.location].landName + " | 땅 주인: "
												+ land[player.location].landowner + " | 도착 당시 | 빌라 선택횟수: "
												+ land[player.location].villaCheckCount + " | 빌딩 선택 횟수: "
												+ land[player.location].buildingCheckCount + " | 호텔 선택 횟수: "
												+ land[player.location].hotelCheckCount + " | 랜드마크 선택 횟수: "
												+ land[player.location].landmarkCheckCount + " | 가격 : "
												+ land[player.location].constructionCost); */
										
										// 땅의 주인이 없거나 자신의 땅일 때
										if (land[player.location].landowner == ""
												|| land[player.location].landowner == "player1") {
											landLabel.setVisible(true);

											if (land[player.location].amountVilla == 0) {
												landBulidingButton[0].setIcon(new ImageIcon("./images/villa.png"));
											} else {
												landBulidingButton[0].setIcon(new ImageIcon("./images/villaCheck.png"));
											}

											if (land[player.location].amountBuilding == 0) {
												landBulidingButton[1].setIcon(new ImageIcon("./images/building.png"));
											} else {
												landBulidingButton[1]
														.setIcon(new ImageIcon("./images/buildingCheck.png"));
											}

											if (land[player.location].amountHotel == 0) {
												landBulidingButton[2].setIcon(new ImageIcon("./images/hotel.png"));
											} else {
												landBulidingButton[2].setIcon(new ImageIcon("./images/hotelCheck.png"));
											}

											if (land[player.location].amountLandmark == 0) {
												landBulidingButton[3].setIcon(new ImageIcon("./images/landmark.png"));
											} else {
												landBulidingButton[3]
														.setIcon(new ImageIcon("./images/landmarkCheck.png"));
											}

											if (land[player.location].amountVilla == 1
													&& land[player.location].amountBuilding == 1
													&& land[player.location].amountHotel == 1) {
												landBulidingButton[3].setEnabled(true);
											} else {
												landBulidingButton[3].setEnabled(false);
											}

											diceThrowButton.setVisible(false);

											landName.setText("" + land[player.location].landName);

											System.out.println("지역이름 : " + land[player.location].landName + " | 땅 주인: "
													+ land[player.location].landowner + " | 도착 당시 | 빌라 선택횟수: "
													+ land[player.location].villaCheckCount + " | 빌딩 선택 횟수: "
													+ land[player.location].buildingCheckCount + " | 호텔 선택 횟수: "
													+ land[player.location].hotelCheckCount + " | 랜드마크 선택 횟수: "
													+ land[player.location].landmarkCheckCount + " | 가격 : "
													+ land[player.location].constructionCost);


											int constructionPriceHorizontalLength = 40;

											// 가격 위치 구성
											for (int j = 0; j < constructionPrice.length; j++) {
												landLabel.add(constructionPrice[j]);
												constructionPrice[j].setBounds(constructionPriceHorizontalLength,
														constructionPriceVerticalLength, constructionPriceWidth,
														constructionPriceHeight);
												constructionPriceHorizontalLength = constructionPriceHorizontalLength
														+ constructionPriceWidth + 10;
											}

											// 땅의 주인이 상대방일때 벌금을 내고 인수를 할 수 있다.
										} else if (land[player.location].landowner == "player2") {

											// 벌금 창을 띄운다
											fineScene.setVisible(true);
											diceThrowButton.setVisible(false);

											// 우대권을 가지고 있을 경우 우대권사용버튼을 활성화 시킨다.
											if (isPlayer1hasFreePass == true) {
												useFreePassButton.setEnabled(true);
											} else {
												useFreePassButton.setEnabled(false);
											}

											// 벌금 가격 측정
											if (land[player.location].amountVilla == 1) {
												amountOfFine = amountOfFine + land[player.location].villaPrice;
												if (land[player.location].amountBuilding == 1) {
													amountOfFine = amountOfFine + land[player.location].buildingPrice;

													if (land[player.location].amountHotel == 1) {
														amountOfFine = amountOfFine + land[player.location].hotelPrice;

														if (land[player.location].amountLandmark == 1) {
															amountOfFine = amountOfFine
																	+ land[player.location].landmarkPrice;
														}
													}
												}
											}

											// 랜드마크가 없으면 건물 금액의 1/2가 벌금 가격이 된다.
											if (land[player.location].amountLandmark != 1) {
												amountOfFine = amountOfFine / 2;
												// System.out.println(amountOfFine);
											} else {
												// 랜드마크이면 건물 금액이 전부 벌금 가격이 된다.
												// System.out.println(amountOfFine);
											}

											amountFineText.setText("벌금가격 : " + amountOfFine);

											// 인수 가격
											if (land[player.location].amountVilla == 1) {
												acquisitionPrice = acquisitionPrice + land[player.location].villaPrice;

												if (land[player.location].amountBuilding == 1) {
													acquisitionPrice = acquisitionPrice
															+ land[player.location].buildingPrice;

													if (land[player.location].amountHotel == 1) {
														acquisitionPrice = acquisitionPrice
																+ land[player.location].hotelPrice;
													}
												}
											}
											acquisitionPrice = acquisitionPrice * 2;
											acquisitionPriceText.setText("인수가격 : " + acquisitionPrice);
											landNumOnArrival = player.location;
											caughtPerson = "player1";

											// player1.haveLand++;
											// player2.haveLand--;
											// System.out.println("플레이어1의 총 땅 갯수 : "+player1.haveLand);
											// System.out.println("플레이어2의 총 땅 갯수 : "+player2.haveLand);
										}

									} else if (player == player2) {

										System.out.println("플레이어 2이 움직였습니다. 현재 플레이어는 " + player.round + "바퀴째 입니다.");

										if (land[player.location].landowner == ""
												|| land[player.location].landowner == "player2") {
											landLabel.setVisible(true);
											diceThrowButton.setVisible(false);

											landName.setText("" + land[player.location].landName);

											if (land[player.location].amountVilla == 0) {
												landBulidingButton[0].setIcon(new ImageIcon("./images/villa.png"));
											} else {
												landBulidingButton[0].setIcon(new ImageIcon("./images/villaCheck.png"));
											}

											if (land[player.location].amountBuilding == 0) {
												landBulidingButton[1].setIcon(new ImageIcon("./images/building.png"));
											} else {
												landBulidingButton[1]
														.setIcon(new ImageIcon("./images/buildingCheck.png"));
											}

											if (land[player.location].amountHotel == 0) {
												landBulidingButton[2].setIcon(new ImageIcon("./images/hotel.png"));
											} else {
												landBulidingButton[2].setIcon(new ImageIcon("./images/hotelCheck.png"));
											}

											if (land[player.location].amountLandmark == 0) {
												landBulidingButton[3].setIcon(new ImageIcon("./images/landmark.png"));
											} else {
												landBulidingButton[3]
														.setIcon(new ImageIcon("./images/landmarkCheck.png"));
											}

											if (land[player.location].amountVilla == 1
													&& land[player.location].amountBuilding == 1
													&& land[player.location].amountHotel == 1) {
												landBulidingButton[3].setEnabled(true);
											} else {
												landBulidingButton[3].setEnabled(false);
											}

											/*System.out.println("지역이름 : " + land[player.location].landName + " | 땅 주인: "
													+ land[player.location].landowner + " | 도착 당시 | 빌라 선택횟수: "
													+ land[player.location].villaCheckCount + " | 빌딩 선택 횟수: "
													+ land[player.location].buildingCheckCount + " | 호텔 선택 횟수: "
													+ land[player.location].hotelCheckCount + " | 랜드마크 선택 횟수: "
													+ land[player.location].landmarkCheckCount + " | 가격 : "
													+ land[player.location].constructionCost); */

											//int landButtonHorizontalLength = 7;

											// 버튼 위치 구성 !!!!!!!!!!!!!!!!!!!!!!!!!!
											
											/*
											 * for (int k = 0; k < landBulidingButton.length; k++) {
											 * landLabel.add(landBulidingButton[k]); //
											 * landBulidingButton[player.round].setEnabled(true);
											 * landBulidingButton[k].setBounds(landButtonHorizontalLength,
											 * landButtonVerticalLength, landButtonWidth, landButtonHeight);
											 * landButtonHorizontalLength = landButtonHorizontalLength + landButtonWidth
											 * + 10;
											 * 
											 * }
											 */

											int constructionPriceHorizontalLength = 40;

											// 가격 위치 구성
											for (int j = 0; j < constructionPrice.length; j++) {
												landLabel.add(constructionPrice[j]);
												constructionPrice[j].setBounds(constructionPriceHorizontalLength,
														constructionPriceVerticalLength, constructionPriceWidth,
														constructionPriceHeight);
												constructionPriceHorizontalLength = constructionPriceHorizontalLength
														+ constructionPriceWidth + 10;
											}

											// 땅의 주인이 상대방일때 벌금을 내고 인수를 할 수 있다.
										} else if (land[player.location].landowner == "player1") {

											// 벌금 창을 띄운다
											fineScene.setVisible(true);
											diceThrowButton.setVisible(false);

											// 우대권을 가지고 있을 경우 우대권사용버튼을 활성화 시킨다.
											if (isPlayer2hasFreePass == true) {
												useFreePassButton.setEnabled(true);
											} else {
												useFreePassButton.setEnabled(false);
											}

											// 벌금 가격 측정
											if (land[player.location].amountVilla == 1) {
												amountOfFine = amountOfFine + land[player.location].villaPrice;

												if (land[player.location].amountBuilding == 1) {
													amountOfFine = amountOfFine + land[player.location].buildingPrice;

													if (land[player.location].amountHotel == 1) {
														amountOfFine = amountOfFine + land[player.location].hotelPrice;

														if (land[player.location].amountLandmark == 1) {
															amountOfFine = amountOfFine
																	+ land[player.location].landmarkPrice;
														}
													}
												}
											}

											// 랜드마크가 없으면 건물 금액의 1/2가 벌금 가격이 된다.
											if (land[player.location].amountLandmark != 1) {
												amountOfFine = amountOfFine / 2;
												// System.out.println(amountOfFine);
											} else {
												// 랜드마크이면 건물 금액이 전부 벌금 가격이 된다.
												// System.out.println(amountOfFine);
											}

											amountFineText.setText("벌금가격 : " + amountOfFine);

											// 인수 가격
											if (land[player.location].amountVilla == 1) {
												acquisitionPrice = acquisitionPrice + land[player.location].villaPrice;

												if (land[player.location].amountBuilding == 1) {
													acquisitionPrice = acquisitionPrice
															+ land[player.location].buildingPrice;

													if (land[player.location].amountHotel == 1) {
														acquisitionPrice = acquisitionPrice
																+ land[player.location].hotelPrice;
													}
												}
											}
											acquisitionPrice = acquisitionPrice * 2;
											acquisitionPriceText.setText("인수가격 : " + acquisitionPrice);
											landNumOnArrival = player.location;
											caughtPerson = "player2";

											// player1.haveLand--;
											// player2.haveLand++;
											// System.out.println("플레이어1의 총 땅 갯수 : "+player1.haveLand);
											// System.out.println("플레이어2의 총 땅 갯수 : "+player2.haveLand);
										}
									}
								}
							}
						}
					}
				}
			}
		}

		// 특별칸에 도착 했을 때
		if (player.location == 4 || player.location == 20) {

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
				player1leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : 3일");

				if (isPlayer1hasuninhabitedCard == true) {
					luckeyCardScene.setVisible(true);
					cardNameText.setText("무인도 탈출 카드");
					cardContentText.setText("사용하시겠습니까?");
				}

			} else if (player == player2) {
				playSituation.setText("Player2이 무인도에 갇혔습니다");
				player2leftdayOfisland.setVisible(true);
				Player2forcedRest = 3;
				player2leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : 3일");

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
				System.out.println("플레이어1 돈: " + player1.money);
			} else if (player == player2) {
				player2moneyText.setText("money : " + player.money);
				System.out.println("플레이어1 돈: " + player2.money);
			}
			playSituation.setText("사회복지기금에서 돈을 기부했습니다 -" + socialWelfareCost + "원");
		}

	}

	public void airport() {

		playSituation.setText("가고 싶은 지역을 선택해주세요");

		for (int i = 0; i < landBottomLine.length; i++) {
			landBottomLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (player1FlyingStatus.contains("비행기 타기")) {
						for (int i = 0; i < landBottomLine.length; i++) {
							if (e.getSource() == landBottomLine[i]) {
								player1Image.setLocation(landBottomLine[i].getX() + 10, landBottomLine[i].getY() + 10);
								player1.location = i + 1;
							}
						}
						playerMove(player1, player1Image);

					} else if (player2FlyingStatus.contains("비행기 타기")) {
						for (int i = 0; i < landBottomLine.length; i++) {
							if (e.getSource() == landBottomLine[i]) {
								player2Image.setLocation(landBottomLine[i].getX() + 10, landBottomLine[i].getY() + 10);
								player2.location = i + 1;
							}
						}
						playerMove(player2, player1Image);
					}
				}
			});
		}

		for (int i = 0; i < landLeftLine.length; i++) {
			landLeftLine[i].addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					if (player1FlyingStatus.contains("비행기 타기")) {
						for (int i = 0; i < landLeftLine.length; i++) {
							if (e.getSource() == landLeftLine[i]) {
								player1Image.setLocation(landLeftLine[i].getX() + 10, landLeftLine[i].getY() + 10);
								player1.location = i + 9;
							}
						}
						playerMove(player1, player1Image);

					} else if (player2FlyingStatus.contains("비행기 타기")) {
						for (int i = 0; i < landLeftLine.length; i++) {
							if (e.getSource() == landLeftLine[i]) {
								player2Image.setLocation(landLeftLine[i].getX() + 10, landLeftLine[i].getY() + 10);
								player2.location = i + 9;
							}
						}
						playerMove(player2, player1Image);
					}
				}
			});
		}

		for (

				int i = 0; i < landTopLine.length; i++) {
			landTopLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (player1FlyingStatus.contains("비행기 타기")) {
						for (int i = 0; i < landTopLine.length; i++) {
							if (e.getSource() == landTopLine[i]) {
								player1Image.setLocation(landTopLine[i].getX() + 10, landTopLine[i].getY() + 10);
								player1.location = i + 16;
							}
						}
						playerMove(player1, player1Image);

					} else if (player2FlyingStatus.contains("비행기 타기")) {
						for (int i = 0; i < landTopLine.length; i++) {
							if (e.getSource() == landTopLine[i]) {
								player2Image.setLocation(landTopLine[i].getX() + 10, landTopLine[i].getY() + 10);
								player2.location = i + 16;
							}
						}
						playerMove(player2, player1Image);
					}
				}
			});
		}

		for (int i = 0; i < landRightLine.length; i++) {
			landRightLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (player1FlyingStatus.contains("비행기 타기")) {
						for (int i = 0; i < landRightLine.length; i++) {
							if (e.getSource() == landRightLine[i]) {
								player1Image.setLocation(landRightLine[i].getX() + 10, landRightLine[i].getY() + 10);
								player1.location = i + 24;
							}
						}
						playerMove(player1, player1Image);

					} else if (player2FlyingStatus.contains("비행기 타기")) {
						for (int i = 0; i < landRightLine.length; i++) {
							if (e.getSource() == landRightLine[i]) {
								player2Image.setLocation(landRightLine[i].getX() + 10, landRightLine[i].getY() + 10);
								player2.location = i + 24;
							}
						}
						playerMove(player2, player1Image);
					}
				}
			});
		}
	}

	public void luckyCard(Player player) {

		luckeyCardScene.setVisible(true);
		diceThrowButton.setVisible(false);

		luckeyCardNum = ramdom.nextInt(7);

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

	/*
	 * public void sellBuilding() { //sellBuilding sellBuilding = new
	 * sellBuilding(); System.out.println("파산 전에 건물을 판매하세요.");
	 * sellLandPanel.setVisible(true); diceThrowButton.setVisible(false);
	 * 
	 * //나중에 landowner 이거 값 수정해야함 if(whosTurn == 1) { for(int i=0; i<land.length;
	 * i++) { if(land[i].landowner == "player2") {
	 * System.out.println("플레이어 2가 가지고 있는 땅 이름 "+land[i].landName+"빌라 가격: "+land[i].
	 * villaPrice); } } for(int i=0; i<player2.haveLand; i++) {
	 * 
	 * } System.out.println("플레이어 2가 가지고 있는 땅 갯수: "+(player2.haveLand));
	 * //System.out.println("플레이어 1가 가지고 있는 땅 갯수: "+player1.haveLand); }else {
	 * for(int i=0; i<land.length; i++) { if(land[i].landowner == "player1") {
	 * System.out.println("플레이어 1가 가지고 있는 땅 이름 "+land[i].landName+"빌라 가격: "+land[i].
	 * villaPrice); } }
	 * System.out.println("플레이어 1가 가지고 있는 땅 갯수: "+(player1.haveLand));
	 * //System.out.println("플레이어 2가 가지고 있는 땅 갯수: "+player1.haveLand); }
	 * 
	 * if(land[player1.location].landowner == "player1") {
	 * System.out.println(land[player1.location].landName); }else {
	 * System.out.println(land[player2.location].landName); } }
	 */

	class PlayerMoving extends Thread {

		Player player = new Player();
		JLabel playerImage = new JLabel();
		JLabel playSituation = new JLabel();
		JLabel playermoneyText = new JLabel();

		boolean running = true;

		public PlayerMoving(Player player, JLabel playerImage, JLabel playSituation, JLabel playermoneyText) {
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
								player.money = player.money + salary;
								playermoneyText.setText("money : " + player.money);
								playSituation.setText("출발점을 지나 월급 " + salary + "원을 얻었습니다");

								// 한바퀴를 돌면 round가 1씩 늘어남
								if (player == player1) {
									player1.round++;
									//System.out.println("player1은 지금 " + player1.round + "바퀴째 입니다.");
								} else {
									player2.round++;
									//System.out.println("player2는 지금 " + player2.round + "바퀴째 입니다.");
								}
							}
						}

						if (player.previousLocation == 0) {
							playerImage.setLocation(BlueMarble.landRightLine[6].getX() + 10,
									BlueMarble.landRightLine[6].getY() + 10);

						} else if (player.previousLocation >= 1 && player.previousLocation <= 8) {
							for (int i = 0; i <= player.previousLocation - 1; i++) {
								playerImage.setLocation(BlueMarble.landBottomLine[i].getX() + 10,
										BlueMarble.landBottomLine[i].getY() + 10);
							}

						} else if (player.previousLocation >= 9 && player.previousLocation <= 15) {
							for (int i = 0; i <= player.previousLocation - 9; i++) {
								playerImage.setLocation(BlueMarble.landLeftLine[i].getX() + 10,
										BlueMarble.landLeftLine[i].getY() + 10);
							}

						} else if (player.previousLocation >= 16 && player.previousLocation <= 23) {
							for (int i = 0; i <= player.previousLocation - 16; i++) {
								playerImage.setLocation(BlueMarble.landTopLine[i].getX() + 10,
										BlueMarble.landTopLine[i].getY() + 10);
							}

						} else if (player.previousLocation >= 24 && player.previousLocation <= 29) {
							for (int i = 0; i <= player.previousLocation - 24; i++) {
								playerImage.setLocation(BlueMarble.landRightLine[i].getX() + 10,
										BlueMarble.landRightLine[i].getY() + 10);
							}
						}

						player.previousLocation++;
						Thread.sleep(150);

					} else {

						playerMove(player, playerImage);

						if (player == player1) {
							if (player2FlyingStatus == "비행기 타기") {
								playSituation.setText("원하는 도시를 클릭 후 주사위 버튼을 눌러주세요");
							}
							whosTurnText.setText("Player 2순서");
							player1FlyingStatus = "";

						} else if (player == player2) {
							if (player1FlyingStatus == "비행기 타기") {
								playSituation.setText("원하는 도시를 클릭 후 주사위 버튼을 눌러주세요");
							}
							whosTurnText.setText("Player 1 순서");
							player2FlyingStatus = "";
						}

						this.wait();

					}

				} catch (InterruptedException e) {
					running = false;
				}
			}
		}
	}
}