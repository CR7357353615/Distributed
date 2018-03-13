package com.test.balancing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 负载均衡，加权随机算法
 * @author lho
 *
 */
public class WeightRandom {
	// 服务器地址以及权重的Map
	private static Map<String, Integer> serverWeightMap;
	public static String testWeightRandom() {
		// 重新创建一个map，避免出现由于服务器上线和下线导致的并发问题
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(serverWeightMap);
		
		Set<String> keySet = serverMap.keySet();
		Iterator<String> it = keySet.iterator();
		
		ArrayList<String> serverList = new ArrayList<String>();
		
		// 权重大的服务器，会多次add进serverList中，这样被定位到的机会就越多。
		while(it.hasNext()) {
			String server = it.next();
			Integer weight = serverMap.get(server);
			for(int i = 0; i < weight; i++) {
				serverList.add(server);
			}
		}
		
		// 计算位置
		Random random = new Random();
		int randomPos = random.nextInt(serverList.size());
		
		String server = serverList.get(randomPos);
		return server;
	}
}
