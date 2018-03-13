package com.test.balancing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 负载均衡，原地址hash算法
 * @author lho
 *
 */
public class ConsumerHash {
	// 服务器地址以及权重的Map
	private static Map<String, Integer> serverWeightMap;
	// 远程ip
	private static String remoteip;
	public static String testConsumerHash() {
		// 重新创建一个map，避免出现由于服务器上线和下线导致的并发问题
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(serverWeightMap);
		
		Set<String> keySet = serverMap.keySet();
		ArrayList<String> keyList = new ArrayList<String>();
		keyList.addAll(keySet);
		
		// 计算hash值求服务器位置
		int hashCode = remoteip.hashCode();
		int serverListSize = keyList.size();
		int serverPos = hashCode % serverListSize;
		
		return keyList.get(serverPos);
	}
}
