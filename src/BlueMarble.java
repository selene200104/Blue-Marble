import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
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

	/* 추가함 */
	JPanel islandPanel = new JPanel() {
		public void paintComponent(Graphics g) {
			Dimension d = getSize();
			ImageIcon image = new ImageIcon("./images/island.png");
			g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		}
	};

	JLabel islandName = new JLabel();
	JLabel constructionCostText = new JLabel();
	JButton[] islandBulidingButton = new JButton[3];
	//JButton[] greenLandBulidingButton = new JButton[3];
	// JPanel[] islandPanel = new JPanel[8];

	int nameHorizontalLength = 150;
	int nameVerticalLength = 20;
	int nameLineWidth = 100;
	int nameLineHeight = 20;

	int islandButtonHorizontalLength = 7;
	int islandButtonVerticalLength = 60;
	int islandButtonWidth = 100;
	int islandButtonHeight = 130;

	// int playerlocation = 0;
	int constructionCost = 0;

	int villaCheckCount = 0;
	int buildingCheckCount = 0;
	int hotelCheckCount = 0;

	/* 추가함 */

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
	Land[] land = new Land[31];
	JLabel[] landLabel = new JLabel[31];

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

		islandPanel.setLayout(null);
		islandPanel.setBounds(200, 100, 340, 300);
		blueMarbleScene.add(islandPanel);
		islandPanel.setVisible(false);
		
		for(int i = 0; i <landLabel.length; i++) {
			blueMarbleScene.add(landLabel[i] = new JLabel());
			landLabel[i].setBounds(200, 100, 340, 300);
			landLabel[i].setIcon(new ImageIcon("./images/island.png"));
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

		islandName.setLayout(null);
		islandName.setText("");
		islandName.setBounds(nameHorizontalLength, nameVerticalLength, nameLineWidth, nameLineHeight);
		//islandPanel.add(islandName);	
		
		/*
		constructionCostText.setLayout(null);
		constructionCostText.setText("건설비용: 0");
		constructionCostText.setBounds(10, 200, nameLineWidth, nameLineHeight);
		*/

		// 땅 객체 배열 생성(위치 지역명 , 빌라 값, 빌딩 값, 호텔 값, 빌라 선택 횟수, 빌딩 선택 횟수, 호텔 선택 횟수)
		land[0] = new Land("출발지", 0, 0, 0, 0, 0, 0);
		land[1] = new Land("서귀포", 10000, 30000, 50000, 0, 0, 0);
		land[2] = new Land("제주", 3000, 230000, 150000, 0, 0, 0);
		land[3] = new Land("독도", 40000, 430000, 250000, 0, 0, 0);
		land[4] = new Land("행운카드", 0, 0, 0, 0, 0, 0);
		land[5] = new Land("경주", 20000, 60000, 10000, 0, 0, 0);
		land[6] = new Land("안동", 20000, 60000, 10000, 0, 0, 0);
		land[7] = new Land("통영", 20000, 60000, 10000, 0, 0, 0);
		land[8] = new Land("창원", 20000, 60000, 10000, 0, 0, 0);

		land[9] = new Land("공항", 0, 0, 0, 0, 0, 0);
		land[10] = new Land("강릉", 30000, 90000, 150000, 0, 0, 0);
		land[11] = new Land("원주", 30000, 90000, 150000, 0, 0, 0);
		land[12] = new Land("춘천", 30000, 90000, 150000, 0, 0, 0);
		land[13] = new Land("청주", 40000, 12000, 200000, 0, 0, 0);
		land[14] = new Land("세종", 40000, 12000, 200000, 0, 0, 0);

		land[15] = new Land("무인도", 0, 0, 0, 0, 0, 0);
		land[16] = new Land("목포", 50000, 150000, 250000, 0, 0, 0);
		land[17] = new Land("여수", 50000, 150000, 250000, 0, 0, 0);
		land[18] = new Land("군산", 50000, 150000, 250000, 0, 0, 0);
		land[19] = new Land("전주", 50000, 150000, 250000, 0, 0, 0);
		land[20] = new Land("행운카드", 0, 0, 0, 0, 0, 0);
		land[21] = new Land("포항", 60000, 180000, 300000, 0, 0, 0);
		land[22] = new Land("울산", 60000, 180000, 300000, 0, 0, 0);
		land[23] = new Land("대구", 60000, 180000, 300000, 0, 0, 0);

		land[24] = new Land("국제기구?", 0, 0, 0, 0, 0, 0);
		land[25] = new Land("인천", 70000, 210000, 350000, 0, 0, 0);
		land[26] = new Land("대전", 70000, 210000, 350000, 0, 0, 0);
		land[27] = new Land("광주", 70000, 210000, 350000, 0, 0, 0);
		land[28] = new Land("세금내는곳?", 0, 0, 0, 0, 0, 0);
		land[29] = new Land("부산", 80000, 240000, 400000, 0, 0, 0);
		land[30] = new Land("서울", 80000, 240000, 400000, 0, 0, 0);
		
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
		
		//landLabel[player.location].setLayout(null);

		islandBulidingButton[0] = new JButton((new ImageIcon("./images/islandVilla.png")));
		islandBulidingButton[1] = new JButton((new ImageIcon("./images/islandBuilding.png")));
		islandBulidingButton[2] = new JButton((new ImageIcon("./images/islandHotel.png")));
		
		landLabel[player.location].add(islandName);
		//landLabel[player.location].add(constructionCostText);
		
		if (player.location >= 30) {
			player.location = player.location - 30;
		}

		if (player.location == 0) {
			playerImage.setLocation(rightLine[6].getX() + 10, rightLine[6].getY() + 10);

		} else if (player.location >= 1 && player.location <= 8) {
			for (int i = 0; i <= player.location - 1; i++) {
				playerImage.setLocation(bottomLine[i].getX() + 10, bottomLine[i].getY() + 10);
			}
			if (player == player1) {
				if(player.location != 4) {
					landLabel[player.location].setVisible(true);
					diceThrowButton.setVisible(false);
					System.out.println("플레이어 1이 움직였습니다.");
					islandName.setText("" + land[player.location].landName);
					System.out.println("지역이름 : "+land[player.location].landName+" 빌라 선택횟수: "+land[player.location].villaCheckCount);

					int islandButtonHorizontalLength = 7;
					
					for (int k = 0; k < islandBulidingButton.length; k++) {
						landLabel[player.location].add(islandBulidingButton[k]);
						islandBulidingButton[k].setBounds(islandButtonHorizontalLength, islandButtonVerticalLength,
							islandButtonWidth, islandButtonHeight);
						islandButtonHorizontalLength = islandButtonHorizontalLength + islandButtonWidth + 10;
					}
					
					islandBulidingButton[0].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("빌라 선택함");
						if(land[player.location].villaCheckCount == 0 ) {
							land[player.location].villaCheckCount++;
							System.out.println("지역이름 : "+land[player.location].landName+" 빌라 선택횟수: "+land[player.location].villaCheckCount);
							islandBulidingButton[0].setIcon(new ImageIcon("./images/islandVillaCheck.png"));
						}else {
							land[player.location].villaCheckCount--;
							System.out.println("지역이름 : "+land[player.location].landName+" 빌라 선택횟수: "+land[player.location].villaCheckCount);
							islandBulidingButton[0].setIcon(new ImageIcon("./images/islandVilla.png"));
						}
					}
				});

				islandBulidingButton[1].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {					
						System.out.println("빌딩 선택함");
						if(land[player.location].buildingCheckCount == 0 ) {
							land[player.location].buildingCheckCount++;
							System.out.println("지역이름 : "+land[player.location].landName+" 빌딩 선택횟수: "+land[player.location].buildingCheckCount);
							islandBulidingButton[1].setIcon(new ImageIcon("./images/islandBuildingCheck.png"));
						}else {
							land[player.location].buildingCheckCount--;
							System.out.println("지역이름 : "+land[player.location].landName+" 빌딩 선택횟수: "+land[player.location].buildingCheckCount);
							islandBulidingButton[1].setIcon(new ImageIcon("./images/islandBuilding.png"));
						}
					}
				});

				islandBulidingButton[2].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("호텔 선택함");
						if(land[player.location].hotelCheckCount == 0 ) {
							land[player.location].hotelCheckCount++;
							System.out.println("지역이름 : "+land[player.location].landName+" 호텔 선택횟수: "+land[player.location].hotelCheckCount);
							islandBulidingButton[2].setIcon(new ImageIcon("./images/islandHotelCheck.png"));
						}else {
							land[player.location].hotelCheckCount--;
							System.out.println("지역이름 : "+land[player.location].landName+" 호텔 선택횟수: "+land[player.location].hotelCheckCount);
							islandBulidingButton[2].setIcon(new ImageIcon("./images/islandHotel.png"));
						}
					}
				});

				JButton CloseButton = new JButton((new ImageIcon("./images/closeButton.png")));
				CloseButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						landLabel[player.location].setVisible(false);
						diceThrowButton.setVisible(true);
						// land[player.location].villaCheckCount = 0;
						// land[player.location].buildingCheckCount = 0;
						// land[player.location].hotelCheckCount = 0;

						/*
						if (land[player.location].villaCheckCount == 0) {
							islandBulidingButton[0].setIcon(new ImageIcon("./images/islandVilla.png"));
							//constructionCost -= land[player.location].villaPrice;
						} else {
							islandBulidingButton[0].setIcon(new ImageIcon("./images/islandVillaCheck.png"));
							//constructionCost += land[player.location].villaPrice;
						}

						if (land[player.location].buildingCheckCount == 0) {
							islandBulidingButton[1].setIcon(new ImageIcon("./images/islandBuilding.png"));
							//constructionCost -= land[player.location].buildingPrice;
						} else {
							islandBulidingButton[1].setIcon(new ImageIcon("./images/islandBuildingCheck.png"));
							//constructionCost += land[player.location].buildingPrice;
						}
						if (land[player.location].hotelCheckCount == 0) {
							islandBulidingButton[2].setIcon(new ImageIcon("./images/islandHotel.png"));
							//constructionCost -= land[player.location].hotelPrice;
						} else {
							islandBulidingButton[2].setIcon(new ImageIcon("./images/islandHotelCheck.png"));
							//constructionCost += land[player.location].hotelPrice;
						}*/
					}
				});
				CloseButton.setBounds(280, 0, 50, 50);
				landLabel[player.location].add(CloseButton);
				CloseButton.setVisible(true);
				CloseButton.setBorderPainted(false);
				CloseButton.setFocusPainted(false);
				CloseButton.setContentAreaFilled(false);
					
					/*for (int k = 0; k < islandBulidingButton.length; k++) {
						islandPanel.add(islandBulidingButton[k]);
						islandBulidingButton[k].setBounds(islandButtonHorizontalLength, islandButtonVerticalLength,
								islandButtonWidth, islandButtonHeight);
						islandButtonHorizontalLength = islandButtonHorizontalLength + islandButtonWidth + 10;
					}*/
					
					/*islandBulidingButton[0].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							land[player.location].villaCheckCount++;
							System.out.println("지역이름 : "+land[player.location].landName+"빌라 선택횟수: "+land[player.location].villaCheckCount);
							if (land[player.location].villaCheckCount % 2 == 0) {
								islandBulidingButton[0].setIcon(new ImageIcon("./images/islandVilla.png"));
								constructionCost -= land[player.location].villaPrice;
								// constructionCostText.setText("건설비용 : " + constructionCost);
							} else {
								islandBulidingButton[0].setIcon(new ImageIcon("./images/islandVillaCheck.png"));
								constructionCost += land[player.location].villaPrice;
								// constructionCostText.setText("건설비용 : " + constructionCost);
							}
						}
					});
					islandPanel.add(islandBulidingButton[0]);
					islandBulidingButton[0].setBounds(islandButtonHorizontalLength, islandButtonVerticalLength,
							islandButtonWidth, islandButtonHeight);
					islandButtonHorizontalLength = islandButtonHorizontalLength + islandButtonWidth + 10;

					islandBulidingButton[1].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							land[player.location].buildingCheckCount++;
							if (land[player.location].buildingCheckCount % 2 == 0) {
								islandBulidingButton[1].setIcon(new ImageIcon("./images/islandBuilding.png"));
								//constructionCost -= land[player.location].buildingPrice;
								// constructionCostText.setText("건설비용 : " + constructionCost);
							} else {
								islandBulidingButton[1].setIcon(new ImageIcon("./images/islandBuildingCheck.png"));
								//constructionCost += land[player.location].buildingPrice;
								// constructionCostText.setText("건설비용 : " + constructionCost);
							}
						}
					});
					islandPanel.add(islandBulidingButton[1]);
					islandBulidingButton[1].setBounds(islandButtonHorizontalLength, islandButtonVerticalLength,
							islandButtonWidth, islandButtonHeight);
					islandButtonHorizontalLength = islandButtonHorizontalLength + islandButtonWidth + 10;

					islandBulidingButton[2].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							land[player.location].hotelCheckCount++;
							if (land[player.location].hotelCheckCount % 2 == 0) {
								islandBulidingButton[2].setIcon(new ImageIcon("./images/islandHotel.png"));
								//constructionCost -= land[player.location].hotelPrice;
								// constructionCostText.setText("건설비용 : " + constructionCost);
							} else {
								islandBulidingButton[2].setIcon(new ImageIcon("./images/islandHotelCheck.png"));
								//constructionCost += land[player.location].hotelPrice;
								// constructionCostText.setText("건설비용 : " + constructionCost);
							}
						}
					});
					islandPanel.add(islandBulidingButton[2]);
					islandBulidingButton[2].setBounds(islandButtonHorizontalLength, islandButtonVerticalLength,
							islandButtonWidth, islandButtonHeight);
					islandButtonHorizontalLength = islandButtonHorizontalLength + islandButtonWidth + 10;

					JButton CloseButton = new JButton((new ImageIcon("./images/closeButton.png")));
					CloseButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							islandPanel.setVisible(false);
							diceThrowButton.setVisible(true);
							// land[player.location].villaCheckCount = 0;
							// land[player.location].buildingCheckCount = 0;
							// land[player.location].hotelCheckCount = 0;

							if (land[player.location].villaCheckCount % 2 == 0) {
								islandBulidingButton[0].setIcon(new ImageIcon("./images/islandVilla.png"));
								//constructionCost -= land[player.location].villaPrice;
							} else {
								islandBulidingButton[0].setIcon(new ImageIcon("./images/islandVillaCheck.png"));
								//constructionCost += land[player.location].villaPrice;
							}

							if (land[player.location].buildingCheckCount % 2 == 0) {
								islandBulidingButton[1].setIcon(new ImageIcon("./images/islandBuilding.png"));
								//constructionCost -= land[player.location].buildingPrice;
							} else {
								islandBulidingButton[1].setIcon(new ImageIcon("./images/islandBuildingCheck.png"));
								//constructionCost += land[player.location].buildingPrice;
							}
							if (land[player.location].hotelCheckCount % 2 == 0) {
								islandBulidingButton[2].setIcon(new ImageIcon("./images/islandHotel.png"));
								//constructionCost -= land[player.location].hotelPrice;
							} else {
								islandBulidingButton[2].setIcon(new ImageIcon("./images/islandHotelCheck.png"));
								//constructionCost += land[player.location].hotelPrice;
							}
						}
					});
					CloseButton.setBounds(280, 0, 50, 50);
					islandPanel.add(CloseButton);
					CloseButton.setVisible(true);
					CloseButton.setBorderPainted(false);
					CloseButton.setFocusPainted(false);
					CloseButton.setContentAreaFilled(false);*/
				}
					
			} else {
				System.out.println("플레이어 2가 움직였습니다.");
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
		
		/*islandBulidingButton[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				land[player.location].villaCheckCount++;
				System.out.println("지역이름 : "+land[player.location].landName+"빌라 선택횟수: "+land[player.location].villaCheckCount);
				if (land[player.location].villaCheckCount % 2 == 0) {
					islandBulidingButton[0].setIcon(new ImageIcon("./images/islandVilla.png"));
					constructionCost -= land[player.location].villaPrice;
					// constructionCostText.setText("건설비용 : " + constructionCost);
				} else {
					islandBulidingButton[0].setIcon(new ImageIcon("./images/islandVillaCheck.png"));
					constructionCost += land[player.location].villaPrice;
					// constructionCostText.setText("건설비용 : " + constructionCost);
				}
			}
		});
		islandPanel.add(islandBulidingButton[0]);
		islandBulidingButton[0].setBounds(islandButtonHorizontalLength, islandButtonVerticalLength,
				islandButtonWidth, islandButtonHeight);
		//islandButtonHorizontalLength = islandButtonHorizontalLength + islandButtonWidth + 10;

		islandBulidingButton[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				land[player.location].buildingCheckCount++;
				if (land[player.location].buildingCheckCount % 2 == 0) {
					islandBulidingButton[1].setIcon(new ImageIcon("./images/islandBuilding.png"));
					//constructionCost -= land[player.location].buildingPrice;
					// constructionCostText.setText("건설비용 : " + constructionCost);
				} else {
					islandBulidingButton[1].setIcon(new ImageIcon("./images/islandBuildingCheck.png"));
					//constructionCost += land[player.location].buildingPrice;
					// constructionCostText.setText("건설비용 : " + constructionCost);
				}
			}
		});
		islandPanel.add(islandBulidingButton[1]);
		islandBulidingButton[1].setBounds(islandButtonHorizontalLength+islandButtonWidth+10, islandButtonVerticalLength,
				islandButtonWidth, islandButtonHeight);
		//islandButtonHorizontalLength = islandButtonHorizontalLength + islandButtonWidth + 10;

		islandBulidingButton[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				land[player.location].hotelCheckCount++;
				if (land[player.location].hotelCheckCount % 2 == 0) {
					islandBulidingButton[2].setIcon(new ImageIcon("./images/islandHotel.png"));
					//constructionCost -= land[player.location].hotelPrice;
					// constructionCostText.setText("건설비용 : " + constructionCost);
				} else {
					islandBulidingButton[2].setIcon(new ImageIcon("./images/islandHotelCheck.png"));
					//constructionCost += land[player.location].hotelPrice;
					// constructionCostText.setText("건설비용 : " + constructionCost);
				}
			}
		});
		islandPanel.add(islandBulidingButton[2]);
		islandBulidingButton[2].setBounds(islandButtonHorizontalLength+(islandButtonWidth+10)*2, islandButtonVerticalLength,
				islandButtonWidth, islandButtonHeight);
		//islandButtonHorizontalLength = islandButtonHorizontalLength + islandButtonWidth + 10;

		JButton CloseButton = new JButton((new ImageIcon("./images/closeButton.png")));
		CloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				islandPanel.setVisible(false);
				diceThrowButton.setVisible(true);
				// land[player.location].villaCheckCount = 0;
				// land[player.location].buildingCheckCount = 0;
				// land[player.location].hotelCheckCount = 0;

				if (land[player.location].villaCheckCount % 2 == 0) {
					islandBulidingButton[0].setIcon(new ImageIcon("./images/islandVilla.png"));
					//constructionCost -= land[player.location].villaPrice;
				} else {
					islandBulidingButton[0].setIcon(new ImageIcon("./images/islandVillaCheck.png"));
					//constructionCost += land[player.location].villaPrice;
				}

				if (land[player.location].buildingCheckCount % 2 == 0) {
					islandBulidingButton[1].setIcon(new ImageIcon("./images/islandBuilding.png"));
					//constructionCost -= land[player.location].buildingPrice;
				} else {
					islandBulidingButton[1].setIcon(new ImageIcon("./images/islandBuildingCheck.png"));
					//constructionCost += land[player.location].buildingPrice;
				}
				if (land[player.location].hotelCheckCount % 2 == 0) {
					islandBulidingButton[2].setIcon(new ImageIcon("./images/islandHotel.png"));
					//constructionCost -= land[player.location].hotelPrice;
				} else {
					islandBulidingButton[2].setIcon(new ImageIcon("./images/islandHotelCheck.png"));
					//constructionCost += land[player.location].hotelPrice;
				}
			}
		});
		CloseButton.setBounds(280, 0, 50, 50);
		islandPanel.add(CloseButton);
		CloseButton.setVisible(true);
		CloseButton.setBorderPainted(false);
		CloseButton.setFocusPainted(false);
		CloseButton.setContentAreaFilled(false);*/
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