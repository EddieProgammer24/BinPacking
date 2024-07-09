import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class BinPacking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("how many items are to be packed? ");
            int number = sc.nextInt();

            System.out.println("enter the bin capacity? ");
            int binCapacity = sc.nextInt();

            ArrayList<Integer> items = generateRandomItems(number, binCapacity);
            System.out.println("................................................................................");
            System.out.println("items to pack:");
            printItems(items);
            System.out.println("................................................................................");

            // First Fit
            System.out.println("First Fit:");
            int firstFitBins = firstFit(items, binCapacity);
            System.out.println("Total number of bins used for First Fit is " + firstFitBins);
            System.out.println("................................................................................");

            // Next Fit
            System.out.println("Next Fit:");
            int nextFitBins = nextFit(items, binCapacity);
            System.out.println("Total number of bins used for Next Fit is " + nextFitBins);
            System.out.println("................................................................................");

            System.out.println("Press any key to continue");
            try {
                System.in.read();
            } catch (Exception e) {
            }
        }
    }

    public static ArrayList<Integer> generateRandomItems(int number, int upperBound) {
        ArrayList<Integer> items = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < number; i++) {
            items.add(r.nextInt(upperBound) + 1);
        }
        return items;
    }

    public static void printItems(ArrayList<Integer> items) {
        for (int item : items) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static int firstFit(ArrayList<Integer> items, int binCapacity) {
        ArrayList<Integer> bins = new ArrayList<>();
        for (int item : items) {
            boolean placed = false;
            for (int i = 0; i < bins.size(); i++) {
                if (bins.get(i) + item <= binCapacity) {
                    bins.set(i, bins.get(i) + item);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                bins.add(item);
            }
        }
        printBins(bins);
        return bins.size();
    }

    public static int nextFit(ArrayList<Integer> items, int binCapacity) {
        ArrayList<Integer> bins = new ArrayList<>();
        bins.add(0);
        for (int item : items) {
            if (bins.get(bins.size() - 1) + item <= binCapacity) {
                bins.set(bins.size() - 1, bins.get(bins.size() - 1) + item);
            } else {
                bins.add(item);
            }
        }
        printBins(bins);
        return bins.size();
    }

    public static void printBins(ArrayList<Integer> bins) {
        for (int i = 0; i < bins.size(); i++) {
            System.out.println("Bin " + (i + 1) + ": " + bins.get(i));
        }
        System.out.println("Value in each bin:");
        for (int bin : bins) {
            System.out.println(bin);
        }
    }
}
