package genericUtilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
	
	public String currentDateTime() {
		LocalDateTime lt= LocalDateTime.now();
		DateTimeFormatter f= DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
		String format=f.format(lt);
		return format;
		
	}

}
