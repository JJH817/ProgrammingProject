package user;

import java.util.regex.Pattern;

import user.Join;

public class IDcheck {
	public boolean idcheck() {
		String check = Join.idText.getText();
		boolean idresult = check.matches("^[a-zA-Z]+[a-zA-Z0-9]{3,}$");
		// 아이디 형식 문자 최소 3자 이상 ex)asdf1234
		if(idresult) {
			return true;
		}else{
			return false;
		}	
	}

}
