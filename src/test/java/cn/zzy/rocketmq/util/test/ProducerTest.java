package cn.zzy.rocketmq.util.test;


import cn.zzy.rocketmq.pojo.User;
import cn.zzy.rocketmq.util.Producer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.common.message.Message;

public class ProducerTest {

    public static void main(String[] args) {
        String mqNameServer = "127.0.0.1:9876";
        String mqTopics = "MQ-MSG-TOPICS-TEST";
        String producerMqGroupName = "CONSUMER-MQ-GROUP";
        Producer producer = new Producer(mqNameServer,producerMqGroupName,mqTopics);
        producer.init();
        for (int i =0;i<5;i++){
            Message message = new Message();
            User user = new User("90001","mark","123.com",22,'男');
            String userStr = JSON.toJSONString(user);
            //message.setBody(("I send message to RockectMQ "+i).getBytes());
            message.setBody(userStr.getBytes());
            producer.send(message);
        }
    }
}
