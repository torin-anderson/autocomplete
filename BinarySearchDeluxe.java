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
        int firstIndex = -1;
        while (low <= high) {
            int mid = (high + low) / 2;
            if (a[mid] == null) {
                throw new IllegalArgumentException("Array contains a null element.");
            }
            else if (comparator.compare(key, a[mid]) < 0) { // less than mid position
                high = mid - 1;
            }
            else if (comparator.compare(key, a[mid]) > 0) { // greater than mid position
                low = mid + 1;
            }
            else { // if key is equal to mid
                high = mid - 1;
                firstIndex = mid;
            }
        }
        return firstIndex;
    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException();
        }

        int low = 0;
        int high = a.length - 1;
        int lastIndex = -1;

        while (low <= high) {
            int mid = (high + low) / 2;
            if (a[mid] == null) {
                throw new IllegalArgumentException("Array contains a null element.");
            }
            else if (comparator.compare(key, a[mid]) < 0) { // less than mid position
                high = mid - 1;
            }
            else if (comparator.compare(key, a[mid]) > 0) { // greater than mid position
                low = mid + 1;
            }
            else { // if key is equal to mid
                low = mid + 1;
                lastIndex = mid;
            }
        }


        return lastIndex;
    }

    // unit testing (required)
    public static void main(String[] args) {
        BinarySearchDeluxe b = new BinarySearchDeluxe();
    }
}
