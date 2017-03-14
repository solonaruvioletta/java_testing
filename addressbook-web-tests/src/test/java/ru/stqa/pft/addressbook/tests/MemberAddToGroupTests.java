package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertTrue;

/**
 * Created by user on 13.03.2017.
 */
public class MemberAddToGroupTests extends TestBase {


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
  public void testMemberAddToGroup() {
    app.goTo().homePage();
    Contacts contactsBefore = app.db().contacts();
    ContactData groupMember = contactsBefore.iterator().next();
    int groupId = app.db().groups().iterator().next().getId();
    String groupValue = String.valueOf(groupId);
    app.contact().addMemberToGroup(groupMember, groupValue);
    Groups groupsList = app.db().groups();
    assertTrue(verifyMemberAddToGroup(groupsList, groupMember, groupId));
  }

  private boolean verifyMemberAddToGroup(Groups groupsList, ContactData memberToGroup, Integer groupId) {
    boolean memberOfGroup = false;
    for (GroupData group : groupsList) {
      GroupData g = new GroupData().withId(group.getId()).withContacts(group.getContacts());
      if (g.getId() == groupId) {
        Contacts listCon = g.getContacts();
        for (ContactData contact : listCon) {
          if (contact.equals(memberToGroup)) {
            memberOfGroup = true;
          }
        }
      }
    }
    return memberOfGroup;
  }
}
