package lesson2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ProcessTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.err.println("OS="+System.getProperty("os.name"));
		
		Runtime.getRuntime().addShutdownHook(new Thread(()->System.err.println("DSDSDDFGSDFHGSHDGHGSDFG")));
		
		
		ProcessBuilder	pb = new ProcessBuilder("cmd.exe", "/C", "dir","c:\\Windows")
				.directory(new File("./"));
		
		Process			p = pb.start();
		
		
		
		
		
		Thread	t = new Thread(()->{
			try(final Reader	rdr = new InputStreamReader(p.getInputStream());
				final BufferedReader	brdr = new BufferedReader(rdr)) {
				
				String	line;
				
				while ((line = brdr.readLine()) != null) {
					System.err.println("Line: "+line);
				}
			} catch (IOException exc) {
			}
		});
		t.setDaemon(true);
		t.start();
		
		p.waitFor();
		
		System.err.println("Exit code: "+p.exitValue());

		
		
//		System.exit(0);
//		Runtime.getRuntime().exit(0);
//		Runtime.getRuntime().halt(0);
	}

}
