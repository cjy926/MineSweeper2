package minesweeper;

import java.io.*;

public class Read {
	public static String read(String fileName)
	{
		String s =   fileName;
		File file = new File(s);
		BufferedReader reader = null;
		String tempString = null;
		int line = 1;

		try {
			//System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			while ((tempString = reader.readLine()) != null) {
				return tempString;
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return tempString;//这里不知道怎么说
		}
	}

	//文件输出
	public static void saveAsFileWriter(String content,String name) {
		String filePath =  name;
		FileWriter fwriter = null;
		try {
			// true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
			fwriter = new FileWriter(filePath, false);//filePath输出位置
			fwriter.write(content);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fwriter.flush();
				fwriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public static void cun(String content) {
		String filePath = "regret.txt" ;
		FileWriter fwriter = null;
		try {
			// true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
			fwriter = new FileWriter(filePath, false);//filePath输出位置
			fwriter.write(content);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fwriter.flush();
				fwriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}