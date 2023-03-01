package com.doctor_patinet.doctor_patient_project;


import com.doctor_patinet.doctor_patient_project.manager.UserManager;
import com.doctor_patinet.doctor_patient_project.manager.impl.UserManagerImpl;
import com.doctor_patinet.doctor_patient_project.models.User;
import com.doctor_patinet.doctor_patient_project.models.enums.Gender;
import com.doctor_patinet.doctor_patient_project.models.enums.UserType;
import com.doctor_patinet.doctor_patient_project.util.AppUtil;
import com.doctor_patinet.doctor_patient_project.util.MailSender;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Application {


    private User currentUser;

    private final static Scanner scanner = new Scanner(System.in);

    private final UserManager userManager = new UserManagerImpl();
    private final MailSender mailSender = new MailSender();

    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private static final int VERIFICATION_TEXT_LENGTH = 5;


    public void start() {
        welcomePage();
        String command = scanner.nextLine();
        switch (command) {
            case "0": {
                exit();
                break;
            }
            case "1": {
                login();
                break;
            }
            case "2": {
                register();
                break;
            }
            default: {
            }
        }


    }


    private void register() {

        String email = emailInitialization();

        System.out.println("Input your name");
        String name = scanner.nextLine();

        System.out.println("Input your surname");
        String surname = scanner.nextLine();

        System.out.println("Input your password");
        String password = scanner.nextLine();

        System.out.println("Input your gender");
        String gender = scanner.nextLine();

        Date birthDate = birthDateInitialization();

        UserType type = userTypeInitialization();

        String verificationCode = AppUtil.generateRandomString(VERIFICATION_TEXT_LENGTH);
        currentUser = userManager.save(User.builder()
                .name(name)
                .surname(surname)
                .birthDate(birthDate)
                .email(email)
                .password(password)
                .type(type)
                .verificationCode(verificationCode)
                .gender(Gender.valueOf(gender))
                .build());
        new Thread(()-> mailSender.sendMessage(currentUser.getEmail(), "Account verification",
                String.format("Welcom doctor_patient application. Your verification code: %s", verificationCode)))
                .start();
        System.out.println("Account successfully created: Input email code to verify your account");
        String code =  scanner.nextLine();
        if(verificationCode.equalsIgnoreCase(code)){
            System.out.println("Account verified. )");
            goToUserPage();
        }else {
            System.out.println("Invalid code");
            welcomePage();
        }


    }

    private void goToUserPage() {
        System.out.println("user paghe");
    }

    private UserType userTypeInitialization() {
        UserType res = null;
        while (res == null) {
            System.out.println("Input user type (doctor|patient)");
            String userType = scanner.nextLine();
            if (!(userType.equalsIgnoreCase("doctor") || userType.equalsIgnoreCase("patient"))) {
                System.out.println("Please input doctor or patient");
            } else {
                res = UserType.valueOf(userType.toUpperCase());
            }
        }
        return res;
    }

    private String emailInitialization() {
        System.out.println("Input your email");
        String email = scanner.nextLine();
        while (userManager.existByEmail(email)) {
            System.out.println("Email already used");
            System.out.println("Input your email");
            email = scanner.nextLine();
        }
        return email;
    }

    private Date birthDateInitialization() {
        Date birthDate = null;
        while (birthDate == null) {
            try {
                System.out.println("Input your birthDate (with dd-MM-yyyy format)");
                String dateInString = scanner.nextLine();
                birthDate = dateFormat.parse(dateInString);
            } catch (ParseException e) {
                System.out.println("Incorrect date format");
            }
        }
        return birthDate;
    }

    private void login() {
        System.out.println("Input your email");
        String email = scanner.nextLine();

        System.out.println("Input your password");
        String password = scanner.nextLine();
        User currentUser = userManager.getByEmailAndPassword(email, password);
        if (currentUser == null) {
            System.out.println("Incorrect email or password");
            start();
        } else if(!currentUser.isVerified()){
            goToVerification(currentUser);
        }else {
            this.currentUser = currentUser;
            userHome();

        }
    }

    private void goToVerification(User currentUser) {
        System.out.println("Your account is not verified. Do you wont to verify. (Y|N)");
        String command =  scanner.nextLine();
        if("y".equalsIgnoreCase(command)){
            System.out.println("Input your verification code");
            String verificationCode = scanner.nextLine();
            if(verificationCode.equalsIgnoreCase(currentUser.getVerificationCode())){

                currentUser.setVerificationCode(null);
                userManager.verify(currentUser);
                this.currentUser = currentUser;
                userHome();

            }
            else{
                System.out.println("Verification failed.");
                start();
            }
        }
        else{
            start();
        }
    }

    private void userHome() {
        if(currentUser.getType().equals(UserType.DOCTOR)){
            doctorHome();
        }
        else if (currentUser.getType().equals(UserType.PATIENT)){
            patientHome();
        }
        else if (currentUser.getType().equals(UserType.ADMIN)){
            patientHome();
        }
    }

    private void doctorHome(){
        System.out.println("For logout press 1");
        System.out.println("For see requests 2");
        System.out.println("For manage work time 3");
        String command = scanner.nextLine();
        switch (command) {
            case "1": {
                currentUser = null;
                start();
            }
            case "2": {
                showRequestsForDoctor();
                break;
            }
            case "3": {
                manageWorkTime();
                break;
            }
        }
    }

    private void showRequestsForDoctor() {

    }

    private void patientHome(){
        System.out.println("For logout press 1");
        System.out.println("For see requests 2");
        System.out.println("For doctors view 3");
        String command = scanner.nextLine();
        switch (command) {
            case "1": {
                currentUser = null;
                start();
            }
            case "2": {
//
                break;
            }
            case "3": {
//
                break;
            }
            case "4": {
//
                break;
            }
        }
    }


    private void exit() {
        System.out.println("By ... ");
        System.exit(0);
    }

    private void welcomePage() {
        System.out.println("For exit press 0");
        System.out.println("For login press 1.");
        System.out.println("For registration press 2.");
    }
}
