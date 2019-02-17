package au.com.x4ax.nyc.service;

/**
 * Describes response for a request to get number of trips for given medallion and date
 */
public class TripCountByMedallion {
    private String medallion;
    private Integer count;

    TripCountByMedallion(String medallion, Integer count) {
        this.medallion = medallion;
        this.count = count;
    }

    /**
     * Requested medallion
     *
     * @return Requested medallion
     */
    public String getMedallion() {
        return medallion;
    }

    /**
     * Number of trips for given medallion and date
     *
     * @return Number of trips
     */
    public Integer getCount() {
        return count;
    }
}
