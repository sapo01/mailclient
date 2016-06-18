import view.MainView;
import view.SettingsView;

import javax.swing.JFrame;

public class Main {

	private static JFrame frame;

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Mailclient");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setResizable(false);
	}

	public static JFrame getFrame() {
		return frame;
	}

	public static void setFrame(JFrame frame) {
		Main.frame = frame;

		// TODO Auto-generated method stub
		MainView menu = new MainView();
		menu.setUp();

	}
}
