package au.com.x4ax.nyc.service;

import java.util.Date;
import java.util.List;

/**
 * Defines functionality of NYC Cab data service
 */
public interface NyCabDataService {
    /**
     * Returns number of trips of a particular cab (medallion) has made given a particular pickup date
     *
     * @param medallions  NYC Cab medallions
     * @param pickupDate  Pickup date
     * @param ignoreCache Specifies whether cache data should be ignored and fresh data should be queried
     * @return Number of trips for each medallion
     */
    List<TripCountByMedallion> getCountByMedallionsAndPickupDate(List<String> medallions, Date pickupDate, Boolean ignoreCache);

    /**
     * Clears the data cache
     */
    void evictCache();
}
