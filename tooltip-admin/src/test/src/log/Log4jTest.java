package log;


import com.qxcwl.tooltip.common.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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

	private static Logger log1 = LoggerFactory.getLogger("Database");

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		String sss = (String) Class.forName("java.lang.String").newInstance();
	}
}
