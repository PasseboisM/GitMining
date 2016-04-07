package logic.calc.python;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PythonRunner {
	
	public static List<String> runPython(String fileName,String...paras) throws IOException, InterruptedException {
		String folderName = "python/";
		String[] args = new String[3];
		args[0] = "python";
		args[1] = folderName+fileName;
		
		File f = File.createTempFile(""+System.currentTimeMillis(), ".params");
		
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < paras.length; i++) {
			bw.write(paras[i]);
			bw.newLine();
		}
		
		bw.close();
		fw.close();
		
		args[2] = f.getAbsolutePath();
		Process process = Runtime.getRuntime().exec(args);
		
		InputStream inputStream = process.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String line;
		List<String> resultList = new ArrayList<>();
		
		try {
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				resultList.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	

}
