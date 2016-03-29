package logic.calc.python;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PythonRunner {
	
	private static final String root = System.getProperty("user.dir");
	
	public static List<String> runPython(String fileName,String...paras) throws IOException, InterruptedException {
		String folderName = "python/";
		String[] args = new String[2+paras.length];
		args[0] = "python";
		args[1] = folderName+fileName;
		for (int i = 0; i < paras.length; i++) {
			args[2+i] = paras[i];
		}
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
	

	public static void main(String[] args) {
		try {
			long t1 = System.currentTimeMillis();
			List<String> result = PythonRunner.runPython("statistic_single_user_rank.py","15","58.5","99","101");
			System.out.println(System.currentTimeMillis()-t1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
