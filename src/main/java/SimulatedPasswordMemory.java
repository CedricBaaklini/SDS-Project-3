import java.util.Arrays;

public class SimulatedPasswordMemory {
    private final char[] passwordBuffer = new char[8];

    private String role = "USER";
    private boolean accessGranted = false;

    public void printState() {
        System.out.println("=== Memory State ===");
        System.out.println("Password Buffer: ");
        for (char c : passwordBuffer) {
            if (c != '\0') {
                System.out.print(c);
            } else {
                System.out.print("_");
            }
        }

        System.out.println();
        System.out.println("Role: " + role);
        System.out.println("Access Granted: " + accessGranted);
        System.out.println("======================");
    }

    /*
      This method shows an unsafe and incoherent way of stopping a password from creating a Buffer Overflow.
       
       Basically, it copies the characters of the password and compares them.
       
       If the password inserted is greater than 8, it won't grant access, but it will elevate user privileges.
       
       However, if the input is exactly equal to the string "secret12", then the access will be granted.
       
       The point of this method is to mimic a buffer overflow without dealing with the memory as that would risk the safety of the computer.     
     */
    public void unsafePasswordCopy(String input) {
        Arrays.fill(passwordBuffer, '\0');

        for (int i = 0; i < input.length() && i < passwordBuffer.length; i++) {
            passwordBuffer[i] = input.charAt(i);
        }

        if (input.length() > 8) {
            role = "ADMIN";
            accessGranted = false;
        } else {
            accessGranted = input.equals("secret12");
        }
    }

    /*
      This method, on the other hand, is much more concise. 
      
      Not only will it check if a password is too long, but it will stop the input from being passed.
      
      On top of this, it will also see if the string is exactly equal to the hard-coded password.  
     */
    public boolean safePasswordProcessing(String input) {
        if (input.length() > 8) {
            System.out.println("Safe Processing: Password too long (rejected)");
            return false;
        }

        if (input.equals("secret12")) {
            System.out.println("Safe Processing: Password accepted");
            return true;
        } else {
            System.out.println("Safe Processing: Invalid password (rejected)");
            return false;
        }
    }

    public void reset() {
        Arrays.fill(passwordBuffer, '\0');

        role = "USER";
        accessGranted = false;
    }
}
