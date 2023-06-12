package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class sort {

    public static void main(String[] args) {
        System.out.println("数组排序----------------------");
        int[] arr = new int[]{4,1,3,9,5,3,6};
        //排序相关
        //selectSort(arr);
        //bubbleSort(arr);
        //insertSort(arr);
        arr = new int[]{2,5,4,3};
        mergeSort(arr,0,arr.length -1);
        printArr(arr);

        System.out.println("二分查找----------------------");
        //二分查找相关
        int result = binarySearch(arr,9);
        System.out.println(result);
        int index = binarySearchNearLeft(arr,3);
        System.out.println(index);
        index = binarySearchNearRight(arr,3);
        System.out.println(index);
        arr = new int[]{14,11,9,10,5,12,13};
        index = binarySearchLittle(arr);
        System.out.println(index);

        //异或运算相关
        int[] arr1 = new int[]{4,4,3,9,5,3,5};
        System.out.println("异或运算----------------------");
        changePos(3,5);
        changePos1(arr1);
        int[] arr2 = new int[]{4,4,3,9,5,3,5,6};
        changePos2(arr2);
        bitCount1(7);

        //链表
        System.out.println("链表----------------------");
        Node node1 = new Node(3);
        Node node2 = new Node(4);
        Node node3 = new Node(5);
        Node node4 = new Node(6);
        Node node5 = new Node(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Node node = resvertLinkedList(node1);
        while(node != null){
            System.out.println(node.value);
            node = node.next;
        }
        System.out.println("");System.out.println("");System.out.println("");
        DoubleNode node11 = new DoubleNode(3);
        DoubleNode node22 = new DoubleNode(4);
        DoubleNode node33 = new DoubleNode(5);
        DoubleNode node44 = new DoubleNode(6);
        DoubleNode node55 = new DoubleNode(7);
        node11.next = node22;
        node11.pre = null;
        node22.next = node33;
        node22.pre = node11;
        node33.next = node44;
        node33.pre = node22;
        node44.next = node55;
        node44.pre = node33;
        DoubleNode doubleNode = resvertDoubleLinkedList(node11);
        while(doubleNode != null){
            System.out.println(doubleNode.value + "-" + (doubleNode.pre == null ? null : doubleNode.pre.value) + "-" + (doubleNode.next == null ? null:doubleNode.next.value));
            doubleNode = doubleNode.next;
        }
        System.out.println("");System.out.println("");System.out.println("");
        Node node10 = new Node(3);
        Node node20 = new Node(4);
        Node node30 = new Node(5);
        Node node40 = new Node(6);
        Node node50 = new Node(7);
        node10.next = node20;
        node20.next = node30;
        node30.next = node40;
        node40.next = node50;
        Node node6 = deleteNode(node10, 3);
        while(node6 != null){
            System.out.println(node6.value);
            node6 = node6.next;
        }

        System.out.println("递归--------------------------------------------");
        //递归
        arr = new int[]{4,1,3,9,5,3,6};
        int maxValue = getMaxValue(arr, 0, arr.length-1);
        System.out.println(maxValue);
        //mergeSort(arr,0,arr.length -1);
        mergeSort2(arr);
        printArr(arr);
    }


    /**
     * 1.选择排序
     */
    public static void selectSort(int[] arr){
        //0~N-1 获取到最小值 然后放到0位置
        // 1~N-1 获取到最小值 然后放到1位置
        //  2~N-1 获取到最小值 然后放到2位置
        //双层for循环，外层++ 内层++ 需要最小值临时变量
        for(int i = 0; i < arr.length; i++){
            int minIndex = i;//最小值在哪个位置上 i~ N-1
            for(int j = i +1 ;j < arr.length; j++){ //与i后面进行对比找到最小值下标
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr,i,minIndex);
        }
    }

    /**
     * 2.冒泡排序
     */
    public static void bubbleSort(int[] arr){
        //0~N-1 比较 如果大则与后面置换位置 然后放到N-1位置
        //0~N-2 比较 如果大则与后面置换位置 然后放到N-2位置
        //0~N-3 比较 如果大则与后面置换位置 然后放到N-3位置
        //双层for循环，外层-- 内层++
        for(int i = arr.length -1; i >= 0 ; i--){
            for(int j = 0 ; j <= i - j; j++){
                if(arr[j] > arr[i]){
                    swap(arr,i,j);
                }
            }
        }
    }

    /**
     * 3.插入排序
     */
    public static void insertSort(int[] arr){
        //0~0 保证有序，和前面元素比较 如果比前面元素小则置换位置
        //0~1 保证有序，和前面元素比较 如果比前面元素小则置换位置
        //0~N 保证有序，和前面元素比较 如果比前面元素小则置换位置
        //双层for循环，外层++ 内层--
        for(int i = 1; i < arr.length; i++){
            for(int j = i -1; j >= 0;j--){
                if(arr[j+1] < arr[j]){
                    swap(arr,j,j + 1);
                }
            }
            /*for(int j = 0; j < i ; j++){
                if(arr[i] < arr[j]){
                    swap(arr,i,j);
                }
            }*/
        }
    }

    /**
     * 4.归并排序：使用递归方式
     */
    public static void mergeSort(int[] arr,int L,int R){
        //整体是递归，左边排好序 + 右边排好序 + merge 让整体有序
        if(L == R){
            return;
        }
        int mid = (L + R) >> 1;
        mergeSort(arr,L,mid);
        mergeSort(arr,mid + 1,R);
        merge(arr,L,mid,R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R -L +1];
        int index = 0;
        int p1 = L;
        int p2 = M +1;
        while(p1 <= M && p2 <= R){//如果两部分都没结束
            if(arr[p1] <= arr[p2]){
                help[index++] = arr[p1++];
            }else{
                help[index++] = arr[p2++];
            }
        }
        while(p1 <= M){//如果剩下的都是L-M的部分 则直接遍历放到help中
            help[index++] = arr[p1++];
        }
        while(p2 <= R){//如果剩下的都是M-R的部分 则直接遍历放到help中
            help[index++] = arr[p2++];
        }
        for(int i = 0; i < help.length; i++){//将排好序的部分替换到原arr中
            arr[L + i] = help[i];
        }
    }

    /**
     * 4.归并排序：非递归方式
     */
    public static void mergeSort2(int[] arr){
        int N = arr.length;
        int mergeSize = 1;//当前有序的，左数组长度
        while(mergeSize < N){
            int L = 0 ;
            while(L < N){
                int M = L + mergeSize - 1;//L..M 左组
                int R = Math.min(M + mergeSize,N - 1);
                if(R >= N){
                    break;
                }
                merge(arr,L,M,R);
                L = R +1;
            }
            mergeSize <<= 1;
        }
    }

    /**
     * 4.归并排序：相关面试题
     * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫做数组小和，求数组小和
     * 例子：[1,3,4,2,5]
     * 1左边比1小的数：没有
     * 3左边比3小的数：1
     * 4左边比4小的数：1，3
     * 2左边比2小的数：1
     * 5左边比5小的数：1，3，4，2
     * 所以数组的小和为1+1+3+1+1++3+4+2=16
     */

    /**
     * 1.二分查找 有序数组中是否存在某个数 并返回下标
     */
    public static int binarySearch(int[] arr,int num){
        int start = 0;
        int end = arr.length -1;

        while(start <= end){
            int mid =(start + end) >> 1;
            if(arr[mid] == num){
                return mid;
            }else if(arr[mid] < num){
                start = mid + 1;
            }else if(arr[mid] > num){
                end = mid -1;
            }

        }
        return -1;
    }

    /**
     * 2.二分查找 有序数组中>=某个数的最左侧位置 返回下标
     */
    public static int binarySearchNearLeft(int[] arr,int num){
        int start = 0;
        int end = arr.length -1;
        int index = -1;
        while(start <= end){
            int mid =start + ((end -start ) >> 1);
            //int mid =(start + end) >> 1;
            if(arr[mid] >= num){
                index = mid;
                end = mid -1;
            }else{
                start = mid + 1;
            }
        }
        return index;
    }

    /**
     * 3.二分查找 有序数组中<=某个数的最右侧位置 返回下标
     */
    public static int binarySearchNearRight(int[] arr,int num){
        int start = 0;
        int end = arr.length -1;
        int index = -1;
        while(start <= end){
            int mid =start + ((end -start ) >> 1);
            //int mid =(start + end) >> 1;
            if(arr[mid] > num){
                end = mid -1;
            }else{
                index = mid;
                start = mid + 1;
            }
        }
        return index;
    }

    /**
     * 4.二分查找 给定任意一个无序数组，任意两个相邻的数不等，要求返回一个局部最小位置
     */
    public static int binarySearchLittle(int[] arr){
        //先判断0位置是否局部最小，是则直接返回
        //再判断N-1位置是否局部最小，是则直接返回
        //否则数组从0~N-1整体是先向下最后向上的走向，所以中间必存在局部最小位置
        if(arr[0] < arr[1]){
            return 0;
        }else if(arr[arr.length -1] < arr[arr.length -2]){
            return arr.length -1;
        }else{
            int left = 0;
            int right = arr.length;
            while(left <= right){
                int mid = left + ((right - left) >> 1);
                if(arr[mid] < arr[mid -1]){//如果中间位置比前一个位置小则返回
                    return mid;
                }else if(arr[mid] < arr[mid + 1]){//如果中间位置比后一个位置小则返回
                    return mid;
                }else{//否则以中间节点为最右侧循环查找 以左侧范围进行二分(也可以以右侧进行二分 left = mid)
                    right = mid;
                }
            }
            return -1;
        }

    }

    /**
     * 1.异或运算 不使用额外变量交换两个数
     */
    public static void changePos(int a,int b){//a = 10,b = 123
        a = a ^ b; //a = 10 ^ 123
        b = a ^ b; //b = 10 ^ 123 ^ 123 = 10 ^ 0 = 10
        a = a ^ b; //a = 10 ^ 123 ^ 10 = 0 ^ 123 = 123
        System.out.println("a=" + a);
        System.out.println("b=" + b);
    }

    /**
     * 2.异或运算 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到这一个数
     */
    public static void changePos1(int[] arr){
        int result = 0;
        for(int i =0; i< arr.length; i ++){
            result ^= arr[i];
        }
        System.out.println(result);
    }

    /**
     * 3.异或运算 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到这两个数
     */
    public static void changePos2(int[] arr){
        int eor = 0;
        for(int i = 0; i< arr.length; i++){
            eor ^= arr[i];
        }
        //eor == a ^ b  eor != 0 则必然有一个位置上是1
        int rightOne = eor & ((~eor) + 1);//找到eor最右位是1的数
        int onlyOne = 0;
        for(int i = 0; i< arr.length; i++){
            if((rightOne & arr[i]) == 0){
                //最右测为1的数 & arr元素 要么 = 0 要么 != 0 且 = 0 的会分到一侧，不=0的会分到另一侧
                //a , b 会分到两侧
                //异或后的操作结果要么是a 要么是b
                onlyOne ^= arr[i];
            }
        }
        System.out.println("第一个数是：" + onlyOne + ",第二个数是：" + (eor ^ onlyOne));
    }

    /**
     * 统计一个int类型数字二进制中包含几个1
     */
    public static void bitCount1(int num){
        int count = 0 ;
        //  num     =       0000 1011 1100
        //rightOne  =       0000 0000 0100
        //num ^ rightOne =  0000 1011 1000  把最后一位1抹掉
        while(num != 0){
            int rightOne = num & ((~num) + 1);
            num ^= rightOne;
            count++;
        }

        System.out.println(count);
    }

    /**
     * 单向链表结构
     */
    public static  class Node{
        private int value;
        private Node next;
        public Node(int value){
            this.value = value;
        }
    }

    /**
     * 双向链表结构
     */
    public static class DoubleNode{
        private int value;
        private DoubleNode next;
        private DoubleNode pre;
        public DoubleNode(int value){
            this.value = value;
        }
    }


    /**
     * 1.链表：单向链表反转
     */
    public static Node resvertLinkedList(Node head){
        Node pre = null;
        Node next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 2.链表：双向链表反转
     */
    public static DoubleNode resvertDoubleLinkedList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 3.链表：删除指定值的node节点
     */
    public static Node deleteNode(Node head,int num){
        if(head == null){
            return null;
        }
        while(head.value == num){
           head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while(cur != null){
            if(cur.value == num){
                pre.next = cur.next;
            }else{
                pre = head;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 1栈和队列：实现一个特殊的栈，在基本功能的基础上再实现返回栈中最小元素的功能，且pop,push,getMin时间复杂度都是O(1)
     */
    public class MyStack{
        private Stack<Integer> dataStack = new Stack<>();
        private Stack<Integer> minStack = new Stack<>();

        public int pop(){
            if(dataStack.isEmpty()){
                throw new RuntimeException("栈已空");
            }
            minStack.pop();
            return dataStack.pop();
        }
        public void push(int value){
            dataStack.push(value);
            if(minStack.isEmpty()){
                minStack.push(value);
            }else{
                int min = value > minStack.peek() ? minStack.peek() : value;
                minStack.push(min);
            }
        }
        public int getMin(){
            if(minStack.isEmpty()){
                throw new RuntimeException("栈已空");
            }
           return minStack.peek();
        }
    }

    /**
     * 2栈和队列：如何用栈结构实现队列结构
     *      使用两个栈实现：
     *      只有当pop栈为空时才可以导入
     *      一次性全部导入进去
     *      每个操作都要触发导入方法
     */
    public class StackReverseQueue{
        private Stack<Integer> push = new Stack<>();
        private Stack<Integer> pop = new Stack<>();
        public void push(int value){
            push.push(value);
            pushTopop();
        }
        public int pop(){
            if(pop.isEmpty()){
                throw new RuntimeException("队列为空");
            }
            pushTopop();
            return pop.pop();
        }

        public int peek(){
            if(push.isEmpty() && pop.isEmpty()){
                throw new RuntimeException("队列为空");
            }
            pushTopop();
            return pop.peek();
        }

        public void pushTopop(){
            if(pop.isEmpty()){
                while(!push.isEmpty()){
                    pop.push(push.pop());
                }
            }
        }
    }
    /**
     * 3栈和队列：如何用队列结构实现栈结构
     *      使用两个队列，将队列1中的size-1 放到队列2中 然后返回队列1中元素，队列1=队列2
     */
    public class QueueReverseStack{
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> help = new LinkedList<>();

        public void push(int value){
            queue.add(value);
        }
        public int pop(){
            if(queue.isEmpty()){
                throw new RuntimeException("队列为空");
            }
            for(int i = 0; i < queue.size()-1 ; i++){
                help.add(queue.peek());
            }
            return queue.poll();
        }

    }

    /**
     * 1递归：求数组arr[L...R]中的最大值，用递归实现。
     */
    public static int getMaxValue(int[] arr,int L,int R){
        if(L == R){//如果L到R上只有一个数时不用比较，直接返回最大值
           return arr[L];
        }
        int mid = L + ((R-L)>>1);
        int leftMax = getMaxValue(arr,L,mid);
        int rightMax = getMaxValue(arr,mid + 1,R);
        return Math.max(leftMax,rightMax);
    }


    //打印数组
    public static void printArr(int[] arr){
        for(int i =0; i< arr.length; i++){
            System.out.println(arr[i]);
        }
    }

    //交换位置
    public static int[] swap(int[] arr,int fromIndex,int toIndex){
        int temp = arr[fromIndex];
        arr[fromIndex] = arr[toIndex];
        arr[toIndex] = temp;
        return arr;
    }

    //交换位置 当fromIndex toIndex对应的数组位置对象不是同一个对象时
    public static int[] swapNew(int[] arr,int fromIndex,int toIndex){
        arr[fromIndex] = arr[fromIndex] ^ arr[toIndex];
        arr[toIndex] = arr[fromIndex] ^ arr[toIndex];
        arr[fromIndex] = arr[fromIndex] ^ arr[toIndex];
        return arr;
    }
}
