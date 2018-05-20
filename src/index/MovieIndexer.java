/**
 * S. Chatchawal 
 * May 21, 2561 BE 12:28:56 AM
 * http://www.conan.in.th
 * chatchawal.sangkeettrakarn@nectec.or.th, conan@conan.in.th
 */
package index;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONObject;

/**
 * @author conan
 *
 */
public class MovieIndexer {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		Scanner sc = new Scanner(new File("files/movie.json"));
		
		String line = "";
		
		while(sc.hasNext()) {
			line = sc.nextLine();
			JSONObject m = new JSONObject(line);
			String title = m.getString("title");
			String url = m.getString("url");
			String synopsis = m.getString("synopsis");
			String format = m.getString("format");
			String genres = m.getString("genres").equals("null")?"":m.getString("genres");
			System.out.println(title+" "+format+" "+genres+" "+url);
		}
		sc.close();
		

	}

}
