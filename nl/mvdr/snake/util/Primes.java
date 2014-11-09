package nl.mvdr.snake.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for prime numbers.
 * 
 * @author Martijn van de Rijdt
 */
public class Primes {
    /** Private constructor to prevent utility class instatiation. */
    private Primes() {
        super();
    }
    
    /**
     * Sieve all the primes up to the given number and return all the gaps.
     * 
     * @param n upper limit
     * @return gaps
     */
    public static List<Integer> sieveGaps(int n) {

        // Sieve of Eratosthenes
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i; i * j <= n; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        // Return the gaps:
        List<Integer> gaps = new ArrayList<Integer>();
        int lastPrime = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                gaps.add(i - lastPrime);
                lastPrime = i;
            }
        }
        return gaps;
    }
}
