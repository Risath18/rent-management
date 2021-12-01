package Model.Role;

import Model.Util.*;

public interface Person {
    public Name name(); //name of a Person
    public String email = ""; //email of a Person

    /**
     * getter for Person
     * @return returns a Person object
     */
    public Person getPerson();

    /**
     * getter for Name
     * @return returns Name object
     */
    public Name getName();

    /**
     * getter for email
     * @return returns a String email
     */
    public String getEmail();

    /**
     * setter for Name
     * @param name Name object to be stored
     */
    public void setName(Name name);

    /**
     * setter for email
     * @param email String email to be stored
     */
    public void setEmail(String email);

}
