package au.com.x4ax.nyc.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import au.com.x4ax.nyc.repository.CabTripRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NyCabDataServiceTest {

    private NyCabDataService nyCabDataService;

    @Mock
    private CabTripRepository cabTripRepository;

    @Before
    public void setup() {
        nyCabDataService = new NyCabDataServiceImpl(cabTripRepository);
    }

    @Test
    public void shouldReturnCountForMedallions() {
        Date pickupDate = java.sql.Date.valueOf(LocalDate.of(2013, 12, 30));
        String medallion1 = "00184958F5D5FD0A9EC0B115C5B55796";
        String medallion2 = "00153E36140C5B2A84EA308F355A7925";

        Integer expectedNumberOfTrips1 = 3;
        Integer expectedNumberOfTrips2 = 5;

        when(cabTripRepository.getCacheCountByMedallionAndPickupDate(medallion1, pickupDate)).thenReturn(expectedNumberOfTrips1);
        when(cabTripRepository.getCacheCountByMedallionAndPickupDate(medallion2, pickupDate)).thenReturn(expectedNumberOfTrips2);

        List<String> medallions = new ArrayList<>();
        medallions.add(medallion1);
        medallions.add(medallion2);
        Map<String, Integer> mapResult = nyCabDataService
                .getCountByMedallionsAndPickupDate(medallions, pickupDate, false)
                .stream()
                .collect(Collectors.toMap(TripCountByMedallion::getMedallion, TripCountByMedallion::getCount));

        assertEquals(expectedNumberOfTrips1, mapResult.get(medallion1));
        assertEquals(expectedNumberOfTrips2, mapResult.get(medallion2));
    }

    @Test
    public void shouldEvictCacheOfCabTrips() {
        nyCabDataService.evictCache();
        verify(cabTripRepository, times(1)).evictCache();
        verifyNoMoreInteractions(cabTripRepository);
    }
}
