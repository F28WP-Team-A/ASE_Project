package f21as_coursework;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CurrentTime {
		public static Timestamp getCurrentTime() throws Exception{
			String strFormat = new String("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formater = new SimpleDateFormat(strFormat);
			java.util.Date theDate= new java.util.Date();
			theDate = (java.util.Date) formater.parse(formater.format(theDate));
			Timestamp returnStamp = new Timestamp(theDate.getTime());
			return returnStamp;
		};

}
