import java.util.logging.Logger;

/**
 * Created by Dggz on 3/14/2018.
 */
public class Worker implements Observer {

    private String name;
    private Subject job;
    private static final Logger LOGGER = Logger.getLogger(Worker.class.getName());

    Worker(String worker_name){
        this.name = worker_name;
    }
    @Override
    public void update() {
        String msg = (String) job.getUpdate(this);
        if(msg == null){
            LOGGER.info("No jobs.");
        }
        else
            LOGGER.info(name +  ": Aware of job:" + msg);
    }

    @Override
    public void setSubject(Subject sub) {
        this.job = sub;
    }

}
