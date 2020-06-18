package com.opsly.hometest.restcontroller;

import com.opsly.hometest.service.SocialMediaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SocialMediaRestControllerTest {
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate testRestTemplate;
    
    @MockBean
    private SocialMediaService mockSocialMediaService;
    
    @Test
    /**
     * Test that the results are aggregated from all three services
     *
     * @throws Exception
     */
    public void happyPath() throws Exception {
        List<String> dummyList = new ArrayList<String>();
        Mockito.when(mockSocialMediaService.getTwitterData()).thenReturn(CompletableFuture.completedFuture(dummyList));
        Mockito.when(mockSocialMediaService.getFacebookData()).thenReturn(CompletableFuture.completedFuture(dummyList));
        Mockito.when(mockSocialMediaService.getInstagramData()).thenReturn(CompletableFuture.completedFuture(dummyList));
    
        String result = this.testRestTemplate.getForObject("http://localhost:" + port + "/", String.class);
        assertTrue(result.contains("twitter") && result.contains("facebook") && result.contains("instagram"));
    }
}
