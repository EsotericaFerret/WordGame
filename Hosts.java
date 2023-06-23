public class Hosts extends Person {
    private Numbers num;
    public Hosts(String first, String last) {
        
        setFirstName(first);
        setLastName(last);
        num = new Numbers();
    }
    public void randomizeNum() {
        num = new Numbers();
        num.generateNumber();
    }
    public Numbers getRandNum() {
        return num;
    }
}
