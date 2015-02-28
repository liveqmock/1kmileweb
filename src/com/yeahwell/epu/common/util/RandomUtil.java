package com.yeahwell.epu.common.util;

import java.util.Random;

public class RandomUtil {

	private RandomUtil() {

	}

	/**
	 * 获取特定范围内的随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandom(final int min, final int max) {
		final Random random = new Random();
		return (random.nextInt(max) % ((max - min) + 1)) + min;
	}

}
