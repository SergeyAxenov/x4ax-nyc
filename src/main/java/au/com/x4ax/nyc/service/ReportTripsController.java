package au.com.x4ax.nyc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for reporting query available for standard users
 */
@RestController
@RequestMapping(value = "/v1/report/trips")
public class ReportTripsController {

    @Autowired
    private NyCabDataService nyCabDataService;

    @GetMapping("/pickup-date/{dt}/medallions/{ids}/count")
    public List<TripCountByMedallion> getCountByPickupDateAndMedallions(
            @PathVariable(value = "dt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate pickupDate,
            @PathVariable(value = "ids") List<String> medallions,
            @RequestParam(value = "noCache", required = false, defaultValue = "false") String noCache) {

        Boolean ignoreCache = "true".equals(noCache);
        return nyCabDataService.getCountByMedallionsAndPickupDate(medallions, java.sql.Date.valueOf(pickupDate), ignoreCache);
    }
}
