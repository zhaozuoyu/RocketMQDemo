package cn.zzy.rocketmq.util.test;

import cn.zzy.rocketmq.util.Consumer;
import cn.zzy.rocketmq.util.Listener;

public class ConsumerTest {

    public static void main(String[] args) {
        String mqNameServer = "127.0.0.1:9876";
        String mqTopics = "MQ-MSG-TOPICS-TEST";
        String consumerMqGroupName = "CONSUMER-MQ-GROUP";
        Listener listener = new Listener();
        Consumer consumer = new Consumer(listener,mqNameServer,consumerMqGroupName,mqTopics);
        consumer.init();
        try{
            Thread.sleep(1000*60);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
