import java.util.concurrent.CountDownLatch;

/*
 * CountDownLatch ：闭锁，在完成某些运算时，只有其他所有线程的运算全部完成，当前运算才继续执行
 * 闭锁的 int count 应当等于 执行任务的线程数量
 */
public class TestCountDownLatch {

	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(5);
		LatchDemo ld = new LatchDemo(latch);

		long start = System.currentTimeMillis();

		for (int i = 0; i < 5; i++) {
			new Thread(ld).start();
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
		}

		long end = System.currentTimeMillis();

		System.out.println("耗费时间为：" + (end - start));
	}

}

class LatchDemo implements Runnable {

	private CountDownLatch latch;

	public LatchDemo(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {

		try {
			for (int i = 0; i < 10; i++) {
					System.out.println(i);
			}
		} finally {
			latch.countDown();
		}

	}

}