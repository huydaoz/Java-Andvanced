package session12.ex5.service;

import session12.ex5.dao.PatientDAO;
import session12.ex5.model.Patient;

import java.util.List;

public class PatientService {

    private PatientDAO dao = new PatientDAO();

    public List<Patient> getAllPatients() {
        return dao.getAllPatients();
    }

    public void addPatient(String name, int age, String dept) {
        if (name.isEmpty() || age <= 0) {
            System.out.println("Invalid data!");
            return;
        }

        Patient p = new Patient();
        p.setName(name);
        p.setAge(age);
        p.setDepartment(dept);

        dao.addPatient(p);
    }

    public void updateDisease(int id, String disease) {
        dao.updateDisease(id, disease);
    }

    public void discharge(int id) {
        double fee = dao.calculateFee(id);
        System.out.println("Total Fee: " + fee);
    }
}