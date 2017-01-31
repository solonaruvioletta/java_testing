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
  }

  public void goToMainPage() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void enterContactInformation() {
    click(By.name("theform"));
  }

  public void selectContact() { click(By.name("selected[]")); }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void acceptContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void initGroupModification() {
    click(By.xpath("//tr[@class='odd']/td[8]/a/img"));
  }

  public void submitGroupModification() { click(By.xpath("//div[@id='content']/form[1]/input[22]")); }
}
