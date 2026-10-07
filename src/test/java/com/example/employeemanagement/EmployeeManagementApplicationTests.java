package com.example.employeemanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// This is a basic "smoke test." It simply checks that the entire Spring
// application context can start up without errors (all beans wire
// together correctly: Controller -> Service -> Repository -> Database).
// NOTE: This test requires a working MySQL connection since it loads
// the full application context.
@SpringBootTest
class EmployeeManagementApplicationTests {

    @Test
    void contextLoads() {
        // Intentionally empty.
        // If the application context fails to start, this test fails
        // automatically -- that's the whole point of this test.
    }

}
