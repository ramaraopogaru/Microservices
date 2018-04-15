package com.dotridge.proxy;

import org.springframework.cloud.stream.annotation.Input;

import org.springframework.messaging.MessageChannel;

public interface SearchSink {

String QNAME = "doctorQ";
	
	@Input("doctorQ")
	public MessageChannel doctorQ();
	
}
