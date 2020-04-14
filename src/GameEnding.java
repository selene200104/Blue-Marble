import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameEnding extends Thread{

	boolean running = true;
	JPanel gameEndingScene = new JPanel();
	JPanel blueMarbleScene = new JPanel();
	JLabel gameEndingText = new JLabel();
	JLabel winnerText = new JLabel();
	Player player1 = new Player();
	Player player2 = new Player();
	
	public GameEnding(Player player1, Player player2, JPanel gameEndingScene, JPanel blueMarbleScene, JLabel gameEndingText, JLabel winnerText) {
		this.gameEndingScene = gameEndingScene;
		this.blueMarbleScene = blueMarbleScene;
		this.gameEndingText = gameEndingText;
		this.winnerText = winnerText;
		this.player1 = player1;
		this.player2 = player2;
	}
	
	@Override
	public synchronized void run() {
		while (running) {
			try {

				if(player1.money <= -50000) {
					gameEndingText.setText("player1이 파산하여 게임이 끝났습니다");
					winnerText.setText("우승자 : player2");
					gameEndingScene.setVisible(true);
					blueMarbleScene.setVisible(false);
					
				}else if(player2.money <= -50000) {
					gameEndingText.setText("player2가 파산하여 게임이 끝났습니다");
					winnerText.setText("우승자 : player1");
					gameEndingScene.setVisible(true);
					blueMarbleScene.setVisible(false);
					
				}else if(player1.money >= 91000000) {
					gameEndingText.setText("player1 돈 백만원을 모아 게임이 끝났습니다");
					winnerText.setText("우승자 : player1");
					gameEndingScene.setVisible(true);
					blueMarbleScene.setVisible(false);
					
				}else if(player2.money >= 91000000) {
					gameEndingText.setText("player2가  백만원을 모아 게임이 끝났습니다");
					winnerText.setText("우승자 : player2");
					gameEndingScene.setVisible(true);
					blueMarbleScene.setVisible(false);
					
				}else {
					
				}
				
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				running = false;
			}
		}
	}
}
