package helpers;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class Counter {
    //Fields
    private int countNum;

    /**
     * Contractor.
     * @param countNum - the num that the counter is holds.
     */
    public Counter(int countNum) {
        this.countNum = countNum;
    }
    /**
     * increase - add number to current count.
     * @param number - the num we add to the counter
     */
    public void increase(int number) {
        this.countNum = this.countNum + number;
    }
    /**
     * decrease - decrease the number to current count.
     * @param number - the num we add to the counter
     */
    public void decrease(int number) {
        this.countNum = this.countNum - number;
    }
    // get current count.

    /**
     * getter.
     * @return int - the current value in the counter.
     */
    public int getValue() {
        return this.countNum;
    }
}