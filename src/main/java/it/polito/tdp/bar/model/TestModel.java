package it.polito.tdp.bar.model;

import java.util.concurrent.ThreadLocalRandom;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ThreadLocalRandom random = ThreadLocalRandom.current();
		int rand = random.nextInt(1,11);
		System.out.println(rand);
		
		Simulator s = new Simulator();
		s.run();
		System.out.println(s.getNumClientiInsoddisfatti()+" "+s.getNumClientiSoddisfatti()+" "+s.getNumClientiTotali());
		
	}

}
