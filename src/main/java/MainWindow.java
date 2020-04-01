import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


class MainWindow extends JFrame {

	public static void main(String[] args) throws InterruptedException {
		new MainWindow();
	}

	MainWindow () throws InterruptedException {
		setTitle("window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		setSize(1280, 918);
		add(new Game());
		setVisible(true);
	}
}
