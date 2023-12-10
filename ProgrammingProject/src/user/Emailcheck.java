package user;

import java.util.regex.Pattern;
import user.Join;

public class Emailcheck {
	public boolean emailcheck() {
		String check = Join.emailText.getText();
		boolean emailresult = check.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");
		// 이메일 ex)perfect@harmony.com 형식
		if(emailresult) {
			return true;
		}else{
			return false;
		}	
	}

}