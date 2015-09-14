package com.plum.notification;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class SubscriberMSGListener implements MessageListener {

	private String job;
	
	public SubscriberMSGListener(String job) {
		this.job = job;
	}

	public void onMessage(Message message) {
		try {
			System.out.println(job);
			if (message instanceof TextMessage) {
				TextMessage txtMsg = (TextMessage) message;
				String msg = txtMsg.getText();
				System.out.println("Consumer:->Received: " + msg);
			} else {
				System.out.println("Consumer:->Received: " + message);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

