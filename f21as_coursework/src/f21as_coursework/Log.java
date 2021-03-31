package f21as_coursework;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.Logger;

public class Log {
	
	private static Log instance;
	private static Log logger;
	//private final static Logger logger = Logger.getLogger(Log.class.getName());
	
	private static FileHandler handler; // = null;
	SimpleFormatter plainText;		//format log messages in plain text
	
	
	public static void Log() {
		try {
			handler = new FileHandler("cafeLog.txt", true);		//write logs to file, updating same file
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Logger log = Logger.getLogger("");
		handler.setFormatter(new SimpleFormatter());
		log.addHandler(handler);
		log.setUseParentHandlers(false);
	}
	
	
	//lazy initialisation 
	//accessible everywhere
	public static Log getInstance() {
		if (instance == null) 				//only if no instance
			synchronized(Log.class) {		//lock block
				if (instance == null) 		//re-check
					instance = new Log();
			}
		return instance;
	}


	/*
	public void log(Level info, String string) {
		logger.log(info, string);
		
	}

	*/

	
}