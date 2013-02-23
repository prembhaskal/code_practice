package topcoder.srm.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottery {


	public String[] sortByOdds(String[] rules) {
		String[] tickets = new String[rules.length];

		List<Ticket> ticketList = new ArrayList<Ticket>();

		for (String rule: rules) {
		// get the input parameters
			String[] lotteryDetails = rule.split(":");
			String lotteryName = lotteryDetails[0];

			String [] choiceDetails = lotteryDetails[1].split(" ");
			int choices = Integer.parseInt(choiceDetails[1]);
			int slots = Integer.parseInt(choiceDetails[2]);
			boolean sorted = choiceDetails[3].equals("T")?true:false;
			boolean unique = choiceDetails[4].equals("T")?true:false;

			long chances = 0;
			if (sorted) {
				if (unique){
					// choosing r from n ==> nCr
					chances = combination(choices, slots);
				} else {
					// choosing r from n, repetition allowed (n+r-1)Cr
					chances = combination(choices + slots - 1, slots);
				}

			} else {
				if (unique){
					// after choosing * no. of ways to arrange
					chances = combination(choices, slots) * factorial(slots);
				} else {
					// arrange n nos. in k slots , repitition allowed
					chances = pow(choices, slots);
				}
			}

			ticketList.add(new Ticket(lotteryName, chances));
		}

		// sort1 the list
		Collections.sort(ticketList);

		for(int i=0;i<ticketList.size();i++)
			tickets[i]=ticketList.get(i).lotName;

		return tickets;
	}

	public long factorial(long n) {
		if (n==1 || n==0)
			return 1;
		return n*factorial(n-1);
	}

	public long pow(int n, int k) {
		long res = 1;
		while (k>0) {
			res = res * n;
			k--;
		}
		return res;
	}


	public long calcCombination(long n, long r){
		if ( r > n/2)
			r = n-r;
		return combination(n,r);
	}

	// use the fact that nCr = (n/r)*((n-1)C(r-1))
	// and any no.   xCo = 1;
	public long combination(long n, long r) {
		if(r==0)
			return 1;
		else {
			long num = n * combination(n - 1, r - 1);
			return num/r;
		}
	}
}



class Ticket implements Comparable<Ticket> {
	public long lotteryChances;
	public String lotName;

	public Ticket(String lotName, long lotteryChances) {
		this.lotName = lotName;
		this.lotteryChances = lotteryChances;
	}

	@Override
	public int compareTo(Ticket ticket) {
		if (ticket.lotteryChances < this.lotteryChances)
			return 1;
		else if (ticket.lotteryChances > this.lotteryChances)
			return -1;
		else
		// reverse ordering
			return ticket.lotName.compareTo(this.lotName) * (-1);
	}
}
