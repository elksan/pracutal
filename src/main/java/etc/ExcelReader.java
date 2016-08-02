package etc;

import models.Student;
import models.User;
import ninja.uploads.FileItem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Diego on 05-07-2016.
 */
public class ExcelReader {

	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();

			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue();

			case Cell.CELL_TYPE_NUMERIC:
				return cell.getNumericCellValue();
		}

		return null;
	}

	public List<Student> readStudentsFromExcel(FileItem excelFilePath){

		List<Student> studentList = new ArrayList<>();
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(excelFilePath.getFile());


			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();

			while (iterator.hasNext()) {

				Row nextRow = iterator.next();
				if(nextRow.getRowNum() == 0)
					continue;

				Iterator<Cell> cellIterator = nextRow.cellIterator();
				Student student = new Student();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
						case 0:
							student.setGender((String) getCellValue(nextCell));
							break;
						case 2:
							student.setName((String) getCellValue(nextCell));
							break;
						case 3:
							student.setLastName((String) getCellValue(nextCell));
							break;
						case 4:
							student.setRegistrationNumber(((Double) getCellValue(nextCell)).intValue());
							break;
						case 5:
							student.setEntryYear(((Double) getCellValue(nextCell)).intValue());
							break;
						case 7:
							student.setEmail((String) getCellValue(nextCell));
							break;

					}
				}
				studentList.add(student);
			}

			workbook.close();
			inputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return studentList;
	}
}
