package ru.stqa.pft.rest;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

  @Test
  public void testCreateIssue() throws IOException {
    Random rand = new Random();
    Set<Issue> oldIssues = getIssues();
    Issue issueRandom = oldIssues.stream().skip(rand.nextInt(oldIssues.size()) % oldIssues.size()).findFirst().get();
    skipIfNotFixed(issueRandom.getId());

    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue"); //вызываем конструктор
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }


}
