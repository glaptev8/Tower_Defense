import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Explosion {
	private List<String> animation;
	private int timeout;
	private int index;
	private final String pathAnimation = "src/main/resources/explosion/";
	private boolean alive;
	private int x;
	private int y;

	public Explosion(int x, int y, String path){
		this.timeout = 1;
		this.alive = true;
		this.x = x;
		this.y = y;
		this.animation = Arrays.asList(new File(getPath(path)).list())
				.stream()
				.filter(str -> str.endsWith(".png"))
				.map(str -> getPath(path)  + "/" + str)
				.sorted()
				.collect(Collectors.toList());
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getAlive() {
		return alive;
	}

	public Image getImage() {
		timeout--;
		if (timeout == 0) {
			index++;
			timeout = 1;
		}
		index++;
		if (animation.size()  <= index && timeout == 1) {
			alive = false;
			return null;
		}
		return new ImageIcon(animation.get(index)).getImage();
	}

	private String getPath(String path) {
		return pathAnimation + path;
	}
}

