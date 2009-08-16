package org.signalml.jsignalml;

public class Logger {
    final org.apache.log4j.Logger log;

    public Logger(Class<?> T){
	this.log = org.apache.log4j.Logger.getLogger(T);
    }

    public void debug(String format, Object...args){
	this.log.debug(String.format(format, args));
    }
    public void info(String format, Object...args){
	this.log.info(String.format(format, args));
    }
    public void warn(String format, Object...args){
	this.log.warn(String.format(format, args));
    }
    public void error(String format, Object...args){
	this.log.error(String.format(format, args));
    }

    public void exception(String format, Object...args){
	this.log.error(String.format(format, args));
    }
    public void exception(String format, Throwable e, Object...args){
	this.log.error(String.format(format, args), e);
    }
}
