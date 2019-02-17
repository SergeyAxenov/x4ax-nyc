package au.com.x4ax.nyc.service;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import au.com.x4ax.nyc.repository.CabTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements functionality of NYC Cab data service
 */
@Service
public class NyCabDataServiceImpl implements NyCabDataService {

    private final CabTripRepository cabTripRepository;

    @Autowired
    public NyCabDataServiceImpl(final CabTripRepository cabTripRepository) {
        this.cabTripRepository = cabTripRepository;
    }

    /**
     * Returns number of trips of a particular cab (medallion) has made given a particular pickup date
     *
     * @param medallions NYC Cab medallions
     * @param pickupDate Pickup date
     * @param ignoreCache Specifies whether cache data should be ignored and fresh data should be queried
     * @return Number of trips for each medallion
     */
    @Override
    public List<TripCountByMedallion> getCountByMedallionsAndPickupDate(
            List<String> medallions,
            Date pickupDate,
            Boolean ignoreCache) {
        return medallions.stream().map(medallion -> {
            Integer count;
            if (ignoreCache) {
                count = cabTripRepository.getCountByMedallionAndPickupDate(medallion, pickupDate);
            } else {
                count = cabTripRepository.getCacheCountByMedallionAndPickupDate(medallion, pickupDate);
            }

            return new TripCountByMedallion(medallion, count);
        }).collect(Collectors.toList());
    }

    /**
     * Clears the data cache
     */
    @Override
    public void evictCache() {
        cabTripRepository.evictCache();
    }
}
