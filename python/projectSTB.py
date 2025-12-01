from threading import Thread, Condition
from time import sleep


class WhiteBoard:
    def __init__(self):
        self.text = ""
        self.number_of_students = 0
        self.count = 0
        self.cv = Condition()

    def attendance(self):
        with self.cv:
            self.number_of_students += 1

    # Teacher writes
    def write(self, t):
        with self.cv:
            print("Teacher is Writing:", t)

            # Wait until all students have read previous text
            while self.count != 0:
                self.cv.wait()

            self.text = t
            self.count = self.number_of_students  # reset count
            self.cv.notify_all()                  # notify all students

    # Students read
    def read(self):
        with self.cv:
            # Students wait until teacher writes something
            while self.count == 0:
                self.cv.wait()

            t = self.text
            self.count -= 1

            # If last student has read, notify teacher
            if self.count == 0:
                self.cv.notify()

            return t


class Teacher(Thread):
    def __init__(self, wb):
        super().__init__()
        self.wb = wb
        self.notes = [
            "Java is a language",
            "It is OOP",
            "It is platform independent",
            "It supports threads",
            "end"
        ]

    def run(self):
        for note in self.notes:
            self.wb.write(note)
            sleep(0.5)


class Student(Thread):
    def __init__(self, name, wb):
        super().__init__()
        self.name = name
        self.wb = wb

    def run(self):
        self.wb.attendance()

        while True:
            text = self.wb.read()
            print(self.name, "reading:", text)

            if text == "end":
                break

            sleep(0.2)


class TeacherStudentWhiteBoardProject:
    @staticmethod
    def main():
        wb = WhiteBoard()
        t = Teacher(wb)

        s1 = Student("1. John", wb)
        s2 = Student("2. Ajay", wb)
        s3 = Student("3. Kloob", wb)
        s4 = Student("4. Smith", wb)

        t.start()
        s1.start()
        s2.start()
        s3.start()
        s4.start()

        t.join()
        s1.join()
        s2.join()
        s3.join()
        s4.join()


if __name__ == "__main__":
    TeacherStudentWhiteBoardProject.main()
