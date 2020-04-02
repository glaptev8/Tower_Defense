import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseMove implements MouseListener, MouseMotionListener  {

	private static int newX;
	private static int newY;
	private static boolean moved;
	private static boolean build;
	private static String nameImage;

	public static boolean getMoved() {
		return moved;
	}
	public static boolean getBuild() {
		return build;
	}

	public static int getNewX() {
		return newX;
	}
	public static int getNewY() {
		return newY;
	}
	public static String getNameImage() {
		return nameImage;
	}

	public static void offBuilder(){
		build = false;
		newX = 0;
		newY = 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getX() > 1280 && e.getX() < 1380 && e.getY() > 128 && e.getY() < 228) {
			moved = true;
			nameImage = "defender1";
			newX = e.getX();
			newY = e.getY();
		} else if (e.getX() > 1280 && e.getX() < 1380 && e.getY() > 234 && e.getY() < 334) {
			moved = true;
			nameImage = "defender2";
			newX = e.getX();
			newY = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
			moved = false;
			newX += 32;
			newY += 32;
			build = true;
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		newX = e.getX() - 32;
		newY = e.getY() - 32;
	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {

	}
}
