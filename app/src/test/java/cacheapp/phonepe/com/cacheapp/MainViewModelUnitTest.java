package cacheapp.phonepe.com.cacheapp;

import org.junit.Test;

import cacheapp.phonepe.com.cacheapp.viewmodels.MainViewModel;

import static org.junit.Assert.*;


public class MainViewModelUnitTest {

    @Test
    public void checkEldestDoesNotExistAfterCacheFull() throws Exception {
        MainViewModel mainViewModel = new MainViewModel();
        mainViewModel.initializeMap(3);
        TestUtilities.putElementsInCache(mainViewModel.getCacheMap(), new int[]{1, 2, 3, 4});
        assertTrue(mainViewModel.checkInCache(2));
        assertFalse(mainViewModel.checkInCache(1));
    }

    @Test
    public void checkCaseWhenElementInsertedMultipleTimes() throws Exception {
        MainViewModel mainViewModel = new MainViewModel();
        mainViewModel.initializeMap(4);
        TestUtilities.putElementsInCache(mainViewModel.getCacheMap(), new int[]{5, 5, 5, 1});
        assertTrue(mainViewModel.checkInCache(5));
        assertTrue(mainViewModel.checkInCache(1));
    }

    @Test
    public void checkNewlyEnteredValueDoesNotExist() throws Exception {
        MainViewModel mainViewModel = new MainViewModel();
        mainViewModel.initializeMap(5);
        TestUtilities.putElementsInCache(mainViewModel.getCacheMap(), new int[]{1, 2, 3, 4});
        assertFalse(mainViewModel.checkInCache(5));
        assertTrue(mainViewModel.checkInCache(4));
        assertTrue(mainViewModel.checkInCache(3));
        assertTrue(mainViewModel.checkInCache(2));
        assertTrue(mainViewModel.checkInCache(1));
    }

    @Test
    public void checkElementExistsAfterFirstEntry() throws Exception {
        MainViewModel mainViewModel = new MainViewModel();
        mainViewModel.initializeMap(5);
        assertFalse(mainViewModel.checkInCache(1));
        assertTrue(mainViewModel.checkInCache(1));
    }
}