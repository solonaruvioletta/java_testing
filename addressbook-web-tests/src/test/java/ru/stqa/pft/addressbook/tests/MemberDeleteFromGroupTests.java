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
public class MemberDeleteFromGroupTests extends TestBase {


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
    app.goTo().homePage();
    app.contact().addMemberToGroup(app.db().contacts().iterator().next(), String.valueOf(app.db().groups().iterator().next().getId()));
  }

  @Test
  public void testContactDeleteFromGroup() {
    app.goTo().homePage();
    Integer groupId = 0;
    Groups groupsList = app.db().groups();
    ContactData deletedContact = null;
    for (GroupData group : groupsList) {
      if (group.getContacts() != null) {
        Contacts contactsListInGroup = group.getContacts();
        groupId = group.getId();
        String groupValue = String.valueOf(groupId);
        deletedContact = app.contact().deleteMemberFromGroup(contactsListInGroup, groupValue);
        System.out.println(group);
        System.out.println(deletedContact);
        break;
      }
    }
    Groups newGroupsList = app.db().groups();

    assertTrue(checkContactDeletedFromGroup(newGroupsList, deletedContact, groupId));
  }

  private boolean checkContactDeletedFromGroup(Groups newGroupsList, ContactData deletedContact, Integer groupId) {
    for (GroupData group : newGroupsList){
      if (group.getId() == groupId){
        Contacts contactsListInGroup = group.getContacts();
        for (ContactData contact : contactsListInGroup){
          if (contact == deletedContact){
            return false;
          }
        }
        break;
      }
    }
    return true;
  }
}