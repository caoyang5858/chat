package com.chat.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NowTime {
   private static final NowTime now= new NowTime();	
   private SimpleDateFormat sdf;
   private NowTime(){
	    sdf = new SimpleDateFormat("HH:mm:ss");
   }
   public String getTime(){
	   Date date = new Date();
	   return sdf.format(date);
   }
   
   public static final NowTime getInstance(){
	   return now;
   }
}
