package com.example.playEmployee_27;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface {
    //    //### Шаг 6
    //    //Написать собственное непроверяемое исключение EmployeeNotFoundException,
    //    // которое выбрасывается, если сотрудник не найден.

    //### Шаг 7
    //Написать собственное непроверяемое исключение EmployeeStorageIsFullException,
    // которое выбрасывается, если превышен лимит количества сотрудников в фирме.

    //### Шаг 8
    //Написать собственное непроверяемое исключение EmployeeAlreadyAddedException,
    // которое выбрасывается, когда нового сотрудника пытаются добавить в коллекцию, а в коллекции уже есть такой сотрудник.  **

    //### Шаг 9
    //Добавить в методы из шага 5 новые исключения.
    /*1. В метод с добавлением сотрудника нужно добавить выброс исключения
    из шага 7 в ситуации, когда коллекция переполнена.
      2. В метод с добавлением сотрудника нужно добавить выброс исключения
      из шага 8 в ситуации, когда добавляемый сотрудник уже имеется в коллекции.
      3. В метод с удалением сотрудника нужно добавить выброс исключения
      из шага 6 в ситуации, когда удаляемый сотрудник не найден.
      4. В метод с поиском сотрудника нужно добавить выброс исключения
      из шага 6 в ситуации, когда сотрудник не найден.*/
//### Шаг 10
//Реализовать EmployeeController, который имеет поле EmployeeService.
//Объявить конструктор с этим параметром, чтобы заинджектить EmployeeService в EmployeeController.
//### Шаг 11
//Объявить в контроллере 3 метода:
//1. По адресу /employee/add?firstName=Ivan&lastName=Ivanov должен вернуться объект Employee в формате JSON,
// т.е. { "firstName": "Ivan", "lastName": "Ivanov" }, или исключение ArrayIsFull в случае переполнения коллекции или EmployeeAlreadyAdded в случае, когда сотрудник уже существует.
//2. По адресу /employee/remove?firstName=Ivan&lastName=Ivanov должен вернуться объект Employee в формате JSON,
// т.е. { "firstName": "Ivan", "lastName": "Ivanov" }, или исключение со статусом EmployeeNotFound, если сотрудник отсутствует.
//3. По адресу /employee/find?firstName=Ivan&lastName=Ivanov должен вернуться объект Employee в формате JSON,
// т.е. { "firstName": "Ivan", "lastName": "Ivanov" }, или исключение со статусом EmployeeNotFound, если такой сотрудник отсутствует.
//### Шаг 12
//Написать метод, который выводит в браузер список всех сотрудников в формате JSON (необходимо вернуть объект списка).
    public List<Employee> employees = new ArrayList<>(10
            /*new Employee("Игнатьева", "Алёна"),
            new Employee("Левченко", "Антон"),
            new Employee("Суркова", "Антонина"),
            new Employee("Раков", "Дмитрий"),
            new Employee("Добрынин", "Елизар"),
            new Employee("Ведешина", "Лаурита"),
            new Employee("Попова", "Снежана"),
            new Employee("Ахметзянова", "Мария"),
            new Employee("Бронзова", "Ирина"),
            new Employee("Ушкина", "Елена")*/
    );
    public static final int MAX_SIZE = 3;

    @Override
    public String getEmployees(Integer number) throws EmployeeStorageIsFullException {
        final Employee employee;
        if (MAX_SIZE <= employees.size()) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме.");
        }
        employee = employees.get(number);
        final String employeeFL = "" + employee.getFirstName() + " " + employee.getLastName();
        return employeeFL;
    }

    public String findOutNumberEmployees() {
        return employees.size() + ", а именно: \" " + employees;
    }

    //### Шаг 5
    //    //Реализовать в сервисе три метода, которые принимают в качестве параметров firstName и lastName:
    //    //1. Добавить сотрудника.
    //    //2. Удалить сотрудника. remove(int index)
    //    //3. Найти сотрудника. contains(element) ИЛИ
    @Override
    public Object addEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        for (Employee employee1 : employees) {
            if (firstName == null || lastName == null) {
                return "Вы забыли прописать фамилию или имя сотрудника.";
            } else if (employees.contains(employee1)) {
                throw new EmployeeAlreadyAddedException("Сотрудник " + employee.getFirstName() + " " + employee.getLastName() + " уже есть в базе данных.");
            } else {
                employees.add(employee);
                return "В базу данных добавлен новый сотрудник: " + employee.getFirstName() + " " + employee.getLastName();
            }
        }
        return "В методе addEmployee что-то пошло не так...";
    }
            /*if (firstName == null || lastName == null) {
                return "Вы забыли прописать фамилию или имя нового сотрудника";
            } else if (employees.contains(new Employee(firstName, lastName))) {
                return "Сотрудник " + firstName + " " + lastName + " уже есть в базе.";
            } else if (employees.size() > MAX_SIZE) {
                return "Превышено допустимое количество сотрудников в базе данных. Вы не можете добавить нового сотрудника.";
            } else {
                employees.add(new Employee(firstName, lastName));
                return new Employee(firstName, lastName);
            }
        }*/

    @Override
    public Object removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i <= employees.size(); i++) {
            if (employees.get(i) != null && employees.get(i).equals(employee)) {
                employees.remove(employee);
                return "Сотрудник " + employee.getFirstName() + " " + employee.getLastName() + " удалён из базы данных.";
            } else {
                return "Некорректно введены данные о сотруднике или такого сотрудника нет в базе данных.";
            }
                /*return "Некорректно введены данные о сотруднике или такого сотрудника нет в базе данных.";
            } else {
                employees.remove((firstName + lastName));
                return "Сотрудник " + firstName + " " + lastName + " удалён из базы данных.";
            }
        }
        return employees;*/
        }
        return null;
    }

    @Override
    public Object containsEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i <= employees.size(); i++) {
            if (employees.get(i) != null && employees.get(i).equals(employee)) {
                employees.get(i);
                return employees;
            } else {
                return "Некорректно введены данные о сотруднике или такого сотрудника нет в базе данных.";
            }
            //найти сотрудника по имени или по индексу?
        /*try {
            employees.contains(new Employee(firstName, lastName));
        } catch (EmployeeNotFoundException e) {
            System.out.println("Данный работник не числится в компании или вы ввели данные не правильно.");
        }
        return employees;
    }*/
        /*for (Employee employee : employees) {
            if (employee != null) {
                return "Сотрудник " + employee + " числится в базе данных.";
            } else {
                return "Некорректно введены данные о сотруднике или такого сотрудника нет в базе данных.";
            }
        }
        return employees;*/
        }
        return null;
    }
}
/*} else if (employees.contains(new Employee(firstName, lastName))) {
            return "Сотрудник " + firstName + " " + lastName + " уже есть в базе данных.";

        } else if (employees.size() >= MAX_SIZE) {
        return "Превышено допустимое количество сотрудников в базе данных. Вы не можете добавить нового сотрудника.";
        */
