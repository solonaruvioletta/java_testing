package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

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
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName("test1"));
      }
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstname("Violetta").withMiddlename(null).withLastname("Solonaru").withNickname(null).withTitle(null).withCompany(null).withAddress("Russian Federation, Sevastopol,AVE October Revolution,26,446").withHomephone("111").withMobilephone("222").withWorkphone("333").withFax(null).withEmail("123@gmail.com").withEmail2("321@gmail.com").withEmail3("231@gmail.com").withBirthyear(null).withGroup("[none]"), true);
    }
  }

  @Test
  public void testContactEmails() {
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

