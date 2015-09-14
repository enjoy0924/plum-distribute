package com.plum.notification;


public class ConsumerThread implements Runnable {

	private ConsumerTool consumerTool;

	public ConsumerThread(ConsumerTool consumerTool){
		this.consumerTool = consumerTool;
	}

	public void run() {
		try {
			if(null != consumerTool){
				consumerTool.consumeMessage();
				while (consumerTool.isConnectioned()) {
					;
				}
			}

		} catch (Exception e) {
		}
	}
}