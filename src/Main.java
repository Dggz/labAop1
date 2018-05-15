import db.DbContract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        //create subject
        Job job = new Job();
        Job job2 = new Job();

        //create observers
        Observer worker1 = new Worker("Worker1");
        Observer worker2 = new Worker("Worker2");
        Observer worker3 = new Worker("Worker3");

        //register observers to the subject
        job.register(worker1);
        job.register(worker2);
        job.register(worker3);

        job2.register(worker2);
        job2.register(worker3);

        //attach observer to subject
        worker1.setSubject(job);
        worker2.setSubject(job);
        worker3.setSubject(job);

        worker2.setSubject(job2);
        worker3.setSubject(job2);

        //check if any update is available
        worker1.update();

        //now send message to subject
        job.postMessage("New Job for y'all");

        job2.postMessage("Need courier!");

        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(
                    DbContract.HOST + DbContract.DB_NAME,
                    DbContract.USERNAME,
                    DbContract.PASSWORD);

            System.out.println("DB connected");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
