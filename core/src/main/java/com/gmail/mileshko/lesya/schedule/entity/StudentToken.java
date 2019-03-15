package com.gmail.mileshko.lesya.schedule.entity;

import javax.persistence.*;

@Entity
@Table(name = "student_token")
public class StudentToken {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @ManyToOne
        @JoinColumn(name = "student_id")
        private Student student;

        @Column(name = "token")
        private String token;

        public StudentToken() {
        }

        public StudentToken(Student student, String token) {
                this.student = student;
                this.token = token;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Student getStudent() {
                return student;
        }

        public void setStudent(Student student) {
                this.student = student;
        }

        public String getToken() {
                return token;
        }

        public void setToken(String token) {
                this.token = token;
        }
}
