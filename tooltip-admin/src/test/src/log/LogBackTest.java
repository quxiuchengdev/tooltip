package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 曲修成
 * @className LogBackTest
 * @description
 * @date 2017-01-03 14:05:00
 */
public class LogBackTest {
	private static Logger log = LoggerFactory.getLogger(LogBackTest.class);
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			log.trace("======trace");
			log.debug("======debug");
			log.info("======info");
			log.warn("======warn");
			log.error("======error");
			Thread.sleep(1000*30);
		}

	}
}
