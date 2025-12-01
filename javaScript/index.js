class WhiteBoard {
    constructor() {
        this.text = "";
        this.numberOfStudents = 0;
        this.count = 0;

        this.waitingTeacher = [];
        this.waitingStudents = [];
    }

    attendance() {
        this.numberOfStudents++;
    }

    // Helper: creates a promise and stores its resolver
    async wait(list) {
        return new Promise(resolve => list.push(resolve));
    }

    notifyAll(list) {
        while (list.length > 0) {
            const resolve = list.shift();
            resolve();
        }
    }

    notify(list) {
        if (list.length > 0) {
            const resolve = list.shift();
            resolve();
        }
    }

    async write(t) {
        console.log("Teacher is Writing:", t);

        // Wait while students are still reading
        while (this.count !== 0) {
            await this.wait(this.waitingTeacher);
        }

        // New text
        this.text = t;
        this.count = this.numberOfStudents;

        // Notify all students
        this.notifyAll(this.waitingStudents);
    }

    async read() {
        // Wait for teacher to write something
        while (this.count === 0) {
            await this.wait(this.waitingStudents);
        }

        const t = this.text;
        this.count--;

        // Last student notifies teacher
        if (this.count === 0) {
            this.notify(this.waitingTeacher);
        }

        return t;
    }
}


// ----------------------------
// Teacher Thread
// ----------------------------
class Teacher {
    constructor(wb) {
        this.wb = wb;
        this.notes = [
            "Java is a language",
            "It is OOP",
            "It is platform independent",
            "It supports threads",
            "end"
        ];
    }

    async start() {
        for (const note of this.notes) {
            await this.wb.write(note);
        }
    }
}


// ----------------------------
// Student Thread
// ----------------------------
class Student {
    constructor(name, wb) {
        this.name = name;
        this.wb = wb;
    }

    async start() {
        this.wb.attendance();

        let text = "";

        do {
            text = await this.wb.read();
            console.log(this.name + " reading:", text);
        } while (text !== "end");
    }
}


// ----------------------------
// MAIN
// ----------------------------
async function main() {
    const wb = new WhiteBoard();

    const teacher = new Teacher(wb);
    const students = [
        new Student("1. John", wb),
        new Student("2. Ajay", wb),
        new Student("3. Kloob", wb),
        new Student("4. Smith", wb)
    ];

    // Start students first (like Java)
    students.forEach(s => s.start());

    // Start teacher
    await teacher.start();
}

main();
