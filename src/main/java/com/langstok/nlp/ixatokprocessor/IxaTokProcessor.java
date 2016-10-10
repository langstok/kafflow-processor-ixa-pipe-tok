package com.langstok.nlp.ixatokprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.SendTo;

import ixa.kaflib.KAFDocument;


@EnableBinding(Sink.class)
public class IxaTokProcessor {
	
	@Autowired
	IxaTokService ixaTokService;
 
    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public KAFDocument handle(KAFDocument kaf) {
      return ixaTokService.transform(kaf);
    }
}