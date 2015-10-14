/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServices;

/**
 *
 * @author lukasgreiner
 */
public class Lock {

    private int runningThreadsNumber;

    public Lock() {
        runningThreadsNumber = 0;
    }

    public int getRunningThreadsNumber() {
        return runningThreadsNumber;
    }

    public void addRunningThread() {
        runningThreadsNumber++;
    }

    public void removeRunningThread() {
        runningThreadsNumber--;
    }
}
