package com.example.aop

import com.example.aop.database.IAccountDao
import com.example.aop.database.IMembershipDao
import com.example.aop.model.Account
import groovy.transform.CompileStatic
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@CompileStatic
@SpringBootApplication
class AopApplication {

	static void main(String[] args) {
		SpringApplication.run(AopApplication, args)
	}

	@Bean
	static CommandLineRunner commandLineRunner(
			IAccountDao accountDao,
			IMembershipDao membershipDao) {
		return { runner -> {
			beforeAdvice(accountDao, membershipDao)
		} }
	}

	static void beforeAdvice(
			IAccountDao accountDao,
			IMembershipDao membershipDao) {
		Account account = new Account()
		account.name = 'test'
		account.name = 'level'
		accountDao.addAccount(account)
		membershipDao.addMembership()
	}
}
