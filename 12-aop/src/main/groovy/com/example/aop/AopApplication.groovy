package com.example.aop

import com.example.aop.database.IAccountDao
import com.example.aop.database.IMembershipDao
import com.example.aop.model.Account
import com.example.aop.service.ITrafficFortuneService
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
			IMembershipDao membershipDao,
			ITrafficFortuneService trafficFortuneService
	) {
		return { runner -> {
			// beforeAdvice(accountDao, membershipDao)
			// afterReturningAdvice(accountDao)
			// runTrafficFortuneService(trafficFortuneService)
			runAroundWithException(trafficFortuneService)
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

	static void afterReturningAdvice(IAccountDao accountDao) {
		List<Account> accounts = accountDao.findAccounts()
		for (Account account in accounts) {
			println("Account: $account")
		}
	}

	static void runTrafficFortuneService(ITrafficFortuneService service) {
		// println('Calling getFortune()')
		println("Fortune: ${service.fortune}")
	}

	static void runAroundWithException(ITrafficFortuneService service) {
		println("Fortune: ${service.fortuneWithErr}")
	}
}
