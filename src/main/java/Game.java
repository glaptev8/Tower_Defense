import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

class Game extends JPanel implements ActionListener {

	private Image		grass;
	private Cell		cell;
	private final int	SIZE_CELL = 64;
	private Map			map_grass;
	int x = 0;
	int y = 0;
	static int k = 0;
	ArrayList<Enemy> enemy = new ArrayList<>();
	Random r = new Random();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < map_grass.getLengthX(); i++) {
			for (int j = 0; j < map_grass.getLengthY(); j++)
				g.drawImage(map_grass.getCell(i, j).getImage(), map_grass.getCell(i, j).getX(), map_grass.getCell(i, j).getY(), null);
			}
		for (Enemy q: this.enemy)
		{
			g.drawImage(q.getImage(), q.getX(), q.getY(), null);
		}

		}

	Game() throws InterruptedException {
		map_grass = new Map();
		this.enemy.add(new Enemy(10, 10, 10, this.map_grass.getCell(19, (int) (Math.random() * 14))));
		init();
	}

	public void init() {
		Timer t = new Timer(100, this);
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		k++;
		for (Enemy q: this.enemy)
		{
			q.setX();
		}
		if (k % 80 == 0)
			this.enemy.add(new Enemy(10, 10, 10, this.map_grass.getCell(19, (int) (Math.random() * 14))));
		repaint();
	}
}