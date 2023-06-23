public class Person {

    protected String firstName;
    protected String lastName;
    public Person(){   
    }

    public Person(String first) {
        this.setFirstName(first);
        this.setLastName("");
    }
    public Person(String first, String last) {
        this.setFirstName(first);
        this.setLastName(last);
    }
    public void setFirstName(String first) {
        firstName = first;
    }
    public void setLastName(String last) {
        lastName = last;
    }
    public String toString(){

        String fullName = firstName + " " + lastName;
        return fullName;
    }
}
