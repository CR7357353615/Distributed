package com.test.balancing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 负载均衡，随机算法
 * @author lho
 *
 */
public class Randoms {
	// 服务器地址以及权重的Map
	private static Map<String, Integer> serverWeightMap;
	public static String testRandoms() {
		// 重新创建一个map，避免出现由于服务器上线和下线导致的并发问题
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(serverWeightMap);
		
		Set<String> keySet = serverMap.keySet();
		ArrayList<String> keyList = new ArrayList<String>();
		keyList.addAll(keySet);
		
		// 计算位置
		Random random = new Random();
		int randomPos = random.nextInt(keyList.size());
		
		String server = keyList.get(randomPos);
		return server;
	}
}
