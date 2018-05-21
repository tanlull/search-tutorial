/**
 * S. Chatchawal 
 * May 20, 2561 BE 1:58:19 AM
 * http://www.conan.in.th
 * chatchawal.sangkeettrakarn@nectec.or.th, conan@conan.in.th
 */
package search;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.th.ThaiAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParser.Operator;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


/**
 * @author conan
 *
 */
public class MySimpleSearcher {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub

		
		Path path = FileSystems.getDefault().getPath("index-news");
		Directory dir = FSDirectory.open(path);
		IndexReader r = DirectoryReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(r);

		 // Must be the same Analyzer when we've created
		Analyzer analyzer = new ThaiAnalyzer();

		// match term in content field
		QueryParser qp = new QueryParser("content",analyzer);
		qp.setDefaultOperator(Operator.AND); // Search every word (in case searching word is seperatered by Analyzer)
		// Searching word
		String keyword ="äÍâ¿¹";
		Query query = qp.parse(keyword);
		
		QueryParser qp2 = new QueryParser("title",analyzer);
		qp2.setDefaultOperator(Operator.AND); // Search every word (in case searching word is seperatered by Analyzer)
		// Searching word
		Query query2 = qp.parse(keyword);
		
		//Exactly Match : for String 
		TermQuery tq = new TermQuery(new Term("publisher",keyword));

		//Search many fields
		Builder bd = new BooleanQuery.Builder();
		
		// combine Search
		bd.add(query,Occur.MUST);
		bd.add(query2,Occur.SHOULD);
		bd.add(tq,Occur.SHOULD);
		
		
		
		// Maximun number of results --> 5 top results
		//TopDocs tops = searcher.search(query, 5);
		TopDocs tops = searcher.search(bd.build(), 5);

		System.out.println(bd.build().toString());
		
		
		System.out.println("Keyword = '"+keyword+"\', Founds: "+tops.totalHits + " Results");
		//searcher.se
		ScoreDoc[] sd = tops.scoreDocs;

		for(ScoreDoc s : sd){
				Document d = searcher.doc(s.doc);
				String title = d.get("title");
				String content = d.get("content");
				String url = d.get("url");
				String date = d.get("datetime");
				System.out.println(title+" "+date+" "+url);
		}
			
		r.close();
		
		
		
		
		
		
	}
	
	

}
