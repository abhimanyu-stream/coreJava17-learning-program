package com.java17.interview.prepartion;



import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class HashSetLearning {

    public static void main(String[] args) {


        Set<User> setOfUser = new HashSet<>();

       /* User user1 = new User("Abhimanyu", 20);
        User user2 = new User("Abhimanyu", 20);
*/
        User user1 = new User(null, 20);
        User user2 = new User(null, 20);
        User user3 = new User(null, -1);
        User user4 = new User("Abhimanyu", -1);

        setOfUser.add(user1);
        setOfUser.add(user2);
        setOfUser.add(user3);
        setOfUser.add(user4);


        System.out.println(setOfUser);
        System.out.println(setOfUser.size());
        // Below method call toUpperCase raises NullPointerEXception
        // List<String> namesList = setOfUser.stream().map(m->m.getName().toUpperCase()).collect(Collectors.toList());
        // Below code do not raise exception
        List<String> namesList = setOfUser.stream().map(m->m.getName()).collect(Collectors.toList());
        System.out.println(namesList);

        TreeSet<User> userTree = new TreeSet<>(setOfUser);
        //Note User class must implement Comparable interface then only we can convert HashSet<User> object into TreeSet<User>
        System.out.println(userTree);

        
    }
}

class User implements Comparable{
   private  String name;
   private int  age;

   public User(){}

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
