package github.JulianNSH;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Scanner;
import static java.lang.System.exit;

/* NOTE
* At this phase all classes are built according to the same principle and perform similar functions
 */

public class Employees {
    //declaring the basic variables(arrays) of the entity

    public static String[] nameArr;// array for holding info in memory

    public static String[] surnameArr;

    public static  String[] positionArr;

    public static  int[] ageArr;

    public static  double[] salaryArr;

    Employees(){}
    //method that alloc array dimensions, used for importing data from XML file
    public void allocateMemory(int index){
        nameArr = new String[index];
        surnameArr = new String[index];
        positionArr = new String[index];
        ageArr = new int[index];
        salaryArr = new double[index];
    }

    int i;
    Scanner scn = new Scanner(System.in);

    //menu method which is called in the main menu
    public int employeeMenu(){

        System.out.println("\n+=========EMPLOYEES=========+");
        System.out.println("|<1>Create                  |");
        System.out.println("|<2>Show                    |");
        System.out.println("|<3>Modify                  |");
        System.out.println("|<4>Delete                  |");
        System.out.println("|<5>                  <<Back|");
        System.out.println("|<0>Exit                    |");
        System.out.println("+===========================+");
        System.out.println("Enter Option>>> ");

        i=scn.nextInt();
        switch (i){
            case 1: createEmployeesData(); break;
            case 2: showEmployeesData();  break;
            case 3: modifyEmployeesData(); break;
            case 4: deleteEmployeesData(); break;
            case 5: return 0; // return to the previous menu
            case 0: exit(0); break; //forced exit
            default:
                System.out.println("!!!Invalid Input!!!"); employeeMenu(); //recursive call
        }
        return 0;
    }
    //method for creating data
    private void createEmployeesData(){
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
            assignInputToArray(j);
        }

        employeeMenu(); //return to the previous menu
    }

    private void modifyEmployeesData(){
        //check if data arrays isn't empty
        if(nameArr != null){
            System.out.println("\n+======EMPLOYEES MODIFY======+");
            System.out.println(" Input ID to modify>>> ");
            int i = scn.nextInt()-1;

            //check if array with input ID exists
            if(i>=0 && i<nameArr.length){
                assignInputToArray(i);
                System.out.println("~Employee data was modified successfully~");
            } else {
                System.out.println("!!!Input ID doesn't exist!!!");
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        employeeMenu();
    }

    private void deleteEmployeesData(){
        //check if data arrays isn't empty
        if(nameArr != null){
            System.out.println("\n+======EMPLOYEES DELETE======+");
            System.out.println(" Input ID to delete>>> ");
            int i = scn.nextInt()-1;

            //check if array with input ID exists
            if(i>=0 && i<nameArr.length){
                nameArr = ArrayUtils.remove(nameArr, i); //using method from imported library
                surnameArr = ArrayUtils.remove(surnameArr, i);
                positionArr = ArrayUtils.remove(positionArr, i);
                ageArr = ArrayUtils.remove(ageArr, i);
                salaryArr = ArrayUtils.remove(salaryArr, i);

                System.out.println("~Employee data was deleted successfully~");

            } else {
                System.out.println("!!!Input ID doesn't exist!!!");
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        employeeMenu();
    }

    //method for output the data in console
    private void showEmployeesData(){
        System.out.println("\n+======LIST OF EMPLOYEES======+");
        System.out.print(  "|ID  |Name      |Surname   |Position  |Age|Salary  |\n");
        System.out.println("|--------------------------------------------------|");

        //output the data if array isn`t empty
        if(nameArr != null) {
           for(int j=0; j<nameArr.length; j++) {

               System.out.printf("|%-4s", j+1); System.out.printf("|%-10s",nameArr[j]); System.out.printf("|%-10s",surnameArr[j]);
               System.out.printf("|%-10s",positionArr[j]);System.out.printf("|%-3s",ageArr[j]); System.out.printf("|%-8s|\n",salaryArr[j]);
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        System.out.println("|--------------------------------------------------|");
        employeeMenu();
    }

    //method for input data, used to avoid code repeat
    private void assignInputToArray(int index){
        System.out.printf("Employee ID-%s\n", index+1);
        System.out.println("*Name: ");
        nameArr[index] = scn.next();
        System.out.println("*Surname: ");
        surnameArr[index] = scn.next();
        System.out.println("*Position: ");
        positionArr[index] = scn.next();
        System.out.println("*Age: ");
        ageArr[index] = scn.nextInt();
        System.out.println("*Salary: ");
        salaryArr[index] = scn.nextDouble();
    }
}
