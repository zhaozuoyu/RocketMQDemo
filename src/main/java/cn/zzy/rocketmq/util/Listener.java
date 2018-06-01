package cn.zzy.rocketmq.util;

import cn.zzy.rocketmq.pojo.User;
import cn.zzy.serializable.util.SerializeUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

public class Listener implements MessageListenerConcurrently {

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
			ConsumeConcurrentlyContext consumeConcurrentlyContext) {
		/*for (MessageExt messageExt : list) {
			try {
				String msg = new String(messageExt.getBody());
				System.out.println("message data from rockectMQ:" + msg);
			} catch (Exception e) {
				e.printStackTrace();
				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
			}
			
		}*/
		showList(list, consumeConcurrentlyContext);
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

	private ConsumeConcurrentlyStatus showList(List<MessageExt> list,
			ConsumeConcurrentlyContext consumeConcurrentlyContext) {
		for (MessageExt messageExt : list) {
			String msg = new String(messageExt.getBody());
			// String msg = messageExt.getBody().toString();
			User user = JSON.parseObject(msg, User.class);
			System.out.println(user.getUserCode() + "--" + user.getUserName() + "--" + user.getPassWord() + "--"
					+ user.getAge() + "--" + user.getSex());
		}
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

}
