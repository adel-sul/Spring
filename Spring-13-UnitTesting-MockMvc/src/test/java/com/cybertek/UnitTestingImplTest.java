package com.cybertek;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UnitTestingImplTest {

    @Mock
    DataRepository dataRepository;

    @InjectMocks
    UnitTestingImpl unitTesting;

    @Test
    void calculateSum() {
        UnitTestingImpl unitTesting = new UnitTestingImpl();
        int actual = unitTesting.calculateSum(new int[]{1, 2, 3});
        assertEquals(6, actual);
    }

    @Test
    void calculateSumUsingDataService() {
        DataRepositoryImpl dataRepository = new DataRepositoryImpl();
        UnitTestingImpl unitTesting = new UnitTestingImpl(dataRepository);
        int actual = unitTesting.calculateSumUsingDataService();
        assertEquals(6, actual);

    }

    @Test
    void calculateSumUsingDataServiceMock() {

        when(dataRepository.findAll()).thenReturn(new int[]{2, 3, 4}).thenReturn(new int[]{5, 5, 5});
        int actual = unitTesting.calculateSumUsingDataService();
        int actual2 = unitTesting.calculateSumUsingDataService();
        assertEquals(9, actual);
        assertEquals(15, actual2);
    }

    @Test
    void calculateSumUsingDataServiceMockWithParameter() {

        when(dataRepository.findById(2)).thenReturn(new int[]{100, 10, 1});
        // generic parameter
//        when(dataRepository.findById(anyInt())).thenReturn(new int[]{100, 10, 1});
        int actual = unitTesting.calculateSumUsingDataServiceWithParameter();
         assertEquals(111, actual);

         // verify that findById(2) method was called and how many times
         verify(dataRepository).findById(2);
         verify(dataRepository, times(2)).findById(2);
    }

}