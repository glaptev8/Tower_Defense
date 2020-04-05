import org.apache.commons.io.FileUtils;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Explosion {
	private List<String> animation;
	private int timeout;
	private int index;
	private final String pathAnimation = "explosion";
	private boolean alive;
	private int x;
	private int y;

	public Explosion(int x, int y, String path){
		this.timeout = 1;
		this.alive = true;
		this.x = x;
		this.y = y;
		URL url = ClassLoader.getSystemResource(this.pathAnimation + "/" + path);
		File file = FileUtils.toFile(url);
		if (file != null)
			this.animation = Arrays.asList(file.list())
					.stream()
					.filter(str -> str.endsWith(".png"))
					.map(str -> pathAnimation + "/" + path + "/" + str)
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
		URL url = ClassLoader.getSystemResource(animation.get(index));
		return new ImageIcon(url).getImage();
	}
}

