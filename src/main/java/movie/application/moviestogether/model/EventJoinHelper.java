package movie.application.moviestogether.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;

import movie.application.moviestogether.entity.EventJoinUser;

public class EventJoinHelper implements Comparator<EventJoinUser>{

    @Override
    public int compare(EventJoinUser a, EventJoinUser b) {
        // TODO Auto-generated method stub
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTimeA = LocalDateTime.parse(a.getEvent().getEvent_datetime(), formatter);
        LocalDateTime dateTimeB = LocalDateTime.parse(b.getEvent().getEvent_datetime(), formatter);
        

        if (dateTimeA.isBefore(dateTimeB)) return -1;
        if (dateTimeA.equals(dateTimeB)) return 0;
        else return 1;
    }

}
