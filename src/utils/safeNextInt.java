package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class safeNextInt {
    static public int nextInt(Scanner scanner){
        int res = -1;
        try {
            res = scanner.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Invalid input");
            scanner.next();
        }
        return res;
    }
}
