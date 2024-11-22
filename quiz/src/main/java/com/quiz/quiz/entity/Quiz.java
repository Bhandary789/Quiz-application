package com.quiz.quiz.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "quiz")
@Entity
public class Quiz {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private UUID id;
    private String question;
    private String correctAnswer;
//    private String optionA;
//    private String optionB;
//    private String optionC;
//    private String optionD;
     private List<String> option;
    private String userAnswer;
}
