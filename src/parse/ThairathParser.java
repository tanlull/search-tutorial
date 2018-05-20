/**
 * S. Chatchawal 
 * May 19, 2561 BE 8:52:36 PM
 * http://www.conan.in.th
 * chatchawal.sangkeettrakarn@nectec.or.th, conan@conan.in.th
 */
package parse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
			
			Document doc = Jsoup.connect(url).timeout(5000).get();
			
			String title = doc.select("h1").first().text();
			String content = doc.select("section[itemprop=articleBody]").first().text();
			
			
			//meta property="og:article:published_time" content="2018-05-14T14:05:00+07:00"
			
			String date = doc.select("meta[property=og:article:published_time]").first().attr("content");
			Date d = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss+07:00").parse(date);
			
			
			//<meta name="cXenseParse:category" content="lifestyle">
			String category = doc.select("meta[name=cXenseParse:category]").first().attr("content");

			
			n.setTitle(title);
			n.setContent(content);
			n.setUrl(url);
			n.setDatetime(d);
			n.setCategory(category);
			n.setPublisher("thairath");
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		return n;
	}

	@Override
	public ArrayList<News> getAllNews(String url) {

		ArrayList<News> nlist = new ArrayList<News>();
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			Elements es = doc.select("h4 a"); // select all tag a (link) which under tag h4
			for(Element e :es) {
				String u = e.absUrl("href");
				if(u.contains("/content/")) {
					News n = getNews(u);					
					nlist.add(n);
					System.out.println(u);
					Thread.sleep(new Random().nextInt(5)*1000);
				}
			}
		}catch(Exception er) {}
		
		
		return nlist;
	}
	
	
	public static void main(String[] args)  throws Exception {
		// TODO Auto-generated method stub
		
		
		String url = "https://www.thairath.co.th/lifestyle/tech";
		ArrayList<News> nlist = new ThairathParser().getAllNews(url);
		
		for(News n : nlist) {
			System.out.println(n.getTitle());
			System.out.println(n.getCategory());
			System.out.println(n.getDatetime());
		}
		

	}






}
