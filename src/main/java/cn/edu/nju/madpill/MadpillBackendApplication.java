package cn.edu.nju.madpill;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class MadpillBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MadpillBackendApplication.class, args);
	}

}
