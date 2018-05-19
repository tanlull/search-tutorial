/**
 * S. Chatchawal 
 * May 19, 2561 BE 8:52:36 PM
 * http://www.conan.in.th
 * chatchawal.sangkeettrakarn@nectec.or.th, conan@conan.in.th
 */
package parse;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import interfaces.NewsParser;
import model.News;

/**
 * @author conan
 *
 */
public class ThairathParser implements NewsParser {

	/* (non-Javadoc)
	 * @see interfaces.NewsParser#getNews(java.lang.String)
	 */
	@Override
	public News getNews(String url) {
		// TODO Auto-generated method stub
		News n = new News();
		
		try {
			
			Document doc = Jsoup.connect(url).get();
			
			
			
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		return n;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws Exception {
		// TODO Auto-generated method stub
		
		
		String url = "https://www.thairath.co.th/lifestyle/tech";
		Document doc = Jsoup.connect(url).get();
		
		
		

	}

}
