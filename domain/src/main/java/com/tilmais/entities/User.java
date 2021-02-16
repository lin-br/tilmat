package com.tilmais.entities;


public class User {

  private final String cellPhone;
  private final String emailAddress;
  private final String name;
  private final String statusDescription;

  private User(String cellPhone, String emailAddress, String name, String statusDescription) {
    this.cellPhone = cellPhone;
    this.emailAddress = emailAddress;
    this.name = name;
    this.statusDescription = statusDescription;
  }

  public String getCellPhone() {
    return cellPhone;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getName() {
    return name;
  }

  public String getStatusDescription() {
    return statusDescription;
  }

  public boolean isNotYourStatus(String statusDescription) {
    return !this.statusDescription.equals(statusDescription);
  }

  public boolean isNotYourName(String result) {
    return !this.name.equals(result);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User that = (User) o;
    return cellPhone.equals(that.cellPhone);
  }

  public static class BuilderUser {

    private String cellPhone;
    private String emailAddress;
    private String name;
    private String statusDescription;

    public BuilderUser setCellPhone(String cellPhone) {
      this.cellPhone = cellPhone;
      return this;
    }

    public BuilderUser setEmailAddress(String emailAddress) {
      this.emailAddress = emailAddress;
      return this;
    }

    public BuilderUser setName(String name) {
      this.name = name;
      return this;
    }

    public BuilderUser setStatusDescription(String statusDescription) {
      this.statusDescription = statusDescription;
      return this;
    }

    public User build() {
      return new User(this.cellPhone, this.emailAddress, this.name, this.statusDescription);
    }
  }

  public static BuilderUser builder() {
    return new BuilderUser();
  }
}
