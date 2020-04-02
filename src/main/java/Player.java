public class Player {
	private int money = 400;
	private int kiils = 0;

	public int getMoney() {
		return money;
	}

	public int getKiils() {
		return kiils;
	}

	public void subtractMoney(int money) {
		this.money -= money;
	}
	public void addMoney(int money) {
		this.money += money;
	}

	public void addKills() {
		this.kiils++;
	}
}
