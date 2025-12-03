class WhiteBoard {

    private String text;
    private int numberOfStudents = 0;
    private int count = 0;

    // Register each student
    public void attendance() {
        numberOfStudents++;
    }

    // Teacher writes
    synchronized public void write(String t) {
        System.out.println("Teacher is Writing: " + t);

        // Wait until all students have read the previous text
        while (count != 0) {
            try {
                wait();
            } catch (Exception e) {
            }
        }

        text = t;
        count = numberOfStudents; // reset count for new text
        notifyAll(); // wake up all students
    }

    // Students read
    synchronized public String read() {
        // Wait until teacher writes something
        while (count == 0) {
            try {
                wait();
            } catch (Exception e) {
            }
        }

        String t = text;
        count--;

        // If last student has read, notify teacher
        if (count == 0) {
            notify();
        }

        return t;
    }
}

class Teacher extends Thread {

    private WhiteBoard wb;
    private String notes[] = {
            "Java is a language",
            "It is OOP",
            "It is platform independent",
            "It supports threads",
            "end"
    };

    public Teacher(WhiteBoard w) {
        wb = w;
    }

    public void run() {
        for (String note : notes) {
            wb.write(note);
        }
    }
}

class Student extends Thread {

    private String name;
    private WhiteBoard wb;

    public Student(String n, WhiteBoard w) {
        name = n;
        wb = w;
    }

    public void run() {
        String text;
        wb.attendance();

        do {
            text = wb.read();
            System.out.println(name + " reading: " + text);
            System.out.flush();
        } while (!text.equals("end"));
    }
}

public class TeacherStudentWhiteBoardProject {

    public static void main(String[] args) {

        WhiteBoard wb = new WhiteBoard();
        Teacher t = new Teacher(wb);

        Student s1 = new Student("1. John", wb);
        Student s2 = new Student("2. Ajay", wb);
        Student s3 = new Student("3. Kloob", wb);
        Student s4 = new Student("4. Smith", wb);

        t.start();

        s1.start();
        s2.start();
        s3.start();
        s4.start();
    }
}
