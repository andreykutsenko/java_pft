package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {

  public Set<Issue> getIssues() throws IOException {
//    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }

  public Set<Issue> getIssueStatusById(int id) throws IOException {
    String json = getExecutor().execute(Request.Get(String.format("http://bugify.stqa.ru/api/issues/%s.json", id)))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }

  public Executor getExecutor() {
//    return Executor.newInstance().auth("210ab85e8234e5eb182dfc09cf4f47c1", "");
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  public int createIssue(Issue newIssue) throws IOException {
//    String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
    String json = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    Boolean result = false;
    if (getIssueStatusById(issueId).iterator().next().getStatus() == 0) {
      result = true;
    }
    return result;
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
