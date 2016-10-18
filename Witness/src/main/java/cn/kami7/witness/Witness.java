package cn.kami7.witness;

import java.util.ArrayList;
import java.util.List;

/**
 * View观察者的事件处理器
 */

public class Witness {
    /**
     * 需要观察的View列表
     */
    private List<SourceItem> listItem;
    /**
     * 需要触发的View
     */
    private Subject subject;
    /**
     * 源View的完成度
     * 一个View从未完成状态变为完成状态则自加
     * 一个View从完成状态变为未完成状态则自减
     */
    private int readyCount = 0;

    /**
     * 默认构造器
     */
    public Witness(){
        listItem = new ArrayList<>();
    }

    /**
     * 带预设容量的构造器
     * @param capability listItem的预设大小
     */
    public Witness(int capability){
        listItem = new ArrayList<>(capability);
    }

    /**
     * 设置目标View
     * @param subject
     */
    public void setSubject(Subject subject){
        this.subject = subject;
    }

    /**
     * 加入原View
     * 需要先调用setSubject
     * @param item
     */
    public void addItem(SourceItem item){
        if(subject == null){
            throw new NullPointerException("Subject should be set first");
        }
        listItem.add(item);
        item.setWitness(this);
    }

    /**
     * 当所有对象加完后调用
     * 判断当前状态
     * 不调用初始状态会错误
     */
    public void allAdded(){
        if(readyCount == listItem.size()){
            subject.onReady(true);
        }else{
            subject.onReady(false);
        }
    }

    /**
     * 初始化状态
     * @param isReady
     */
    protected void initState(boolean isReady){
        if(isReady){
            readyCount++;
        }else{
            readyCount--;
        }
    }

    /**
     * 被SourceItem调用
     * 通知Subject做出变化
     *
     * @param sourceItem
     * @param isReady
     */
    protected void notifyChanged(SourceItem sourceItem, boolean isReady){
        if(isReady){
            readyCount++;
        }else{
            readyCount--;
        }

        if(readyCount == listItem.size()){
            subject.onReady(true);
        }else if(readyCount == listItem.size() - 1){
            subject.onReady(false);
        }
    }

    /**
     * 注销观察处理器
     * 后续的操作将不起作用
     */
    public void unregister(){
        for(SourceItem item : listItem){
            item.unregister();
        }

        listItem.clear();
        subject = null;
    }
}
