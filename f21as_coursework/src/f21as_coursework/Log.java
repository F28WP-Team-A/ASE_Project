package f21as_coursework;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Log {
	
	private static Log instance;


	private static FileHandler fh; //= null;
	SimpleFormatter plainText;				//format text to human readable
	
	
	public static void Log() {
		try {
			fh = new FileHandler("cafeLog.txt", true);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		Log log = Log.getLogger("");
		fh.setFormatter(new SimpleFormatter());
		log.addHandler(fh);
		log.setUseParentHandlers(false);			
	}



	//Lazy initialisation used
	//accessible everywhere 
	public static Log getInstance() throws IOException {
		if (instance == null) 	//only if no instance
			synchronized(Log.class) {	//lock block
				if (instance == null)	//re-check
					instance = new Log();
			}
		return instance;
	}

	
}