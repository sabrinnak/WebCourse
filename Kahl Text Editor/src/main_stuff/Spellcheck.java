package main_stuff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Spellcheck {

	public static String checker(String str) throws Exception {
		String incorrect = "";
		String[] splitSentence = str.split(" ");

		for (int i = 0; i < splitSentence.length; i++) {
			try {
				File file = new File("Dictionary");
				Scanner read = new Scanner(file);
				boolean found = false;
				while (read.hasNextLine()) {
					String compare = read.nextLine();

					if (compare.equalsIgnoreCase(splitSentence[i])) {
						found = true;
						break;
					}
				}
				if (!found) {
					incorrect = incorrect + " " + splitSentence[i];
				}
				read.close();
			} catch (FileNotFoundException e) {
				e.getStackTrace();
			}
			
		}
		return incorrect;
	}
}
