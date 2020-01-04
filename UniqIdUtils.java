public class TestF {
    static Random random = new Random();
    static Set<Long> set = Collections.newSetFromMap(new ConcurrentHashMap<Long, Boolean>());
    public static void main(String[] args) {
        for (int i=0;i<1000;i++){
            Executors.execute(()->{
                for (int j=0;j<10000;j++) {
                    long num = uniqId();
                    if (set.contains(num)) {
                        System.out.println(num);
                    }
                    set.add(num);
                }
                System.out.println(Thread.currentThread().getName() + " end! set.size=" + set.size());
            });
        }
    }

    private static long uniqId() {
        String nanoRandom = System.nanoTime() + "" + random.nextInt(99999);
        int hash = Math.abs(UUID.randomUUID().hashCode());
        int needAdd = 19 - String.valueOf(hash).length() + 1;
        return Long.valueOf(hash + "" + nanoRandom.substring(needAdd));
    }
}
