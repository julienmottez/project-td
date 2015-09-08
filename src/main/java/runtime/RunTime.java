package runtime;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.treeptik.conf.ApplicationConfiguration;

public class RunTime {

	public static void main(String[] args) {
		System.out.println("luuuuuuuuuuuuuuuuuuuuu");

		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

	}
}
