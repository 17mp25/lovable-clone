package com.mp.projects.lovable_clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneId;
import java.util.TimeZone;

@SpringBootApplication
public class LovableCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(LovableCloneApplication.class, args);
        System.out.println("ZoneId: " + ZoneId.systemDefault());
        System.out.println("TimeZone: " + TimeZone.getDefault());
	}

}
