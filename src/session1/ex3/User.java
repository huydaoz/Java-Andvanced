package session1.ex3;
import session1.ex5.InvalidAgeException;

public class User {
    private int age ;

    public User(int age) {
        this.age = age;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) throws InvalidAgeException {
        if(age<0){
            throw  new InvalidAgeException("Tuoi khong the am");
        }
        this.age = age;
    }

}