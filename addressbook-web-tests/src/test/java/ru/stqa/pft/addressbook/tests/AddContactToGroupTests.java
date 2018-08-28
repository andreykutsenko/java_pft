package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    Groups groups = app.db().groups();
    app.goTo().contactPage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Name1").withMiddlename("Name2").withLastname("Name3").withNickname("Name4").withTitle("Title")
              .withCompany("NameCompany").withAddress("AddressCompany").withHomePhone("HomePhone").withMobilePhone("MobilePhone")
              .withWorkPhone("WorkPhone").withFax("FaxPhone").withEmail1("email@email.com").withEmail2("email2@email.com")
              .withEmail3("email3@email.com").withHomepage("homepage.com").inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testAddContactToGroup() {
    Groups groupsBefore = app.db().groups();
    Contacts contactsBefore = app.db().contacts();

    Random rand = new Random();
    ContactData selectedContact = contactsBefore.stream().skip(rand.nextInt(contactsBefore.size()) % contactsBefore.size()).findFirst().get();

    Groups selectedContactGroupsBefore = selectedContact.getGroups();
    groupsBefore.removeAll(selectedContactGroupsBefore);
    GroupData addedGroup;
    app.goTo().contactPage();
    if (groupsBefore.size() > 0) {
      addedGroup = groupsBefore.iterator().next();
      app.contact().addToGroup(selectedContact, addedGroup);
    } else {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("NewGroupForContact"));
      Groups groupsAfter = app.db().groups();
      groupsAfter.removeAll(selectedContactGroupsBefore);
      addedGroup = groupsAfter.iterator().next();
      app.goTo().contactPage();
      app.contact().addToGroup(selectedContact, addedGroup);
    }

    Groups selectedContactGroupsAfter = app.db().contactById(selectedContact.getId()).iterator().next().getGroups();
    assertThat(selectedContactGroupsAfter, equalTo(selectedContactGroupsBefore.withAdded(addedGroup)));
  }

}


