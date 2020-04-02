import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

class Game extends JPanel implements ActionListener {

	private Map			map_grass;
	private Defender defender1 = new Defender1(0, 0);
	private Defender defender2 = new Defender2(0, 0);
	static int k = 0;
	private boolean inGame = false;
	private Player player = new Player();
	static int level = 1;
	private int time_enumy = 80;
	ArrayList<Enemy> enemy = new ArrayList<>();
	ArrayList<Defender> defenders = new ArrayList<>();
	ArrayList<Bullet> bullets = new ArrayList<>();
	Timer t = new Timer(100, this);

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (inGame)
		{
			for (int i = 0; i < map_grass.getLengthX(); i++) {
				for (int j = 0; j < map_grass.getLengthY(); j++)
					g.drawImage(map_grass.getCell(i, j).getImage(), map_grass.getCell(i, j).getX(), map_grass.getCell(i, j).getY(), null);
			}
			Iterator<Enemy> iter2 = this.enemy.iterator();
			while (iter2.hasNext()) {
				Enemy q = iter2.next();
				if (q.getX() <= 0) {
					g.drawImage(q.getImage(), q.getX(), q.getY(), null);
					t.stop();
					repaint();
				} else
					g.drawImage(q.getImage(), q.getX(), q.getY(), null);
			}
			Iterator<Bullet> iter1 = this.bullets.iterator();
			while (iter1.hasNext()) {
				Bullet q = iter1.next();
				if (q.getX() > 1280)
					iter1.remove();
				else {
					g.drawImage(q.getImage(), q.getX(), q.getY() - 8, 64, 64, null);
					q.setSpeed();
				}
			}
			for (Defender q : this.defenders)
				g.drawImage(q.getImage(), q.getX(), q.getY(), 64, 64, null);

// RIGHT TOOLS

			g.setColor(Color.getHSBColor(180, 180, 200));
			g.fillRect(1216, 0, 164, 918);
			g.drawImage(constructorImage("icon"), 1255, 128, 100, 100, null);
			g.drawImage(constructorImage("icon"), 1255, 247, 100, 100, null);
			g.drawImage(constructorImage("defender1"), 1275, 143, 64, 64, null);
			g.drawImage(constructorImage("defender2"), 1275, 266, 64, 64, null);
			g.setColor(Color.BLACK);
			g.drawString("Price: " + defender1.getCost(), 1275, 234);
			g.drawString("Price: " + defender2.getCost(), 1275, 357);
			g.drawString("Your money: " + player.getMoney(), 1250, 128);
			if (inGame && MouseMove.getMoved()) {
				g.drawImage(constructorImage(MouseMove.getNameImage()), MouseMove.getNewX(), MouseMove.getNewY(), 64, 64, null);
			}
			if (inGame && MouseMove.getBuild()) {
				if (MouseMove.getNewX() < 1216 && MouseMove.getNewY() < 916) {
					if (MouseMove.getNameImage().equals("defender1") && player.getMoney() >= defender1.getCost()) {
						this.player.subtractMoney(defender1.getCost());
						defenders.add(new Defender1((MouseMove.getNewX() / 64) * 64, (MouseMove.getNewY() / 64) * 64));
					} else if (MouseMove.getNameImage().equals("defender2") && player.getMoney() >= defender1.getCost()) {
						this.player.subtractMoney(defender2.getCost());
						defenders.add(new Defender2((MouseMove.getNewX() / 64) * 64, (MouseMove.getNewY() / 64) * 64));
					}
				}
				MouseMove.offBuilder();
			}

		}
	}


	Game() throws InterruptedException {
		map_grass = new Map();
		init();
	}

	public void init() {
		final JButton button = new JButton("Start");
		final MouseMove q = new MouseMove();
		if (!inGame) {
			button.setBounds(1225, 700, 150, 70);
			final JButton finalButton = button;
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					if (!inGame) {
						inGame = true;
						finalButton.setText("Stop");
						addMouseListener(q);
						addMouseMotionListener(q);
						t.start();
					} else {
						inGame = false;
						removeMouseListener(q);
						removeMouseMotionListener(q);
						finalButton.setText("Start");
						t.stop();
					}
				}
			});
			add(button);
			setLayout(null);
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		k++;
		for (Enemy q: this.enemy)
			q.setX();
		if (k % 30 == 0)
		{
			for (Defender q: this.defenders)
			{
				boolean flag = false;
				for (Enemy p: this.enemy)
					if (p.getY() == q.getY() && q.getX() < p.getX())
						flag = true;
				if (flag)
					bullets.add(new Bullet(q.getX() + 6, q.getY() + 6, q));
			}
		}
		Iterator<Bullet> iter1 = this.bullets.iterator();
		while (iter1.hasNext()) {
			Bullet q = iter1.next();
			Iterator<Enemy> iter = this.enemy.iterator();
			while (iter.hasNext()) {
				Enemy p = iter.next();
				if (q.getX() >= p.getX() && q.getY() - 6 == p.getY() && q.getStartX() < p.getX()) {
					p.setHp(q.getDamage());
					iter1.remove();
					if (p.getHp() <= 0) {
						player.addMoney(p.getMoney());
						iter.remove();
					}
				}
			}
		}

		Iterator<Enemy> iter = this.enemy.iterator();
		while (iter.hasNext()) {
			Iterator<Defender> iter2 = this.defenders.iterator();
			Enemy q = iter.next();
			while (iter2.hasNext()) {
				Defender p = iter2.next();
				if (q.getY() == p.getY() && q.getX() == p.getX())
					iter2.remove();
			}
		}

		if (k % 2 == 0)
		{
			time_enumy = 1 + (int)(Math.random() * 2 / level);
			this.enemy.add(new Enemy1((int)(Math.random() * level + 1), this.map_grass.getCell(19, (int) (Math.random() * 14))));
			level++;
		}
		repaint();
	}

	public Image constructorImage(String nameImage) {
		return new ImageIcon("src/main/resources/" + nameImage + ".png").getImage();
	}
}