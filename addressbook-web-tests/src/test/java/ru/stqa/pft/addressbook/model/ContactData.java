package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String id;
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String title;
  private final String company;
  private final String address;
  private final String homephone;
  private final String mobilephone;
  private final String workphone;
  private final String fax;
  private final String email;
  private final String email2;
  private final String birthyear;
  private String group;

  public ContactData(String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String homephone, String mobilephone, String workphone, String fax, String email, String email2, String birthyear, String group) {
    this.id = null;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.workphone = workphone;
    this.fax = fax;
    this.email = email;
    this.email2 = email2;
    this.birthyear = birthyear;
    this.group = group;
  }

  public ContactData(String id, String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String homephone, String mobilephone, String workphone, String fax, String email, String email2, String birthyear, String group) {
    this.id = id;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.workphone = workphone;
    this.fax = fax;
    this.email = email;
    this.email2 = email2;
    this.birthyear = birthyear;
    this.group = group;

  }

  public String getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getWorkphone() {
    return workphone;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getBirthyear() {
    return birthyear;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (homephone != null ? !homephone.equals(that.homephone) : that.homephone != null) return false;
    if (mobilephone != null ? !mobilephone.equals(that.mobilephone) : that.mobilephone != null) return false;
    if (workphone != null ? !workphone.equals(that.workphone) : that.workphone != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    return email2 != null ? email2.equals(that.email2) : that.email2 == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (homephone != null ? homephone.hashCode() : 0);
    result = 31 * result + (mobilephone != null ? mobilephone.hashCode() : 0);
    result = 31 * result + (workphone != null ? workphone.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", homephone='" + homephone + '\'' +
            ", mobilephone='" + mobilephone + '\'' +
            ", workphone='" + workphone + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            '}';
  }
}