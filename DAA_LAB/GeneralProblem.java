import java.util.*;

// A class to store the license index and its cost at a particular month
 class License {
    int index;
    double cost;

    public License(int index, double cost) {
        this.index = index;
        this.cost = cost;
    }
}

// A class to compare licenses based on their cost at the current month
class LicenseComparator implements Comparator<License> {
    public int compare(License a, License b) {
        return Double.compare(a.cost, b.cost);
    }
}

public class GeneralProblem {
    // A function to compute the order in which to buy the licenses to minimize the total cost
    public static List<Integer> computeOrder(List<Double> growthRates) {
        int n = growthRates.size();

        // Initialize a list of licenses with their current cost at month 0
        List<License> licenses = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            licenses.add(new License(i, 100 * growthRates.get(i)));
        }

        // Sort the licenses based on their cost at month 0
        Collections.sort(licenses, new LicenseComparator());

        // Initialize the order of licenses to buy
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            order.add(licenses.get(i).index);
        }

        return order;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // Input the growth rates of the licenses
        List<Double> growthRates = new ArrayList<>();
        System.out.println("Enter the growth rates of the licenses: ");
        for (int i = 0; i < n; i++) {
            growthRates.add(sc.nextDouble());
        }

        // Compute the order of licenses to buy
        List<Integer> order = computeOrder(growthRates);

        // Output the order of licenses to buy
        System.out.print("The order of licenses to buy is: ");
        for (int i = 0; i < n; i++) {
            System.out.print(order.get(i) + " ");
        }
        System.out.println();
    }
}
