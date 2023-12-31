package diginamic.lightRh.util;

import java.util.Date;

public class DateRangeValidator {

    private final Date startDate;
    private final Date endDate;
    
    // Sources : https://mkyong.com/java/how-to-compare-dates-in-java/
    public DateRangeValidator(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public boolean isWithinRange(Date testDate) {

        // it works, alternatives
        /*boolean result = false;
        if ((testDate.equals(startDate) || testDate.equals(endDate)) ||
                (testDate.after(startDate) && testDate.before(endDate))) {
            result = true;
        }
        return result;*/

        // compare date and time, inclusive of startDate and endDate
        // getTime() returns number of milliseconds since January 1, 1970, 00:00:00 GMT
        return testDate.getTime() >= startDate.getTime() &&
                testDate.getTime() <= endDate.getTime();
    }
}
