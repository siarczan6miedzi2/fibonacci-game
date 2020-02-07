import java.util.Random;

public final class GeneralMethods
{
	public final static int fib(int n) // nth Fibonacci number
	{
		if (n < 0) return -1; // TODO: learn throwing errors
		if (n == 0) return 0;
		if (n == 1) return 1;
		int k1 = 0; int k2 = 1; int tmp = 0;
		for (int i = 1; i < n; i++)
		{
			tmp = k1;
			k1 = k2;
			k2 = k1 + tmp;
		}
		return k2;
	}
	
	public final static int simplePower(int n, int k) // n^k
	{
		if (k < 0) return -1; // TODO: learn throwing errors
		if (n == 0) return 0; // yeah, I know, 0^0 should throw error also; TODO later
		if (k == 0) return 1;
		int result = 1;
		for (int i = 0; i < k; i++) result *= n;
		return result;
	}
	
	public final static int floorSqrt(int n) // floor(sqrt(n))
	{
		if (n < 0) return -1; // TODO: learn throwing errors
		int result = 1;
		while(result*result <= n)
			result++;
		return result-1;
	}
	
	public final static int randMax(int n) // random integer up to n
	{
		Random r = new Random();
		return r.nextInt(n+1);
	}
}
