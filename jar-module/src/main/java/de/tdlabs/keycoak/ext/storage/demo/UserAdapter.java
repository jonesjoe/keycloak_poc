package de.tdlabs.keycoak.ext.storage.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.adapter.AbstractUserAdapterFederatedStorage;

public class UserAdapter extends AbstractUserAdapterFederatedStorage {

  private final User user;
  private final String keycloakId;
  private final String role;
  private final String companyId;


  public UserAdapter(KeycloakSession session, RealmModel realm, ComponentModel model, User user) {
    super(session, realm, model);
    this.user = user;
    this.role=user.getRole();
    this.companyId=user.getCompanyId();
    this.keycloakId = StorageId.keycloakId(model, user.getId());
    setAttributes();
  }

  @Override
  public String getId() {
    return keycloakId;
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public void setUsername(String username) {
    user.setUsername(username);
  }

  @Override
  public String getEmail() {
    return user.getEmail();
  }

  @Override
  public void setEmail(String email) {
    user.setEmail(email);
  }

  @Override
  public String getFirstName() {
    return user.getFirstName();
  }

  @Override
  public void setFirstName(String firstName) {
    user.setFirstName(firstName);
  }

  @Override
  public String getLastName() {
    return user.getLastName();
  }

  @Override
  public void setLastName(String lastName) {
    user.setLastName(lastName);
  }

  @Override
  public Set<RoleModel> getRoleMappings() {
  Set<RoleModel> roleModels=super.getRoleMappings();
  for(RoleModel roleModel:realm.getRoles()){
     if(roleModel.getName().equals(role)){
      roleModels.add(roleModel);
     }
  }
  return roleModels;
  }

  public void setAttributes() {
    List<String> companyAttributes=new ArrayList<String>();
    companyAttributes.add(companyId);
    getFederatedStorage().setAttribute(realm, this.getId(), "companyId", companyAttributes);
  }

}
