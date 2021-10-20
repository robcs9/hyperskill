
class FixingExceptions {

    public static void calculateSquare(int[] array, int index) {
        // write your code here
        if (array != null && index >= 0 && index < array.length) {
            System.out.printf("%d\n", array[index] * array[index]);
        } else {
            System.out.println("Exception!");
        }
    }
}
