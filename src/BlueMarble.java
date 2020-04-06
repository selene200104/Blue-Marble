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
	
	//플레이어
	JLabel player1Image = new JLabel();
	JLabel player2Image = new JLabel();
	JLabel player1infomationBoard = new JLabel();
	JLabel player2infomationBoard = new JLabel();
	JLabel player1moneyText = new JLabel();
	JLabel player2moneyText = new JLabel();
	JLabel player1leftdayOfisland = new JLabel();
	JLabel player2leftdayOfisland = new JLabel();
	
	Player player1 = new Player();
	Player player2 = new Player();
	
	int howsTurn = 1;
	int Player1forcedRest = 0; //플레이어가 강제로 쉬어야 하는 날짜
	int Player2forcedRest = 0;
	
	//부루마블 판 
	JLabel[] topLine = new JLabel[8];
	JLabel[] bottomLine = new JLabel[8];
	JLabel[] leftLine = new JLabel[7];
	JLabel[] rightLine = new JLabel[7];

	int topLineHorizontalLength = 82;
	int topLineVerticalLength = 3;
	int bottomLineHorizontalLength = 82;
	int bottomLineVerticalLength = 482;
	int leftLineHorizontalLength = 2;
	int leftLineVerticalLength = 3;
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
			
			bottomLineHorizontalLength = bottomLineHorizontalLength + HorizontalLineInterval;
			
			bottomLine[i].setIcon(new ImageIcon("./images/Line.png"));
		}
		
		// 왼쪽 줄
		for (int i = 0; i < leftLine.length; i++) {

			blueMarbleScene.add(leftLine[i] = new JLabel());

			leftLine[i].setBounds(leftLineHorizontalLength, leftLineVerticalLength, lineWidth, lineHeight);

			leftLineVerticalLength = leftLineVerticalLength + VerticalLineInterval;

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

				//player1의 차례일 때
				if(howsTurn == 1) {
					
					if(Player1forcedRest == 0) {
						player1leftdayOfisland.setVisible(false);
						
						player1.location = player1.location + diceNum;
						playerMove(player1, player1Image);
						player(player1);
					}else {
						Player1forcedRest--;
						player1leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : " + (Player1forcedRest+1) +" 일");
					}
					
					//플레이어을 움직인 후 차례를 바꾼다
					howsTurn = 2;
					
				//player2의 차례일 때
				}else if(howsTurn == 2) {
					
					if (Player2forcedRest == 0) {
						player2leftdayOfisland.setVisible(false);
						
						player2.location = player2.location + diceNum;
						playerMove(player2, player2Image);
						player(player2);
					} else {
						Player2forcedRest--;
						player2leftdayOfisland.setText("무인도 탈출하기까지 남은 일수 : " + (Player2forcedRest+1) +" 일");
					}
					
					//플레이어을 움직인 후 차례를 바꾼다
					howsTurn = 1;
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

		if(player.location >= 30) {
			player.location = player.location - 30;
		}
		
		if(player.location == 0) {
			playerImage.setLocation(rightLine[6].getX() + 10, rightLine[6].getY() + 10);
			
		}else if (player.location >= 1 && player.location <= 8) {
			for (int i = 0; i <= 8 - player.location; i++) {
				playerImage.setLocation(bottomLine[i].getX() + 10, bottomLine[i].getY() + 10);
			}
			
		}else if (player.location >= 9 && player.location <= 15) {
			for (int i = 0; i <= 15 - player.location; i++) {
				playerImage.setLocation(leftLine[i].getX() + 10, leftLine[i].getY() + 10);
			}
			
		}else if (player.location >= 16 && player.location <= 23) {
			for (int i = 0; i <= player.location -16; i++) {
				playerImage.setLocation(topLine[i].getX() + 10, topLine[i].getY() + 10);
			}
			
		}else if (player.location >= 24 && player.location <= 29) {
			for (int i = 0; i <= player.location - 24; i++) {
				playerImage.setLocation(rightLine[i].getX() + 10, rightLine[i].getY() + 10);
			}
		}
	}

	public void player(Player player) {

		if (player.location == 9) {
			if (player == player1) {
				airport(player, player1Image);

			}else if(player == player2) {
				airport(player, player2Image);
				
			}

		} else if (player.location == 15) {
			if(player == player1) {
				player1leftdayOfisland.setVisible(true);
				Player1forcedRest = 2;
				
			}else if(player == player2) {
				player2leftdayOfisland.setVisible(true);
				Player2forcedRest = 2;
			}
		}
	}
	
	public void airport(Player player, JLabel playerImage) {
		
		for (int i = 0; i < leftLine.length; i++) {
			leftLine[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					for (int i = 0; i < leftLine.length; i++) {
						if (e.getSource() == leftLine[i]) {
							playerImage.setLocation(leftLine[i].getX() + 10, leftLine[i].getY()+ 10);

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
							playerImage.setLocation(rightLine[i].getX()+ 10, rightLine[i].getY()+ 10);
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
							playerImage.setLocation(topLine[i].getX() + 10 , topLine[i].getY() + 10);
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
							playerImage.setLocation(bottomLine[i].getX()+ 10, bottomLine[i].getY() + 10);

						}
					}
				}
			});
		}
	}
}
