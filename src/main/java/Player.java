import java.util.LinkedList;
import java.util.List;

public class Player {
	private int		money = 600;
	private int 	kills = 0;
	private int 	level = 1;
	static  float	begin_frequency = 12;
	private float 	time_enemy = 2;
	private double	speed = 2;
	private Map		map;
	private List<Enemy> enemy = new LinkedList<>();
	private List<Defender> defenders = new LinkedList<>();
	private List<Bullet>	bullets = new LinkedList<>();
	private List<Explosion> explosions = new LinkedList<>();

	Player(Map map) {
		this.map = map;
	}

	public int getMoney() {
		return money;
	}

	public int getKills() {
		return kills;
	}

	public double getSpeed() {
		return speed;
	}

	public List<Enemy> getEnemy () {
		return (this.enemy);
	}

	public List<Defender> getDefenders () {
		return (this.defenders);
	}

	public List<Bullet> getBullets () {
		return (this.bullets);
	}

	public List<Explosion> getExplosions () {
		return (this.explosions);
	}

	public void addExplosion(int x, int y, String str) {
		this.explosions.add(new Explosion(x, y, str));
	}

	public void setSpeed() {
		this.speed += 0.2;
	}

	public void subtractMoney(int money) {
		this.money -= money;
	}

	public void addMoney(int money) {
		this.money += money;
	}

	public void addKills() {
		this.kills++;
	}

	public void addEnemy(int k) {
		int hard = (int)(Math.random() * (15 - (Math.min(level, 10))));;
		if (k % time_enemy == 0.0)
		{
			time_enemy = begin_frequency + (int)(Math.random() * 500 / Math.max(level, 3));
			if (hard == 2 || hard == 1 || hard == 3)
				this.enemy.add(new Enemy1(getSpeed(), this.map.getCell(19, (int) (Math.random() * 14))));
			else
				this.enemy.add(new Enemy2(getSpeed(), this.map.getCell(19, (int) (Math.random() * 14))));
			if (getKills() % 2 == 0 && getKills() != 0) {
				if (begin_frequency > 0.5)
					begin_frequency -= 0.5;
				if (level % 10 == 0)
					level += 4;
				level++;
				setSpeed();
			}
		}
	}
}
