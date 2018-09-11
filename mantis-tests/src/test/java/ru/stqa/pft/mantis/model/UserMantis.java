package ru.stqa.pft.mantis.model;

import java.util.Objects;

public class UserMantis {

  public int id;
  public String userName;
  public String email;

  public Integer getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }

  public String getEmail() {
    return email;
  }

  public UserMantis withId(int id) {
    this.id = id;
    return this;
  }

  public UserMantis withUserName(String userName) {
    this.userName = userName;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserMantis that = (UserMantis) o;
    return id == that.id &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userName, email);
  }

  public UserMantis withEmail(String email) {
    this.email = email;
    return this;
  }

  @Override
  public String toString() {
    return "UserMantis{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

}
