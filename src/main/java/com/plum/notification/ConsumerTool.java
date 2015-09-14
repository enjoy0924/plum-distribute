package com.plum.notification;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import com.plum.constant.CONST;
import com.plum.core.common.AbstractLifeBean;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationEvent;

public class ConsumerTool extends AbstractLifeBean implements MessageListener, ExceptionListener {

	private String user = ActiveMQConnection.DEFAULT_USER;
	private String password = ActiveMQConnection.DEFAULT_PASSWORD;
	private String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	private String clientId;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	private ActiveMQConnectionFactory connectionFactory;
	public ActiveMQConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ActiveMQConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}


	private Connection connection;
	private Session session;

	public boolean isConnectioned() {
		return isConnectioned;
	}

	private boolean isConnectioned;

	private Set<String> destinations;

	public Set<String> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<String> destinations) {
		this.destinations = destinations;
	}


	private Map<String, Destination> destinationMap = new HashMap<>();
	private Map<String, MessageConsumer> consumerMap = new HashMap<>();

	// 初始化
	private void initialize() {

		try {
			if(null == connectionFactory)
				connectionFactory = new ActiveMQConnectionFactory(user, password, url);
			connection = connectionFactory.createConnection();
			if(null != clientId && !clientId.isEmpty())
				connection.setClientID(clientId);
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			parseDestinations();

			connection.start();
			
			
		} catch (JMSException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseDestinations(){

		if(null != destinations && !destinations.isEmpty()){
			for(String destination : destinations){
				String[] destConfigArray = destination.split(":");
				if(null == destConfigArray || destConfigArray.length==0)
					continue;

				String name = destConfigArray[0];
				Integer type = Integer.valueOf(destConfigArray[1]);

				try {
					addQueueOrTopic(name, type, new SubscriberMSGListener(name));
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 
	 * @param destinationName
	 * @param queueOrTopic 1-Queue,0-Topic
	 * @throws JMSException
	 */
	public void addQueueOrTopic(String destinationName, int queueOrTopic,MessageListener listener) throws JMSException{
		if(queueOrTopic == CONST.MQ_PIPE_TYPE_QUEUE){
			addQueue(destinationName,listener);
		}else{
			addTopic(destinationName,listener);
		}
	}
	
	private void addQueue(String queueName,MessageListener listener) throws JMSException{
		Queue queue = session.createQueue(queueName);

		addDestination(queue, queueName,listener);
	}
	
	private void addTopic(String topicName,MessageListener listener) throws JMSException{
		Topic topic = session.createTopic(topicName);
		addDestination(topic, topicName,listener);
	}
	/**
	 * addDestination
	 * 添加目标对象，即创建Queue或Topic对象
	 * @param destination Queue或Topic
	 * @param destinationName Queue或Topic的名称
	 * @throws JMSException
	 */
	private void addDestination(Destination destination, String destinationName,MessageListener listener) throws JMSException{
		MessageConsumer consumer = session.createConsumer(destination);  //创建普通订阅
		//MessageConsumer consumer = session.createDurableSubscriber(destination, "ANDY");   //Topic的持久化订阅
		if(null == listener){
			consumer.setMessageListener(this);
		}else{
			consumer.setMessageListener(listener);
		}
		destinationMap.put(destinationName, destination);
		consumerMap.put(destinationName, consumer);
	}
	
	public  MessageConsumer getConsumer	(String destinationName){
		return consumerMap.get(destinationName);
	}

	/**
	 * 设置客户端消息状态
	 */
	public void consumeMessage() {
		try {
			connection.setExceptionListener(this);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		isConnectioned = true;
		System.out.println("Consumer:->Begin listening...");
	}

	/**
	 * 反初始化
	 * @throws JMSException
	 */
	public void uninitialize() throws JMSException {
		System.out.println("Consumer:->Closing connection");
		for (Entry<String, MessageConsumer> entry : consumerMap.entrySet()) {
			MessageConsumer pb=entry.getValue();
			pb.close();
		}
		if(destinationMap!=null && destinationMap.size()>0){
			destinationMap.clear();
			destinationMap=null;
		}
		if(consumerMap!=null && consumerMap.size()>0){
			consumerMap.clear();
			consumerMap=null;
		}
		if (session != null)
			session.close();
		if (connection != null)
			connection.close();
	}

	// 消息处理函数
	public void onMessage(Message message) {
		try {
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

	public void onException(JMSException arg0) {
		isConnectioned = false;
	}

	@Override
	protected void onBootstrap(ApplicationEvent event) {
		initialize();
	}

	@Override
	protected void onShutdown(ApplicationEvent event) {
		try {
			uninitialize();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}