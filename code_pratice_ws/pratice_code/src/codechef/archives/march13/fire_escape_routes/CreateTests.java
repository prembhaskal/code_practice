package codechef.archives.march13.fire_escape_routes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CreateTests {

	public static void main(String[] s) throws FileNotFoundException {
		File fireExistFile = new File("G://test_fire_exit.txt");
		PrintWriter out = new PrintWriter(fireExistFile);

		int tests = 0;

		out.println(1);
		out.println(10000 + " " + 10000);
		for (int i=1;i<=500;i++) {
			for (int j=i+1;j<900;j++) {
				out.println(i + " " + j);
				tests++;
				if (tests > 10000)
					break;
			}

			if (tests > 10000)
				break;
		}

		out.close();
	}
}
