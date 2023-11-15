package test;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.netology.Alex_Zadevalov.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OperationServiceTest {

    @Test
    public void testGetOperations() {
        StorageService<Operation> mockOperations = new StorageService<>();
        StorageService<Customer> mockCustomers = new StorageService<>();
        // Подготавливаем данные для теста
        int customerId = 1;
        int[] customerOperationsCount = {2};
        int[][] statement = {{0, 1}};

        // Имитируем getElement для возвращения валидных операций
        when(mockOperations.getElement(anyInt())).thenReturn(new Operation(0, 100, "USD", "Amazon"), new Operation(1, 150, "EUR", "eBay"));

        // Вызываем метод getOperations
        Operation[] result = OperationService.getOperations(customerId, customerOperationsCount, mockOperations, statement);

        // Проверяем, что метод getElement был вызван правильное количество раз
        verify(mockOperations, times(2)).getElement(anyInt());

        // Проверяем, что возвращенный массив операций соответствует ожиданиям
        assertEquals(2, result.length);
        assertEquals(100, result[0].getSum());
        assertEquals(150, result[1].getSum());
    }

    @Test
    public void testInputOperation() {
        // Подготавливаем ввод пользователя
        StorageService<Operation> mockOperations = new StorageService<>();
        StorageService<Customer> mockCustomers = new StorageService<>();
        String input = "100\nUSD\nAmazon\nY\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());


        // Вызываем метод inputOperation
        OperationService.inputOperation(mockOperations, 5, mockCustomers, new int[]{2}, 3, new int[][]{{0, 1}});

        // Проверяем, что методы setElement и updateStatement были вызваны правильное количество раз
        verify(mockOperations, times(1)).setElement(anyInt(), any(Operation.class));
        verify(mockCustomers, times(1)).getElement(anyInt());
        verify(mockCustomers, times(1)).setElement(anyInt(), any(Customer.class));
    }

    // Тест ImplementCustomerToOperation может быть написан по аналогии с testInputOperation
}

