package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UserMantis> {

  private Set<UserMantis> delegate;

  public Users(Users users) {
    this.delegate = new HashSet<UserMantis>(users.delegate);
  }

  public Users() {
    this.delegate = new HashSet<UserMantis>();
  }

  public Users(Collection<UserMantis> users) {
    this.delegate = new HashSet<UserMantis>(users);
  }

  @Override
  protected Set<UserMantis> delegate() {
    return delegate;
  }

  public Users withAdded(UserMantis user) {
    Users users = new Users(this);
    users.add(user);
    return users;
  }

  public Users withOut(UserMantis user) {
    Users users = new Users(this);
    users.remove(user);
    return users;
  }
}
