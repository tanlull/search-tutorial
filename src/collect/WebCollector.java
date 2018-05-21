/**
 * S. Chatchawal 
 * May 20, 2561 BE 11:29:58 AM
 * http://www.conan.in.th
 * chatchawal.sangkeettrakarn@nectec.or.th, conan@conan.in.th
 */
package collect;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author conan
 *
 */
public class WebCollector {

	/**
	 * @param args
	 * @throws IOException 
	 */
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// Set user agent
		String useragent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:42.0) Gecko/20100101 Firefox/42.0";

		String url = "http://www.boomerangshop.com/web/index.php/app/product/cate/bluray/category/new_release/page/1";

		//String url = "http://totws.com";

		// Jsoup Document = Collector + parser
		Document doc = Jsoup.connect(url)
				.timeout(10000)
				.userAgent(useragent)
				.get();
		
		//System.out.println(doc);
	
		
		//List all url 
		Elements allList = doc.select("li div[class=ten columns clear-padding]");
		
		
		// Only ul part // list of movie
		//Element a = allList.get(0);
		//Elements es = a.select("h3 a");
		
		//System.out.println(allList);
		
		//Elements es = a.select("h3 a");
		
		for(Element e : allList) {
			//System.out.println("AAAAA -> "+e);
			Elements href = e.select("h3 a");
			//String href = e.absUrl("a");
			//System.out.println(href);

					
			System.out.println("Link="+ href.attr("href"));	
					
			System.out.println("Name="+ href.attr("title"));
					
			//Element date = e.select("")
			
			Elements release_date = e.select("span[class=release_date]");
			System.out.println(release_date.text());
			
			Elements mov_type = e.select("span[class=mov_type]");
			System.out.println("Movie Type="+ mov_type.text());

			System.out.println("Price="+ e.select("span[class=line-through]").text());
			System.out.println("Our Price="+ e.select("span[class=green font14]").text());
	
			
			System.out.println("---------------------------------------------------------------------------");

		}

		

	}

}
