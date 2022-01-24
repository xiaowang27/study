package chapter09.abstract_;

public abstract class Template {
    public abstract void job();

    public void caleTime() {
        long start = nowTime();
        job();
        long end = nowTime();
        System.out.println("耗时" + (end - start) + "ms");
    }

    public long nowTime() {
        return System.currentTimeMillis();
    }
}
