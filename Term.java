import java.util.Comparator;

public class Term implements Comparable<Term> {
    private String query;
    private long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null || weight < 0) {
            throw new IllegalArgumentException();
        }
        this.query = query;
        this.weight = weight;
    }


    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new byReverseWeightOrder();
    }

    public static class byReverseWeightOrder implements Comparator<Term> {
        public int compare(Term a, Term b) {
            return (int) (a.weight - b.weight);
        }
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) {
            throw new IllegalArgumentException();
        }

        return new ByPrefixOrder(r);
    }

    public static class ByPrefixOrder implements Comparator<Term> {
        private int r;

        public ByPrefixOrder(int r) {
            this.r = r;
        }

        public int compare(Term a, Term b) {
            int s1 = Math.min(a.query.length(), r);
            int s2 = Math.min(b.query.length(), r);

            return a.query.substring(0, s1).compareTo(b.query.substring(0, s2));
        }
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return query.compareTo(that.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return weight + "\t" + query;
    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}
