package spring.summer.socialnetwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.summer.socialnetwork.migrations.Migration;

@Component
public class MyCommandLineRunner  implements CommandLineRunner {

    private final Migration migration;
    @Autowired
    public MyCommandLineRunner(Migration migration) {
        this.migration = migration;
    }

    @Override
    public void run(String... args) throws Exception {
        migration.initialize_db();
    }
}
