package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 31.01.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.goTo().groupPage();
      if (app.group().list().size() == 0) {
        app.group().create(new GroupData().withName("test1"));
      }
      app.goTo().addNewPage();
      app.contact().create(new ContactData("Violetta", "Igorevna", "Solonaru", null, null, null, null, null, null, null, null, null, null, null, "test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "Violetta", "Igorevna", "Solonaru", "Violet", "Test engineer", "OTR", "Russian Federation, Sevastopol", "8692 23 96 57", "7978 800 57 64", "8692 55 55 00", "8692 93 78 17", "violetta.solonaru@gmail.com", "solonaru.violetta@otr.ru", "1988", null);
    app.contact().modify(index, contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
