package cacheapp.phonepe.com.cacheapp.viewmodels;

import java.util.LinkedHashMap;
import java.util.Map;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by kapilbakshi on 29/05/17.
 */

public class MainViewModel {

    private final static int CACHE_MAX_SIZE = 10;
    private LinkedHashMap<Integer, Integer> cacheMap;
    private PublishSubject<Boolean> inCacheSubject = PublishSubject.create();

    public void initializeMap() {
        cacheMap = new LinkedHashMap<Integer, Integer>(CACHE_MAX_SIZE) {
            @Override
            protected boolean removeEldestEntry(
                    Map.Entry<Integer, Integer> eldest) {
                return size() > CACHE_MAX_SIZE;
            }
        };
    }

    /**
     * Checks if the number is in the cache or not. If it is then it simply makes the view aware via observable
     * else it puts it inside the cache by replacing it with the eldest entry if the cache is full
     * @param number
     *              is the number to be checked
     */
    public void checkInCache(Integer number) {
        boolean cacheContainsNumber = cacheMap.containsKey(number);
        inCacheSubject.onNext(cacheContainsNumber);
        if(!cacheContainsNumber) {
            cacheMap.put(number,1);
        }
    }

    public Observable<Boolean> getInCacheObservable() {
        return inCacheSubject;
    }

}
