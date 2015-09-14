package com.plum.notification;

import com.plum.constant.CONST;
import com.plum.core.common.AbstractLifeBean;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

import javax.jms.*;
import javax.jms.Queue;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by G_dragon on 2015/9/11.
 */
public class ProducerTool extends AbstractLifeBean {

    private static final Logger logger = LoggerFactory.getLogger(ProducerTool.class);

    private ActiveMQConnectionFactory connectionFactory;

    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    private javax.jms.Connection connection;
    private Session session;

    /** 读写锁 **/
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock   = readWriteLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    private Map<String, Destination> destinationMap = new HashMap<>();
    private Map<String, MessageProducer> producerMap = new HashMap<>();

    private Set<String> destinations = new HashSet<>();

    public ActiveMQConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ActiveMQConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    // 初始化
    private void initialize() {
        try {
            if(null == connectionFactory)
                connectionFactory = new ActiveMQConnectionFactory(user, password, url);

            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            parseDestinations();

            connection.start();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
                Integer mode = Integer.valueOf(destConfigArray[2]);

                try {
                    addDestination(name, type, mode);
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
     * @param deliveryMode 传送模式(0-PERSISTENT（持久性消息）或1-NON_PERSISTENT（非持久性消息）)
     * @throws JMSException
     */
    public void addDestination(String destinationName, int queueOrTopic, int deliveryMode) throws JMSException{
        if(queueOrTopic == CONST.MQ_PIPE_TYPE_QUEUE){
            addQueue(destinationName, deliveryMode);
        }else{
            addTopic(destinationName, deliveryMode);
        }
    }

    private void addQueue(String queueName, int deliveryMode) throws JMSException{
        Queue queue = session.createQueue(queueName);
        addDestination(queue, queueName, deliveryMode);
    }

    private void addTopic(String topicName, int deliveryMode) throws JMSException{
        Topic topic = session.createTopic(topicName);
        addDestination(topic, topicName, deliveryMode);
    }

    private void addDestination(Destination destination, String destinationName, int deliveryMode) throws JMSException{
        MessageProducer producer = session.createProducer(destination);
        if(deliveryMode == 1){
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        }else{
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        }

        destinationMap.put(destinationName, destination);
        producerMap.put(destinationName, producer);
    }

    public  MessageProducer getProducer	(String destinationName){
        return producerMap.get(destinationName);
    }

    // 发送消息
    public void sendBuilder(String destinationName) throws JMSException, Exception {
        BytesMessage msg = session.createBytesMessage();
        //msg.writeBytes(builder.build().toByteArray());

        MessageProducer producer=producerMap.get(destinationName);
        producer.send(msg);
    }

    // 发送消息
    public void sendBytesMessage(String destinationName,Object message) throws JMSException, Exception {
        byte[] data =  (byte[])message;
        BytesMessage msg = session.createBytesMessage();
        msg.writeBytes(data);
        MessageProducer producer=producerMap.get(destinationName);
        System.out.println("Producer:->Sending message: " + message);
        producer.send(msg);
        System.out.println("Producer:->Message sent complete!");
    }

    // 发送消息
    public void sendTextMessage(String destinationName,String message) throws JMSException, Exception {
        TextMessage msg = session.createTextMessage(message);
        MessageProducer producer=producerMap.get(destinationName);
        System.out.println("Producer:->Sending message: " + message);
        producer.send(msg);
        System.out.println("Producer:->Message sent complete!");
    }
    /**
     * send 发送消息到MQ服务器
     * @param destination Queue或Topic
     * @param message  要发送的消息内容
     * @param deliveryMode 传送模式(0-PERSISTENT（持久性消息）或1-NON_PERSISTENT（非持久性消息）)
     * @param priority 消息优先级
     * @param timeToLive 消息过期时间
     * @throws JMSException
     */
    public void sendMessage(Destination destination, Message message, int deliveryMode, int priority,long timeToLive) throws JMSException{
        MessageProducer producer = session.createProducer(destination);
        producer.send(message, deliveryMode, priority, timeToLive);
    }
    /**
     * send 发送消息到MQ服务器
     * @param name 消息对列的名称
     * @param message 要发送的消息内容
     * @param deliveryMode 传送模式(0-PERSISTENT（持久性消息）或1-NON_PERSISTENT（非持久性消息）)
     * @param priority 消息优先级
     * @param timeToLive 消息过期时间
     * @throws JMSException
     */
    public void sendMessage(String name, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException{
        MessageProducer producer=producerMap.get(name);
        producer.send(message, deliveryMode, priority, timeToLive);
    }
    /**
     * send 发送消息到MQ服务器
     * @param name 消息对列的名称
     * @param message 要发送的消息内容(文本方式)
     * @param deliveryMode 传送模式(0-PERSISTENT（持久性消息）或1-NON_PERSISTENT（非持久性消息）)
     * @param priority 消息优先级
     * @param timeToLive 消息过期时间
     * @throws JMSException
     */
    public void sendStringMessage(String name,String message, int deliveryMode, int priority, long timeToLive) throws JMSException{
        TextMessage msg = session.createTextMessage(message);
        MessageProducer producer=producerMap.get(name);
        producer.send(msg, deliveryMode, priority, timeToLive);
    }
    /**
     * send 发送Object消息到MQ服务器
     * @param name 消息对列的名称
     * @param message 要发送的消息内容(对象方式)
     * @param deliveryMode 传送模式(0-PERSISTENT（持久性消息）或1-NON_PERSISTENT（非持久性消息）)
     * @param priority 消息优先级
     * @param timeToLive 消息过期时间
     * @throws JMSException
     */
    public void sendObjectMeaage(String name,Object message, int deliveryMode, int priority, long timeToLive) throws JMSException{
        byte[] data =  (byte[])message;
        BytesMessage msg = session.createBytesMessage();
        msg.writeBytes(data);
        MessageProducer producer=producerMap.get(name);
        producer.send(msg, deliveryMode, priority, timeToLive);
    }

    /**
     * 发送键值对数据
     *
     * @param msgMap
     * @param topicName
     */
    public void sendMessage(Map<String, String> msgMap, String topicName) {

        try {
            MessageProducer producer=producerMap.get(topicName);
            MapMessage mapMessage = session.createMapMessage();
            for (Map.Entry<String, String> entity : msgMap.entrySet()) {
                mapMessage.setString(entity.getKey(), entity.getValue());
            }
            producer.send(mapMessage);
        } catch (Exception e) {

        }
    }

    // 关闭连接
    public void uninitialize() throws JMSException {

        logger.debug("Producer:->Closing connection");

        for (Map.Entry<String, MessageProducer> entry : producerMap.entrySet()) {
            MessageProducer producer=entry.getValue();
            producer.close();
        }
        if(destinationMap!=null && destinationMap.size()>0){
            destinationMap.clear();
            destinationMap=null;
        }
        if(producerMap!=null && producerMap.size()>0){
            producerMap.clear();
            producerMap=null;
        }
        if (session != null){
            session.close();
        }
        if (connection != null){
            connection.close();
        }
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

    public Set<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(Set<String> destinations) {
        this.destinations = destinations;
    }
}