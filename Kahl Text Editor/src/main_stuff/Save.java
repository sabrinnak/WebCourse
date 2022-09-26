package main_stuff;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Save {

	public static void sv(String str, File f) {
		try {
			PrintWriter pw = new PrintWriter(f);
			pw.write(str);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
