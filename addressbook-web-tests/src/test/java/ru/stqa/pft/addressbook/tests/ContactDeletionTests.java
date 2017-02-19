package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

/**
 * Created by user on 31.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName("test1"));
      }
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstname("Violetta").withMiddlename("Igorevna").withLastname("Solonaru").withNickname(null).withTitle(null).withCompany(null).withAddress(null).withHomephone(null).withMobilephone(null).withWorkphone(null).withFax(null).withEmail(null).withEmail2(null).withBirthyear(null).withGroup("test1"), true);
    }
  }

  @Test

  public void testContactDeletion() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}

