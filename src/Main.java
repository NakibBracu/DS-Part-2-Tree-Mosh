public class Main {
    public static void main(String[] args) {
     Tree tree = new Tree();
    tree.insert(7);
    tree.insert(4);
    tree.insert(9);
    tree.insert(1);
    tree.insert(6);
    tree.insert(8);
    tree.insert(10);
//        System.out.println(tree.find(22));
//        System.out.println(factorial(4));
//        System.out.println(factorialUsingRecursion(4));
//        System.out.println("Preorder traversal");
//        tree.traversPreOrder();
//        System.out.println("InOrder traversal");
//        tree.traversInOrder();
//        System.out.println("PostOrder traversal");
//        tree.traversPostOrder();
       // System.out.println(tree.height());
    //    System.out.println(tree.min());
//        System.out.println(tree.min2());//if the tree is BST , then only this method
//        System.out.println(tree.max());
        Tree tree2 = new Tree();
        tree2.insert(7);
        tree2.insert(4);
        tree2.insert(9);
        tree2.insert(1);
        tree2.insert(6);
        tree2.insert(8);
        tree2.insert(10);
       // System.out.println(tree.equals(null));
        //tree equality check is an exercise of pre-order traversal
        // cause visiting order = root,left,right
      //  System.out.println(tree2.equals(tree));
//        System.out.println("before swapping left and right child of root, tree will be "+tree.istreeBST());
//        tree.swapRoot();
//        System.out.println("after swapping left and right child of root, tree will be "+tree.istreeBST());
//       var list = tree.getNodesAtDistance(0);
//       for(var item:list)
//           System.out.println(item);
       tree.traverseLevelOrder();
    }
    public static int factorial(int n){
        // 4! = 4 x 3 x 2 x 1
        // 3! = 3 x 2 x 1
        var factorial = 1;
        for (int i=n ; i>1 ;i--)//using traditional for loop
            factorial*=i;
        return factorial;
    }
    public static int factorialUsingRecursion(int n){
        // 4! = 4 x 3!
        // 3! = 3 x 2 x 1
        // 2! = 2 x 1
        // n! = n x (n-1)!
        // 0! = 1 [this will be the case where recursion for factorial should be stopped
        if(n==0) return 1;//base case to stop recursion otherwise it will give stack overflow
        return n*factorialUsingRecursion(n-1);
    }
}
