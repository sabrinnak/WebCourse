package main_stuff;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveAs {

	public static void sa(String s, File name) {
		try {
			FileWriter fw = new FileWriter(name);
			fw.write(s);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
