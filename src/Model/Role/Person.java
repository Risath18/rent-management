package Model.Role;

import Model.Util.*;

public interface Person {
    public Name name();
    public String email = "";

    public Person getPerson();
    public Name getName();
    public String getEmail();
    public void setName(Name name);
    public void setEmail(String email);

}
