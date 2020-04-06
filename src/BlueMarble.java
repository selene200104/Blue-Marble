import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	//플레이어
	JLabel playerImage = new JLabel();
	Player player1 = new Player();
	
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

		JPanel blueMarbleScene = new JPanel();
		blueMarbleScene.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(blueMarbleScene);
		blueMarbleScene.setLayout(null);

		//플레이어
		playerImage.setIcon(new ImageIcon("./images/Player1.png"));
		playerImage.setBounds(715, 495, 110, 60);
		blueMarbleScene.add(playerImage);
		
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

				player1.location = player1.location + diceNum;
				playerMove(player1);
				
			}
		});
		blueMarbleScene.add(diceThrowButton);
		

		diceNumberText.setText("주사위를 던지시오");
		diceNumberText.setFont(new Font("굴림", Font.BOLD, 13));
		diceNumberText.setBounds(510, 360, 110, 60);
		blueMarbleScene.add(diceNumberText);
		
	}
	
	public void playerMove(Player player) {

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
}
