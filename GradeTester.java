import java.util.ArrayList;
import java.util.Scanner;


class SchoolClass{
    static Scanner in = new Scanner(System.in);

    private String[] parameters;
    private int[] perOfTotal;
    private double grade;
    private ArrayList<ArrayList<Double>> gradeOnAssignment;
    private double[] eachCatGrade; //creates a list for all the grades in a certain param
    private String nameOfClass;


    /**
     * This is a constructor, in which it asks for all the parameters that make up a class grade
     * it also calls another function which gets all the grades for a specific parameter (ex. all the hw grades)
     * it takes those and finds the averages, and then calculates the final grade in a class
     * @param howManyParam takes in how many different things factor into a grade
     * @param nameOfClass the name of the class (ex. math, science, etc)
     */
    SchoolClass(int howManyParam, String nameOfClass){
        parameters= new String[howManyParam];
        perOfTotal= new int[howManyParam];
        eachCatGrade= new double[howManyParam];
        this.gradeOnAssignment = new ArrayList<>();
        this.nameOfClass= nameOfClass;

        double count=0; //this will start a count to check if all the % add to 100
        do { //will run once first to take in all the param % for grade in class
            for (int i = 0; i < howManyParam; i++) { //checks param name then the percent
                System.out.print("What is the " + (i + 1) + " parameter that contributes to your grade: ");
                parameters[i] = in.next();
                System.out.print("How much of your grade does it make up: ");
                perOfTotal[i] = in.nextInt();
                count += perOfTotal[i]; //adds it to count to check later
            }
            if(count!=100){ //if its not 100 it will tell the user to do it again
                System.out.println("this only adds to "+count+"%. Input again");
            }
        }while(count!=100); //resets the asking part


        int answer;
        do { //you can run multiple different grades with the same param, as long as you type 1 at the end
            eachCatGrade= new double[howManyParam];
            gradeOnAssignment= new ArrayList<>();
            grade=0;

            int avgTemp; //establishes an avg grade for each param, it will change as it moves tho diff param

            for (int i = 0; i < parameters.length; i++) { //this runs through each param
                gradeOnAssignment.add(calcGradeIn(parameters[i])); //first it calls calcGradeIn to get every grade in a specific param
                avgTemp = 0; //sets the avg grade to 0 before calculating
                for (Double y : gradeOnAssignment.get(i)) { //it adds all the grades in the specific param to the avg
                    avgTemp += y;
                }
                eachCatGrade[i] = ((double) avgTemp / gradeOnAssignment.get(i).size()); //then it finds the avg grade and sets it to the list
            } //this repeats until it has found the avg grade of each parameter

            double totalTemp = 0; //this will find the correct final class grade, starts by setting a temp value to 0
            for (int i = 0; i < howManyParam; i++) { //it runs through each parameter
                double x = Double.parseDouble(String.format("%.2f", eachCatGrade[i]));
                totalTemp += (x * ((double) perOfTotal[i] / 100)); //it multiplies each average grade by how much of a % it
                //makes of a total grade and adds it to the total
            }
            grade = Double.parseDouble(String.format("%.2f", totalTemp)); //sets grade equal to what it calculated, but rounds to 2 decimal places
            showTable();
            System.out.print("If you would like to do this again with the same param type 1, otherwise type 0: ");
            answer= in.nextInt();
        }while(answer==1);
    }

    /**
     * this will get all the grades in a specific parameter, it will then add it to a ArrayList
     * @param assignmentType what parameter it is calculating for
     * @return gives back an ArrayList of all the grades in a category
     */
    public ArrayList<Double> calcGradeIn(String assignmentType){
        ArrayList<Double> tempHold = new ArrayList<>(); //makes a temporary holding list to house every grade in a specific parameter like all the hw grades
        System.out.println("In "+assignmentType+" add what you got on each assignment, when done type -1");
        System.out.print("Grade: ");
        double x= in.nextDouble();
        while(x!=-1){ //as long as the user docent type in -1 it will keep on asking for more grades
            tempHold.add(x);
            System.out.print("Grade: ");
            x= in.nextDouble();
        }
        //then it returns all the grades it gathered
        return tempHold;
    }

    public void showTable(){
        System.out.println(nameOfClass+" Grade Tester, Overall Grade: "+grade+"%");
        for(int i=0;i<parameters.length; i++){
            System.out.println(parameters[i]+" " +perOfTotal[i]+"% : "+eachCatGrade[i]);
            for(double j: gradeOnAssignment.get(i)){
                System.out.print(j+", ");
            }
            System.out.println();
            System.out.println();
        }
    }

    //these are all the getters if needed when modifying code
    public double[] getEachCatGrade() {
        return eachCatGrade;
    }
    public ArrayList<ArrayList<Double>> getGradeOnAssignment(){
        return gradeOnAssignment;
    }
    public String[] getParameters(){
        return parameters;
    }
    public int[] getPerOfTotal(){
        return perOfTotal;
    }
    public double getGrade(){
        return grade;
    }
    public String getNameOfClass(){
        return nameOfClass;
    }
}


public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("What class is this in: ");
        String className= in.next();

        System.out.print("How many grading parameters does "+className+" have: ");
        int param= in.nextInt();
        new SchoolClass(param, className);

    }
}
