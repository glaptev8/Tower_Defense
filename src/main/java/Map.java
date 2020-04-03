class Map {
	Cell[][] map;
	final int column = 20;
	final int rows = 14;
	private int[][]		start_map = {
			{0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 1, 2, 2, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 2, 2, 1, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 1, 2, 2, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 2, 1, 1, 1, 2, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0}
	};

	Map() {
		map = new Cell[column][rows];
		for (int i = 0; i < start_map.length; i++) {
			for (int j = 0; j < start_map[i].length; j++) {
				switch (start_map[i][j]) {
					case (1):
						map[j][i] = new Cell(j * 64, i * 64, TypeCell.Mud.type);
						break;
					case (2):
						map[j][i] = new Cell(j * 64, i * 64, TypeCell.Water.type);
						break;
					case (0):
						map[j][i] = new Cell(j * 64, i * 64, TypeCell.Grass.type);
						break;
					default:
						map[j][i] = new Cell(j * 64, i * 64, TypeCell.Grass.type);
				}
			}
		}

	}

	public int getLengthX() {
		return this.map.length;
	}

	public int getLengthY() {
		return this.map[0].length;
	}

	public Cell getCell(int x, int y) {
		return (this.map[x][y]);
	}
}