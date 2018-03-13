package com.test.balancing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 负载均衡，加权轮询算法
 * @author lho
 *
 */
public class WeightRoundRobin {
	// 服务器地址以及权重的Map
	private static Map<String, Integer> serverWeightMap;
	// 轮询位置
	private static int pos;
	public static String testWeightRoundRobin() {
		// 重新创建一个map，避免出现由于服务器上线和下线导致的并发问题
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(serverWeightMap);
		
		Set<String> keySet = serverMap.keySet();
		Iterator<String> it = keySet.iterator();
		
		ArrayList<String> serverList = new ArrayList<String>();
		
		// 权重大的服务器，会多次add进serverList中，这样被轮询到的机会就越多。
		while(it.hasNext()) {
			String server = it.next();
			Integer weight = serverMap.get(server);
			for(int i = 0; i < weight; i++) {
				serverList.add(server);
			}
		}
		
		String server = null;
		
		synchronized (WeightRoundRobin.class) {
			// 如果大于当前服务器列表的数量，重置为0
			if(pos >= keySet.size()) {
				pos = 0;
			}
			// 获取服务器
			server = serverList.get(pos);
			pos++;
		}
		return server;
	}
}
