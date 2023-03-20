import java.io.IOException;
import java.io.InputStream;

public class App {

	public static void main(String[] args) throws IOException {
//		while(true) {
//			if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23 &&  Calendar.getInstance().get(Calendar.MINUTE) > 45) {
////				Runtime.getRuntime().exec("ls");
////				System.out.println(Calendar.getInstance().get(Calendar.SECOND));
//			}
//		}
        Runtime run = Runtime.getRuntime();
        try {
            // run.exec("cmd /k shutdown -s -t 3600");
            Process process = run.exec("dir");
            InputStream in = process.getInputStream();
            while (in.read() != -1) {
                System.out.println(in.read());
            }
            in.close();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
}
