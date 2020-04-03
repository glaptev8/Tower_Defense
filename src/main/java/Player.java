import java.util.LinkedList;

public class Player {
	private int		money = 500;
	private int 	kills = 0;
	private int 	level = 1;
	private int 	time_enemy = 2;
	private double	speed = 1;
	private Map		map;
	LinkedList<Enemy>	enemy = new LinkedList<>();
	LinkedList<Defender> defenders = new LinkedList<>();
	LinkedList<Bullet>	bullets = new LinkedList<>();
	LinkedList<Explosion> explosions = new LinkedList<>();

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
		int hard = (int)(Math.random() * (15 - (Math.min(level, 10))));
		if (k % time_enemy == 0)
		{
			time_enemy = 20 - (Math.min(level, 10)) + (int)(Math.random() * 500 / level);
			if (hard == 2 || hard == 1)
				this.enemy.add(new Enemy1(getSpeed(), this.map.getCell(19, (int) (Math.random() * 14))));
			else
				this.enemy.add(new Enemy2(getSpeed(), this.map.getCell(19, (int) (Math.random() * 14))));
			if (getKills() % 2 == 0 && getKills() != 0) {
				level++;
				setSpeed();
			}
		}
	}
}
