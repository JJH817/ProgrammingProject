package user;

import java.util.regex.Pattern;
import user.Join;

public class Namecheck {
	public boolean namecheck() {
		String check = Join.nameText.getText();
		boolean nameresult = check.matches("^[가-힣]*$"); // 한글 형식 글자 유효성 검사

		if(nameresult) {
			return true;
		}else{
			return false;
		}	
	}

}