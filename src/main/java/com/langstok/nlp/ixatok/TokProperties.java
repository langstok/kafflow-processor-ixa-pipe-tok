package com.langstok.nlp.ixatok;

import javax.validation.constraints.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class TokProperties {
			
	/**
	 * Set normalization method according to corpus; the default option does not escape brackets or forward slashes. See README for more details.
	 * "alpino", "ancora", "ctag", "default", "ptb", "tiger", "tutpenn"
	 */
	private String normalize = "default";
	
	/**
	 * Print untokenizable characters
	 */
	private String untokenizable = "no";
	
	
	/**
	 * Set kaf document version
	 */
	private String kafversion =  "v1.naf";
			
	/**
	 * Use this option if input is a KAF/NAF document with <raw> layer. (true)"
	 */
	private Boolean inputkaf = true;
	
	/**
	 * Build a KAF document from an already tokenized sentence per line file. (true)
	 */
	private Boolean notok = false;
	
	/**
	 * Choose output format; it defaults to NAF
	 * "conll", "oneline", "naf"
	 */
	private String outputFormat = "naf";
	
	/**
	 * Do not print offset and lenght information of tokens in CoNLL format.
	 */
	private Boolean offsets = false;
	
	/**
	 * Do not segment paragraphs. Ever
	 */
	private String hardParagraph = "no";

	@Pattern(regexp = "(?i)(alpino|ancora|ctag|default|ptb|tiger|tutpenn)")
	public String getNormalize() {
		return normalize;
	}
	
	private String language = "en";

	public void setNormalize(String normalize) {
		this.normalize = normalize;
	}

	public String getUntokenizable() {
		return untokenizable;
	}

	public void setUntokenizable(String untokenizable) {
		this.untokenizable = untokenizable;
	}

	public String getKafversion() {
		return kafversion;
	}

	public void setKafversion(String kafversion) {
		this.kafversion = kafversion;
	}

	public Boolean getInputkaf() {
		return inputkaf;
	}

	public void setInputkaf(Boolean inputkaf) {
		this.inputkaf = inputkaf;
	}

	public Boolean getNotok() {
		return notok;
	}	

	public void setNotok(Boolean notok) {
		this.notok = notok;
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	public Boolean getOffsets() {
		return offsets;
	}

	public void setOffsets(Boolean offsets) {
		this.offsets = offsets;
	}

	public String getHardParagraph() {
		return hardParagraph;
	}

	public void setHardParagraph(String hardParagraph) {
		this.hardParagraph = hardParagraph;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
