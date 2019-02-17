package au.com.x4ax.nyc.repository;

import java.util.Date;

/**
 * Defines data access layer for NYC cab trip data
 */
public interface CabTripRepository {

    /**
     * Query or get from cache count of tips made by a cab
     * Store the value in cache if the query to data layer was executed
     *
     * @param medallion Cab's medallion
     * @param pickupDate Pickup date
     * @return Number of trip
     */
    Integer getCacheCountByMedallionAndPickupDate(String medallion, Date pickupDate);

    /**
     * Query count of tips made by a cab
     * Ignore cache
     *
     * @param medallion Cab's medallion
     * @param pickupDate Pickup date
     * @return Number of trip
     */
    Integer getCountByMedallionAndPickupDate(String medallion, Date pickupDate);

    /**
     * Clear data cache
     */
    void evictCache();
}
