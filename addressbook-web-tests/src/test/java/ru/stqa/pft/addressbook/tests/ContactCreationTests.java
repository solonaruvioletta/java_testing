package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData().withPhoto(new File ("src/test/resources/picture.jpg")).withFirstname("Violetta").withMiddlename(null).withLastname("Solonaru").withNickname(null).withTitle(null).withCompany(null).withAddress("Russian Federation, Sevastopol,AVE October Revolution,26,446").withHomephone("111").withMobilephone("222").withWorkphone("333").withFax(null).withEmail("123@gmail.com").withEmail2("321@gmail.com").withEmail3("231@gmail.com").withBirthyear(null).withGroup("[none]")});
    list.add(new Object[] {new ContactData().withPhoto(new File ("src/test/resources/picture.jpg")).withFirstname("Veta").withMiddlename(null).withLastname("Solonaru").withNickname(null).withTitle(null).withCompany(null).withAddress("Russian Federation, Sevastopol,AVE October Revolution,26,446").withHomephone("111").withMobilephone("222").withWorkphone("333").withFax(null).withEmail("123@gmail.com").withEmail2("321@gmail.com").withEmail3("231@gmail.com").withBirthyear(null).withGroup("[none]")});
    list.add(new Object[] {new ContactData().withPhoto(new File ("src/test/resources/picture.jpg")).withFirstname("Violet").withMiddlename(null).withLastname("Solonaru").withNickname(null).withTitle(null).withCompany(null).withAddress("Russian Federation, Sevastopol,AVE October Revolution,26,446").withHomephone("111").withMobilephone("222").withWorkphone("333").withFax(null).withEmail("123@gmail.com").withEmail2("321@gmail.com").withEmail3("231@gmail.com").withBirthyear(null).withGroup("[none]")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(File photo, String firstname, String middlename, String lastname, String nickname, String title, String company, String address,String homephone, String mobilephone, String workphone, String fax, String email, String email2, String email3, String birthyear, String group) {
    ContactData contact = new ContactData().withPhoto(photo).withFirstname(firstname).withMiddlename(middlename).withLastname(lastname).withNickname(nickname).withTitle(title).withCompany(company).withAddress(address).withHomephone(homephone).withMobilephone(mobilephone).withWorkphone(workphone).withFax(fax).withEmail(email).withEmail2(email2).withEmail3(email3).withBirthyear(birthyear).withGroup(group);
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().addNewPage();
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

  @Test (enabled = false)
  public void testBadContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().addNewPage();
    ContactData contact = new ContactData().withFirstname("Veta'").withMiddlename("Igorevna").withLastname("Solonaru").withNickname(null).withTitle(null).withCompany(null).withAddress(null).withHomephone(null).withMobilephone(null).withWorkphone(null).withFax(null).withEmail(null).withEmail2(null).withBirthyear(null).withGroup("[none]");
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}

