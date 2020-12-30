package de.tdlabs.keycoak.ext.storage.demo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

  private String id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private String password;
  private String role;
  private String companyId;

  public User(String id, String firstName, String lastName, String role, String companyId) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = (firstName + "." + lastName).toLowerCase();
    this.email = this.username + "@example.com";
    this.password = firstName.toLowerCase();
    this.role = role;
    this.companyId = companyId;
  }
}
