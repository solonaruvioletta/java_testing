package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.goTo().groupPage();
    if (!app.group().isThereAGroup()) {
      app.goTo().groupPage();
      app.group().create(new GroupData("test1", null, null));
    }
    app.goTo().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.goTo().goToAddNewPage();
    ContactData contact = new ContactData("Violetta", "Igorevna", "Solonaru", null, null, null, null, null, null, null, null, null, null, null, "test1");
    app.getContactHelper().createContact(contact, true);
    app.goTo().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}

