package diginamic.lightRh.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// In AbsenceService
public class DateWithinRange {
	
	  public static void main(String[] args) throws ParseException {

	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	      Date startDate = sdf.parse("2020-01-01");
	      Date endDate = sdf.parse("2020-01-31");

	      System.out.println("startDate : " + sdf.format(startDate));
	      System.out.println("endDate : " + sdf.format(endDate));

	      DateRangeValidator checker = new DateRangeValidator(startDate, endDate);

	      Date testDate = sdf.parse("2020-01-01");
	      System.out.println("testDate : " + sdf.format(testDate));

	      if(checker.isWithinRange(testDate)){
	          System.out.println("testDate is within the date range.");
	      }else{
	          System.out.println("testDate is NOT within the date range.");
	      }

	  }
}
