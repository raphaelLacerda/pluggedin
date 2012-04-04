package br.com.pluggedin.test;

import java.util.Random;

public class TesteInsert {

	public static void main(String[] args) throws InterruptedException {

		Random random = new Random();
		long nextLong = random.nextInt(11);
		System.out.println(nextLong);

	}
}
