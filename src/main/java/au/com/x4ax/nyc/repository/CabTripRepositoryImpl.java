package au.com.x4ax.nyc.repository;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Implements data access layer for NYC cab trip data
 */
@Repository
public class CabTripRepositoryImpl implements CabTripRepository {

    private static final Logger log = LoggerFactory.getLogger(CabTripRepositoryImpl.class);

    private static final String QUERY_GET_COUNT_BY_MEDALLION_AND_PICKUP_DATE =
            "SELECT count(*) FROM cab_trip_data WHERE medallion = ? and date(pickup_datetime) = ?";

    private final JdbcTemplate jdbc;

    @Autowired
    public CabTripRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    /**
     * Query count of tips made by a cab
     * Ignore cache
     *
     * @param medallion Cab's medallion
     * @param pickupDate Pickup date
     * @return Number of trip
     */
    @Override
    public Integer getCountByMedallionAndPickupDate(String medallion, Date pickupDate) {
        log.info("getCountByMedallionAndPickupDate(medallion:{}, pickupDate:{})", medallion, pickupDate);
        return jdbc.queryForObject(
                QUERY_GET_COUNT_BY_MEDALLION_AND_PICKUP_DATE,
                new Object[]{medallion, pickupDate},
                Integer.class);
    }

    /**
     * Query or get from cache count of tips made by a cab
     * Store the value in cache if the query to data layer was executed
     *
     * @param medallion Cab's medallion
     * @param pickupDate Pickup date
     * @return Number of trip
     */
    @Override
    @Cacheable(value = "trips")
    public Integer getCacheCountByMedallionAndPickupDate(String medallion, Date pickupDate) {
        log.info("getCacheCountByMedallionAndPickupDate(medallion:{}, pickupDate:{})", medallion, pickupDate);
        return getCountByMedallionAndPickupDate(medallion, pickupDate);
    }

    /**
     * Clear data cache
     */
    @Override
    @CacheEvict(value = "trips", allEntries = true)
    public void evictCache() {
        log.info("evictCache(trips)");

        // No implementation necessary
    }
}
