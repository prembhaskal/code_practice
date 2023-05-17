package practice.math_problems;

/**
 * User: premkumar.bhaskal
 * Date: 4/22/13
 * Time: 5:51 PM
 */
public class ExponentBySquaring {

	int MOD = 1000000007;

	public long powerIterative(long num, long pow) {
		long power = 1;

		while (pow > 0) {
			if (pow%2==1) {
				power = (power * num)%MOD;
			}

			num = (num * num)%MOD;
			pow /= 2;
		}

		return power;
	}


	public long powerRecursive(long num, long pow) {
		if (pow==0)
			return 1;
		if (pow ==1)
			return num;

		if (pow%2==0)
			return powerRecursive((num * num)%MOD, pow/2);
		else
			return (num * powerRecursive((num*num)%MOD, pow/2))%MOD;
	}
}
