package ru.netology.Alex_Zadevalov;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class IOService {

    private static Scanner scanner;

    public static void getFinalOutput(StorageService<Customer> customers, StorageService<Operation> operations, int[][] statement, int[] customer_operations_count){
        System.out.println();
        System.out.println(" Customers: " + Arrays.deepToString(customers.getStorage().toArray(new Customer[0])));
        System.out.println(" Operations: " + Arrays.toString(operations.getStorage().toArray(new Operation[0])));
        System.out.println(" Statement: " + Arrays.deepToString(statement));
        System.out.println(" Operations count: " + Arrays.toString(customer_operations_count));
    }
    public static String[] readTransactionInput(){
        System.out.println("Currency: ");
        String currency = scanner.nextLine();

        System.out.println("Merchant: ");
        String merchant = scanner.nextLine();

        System.out.println("Do you want to enter next Operation? Y/N");
        String answer = scanner.nextLine();
        return new String[]{currency,merchant,answer};
    }

    public static int CheckCustomerExistence(Scanner scanner, StorageService<Customer> customers, int customerId) {
        if (customers.getElement(customerId) == null)
        {
            System.out.println("There is no client with this id!");
            System.out.println("If you want to create it, write create, if you want to change it, then write change");
            String answer = scanner.nextLine();

            if (answer.equals("create")){
                System.out.println("Customer name: ");
                String name = scanner.nextLine();
                customers.setElement(customerId, new Customer(customerId,name));
                System.out.println("User created!");
            }
            else if (answer.equals("change")) {
                while (customers.getElement(customerId) == null) {
                    System.out.println("Enter the correct id: ");
                    customerId = scanner.nextInt();
                    scanner.nextLine();
                }
                System.out.println("You have entered an existing id! Complete the next transaction!");
            }
        }
        return customerId;
    }
    public static void CheckCustomerAllOperations(Scanner scanner, int[] customer_operations_count, StorageService<Operation> operations, int[][] statement) {
        System.out.println("Do you want known all operation of certain customer? Y/N");
        String answer = scanner.nextLine();
        if (answer.equals("N")) {
            System.out.println("Ok, than get all information: ");
        } else if (answer.equals("Y")) {
            System.out.println("Please, write customer id: ");
            int customerId = scanner.nextInt();
            System.out.println("There are all his operations: ");
            System.out.println(Arrays.toString(OperationService.getOperations(customerId, customer_operations_count, operations, statement)));
        } else {
            System.out.println("Sorry, Error command.");
        }
    }
}
