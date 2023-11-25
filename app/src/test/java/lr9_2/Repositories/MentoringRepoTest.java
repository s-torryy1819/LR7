package lr9_2.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lr9_2.DTO.Employee;
import lr9_2.DTO.Mentoring;

@SpringBootTest
public class MentoringRepoTest {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private MentoringRepo mentoringRepo;

    @Test
    public void FindMentorById_Position_CalledGetPositionIdToCheckMentoringData() {
        // Arrange
        Employee employee = new Employee("Anna", "Willington", null, null, null, null, null, null);
        Mentoring mentor = new Mentoring("Automotive Engineer", 12, 40, employee, null);
        employee.setMentor(mentor);

        employeeRepo.save(employee);
        mentoringRepo.save(mentor);

        Integer expectedMentorId = employee.getMentor().getMentoringId();

        // Act

        Integer actualMentorId = mentoringRepo.findById(mentor.getMentoringId()).get().getMentoringId();

        employeeRepo.delete(employee);
        mentoringRepo.delete(mentor);

        // Assert
        assertEquals(expectedMentorId, actualMentorId);
    }
}