package edu.fyp.search;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.snowball.SnowballAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

public class SearchJanitorUtils {

	private static final Logger log = Logger.getLogger(SearchJanitorUtils.class
			.getName());

	/** From StopAnalyzer Lucene 2.9.1 */
	public final static String[] stopWords = new String[] { "a", "an", "and",
			"are", "as", "at", "be", "but", "by", "for", "if", "in", "into",
			"is", "it", "no", "not", "of", "on", "or", "such", "that", "the",
			"their", "then", "there", "these", "they", "this", "to", "was",
			"will", "with" };

	/**
	 * Uses english stemming (snowball + lucene) + stopwords for getting the
	 * words.
	 * 
	 * @param index
	 * @return
	 */
	public static Set<String> getTokensForIndexingOrQuery(String index_raw,
			int maximumNumberOfTokensToReturn) {

		String indexCleanedOfHTMLTags = index_raw.replaceAll("\\<.*?>", " ");

		Set<String> returnSet = new HashSet<String>();

		try {

			Analyzer analyzer = new EnglishAnalyzer(
					org.apache.lucene.util.Version.LUCENE_43);

			TokenStream tokenStream = analyzer.tokenStream("content",
					new StringReader(indexCleanedOfHTMLTags));

			OffsetAttribute offsetAttribute = tokenStream
					.addAttribute(OffsetAttribute.class);
			CharTermAttribute charTermAttribute = tokenStream
					.addAttribute(CharTermAttribute.class);
			tokenStream.reset();
			Token token = new Token();
			while ((tokenStream.incrementToken())
					&& (returnSet.size() < maximumNumberOfTokensToReturn)) {

				returnSet.add(charTermAttribute.toString());

			}

		} catch (IOException e) {
			log.severe(e.getMessage());
		}

		return returnSet;

	}

}