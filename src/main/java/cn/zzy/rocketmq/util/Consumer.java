package cn.zzy.rocketmq.util;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;

import java.util.UUID;

public class Consumer {

    private DefaultMQPushConsumer consumer;
    private MessageListener listener;
    protected String nameServer;
    protected String groupName;
    protected String topics;

    public Consumer() {
    }

    public Consumer(MessageListener listener, String nameServer, String groupName, String topics) {
        this.listener = listener;
        this.nameServer = nameServer;
        this.groupName = groupName;
        this.topics = topics;
    }

    public void init(){
        consumer = new DefaultMQPushConsumer(groupName);
        //指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr(nameServer);
        try {
            consumer.subscribe(topics, "*");
        }catch (Exception e){
            e.printStackTrace();
        }
        consumer.setInstanceName(UUID.randomUUID().toString());
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.registerMessageListener((MessageListenerConcurrently) this.listener);
        try {
            consumer.start();
        }catch (Exception e){	
            e.printStackTrace();
        }
        System.out.println("RocketMQConsumer Started! group=" + consumer.getConsumerGroup() + " instance=" + consumer.getInstanceName()  );
    }
}
