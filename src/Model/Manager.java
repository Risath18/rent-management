package Model;

public class Manager implements Person{
    String name;
    int age;

    public Manager(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void printString(){
        System.out.println("Manager name is " + name + " and age is " + age);
    }
}
