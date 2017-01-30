package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by 1 on 29.01.2017.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("work"), contactData.getWorkphone());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());

    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[11]")).isSelected()) {
      wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[11]")).click();
    }
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[4]")).isSelected()) {
      wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[4]")).click();
    }

    type(By.name("byear"), contactData.getBirthyear());
  }

  public void goToMainPage() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void enterContactInformation() {
    click(By.name("theform"));
  }
}
