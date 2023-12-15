package vttp.ssf.assessment.eventmanagement.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Participant {

    @NotBlank(message = "Full Name is required")
    @Size(min = 5, max = 25, message = "Full Name must be between 5 to 25 characters")
    private String name;

    @Past(message = "DOB must be before today")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Email(message = "Invalid email format")
    @Size(max = 50, message = "Email length exceeded 50 characters")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Invalid phone number, must start with 8 or 9 and contain total of 8 digits")
    private String phone;

    private String gender;

    @NotNull(message = " Number of tickets to be requested should be bettwen 1 to 3")
    @Min(value = 1, message = "Minimum of 1 ticket to request")
    @Max(value = 3, message = "Maximum of 3 ticket to request")
    private Integer tickets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    public Participant() {
    }

    public Participant(String name, Date birthday, String email, String phone, String gender, Integer tickets) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.tickets = tickets;
    }
}
