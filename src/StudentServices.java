void main(){
    var scanner = new Scanner(System.in);
    var services = new StudentServices();

    while (true) {
        IO.println("\n---Student Management System---");
        IO.println("1. Add Students");
        IO.println("2. View Students");
        IO.println("3. Search Students");
        IO.println("4. Delete Students");
        IO.println("5. exits");
        IO.println("Choose Options:");

        IO.println("Enter a options:");
        var choose = scanner.nextInt();

        switch (choose) {
            case 1 -> {
                IO.println("Enter a ID:");
                var id = scanner.nextInt();
                IO.println("Enter a name:");
                scanner.nextLine();
                var name = scanner.nextLine();
                IO.println("Enter a age:");
                var age = scanner.nextInt();
                services.addStudent(new Student(id,name,age));
            }
            case 2 -> services.viewStudent();
            case 3 -> {
                IO.println("Enter a student Id:");
                var id = scanner.nextInt();
                services.searchStudent(id);
            }
            case 4 -> {
                IO.println("Enter a student ID to delete:");
                var id = scanner.nextInt();
                services.deleteStudent(id);
            }
            case 5 -> {
                IO.println("Existing...");
                return;
            }
            default -> IO.println("Invalid Options!");
        }
    }
}

public class StudentServices{
    private List<Student> students = new ArrayList<>();

    void addStudent(Student s){
        students.add(s);
        IO.println("Student added Successfully");
    }

    void viewStudent(){
        if(students.isEmpty()){
            IO.println("Students data not founded!");
            return;
        }
        students.forEach(IO::println);
    }

    void searchStudent(int id){
        for(Student data : students){
            if(data.id() == id){
                IO.println(data);
                return;
            }
        }
        IO.println("Student not found");
    }

    void deleteStudent(int id){
        students.removeIf(studentId -> studentId.id == id);
        IO.println("Student deleted if existed!");
    }
}

private record Student(int id,String name,int age) { }