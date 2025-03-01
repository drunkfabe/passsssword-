import java.security.SecureRandom;
import java.util.Scanner;

public class Main {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "1234567890";
    private static final String SYMBOLS = "!@#$%^&*()-_=+<>?/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();

        //инпут
        System.out.print("Enter minimum password length: ");
        int minLength = scanner.nextInt();
        System.out.print("Enter maximum password length: ");
        int maxLength = scanner.nextInt();

        System.out.print("Include lowercase letters? (yes/no): ");
        boolean useLower = scanner.next().equalsIgnoreCase("yes");
        System.out.print("Include uppercase letters? (yes/no): ");
        boolean useUpper = scanner.next().equalsIgnoreCase("yes");
        System.out.print("Include numbers? (yes/no): ");
        boolean useNumbers = scanner.next().equalsIgnoreCase("yes");
        System.out.print("Include symbols? (yes/no): ");
        boolean useSymbols = scanner.next().equalsIgnoreCase("yes");

        //пароль
        String password = generatePassword(minLength, maxLength, useLower, useUpper, useNumbers, useSymbols, random);
        System.out.println("Generated Password: " + password);

        scanner.close();
    }

    public static String generatePassword(int minLength, int maxLength, boolean useLower, boolean useUpper, boolean useNumbers, boolean useSymbols, SecureRandom random) {
        StringBuilder charPool = new StringBuilder();
        if (useLower) charPool.append(LOWERCASE);
        if (useUpper) charPool.append(UPPERCASE);
        if (useNumbers) charPool.append(NUMBERS);
        if (useSymbols) charPool.append(SYMBOLS);

        if (charPool.length() == 0) {
            throw new IllegalArgumentException("At least one character type must be selected.");
        }

        int passwordLength = random.nextInt(maxLength - minLength + 1) + minLength;
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            int index = random.nextInt(charPool.length());
            password.append(charPool.charAt(index));
        }

        return password.toString();
    }
}