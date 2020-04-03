import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

class Game extends JPanel implements ActionListener {

	private Map			map = new Map();
	private Player 		player = new Player();
	static int 			k = 0;
	static int			level = 1;
	private boolean 	inGame = false;
	private boolean 	gameOver = false;
	LinkedList<Enemy>	enemy = new LinkedList<>();
	LinkedList<Defender>	defenders = new LinkedList<>();
	LinkedList<Bullet> bullets = new LinkedList<>();
	LinkedList<Explosion> explosions = new LinkedList<>();
	JButton button = createButton();
	Timer				t = new Timer(100, this);

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
			g.drawString("Result: " + player.getkills(), 595, 545);
		}
	}

	Game() {
		init();
	}

	public void init() {
		add(button);
	}

	public void createBullet() {
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
	}

	public void runBullet() {
		createBullet();
		Iterator<Bullet> bullets = this.bullets.iterator();

		while (bullets.hasNext()) {
			Bullet bullet = bullets.next();
			bullet.setSpeed();
			if (bullet.getX() > 1280)
			{
				bullets.remove();
				continue;
			}
			Iterator<Enemy> iter = this.enemy.iterator();
			while (iter.hasNext()) {
				Enemy p = iter.next();
				if (bullet.getX() >= p.getX() && bullet.getY() - 6 == p.getY() && bullet.getStartX() < p.getX()) {
					p.setHp(bullet.getDamage());
					bullets.remove();
					if (p.getHp() <= 0) {
						player.addKills();
						explosions.add(new Explosion(p.getX(), p.getY(), "shot"));
						player.addMoney(p.getMoney());
						iter.remove();
					}
					break;
				}
			}
		}
	}

	public void runEnemy() {
		addEnemy();
		Iterator<Enemy> enemies = this.enemy.iterator();

		while (enemies.hasNext()) {
			Iterator<Defender> iter2 = this.defenders.iterator();
			Enemy enemy = enemies.next();
			enemy.setX();
			if (enemy.getX() <= 0) {
				t.stop();
				gameOver = true;
				repaint();
			}
			while (iter2.hasNext()) {
				Defender p = iter2.next();
				if (enemy.getY() == p.getY() && (enemy.getX() - p.getX() + level <= level) && (enemy.getX() - p.getX() + level >= 0))
				{
					explosions.add(new Explosion(p.getX(), p.getY(), "defender"));
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

	public void addEnemy() {
		int time_enemy = 1 + (int)(Math.random() * 1000 / level);
		int hard = (int)(Math.random() * 5);
		if (k % time_enemy == 0)
		{
			if (hard == 2 || hard == 1)
				this.enemy.add(new Enemy1(player.getSpeed(), this.map.getCell(19, (int) (Math.random() * 14))));
			else
				this.enemy.add(new Enemy2(player.getSpeed(), this.map.getCell(19, (int) (Math.random() * 14))));
			if (player.getkills() % 2 == 0) {
				level += 1;
				player.setSpeed();
			}
		}
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
		return new ImageIcon("src/main/resources/" + nameImage + ".png").getImage();
	}

	public void drawMap(Graphics g) {
		for (int i = 0; i < map.getLengthX(); i++) {
			for (int j = 0; j < map.getLengthY(); j++)
				g.drawImage(map.getCell(i, j).getImage(), map.getCell(i, j).getX(), map.getCell(i, j).getY(), null);
		}
	}

	public void drawEnemy(Graphics g) {
		Iterator<Enemy> iter2 = this.enemy.iterator();
		while (iter2.hasNext()) {
			Enemy q = iter2.next();
			g.drawImage(q.getImage(), q.getX(), q.getY(), 64, 64, null);
		}
	}

	public void drawBullet(Graphics g) {
		Iterator<Bullet> iter1 = this.bullets.iterator();
		while (iter1.hasNext()) {
			Bullet q = iter1.next();
			g.drawImage(q.getImage(), q.getX(), q.getY() - 8, 64, 64, null);
		}
	}

	public void drawAnimation(Graphics g) {
		Iterator<Explosion> iterExp = this.explosions.iterator();
		while (iterExp.hasNext()) {
			Explosion exp = iterExp.next();
			g.drawImage(exp.getImage(), exp.getX(), exp.getY() , 64,64, null);
			if (!exp.getAlive()) {
				iterExp.remove();
			}
		}
	}

	public void drawDefender(Graphics g) {
		for (Defender q : this.defenders)
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
						defenders.add(new Defender1((MouseMove.getNewX() / 64) * 64, (MouseMove.getNewY() / 64) * 64));
					} else if (MouseMove.getNameImage().equals("defender2") && player.getMoney() >= defender1.getPrice()) {
						this.player.subtractMoney(defender2.getPrice());
						defenders.add(new Defender2((MouseMove.getNewX() / 64) * 64, (MouseMove.getNewY() / 64) * 64));
					}
					map.getCell(MouseMove.getNewX() / 64, MouseMove.getNewY() / 64).setDefender(true);
			}
			}
			MouseMove.offBuilder();
		}
	}
}