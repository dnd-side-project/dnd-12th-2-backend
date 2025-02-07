package ac.dnd.dodal.flyway;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
@ActiveProfiles("test")
public class FlywayTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @DisplayName("Flyway DB Migration Test")
    @Test
    public void flywayMigrationTest() {
        // get information from information_schema
        List<String> tableSchemas = jdbcTemplate.queryForList("SELECT DISTINCT table_schema FROM information_schema.tables", String.class);
        System.out.println("Table Schemas: " + tableSchemas);

        List<String> tableNames = jdbcTemplate.queryForList("SELECT table_name FROM information_schema.tables WHERE table_schema = 'PUBLIC'", String.class);
        System.out.println("Table Names: " + tableNames);

        // check flyway applied
        List<String> flywayVersionColumns = jdbcTemplate.queryForList("SELECT column_name FROM information_schema.columns WHERE table_name = 'dodal_flyway_schema_version'", String.class);
        List<Map<String, Object>> flywayVersion =
                jdbcTemplate.queryForList("SELECT * FROM \"dodal_flyway_schema_version\"");

        System.out.println("==dodal_flyway_schema_version==");
        System.out.println(flywayVersionColumns);
        flywayVersion.forEach(version -> {
            System.out.println(version.values());
        });
        assertThat(flywayVersion.size()).isGreaterThan(0);

        // check data count of specific table
        tableNames.forEach(tableName -> {
            int count = jdbcTemplate
                    .queryForObject("SELECT COUNT(*) FROM \"" 
                    + tableName + "\"", Integer.class);
            System.out.println(tableName + " Record Count: " + count);
        });
    }
}
