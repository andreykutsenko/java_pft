package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("newName1", "newName2", "newName3", "newName4", "newTitle", "newNameCompany", "newAddressCompany", "newHomePhone", "newMobilePhone", "newWorkPhone", "newFaxPhone", "newemail@email.com", "newemail2@email.com", "newemail3@email.com", "newhomepage.com"));
    app.getContactHelper().submitModification();
    app.getContactHelper().returnToContactPage();
  }

}


