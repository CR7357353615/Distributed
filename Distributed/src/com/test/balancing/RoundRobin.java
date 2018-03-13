package com.test.balancing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 负载均衡，轮询算法
 * @author lho
 *
 */
public class RoundRobin {
	// 服务器地址以及权重的Map
	private static Map<String, Integer> serverWeightMap;
	// 轮询位置
	private static int pos;
	public static String testRoundRobin() {
		// 重新创建一个map，避免出现由于服务器上线和下线导致的并发问题
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(serverWeightMap);
		
		Set<String> keySet = serverMap.keySet();
		ArrayList<String> keyList = new ArrayList<String>();
		keyList.addAll(keySet);
		
		String server = null;
		synchronized (RoundRobin.class) {
			// 如果大于当前服务器列表的数量，重置为0
			if(pos >= keySet.size()) {
				pos = 0;
			}
			// 获取服务器
			server = keyList.get(pos);
			pos++;
		}
		return server;
	}
}
