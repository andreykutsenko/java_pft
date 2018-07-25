package ru.stqa.pft.addressbook.tests;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereARecord()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testGroupModification() {
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size() - 1;
    GroupData group = new GroupData(before.get(index).getId(),"test1", "test2", "test3");
//    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().modifyGroup(index, group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
//    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
//    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); //перед сравнением объекты преобразуются в множества (неупорядоченные коллекции)
    Assert.assertEquals(before, after);
  }
}
