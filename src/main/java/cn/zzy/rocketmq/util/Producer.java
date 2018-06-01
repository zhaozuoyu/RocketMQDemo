package cn.zzy.rocketmq.util;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;

import java.util.UUID;

public class Producer {

    private DefaultMQProducer producer;
    protected String nameServer;
    protected String groupName;
    protected String topics;

    public Producer() {
    }

    public Producer(String nameServer, String groupName, String topics) {
        this.nameServer = nameServer;
        this.groupName = groupName;
        this.topics = topics;
    }

    public void init(){
        producer = new DefaultMQProducer(groupName);
        //指定NameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(nameServer);
        producer.setInstanceName(UUID.randomUUID().toString());
        try {
            /**
             * Producer对象在使用之前必须要调用start初始化，初始化一次即可
             * 注意：切记不可以在每次发送消息时，都调用start方法
             */
            producer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void send(Message message){
        message.setTopic(topics);
        try{
            //发送同步消息
            SendResult result = producer.send(message);
            //获取消息发送状态
            SendStatus status = result.getSendStatus();
            System.out.println("messageId=" + result.getMsgId() + ", status=" + status);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
