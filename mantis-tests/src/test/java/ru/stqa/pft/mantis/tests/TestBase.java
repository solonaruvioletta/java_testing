package ru.stqa.pft.mantis.tests;

import com.mysql.fabric.jdbc.FabricMySQLDataSource;
import org.openqa.selenium.remote.BrowserType;

import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;

import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by 1 on 29.01.2017.
 */
public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.back");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.back","config_inc.php" );
    app.stop();
  }



}
