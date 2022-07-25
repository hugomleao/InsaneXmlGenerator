package reader;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class Reader {

	private static JFileChooser fileChooser = new JFileChooser();

	public static String readFile() {
		fileChooser = new JFileChooser();
		fileChooser.requestFocus();
		fileChooser.setFont(new Font("Arial", Font.PLAIN, 12));
		String path = System.getProperty("user.home");
		int retVal = fileChooser.showDialog(null, "Choose File");
		if (retVal == JFileChooser.APPROVE_OPTION) {
			// Getting the file name
			String s = fileChooser.getSelectedFile().getName();

			// Getting the file extension
			String ext = new String();
			int i = s.lastIndexOf('.');
			if (i > 0 && i < s.length() - 1) {
				ext = s.substring(i + 1).toLowerCase();
			}
			// Comparing the extension
			if (!ext.equals("geo")) {
				s = s + ".geo";
				File file = fileChooser.getSelectedFile();
				// Changing the file in case of no good extensions
				fileChooser.setSelectedFile(new File(file.getParent() + File.separator + s));
			}

		}
		if (fileChooser.getSelectedFile() != null) {
			path = fileChooser.getSelectedFile().getAbsolutePath();
		}
		int i = path.lastIndexOf('.');
		fileChooser.setVisible(false);
		return path;
	}

	public static void findLineThatStartsWith(BufferedReader br, String string) {
		try {
			
			String line = "";
			do {
				line = br.readLine().trim();
			} while (!line.equals(string));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
