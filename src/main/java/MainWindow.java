import javax.swing.*;
import java.awt.*;


class MainWindow extends JFrame {

	public static void main(String[] args) {
		new MainWindow();
	}

	MainWindow () {
		setTitle("⚔INVASION⚔");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		setSize(1380, 918);
		add(new Game());
		setVisible(true);
	}
}
