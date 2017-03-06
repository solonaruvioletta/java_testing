package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by user on 20.02.2017.
 */
public class ContactAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      if (app.db().groups().size() == 0){
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test1"));
      }
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstname("Violetta").withMiddlename(null).withLastname("Solonaru").withNickname(null).withTitle(null).withCompany(null).withAddress("Russian Federation, Sevastopol,AVE October Revolution,26,446").withHomephone("111").withMobilephone("222").withWorkphone("333").withFax(null).withEmail("123@gmail.com").withEmail2("321@gmail.com").withEmail3("231@gmail.com").withBirthyear(null).withGroup("[none]"), true);
    }
  }

  @Test
  public void testContactAddress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
    verifyContactListInUI();
  }

  public String cleaned(String address) {
    return address;

  }
}
