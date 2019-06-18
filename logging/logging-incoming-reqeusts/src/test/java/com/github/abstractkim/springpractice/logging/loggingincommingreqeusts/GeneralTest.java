package com.github.abstractkim.springpractice.logging.loggingincommingreqeusts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class GeneralTest {
    @Test
    public void timeStampTest(){
        final long curentTimeAsLong = System.currentTimeMillis();
        System.out.println("curentTimeAsLong: " + curentTimeAsLong);
        Timestamp curentTimeAsTimestamp = new Timestamp(curentTimeAsLong);
        System.out.println("curentTimeAsTimestamp            : " + curentTimeAsTimestamp);
        assertThat(curentTimeAsTimestamp.getTime()).isEqualTo(curentTimeAsLong);
        System.out.println("curentTimeAsTimestamp (add 2000) : " + new Timestamp(curentTimeAsLong + 2000));

        System.out.println("one : " + new Timestamp(1560430431168L));
        System.out.println("two : " + new Timestamp(1560430460325L));

    }
}
