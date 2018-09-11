package ru.stqa.pft.mantis.appmanager;

import ru.stqa.pft.mantis.model.UserMantis;
import ru.stqa.pft.mantis.model.Users;

import java.sql.*;

public class DbHelper {

  private final ApplicationManager app;

  public DbHelper(ApplicationManager app) {
    this.app = app;
  }

  public Users users() {
    Connection conn = null;
    Users result = new Users();
    try {
      conn = DriverManager.getConnection(app.getProperty("db.url"));
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select id, username, email from mantis_user_table where id<>1"); //only not administrator
      while (rs.next()) {
        result.add(new UserMantis().withId(rs.getInt("id"))
                .withUserName(rs.getString("username"))
                .withEmail(rs.getString("email")));
      }
      rs.close();
      st.close();
      conn.close();

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return new Users(result);
  }

}
