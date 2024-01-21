package me.whiteship.restapiwithspring.events;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(JUnitParamsRunner.class)
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

    /**
     * Junit 4
     * @Test, @Parameters 사용
     *
     * Junit 5
     * @ParameterizedTest, @MethodSource @CsvSource... 사용
     * 용도에 따라 사용하는 어노테이션이 다르며, MethodSource를 사용하려면 class 에 @TestInstance(TestInstance.Lifecycle.PER_CLASS) 지정
     *
     */
    @ParameterizedTest
    @MethodSource("parametersForTestFree")
    //org.junit.jupiter.api.extension.ParameterResolutionException 발생 - Junit 5버전 @Test를 사용하여 발생한 오류 Junit 4버전은 상관없다 @Test, @Parameters
    public void testFree(int basePrice, int maxPrice, boolean isFree) {
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();
//        WHEN
        event.update();
//        THEN
        AssertionsForClassTypes.assertThat(event.isFree()).isEqualTo(isFree);
    }

    private Object[] parametersForTestFree() {
        return new Object[] {
                new Object[] {0, 0, true},
                new Object[] {100, 0, false},
                new Object[] {0, 100, false},
                new Object[] {100, 100, false}
        };
    }


    @ParameterizedTest
    @CsvSource({
            "강남역 네이버 D2 스타텁 팩토리, true",
            ", false"
    })
    public void testOffline(String location, boolean isOffline) {
        //Given
        Event event = Event.builder()
                .location(location)
                .build();
        //WHEN
        event.update();

        //THEN
        AssertionsForClassTypes.assertThat(event.isOffline()).isEqualTo(isOffline);
    }
}
