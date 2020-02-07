package cn.edu.nju.madpill;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
@MapperScan("cn.edu.nju.madpill.mapper")
public class MadpillBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MadpillBackendApplication.class, args);
    }

}
