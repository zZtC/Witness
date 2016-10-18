package cn.kami7.witness;

/**
 * 目标对象
 */

public abstract class Subject<T> {
    T t;

    /**
     * 构造器设置目标对象
     * 初始置为未就绪状态
     * @param t
     */
    public Subject(T t){
        this.t = t;
        onReady(false);
    }

    /**
     * 会执行onReady通知目标作出变化
     * @param isReady
     */
    protected void onReady(boolean isReady){
        onReady(t, isReady);
    }

    /**
     * 当
     * @param subject
     * @param isReady
     */
    public abstract void onReady(T subject, boolean isReady);
}
