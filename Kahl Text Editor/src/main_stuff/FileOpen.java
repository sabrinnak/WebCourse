package main_stuff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileOpen {

	public static String openFile(File name) {
		String content = "";
		try {
			Scanner sc = new Scanner(name);
			content = sc.nextLine();
			sc.close();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
		return content;
	}

}
