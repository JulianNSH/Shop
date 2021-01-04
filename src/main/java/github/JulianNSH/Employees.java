package github.JulianNSH;

import java.util.Scanner;

import static java.lang.System.exit;

public class Employees {
    int idemployee;
    String name;
    String surname;
    String position;
    int age;
    double salary;

    Employees(){}

    int i = 0;
    Scanner scn = new Scanner(System.in);

    int employeeMenu(){
        System.out.println("\n+=========EMPLOYEES=========+");
        System.out.println("|<1>Create                  |");
        System.out.println("|<2>Show                    |");
        System.out.println("|<3>                  <<Back|");
        System.out.println("|<0>Exit                    |");
        System.out.println("+===========================+");
        System.out.println("Enter Option>>> ");

        i=scn.nextInt();
        switch (i){
            case 1: createEmployeesData(); break;
            case 2: showEmployeesData();  break;
            case 3: return 0;
            case 0: exit(0); break;
            default:
                System.out.println("!!!Invalid Input!!!"); employeeMenu();
        }
        return 0;
    }
    void createEmployeesData(){

        employeeMenu();
    }
    void showEmployeesData(){
        employeeMenu();
    }

}
