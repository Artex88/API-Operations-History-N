package ru.netology.Alex_Zadevalov;

import ru.netology.Alex_Zadevalov.exception.CustomerOperationOutOfBoundException;

public class StatementService {
    public static void updateStatement(int operationId, int MAX_OPERATION, int[] customer_operations_count, int MAX_CUSTOMERS, int[][] statement, int customerId) {
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
