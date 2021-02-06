package com.revature;

import org.slf4j.Logger;
//import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Driver {
	
//	private static Logger log = Logger.getLogger(Driver.class);
	private static Logger log = LoggerFactory.getLogger(Driver.class);
	// We have access to the Logger interface from both Log4J as well as through SLF4J (Simple Logging Facade for Java)
	// Generally, you use one of these Factory method to obtain the instance of your logger
	
	/*
	 * We have several "Logging Levels"
	 * 
	 * OFF: No logging at all
	 * ALL: Turns on all levels of logging
	 * TRACE: Adds more information to our DEBUG logs
	 * DEBUG: Primarily a logging level that is helpful for Developers (turn on all of the following levels)
	 * INFO: Represent important business processes, used for defining when they have completed
	 * 		and that the current application state is "as expected"
	 * 		Generally speaking, System Administrators as well as similar roles (such as SREs) will monitor INFO logs to track
	 * 		the Application Status
	 * 
	 * WARN: Suggest that the application might be continued, but you should take extra caution
	 * ERROR: Shouting that something has gone very wrong and should be investigated immediately
	 * FATAL: Very rare, but signals that something is terribly wrong, and will likely result in application failure
	 */

	public static void main(String[] args) {
		
		log.info("The application has started");
		MDC.put("userId", Integer.valueOf(1).toString());
		
		try {
			recurse();
		} catch(Error err) {
			log.error("Something has gone horribly wrong!", err);
		}
		
		log.info("The application has completed");
		
		// Don't forget to empty the MDC
		// VERY important
//		MDC.clear();
		MDC.remove("userId");
	}

	public static void recurse() {
		recurse();
	}
}
