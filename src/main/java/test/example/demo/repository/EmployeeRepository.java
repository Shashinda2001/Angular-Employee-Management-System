package test.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import test.example.demo.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String> {
}
