package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserMantis;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordMantisTests extends TestBase {

  @Test
  public void testChangePasswordMantis() throws MessagingException, IOException {
    long now = System.currentTimeMillis();
    String oldPassword = "password";
    String newPassword = String.format(oldPassword+"%s", now);
    Users allUsers = app.db().users();
    UserMantis selectedUser = allUsers.iterator().next();
    String selectedUserName = selectedUser.getUserName();
    String selectedUserEmail = selectedUser.getEmail();
    app.james().drainEmail(selectedUserName, oldPassword);
    app.goTo().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.goTo().managerUsers();
    app.user().resetPassword(selectedUser);

    List<MailMessage> mailMessages = app.james().waitForMail(selectedUserName, oldPassword,60000);
    String changePasswordLink = findChangePasswordLink(mailMessages, selectedUserEmail);
    app.registration().finish(changePasswordLink, newPassword);

    HttpSession session = app.newSession();
    assertTrue(session.login(selectedUserName, newPassword));
    assertTrue(session.isLoggedInAs(selectedUserName));
  }

  private String findChangePasswordLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}