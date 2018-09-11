package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserMantis;

public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void resetPassword(UserMantis user) {
    selectUserById(user.getId());
    clickResetPassword();
  }

  public void selectUserById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", id))).click();
  }

  public void clickResetPassword() {
    click(By.xpath("//input[@value='Reset Password']"));
  }

}
