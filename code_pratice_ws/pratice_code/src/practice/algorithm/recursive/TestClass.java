package practice.algorithm.recursive;

import topcoder.srm.misc.*;
import topcoder.srm.archive.s566iv2.PenguinPals;
import topcoder.srm.archive.s566iv2.PenguinTiles;

public class TestClass {

	public static void main(String[] s) {
		System.out.println("value of " + -1%7);
	}

	private static long startTime() {
		return System.nanoTime();
	}

	private static void printTimeElapsed(long starttime) {
		long now  = System.nanoTime();
		System.out.println("elapsed time " + (now-starttime) + "");
	}

	private static void penguintiles() {
		PenguinTiles tiles = new PenguinTiles();

		System.out.println(tiles.minMoves(new String[] {".PPP",
				"PPPP",
				"PPPP",
				"PPPP"}));



		System.out.println(tiles.minMoves(new String[] {"P.",
				"PP",
				"PP",
				"PP"}));

		System.out.println(tiles.minMoves(new String[] {"PPP",
				"P.P",
				"PPP"}));

		System.out.println(tiles.minMoves(new String[]{"PPPPPPPP",
				".PPPPPPP"}));

		System.out.println(tiles.minMoves(new String[]{"PP",
				"P."}));
	}

	private static void testPenguinPals() {
		PenguinPals pals = new PenguinPals();

		System.out.println(pals.findMaximumMatching("RBRB")); // return 1

		System.out.println(pals.findMaximumMatching("RRBRBRBB")); // return 3

		System.out.println(pals.findMaximumMatching("BRBRB")); // return 2

		System.out.println(pals.findMaximumMatching("RRRR")); // return 2

		System.out.println(pals.findMaximumMatching("BBBBB")); // return 2

		System.out.println(pals.findMaximumMatching("RBRBRBRBR")); // return 4

		System.out.println(pals.findMaximumMatching("RRRBRBRBRBRB")); // return 5

		System.out.println(pals.findMaximumMatching("R")); // return 0

		System.out.println(pals.findMaximumMatching("RBRRBBRB")); // return 3

		System.out.println(pals.findMaximumMatching("RBRBBRBRB")); // return 4

		System.out.println(pals.findMaximumMatching("BRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBR")); // return 24
	}

	private static void testAlternateColors() {
		AlternateColors colors = new AlternateColors();
		long r,g,b,k;

		r=1;
		g=1;
		b=1;
		k=3;
		System.out.println(colors.getColor(r,g,b,k));

		r=3;
		g=4;
		b=5;
		k=4;
		System.out.println(colors.getColor(r,g,b,k));

		r=7;
		g=7;
		b=1;
		k=7;
		System.out.println(colors.getColor(r,g,b,k));

		r=1000000000000L;
		g=1;
		b=1;
		k=1000000000002L;
		System.out.println(colors.getColor(r,g,b,k));

		r=653;
		g=32;
		b=1230;
		k=556;
		System.out.println(colors.getColor(r,g,b,k));

		r=35;
		g=41;
		b=37;
		k=106;
		System.out.println(colors.getColor(r,g,b,k));

		r=33;
		g=38;
		b=18;
		k=85;
		System.out.println(colors.getColor(r,g,b,k));


		r=2;
		g=2;
		b=2;
		k=1;
		System.out.println(colors.getColor(r,g,b,k));


	}

	private static void testDivisibleSequence() {
		DivisibleSequence divisibleSequence = new DivisibleSequence();
		System.out.println(divisibleSequence.count(30,4));
		System.out.println(divisibleSequence.count(6,3));
		System.out.println(divisibleSequence.count(10,2));
		System.out.println(divisibleSequence.count(1,10000));
		System.out.println(divisibleSequence.count(25883,100000));// 991000009
		System.out.println(divisibleSequence.count(999999999, 999999999)); // 21000
		System.out.println(divisibleSequence.count(724618275, 1000000000));// 999973765
		System.out.println(divisibleSequence.count(618072047, 241039583)); // 241039583

//		System.out.println(divisibleSequence.fastCombination(4 + 999999999 - 1, 4));
//		System.out.println(divisibleSequence.combinationWithBIGint(BigInteger.valueOf(4+999999999-1), BigInteger.valueOf(4)));
//		System.out.println("" + Long.MAX_VALUE);

	}


	private static void testMonsterValley() {
		MonstersValley2 mon = new MonstersValley2();

		// expected is 27 for this...got after recursion failed system testing.
		long [] dread1 = new long [] {516, 76962, 715143, 4233449, 218939, 32096865, 46368459, 158595111, 46918028, 151723364, 277050296, 1095407144, 1367250788, 1604311035, 2020393301, 3839096555L, 2898212066L, 4936093512L, 2251843090L, 7205942741L, 10378594858L, 22314762689L, 19656860592L, 10619628123L, 18538763871L, 17359783920L, 60061570949L, 64433683696L, 49575833223L, 104812566815L, 3352944657L, 154812076164L, 38959320264L, 28869236952L, 54872805114L, 52328045398L, 34880941693L, 302781869195L, 307732887247L, 39783982279L, 416046105978L, 519292177113L, 185134353884L, 406685459040L, 315653902838L, 646603757540L};
		int[] price1 = new int[] {2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 1, 1, 2, 1, 1, 2, 2, 2, 1, 1, 2, 1, 2, 1, 1, 1, 2};
//		long[] dread1 = new long[] {1, 2, 4, 1000000000};
//		int [] price1 = new int[]{1, 1, 1, 2};

//		long[] dread1 = new long[] {8,5,10};
//		int [] price1 = new int[]{1, 1, 2};

		MonstersValley mon1 = new MonstersValley();
		System.out.println(mon1.minimumPrice(dread1,price1));

	}



	private static void testHisto() {
		ValueHistogram histo = new ValueHistogram();

		int [] values = new int[]{6, 2, 3, 3, 3, 7, 8, 1, 0, 9, 2, 2, 4, 3};
		String[] strings = histo.build(values);

		for (int i=0;i<strings.length;i++)
			System.out.println(strings[i]);
	}


	private static void testKnightCircuit() {
		KnightCircuit knightCircuit = new KnightCircuit();
		int maxSize = 0;

		maxSize = knightCircuit.maxSize(1,1);
		System.out.println("w = 1, h = 1 maxSize = " + maxSize);

		maxSize = knightCircuit.maxSize(15, 2);
		System.out.println("w = 15, h = 2  maxSize = " + maxSize);

		maxSize = knightCircuit.maxSize(3,3);
		System.out.println("w = 3, h =3  maxSize = " + maxSize);

		maxSize = knightCircuit.maxSize(100, 100);
		System.out.println("w = 100, h = 100  maxSize = " + maxSize);
	}


	private static void testLotterySortByOdds() {
		Lottery lottery = new Lottery();

		String [] input = null;
		String [] results = null;

		input = new String[]{
						"PICK ANY TWO: 10 2 F F"
						,"PICK TWO IN ORDER: 10 2 T F"
						,"PICK TWO DIFFERENT: 10 2 F T"
						,"PICK TWO LIMITED: 10 2 T T"
		};

		results = lottery.sortByOdds(input);
		for(String res : results)
			System.out.println(res);

		input = new String[]{
						"INDIGO: 93 8 T F",
						"ORANGE: 29 8 F T",
						"VIOLET: 76 6 F F",
						"BLUE: 100 8 T T",
						"RED: 99 8 T T",
						"GREEN: 78 6 F T",
						"YELLOW: 75 6 F F"
				};

		results = lottery.sortByOdds(input);
		for(String res : results)
			System.out.println(res);

		input = new String[]{
		};

		results = lottery.sortByOdds(input);
		for(String res : results)
			System.out.println(res);

//		System.out.println(lottery.factorial((long)25));

		System.out.println(lottery.calcCombination(5000, 4997));
	}


	private static void testWhatTime() {
		int secs = 0;
		Time time  = new Time();
		System.out.println(time.whatTime(secs));
	}


	private static void testPowerOutage() {
		PowerOutage powerOutage = new PowerOutage();
		int[] fromJunctions;
		int[] toJunctions;
		int[] lengths;

		fromJunctions = new int[]{
				0,1,0
		};

		toJunctions = new int[] {
				1,2,3
		};

		lengths = new int[] {
				10,10,10
		};

		System.out.println(powerOutage.estimateTimeOut(fromJunctions, toJunctions, lengths));


		fromJunctions = new int[]{
				0,0,0,1,4
		};

		toJunctions = new int[] {
				1,3,4,2,5
		};

		lengths = new int[] {
				10,10,100,10,5
		};

		System.out.println(powerOutage.estimateTimeOut(fromJunctions, toJunctions, lengths));


		fromJunctions = new int[]{0,0,0,1,4,4,6,7,7,7,20};

		toJunctions = new int[]{1,3,4,2,5,6,7,20,9,10,31};

		lengths = new int[]{10,10,100,10,5,1,1,100,1,1,5};

		System.out.println(powerOutage.estimateTimeOut(fromJunctions, toJunctions, lengths));


		fromJunctions = new int[]{1, 1, 3, 7, 4, 2, 0, 0, 4, 4, 4, 6};

		toJunctions = new int[]{4, 3, 7, 9, 10, 5, 1, 2, 12, 11, 6, 8};

		lengths = new int[]{756086, 1354570, 1221591, 178962, 1697791, 359837, 1296576, 1527871, 1198063, 153975, 1229519, 48712};

		System.out.println(powerOutage.estimateTimeOut(fromJunctions, toJunctions, lengths));
	}
}


