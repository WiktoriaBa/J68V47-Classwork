public class L12E1 {
    public static void displayStudent(Student thisStudent){
        System.out.format("(%d) %s %s %n",thisStudent.id, thisStudent.firstName, thisStudent.surname);
    }
    public static void main(String[] args) {
        Student student1 = new Student();
        displayStudent(student1);

        student1.firstName = "Witkoria";
        student1.surname = "Bardzik";
        student1.id = 30160329;
        student1.present = true;
        displayStudent(student1);


    }
}
