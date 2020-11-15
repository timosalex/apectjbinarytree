
public class ReadWriteLock {
	
	static boolean insert,delete=false;
	static int lookups=0;

	public  synchronized  void enterRead()  {
		System.out.println("I am doing a lookup");
		while(insert || delete) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lookups++;
		
	}
	
	public synchronized void exitRead() {
		System.out.println("I am exiting lookup");
		lookups--;
		notifyAll();
	}

	
	public synchronized void enterInsert()  {
		System.out.println("I am doing an insert");
		while(lookups>0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		insert=true;
	}
	
	public synchronized void exitInsert() {
		System.out.println("I am exiting the insert");
		insert=false;
		notifyAll();
	}
	
	public synchronized void enterDelete()  {
		System.out.println("I am doing a delete");
		while(lookups>0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		delete=true;
	}
	
	public synchronized void exitDelete() {
		System.out.println("I am exiting the delete");
		delete=false;
		notifyAll();
	}


}
