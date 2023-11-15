package test;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.netology.Alex_Zadevalov.Customer;
import ru.netology.Alex_Zadevalov.IOService;
import ru.netology.Alex_Zadevalov.Operation;
import ru.netology.Alex_Zadevalov.StorageService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class IOServiceTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCheckCustomerExistenceCreate() {
        StorageService<Operation> mockOperations = new StorageService<>();
        StorageService<Customer> mockCustomers = new StorageService<>();
        // Подготавливаем ввод пользователя
        Scanner mockScanner = new Scanner(System.in);
        String input = "create\nJohn\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        when(mockScanner.nextLine()).thenReturn("create").thenReturn("John");

        // Имитируем вывод в консоль

        System.out.println(anyString());

        // Вызываем метод CheckCustomerExistence
        int result = IOService.CheckCustomerExistence(mockScanner, mockCustomers, 1);

        // Проверяем, что методы getElement и setElement были вызваны правильное количество раз
        verify(mockCustomers, times(1)).getElement(anyInt());
        verify(mockCustomers, times(1)).setElement(anyInt(), any(Customer.class));

        // Проверяем, что клиент был создан и возвращен ожидаемый результат
        assertEquals(1, result);
        verify(System.out).println("User created!");
    }

    @Test
    public void testCheckCustomerExistenceChange() {
        StorageService<Operation> mockOperations = new StorageService<>();
        StorageService<Customer> mockCustomers = new StorageService<>();
        // Подготавливаем ввод пользователя
        Scanner mockScanner = new Scanner(System.in);
        String input = "change\n2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        when(mockScanner.nextLine()).thenReturn("change").thenReturn("2");

        // Имитируем вывод в консоль

        System.out.println(anyString());

        // Имитируем getElement для первого вызова возвращая null, для второго - валидный элемент
        when(mockCustomers.getElement(anyInt())).thenReturn(null).thenReturn(new Customer(2, "Jane"));

        // Вызываем метод CheckCustomerExistence
        int result = IOService.CheckCustomerExistence(mockScanner, mockCustomers, 1);

        // Проверяем, что методы getElement и setElement были вызваны правильное количество раз
        verify(mockCustomers, times(2)).getElement(anyInt());
        verify(mockCustomers, times(1)).setElement(anyInt(), any(Customer.class));

        // Проверяем, что клиент был изменен и возвращен ожидаемый результат
        assertEquals(2, result);
        verify(System.out).println("You have entered an existing id! Complete the next transaction!");
    }

    @Test
    public void testCheckCustomerAllOperations() {
        StorageService<Operation> mockOperations = new StorageService<>();
        StorageService<Customer> mockCustomers = new StorageService<>();
        // Подготавливаем ввод пользователя
        Scanner mockScanner = new Scanner(System.in);
        String input = "Y\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        when(mockScanner.nextLine()).thenReturn("Y").thenReturn("1");

        // Имитируем вывод в консоль

        System.out.println(anyString());

        // Подготавливаем возвращаемое значение для getOperations
        when(mockOperations.getStorage()).thenReturn(Collections.singletonList(new Operation(1, 100, "USD", "Amazon")));

        // Вызываем метод CheckCustomerAllOperations
        IOService.CheckCustomerAllOperations(mockScanner, new int[]{1}, mockOperations, new int[][]{{1}});

        // Проверяем, что метод getOperations был вызван с правильными параметрами
        verify(mockOperations).getStorage();
        verify(System.out).println("There are all his operations: ");
        verify(System.out).println(Arrays.toString(new Operation[]{new Operation(1, 100, "USD", "Amazon")}));
    }

    @Test
    public void testReadTransactionInput() {
        // Подготавливаем ввод пользователя
        String input = "USD\nAmazon\nN\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        // Вызываем метод readTransactionInput
        String[] result = IOService.readTransactionInput();

        // Проверяем, что метод println был вызван трижды
        verify(System.out, times(3)).println(anyString());

        // Проверяем, что метод readTransactionInput вернул ожидаемый результат
        assertArrayEquals(new String[]{"USD", "Amazon", "N"}, result);
    }
}
