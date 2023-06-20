public class Person {

    private String firstName;
    private String lastName;
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
    public String getName(){

        String fullName = firstName + " " + lastName;
        return fullName;
    }
}
