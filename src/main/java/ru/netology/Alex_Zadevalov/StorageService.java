package ru.netology.Alex_Zadevalov;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
 public class StorageService<T>{

     private final static int MAX_OPERATION = 300;
     private final static int MAX_CUSTOMERS = 100;
     private  final static int OPERATIONS_PER_CUSTOMER = MAX_OPERATION / MAX_CUSTOMERS;
     @Getter
     private final static  int[][] statement = new int[MAX_CUSTOMERS][OPERATIONS_PER_CUSTOMER];

    private  final List<T> storage = new ArrayList<>();

    public T getElement(int position) {
        return storage.get(position);
    }

    public void setElement(int position, T element) {
        storage.add(position, element);
    }

    public void setElement(T element) {
        storage.add(element);
    }
}
