package org.signalml.jsignalml;

import static java.lang.String.format;

public class Logger {
    final org.apache.log4j.Logger log;

    public Logger(Class<?> T){
	this.log = org.apache.log4j.Logger.getLogger(T);
    }

    public void debug(String message, Object...args){
	this.log.debug(format(message, args));
    }
    public void info(String message, Object...args){
	this.log.info(format(message, args));
    }
    public void warn(String message, Object...args){
	this.log.warn(format(message, args));
    }
    public void error(String message, Object...args){
	this.log.error(format(message, args));
    }

    public void exception(String message, Object...args){
	this.log.error(format(message, args));
    }
    public void exception(String message, Throwable e, Object...args){
	this.log.error(format(message, args), e);
    }
}
