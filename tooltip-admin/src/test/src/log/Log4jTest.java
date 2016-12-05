package log;


import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.util.Enumeration;
import java.util.logging.LogManager;

/**
 * @Author 曲修成
 * @ClassName Log4jTest
 * @Description
 * @Date 2016-11-15 11:49:00
 */
public class Log4jTest {

	private static Logger log1 = Logger.getLogger("Database");

	public static void main(String[] args) {

		log1.info("aaa");
	}
}
