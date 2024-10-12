import java.util.Comparator;

public class Term implements Comparable<Term> {
    private String savedQuery;
    private long savedWeight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null || weight < 0) {
            throw new IllegalArgumentException();
        }
        savedQuery = query;
        savedWeight = weight;
    }


    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    public static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term a, Term b) {
            return Long.compare(b.savedWeight, a.savedWeight);
        }
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) {
            throw new IllegalArgumentException();
        }

        return new PrefixOrder(r);
    }

    public static class PrefixOrder implements Comparator<Term> {
        private int r;

        public PrefixOrder(int r) {
            this.r = r;
        }

        public int compare(Term a, Term b) {
            int s1 = Math.min(a.savedQuery.length(), r);
            int s2 = Math.min(b.savedQuery.length(), r);

            return a.savedQuery.substring(0, s1).compareTo(b.savedQuery.substring(0, s2));
        }
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return savedQuery.compareTo(that.savedQuery);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return savedWeight + "\t" + savedQuery;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Term b = new Term("Hello", 10000);
    }
}
