package au.com.x4ax.nyc.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ReportTripsController.class, secure = false)
public class ReportTripsControllerTest {

    private static final String BASE_PATH = "/v1/report/trips";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NyCabDataService nyCabDataService;

    @Before
    public void setup() {
    }

    @Test
    public void shouldHandleGetCountByMedallionsAndPickupDateAction() throws Exception {
        String endpoint = BASE_PATH + "/pickup-date/2013-12-30/medallions/00184958F5D5FD0A9EC0B115C5B55796,00153E36140C5B2A84EA308F355A7925/count";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(endpoint)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
