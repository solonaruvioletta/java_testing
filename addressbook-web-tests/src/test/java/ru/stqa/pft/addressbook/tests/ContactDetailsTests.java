package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by user on 21.02.2017.
 */
public class ContactDetailsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      if (app.db().groups().size() == 0){
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test1"));
      }
      Groups groups = app.db().groups();
      ContactData newContact = new ContactData().withFirstname("Violetta").withMiddlename("Igorevna").withLastname("Solonaru").withNickname(null).withTitle(null).withCompany(null).withAddress(null).withHomephone(null).withMobilephone(null).withWorkphone(null).withFax(null).withEmail(null).withEmail2(null).withBirthyear(null)
              .inGroup(groups.iterator().next());
      app.goTo().addNewPage();
      app.contact().create(newContact, true);
    }
  }
  @Test
  public void testContactDetails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    String contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);

    assertThat(cleanDetails(contactInfoFromDetailsForm), equalTo(mergeEditForm(contactInfoFromEditForm)));
  }

  private String mergeEditForm(ContactData contact) {
    return Stream.of(contact.getFirstname(), contact.getLastname(), contact.getAddress(),contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone(), contact.getEmail(), contact.getEmail2(), contact.getEmail3()).filter((s)->!s.equals(""))
            .map(ContactDetailsTests::cleaned)
            .collect(Collectors.joining());
  }

  public static String cleanDetails(String details) {
    return details.replaceAll("\\s","").replaceAll("[-()]","").replaceAll("[H: ]","").replaceAll("[M: ]","").replaceAll("[W: ]","");
  }

  public static String cleaned(String editForm) {
    return editForm.replaceAll("\\s","").replaceAll("[-()]","");
  }

}

