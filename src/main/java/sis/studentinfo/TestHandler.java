/**
 * 
 */
package sis.studentinfo;

/**
 * @author hirian
 *
 */
/*
 * Lesson 8
 * Instead, you will create a new handler class, TestHandler. 
 * Within this handler class, you will trap all messages being logged. 
 * You will provide a getter method that returns the last message that 
 * was logged. Once you have built this handler class, you can pass an 
 * instance of it to the logger.
 * 
 * For each message you log, the Logger object calls the method publish 
 * on the Handler object, passing it a java.util.logging.LogRecord 
 * parameter object.
 * 
 * It is the publish method that you will override in your Handler 
 * subclass to trap the message being logged. You must also supply 
 * definitions for the other two abstract methods in Handler, flush and 
 * close. They can be empty.
 */

import java.util.logging.*;
public class TestHandler extends Handler{
	
	private LogRecord record;

	/* (non-Javadoc)
	 * @see java.util.logging.Handler#close()
	 */
	@Override
	public void close() throws SecurityException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Handler#flush()
	 */
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Handler#publish(java.util.logging.LogRecord)
	 */
	@Override
	public void publish(LogRecord record) {
		this.record = record;
		
	}
	
	String getMessage() {
		return record.getMessage();
	}

}
