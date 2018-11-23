package jp.cafebabe.pochi.cli.scripts.helper;

public class SystemInfoHelper {
    public String version(){
        Package helperPackage = findPackage();
        return helperPackage.getImplementationVersion();
    }

    public double measure(Runnable action) {
        long startTime = System.nanoTime();
        action.run();
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    private Package findPackage(){
        return getClass().getPackage();
        // return getClass().getClassLoader()
        //         .getDefinedPackage("jp.cafebabe.pochi.cli.scripts.helper");
    }
}
