package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    //int before = app.group().getGroupCount();
    GroupData group = new GroupData().withName("Test2");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    //int after = app.group().getGroupCount();
    Assert.assertEquals(after.size(), before.size() + 1);

//    int max = 0;
//    for (GroupData g : after) {
//      if (g.getId() > max) {
//        max = g.getId();
//      }
//    }
//    int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
//    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
//    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//    before.sort(byId);
//    after.sort(byId);
//    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    Assert.assertEquals(before, after);
  }

}
