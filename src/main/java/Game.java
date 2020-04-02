import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

class Game extends JPanel implements ActionListener {

	private Map			map_grass;
	static int k = 0;
	static int level = 1;
	private int time_enumy = 80;
	ArrayList<Enemy> enemy = new ArrayList<>();
	ArrayList<Defender> defenders = new ArrayList<>();
	ArrayList<Bullet> bullets = new ArrayList<>();
	ArrayList<Explosion> explosions = new ArrayList<>();
	Timer t = new Timer(100, this);

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < map_grass.getLengthX(); i++) {
			for (int j = 0; j < map_grass.getLengthY(); j++)
				g.drawImage(map_grass.getCell(i, j).getImage(), map_grass.getCell(i, j).getX(), map_grass.getCell(i, j).getY(), null);
			}
		Iterator<Bullet> iter1 = this.bullets.iterator();
		while (iter1.hasNext()) {
			Bullet q = iter1.next();
			if (q.getX() > 1280)
				iter1.remove();
			else {
				g.drawImage(q.getImage(), q.getX(), q.getY() - 8, 64,64, null);
				q.setSpeed();
			}
			}
		for (Defender q: this.defenders)
			g.drawImage(q.getImage(), q.getX(), q.getY(), 64, 64, null);
		Iterator<Enemy> iter2 = this.enemy.iterator();
		while (iter2.hasNext()) {
			Enemy q = iter2.next();
			if (q.getX() < 0)
			{
				iter2.remove();
				t.stop();
			}
			else
				g.drawImage(q.getImage(), q.getX(), q.getY(), 64, 64,null);
		}

		Iterator<Explosion> iterExp = this.explosions.iterator();
		while (iterExp.hasNext()) {
			Explosion exp = iterExp.next();
			g.drawImage(exp.getImage(), exp.getX(), exp.getY() , 64,64, null);
			if (!exp.getAlive()) {
				iterExp.remove();
			}
		}

// RIGHT TOOLS

		g.setColor(Color.getHSBColor(180, 180, 200));
		g.fillRect(1216, 0, 164, 918);
		g.drawImage(constructorImage("icon"), 1280, 128, 100, 100, null);
		g.drawImage(constructorImage("icon"), 1280, 234, 100, 100, null);
		g.drawImage(constructorImage("defender1"), 1300, 146, 64, 64, null);
		g.drawImage(constructorImage("defender2"), 1300, 252, 64, 64, null);
		if (MouseMove.getMoved()) {
			g.drawImage(constructorImage(MouseMove.getNameImage()), MouseMove.getNewX(), MouseMove.getNewY(), 64, 64, null);
		}
		if (MouseMove.getBuild()) {
			if (MouseMove.getNewX() < 1216 && MouseMove.getNewY() < 916) {
				if (!map_grass.getCell(MouseMove.getNewX() / 64, MouseMove.getNewY() / 64).getDefender()) {
					if (MouseMove.getNameImage().equals("defender1")) {
						defenders.add(new Defender1((MouseMove.getNewX() / 64) * 64, (MouseMove.getNewY() / 64) * 64));
					} else if (MouseMove.getNameImage().equals("defender2")) {
						defenders.add(new Defender2((MouseMove.getNewX() / 64) * 64, (MouseMove.getNewY() / 64) * 64));
					}
					map_grass.getCell(MouseMove.getNewX() / 64, MouseMove.getNewY() / 64).setDefender(true);
				}
			}
				MouseMove.offBuilder();
			}
		}



	Game() throws InterruptedException {
		map_grass = new Map();
		this.enemy.add(new Enemy((int)(Math.random() * level + 1), this.map_grass.getCell(19, (int) (Math.random() * 14))));
		this.addMouseListener(new MouseMove());
		this.addMouseMotionListener(new MouseMove());
		init();
	}

	public void init() {
		t.start();
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
					if (p.getHp() <= 0){
						explosions.add(new Explosion(p.getX(), p.getY(), "enemy"));
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
				if (q.getY() == p.getY() && q.getX() == p.getX()) {
					explosions.add(new Explosion(p.getX(), p.getY(), "defender"));
					map_grass.getCell(p.getX(),p.getY()).setDefender(false);
					iter2.remove();
				}
			}
		}

		if (k % time_enumy == 0)
		{
			time_enumy = 30 + (int)(Math.random() * 700 / level);
			this.enemy.add(new Enemy((int)(Math.random() * level + 1), this.map_grass.getCell(19, (int) (Math.random() * 14))));
			level++;
		}
		repaint();
	}

	public Image constructorImage(String nameImage) {
		return new ImageIcon("src/main/resources/" + nameImage + ".png").getImage();
	}
}