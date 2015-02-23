package com.querydsl.collections;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import com.querydsl.core.types.path.DatePath;
import com.querydsl.core.types.path.DateTimePath;
import com.querydsl.core.types.path.TimePath;

public class JodaTimeTemplatesTest {
    
    private CollQuery<Void> query = new CollQuery<Void>(JodaTimeTemplates.DEFAULT);
    
    @Test
    public void DateTime() {
        DateTimePath<DateTime> entity = new DateTimePath<DateTime>(DateTime.class, "entity");
        query.from(entity, Arrays.asList(new DateTime(), new DateTime(0l)))
             .select(entity.year(), entity.yearMonth(), entity.month(), entity.week(),
                   entity.dayOfMonth(), entity.dayOfWeek(), entity.dayOfYear(),
                   entity.hour(), entity.minute(), entity.second(), entity.milliSecond())
             .list();
    }
    
    @Test
    public void LocalDate() {
        DatePath<LocalDate> entity = new DatePath<LocalDate>(LocalDate.class, "entity");
        query.from(entity, Arrays.asList(new LocalDate(), new LocalDate(0l)))
             .select(entity.year(), entity.yearMonth(), entity.month(), entity.week(),
                   entity.dayOfMonth(), entity.dayOfWeek(), entity.dayOfYear())
             .list();
    }
    
    @Test
    public void LocalTime() {
        TimePath<LocalTime> entity = new TimePath<LocalTime>(LocalTime.class, "entity");
        query.from(entity, Arrays.asList(new LocalTime(), new LocalTime(0l)))
             .select(entity.hour(), entity.minute(), entity.second(), entity.milliSecond())
             .list();
    }

}
