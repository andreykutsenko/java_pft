package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().createContact(new ContactData("Name1", "Name2", "Name3", "Name4", "Title", "NameCompany", "AddressCompany", "HomePhone", "MobilePhone", "WorkPhone", "FaxPhone", "email@email.com", "email2@email.com", "email3@email.com", "homepage.com", "er123"));
  }

}
