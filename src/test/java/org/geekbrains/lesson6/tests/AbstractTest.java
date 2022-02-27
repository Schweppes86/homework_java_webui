package org.geekbrains.lesson6.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class AbstractTest {
    private static WebDriver driver;
    private static List<LogEntry> logList;

    @BeforeAll
    static void init(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @BeforeEach
    void goTo(){
        Assertions.assertDoesNotThrow( ()-> driver.navigate().to("https://www.saucedemo.com/"),
                "Страница не доступна");
    }

    @AfterAll
    public static void close(){
        driver.quit();
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    @AfterEach
    void verifyLogs(){
        LogEntries browserLogs = getDriver().manage().logs().get(LogType.BROWSER);
        logList = browserLogs.getAll();
        if (logList.size() > 0 ) {
            logList.forEach(logEntry -> {
                System.out.println("Find in console logs: " + logEntry.getMessage());
                addConsoleLogToReport();
            });
        }
    }

    @Attachment(value = "Browser console log", type = "text/plain")
    private static String addConsoleLogToReport() {
        StringBuilder sb = new StringBuilder();
        for (Object line : logList) {
            sb.append(line);
            sb.append("\t");
        }
        return sb.toString();
    }
}
