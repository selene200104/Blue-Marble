import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	}

}
