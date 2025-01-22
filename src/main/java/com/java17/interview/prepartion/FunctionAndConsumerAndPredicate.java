package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionAndConsumerAndPredicate {


    public static void main(String[] args) {
        //Predicate has test method
        //public interface Predicate<T> {
        //boolean test(T t);
        //}
        Predicate<Integer> predicateEvenCheck = x-> x % 2 == 0;// check for even element
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        for(Integer i: list){
            System.out.println(i + "-isEven" +predicateEvenCheck.test(i));
        }

        Predicate<String> predicateStartsWithA = x->x.toLowerCase().charAt(0) == 'a';
        System.out.println("startsWithA " + predicateStartsWithA.test("abhimanyu"));
        StudentKVS s = new StudentKVS("Animal");
        System.out.println("startsWithA " + predicateStartsWithA.test(s.getName()));
        System.out.println("startsWithA " + predicateStartsWithA.negate().test(s.getName()));




        //public interface Consumer<T> {
        //void accept(T t);
        //}
        Consumer<Integer> consumer = x->System.out.println(x);
        consumer.accept(1);


        //public interface Supplier<T> {
        //T get();
        //}
        Supplier<String> supplier = ()-> "calli";
        System.out.println("supplier "+supplier.get());



        //Function
        //public interface Function<T, R> {
        //R apply(T t);
        //}

        //Function<String, Integer> here String is input and Integer is output
        Function<String, Integer> functionGetLength = String::length;
        System.out.println("function "+functionGetLength.apply("sun"));

        Function<String, String> functionSubString = x-> x.substring(0,3);// three letter String[including count)
        System.out.println("function2 " +functionSubString.apply("suppliers"));


        Function<String, String> functionIdentity = Function.identity();
        System.out.println("functionIdentity " +functionIdentity.apply("books"));




        Predicate<Integer> integerPredicate = x -> x % 2 == 0;
        Function<Integer, Integer> integerFunction = x->x * x;
        Consumer<Integer> integerConsumer= System.out::println;//or x->System.out.println(x);
        Supplier<Integer> integerSupplier=()->100;

        if(integerPredicate.test(integerSupplier.get())){
            integerConsumer.accept(integerFunction.apply(integerSupplier.get()));// 1. Supplier-->2. Function-->3. Consumer
        }



        Optional<List<String>> empty = Optional.empty();
        //empty.get().add("hum"); // it raises Exception java.util.NoSuchElementException : No value present

        //Note:-- after creating empty you have re-assign with valued Otional Object, then you can use/ call get() over empty

        Optional<String> stringOptional = Optional.of("hum");
        //Optional<Object> optionalofnull = Optional.of(null);// it raises null pointer exception[box[null] Exception in thread "main" java.lang.NullPointerException[ it mean we can not pass null as argument in of method of Optional]

        //System.out.println("optionalofnull "+ optionalofnull);
        Optional<String> stringOptional1 = Optional.ofNullable("");
        stringOptional1.ifPresent(value -> System.out.print("Optional.ofNullable(\"\");" + value));
        Optional<Object> optionalnull = Optional.ofNullable(null);// it do not raises null pointer exception[it prints  empty box/space on console]
        optionalnull.ifPresent(System.out :: println);
        Optional<String> hum = Optional.ofNullable("hum");
        hum.ifPresent(System.out :: println);



        // very important util Function List to DTO
        List<StudentKVS> studentKVSList = Arrays.asList(new StudentKVS("Dog"),new StudentKVS("Tree"), new StudentKVS("Moutain"));
        //Function
        Function<List<StudentKVS>, StudentResponse> resposeFunction = studentkvs -> {

            StudentResponse response = new StudentResponse();
            response.setName(new ArrayList<String>(studentkvs.stream().map(m->m.getName()).toList()));
            //OR
            //setFirstField
            //setSecondField
            //...so on


            //Or BeanUtil.copyProperties(sources, dtoRef);
            return response;
        };
        System.out.println("responseFunction " +resposeFunction.apply(studentKVSList));


    }


}
class StudentResponse{

    private List<String> name;

    public StudentResponse(List<String> name) {
        this.name = name;
    }
    public StudentResponse(){}

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}
class StudentKVS{
    private String name;

    public StudentKVS(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}