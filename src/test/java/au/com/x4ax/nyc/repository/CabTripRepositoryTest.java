package au.com.x4ax.nyc.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
public class CabTripRepositoryTest {

    private CabTripRepository sut;

    @Mock
    private JdbcTemplate jdbc;

    @Before
    public void setup() {
        sut = new CabTripRepositoryImpl(jdbc);
    }

    @Test
    public void shouldReturnQueryResultAsCount() {
        when(jdbc.queryForObject(anyString(), anyObject(), eq(Integer.class))).thenReturn(3);

        Date pickupDate = java.sql.Date.valueOf(LocalDate.of(2013, 12, 30));
        String medallion = "00153E36140C5B2A84EA308F355A7925";

        Integer actual = sut.getCacheCountByMedallionAndPickupDate(medallion, pickupDate);
        Integer expected = 3;

        assertEquals(expected, actual);
    }
}
