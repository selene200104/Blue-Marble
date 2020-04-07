import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BlueMarble {

	private JFrame frame;

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
	
	/*코드 추가된 곳*/
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

		JPanel blueMarbleScene = new JPanel();
		blueMarbleScene.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(blueMarbleScene);
		blueMarbleScene.setLayout(null);
		
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
		
		/*코드 추가된 곳*/
		Player player1 = new Player();
		
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
		
		/*코드 추가된 곳*/
		//서귀포 기본 창 구성(아직 도착했을 때, 이미지 등 삽입 안함) 
		JPanel seogwipo = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				ImageIcon image = new ImageIcon("./images/Seogwipo.jpg");
				g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
			}
		};
		seogwipo.setBounds(150,100,450,350);
		blueMarbleScene.add(seogwipo);
		seogwipo.setLayout(null);
		seogwipo.setVisible(true);
		
		JLabel constructionCostText = new JLabel();
		constructionCostText.setText("건설비용 : " + constructionCost);
		constructionCostText.setFont(new Font("굴림", Font.BOLD, 15));
		constructionCostText.setBounds(10,180,450,100);
		seogwipo.add(constructionCostText);
		constructionCostText.setVisible(true);
		
		JButton close = new JButton((new ImageIcon("./images/closeButton.png")));
		//close.setText("X");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seogwipo.setVisible(false);
			}			
		});
		close.setBounds(400,0,50,50);
		seogwipo.add(close);
		close.setVisible(true);
		close.setBorderPainted(false); 
		close.setFocusPainted(false); 
		close.setContentAreaFilled(false);
		
		JButton land = new JButton((new ImageIcon("./images/land.png")));
		//land.setText("땅 값");
		land.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				landCount++;
				//BlueMarble cost = new BlueMarble();
				if(landCount % 2 == 0) {
					land.setIcon(new ImageIcon("./images/land.png"));
					//landCost = 130000;
					constructionCost -= landCost;
					constructionCostText.setText("건설비용 : " + constructionCost);
				}else {
					land.setIcon(new ImageIcon("./images/landCheck.png"));
					//landCost = 130000;
					constructionCost += landCost;
					constructionCostText.setText("건설비용 : " + constructionCost);
				}
			}			
		});
		land.setBounds(10,60,100,130);
		seogwipo.add(land);
		land.setVisible(true);
		
		JButton villaButton = new JButton((new ImageIcon("./images/villa.png")));
		//villaButton.setText("빌라");
		villaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				villaCount++;
				//BlueMarble cost = new BlueMarble();
				if(villaCount % 2 == 0) {
					//villaCost = 50000;
					villaButton.setIcon(new ImageIcon("./images/villa.png"));
					constructionCost -= villaCost;
					constructionCostText.setText("건설비용 : " + constructionCost);
				}else {
					//villaCost = 50000;
					villaButton.setIcon(new ImageIcon("./images/villaCheck.png"));
					constructionCost += villaCost;
					constructionCostText.setText("건설비용 : " + constructionCost);
				}
			}			
		});
		villaButton.setBounds(120,60,100,130);
		seogwipo.add(villaButton);
		villaButton.setVisible(true);
		
		JButton building = new JButton((new ImageIcon("./images/building.png")));
		//building.setText("빌딩");
		building.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildingCount++;
				//BlueMarble cost = new BlueMarble();
				if(buildingCount % 2 == 0) {
					//buildingCost = 150000;
					building.setIcon(new ImageIcon("./images/building.png"));
					constructionCost -= buildingCost;
					constructionCostText.setText("건설비용 : " + constructionCost);
				}else {
					//buildingCost = 150000;
					building.setIcon(new ImageIcon("./images/buildingCheck.png"));
					constructionCost += buildingCost;
					constructionCostText.setText("건설비용 : " + constructionCost);
				}
			}			
		});
		building.setBounds(230,60,100,130);
		seogwipo.add(building);
		building.setVisible(true);
		
		JButton hotel = new JButton((new ImageIcon("./images/hotel.png")));
		//hotel.setText("호텔");
		hotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hotelCount++;
				//BlueMarble cost = new BlueMarble();
				if(hotelCount % 2 == 0) {
					//hotelCost = 250000;
					hotel.setIcon(new ImageIcon("./images/hotel.png"));
					constructionCost -= hotelCost;
					constructionCostText.setText("건설비용 : " + constructionCost);
				}else {
					//hotelCost = 250000;
					hotel.setIcon(new ImageIcon("./images/hotelCheck.png"));
					constructionCost += hotelCost;
					constructionCostText.setText("건설비용 : " + constructionCost);
				}
			}			
		});
		hotel.setBounds(340,60,100,130);
		seogwipo.add(hotel);
		hotel.setVisible(true);
		
		JButton BuyButton = new JButton();
		BuyButton.setText("구매하기");
		BuyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(player1.money > constructionCost) {
					if(landCount % 2 != 0) {
						player1.money -= constructionCost;
						System.out.println("구입 후 남은 돈 : "+player1.money);
					}else {
						System.out.println("구매할 수 없습니다.");
					}
				}else {
					System.out.println("돈이 부족합니다.");
				}
				
			}			
		});
		BuyButton.setBounds(180,250,100,50);
		seogwipo.add(BuyButton);
		BuyButton.setVisible(true);
		
	}

}
