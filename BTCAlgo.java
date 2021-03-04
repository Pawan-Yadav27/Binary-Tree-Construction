import java.util.ArrayList;
import java.util.Scanner;


class Block{
	int key ,count ;
	public static int i=0;

	Block(int key) {
		this.key = key;
		this.count=++i;
	}
}

class Node {
    int key, height,count; 
    Node left, right; 
  
    Node(int d,int c) { 
        key = d; 
        count=c;
        height = 1; 
    } 
}
class BSTNode{
	int key,count;
	BSTNode left, right;

	BSTNode(int key,int count) {
		this.key = key;
		this.count=count;
	}
}
public class BTCAlgo {
	

	
	 public static void main(String[] args) { 
		 
	    Scanner scn = new Scanner(System.in);
	    System.out.println("insert inorder sequence: ");
	    String inorder = scn.nextLine();
	    System.out.println("insert preorder sequence: ");
	    String preorder = scn.nextLine();
	    Scanner tokenizer = new Scanner(inorder);
	    tokenizer.useDelimiter(" ");
	    ArrayList<Block> arr = new ArrayList<Block>();
	    while(tokenizer.hasNext())
	    {
	    	
	    	//node.key=Integer.parseInt(tokenizer.next());
	        arr.add(new Block(Integer.valueOf(tokenizer.next())));
	     
	    }
	    AVLTree tree = new AVLTree(); 
	    for (int i = 0; i <arr.size() ; i++) 
        { 
          
	        tree.root = tree.insert(tree.root,arr.get(i).key,arr.get(i).count); 

       
        }    
	    
	    
	    System.out.println("Preorder traversal of inbetween constructed tree is : "); 
	    tree.preOrder(tree.root); 
	    

	    ArrayList<BSTNode> prearr=new  ArrayList<BSTNode>();
	    int index=0,key;
	    Scanner tokenizer2 = new Scanner(preorder);
	    BinaryTree Btree = new BinaryTree();
	    
	    while(tokenizer2.hasNext())
	    {
		     key=Integer.parseInt(tokenizer2.next());	    	
	    	 index=tree.searchkey(tree.root,key);
	    	 System.out.println("index"+index);
	    	 System.out.println("key"+key);
	        prearr.add(new BSTNode(key,index));
	     	      
	    }

	    BSTNode root = Btree.constructTree(prearr, prearr.size());   
	    System.out.println("Postorder traversal of constructed tree is : "); 
	    Btree.postOrder(root);
	 }
}
	 
	 class AVLTree { 
		  
		    Node root; 
		    
		  
		    // A utility function to get the height of the tree 
		    int height(Node N) { 
		        if (N == null) 
		            return 0; 
		  
		        return N.height; 
		    } 
		  
		    // A utility function to get maximum of two integers 
		    int max(int a, int b) { 
		        return (a > b) ? a : b; 
		    } 
		  
		    // A utility function to right rotate subtree rooted with y 
		    // See the diagram given above. 
		    Node rightRotate(Node y) { 
		        Node x = y.left; 
		        Node T2 = x.right; 
		  
		        // Perform rotation 
		        x.right = y; 
		        y.left = T2; 
		  
		        // Update heights 
		        y.height = max(height(y.left), height(y.right)) + 1; 
		        x.height = max(height(x.left), height(x.right)) + 1; 
		  
		        // Return new root 
		        return x; 
		    } 
		  
		    // A utility function to left rotate subtree rooted with x 
		    // See the diagram given above. 
		    Node leftRotate(Node x) { 
		        Node y = x.right; 
		        Node T2 = y.left; 
		  
		        // Perform rotation 
		        y.left = x; 
		        x.right = T2; 
		  
		        //  Update heights 
		        x.height = max(height(x.left), height(x.right)) + 1; 
		        y.height = max(height(y.left), height(y.right)) + 1; 
		  
		        // Return new root 
		        return y; 
		    } 
		  
		    // Get Balance factor of node N 
		    int getBalance(Node N) { 
		        if (N == null) 
		            return 0; 
		  
		        return height(N.left) - height(N.right); 
		    } 
		    
		  
		    Node insert(Node node, int key,int count) { 
		  
		        /* 1.  Perform the normal BST insertion */
		        if (node == null) 
		            return (new Node(key,count)); 
		  
		        if (key < node.key) 
		            node.left = insert(node.left, key,count); 
		        else if (key > node.key) 
		            node.right = insert(node.right, key,count); 
		        else // Duplicate keys not allowed 
		            return node; 
		  
		        /* 2. Update height of this ancestor node */
		        node.height = 1 + max(height(node.left), 
		                              height(node.right)); 
		  
		        /* 3. Get the balance factor of this ancestor 
		              node to check whether this node became 
		              unbalanced */
		        int balance = getBalance(node); 
		  
		        // If this node becomes unbalanced, then there 
		        // are 4 cases Left Left Case 
		        if (balance > 1 && key < node.left.key) 
		            return rightRotate(node); 
		  
		        // Right Right Case 
		        if (balance < -1 && key > node.right.key) 
		            return leftRotate(node); 
		  
		        // Left Right Case 
		        if (balance > 1 && key > node.left.key) { 
		            node.left = leftRotate(node.left); 
		            return rightRotate(node); 
		        } 
		  
		        // Right Left Case 
		        if (balance < -1 && key < node.right.key) { 
		            node.right = rightRotate(node.right); 
		            return leftRotate(node); 
		        } 
		  
		        /* return the (unchanged) node pointer */
		        return node; 
		    } 
		    void preOrder(Node node) { 
		        if (node != null) { 
		            System.out.print(node.key + " "); 
		            preOrder(node.left); 
		            preOrder(node.right); 
		        } 
		    } 
		    int searchkey(Node node,int key)
		    {
		    	if (key == node.key) 
		            return node.count;
		  
		        if (key < node.key) 
		             return(searchkey(node.left, key)); 
		        else if (key > node.key) 
		            return(searchkey(node.right, key));
		        
				return 0; 
		      		  
		    }
}
	 
	 class Index { 
		  
		    int index = 0; 
		} 
		  
	class BinaryTree { 
		  
		    Index index = new Index(); 
		      
		    // A recursive function to construct Full from pre[]. preIndex is used 
		    // to keep track of index in pre[]. 
		    BSTNode constructTreeUtil(ArrayList<BSTNode> prearr, Index preIndex, 
		            int low, int high, int size) { 
		          
		        // Base case 
		        if (preIndex.index >= size || low > high) { 
		            return null; 
		        } 
		  
		        // The first node in preorder traversal is root. So take the node at 
		        // preIndex from pre[] and make it root, and increment preIndex 
		        BSTNode root = new BSTNode(prearr.get(preIndex.index).key,prearr.get(preIndex.index).count); 
		        preIndex.index = preIndex.index + 1; 
		  
		        // If the current subarry has only one element, no need to recur 
		        if (low == high) { 
		            return root; 
		        } 
		  
		        // Search for the first element greater than root 
		        int i; 
		        for (i = low; i <= high; ++i) { 
		            if (prearr.get(i).count > root.count) { 
		                break; 
		            } 
		        } 
		  
		        // Use the index of element found in preorder to divide  
		        // preorder array in two parts. Left subtree and right subtree 
		        root.left = constructTreeUtil(prearr, preIndex, preIndex.index,  
		                                      i - 1, size); 
		        root.right = constructTreeUtil(prearr, preIndex, i, high, size); 
		  
		        return root; 
		    } 
		  
		    // The main function to construct BST from given preorder traversal. 
		    // This function mainly uses constructTreeUtil() 
		    BSTNode constructTree(ArrayList<BSTNode> prearr, int size) { 
		        return constructTreeUtil(prearr, index, 0, size - 1, size); 
		    } 
		  
		    // A utility function to print postorder traversal of a Binary Tree 
		    void postOrder(BSTNode node) { 
		        if (node == null) { 
		            return; 
		        } 
		        postOrder(node.left); 
		        postOrder(node.right); 
		        System.out.print(node.key + " "); 
		    }
		}
	