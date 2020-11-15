public aspect AspectControlFlow{
	
	private ReadWriteLock rwl=new ReadWriteLock();
	
	pointcut lookUp(): call(boolean BinaryTree.lookup(BinaryTree.TreeNode, int));
	pointcut insert(): call(BinaryTree.TreeNode BinaryTree.insert(BinaryTree.TreeNode, int));
	pointcut delete(): call(BinaryTree.TreeNode BinaryTree.delete(BinaryTree.TreeNode, int));

	
		before(): lookUp(){
		rwl.enterRead();
			}
		
		after(): lookUp(){
			rwl.exitRead();
		}
		
		before(): insert(){
			rwl.enterInsert();
		}
		
		after(): insert(){
			rwl.exitInsert();
		}
		
		before(): delete(){
			rwl.enterDelete();
		}
		
		after(): delete(){
			rwl.exitDelete();
		}
}