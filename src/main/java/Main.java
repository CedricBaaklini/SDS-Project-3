import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SimulatedPasswordMemory memory = new SimulatedPasswordMemory();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Buffer Overflow Demonstration ===");
        System.out.print("Enter password: ");
        String userInput = scanner.nextLine();

        System.out.println("\n--- Safe Password Processing ---");
        boolean safeResult = memory.safePasswordProcessing(userInput);
        System.out.println("Safe processing result: " + safeResult);

        System.out.println("\n--- Unsafe Password Copy (Before) ---");
        memory.printState();

        System.out.println("\n--- Running unsafe password copy ---");
        memory.unsafePasswordCopy(userInput);

        System.out.println("\n--- Unsafe Password Copy (After) ---");
        memory.printState();

        System.out.println("\n=== Demonstrating Buffer Overflow ===");
        String overflowInput = "verylongpassword";
        System.out.println("Testing with input: '" + overflowInput + "'");

        memory.reset();
        System.out.println("\nBefore overflow:");
        memory.printState();

        memory.unsafePasswordCopy(overflowInput);
        System.out.println("\nAfter overflow:");
        memory.printState();

        scanner.close();
    }
}
