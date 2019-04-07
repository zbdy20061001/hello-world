

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Hello world!
 *
 */
public class JarFileReadingSample {
	public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
		System.out.println(ClassLoader.getSystemResource(""));
		System.out.println(JarFileReadingSample.class.getResource(""));
		System.out.println(JarFileReadingSample.class.getResource("/"));
		System.out.println(JarFileReadingSample.class.getClassLoader().getResource(""));
		JarFile jarFile = new JarFile(
				"D:\\programming\\git\\swift\\swift-study\\target\\swift-study-0.0.1-SNAPSHOT.jar");

		for (Enumeration<JarEntry> e = jarFile.entries(); e.hasMoreElements();) { // 这个循环会读取jar包中所有文件，包括文件夹

			JarEntry jarEntry = e.nextElement();// jarEntry就是我们读取的jar包中每一个文件了，包括目录

			if (jarEntry.getName().endsWith(".xsd")) {
				System.out.println(jarEntry.getName());
				InputStream is = jarFile.getInputStream(jarEntry); // 将目标文件读到流
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				System.out.println(reader.readLine());
				reader.close();
				is.close();
			}
		}

		jarFile.close();
	}
}
