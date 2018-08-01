package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Name1").withMiddlename("Name2").withLastname("Name3").withNickname("Name4").withTitle("Title")
              .withCompany("NameCompany").withAddress("AddressCompany").withHome("HomePhone").withMobile("MobilePhone")
              .withWork("WorkPhone").withFax("FaxPhone").withEmail("email@email.com").withEmail2("email2@email.com")
              .withEmail3("email3@email.com").withHomepage("homepage.com").withGroup("er123"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();

    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Name1").withMiddlename("Name2").withLastname("Name3")
            .withNickname("Name4").withTitle("Title").withCompany("NameCompany").withAddress("AddressCompany").withHome("HomePhone")
            .withMobile("MobilePhone").withWork("WorkPhone").withFax("FaxPhone").withEmail("email@email.com")
            .withEmail2("email2@email.com").withEmail3("email3@email.com").withHomepage("homepage.com").withGroup("test1");

    app.contact().modify(contact);
    Contacts after = app.contact().all();

    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
