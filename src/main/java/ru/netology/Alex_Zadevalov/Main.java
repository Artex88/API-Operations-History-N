package ru.netology.Alex_Zadevalov;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private final static int MAX_OPERATION = 300;
    private final static int MAX_CUSTOMERS = 100;
    static StorageService<Customer> customerStorageService = new StorageService<>();
    static StorageService<Operation> operationStorageService = new StorageService<>();
    private static final int[] customer_operations_count = new int[MAX_CUSTOMERS];


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerService.inputCustomer(scanner, MAX_CUSTOMERS, customerStorageService);
        OperationService.inputOperation(operationStorageService, MAX_OPERATION, customerStorageService, customer_operations_count, MAX_CUSTOMERS, StorageService.getStatement());
        IOService.CheckCustomerAllOperations(scanner, customer_operations_count, operationStorageService, StorageService.getStatement());
        IOService.getFinalOutput(customerStorageService, operationStorageService, StorageService.getStatement(), customer_operations_count);
    }


}
