package com.automation.api.listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
    Logger log = Logger.getLogger(Listeners.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("Starting test");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("Successful test");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info("Test failed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
