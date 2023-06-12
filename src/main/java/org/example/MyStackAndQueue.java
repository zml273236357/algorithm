package org.example;

import sun.security.mscapi.CPublicKey;

/***
 * 双向链表实现栈和队列
 */
public class MyStackAndQueue {

    public static class DoubleNode<T>{
        private T value;
        private DoubleNode next;
        private DoubleNode pre;
        public DoubleNode(T value){
            this.value = value;
        }
    }

    private DoubleNode head;//头节点
    private DoubleNode tail;//尾节点

    //从头部节点插入
    public DoubleNode addHead(int value){
        DoubleNode newNode = new DoubleNode(value);
        if(head == null){
            head = newNode;
            tail = newNode;
        }else {
            newNode.next = head;
            head.pre = newNode;
            head = newNode;
        }
        return newNode;
    }

    //从尾部节点插入
    public DoubleNode addTail(int value){
        DoubleNode newNode = new DoubleNode(value);
        if(tail == null){
            tail = newNode;
            head = newNode;
        }else{
            newNode.pre = tail;
            tail.next = newNode;
            tail = newNode;
        }
        return newNode;
    }

    //从头部弹出
    public DoubleNode popHead(){
        if(head == null){
            return null;
        }
        DoubleNode cur = head;
        if(head == tail){
            head = null;
            tail = null;
        }else{
            head = head.next;
            head.pre = null;
            cur.next = null;
        }
        return cur;
    }

    //从尾部弹出
    public DoubleNode popTail(){
        if(tail == null){
            return null;
        }
        DoubleNode cur = tail;
        if(tail == head){
            tail = null;
            head = null;
        }else{
            tail = tail.pre;
            cur.pre = null;
            tail.next = null;
        }
        return cur;
    }

    public class MyStack{
        public DoubleNode push(int value){
            return addHead(value);
        }
        public DoubleNode pop(){
            return popHead();
        }
    }

    public class MyQueue{
        public DoubleNode push(int value){
            return addHead(value);
        }
        public DoubleNode poll(){
            return popTail();
        }
    }
}


/**
 * 基于数组实现队列（环形数组）
 */
 class MyQueueByArray{
    private int[] arr;
    private int size;
    private int putIndex;
    private int takeIndex;
    private int limit;
    public MyQueueByArray(int capcity){
        arr = new int[capcity];
        putIndex = 0;
        takeIndex = 0;
        size = 0;
        limit = capcity;
    }

    public void addQueue(int value){
        if(size == limit){
            throw new RuntimeException("队列已满");
        }else{
            arr[putIndex] = value;
            if(putIndex == limit -1){
                putIndex = 0;
            }else{
                putIndex++;
            }
            size++;
        }
    }

    public int getQueue(){
        if(size == 0){
            return -1;
        }
        int result = arr[takeIndex];
        if(takeIndex == limit -1){
            takeIndex = 0 ;
        }else{
            takeIndex++;
        }
        size--;
        return result;
    }
}

/**
 * 基于数组实现栈结构
 */
class MyStackByArray {
    private int[] arr;
    private int size;
    private int index;
    private int limit;

    public MyStackByArray(int capcity) {
        arr = new int[capcity];
        index = 0;
        size = 0;
        limit = capcity;
    }

    public void addStack(int value){
        if(size == limit){
            throw new RuntimeException("栈已满");
        }
        arr[index++] = value;
    }

    public int popStack(){
        if(size == 0){
            throw new RuntimeException("栈已空");
        }
        int result = arr[index--];
        return result;
    }
}