package github.JulianNSH;
import java.util.Scanner;
import static java.lang.System.exit;

/* NOTE
* At this phase all classes are built according to the same principle and perform similar functions
 */

public class Employees {
    //declaring the basic variables of the entity
    int idemployee; // will be used for linking in DB

    String name;// also for DB
    String[] nameArr;// actual for holding info in memory

    String surname;
    String[] surnameArr;

    String position;
    String[] positionArr;

    int age;
    int[] ageArr;

    double salary;
    double[] salaryArr;

    Employees(){}

    int i;
    Scanner scn = new Scanner(System.in);

    //menu method which is called in the main menu
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
            case 3: return 0; // return to the previous menu
            case 0: exit(0); break; //forced exit
            default:
                System.out.println("!!!Invalid Input!!!"); employeeMenu(); //recursive call
        }
        return 0;
    }
    //method for creating data
    void createEmployeesData(){
        System.out.println("\n+======EMPLOYEES CREATE======+");
        System.out.println(" Number Of Employees>>> ");
        int i = scn.nextInt();

        //memory allocation in declared arrays
        nameArr = new String[i];
        surnameArr = new String[i];
        positionArr = new String[i];
        ageArr = new int[i];
        salaryArr = new double[i];

        //completing the arrays
        for (int j = 0; j<i; j++){
            System.out.printf("Employee Nr-%s\n", j+1);
            System.out.println("*Name: ");
            nameArr[j] = scn.next();
            System.out.println("*Surname: ");
            surnameArr[j] = scn.next();
            System.out.println("*Position: ");
            positionArr[j] = scn.next();
            System.out.println("*Age: ");
            ageArr[j] = scn.nextInt();
            System.out.println("*Salary: ");
            salaryArr[j] = scn.nextDouble();
        }

        employeeMenu(); //return to the previous menu
    }

    //method for output the data in console
    void showEmployeesData(){
        System.out.println("\n+======LIST OF EMPLOYEES======+");
        System.out.print(  "|Name      |Surname   |Position  |Age|Salary  |\n");
        System.out.println("|---------------------------------------------|");

        //output the data if array isn`t empty
        if(nameArr != null) {
           for(int j=0; j<nameArr.length; j++) {

               System.out.printf("|%-10s",nameArr[j]); System.out.printf("|%-10s",surnameArr[j]); System.out.printf("|%-10s",positionArr[j]);
               System.out.printf("|%-3s",ageArr[j]); System.out.printf("|%-8s|\n",salaryArr[j]);
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        System.out.println("|---------------------------------------------|");
        employeeMenu();
    }

}
