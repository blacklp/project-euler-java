package main;

import java.util.HashSet;
import java.util.Set;

public class Problem41 {
    /*
     * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example, 2143 is a 4-digit
     * pandigital and is also prime.
       What is the largest n-digit pandigital prime that exists?
     */
    public int pandigitalPrime() {
        int numDigits = 9;
        int result = 0;
        while (numDigits > 1) {
            if (getDigitSum(numDigits) % 3 == 0) { // no prime with numDigits digits
                numDigits--;
                continue;
            }
            int largest = getLargestPandigitalPrime(numDigits);
            if (largest != -1) {
                result = largest;
                break;
            }
            numDigits--;
        }
        return result;
    }

    private int getLargestPandigitalPrime(int numDigits) {
        int first = 0; // 1234567 (7 digits)
        int last = 0; // 7654321
        int digit = numDigits;
        int multiplier = 1;
        int finalDigit = 1;
        while (digit > 0) {
            first += (digit * multiplier); // # zeros = (num_digits - 1)
            digit--;
            last += (finalDigit * multiplier);
            finalDigit++;
            multiplier *= 10;
        }
        for (int i = last; i > first; i-=2) {
            if (isPrime(i) && isPandigital(i, numDigits)) {
                return i;
            }
        }
        return -1; // shouldn't reach here, unless no pandigital prime for numDigits
    }

    private boolean isPandigital(int num, int numDigits) {
        Set<Integer> digits = new HashSet<>();
        while (num > 0) {
            int digit = num % 10;
            if (digit == 0 || digit > numDigits || digits.contains(digit)) {
                return false;
            }
            digits.add(digit);
            num /= 10;
        }
        return digits.size() == numDigits;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int getDigitSum(int n) {
        return (n * (n + 1)) / 2;
    }

    public static void main(String[] args) {
        Problem41 o = new Problem41();
        int result = o.pandigitalPrime();
        System.out.println("result: " + result);
    }
}
