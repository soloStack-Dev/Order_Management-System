void main(){

    //SIMPLE THREAD CREATION (LAMBDA + Runnable)
    Runnable r = () -> {
        IO.println("Running in: "+ Thread.currentThread().getName());
    };

    var thread = new Thread(r, "worker-1");
    thread.start();

    //JOINING THREADS (WAIT FOR COMPLETION)
    Runnable r1 = () -> {
        for(var i = 1;i <= 5;i++){
            IO.println("value "+i);
        }
    };

    var t = new Thread(r1,"count-Thread");
    t.start();

    try{
        t.join();
    }catch(InterruptedException e){
        Thread.currentThread().interrupt();
    }
    IO.println("Thread process finished!");

    //DAEMON THREAD (BACKGROUND TASK)
    var demon = new Thread(() -> IO.println("Demon Thread was running!"));
    demon.setDaemon(true);
    demon.start();

    //SYNCHRONIZATION (PREVENT RACECONDITION)
    var counter = new Counter();

    Runnable incTask = () -> {
        for(var i = 0; i < 1000;i++){
            counter.Increament();
        }
    };

    var count1 = new Thread(incTask);
    var count2 = new Thread(incTask);

    count1.start();
    count2.start();

    try{
        count1.join();
        count2.join();
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }

    IO.println("Final count: "+counter.value());

    //EXECUTOR SERVICE (REPLACEMENT FOR RAW THREADS)
    var executor = Executors.newFixedThreadPool(3);
    try{
        for(var i = 1;i < 5;i++){
            var taskid = i;

            executor.submit(() -> {
                IO.println("Task "+taskid+ "running in"+Thread.currentThread().getName());
            });
        }
    }catch (Exception e){
        IO.println("Error executing tasks: "+e.getMessage());
    }finally{
        executor.shutdown();
    }

    try{
        if(!executor.awaitTermination(10,TimeUnit.SECONDS)){
            executor.shutdownNow();
        }
    }catch(InterruptedException e){
        executor.shutdownNow();
        Thread.currentThread().interrupt();
    }
    IO.println("All task completed");
}

class Counter{
    private int count = 0;

     synchronized void Increament() {
        count++;
    }
    int value(){
         return count;
    }
}