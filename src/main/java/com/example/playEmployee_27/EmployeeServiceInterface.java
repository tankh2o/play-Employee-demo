package com.example.playEmployee_27;

import java.util.List;

public interface EmployeeServiceInterface {
    //### Шаг 5
    //    //Реализовать в сервисе три метода, которые принимают в качестве параметров firstName и lastName:
    //    //1. Добавить сотрудника.
    //    //2. Удалить сотрудника. remove(int index)
    //    //3. Найти сотрудника. contains(element) ИЛИ

    String getEmployees(Integer number);

    String findOutNumberEmployees();

    Object addEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    Object removeEmployee(String firstName, String lastName);

    Object containsEmployee(String firstName, String lastName);
}
