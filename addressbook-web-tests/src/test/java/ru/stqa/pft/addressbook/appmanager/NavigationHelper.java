package ru.stqa.pft.addressbook.appmanager;

import org.eclipse.jetty.io.ClientConnectionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by 1 on 29.01.2017.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void goToGroupPage() {
    click(By.linkText("groups"));
  }

  public void goToAddNewPage() {
    click(By.linkText("add new"));
  }
}
