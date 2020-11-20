package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controller.ValidationMenu.errorKeys;

class ValidationTest {

	String name;
	String kana;
	Integer tagId;
	String[] strQuantities;
	ValidationMenu vm = new ValidationMenu();

	@Test
	@DisplayName("必須項目が入力されていれば保存できること")
	void testErrorCheck() {
		name = "カレーライス";
		kana = "かれーらいす";
		tagId = 21;
		strQuantities = new String[3];
		strQuantities[0] = "12";
		strQuantities[1] = "20";
		strQuantities[2] = "33";

		Map<String, String> errors = vm.errorCheck(name, kana, tagId, strQuantities);
		assertEquals("", errors.get(errorKeys.ERROR_MSG.toString()));
	}


	@Test
	@DisplayName("名前が入力されていないと保存できないこと")
	void testNameNullErrorCheck() {
		name = "";
		kana = "かれーらいす";
		tagId = 21;
		strQuantities = new String[3];
		strQuantities[0] = "12";
		strQuantities[1] = "20";
		strQuantities[2] = "33";

		Map<String, String> errors = vm.errorCheck(name, kana, tagId, strQuantities);
		assertEquals("料理名の入力は必須です！", errors.get(errorKeys.ERROR_NAME.toString()));
	}

	@Test
	@DisplayName("名前が21文字以上で入力されていると保存できないこと")
	void testNameUpperErrorCheck() {
		name = "カレーライスカレーライスカレーライスカレーライス";
		kana = "かれーらいす";
		tagId = 21;
		strQuantities = new String[3];
		strQuantities[0] = "12";
		strQuantities[1] = "20";
		strQuantities[2] = "33";

		Map<String, String> errors = vm.errorCheck(name, kana, tagId, strQuantities);
		assertEquals("料理名は20文字以下で入力してください！", errors.get(errorKeys.ERROR_NAME.toString()));
	}


	@Test
	@DisplayName("ふりがなが入力されていないと保存できないこと")
	void testKanaNullErrorCheck() {
		name = "カレーライス";
		kana = "";
		tagId = 21;
		strQuantities = new String[3];
		strQuantities[0] = "12";
		strQuantities[1] = "20";
		strQuantities[2] = "33";

		Map<String, String> errors = vm.errorCheck(name, kana, tagId, strQuantities);
		assertEquals("ふりがなの入力は必須です！", errors.get(errorKeys.ERROR_KANA.toString()));
	}

	@Test
	@DisplayName("ふりがなが41文字以上で入力されていると保存できないこと")
	void testKanaUpperErrorCheck() {
		name = "カレーライス";
		kana = "かれーらいすかれーらいすかれーらいすかれーらいすかれーらいすかれーらいすかれーらいす";
		tagId = 21;
		strQuantities = new String[3];
		strQuantities[0] = "12";
		strQuantities[1] = "20";
		strQuantities[2] = "33";

		Map<String, String> errors = vm.errorCheck(name, kana, tagId, strQuantities);
		assertEquals("ふりがなは40文字以下で入力してください！", errors.get(errorKeys.ERROR_KANA.toString()));
	}

	@Test
	@DisplayName("ふりがな以外で入力されていると保存できないこと")
	void testKanaErrorCheck() {
		name = "カレーライス";
		kana = "カレーライス";
		tagId = 21;
		strQuantities = new String[3];
		strQuantities[0] = "12";
		strQuantities[1] = "20";
		strQuantities[2] = "33";

		Map<String, String> errors = vm.errorCheck(name, kana, tagId, strQuantities);
		assertEquals("全角ひらがなで入力してください！", errors.get(errorKeys.ERROR_KANA.toString()));
	}


	@Test
	@DisplayName("タグが選択されていないと保存できないこと")
	void testTagErrorCheck() {
		name = "カレーライス";
		kana = "かれーらいす";
		tagId = 0;
		strQuantities = new String[3];
		strQuantities[0] = "12";
		strQuantities[1] = "20";
		strQuantities[2] = "33";

		Map<String, String> errors = vm.errorCheck(name, kana, tagId, strQuantities);
		assertEquals("料理区分の選択は必須です！", errors.get(errorKeys.ERROR_TAG_ID.toString()));
	}


	@Test
	@DisplayName("調味料の分量が入力されていないと保存できないこと")
	void testQuantitiesErrorCheck() {
		name = "カレーライス";
		kana = "かれーらいす";
		tagId = 21;
		strQuantities = new String[3];
		strQuantities[0] = "12";
		strQuantities[1] = "20";
		strQuantities[2] = "0";

		Map<String, String> errors = vm.errorCheck(name, kana, tagId, strQuantities);
		assertEquals("調味料の分量は0以上で入力してください！", errors.get(errorKeys.ERROR_QUANTITY.toString()));
	}

}
