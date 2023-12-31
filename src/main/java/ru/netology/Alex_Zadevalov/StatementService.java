package ru.netology.Alex_Zadevalov;

import ru.netology.Alex_Zadevalov.exception.CustomerOperationOutOfBoundException;

import static ru.netology.Alex_Zadevalov.StorageService.MAX_CUSTOMERS;
import static ru.netology.Alex_Zadevalov.StorageService.MAX_OPERATION;

public class StatementService {
    public static void updateStatement(int operationId, int[] customer_operations_count, int[][] statement, int customerId) {
        int operationCountForClient = customer_operations_count[customerId];

        if (operationCountForClient == MAX_OPERATION / MAX_CUSTOMERS)
            try {
                throw new CustomerOperationOutOfBoundException(customerId, operationId);
            } catch (CustomerOperationOutOfBoundException e) {
                throw new RuntimeException(e);
            }
        statement[customerId][operationCountForClient] = operationId;

        customer_operations_count[customerId]++;
    }
}
