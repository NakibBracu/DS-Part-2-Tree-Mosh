//BST(Binary Search Tree)
//left subtree<root<right subtree

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private class Node{
        private int value;
        private Node leftchild;
        private Node rightchild;
        public Node(int value){
            this.value=value;
        }
        @Override
        public String toString(){
            return "Node="+value;
        }
    }
    private Node root;
    //O(log(n))
    public void insert(int value){
        var node = new Node(value);//creating the node with given value
       if(root==null){//if root isn't defined yet then make the node as root
           root = node;
           return;//we need to immediately return
       }
       //if root node is defined already then
       var current = root;
       while(true){//insert nodes by left<root<right (by comparing values)
       if(value< current.value){
           if(current.leftchild==null){//if leftchild is null then insert(set) leftchild here(current is the parent of the leftchild)
               current.leftchild=node;
               break;
           }
           current = current.leftchild;
       }
       else{
           if(current.rightchild==null){//same logic as rightchild
               current.rightchild=node;
               break;
           }
           current = current.rightchild;
       }
       }

    }
    public boolean find(int value){
        var current = root;
        while(current!=null){
            if(value<current.value)
                current=current.leftchild;
            else if (value> current.value)
                current=current.rightchild;
            else
                return true;
        }
        return false;
    }
    //override
    public void traversPreOrder(){
        traversPreOrder(root);
    }
    private void traversPreOrder(Node root){
        if(root==null)
            return;
        System.out.println(root.value);
       traversPreOrder(root.leftchild);
       traversPreOrder(root.rightchild);
    }
    public void traversInOrder(){
        traversInOrder(root);
    }
    private void traversInOrder(Node root){
        if(root==null)
            return;
        traversInOrder(root.leftchild);
        System.out.println(root.value);
        traversInOrder(root.rightchild);
    }
    public void traversPostOrder(){
        traversPostOrder(root);
    }
    private void traversPostOrder(Node root){
        if(root==null)
            return;
        traversPostOrder(root.leftchild);
        traversPostOrder(root.rightchild);
        System.out.println(root.value);
    }
    public int height(){
       return height(root);
    }
    private int height(Node root){
        if(root==null) return -1;
        if(isLeaf(root))
            return 0;
        return 1+Math.max(height(root.leftchild),height(root.rightchild));
    }
    private boolean isLeaf(Node node){
        return node.leftchild == null && node.rightchild==null;
    }
    public int min2(){
        //O(log n) cause it will works with half of the elements
        //if the tree is BST , then only we can apply this method to find minimum
        //cause in leftmost node carry the minimum value in a BST
        root_null_throw_exception();
        var current = root;
        var last=current;
        while(current!=null){
            last = current;
            current = current.leftchild;
        }
        return last.value;
    }
    public int max2(){
        //O(log n) cause it will works with half of the elements
        //if the tree is BST , then only we can apply this method to find maximum
        //cause in rightmost node carry the maximum value in a BST
        root_null_throw_exception();
        var current = root;
        var last=current;
        while(current!=null){
            last = current;
            current = current.rightchild;
        }
        return last.value;
    }
    public int min(){
        root_null_throw_exception();
        return min(root);
    }
    private int min(Node root){ // 0(n) cause it is like post-order traversal, we have to iterate through every nodes
        //this works for Binary Tree+ BST(Binary Search Tree)
        if(isLeaf(root)) return root.value;
      var left = min(root.leftchild);
      var right = min(root.rightchild);
      return Math.min(Math.min(left,right), root.value);
    }
    public int max(){
        root_null_throw_exception();
        return max(root);
    }
    private int max(Node root){//this works for Binary Tree+ BST(Binary Search Tree)
        if(isLeaf(root)) return root.value;
        var left = max(root.leftchild);
        var right = max(root.rightchild);
        return Math.max(Math.max(left,right), root.value);
    }
    private void root_null_throw_exception(){
        if(root==null)
            throw new IllegalStateException();
    }
    public boolean equals(Tree other){
        if(other == null) return false;
    return equals(root,other.root);
    }
    private boolean equals(Node first,Node second){
        //1st case, if both trees are null then they are equal
    if(first== null && second==null) return true;
    //2nd case, if both nodes are not null , then check root and left+right subtrees
    if(first!=null && second!=null)
        return first.value == second.value &&
                equals(first.leftchild,second.leftchild) &&
                equals(first.rightchild, second.rightchild);
    return false;//if one of the tree root is null but other is not(3rd case)
    }
    public void swapRoot(){
        var temp = root.leftchild;
        root.leftchild = root.rightchild;
        root.rightchild = temp;
    }
    public boolean istreeBST(){
        return istreeBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    private boolean istreeBST(Node root,int min,int max){
        //if tree has nothing then nothing to check
        if(root==null)
            return true;
        //We know in BST left(min) <root< right(max) , if break this then false
        if(root.value>max || root.value<min)
            return false;
        //if above two of the cases aren't then we have to check for every node and left and right child value
        //if we have a root of 10 , then its left child  must be in range of -infinity to 9(root-1)
        // & right child must be in range of root+1 to infinity, that mean for root 10
        // right child must be at least 11.
        return istreeBST(root.leftchild,min, root.value-1)
                && istreeBST(root.rightchild, root.value+1,max);
    }
    public ArrayList<Integer> getNodesAtDistance(int distance){
        var list = new ArrayList<Integer>();
        getNodesAtDistance(root,distance,list);
        return list;
    }
    private void getNodesAtDistance(Node root,int distance,ArrayList<Integer> list){
    if(root==null) return;
    if(distance==0){
        list.add(root.value);
        return;
    }
    //we simply decrease the distance by 1 everytime for left and right child
    // when we get the distance 0 that means we reached the destination, just print immediately.
    getNodesAtDistance(root.leftchild,distance-1,list);
    getNodesAtDistance(root.rightchild,distance-1,list);
    }
    public void traverseLevelOrder(){
        for (int i=0;i<=height();i++){
            for(var item :getNodesAtDistance(i))
                System.out.println(item);
        }
    }
    public int size() {
        return size(root);
    }
    private int size(Node root) {
        if (root == null)
            return 0;

        if (isLeaf(root))
            return 1;

        return 1 + size(root.leftchild) + size(root.rightchild);
    }
    public int countLeaves() {
        return countLeaves(root);
    }
    private int countLeaves(Node root) {
        if (root == null)
            return 0;

        if (isLeaf(root))
            return 1;

        return countLeaves(root.leftchild) + countLeaves(root.rightchild);
    }
    public int max3() {
        if (root == null)
            throw new IllegalStateException();
        return max_3(root);
    }
    private int max_3(Node root) {
        if (root.rightchild == null)
            return root.value;
        return max(root.rightchild);
    }
    public boolean contains(int value) {
        return contains(root, value);
    }
    private boolean contains(Node root, int value) {
        if (root == null)
            return false;

        if (root.value == value)
            return true;

        return contains(root.leftchild, value) || contains(root.rightchild, value);
    }
    public boolean areSibling(int first, int second) {
        return areSibling(root, first, second);
    }
    private boolean areSibling(Node root, int first, int second) {
        if (root == null)
            return false;

        var areSibling = false;
        if (root.leftchild != null && root.rightchild != null) {
            areSibling = (root.leftchild.value == first && root.rightchild.value == second) ||
                    (root.rightchild.value == first && root.leftchild.value == second);
        }

        return areSibling ||
                areSibling(root.leftchild, first, second) ||
                areSibling(root.rightchild, first, second);
    }
    public List<Integer> getAncestors(int value) {
        var list = new ArrayList<Integer>();
        getAncestors(root, value, list);
        return list;
    }
    private boolean getAncestors(Node root, int value, List<Integer> list) {
        // We should traverse the tree until we find the target value. If
        // find the target value, we return true without adding the current node
        // to the list; otherwise, if we ask for ancestors of 5, 5 will be also
        // added to the list.
        if (root == null)
            return false;

        if (root.value == value)
            return true;

        // If we find the target value in the left or right sub-trees, that means
        // the current node (root) is one of the ancestors. So we add it to the list.
        if (getAncestors(root.leftchild, value, list) ||
                getAncestors(root.rightchild, value, list)) {
            list.add(root.value);
            return true;
        }

        return false;
    }
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node root) {
        if (root == null)
            return true;

        var balanceFactor = height(root.leftchild) - height(root.rightchild);

        return Math.abs(balanceFactor) <= 1 &&
                isBalanced(root.leftchild) &&
                isBalanced(root.rightchild);
    }

    public boolean isPerfect() {
        return size() == (Math.pow(2, height() + 1) - 1);
    }
}
