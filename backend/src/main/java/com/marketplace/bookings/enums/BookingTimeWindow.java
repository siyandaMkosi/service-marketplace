package com.marketplace.bookings.enums;

import java.time.LocalTime;

public enum BookingTimeWindow {

    MORNING(
        LocalTime.of(8, 0),
        LocalTime.of(12, 0)
    ),

    AFTERNOON(
        LocalTime.of(12, 0),
        LocalTime.of(17, 0)
    ),

    EVENING(
        LocalTime.of(17, 0),
        LocalTime.of(20, 0)
    );

    private final LocalTime start;

    private final LocalTime end;

    BookingTimeWindow(
        LocalTime start,
        LocalTime end
    ) {
        this.start = start;
        this.end = end;
    }

    public boolean contains(LocalTime time) {
        return !time.isBefore(start)
            && time.isBefore(end);
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

}
