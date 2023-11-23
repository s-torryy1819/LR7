package com.example.demo.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.DTO.Employee;
import com.example.demo.DTO.MonthlyExpectedWorkingHours;
import com.example.demo.DTO.Schedule;
import com.example.demo.DTO.WorkedHours;

@SpringBootTest
public class WorkedHoursRepoTest {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ExpectedHoursRepo expectedHoursRepo;
    @Autowired
    private ScheduleRepo scheduleRepo;
    @Autowired
    private WorkedHoursRepo workedHoursRepo;

    @Test
    public void GetHoursId_WorkedHours_CalledGetWorkedHoursIdToCheckSavingObject() {
        // Arrange
        Employee employee = new Employee("Anna", "Willington", null, null, null, null, null, null);
        MonthlyExpectedWorkingHours expectedHours = new MonthlyExpectedWorkingHours("Jan", 160, null);
        WorkedHours workedHours = new WorkedHours(160, null);
        Schedule schedule = new Schedule(expectedHours, workedHours, employee);

        workedHours.setSchedule(schedule);
        expectedHours.setSchedule(schedule);

        Integer expectedWorkedHoursId = workedHours.getWorkedHoursId();
        Integer actualWorkedHoursId = schedule.getActualWorkingHours().getWorkedHoursId();

        // Act
        employeeRepo.save(employee);
        scheduleRepo.save(schedule);
        expectedHoursRepo.save(expectedHours);
        workedHoursRepo.save(workedHours);

        // Assert
        assertEquals(expectedWorkedHoursId, actualWorkedHoursId);
    }
}