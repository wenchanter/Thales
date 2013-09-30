package com.wenchanter.thales.core.job;

import org.apache.log4j.Logger;

public abstract class AbstractJob {
	private Logger logger = Logger.getLogger(this.getClass());
    protected Boolean isRunning = false;
    private Object lock = new Object();
    private boolean userLog = true;
    
    /**
     * The entrance function of the job.
     */
    public void execute() {
        synchronized (lock){
            if (isRunning) {
                logger.info("AbstractJob:" + this.getClass().getName() + " already running...");
                return;
            }
            if(userLog){
            	logger.info("AbstractJob:" + this.getClass().getName() + " begin running...");
            }
            isRunning = true;
        }

        try {
            executeInternal();
        } catch (Throwable e) {
        	logger.error("job excute error ", e);
        } finally {
        	synchronized (lock) {
                isRunning = false;
            }
        }
    }

    /**
     * The internal function which focus on the business logic.
     * 
     * @throws Exception the exception.
     */
    protected abstract void executeInternal() throws Exception;
    
	public void setUserLog(boolean userLog) {
		this.userLog = userLog;
	}
}
