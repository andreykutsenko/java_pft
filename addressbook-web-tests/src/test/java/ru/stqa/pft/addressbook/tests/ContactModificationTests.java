package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test(enabled = false)
  public void testContactModification() {
    app.goTo().gotoHomePage();
    if (! app.getContactHelper().isThereARecord()) {
      app.getContactHelper().createContact(new ContactData("Name1", "Name2", "Name3", "Name4", "Title", "NameCompany", "AddressCompany", "HomePhone", "MobilePhone", "WorkPhone", "FaxPhone", "email@email.com", "email2@email.com", "email3@email.com", "homepage.com", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size() + 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Name1", "Name2", "Name3", "Name4", "Title", "NameCompany", "AddressCompany", "HomePhone", "MobilePhone", "WorkPhone", "FaxPhone", "email@email.com", "email2@email.com", "email3@email.com", "homepage.com", "test1");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitModification();
//    app.getContactHelper().returnToContactPage();
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
//    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); //перед сравнением объекты преобразуются в множества (неупорядоченные коллекции)
    Assert.assertEquals(before, after);

  }

}


