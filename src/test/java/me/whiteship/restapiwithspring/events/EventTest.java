package me.whiteship.restapiwithspring.events;


import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @Test
    public void builder() {
        Event event = Event.builder()
                .name("Inflearn Spring REST API")
                .description("REST API development with Spring")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean() {
        //Given
        String name = "Event";
        String spring = "Spring";

        // When
        Event event = new Event();
        event.setName(name);
        event.setDescription(spring);

        //Then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(spring);
    }

    @Test
    public void testFree() {
        Event event = Event.builder()
                .basePrice(0)
                .maxPrice(0)
                .build();
        //WHEN
        event.update();

        //THEN
        AssertionsForClassTypes.assertThat(event.isFree()).isTrue();

        event = Event.builder()
                .basePrice(0)
                .maxPrice(100)
                .build();

        event.update();

        AssertionsForClassTypes.assertThat(event.isFree()).isFalse();

    }


    @Test
    public void testOffline() {
        //Given
        Event event = Event.builder()
                .location("강남역 네이버 D2 스타텁 팩토리")
                .build();
        //WHEN
        event.update();

        //THEN
        AssertionsForClassTypes.assertThat(event.isOffline()).isTrue();

        event = Event.builder()
                .build();

        event.update();

        AssertionsForClassTypes.assertThat(event.isOffline()).isFalse();
    }
}
