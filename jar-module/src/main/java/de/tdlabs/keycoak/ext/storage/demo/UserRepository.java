package de.tdlabs.keycoak.ext.storage.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
class UserRepository {

  private List<User> users;

  public UserRepository() {
    users = Arrays.asList(
      new User("User1001", "Katie", "Washington","admin","1001"),
      new User("User1002", "Enrique", "Perkins","developer","1002"),
      new User("User1003", "Joshua", "Little","manager","ABC Company"),
      new User("User1004", "Billie", "Newman","admin","1004"),
      new User("User1005", "Leslie", "Thompson","admin","1005")
    );
  }

  public List<User> getAllUsers() {
    return users;
  }

  public int getUsersCount() {
    return users.size();
  }

  public User findUserById(String id) {
    return users.stream().filter(user -> user.getId().equals(id)).findFirst().get();
  }

  public User findUserByUsernameOrEmail(String username) {

    log.infov("search for users with params: username={0} users={1}", username, users);

    return users.stream()
      .filter(user -> user.getUsername().equalsIgnoreCase(username) || user.getEmail().equalsIgnoreCase(username))
      .findFirst().get();
  }

  public List<User> findUsers(String query) {
    return users.stream()
      .filter(user -> user.getUsername().contains(query) || user.getEmail().contains(query))
      .collect(Collectors.toList());
  }

  public boolean validateCredentials(String username, String password) {
    log.infov("validate credentials: username={0} password={1}", username, password);

    return findUserByUsernameOrEmail(username).getPassword().equals(password);
  }

  public boolean updateCredentials(String username, String password) {
    findUserByUsernameOrEmail(username).setPassword(password);
    return true;
  }

}
