import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException();
        }

        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (comparator.compare(key, a[mid]) < 0) { // less than mid position
                high = mid - 1;
            }
            else if (comparator.compare(key, a[mid]) > 0) { // greater than mid position
                low = mid + 1;
            }
            else { // if key is equal to mid

                if (mid == 0) { // if key is first item
                    return mid;
                }
                else if (comparator.compare(key, a[mid - 1])
                        > 0) // check if previous key is less than key
                {
                    return mid;
                }
                else // if previous is equal to or greater than key
                {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException();
        }

        int low = 0;
        int high = a.length - 1;
        int length = a.length - 1;

        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (comparator.compare(key, a[mid]) < 0) { // less than mid position
                high = mid - 1;
            }
            else if (comparator.compare(key, a[mid]) > 0) { // greater than mid position
                low = mid + 1;
            }
            else { // if key is equal to mid
                if (mid == length) { // if key is all the way to the right. (last key)
                    return mid;

                }
                else if (comparator.compare(key, a[mid + 1]) < 0) // Next position greater?
                {
                    return mid;
                }
                else // if next item is also equal/less
                {
                    low = mid + 1;
                }
            }
        }


        return -1;
    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}
