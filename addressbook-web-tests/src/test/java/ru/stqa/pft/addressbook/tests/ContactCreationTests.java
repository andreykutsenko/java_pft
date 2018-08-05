package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/112233.jpg");
    ContactData contact = new ContactData()
            .withFirstname("Name1").withMiddlename("Name2").withLastname("Name3").withNickname("Name4").withTitle("Title")
            .withCompany("NameCompany").withAddress("AddressCompany").withHomePhone("HomePhone").withMobilePhone("MobilePhone")
            .withWorkPhone("WorkPhone").withFax("FaxPhone").withEmail1("email@email.com").withEmail2("email2@email.com")
            .withEmail3("email3@email.com").withHomepage("homepage.com").withGroup("er123").withPhoto(photo);
    app.contact().create(contact);
    Contacts after = app.contact().all();

    assertThat((after.size()), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

//  @Test
//  public void testCurrentDir() {
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsolutePath());
//    File photo = new File("src/test/resources/112233.jpg");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//  }

}