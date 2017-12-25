package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (! text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  public boolean isAlertPresent() {
    try {
      getAlert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected Alert getAlert() {
    return wd.switchTo().alert();
  }

  public void submitModification() {
    click(By.name("update"));
  }

  public void submitCreation() {
    click(By.name("submit"));
  }

  public void selectRecord() {
    click(By.name("selected[]"));
  }

  protected boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }

  }
}
