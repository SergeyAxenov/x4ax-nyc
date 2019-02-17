package au.com.x4ax.nyc.service;

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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AdminController.class, secure = false)
public class AdminControllerTest {

    private static final String BASE_PATH = "/v1/admin";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NyCabDataService nyCabDataService;

    @Before
    public void setup() {
    }

    @Test
    public void shouldHandleEvictCacheDeleteAction() throws Exception {
        String endpoint = BASE_PATH + "/cache";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(endpoint)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
