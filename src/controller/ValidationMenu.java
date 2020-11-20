package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationMenu {

	public enum errorKeys {
		ERROR_NAME,
		ERROR_KANA,
		ERROR_TAG_ID,
		ERROR_QUANTITY,
		ERROR_MSG
	}


	public Map<String, String> errorCheck(String name, String kana, Integer tagId, String[] strQuantities) {
		boolean isValidated = true;
		Map<String, String> errors = new HashMap<>();

	    if (name.isEmpty()) {
	    	isValidated = false;
	    	errors.put(errorKeys.ERROR_NAME.toString(), "料理名の入力は必須です！");
	    } else if (name.length() >= 20) {
	    	isValidated = false;
	    	errors.put(errorKeys.ERROR_NAME.toString(), "料理名は20文字以下で入力してください！");
	    } else {
	    	errors.put(errorKeys.ERROR_NAME.toString(), "");
	    }

	    Pattern kanaPattern = Pattern.compile("[ぁ-ゖ][ぁ-ゖー 　]+");
	    Matcher kanaMatcher = kanaPattern.matcher(kana);
	    if (kana.isEmpty()) {
	    	isValidated = false;
	    	errors.put(errorKeys.ERROR_KANA.toString(), "ふりがなの入力は必須です！");
	    } else if (!kanaMatcher.matches()) {
	    	isValidated = false;
	    	errors.put(errorKeys.ERROR_KANA.toString(), "全角ひらがなで入力してください！");
	    } else if (kana.length() >= 40) {
	    	isValidated = false;
	    	errors.put(errorKeys.ERROR_KANA.toString(), "ふりがなは40文字以下で入力してください！");
	    } else {
	    	errors.put(errorKeys.ERROR_KANA.toString(), "");
	    }

	    if (tagId == 0) {
	    	isValidated = false;
	    	errors.put(errorKeys.ERROR_TAG_ID.toString(), "料理区分の選択は必須です！");
	    } else {
	    	errors.put(errorKeys.ERROR_TAG_ID.toString(), "");
	    }

	    for (String strQuantitie: strQuantities) {
	    	errors.put(errorKeys.ERROR_QUANTITY.toString(), "");
	    	if (!strQuantitie.equals("") && Double.parseDouble(strQuantitie) <= 0) {
	    		isValidated = false;
	    		errors.put(errorKeys.ERROR_QUANTITY.toString(), "調味料の分量は0以上で入力してください！");
	    		break;
	    	}
	    }

	    if (isValidated == false) {
	    	errors.put(errorKeys.ERROR_MSG.toString(), "入力に不備があります！！");
	    } else {
	    	errors.put(errorKeys.ERROR_MSG.toString(), "");
	    }
		return errors;
	}

}
