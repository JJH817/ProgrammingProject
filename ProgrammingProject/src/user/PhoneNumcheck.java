package user;

import java.util.regex.Pattern;
import user.Join;

public class PhoneNumcheck {
	public boolean phonenumcheck() {
		String check = Join.phoneNumText.getText();
		boolean phonenumresult = check.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$");
		// ex)01012341234 휴대폰 번호 형식 
		
		if(phonenumresult) {
			return true;
		}else{
			return false;
		}	
	}

}