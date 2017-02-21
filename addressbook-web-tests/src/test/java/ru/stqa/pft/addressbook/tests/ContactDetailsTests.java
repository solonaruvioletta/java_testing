package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by user on 21.02.2017.
 */
public class ContactDetailsTests extends TestBase {

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
  public void testContactDetails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getFirstname(), equalTo(contactInfoFromEditForm.getFirstname()));
    assertThat(contact.getLastname(), equalTo(contactInfoFromEditForm.getLastname()));
  }
  }

