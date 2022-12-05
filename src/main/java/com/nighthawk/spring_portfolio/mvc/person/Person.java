package com.nighthawk.spring_portfolio.mvc.person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDef(name="json", typeClass = JsonType.class)
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    
    @Column(unique=false)
    private int height;

    @Column(unique=false)
    private int weight;

    @Type(type="json")
    @Column(columnDefinition = "jsonb")
    private Map<String,Map<String, Object>> stats = new HashMap<>(); 
    

    // Constructor used when building object from an API
    public Person(String name, String email, String password, Date dob, int height, int weight) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
    }

    public String toString(){
        return ("{ \"email\": " + this.email + ", " + "\"password\": " + this.password + ", " + "\"name\": " + this.name + ", " + "\"dob\": " + this.dob + " }" );
    }

    public int getAge() {
        if (this.dob != null) {
            LocalDate birthDay = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(birthDay, LocalDate.now()).getYears(); }
        return -1;
    }

    public String getAgeToString(){
        return ("{ \"name\": " + this.name + " ," + "\"age\": " + this.getAge() + " }" );
    }

    public int getBmi(){
        int bmi = (int) ( 703 * this.weight / Math.pow(this.height, 2) );
        return bmi;
    }

    public String getBmiToString(){
        return ("{ \"name\": " + this.name + " ," + "\"bmi\": " + this.getBmi() + " }" );
    }

    public static void main(String[] args) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = sdf.parse("2005-10-29");
        Person allArgsPerson = new Person("Kian", "Kianpasokhi@gmail.com", "Password123", myDate, 67, 130 );
        Person noArgsPerson = new Person();

        System.out.println(noArgsPerson);
        System.out.println(allArgsPerson);
    }

}