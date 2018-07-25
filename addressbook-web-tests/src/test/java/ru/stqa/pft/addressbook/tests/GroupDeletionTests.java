package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereARecord()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testGroupDeletion() {
    List<GroupData> before = app.getGroupHelper().getGroupList();
//    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectRecord(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
//    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
//    for (int i = 0; i < after.size(); i++) {
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    Assert.assertEquals(before, after);
  }

}
