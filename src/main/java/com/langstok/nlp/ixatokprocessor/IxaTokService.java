package com.langstok.nlp.ixatokprocessor;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import ixa.kaflib.KAFDocument;

@Service
public class IxaTokService {
	
	private final static Logger LOGGER = Logger.getLogger(IxaTokService.class);
	
	public KAFDocument transform(KAFDocument document){
		LOGGER.info("KAF TOK processing start (publicId / uri): " 
				+ document.getPublic().publicId + " / " + document.getPublic().uri);
		long time = System.currentTimeMillis();
		LOGGER.info(document.getFileDesc().title);
		time = System.currentTimeMillis() - time;
		LOGGER.info("KAF TOK processing finished (time ms / publicId / uri): " 
				+ time + " / " + document.getPublic().publicId + " / " + document.getPublic().uri);
		return document;
	}

}
