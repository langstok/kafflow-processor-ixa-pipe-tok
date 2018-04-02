package com.langstok.nlp.ixatok;

import ixa.kaflib.KAFDocument;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;


@EnableBinding(Processor.class)
public class IxaTokProcessor {

	private IxaTokService ixaTokService;

	public IxaTokProcessor(IxaTokService ixaTokService) {
		this.ixaTokService = ixaTokService;
	}

	@ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public KAFDocument handle(KAFDocument kaf) {
		return ixaTokService.transform(kaf);
	}
}