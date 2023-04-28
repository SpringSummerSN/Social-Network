package spring.summer.socialnetwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.summer.socialnetwork.migrations.Migration;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private final Migration migration;
    private final Mockup mockup;

    @Autowired
    public MyCommandLineRunner(Migration migration, Mockup mockup) {
        this.migration = migration;
        this.mockup = mockup;
    }

    @Override
    public void run(String... args) throws Exception {
//        migration.initialize_db();
//        mockup.mockData();
    }
}
