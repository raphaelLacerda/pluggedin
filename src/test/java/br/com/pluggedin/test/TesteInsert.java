package br.com.pluggedin.test;

import java.util.Random;

import org.junit.Ignore;

@Ignore
public class TesteInsert extends AbstractTest{

	public static void main(String[] args) throws InterruptedException {

		Random random = new Random();
		long nextLong = random.nextInt(11);
		System.out.println(nextLong);

	}
}
