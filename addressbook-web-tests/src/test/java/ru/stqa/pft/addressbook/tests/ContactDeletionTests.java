package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test(enabled = false)
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereARecord()) {
      app.getContactHelper().createContact(new ContactData("Name1", "Name2", "Name3", "Name4", "Title", "NameCompany", "AddressCompany", "HomePhone", "MobilePhone", "WorkPhone", "FaxPhone", "email@email.com", "email2@email.com", "email3@email.com", "homepage.com", "tt1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectRecord(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
//    app.getContactHelper().returnToContactPage();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
