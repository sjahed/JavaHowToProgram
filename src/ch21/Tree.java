package ch21;

import java.security.SecureRandom;

public class Tree<T extends Comparable<T>> {
	
	public static void main(String[] args)
	{
	   Tree<Integer> tree = new Tree<Integer>();
	   SecureRandom randomNumber = new SecureRandom();
	   System.out.println("Inserting the following values:");
	   // insert 10 random integers from 0-99 in tree
	   for (int i = 1; i <= 10; i++)
	   {
	      int value = randomNumber.nextInt(100);
	      System.out.printf("%d ", value);
	      tree.insertNode(value);
	   }
	   System.out.printf("%n%nPreorder traversal%n");
	   tree.preOrderTraversal();
	   System.out.printf("%n%nInorder traversal%n");
	   tree.inOrderTraversal();
	   System.out.printf("%n%nPostorder traversal%n");
	   tree.postOrderTraversal();
	   System.out.println();
	}

	private TreeNode<T> root;
	
	public Tree(){
		root = null;
	}
	public void insertNode(T insertValue){
		if(root == null){
			root = new TreeNode<T>(insertValue);
			//System.out.printf("creating new node!");
		}
		else
			root.insert(insertValue);
	}
	
	public void preOrderTraversal(){
		preOrderHelper(root);
	}
	private void preOrderHelper(TreeNode<T> node) {
		if(node == null)
			return;
		System.out.printf("%s ",node.nodeData);
		preOrderHelper(node.leftNode);
		preOrderHelper(node.rightNode);
		
	}
	
	public void inOrderTraversal(){
		inOrderHelper(root);
	}
	private void inOrderHelper(TreeNode<T> node){
		if(node == null)
			return;
		inOrderHelper(node.leftNode);
		System.out.printf("%s ", node.nodeData);
		inOrderHelper(node.rightNode);
	}
	
	public void postOrderTraversal(){
		postOrderHelper(root);
	}
	private void postOrderHelper(TreeNode<T> node){
		if(node == null)
			return;
		postOrderHelper(node.leftNode);
		postOrderHelper(node.rightNode);
		System.out.printf("%s ", node.nodeData);
	}
	
}
class TreeNode<T extends Comparable<T>>{

	TreeNode<T> leftNode;
	TreeNode<T> rightNode;
	T nodeData;
	
	public TreeNode(T nodeData){
		this.nodeData = nodeData;
		leftNode = rightNode = null;
	}
	
	public void insert(T insertValue){
		
		if(insertValue.compareTo(nodeData) < 0){
			
			if(leftNode == null){
				leftNode = new TreeNode<T>(insertValue);
			}else{
				leftNode.insert(insertValue);
			}
		}else if(insertValue.compareTo(insertValue) > 0){
			
			if(rightNode ==null){
				rightNode = new TreeNode<T>(insertValue);
			}else
				rightNode.insert(insertValue);
		}
	}
}