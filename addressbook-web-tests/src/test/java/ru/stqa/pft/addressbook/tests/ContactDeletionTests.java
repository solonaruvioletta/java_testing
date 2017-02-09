package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by user on 31.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Violetta", "Igorevna", "Solonaru", null, null, null, null, null, null, null, null, null, null, null, "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().acceptContactDeletion();
    app.getNavigationHelper().goToHomePage();
  }
}
