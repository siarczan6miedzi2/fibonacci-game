public final class GeneralMethods
{
	public final static int fib(int n)
	{
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
}