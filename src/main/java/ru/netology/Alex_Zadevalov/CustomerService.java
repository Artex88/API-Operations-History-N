package ru.netology.Alex_Zadevalov;

import java.util.Scanner;

public class CustomerService {
    public static void inputCustomer(Scanner scanner, int MAX_CUSTOMERS, StorageService<Customer> customers)
    {
        int customersCount = 0;
        while (true)
        {
            System.out.println("Customer name: ");
            String name = scanner.nextLine();

            System.out.println("Do you want to enter next Customer? Y/N");
            String answer = scanner.nextLine();

            if (answer.equals("N"))
            {
                break;
            }
            else if (customersCount == MAX_CUSTOMERS)
            {
                break;
            }

            if (customers.getElement(customersCount) != null)
            {
                while (customers.getElement(customersCount) != null)
                    customersCount++;
                Customer customer = new Customer(customersCount ,name);
                customers.setElement(customersCount,customer);
            }
            Customer customer = new Customer(customersCount ,name);
            customers.setElement(customersCount,customer);
            customersCount++;
        }
    }
}
