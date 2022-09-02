import java.util.concurrent.*;

public class MultithreadedMaxFinder {

  public static int max(int[] data) throws InterruptedException, ExecutionException {

    if (data.length == 1) {
      return data[0];
    } else if (data.length == 0) {
      throw new IllegalArgumentException();
    }
    
    // split the job into 2 pieces
    FindMaxTask task1 = new FindMaxTask(data, 0, data.length/2);
    FindMaxTask task2 = new FindMaxTask(data, data.length/2, data.length);
    
    // spawn 2 threads
    ExecutorService service = Executors.newFixedThreadPool(2);

    Future<Integer> future1 = service.submit(task1);
    Future<Integer> future2 = service.submit(task2);
    service.shutdown();
    return Math.max(future1.get(), future2.get());

  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    int[] data = new int[]{4, 5, 6, 7, 8,9,10,11,12,13,14,15,16,17,18};
    MultithreadedMaxFinder num = new MultithreadedMaxFinder();
    System.out.println(num.max(data));
  }
}