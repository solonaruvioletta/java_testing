package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by user on 31.01.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().initGroupModification();
    app.getContactHelper().fillContactForm(new ContactData("Violetta", "Igorevna", "Solonaru", "Violet", "Test engineer", "OTR", "Russian Federation, Sevastopol", "8692 23 96 57", "7978 800 57 64", "8692 55 55 00", "8692 93 78 17", "violetta.solonaru@gmail.com", "solonaru.violetta@otr.ru", "1988"));
    app.getContactHelper().submitGroupModification();
    app.getNavigationHelper().goToHomePage();

  }
}
