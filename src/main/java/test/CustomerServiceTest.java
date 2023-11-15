package test;

import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.netology.Alex_Zadevalov.Customer;
import ru.netology.Alex_Zadevalov.CustomerService;
import ru.netology.Alex_Zadevalov.StorageService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Test
    public void testInputCustomer() {
        StorageService<Customer> mockCustomers = new StorageService<>();
        // Создаем объект StorageService<Customer>
        StorageService<Customer> customers = new StorageService<>();

        // Имитируем ввод пользователя с помощью ByteArrayInputStream
        Scanner scanner = new Scanner(System.in);

        // Вызываем метод inputCustomer
        CustomerService.inputCustomer(scanner, 2, customers);

        // Проверяем, что количество клиентов соответствует ожидаемому
        assertEquals(2, customers.getStorage().size());

        // Проверяем, что методы getElement и setElement были вызваны правильное количество раз
        verify(mockCustomers, times(2)).getElement(anyInt());
        verify(mockCustomers, times(2)).setElement(anyInt(), any(Customer.class));

        // Можно также добавить дополнительные проверки по содержанию
        // Например, можно проверить, что имена клиентов соответствуют ожидаемым значениям
        assertEquals("John", customers.getElement(0).getName());
        assertEquals("Jane", customers.getElement(1).getName());
    }
}
