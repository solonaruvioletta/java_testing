package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getNavigationHelper().goToGroupPage();
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getNavigationHelper().goToAddNewPage();
    app.getContactHelper().createContact(new ContactData("Violetta", "Igorevna", "Solonaru", null, null, null, null, null, null, null, null, null, null, null, "test1"), true);
  }
}

