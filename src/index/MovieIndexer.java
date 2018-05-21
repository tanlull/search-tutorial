/**
 * S. Chatchawal 
 * May 21, 2561 BE 12:28:56 AM
 * http://www.conan.in.th
 * chatchawal.sangkeettrakarn@nectec.or.th, conan@conan.in.th
 */
package index;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.th.ThaiAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
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
		System.out.println("START");
		// read
		Scanner sc = new Scanner(new File("D:\\git\\search-tutorial\\movie.json"));
		
		String line = "";
		
		System.out.println(sc.toString());
		
		// ---------------- index-movies ---------------
		 java.nio.file.Path path = FileSystems.getDefault().getPath("index-movies");
		 Directory dir = FSDirectory.open(path);
		 
		 /* set analyzer to analyze contents */
		 // �Ѵ��  ThaiAnalyzer (java blade) or ICU
		 // Make indices 
		 Analyzer analyzer=new ThaiAnalyzer();
		 
		 IndexWriterConfig iwc=new IndexWriterConfig(analyzer);
		 
		 /* always replace old index */
		 // reindex all 
		 iwc.setOpenMode(OpenMode.CREATE); 
		 IndexWriter writer = new IndexWriter(dir,iwc);
		
		// -----------------------------------------
		int i =1;
		System.out.println(i);
		while(sc.hasNext()) {
			line = sc.nextLine();
			JSONObject m = new JSONObject(line);
			String title = m.getString("title");
			String url = m.getString("url");
			String synopsis = m.getString("synopsis");
			String format = m.getString("format");
			String genres = m.getString("genres").equals("null")?"":m.getString("genres");
			System.out.println(title+" "+format+" "+genres+" "+url);
			
			
			// Lucene Document 
			 Document d = new Document();
			 
			 // Add column & create index 
			 d.add(new Field("title", title, TextField.TYPE_STORED)); // TextField = Tokenize  -> search
			 d.add(new Field("url", url, TextField.TYPE_STORED));
			 d.add(new Field("synopsis", synopsis, TextField.TYPE_STORED)); // StringField = do not tokenize/search
			 d.add(new Field("format", format, TextField.TYPE_STORED));
			 d.add(new Field("genres", genres, TextField.TYPE_STORED));
			 
			 writer.addDocument(d);
			 System.out.println(i++);
			
		}
		sc.close();
		writer.close();
		System.out.println("END");

	}

}
