import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Seogwipo {

	int constructionCost = 0;
	int landCost = 0;
	int villaCost = 0;
	int buildingCost = 0;
	int hotelCost = 0;
	int landCount = 0;
	int villaCount = 0;
	int buildingCount = 0;
	int hotelCount = 0;

	public Seogwipo() {
		//BlueMarble bluemarble = new BlueMarble();
		
		Player player1 = new Player();
		// 서귀포 도착시
		JPanel seogwipo = new JPanel() {
					public void paintComponent(Graphics g) {
						Dimension d = getSize();
						ImageIcon image = new ImageIcon("./images/Seogwipo.jpg");
						g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
					}
				};
				seogwipo.setBounds(150,100,450,350);
				//BlueMarble.blueMarbleScene.add(seogwipo);
				seogwipo.setLayout(null);
				seogwipo.setVisible(true);

		JLabel constructionCostText = new JLabel();constructionCostText.setText("건설비용 : "+constructionCost);constructionCostText.setFont(new Font("굴림",Font.BOLD,15));constructionCostText.setBounds(10,180,450,100);seogwipo.add(constructionCostText);constructionCostText.setVisible(true);

		JButton close = new JButton();close.setText("X");close.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
						seogwipo.setVisible(false);
					}			
				});
				close.setBounds(400,0,50,50);
				seogwipo.add(close);
				close.setVisible(true);
				
				JButton land = new JButton();
				land.setText("땅 값");
				land.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						landCount++;
						//BlueMarble cost = new BlueMarble();
						if(landCount % 2 == 0) {
							landCost = 130000;
							constructionCost -= landCost;
							constructionCostText.setText("건설비용 : " + constructionCost);
						}else {
							landCost = 130000;
							constructionCost += landCost;
							constructionCostText.setText("건설비용 : " + constructionCost);
						}
					}			
				});
				land.setBounds(10,60,100,130);
				seogwipo.add(land);
				land.setVisible(true);
				
				JButton villa = new JButton();
				villa.setText("빌라");
				villa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						villaCount++;
						//BlueMarble cost = new BlueMarble();
						if(villaCount % 2 == 0) {
							villaCost = 50000;
							constructionCost -= villaCost;
							constructionCostText.setText("건설비용 : " + constructionCost);
						}else {
							villaCost = 50000;
							constructionCost += villaCost;
							constructionCostText.setText("건설비용 : " + constructionCost);
						}
					}			
				});
				villa.setBounds(120,60,100,130);
				seogwipo.add(villa);
				villa.setVisible(true);
				
				JButton building = new JButton();
				building.setText("빌딩");
				building.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buildingCount++;
						//BlueMarble cost = new BlueMarble();
						if(buildingCount % 2 == 0) {
							buildingCost = 150000;
							constructionCost -= buildingCost;
							constructionCostText.setText("건설비용 : " + constructionCost);
						}else {
							buildingCost = 150000;
							constructionCost += buildingCost;
							constructionCostText.setText("건설비용 : " + constructionCost);
						}
					}			
				});
				building.setBounds(230,60,100,130);
				seogwipo.add(building);
				building.setVisible(true);
				
				JButton hotel = new JButton();
				hotel.setText("호텔");
				hotel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						hotelCount++;
						//BlueMarble cost = new BlueMarble();
						if(hotelCount % 2 == 0) {
							hotelCost = 250000;
							constructionCost -= hotelCost;
							constructionCostText.setText("건설비용 : " + constructionCost);
						}else {
							hotelCost = 250000;
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
