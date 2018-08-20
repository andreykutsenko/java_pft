package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
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
  public void testContactModification() {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();

    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Name1").withMiddlename("Name2").withLastname("Name3")
            .withNickname("Name4").withTitle("Title").withCompany("NameCompany").withAddress("AddressCompany").withHomePhone("HomePhone")
            .withMobilePhone("MobilePhone").withWorkPhone("WorkPhone").withFax("FaxPhone").withEmail1("email@email.com")
            .withEmail2("email2@email.com").withEmail3("email3@email.com").withHomepage("homepage.com").inGroup(groups.iterator().next());

    app.contact().modify(contact);
    Contacts after = app.db().contacts();

    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}