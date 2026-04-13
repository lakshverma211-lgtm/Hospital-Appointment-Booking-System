import java.util.Scanner;

// First year level project - simple OOP + arrays

class Doctor {
    int id;
    String name;
    String specialization;

    Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
}

class Patient {
    int id;
    String name;
    int age;

    Patient(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

class Appointment {
    Doctor doctor;
    Patient patient;
    String time;
    String status;

    Appointment(Doctor d, Patient p, String time) {
        this.doctor = d;
        this.patient = p;
        this.time = time;
        this.status = "Booked";
    }
}

class AppointmentManager {
    Appointment[] appointments = new Appointment[10];
    int count = 0;

    // check if slot is available
    boolean isAvailable(Doctor d, String time) {
        for (int i = 0; i < count; i++) {
            if (appointments[i].doctor.id == d.id &&
                appointments[i].time.equals(time) &&
                appointments[i].status.equals("Booked")) {
                return false;
            }
        }
        return true;
    }

    // book appointment
    void book(Doctor d, Patient p, String time) {
        if (isAvailable(d, time)) {
            appointments[count] = new Appointment(d, p, time);
            count++;
            System.out.println("Appointment booked successfully");
        } else {
            System.out.println("Slot not available");
        }
    }

    // cancel appointment
    void cancel(int patientId, String time) {
        for (int i = 0; i < count; i++) {
            if (appointments[i].patient.id == patientId &&
                appointments[i].time.equals(time) &&
                appointments[i].status.equals("Booked")) {

                appointments[i].status = "Cancelled";
                System.out.println("Appointment cancelled");
                return;
            }
        }
        System.out.println("Appointment not found");
    }

    // display all appointments
    void display() {
        System.out.println("\nAppointments List:");
        for (int i = 0; i < count; i++) {
            System.out.println("----------------------");
            System.out.println("Doctor: " + appointments[i].doctor.name);
            System.out.println("Patient: " + appointments[i].patient.name);
            System.out.println("Time: " + appointments[i].time);
            System.out.println("Status: " + appointments[i].status);
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // sample doctors
        Doctor d1 = new Doctor(1, "Dr Sharma", "Cardiology");
        Doctor d2 = new Doctor(2, "Dr Mehta", "Skin");

        // sample patients
        Patient p1 = new Patient(1, "Rahul", 20);
        Patient p2 = new Patient(2, "Amit", 22);

        AppointmentManager manager = new AppointmentManager();

        int choice;

        do {
            System.out.println("\n--- Hospital Menu ---");
            System.out.println("1. Book Appointment");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. Show Appointments");
            System.out.println("4. Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Enter Patient ID (1 or 2): ");
                    int pid = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Doctor ID (1 or 2): ");
                    int did = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Time (example 10AM): ");
                    String time = sc.nextLine();

                    Patient patient = (pid == 1) ? p1 : p2;
                    Doctor doctor = (did == 1) ? d1 : d2;

                    manager.book(doctor, patient, time);
                    break;

                case 2:
                    System.out.println("Enter Patient ID: ");
                    int cpid = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Time: ");
                    String ctime = sc.nextLine();

                    manager.cancel(cpid, ctime);
                    break;

                case 3:
                    manager.display();
                    break;

                case 4:
                    System.out.println("Thank you");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 4);

        sc.close();
    }
}
