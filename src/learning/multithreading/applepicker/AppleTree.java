package learning.multithreading.applepicker;

import java.util.concurrent.TimeUnit;

public class AppleTree {
    private final String label;
    private final int numberOfApples;

    public AppleTree(String label) {
        this.label = label;
        numberOfApples = 3;
    }

    public int pickApples(String workerName) {
        try {
            System.out.printf("%s started picking apples from %s %n", workerName, label);
            TimeUnit.SECONDS.sleep(1);
            System.out.printf("%s picked %s apples from %s %n", workerName, numberOfApples, label);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        return numberOfApples;
    }

    public static AppleTree[] newAppleTreeGarden(int numberOfTrees) {
        AppleTree[] appleTrees = new AppleTree[6];
        for(int i = 0; i < numberOfTrees; i++) {
            appleTrees[i] = new AppleTree("AppleTree" + i);
        }
        return appleTrees;
    }
}
