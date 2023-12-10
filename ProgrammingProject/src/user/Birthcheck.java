package user;

import java.util.regex.Pattern;
import user.Join;

public class Birthcheck {
	public boolean birthcheck() {
		String check = Join.birthDateText.getText();
		boolean birthresult = check.matches("(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])");
		// 생년월일 ex)19991212 형식
		if(birthresult) {
			return true;
		}else{
			return false;
		}	
	}

}