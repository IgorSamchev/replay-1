package play;

import org.apache.log4j.Log4jJava9Support;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import java.net.URL;

public class Logger {
    public static void init() {
        String log4jPath = Play.configuration.getProperty("application.log.path", "/log4j.xml");
        URL log4jConf = Logger.class.getResource(log4jPath);
        if (log4jConf == null) {
            log4jPath = Play.configuration.getProperty("application.log.path", "/log4j.properties");
            log4jConf = Logger.class.getResource(log4jPath);
        }
        if (log4jConf == null) {
            throw new RuntimeException("File " + log4jPath + " not found");
        }

        if (log4jPath.endsWith(".xml")) {
            DOMConfigurator.configure(log4jConf);
        } else {
            PropertyConfigurator.configure(log4jConf);
        }

        Log4jJava9Support.initMDC();
    }
}
