package com.example.brief17;

import com.example.brief17.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.example.brief17.entity.Student;
import com.example.brief17.service.StudentService;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

// TODO: Ajouter les tags nécessaires pour charger H2, charger le profil de test et importer le StudentService

@DataJpaTest
@ActiveProfiles("test")
@Import(StudentService.class)
class StudentServiceIntegrationTest {

    @Autowired
    private StudentService studentService;

    @Test
    void shouldSaveAndRetrieveStudent() {
        // Créer un nouvel étudiant
        Student student = new Student();
        student.setName("John Doe");
        student.setAddress("john.doe@example.com");

        // Sauvegarder l'étudiant
        Student savedStudent = studentService.saveStudent(student);

        // Récupérer l'étudiant par ID
        Optional<Student> retrievedStudent = studentService.findStudentById(savedStudent.getId());

        // Vérifier que l'étudiant récupéré est le même que l'étudiant sauvegardé
        assertThat(retrievedStudent).isPresent();
        assertThat(retrievedStudent.get().getName()).isEqualTo("John Doe");
        assertThat(retrievedStudent.get().getAddress()).isEqualTo("john.doe@example.com");
    }
}
