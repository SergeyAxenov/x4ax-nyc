package au.com.x4ax.nyc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for administration operations that requires elevated privileges
 */
@RestController
@RequestMapping(value = "/v1/admin")
public class AdminController {

    @Autowired
    private NyCabDataService nyCabDataService;

    /**
     * Evicts data cache
     */
    @DeleteMapping("/cache")
    public void evictCache() {
        nyCabDataService.evictCache();
    }
}
