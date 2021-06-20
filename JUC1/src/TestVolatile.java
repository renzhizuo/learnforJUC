/**
 * 一、volatile 关键字：当多个线程进行操作共享数据时，可以保证内存中的数据可见。
 * 					  相较于 synchronized 是一种较为轻量级的同步策略。
 * 
 * 注意：
 * 1. volatile 不具备“互斥性”
 * 2. volatile 不能保证变量的“原子性”
 */
public class TestVolatile {
	
	public static void main(String[] args) {
		//共享的,可执行task
		ThreadDemo td = new ThreadDemo();
		//子线程-执行task
		new Thread(td).start();

		//主线程-依赖共享task的flag
		while(true){
			if(td.isFlag()){
				System.out.println("!!!!!!!!!!!!!!!");
				break;
			}
		}
		
	}

}

class ThreadDemo implements Runnable {

//	private boolean flag = false; //主线程while(true)不能感知到flag已被修改
	private volatile boolean flag = false;//每次get从主内存读取，可以获取到最新的值

	@Override
	public void run() {
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}

		flag = true;

		System.out.println("flag=" + isFlag());

	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}