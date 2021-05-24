import java.io.*;

public class Load {
	public static void main(String[] args) {
	   new Load("123");
	}

	//传值传的是string，引用load
	public Load(String string) {
		File file = new File(string);
		BufferedReader reader = null;
		String tempString = null;
		StringBuilder sb = new StringBuilder();
		int line = 1;
		String[] pieces = new String[26];
		//下面就都是你要记录输出的变量什么的
		int n;
		int row;
		int col;
		int counter = 0;
		int numberOfPlayers;
		int numberOfClicks;
		int numberOfClicksInTotal;
		int amountOfMine;
		int amountOfMineOpened;
		boolean whetherIntellectual;
		int[] scores;
		int[] errors;
		String[] scoresInfo;
		String[] errorsInfo;
		String[] dataInfo;
		String[] buttonInfo;
		int[][] data;
		char[][]buttonState;


		//这里是读入部分
		try {
			//System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));//这一句就是读文件
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
				line++ ;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println(sb.toString());
		pieces = sb.toString().split("~");//dbq这里是用波浪号吧数据隔开，检索符号来搜索数据
		for (int i = 0;i < 26;i++){
			System.out.println(pieces[i]);
		}

		//这下面就都是数据的记录，换成自己的就行
		n = Integer.parseInt(pieces[1]);
		row = Integer.parseInt(pieces[3]);
		col = Integer.parseInt(pieces[5]);
		numberOfPlayers = Integer.parseInt(pieces[7]);
		numberOfClicks = Integer.parseInt(pieces[9]);
		numberOfClicksInTotal = Integer.parseInt(pieces[11]);
		amountOfMine = Integer.parseInt(pieces[13]);
		amountOfMineOpened = Integer.parseInt(pieces[15]);
		whetherIntellectual = Boolean.parseBoolean(pieces[17]);


		data = new int[row][col];
		buttonState = new char[row][col];
		scores = new int[numberOfPlayers];
		errors = new int[numberOfPlayers];
		scoresInfo = new String[numberOfPlayers];
		errorsInfo = new String[numberOfPlayers];

		scoresInfo = pieces[19].split(" ");
		errorsInfo = pieces[21].split(" ");
		dataInfo = pieces[23].split(" ");
		buttonInfo = pieces[25].split(" ");

		for (int i = 0;i < numberOfPlayers;i++) {
			scores[i] = Integer.parseInt(scoresInfo[i]);
			errors[i] = Integer.parseInt(errorsInfo[i]);
		}

		for (int i = 0;i < row;i++) {
			for (int j = 0;j < col;j++) {
				data[i][j] = Integer.parseInt(dataInfo[counter]);
				counter++;
			}
		}

		counter = 0;
		for (int i = 0;i < row;i++) {
			for (int j = 0;j < col;j++){
				buttonState[i][j] = buttonInfo[counter].charAt(0);
				counter++;
			}
		}

		//这一句是把引用过的值都读入进去
//		new MineSweeper(n,row,col,numberOfPlayers,numberOfClicks,numberOfClicksInTotal,amountOfMine,amountOfMineOpened,scores,errors,data,buttonState);
	}

}