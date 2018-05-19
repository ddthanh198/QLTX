package date;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Date {
	int ngay;
	int thang;
	int nam;
	
	public Date(String date) {
		
		if(date.isEmpty()) {
			this.ngay = 0;
			this.thang = 0;
			this.nam = 0;
		} else {
			this.nam = Integer.parseInt(date.substring(0, 4));
			this.thang = Integer.parseInt(date.substring(5, 7));
			this.ngay = Integer.parseInt(date.substring(8));
		}
	}
	
	public String getDateString() {
		if(ngay == 0) {
			return "";
		} 
		String str_ngay = String.valueOf(ngay);
		if(ngay < 10) {
			str_ngay = "0" + String.valueOf(ngay);
		}
		String str_thang = String.valueOf(thang);
		if(thang < 10) {
			str_thang = "0" + String.valueOf(thang);
		}
		String str_nam = String.valueOf(nam);
		return str_nam + "-" + str_thang + "-" + str_ngay;
	}
	
	public static long getNumberOfDaysBetween(Date firstDate, Date secondDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		try {
		    java.util.Date date1 = dateFormat.parse(firstDate.getDateString());
		    java.util.Date date2 = dateFormat.parse(secondDate.getDateString());
		    long diff = date2.getTime() - date1.getTime();
		    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return 0;
	}
}
