package me.whiteship.restapiwithspring.events;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EventControllerTests {

    //Spring mvc에 대한 tes 디스팩처 서블릿 생성후 Bean들을 테스트 단위테스트보다 걸린다.
    @Autowired
    MockMvc mockMvc;

    // api.json api.xml 확장자 요청 -> 보다는-> accept header지정을 추천
    @Test
    public void createEvent() throws Exception {
        //perform 요청
        mockMvc.perform(post("/api/events/")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaTypes.HAL_JSON)
                )
                //create는 201
                .andExpect(status().isCreated());
    }
}
