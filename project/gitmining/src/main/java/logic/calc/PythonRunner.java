package logic.calc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PythonRunner {
	public static void runpython(String fileName,String...paras) throws IOException, InterruptedException {
		String pythonPath = System.getenv("PYTHON_PATH") + "/python.exe";
		String folderName = "src/main/java/logic/calc/";
		String[] args = new String[2+paras.length];
		args[0] = pythonPath;
		args[1] = folderName+fileName;
		for (int i = 0; i < paras.length; i++) {
			args[2+i] = paras[i];
		}
		Process process = Runtime.getRuntime().exec(args);
		InputStream inputStream = process.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String line;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			PythonRunner.runpython("repo_json_to_csv.py");
//			PythonRunner.runpython("user_json_to_csv.py");
			PythonRunner.runpython("statistics_user.py","0-wiz-0");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
