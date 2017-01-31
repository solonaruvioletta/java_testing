package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by user on 31.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() {

    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().acceptContactDeletion();
  }
}
