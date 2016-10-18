package cn.kami7.witness;

/**
 * 事件发出源
 */

public abstract class SourceItem<T> {
    /**
     * 源事件对象
     */
    protected T source;
    /**
     * 观察处理器
     */
    private Witness witness;

    /**
     * 构造函数加入事件源
     * @param source
     */
    public SourceItem(T source){
        this.source = source;
    }

    /**
     * 设置观察处理器
     * 当设置进去的时候会对事件源的初始状态做一次判断
     * @param witness
     */
    protected void setWitness(Witness witness) {
        this.witness = witness;
        // 做首次判断
        if(isReady(source)){
            this.witness.initState(true);
        }
    }

    /**
     * 需要实现的方法
     * 指示怎样的状态是就绪状态
     * @param source
     * @return 如果source达到就绪状态则返回true，否则返回false
     */
    public abstract boolean isReady(T source);

    /**
     * 注销时候取消witness
     */
    protected void unregister(){
        witness = null;
    }

    /**
     * 当状态变化时调用，通知观察处理器事件发生了变化
     */
    public void notifyChanged(){
        witness.notifyChanged(this, isReady(source));
    }
}
