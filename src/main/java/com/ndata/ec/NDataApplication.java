package com.ndata.ec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Class for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.ndata.ec.*")
@EnableJpaRepositories(basePackages = "com.ndata.ec.*")
@EntityScan("com.ndata.ec.entities")
public class NDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(NDataApplication.class, args);
	}

}
