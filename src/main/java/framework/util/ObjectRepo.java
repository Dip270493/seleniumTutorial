package framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

public class ObjectRepo {
	HashMap<String, String> map = new HashMap<String, String>();
	FileInputStream file;
	XSSFWorkbook workbook;

	public void LoadData() {
		try {
			FileInputStream file = new FileInputStream(new File("src\\test\\java\\resources\\ObjectRepo.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("ObjectRepository");
			Iterator<Row> itr = sheet.iterator();
			while (itr.hasNext()) {
				Row row = itr.next();
				map.put(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());
			}
			workbook.close();
			file.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public By get(String key) {
		String value = map.get(key);
		String locator = value.split("-->")[0];
		String selector = value.substring(value.indexOf("-->") + 1);

		switch (locator) {
		case "xpath":
			return By.xpath(selector);
		case "id":
			return By.id(selector);
		case "cssSelector":
			return By.cssSelector(selector);
		case "name":
			return By.name(selector);
		case "className":
			return By.className(selector);
		case "linkText":
			return By.linkText(selector);
		case "partialLinkText":
			return By.partialLinkText(selector);
		case "tagName":
			return By.tagName(selector);
		}
		return null;
	}
}