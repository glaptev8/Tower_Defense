public class Player {
	private int money = 10000;
	private int kills = 0;
	private double speed = 1;

	public int getMoney() {
		return money;
	}

	public int getkills() {
		return kills;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed() {
		this.speed += 0.1;
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
}
