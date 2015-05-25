import javax.swing.*;

public class Splash extends JFrame {
	public Splash() {
		setSize(620, 780);
		setVisible(true);
		setLocation(650, 100);
		JLabel icon = new JLabel();
		ImageIcon corgy = new ImageIcon("images/Splash.jpg");
		icon.setIcon(corgy);
		icon.setSize(100, 100);
		add(icon);
	}

	public static void main(String[] args) {
		Splash frame = new Splash();
		//frame.getContentPane().add(new JButton("OK"));
	}
}