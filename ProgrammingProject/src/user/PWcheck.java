package user;

import java.util.regex.Pattern;
import user.Join;

public class PWcheck {
	public boolean pwcheck() {
		String check = Join.pwText.getText();
		boolean pwresult = check.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,}$");
		// 최소 8글자 숫자, 문자, 특수문자 최소 1개씩 ex) asdf1234!
		if(pwresult) {
			return true;
		}else{
			return false;
		}	
	}

}
