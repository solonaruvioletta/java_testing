package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by user on 31.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.goTo().groupPage();
      if (!app.group().isThereAGroup()) {
        app.group().create(new GroupData("test1", null, null));
      }
      app.goTo().goToAddNewPage();
      app.getContactHelper().createContact(new ContactData("Violetta", "Igorevna", "Solonaru", null, null, null, null, null, null, null, null, null, null, null, "test1"), true);
    }
  }

  @Test

  public void testContactDeletion() {
    app.goTo().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().acceptContactDeletion();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}

