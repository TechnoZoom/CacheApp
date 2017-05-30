package cacheapp.phonepe.com.cacheapp.viewmodels;

import java.util.LinkedHashMap;
import java.util.Map;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by kapilbakshi on 29/05/17.
 */

public class MainViewModel {

    private LinkedHashMap<Integer, Integer> cacheMap;
    private PublishSubject<Boolean> inCacheSubject = PublishSubject.create();

    public void initializeMap(int size) {
        cacheMap = new LinkedHashMap<Integer, Integer>(size) {
            @Override
            protected boolean removeEldestEntry(
                    Map.Entry<Integer, Integer> eldest) {
                return size() > size;
            }
        };
    }

    /**
     * Checks if the number is in the cache or not. If it is then it simply makes the view aware via observable
     * else it puts it inside the cache by replacing it with the eldest entry if the cache is full
     * @param number
     *              is the number to be checked
     */
    public boolean checkInCache(Integer number) {
        boolean cacheContainsNumber = cacheMap.containsKey(number);
        inCacheSubject.onNext(cacheContainsNumber);
        if(!cacheContainsNumber) {
            cacheMap.put(number,1);
        }
        return cacheContainsNumber;
    }

    public Observable<Boolean> getInCacheObservable() {
        return inCacheSubject;
    }

    public LinkedHashMap<Integer, Integer> getCacheMap() {
        return cacheMap;
    }

}
