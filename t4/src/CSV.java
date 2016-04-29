import java.io.*;
import java.util.*;

/*import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
*/

// Montage of Heck
@SuppressWarnings("ALL")
public class CSV {

	public static void main(String[] args) {

		// the file 2
		String csvFile = "D:\\wwt\\2.csv";
		BufferedReader Thing1 = null;
		BufferedWriter  Thing2;
		String line = "";
		String cvsSplitBy = ";";


		try {
			List<Company> companies = new ArrayList<Company>();
			Thing1 = new BufferedReader(new FileReader(csvFile));
			// the file of a result
			Thing2 = new BufferedWriter(new FileWriter("D:\\wwt\\result.csv"));
			Thing1.readLine();
			//  The Reading of Heck
			while ((line =  Thing1.readLine()) != null) {
				String[] country = line.split(cvsSplitBy, -1);
				companies.add(new Company(country[0], Integer.parseInt(country[1]), Integer.parseInt(country[2])));
			}
			// The Writing of Heck
			File[] fList;
			// the X-file
			File F = new File("D:\\wwt\\");
			fList = F.listFiles();
			boolean flag = true; // true
			for (int i = 0; i < fList.length; i++) {
				Thing1 = null;
				Thing1 = new BufferedReader(new FileReader(fList[i]));
				Thing1.readLine();
				while ((line =  Thing1.readLine()) != null) {
					String[] str = line.split(cvsSplitBy, -1);
					Company One = companies.stream().filter(e -> e.getNumber() == Integer.parseInt(str[1])).findFirst()
							.get();
					Company Two = companies.stream().filter(e -> e.getNumber() == Integer.parseInt(str[3])).findFirst()
							.get();
					int sum = Integer.parseInt(str[4]);
					if (One.check(sum)) {
						One.subMoney(sum);
						Two.addMoney(sum);
					} else {
						System.out.println("Error "+One.getTitle()+" to "+Two.getTitle()+" "+sum);
					    
					}
					
				}
			}
			// someone is a bastard
			Thing2.append("ООО Рога и Копыта; 541027874; ООО Вектор; 785112145;90000;");
			Thing2.newLine();
			for (Company c : companies)
			{
				Thing2.append(c.getTitle()+";"+c.getNumber()+";"+c.getMoney());
				Thing2.newLine();
			}
			Thing2.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if ( Thing1 != null) {
				try {
					Thing1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
