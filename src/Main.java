import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("generate irish rail timetable");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter station code-defaults to ENFLD: ");
            String code = scanner.nextLine().trim();

            if (code.isEmpty()) {
                code = "ENFLD";
                System.out.print("Enter time window in minutes-defaults to 90(90 is max): ");
                String mins = scanner.nextLine().trim();
                if (mins.isEmpty()) {
                    mins = "90";
                }
                scanner.close();

                //fetch data
                //create HTML+save file
            }
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }
    //open xml auto in browser????
}