package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import javax.swing.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by user on 31.01.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test1"));
      }
      Groups groups = app.db().groups();
      ContactData newContact = new ContactData().withFirstname("Violetta").withMiddlename("Igorevna").withLastname("Solonaru").withNickname(null).withTitle(null).withCompany(null).withAddress(null).withHomephone(null).withMobilephone(null).withWorkphone(null).withFax(null).withEmail(null).withEmail2(null)
              .inGroup(groups.iterator().next());
      app.goTo().addNewPage();
      app.contact().create(newContact, true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();

    Groups groups = app.db().groups();
    JOptionPane.showMessageDialog(null, groups);
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("Violetta")
            .withMiddlename("Igorevna")
            .withLastname("Solonaru")
            .withNickname("Violet")
            .withTitle("Test engineer")
            .withCompany("OTR")
            .withAddress("Russian Federation, Sevastopol")
            .withHomephone("8692 23 96 57")
            .withMobilephone("7978 800 57 64")
            .withWorkphone("8692 55 55 00")
            .withFax("8692 93 78 17")
            .withEmail("violetta.solonaru@gmail.com")
            .withEmail2("solonaru.violetta@otr.ru")
            .withEmail3("123@gmail.com")
            .inGroup(groups.iterator().next());
    app.goTo().homePage();
    app.contact().modify(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size())); //проверка для контроля пользовательского интерфейса
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
