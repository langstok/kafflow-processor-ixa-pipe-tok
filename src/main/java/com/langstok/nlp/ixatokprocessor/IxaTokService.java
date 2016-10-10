package com.langstok.nlp.ixatokprocessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import eus.ixa.ixa.pipe.tok.Annotate;
import eus.ixa.ixa.pipe.tok.CLI;
import ixa.kaflib.KAFDocument;


@Service
@EnableConfigurationProperties(TokProperties.class)
public class IxaTokService {

	private final static Logger LOGGER = Logger.getLogger(IxaTokService.class);

	@Autowired
	TokProperties tokProperties;

	private final String version = CLI.class.getPackage()
			.getImplementationVersion();
	/**
	 * Get the commit of ixa-pipe-tok by looking at the MANIFEST file.
	 */
	private final String commit = CLI.class.getPackage()
			.getSpecificationVersion();


	public KAFDocument transform(KAFDocument document){
		LOGGER.info("KAF TOK processing start (publicId / uri): " 
				+ document.getPublic().publicId + " / " + document.getPublic().uri);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {
			LOGGER.info("Tokenize: " + document.getFileDesc().title);
			document = annotate(document);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		} catch (JDOMException e) {
			LOGGER.error("JDOMException", e);
		}
		stopWatch.stop();
		LOGGER.info("KAF TOK processing finished (time ms / publicId / uri): " 
				+ stopWatch.getTotalTimeMillis() + " / " + document.getPublic().publicId + " / " + document.getPublic().uri);
		return document;
	}

	public final KAFDocument annotate(KAFDocument kaf) throws IOException, JDOMException	{
		
		final String normalize = tokProperties.getNormalize();
		final String lang = tokProperties.getLanguage();
		final String untokenizable = tokProperties.getUntokenizable();
		final String hardParagraph = tokProperties.getHardParagraph();
		final Properties properties = setAnnotateProperties(lang, normalize, untokenizable, hardParagraph);
		final String text = kaf.getRawText();
		final StringReader stringReader = new StringReader(text);
		BufferedReader breader = new BufferedReader(stringReader);
		
		final Annotate annotator = new Annotate(breader, properties);
		final KAFDocument.LinguisticProcessor newLp = kaf
				.addLinguisticProcessor("text", "ixa-pipe-tok-" + lang, version
						+ "-" + commit);
		newLp.setBeginTimestamp();
		annotator.tokenizeToKAF(kaf);
		newLp.setEndTimestamp();
		breader.close();
		return kaf;
	}



	private Properties setAnnotateProperties(final String lang, final String normalize, final String untokenizable, final String hardParagraph) {
		final Properties annotateProperties = new Properties();
		annotateProperties.setProperty("language", lang);
		annotateProperties.setProperty("normalize", normalize);
		annotateProperties.setProperty("untokenizable", untokenizable);
		annotateProperties.setProperty("hardParagraph", hardParagraph);
		return annotateProperties;
	}

}
