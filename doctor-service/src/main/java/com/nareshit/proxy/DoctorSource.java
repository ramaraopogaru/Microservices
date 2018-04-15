package com.nareshit.proxy;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface DoctorSource {

	String QNAME = "doctorQ";
	
	@Output("doctorQ")
	public MessageChannel doctorQ();
	
}
