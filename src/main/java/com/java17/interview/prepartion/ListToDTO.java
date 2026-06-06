package com.java17.interview.prepartion;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ListToDTO {
    public static void main(String[] args) {

        //List<T> to DTO

        List<StudentSysmbosys> studentSysmbosys = Arrays.asList(new StudentSysmbosys("Dog"),new StudentSysmbosys("Tree"), new StudentSysmbosys("Moutain"));
        Function<List<StudentSysmbosys>, StudentSysmbosysDTO> resposeFunction = sysmbosys -> {

            StudentSysmbosysDTO respose = new StudentSysmbosysDTO();
            respose.setName(new ArrayList<String>(sysmbosys.stream().map(m->m.getName()).toList()));
            //respose.setOtherKey1
            //respose.setOtherKey2...
            return respose;
        };
        System.out.println("StudentSysmbosysDTO " +resposeFunction.apply(studentSysmbosys));


    }

}
@Data
class StudentSysmbosysDTO{

    private List<String> name;

}
class StudentSysmbosys{
    private String name;

    public StudentSysmbosys(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
