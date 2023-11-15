package ru.netology.Alex_Zadevalov;

import java.util.Scanner;

public class OperationService {

    public static Operation[] getOperations(int customerId, int[] customer_operations_count, StorageService<Operation> operations, int[][] statement)
    {
        Operation[] customerOperations = new Operation[customer_operations_count[customerId]];
        for (int j = 0; j < customer_operations_count[customerId]; j++)
            customerOperations[j] = operations.getElement(statement[customerId][j]);
        return customerOperations;
    }

    public static void inputOperation(StorageService<Operation> operations, int MAX_OPERATION, StorageService<Customer> customers,int[] customer_operations_count, int MAX_CUSTOMERS, int[][] statement) {
        Scanner scanner = new Scanner(System.in);
        int operationId = 0;
        while (true) {
            int sum = 0;
            System.out.println("Sum: ");
            try {
                sum = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Sum not a number.");
                scanner.nextLine();
                break;
            }
            scanner.nextLine();

            String[] input = IOService.readTransactionInput();

            if (input[2].equals("N")) {
                break;
            }

            ImplementCustomerToOperation(operationId, sum, input[0], input[1], operations, MAX_OPERATION, customers,customer_operations_count,MAX_CUSTOMERS,statement);
            operationId++;
        }
    }

        private static void ImplementCustomerToOperation(int operationId, int sum, String currency, String merchant,
                                                         StorageService<Operation> operations, int MAX_OPERATION,
                                                         StorageService<Customer> customers, int[] customer_operations_count, int MAX_CUSTOMERS, int[][] statement) {
        Scanner scanner = new Scanner(System.in);
        if (operationId == MAX_OPERATION)
        {
            System.out.println("Transaction limit reached!");
            throw new ArrayIndexOutOfBoundsException();
        }

        Operation operation = new Operation(operationId, sum, currency, merchant);
        operations.setElement(operationId, operation);

        System.out.println("ID of the customer who owns the operation: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

            customerId = IOService.CheckCustomerExistence(scanner, customers, customerId);
            StatementService.updateStatement(operationId, MAX_OPERATION, customer_operations_count, MAX_CUSTOMERS, statement, customerId);
        }
}
