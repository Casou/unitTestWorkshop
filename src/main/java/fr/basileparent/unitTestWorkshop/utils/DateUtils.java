package fr.basileparent.unitTestWorkshop.utils;

import java.time.LocalDateTime;

public class DateUtils {

    public static LocalDateTime addOneYear(LocalDateTime date) {
        return date.plusDays(365);
    }

}
