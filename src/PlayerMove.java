import javax.swing.JLabel;

public class PlayerMove extends Thread{

	/*
	JLabel[] topLine = new JLabel[8];
	JLabel[] bottomLine = new JLabel[8];
	JLabel[] leftLine = new JLabel[7];
	JLabel[] rightLine = new JLabel[7];
	
	
	public PlayerMove(JLabel[] rightLine,  JLabel[] leftLine, JLabel[] bottomLine, JLabel[] topLine) {
		for(int i = 0; i < 8; i++) {
			this.topLine[i] = topLine[i];
			this.bottomLine[i] = bottomLine[i];
		}
		
		for(int i = 0; i < 7; i++) {
			this.leftLine[i] = leftLine[i];
			this.rightLine[i] = rightLine[i];
		}
	}
	*/
	
	Player player = new Player();
	JLabel playerImage = new JLabel();
	
	boolean running = true;
	
	public PlayerMove(Player player, JLabel playerImage) {
		this.player = player;
		this.playerImage = playerImage;
	}
	
	@Override
	public synchronized void run() {
		while (running) {
			try {

				if(player.previousLocation <= player.location) {
					
					if(player.previousLocation >= 30) {
						player.previousLocation = player.previousLocation - 30;
					}
					
					if(player.previousLocation == 0) {
						playerImage.setLocation(BlueMarble.rightLine[6].getX() + 10, BlueMarble.rightLine[6].getY() + 10);
						
					}else if (player.previousLocation >= 1 && player.previousLocation <= 8) {
						for (int i = 0; i <= player.previousLocation - 1; i++) {
							playerImage.setLocation(BlueMarble.bottomLine[i].getX() + 10, BlueMarble.bottomLine[i].getY() + 10);
						}
						
					}else if (player.previousLocation >= 9 && player.previousLocation <= 15) {
						for (int i = 0; i <= player.previousLocation -9; i++) {
							playerImage.setLocation(BlueMarble.leftLine[i].getX() + 10, BlueMarble.leftLine[i].getY() + 10);
						}
						
					}else if (player.previousLocation >= 16 && player.previousLocation <= 23) {
						for (int i = 0; i <= player.previousLocation -16; i++) {
							playerImage.setLocation(BlueMarble.topLine[i].getX() + 10, BlueMarble.topLine[i].getY() + 10);
						}
						
					}else if (player.previousLocation >= 24 && player.previousLocation <= 29) {
						for (int i = 0; i <= player.previousLocation - 24; i++) {
							playerImage.setLocation(BlueMarble.rightLine[i].getX() + 10, BlueMarble.rightLine[i].getY() + 10);
						}
					}
					
					player.previousLocation++;
					Thread.sleep(500);
					
				}else {
					this.wait();
				}
					
/*
				if(player.location >= 30) {
					player.location = player.location - 30;
				}
				
				if(player.location == 0) {
					playerImage.setLocation(BlueMarble.rightLine[6].getX() + 10, BlueMarble.rightLine[6].getY() + 10);
					
				}else if (player.location >= 1 && player.location <= 8) {
					for (int i = 0; i <= player.location - 1; i++) {
						playerImage.setLocation(BlueMarble.bottomLine[i].getX() + 10, BlueMarble.bottomLine[i].getY() + 10);
					}
					
				}else if (player.location >= 9 && player.location <= 15) {
					for (int i = 0; i <= player.location -9; i++) {
						playerImage.setLocation(BlueMarble.leftLine[i].getX() + 10, BlueMarble.leftLine[i].getY() + 10);
					}
					
				}else if (player.location >= 16 && player.location <= 23) {
					for (int i = 0; i <= player.location -16; i++) {
						playerImage.setLocation(BlueMarble.topLine[i].getX() + 10, BlueMarble.topLine[i].getY() + 10);
					}
					
				}else if (player.location >= 24 && player.location <= 29) {
					for (int i = 0; i <= player.location - 24; i++) {
						playerImage.setLocation(BlueMarble.rightLine[i].getX() + 10, BlueMarble.rightLine[i].getY() + 10);
					}
				}
				*/
				
			} catch (InterruptedException e) {
				running = false;
			}
		}
	}
}
