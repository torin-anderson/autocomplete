import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Autocomplete {
    private Term[] sortedTerms;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            throw new IllegalArgumentException("No null!");
        }
        for (Term value : terms) {
            if (value == null) {
                throw new IllegalArgumentException("No null!");
            }
        }

        // Creating copy of terms for immutability and prevent bugs
        sortedTerms = new Term[terms.length];
        for (int i = 0; i < terms.length; i++) {
            sortedTerms[i] = terms[i];
        }
        Arrays.sort(sortedTerms);
    }

    // Returns all terms that start with the given prefix,
    // in descending order of weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("No null!");
        }

        Comparator<Term> prefix_order = Term.byPrefixOrder(prefix.length());
        Term key = new Term(prefix, 0);

        // Creating copy of terms for immutability and prevent bugs
        Term[] immutability = new Term[sortedTerms.length];
        for (int i = 0; i < sortedTerms.length; i++) {
            immutability[i] = sortedTerms[i];
        }

        int beginning = BinarySearchDeluxe.firstIndexOf(immutability, key, prefix_order);
        int end = BinarySearchDeluxe.lastIndexOf(immutability, key, prefix_order);

        if (beginning == -1 || end == -1) {
            Term[] allMatchingTerms = new Term[0];
            return allMatchingTerms;
        }
        else {
            Term[] allMatchingTerms = new Term[end - beginning + 1];
            for (int i = beginning; i <= end; i++) {
                allMatchingTerms[i - beginning] = sortedTerms[i];
            }
            Comparator<Term> weightComparator = Term.byReverseWeightOrder();

            Arrays.sort(allMatchingTerms, weightComparator);

            return allMatchingTerms;
        }
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("No null!");
        }

        Comparator<Term> prefix_order = Term.byPrefixOrder(prefix.length());
        Term key = new Term(prefix, 0);

        // Creating copy of terms for immutability and prevent bugs
        Term[] immutability = new Term[sortedTerms.length];
        for (int i = 0; i < sortedTerms.length; i++) {
            immutability[i] = sortedTerms[i];
        }

        int beginning = BinarySearchDeluxe.firstIndexOf(immutability, key, prefix_order);
        int end = BinarySearchDeluxe.lastIndexOf(immutability, key, prefix_order);

        if (beginning == -1 || end == -1) {
            return 0;
        }
        else {
            return end - beginning + 1;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}
