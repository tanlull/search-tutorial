/**
 * S. Chatchawal 
 * May 19, 2561 BE 8:51:48 PM
 * http://www.conan.in.th
 * chatchawal.sangkeettrakarn@nectec.or.th, conan@conan.in.th
 */
package interfaces;

import java.util.ArrayList;

import model.News;

/**
 * @author conan
 *
 */
public interface NewsParser {
	public News getNews(String url);
	public ArrayList<News> getAllNews(String url);
}
