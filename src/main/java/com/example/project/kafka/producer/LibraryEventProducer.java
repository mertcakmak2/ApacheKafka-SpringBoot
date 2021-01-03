package com.example.project.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


import com.example.project.kafka.domain.LibraryEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.*;

@Component
@Slf4j
public class LibraryEventProducer {
	
	@Autowired
	KafkaTemplate<Integer, String> kafkaTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public void sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {
		
		Integer key = libraryEvent.getLibraryEventId();
		String value = objectMapper.writeValueAsString(libraryEvent);
		
//		ListenableFuture<SendResult<Integer, String>> listenableFuture =  kafkaTemplate.sendDefault(key, value);
		ListenableFuture<SendResult<Integer,String>> listenableFuture =  kafkaTemplate.send("library-events", value);
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

			@Override
			public void onSuccess(SendResult<Integer, String> result) {
				handleSuccess(key, value, result);				
			}
			
			@Override
			public void onFailure(Throwable ex) {
				handleFailure(key, value, ex);
			}

		});
	}
	private void handleFailure(Integer key, String value, Throwable ex) {
		System.out.println("Error sending the message: exception is "+ex.getMessage());
		try {
			throw ex;
		} catch (Throwable e) {
			System.out.println("Error "+e.getMessage());
		}
	}
	
	private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
		System.out.println("Success: Message sent success for the key: "+key+ " "
				+ "and the value is "+value+" , "
						+ "patition is a "+result.getRecordMetadata().partition());
	}
}
