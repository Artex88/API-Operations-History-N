package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.Alex_Zadevalov.Operation;
import ru.netology.Alex_Zadevalov.StorageService;

public class StorageServiceTest {
    @Test
    public void getCustomerTest() {

        StorageService<Operation> operationStorageService = new StorageService<>();
        int sum = 100;
        String currency = "RUB";
        String merchant = "Shoko";
        Operation operation = new Operation(sum, currency, merchant);

        operationStorageService.setElement(operation);

        Operation storageOperation = operationStorageService.getElement(0);
        Assertions.assertEquals(sum, storageOperation.getSum());
        Assertions.assertEquals(merchant, storageOperation.getMerchant());
        Assertions.assertEquals(merchant, storageOperation.getMerchant());
    }
}
