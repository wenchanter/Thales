package com.wenchanter.thales.core.job;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

/**
 * 定时任务抽象类
 * 
 * @author wang_hui
 *
 */
public abstract class AbstractJob {

    private Logger logger = Logger.getLogger(this.getClass());
    private ReentrantLock lock = new ReentrantLock();
    
    protected abstract void exec();
    
    public void execute() {
        if (lock.tryLock()) {
            logger.info("AbstractJob:" + this.getClass().getName() + " begin running...");
            try {
                exec();
            } catch (Exception e) {
                logger.error("job excute error: " + this.getClass().getName(), e);
            } finally {
                lock.unlock();
            }
        } else {
            logger.info("AbstractJob:" + this.getClass().getName() + "is running...");
        }
        
    }
}
