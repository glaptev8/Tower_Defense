import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

class Game extends JPanel implements ActionListener {

	private Map			map = new Map();
	private Player 		player = new Player(map);
	private int 		k = 0;
	private boolean 	inGame = false;
	private boolean 	gameOver = false;
	private JButton 	button = null;
	private Timer		t = new Timer(100, this);

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!gameOver)
		{
			drawMap(g);
			drawBullet(g);
			drawDefender(g);
			drawEnemy(g);
			drawAnimation(g);
			drawRightTools(g);
		}
		else {
			String s = "GAME OVER";
			setBackground(Color.BLACK);
			g.setColor(Color.getHSBColor(180, 180, 200));
			Font f = new Font("Arial", Font.BOLD, 100);
			remove(button);
			g.setFont(f);
			g.drawString(s, 500, 450);
			g.drawString("Result: " + player.getKills(), 595, 545);
		}
	}

	Game() {
		init();
	}

	public void init() {
		this.button = createButton();
		add(button);
	}

	public void createBullet() {
		if (k % 30 == 0)
		{
			for (Defender q: player.getDefenders())
			{
				boolean flag = false;
				for (Enemy p: player.getEnemy())
					if (p.getY() == q.getY() && q.getX() < p.getX())
						flag = true;
				if (flag)
					player.getBullets().add(new Bullet(q.getX() + 6, q.getY() + 6, q));
			}
		}
	}

	public void runBullet() {
		createBullet();
		Iterator<Bullet> bullets = player.getBullets().iterator();

		while (bullets.hasNext()) {
			Bullet bullet = bullets.next();
			bullet.setSpeed();
			if (bullet.getX() > 1280)
			{
				bullets.remove();
				continue;
			}
			Iterator<Enemy> iter = player.getEnemy().iterator();
			while (iter.hasNext()) {
				Enemy p = iter.next();
				if (bullet.getX() >= p.getX() && bullet.getY() - 6 == p.getY() && bullet.getStartX() < p.getX()) {
					p.setHp(bullet.getDamage());
					bullets.remove();
					if (p.getHp() <= 0) {
						player.addKills();
						player.getExplosions().add(new Explosion(p.getX(), p.getY(), "shot"));
						player.addMoney(p.getMoney());
						iter.remove();
					}
					break;
				}
			}
		}
	}

	public void runEnemy() {
		player.addEnemy(k);
		Iterator<Enemy> enemies = player.getEnemy().iterator();

		while (enemies.hasNext()) {
			Iterator<Defender> iter2 = player.getDefenders().iterator();
			Enemy enemy = enemies.next();
			enemy.setX();
			if (enemy.getX() <= 0) {
				t.stop();
				gameOver = true;
				repaint();
			}
			while (iter2.hasNext()) {
				Defender p = iter2.next();
				if (enemy.getY() == p.getY() && (enemy.getX() - p.getX() <= 0) && (enemy.getX() - p.getX() + player.getSpeed() >= 0))
				{
					player.addExplosion(p.getX(), p.getY(), "defender");
					map.getCell(p.getX() / 64, p.getY() / 64).setDefender(false);
					iter2.remove();
				}
			}
		}
	}

	public void run() {
		runEnemy();
		runBullet();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		k++;
		run();
		repaint();
	}

	public JButton createButton() {
		JButton button = new JButton("Start");
		final MouseMove q = new MouseMove();

		if (!inGame) {
			button.setBounds(1225, 700, 150, 70);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					if (!inGame) {
						inGame = true;
						button.setText("Stop");
						addMouseListener(q);
						addMouseMotionListener(q);
						t.start();
					} else {
						inGame = false;
						removeMouseListener(q);
						removeMouseMotionListener(q);
						button.setText("Start");
						t.stop();
					}
				}
			});
		}
		setLayout(null);
		return (button);
	}

	public Image constructorImage(String nameImage) {
		return new ImageIcon(ClassLoader.getSystemResource(nameImage + ".png")).getImage();
	}

	public void drawMap(Graphics g) {
		for (int i = 0; i < map.getLengthX(); i++) {
			for (int j = 0; j < map.getLengthY(); j++)
				g.drawImage(map.getCell(i, j).getImage(), map.getCell(i, j).getX(), map.getCell(i, j).getY(), null);
		}
	}

	public void drawEnemy(Graphics g) {
		Iterator<Enemy> iter2 = player.getEnemy().iterator();
		while (iter2.hasNext()) {
			Enemy q = iter2.next();
			g.drawImage(q.getImage(), q.getX(), q.getY(), 64, 64, null);
		}
	}

	public void drawBullet(Graphics g) {
		Iterator<Bullet> iter1 = player.getBullets().iterator();
		while (iter1.hasNext()) {
			Bullet q = iter1.next();
			g.drawImage(q.getImage(), q.getX(), q.getY() - 8, 64, 64, null);
		}
	}

	public void drawAnimation(Graphics g) {
		Iterator<Explosion> iterExp = player.getExplosions().iterator();
		while (iterExp.hasNext()) {
			Explosion exp = iterExp.next();
			g.drawImage(exp.getImage(), exp.getX(), exp.getY() , 64,64, null);
			if (!exp.getAlive()) {
				iterExp.remove();
			}
		}
	}

	public void drawDefender(Graphics g) {
		for (Defender q : player.getDefenders())
			g.drawImage(q.getImage(), q.getX(), q.getY(), 64, 64, null);
	}

	public void drawRightTools(Graphics g) {
		Defender defender1 = new Defender1(0, 0);
		Defender defender2 = new Defender2(0, 0);

		g.setColor(Color.getHSBColor(180, 180, 200));
		g.fillRect(1216, 0, 164, 918);
		g.drawImage(constructorImage("icon"), 1255, 128, 100, 100, null);
		g.drawImage(constructorImage("icon"), 1255, 247, 100, 100, null);
		g.drawImage(constructorImage("defender1"), 1275, 143, 64, 64, null);
		g.drawImage(constructorImage("defender2"), 1275, 266, 64, 64, null);
		g.setColor(Color.BLACK);
		g.drawString("Price: " + defender1.getPrice(), 1275, 234);
		g.drawString("Price: " + defender2.getPrice(), 1275, 357);
		g.drawString("Your money: " + player.getMoney(), 1250, 128);
		if (inGame && MouseMove.getMoved()) {
			g.drawImage(constructorImage(MouseMove.getNameImage()), MouseMove.getNewX(), MouseMove.getNewY(), 64, 64, null);
		}
		if (inGame && MouseMove.getBuild()) {
			if (MouseMove.getNewX() < 1216 && MouseMove.getNewY() < 916) {
				if (!map.getCell(MouseMove.getNewX() / 64, MouseMove.getNewY() / 64).getDefender()) {
					if (MouseMove.getNameImage().equals("defender1") && player.getMoney() >= defender1.getPrice()) {
						this.player.subtractMoney(defender1.getPrice());
						player.getDefenders().add(new Defender1((MouseMove.getNewX() / 64) * 64, (MouseMove.getNewY() / 64) * 64));
						map.getCell(MouseMove.getNewX() / 64, MouseMove.getNewY() / 64).setDefender(true);
					} else if (MouseMove.getNameImage().equals("defender2") && player.getMoney() >= defender2.getPrice()) {
						this.player.subtractMoney(defender2.getPrice());
						player.getDefenders().add(new Defender2((MouseMove.getNewX() / 64) * 64, (MouseMove.getNewY() / 64) * 64));
						map.getCell(MouseMove.getNewX() / 64, MouseMove.getNewY() / 64).setDefender(true);
					}
			}
			}
			MouseMove.offBuilder();
		}
	}
}