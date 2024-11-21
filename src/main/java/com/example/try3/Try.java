package com.example.try3;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Text;

import java.sql.*;
import java.util.Arrays;

public class Try extends Application {
    // connection to the sql database
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/?user=root";
    static final String USER = "root";
    static final String PASS = "12345678";
    // method which allows for the application to automatically obtain the correct new doctor id, even if the application is closed
    private int getDoctorID() {
        int nextId = 1;
        String Query = "Select Max(doctor_id) from simplyhealthy.doctor";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(Query)){
            if (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
        }catch(SQLException ex){
            ex.printStackTrace();

        }
        return nextId;
    }
    // method which allows for the application to automatically obtain the correct new patient id, even when the application is closed
    private int getPatientID(){
        int nextId = 1;
        String Query = "Select Max(patient_id) from simplyhealthy.patient";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(Query)){
        if (rs.next()) {
            nextId = rs.getInt(1) + 1;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();

    }
        return nextId;
    }
    private int getNurseID(){
        int nextId = 1;
        String Query = "Select Max(nurse_id) from simplyhealthy.nurse";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(Query)){
            if (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return nextId;
    }
    private int getMedicineID(){
        int nextId = 1;
        String Query = "Select Max(medicine_id) from simplyhealthy.Medicine";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(Query)){
            if (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return nextId;
    }
    private int getcheckInID(){
        int nextId = 1;
        String Query = "Select Max(CheckIn_id) from simplyhealthy.Checkin";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(Query)){
            if (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return nextId;
    }

    private int getNurseSalaryID(){
        int nextId = 1;
        String Query = "Select Max(nurseSalary_id) from simplyhealthy.nurse_salaryHistory";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(Query)){
            if (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return nextId;
    }

    private int getDoctorSalaryID(){
        int nextId = 1;
        String Query = "Select Max(doctorSalary_id) from simplyhealthy.doctor_salaryHistory";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(Query)){
            if (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return nextId;
    }
    private int getOrderID(){
        int nextId = 1;
        String Query = "Select Max(order_id) from simplyhealthy.order";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(Query)){
            if (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return nextId;
    }

    @Override
    public void start(Stage primaryStage)  {

        /**
        All of the necessary user-friendly buttons throughout the application
        **/
        // all of the necessary add buttons in the application
        Button addDoctor = new Button("Add Doctor");
        Button addPatient = new Button("Add Patient");
        Button addNurse = new Button("Add Nurse");
        Button addMedicine = new Button("Add Medicine");
        Button addDoctorSalary = new Button("Add Doctor Salary");
        Button addNurseSalary = new Button("Add Nurse Salary");
        Button addOrder = new Button("Add Order");
        Button addCheckIn = new Button("Add Check-in");


        // all of the necessary deletion buttons in the application
        Button deleteNurse = new Button("Remove Nurse");
        Button deleteDoctor = new Button("Remove Doctor");
        Button deletePatient = new Button("Remove Patient");
        Button deleteMedicine = new Button("Remove Medicine");
        Button deleteDoctorSalary = new Button("Remove Doctor Salary History");
        Button deleteNurseSalary = new Button ("Remove Nurse Salary History");
        Button deleteOrder = new Button ("Remove Order");
        Button deleteCheckin = new Button("Remove Check-in");
        // all of the necessary update buttons in the application
        Button updateNurse = new Button("Update Nurse");
        Button updateDoctor = new Button("Update Doctor");
        Button updatePatient = new Button("Update Patient");
        Button updateMedicine = new Button("Update Medicine");
        Button updateDoctorSalary = new Button("Update Doctor Salary");
        Button updateNurseSalary = new Button("Update Nurse Salary");
        Button updateOrder = new Button("Update Order");
        Button updateCheckIn = new Button("Update CHeck-In");
        // all of the "get buttons in this application

        Button getDoctors = new Button("Search Doctors");
        Button getNurses = new Button("Search Nurses");
        Button getPatients = new Button("Search Patients");
        Button getDoctorSalaries = new Button("Search Doctors Salaries");
        Button getNurseSalaries = new Button("Search nurse Salaries");
        Button getOrders = new Button("Search orders");
        Button getCheckIns = new Button("Search check-ins");
        Button getMedicine = new Button("Search medicines");

        // all of the title pages, which have a defult size of 24
        MyText title = new MyText("Simply Healthy Page One");
        MyText title1 = new MyText("Simply Healthy Page Two");
        MyText title3 = new MyText("Simply Healthy Page Four");
        MyText title4 = new MyText("Simply Healthy Page Five");
        MyText title5 = new MyText("Simply Healthy Page Six");
        MyText title6 = new MyText("Simply Healthy Page Seven");
        MyText title7 = new MyText("Simply Healthy Page Eight");

        // Text used on the first page to add the doctors
        MyText docFName = new MyText("Doctor First Name (Cannot be null)",15);
        MyText docLname = new MyText("Doctor Last Name (Cannot be null)",15);
        MyText docPhone = new MyText("Doctor Phone (Cannot be null)", 15);
        MyText docYOE = new MyText("Doctor Years of Experience (cannot be null)",15);
        MyText docEdu = new MyText("Doctor Highest Education (cannot be null)",15);
        MyText docSpecialization = new MyText("Doctor Specialization (cannot be null)",15);
        // Texfields wihch allow for user-friendly input on the first page
        TextField fNameText = new TextField();
        fNameText.setPromptText("Use letters only");
        TextField lNameText = new TextField();
        lNameText.setPromptText("Use letters only");
        TextField docPhoneText = new TextField();
        docPhoneText.setPromptText("Use numbers only");
        TextField docPhoneText1 = new TextField();
        docPhoneText1.setPromptText("Use numbers only");
        TextField docPhoneText2 = new TextField();
        docPhoneText2.setPromptText("Use numbers only");
        TextField docYOEText = new TextField();
        docYOEText.setPromptText("Use numbers only");
        TextField docEduText = new TextField();
        docEduText.setPromptText("Use letters only");
        TextField docSpecializationText = new TextField();
        docSpecializationText.setPromptText("Use letters only");
        TextField deletePatientText = new TextField();
        deletePatientText.setPromptText("Use numbers only");

        MyText nurseFname = new MyText("Nurse First Name (cannot be null)",15);
        TextField nurseFnameText = new TextField();
        MyText nurseLname = new MyText("Nurse Last Name (cannot be null)",15);
        TextField nurseLnameText = new TextField();
        MyText shiftStart = new MyText("Nurse primary Shift Start Time (cannot be null)",15);
        TextField nurseShiftStartText = new TextField();
        TextField nurseShiftStartText1 = new TextField();
        MyText nurseShiftEnd = new MyText("Nurse Primary End Time (cannot be null)",15);
        TextField nurseShiftEndText = new TextField();
        TextField nurseShiftEndText1 = new TextField();
        MyText nursePhone = new MyText("Nurse Phone Number (cannot be null)",15);
        TextField nursePhoneText = new TextField();
        TextField nursePhoneText1 = new TextField();
        TextField nursePhoneText2 = new TextField();
        MyText nurseDoctor = new MyText("Nurse Primary doctor ID (cannot be null)", 15);
        TextField nurseDoctorText = new TextField();

        MyText docID = new MyText("Doctor ID",15);
        TextField docIDText = new TextField();
        MyText patID = new MyText("Patient ID",15);



        MyText patFName = new MyText("Patient First Name (cannot be null)",15);
        MyText patLName = new MyText("Patient Last Name (cannot be null)",15);
        MyText patPhone = new MyText("Patient Phone Number (cannot be null)",15);
        MyText patGender = new MyText("Patient Gender",15);

        TextField patFnameText = new TextField();
        patFnameText.setPromptText("Use letters only");
        TextField patLnameText = new TextField();
        patLnameText.setPromptText("Use letters only");
        TextField patPhoneText = new TextField();
        patPhoneText.setPromptText("Use numbers only");
        TextField patPhoneText1 = new TextField();
        patPhoneText1.setPromptText("Use numbers only");
        TextField patPhoneText2 = new TextField();
        patPhoneText2.setPromptText("Use numbers only");
        TextField patGenderText = new TextField();
        patGenderText.setPromptText("Use letters only");
        final int[] count = {getDoctorID()};
        final  int[] count1 = {getPatientID()};
        final int[] count2 = {getNurseID()};
        final int[] count3 = {getMedicineID()};
        final int[] count4 = {getDoctorSalaryID()};
        final  int[] count5 = {getNurseSalaryID()};
        final int[] count6 = {getcheckInID()};
        final int[] count7 = {getOrderID()};



        MyText docDocSalaryId = new MyText("Doctor ID (Cannot be null)",15);
        TextField docDocSalaryIdText = new TextField();
        docDocSalaryIdText.setPromptText("Use numbers only");
        MyText docSalaryStart = new MyText("Doctor Salary Start Date (Cannot be null)",15);
       TextField docSalaryStartText = new TextField();
       docSalaryStartText.setPromptText("YYYY");
       TextField docSalaryStartText1 = new TextField();
       docSalaryStartText1.setPromptText("MM");
        TextField docSalaryStartText2 = new TextField();
        docSalaryStartText2.setPromptText("DD");
        MyText docSalaryEnd = new MyText("Doctor Salary End Date (Cannot be null)",15);
       TextField docSalaryEndText = new TextField();
       docSalaryEndText.setPromptText("YYYY");
       TextField docSalaryEndText1 = new TextField();
       docSalaryEndText1.setPromptText("MM");
       TextField docSalaryEndText2 = new TextField();
       docSalaryEndText2.setPromptText("DD");
        MyText docSalaryAmount = new MyText("Doctor Salary Amount (Cannot be null)",15);
        TextField docSalaryAmountText = new TextField();
        docSalaryAmountText.setPromptText("Use numbers only");

        MyText medicineName = new MyText("Medicine Name (Cannot be null)",15);
        TextField medicineNameText = new TextField();
        MyText medicineDescription = new MyText("Medicine Description (Cannot be null)",15);
        TextField medicineDescriptionText = new TextField();
        MyText medicineTreatment = new MyText("Medicine Treatment(s) (Cannot be null)",15);
        TextField medicineTreatmentText = new TextField();


        MyText checkInDoc = new MyText("Doctor ID",15);
        TextField checkInDocText = new TextField();
        MyText checkInPat = new MyText("Patient ID",15);
        TextField checkInPatText = new TextField();
        MyText checkInTime = new MyText("Check in Time",15);
        TextField checkInTimeText = new TextField();
        TextField checkInTImeText1 = new TextField();
        MyText checkInDate = new MyText("Check in Date",15);
        TextField checkInDateText = new TextField();
        TextField checkInDateText1 = new TextField();
        TextField checkInDateText2 = new TextField();
        MyText nurSalaryHistory = new MyText("Nurse ID",15);
        TextField nurSalaryHistoryText = new TextField();
        MyText nurSalaryStart = new MyText("Nurse Salary Start",15);
        TextField nurSalaryStartText = new TextField();
        nurSalaryStartText.setPromptText("YYYY");
        TextField nurSalaryStartText1 = new TextField();
        nurSalaryStartText1.setPromptText("MM");
        TextField nurSalaryStartText2 = new TextField();
        nurSalaryStartText2.setPromptText("DD");
        MyText nurSalaryEnd = new MyText("Nurse Salary End",15);
        TextField nurseSalaryEndText = new TextField();
        nurseSalaryEndText.setPromptText("YYYY");
        TextField nurseSalaryEndText1 = new TextField();
        nurseSalaryEndText1.setPromptText("MM");
        TextField nurseSalaryEndText2 = new TextField();
        nurseSalaryEndText2.setPromptText("DD");
        MyText nurSalaryAmount = new MyText("Nurse Salary Amount",15);
        TextField nurSalaryAmountText = new TextField();

        MyText orderMedicineID = new MyText("Medicine ID",15);
        TextField orderMedicineIDText = new TextField();
        MyText orderPatientID = new MyText("Patient ID",15);
        TextField orderPatientIDText = new TextField();
        MyText orderAmount = new MyText("Order Quantity",15);
        TextField orderAmountText = new TextField();


        MyText statusMessage = new MyText("", 15);
        MyText displayMessage = new MyText("",15);
        MyText showMessage = new MyText("",15);
        MyText showNurseMessage = new MyText("",15);
        MyText showMedicineMessage = new MyText("",15);
        MyText showDoctorSalaryMessage = new MyText("",15);
        MyText showNurseSalaryMessage = new MyText("",15);
        MyText addCheckInMessage = new MyText("",15);
        MyText addOrderMessage = new MyText("",15);


        MyText delNurse = new MyText("Nurse ID",15);
        TextField delNurseText = new TextField();
        MyText delNurSalary = new MyText("Nurse Salary History ID",15);
        TextField delNurSalaryText = new TextField();
        MyText delDocSalary = new MyText("Doctor Salary History ID",15);
        TextField delDocSalaryText = new TextField();
        MyText delOrder = new MyText("Order ID",15);
        TextField delOrderText = new TextField();
        MyText delCheckIn = new MyText("Check-In ID",15);
        TextField delCheckInText = new TextField();
        MyText delMedicine= new MyText("Medicine ID",15);
        TextField delMedicineText = new TextField();

        MyText delPatMessage = new MyText("",15);
        MyText delDocMessage = new MyText("",15);
        MyText delNurMessage = new MyText("",15);
        MyText delNurSalMessage = new MyText("",15);
        MyText delDocSalMessage = new MyText("",15);
        MyText delOrderMessage = new MyText("",15);
        MyText delCheckInMessage = new MyText("",15);
        MyText delMedicineMessage = new MyText("",15);

        MyText updateDocID = new MyText("ID of The Doctor That Needs Updated Information",15);
        TextField updateDocText = new TextField();
        MyText updateDocFName = new MyText("Update Doctor First Name (Leave Blank If you do not want to change)",15);
        TextField updateDocFnameText = new TextField();
        MyText updateDocLName = new MyText("Update Doctor Last Name (Leave Blank If you do not want to Change)",15);
        TextField updateDocLNameText = new TextField();
        MyText updateDocPhone = new MyText("Update Doctor Phone Number(Leave Blank If you do not want to Change)",15);
        TextField updateDocPhoneText = new TextField();
        TextField updateDocPhoneText1 = new TextField();
        TextField updateDocPhoneText2 = new TextField();
        MyText updateDocYOE = new MyText("Update Doctor Years of Experience",15);
        TextField updateDocYOEText= new TextField();
        MyText updateDocHighestEDU = new MyText("Update Doctor Highest Education",15);
        TextField updateDocHighestEDUTEXT = new TextField();

        MyText updatePatientId = new MyText("Id of the Patient Which needs to be Updated",15);
        TextField updatePatientIdText = new TextField();
        MyText updatePatientFname =new MyText("Update Patient First Name (Leave Blank if you do not want to Change)",15);
        TextField updatePatientFnameText = new TextField();
        MyText updatePatientLname = new MyText("Update Patient Last Name",15);
        TextField updatePatientLnameText = new TextField();
        MyText updatePatientPhone = new MyText("Update Patient Phone Number",15);
        TextField updatePatientPhoneText = new TextField();
        TextField updatePatientPhoneText1 = new TextField();
        TextField updatePatientPhoneText2 = new TextField();
        MyText updatePatientGender = new MyText("Update Patient Gender",15);
        TextField updatePatientGenderText = new TextField();

        MyText updateNurseID = new MyText("Id of the Nurse which needs updating",15);
        TextField updateNurseIDText = new TextField();
        MyText updateNurseFname = new MyText("Update Nurse First Name",15)    ;
        TextField updateNurseFnameText = new TextField();
        MyText updateNurseLname = new MyText("Update Nurse Last Name",15);
        TextField updateNurselnameText = new TextField();
        MyText updateNurseStart = new MyText("Update Nurse Start Time",15);
        TextField updateNurseStartText = new TextField();
        TextField updateNurseStartText1 = new TextField();
        MyText updateNurseEnd = new MyText("Update Nurse End Time",15);
        TextField updateNurseEndText = new TextField();
        TextField updateNurseEndText1 = new TextField();
        MyText updateNursePhone = new MyText("Update Nurse Phone Number",15);
        TextField updateNursePhoneText = new TextField();
        MyText updateNurseDocId = new MyText("Update Nurse Primary Doctor",15);
        TextField updateNurseDocIdText = new TextField();
        
        MyText updateDocSalaryID = new MyText("Id of the Doctor Salary History that needs to be updated",15);
        TextField updateDocSalaryIDText = new TextField();
        MyText updateDocSalaryDocID = new MyText("Update Doctor ID of Doctor Salary",15) ;
        TextField updateDocSalaryDocIDText = new TextField();
        MyText updateDocSalaryStart = new MyText("Update Start Date of Doctor Salary",15) ;
        TextField updateDocSalaryStartText = new TextField();
        MyText updateDocSalaryEnd = new MyText("Update End Date of Doctor Salary",15) ;
        TextField updateDocSalaryEndText = new TextField();
        MyText updateDocSalaryAmount = new MyText("Update Doctor Salary Amount",15);
        TextField updateDocSalaryAmountText = new TextField();

        MyText updateMedicineID = new MyText("Id of the Medicine that needs to be updated") ;
        TextField updateMedicineIDText = new TextField();
        MyText updateMedicineName = new MyText("Update Medicine Name");
        TextField updateMedicineNameText = new TextField();
        MyText updateMedicineDesc = new MyText("Update Medicine Description");
        TextField updateMedicineDescText = new TextField();
        MyText updateMedicineTreatment = new MyText("Update Medicine Treatment");
        TextField updateMedicineTreatmentText = new TextField();

         MyText updateNurseSalaryID = new MyText("Id of the Nurse Salary History that needs to be updated",15);
         TextField updateNurseSalaryIDText = new TextField();
         MyText updateNurseSalaryDocID = new MyText("Update Doctor ID of Nurse Salary",15) ;
         TextField updateNurseSalaryDocIDText = new TextField();
         MyText updateNurseSalaryStart = new MyText("Update Start Date of Nurse Salary",15) ;
         TextField updateNurseSalaryStartText = new TextField();
         MyText updateNurseSalaryEnd = new MyText("Update End Date of Nurse Salary",15) ;
         TextField updateNurseSalaryEndText = new TextField();
         MyText updateNurseSalaryAmount = new MyText("Update Nurse Salary Amount",15);

         MyText updateOrderID = new MyText("The Id of the Order that needs to be Updated",15);
         TextField updateOrderIDText = new TextField();
         MyText updateOrderPatientID = new MyText("Update Patient ID for the Order",15);
         TextField updateOrderPatientIDText = new TextField();
         MyText updateOrderMedicineID = new MyText("Update Medicine Id for the Order",15);
         TextField updateOrderMedicineIDText = new TextField();
         MyText updateOrderQuantity = new MyText("Update Order Quantity",15) ;
         TextField updateOrderQuantityText = new TextField();

         MyText updateCheckInID = new MyText("The Id of the Check-In that needs to be Updated",15) ;
         TextField updateCheckInIDText = new TextField();
         MyText updateCheckInDocID = new MyText("Update Doctor ID in the Check-In",15);
         TextField updateCheckInDocIDText = new TextField();
         MyText updateCheckInPatID = new MyText("Update Patient ID in the Check-In",15);
         TextField updateCheckInPatIDText = new TextField();
         MyText updateCheckInTime = new MyText("Update Check in time",15);
         TextField updateCheckInTimeText = new TextField();
         MyText updateCheckInDate = new MyText("Update Check in Date",15);
         TextField updateCheckInDateText = new TextField();

         

         addDoctor.setOnAction(e -> {
         TextField updateNurseSalaryAmountText = new TextField();

         // Retrieve the values from the text fields



            String doctorFname = fNameText.getText();
            String doctorLname = lNameText.getText();
            String doctorPhone = docPhoneText.getText();
            String doctorPhone1 = docPhoneText1.getText();
            String doctorPhone2 = docPhoneText2.getText();
            String addedUP = doctorPhone + "-" + doctorPhone1 + "-"+ doctorPhone2;
            Integer doctorYOE = Integer.parseInt(docYOEText.getText());
            String docEduAns = docEduText.getText();
            String doctorspecialization = docSpecializationText.getText();


                if (doctorFname.matches("[a-zA-Z]+") && doctorLname.matches("[a-zA-Z]+") && doctorPhone.matches("\\d+")&& doctorPhone1.matches("\\d+")&& doctorPhone2.matches("\\d+")&& docYOEText.getText().matches("\\d+") && docEduAns.matches("[a-zA-Z]+")&& doctorspecialization.matches("[a-zA-Z]+")){
                // Execute the insert statement using the retrieved values
                String INSERT_QUERY = "INSERT INTO simplyhealthy.doctor " +
                        "(doctor_id, doctor_fname, doctor_lname, doctor_phone, doctor_YOE, doctor_highestEdu, doctor_specialization) " +
                        "VALUES (" + count[0] + ", '" + doctorFname + "', '" + doctorLname + "', '" + addedUP + "', " + doctorYOE + ", '" + docEduAns + "', '" + doctorspecialization + "')";
               Integer oneLess = count[0];
                count[0]++;


                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                     Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate(INSERT_QUERY);

                    showMessage.setText("New Doctor has been added with the id: " + oneLess);
                    showMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> showMessage.setVisible(false));
                    pauseTime.play();

                } catch (SQLException ex) {

                    ex.printStackTrace();

                }
            }
                else {
                    showMessage.setText("An error has occurred and a new doctor has not been added");
                    showMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> showMessage.setVisible(false));
                    pauseTime.play();
                }
        });

        addPatient.setOnAction(e->{
            String patientFname = patFnameText.getText();
            String patientLname = patLnameText.getText();
            String patientPhone = patPhoneText.getText();
            String patientPhone1 = patPhoneText1.getText();
            String patientPhone2 = patPhoneText2.getText();
            String patientGender = patGenderText.getText();

            String Insert_Query = "Insert Into simplyhealthy.patient"+
                    "(patient_id,patient_fname,patient_lname,patient_phone,patient_gender)"+
                    "values("+count1[0]+",'"+patientFname+"','"+patientLname+"','"+patientPhone+"','"+patientGender+"')";
            count1[0]++;
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(Insert_Query);
                displayMessage.setText("New Patient has been added with the id: "+ Arrays.toString(count1));
                displayMessage.setVisible(true);
                PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                pauseTime.setOnFinished(event -> displayMessage.setVisible(false));
                pauseTime.play();


            } catch (SQLException ex) {

                ex.printStackTrace();

            }
        });
        addNurse.setOnAction(e->{
            String nurseFirstName = nurseFnameText.getText();
            String nurseLastName = nurseLnameText.getText();
            String nurseShiftStart = nurseShiftStartText.getText();
            String nurseShiftStart1 = nurseShiftStartText1.getText();
            String addedNurseShiftStart = nurseShiftStart + ":"+nurseShiftStart1;
            String nurseShiftEnding = nurseShiftEndText.getText();
            String nurseShiftEnding1 = nurseShiftEndText1.getText();
            String addedNurseShiftEnd = nurseShiftEnding + ":" +nurseShiftEnding1;
            String addNursePhone = nursePhoneText.getText()+ "-" + nursePhoneText1.getText() +"-"+ nursePhoneText2.getText();
            String doctorNurseId = nurseDoctorText.getText();
            String INSERT_QUERY = "INSERT INTO simplyhealthy.nurse " +
                    "(nurse_id,nurse_Fname,nurse_Lname, nurse_shiftStart,nurse_shiftend,nurse_phone,doctor_id)"+
                    "values("+count2[0]+",'"+nurseFirstName+"','"+nurseLastName+"','"+addedNurseShiftStart+"','"+addedNurseShiftEnd+"','"+addNursePhone+"','"+doctorNurseId+"')";
            count2[0]++;
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(INSERT_QUERY);
                showNurseMessage.setText("New Nurse has been added with the id: "+ Arrays.toString(count2));
                showNurseMessage.setVisible(true);
                PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                pauseTime.setOnFinished(event -> showNurseMessage.setVisible(false));
                pauseTime.play();
            } catch (SQLException ex) {

                ex.printStackTrace();

            }

            });
        addMedicine.setOnAction(e->{
            String addMedicineName = medicineNameText.getText();
            String addMedicineDescription = medicineDescriptionText.getText();
            String addMedicineTreatment = medicineTreatmentText.getText();

            String Insert_Query = "Insert Into simplyHealthy.medicine"+
                    "(medicine_id,medicine_name,medicine_description,medicine_treatment)"+
                    "values("+count3[0]+",'"+addMedicineName+"','"+addMedicineDescription+"','"+addMedicineTreatment+"')";
            count3[0]++;
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(Insert_Query);
                showMedicineMessage.setText("New Nurse has been added with the id: "+ Arrays.toString(count3));
                showMedicineMessage.setVisible(true);
                PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                pauseTime.setOnFinished(event -> showMedicineMessage.setVisible(false));
                pauseTime.play();
            } catch (SQLException ex) {

                ex.printStackTrace();

            }

        });
        addNurseSalary.setOnAction(e->{
            Integer addNurseNurseSalID = Integer.parseInt(nurSalaryHistoryText.getText());
            String addNurseSalaryStart = nurSalaryStartText.getText();
            String addNurseSalaryStart1 = nurSalaryStartText1.getText();
            String addNurseSalaryStart2 = nurSalaryStartText2.getText();
            String allAddedNurseSalaryStart = addNurseSalaryStart + "-" + addNurseSalaryStart1 + "-"+addNurseSalaryStart2;
            String addNurseSalaryEnd = nurseSalaryEndText.getText();
            String addNurseSalaryEnd1 = nurseSalaryEndText1.getText();
            String addNurseSalaryEnd2 = nurseSalaryEndText2.getText();
            String allAddedNurseSalarEnd = addNurseSalaryEnd+ "-" + addNurseSalaryEnd1 + "-"+addNurseSalaryEnd2;
            Double addNurseSalaryAmount = Double.parseDouble(nurSalaryAmountText.getText());

            String addNurseSal_Query = "Insert into simplyhealthy.nurse_salaryhistory"+
                   "(nursesalary_id, nurse_id, nurseSalary_start, nurseSalary_end, nurseSalary_amount)"+
                    "values("+count5[0]+","+addNurseNurseSalID+",'"+allAddedNurseSalaryStart+"','"+allAddedNurseSalarEnd+"','"+addNurseSalaryAmount+"') ";
            count5[0]++;
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(addNurseSal_Query);
                showNurseSalaryMessage.setText("New Nurse Salary has been added with the id: "+ Arrays.toString(count5));
                showNurseSalaryMessage.setVisible(true);
                PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                pauseTime.setOnFinished(event -> showNurseSalaryMessage.setVisible(false));
                pauseTime.play();
            } catch (SQLException ex) {

                ex.printStackTrace();

            }

        });
        addDoctorSalary.setOnAction(e->{
            Integer addDocID = Integer.parseInt(docDocSalaryIdText.getText());
            String addDocSalaryStart = docSalaryStartText.getText();
            String addDocSalaryStart1 = docSalaryStartText1.getText();
            String addDocSalaryStart2 = docSalaryStartText2.getText();
            String addedUpDocStart =  addDocSalaryStart + "-" + addDocSalaryStart1 + "-" + addDocSalaryStart2;
            String addDocSalaryEnd = docSalaryEndText.getText();
            String addDocSalaryEnd1 = docSalaryEndText1.getText();
            String addDocSalaryEnd2 = docSalaryEndText2.getText();
            String addedUpDocEnd =  addDocSalaryEnd + "-" + addDocSalaryEnd1 + "-" + addDocSalaryEnd2;
            String addDocSalaryAmount = docSalaryAmountText.getText();
            String addDocSalary_Query = "Insert into simplyhealthy.doctor_salaryHistory"+
                    "(doctorsalary_id, doctor_id, doctorSalary_start, doctorSalary_end,doctorSalary_amount)"+
                    "values("+count4[0]+","+addDocID+",'"+addedUpDocStart+"','"+addedUpDocEnd+"','"+addDocSalaryAmount+"') ";
            count4[0]++;


            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(addDocSalary_Query);
                showDoctorSalaryMessage.setText("New Nurse has been added with the id: "+ Arrays.toString(count4));
                showDoctorSalaryMessage.setVisible(true);
                PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                pauseTime.setOnFinished(event -> showDoctorSalaryMessage.setVisible(false));
                pauseTime.play();
            } catch (SQLException ex) {

                ex.printStackTrace();

            }
        });
        addCheckIn.setOnAction(e->{
            Integer addCheckInDoctorID = Integer.parseInt(checkInDocText.getText());
            Integer addCheckInPatientID = Integer.parseInt(checkInPatText.getText());
            String addCheckInTime = checkInTimeText.getText();
            String addCheckInTIme1 = checkInTImeText1.getText();
            String addedUpCheckIn = addCheckInTime+":"+addCheckInTIme1;
            String addCheckInDate = checkInDateText.getText();
            String addCheckInDate1 = checkInDateText1.getText();
            String addCheckInDate2 = checkInDateText2.getText();
            String addedUpCheckInDate = addCheckInDate+ "-" + addCheckInDate1 + "-" +addCheckInDate2;

            String addDocSalary_Query = "Insert into simplyhealthy.CheckIn"+
                    "(checkIn_id, doctor_id, patient_id, checkIn_time, checkIn_date)"+
                    "values("+count6[0]+","+addCheckInDoctorID+","+addCheckInPatientID+",'"+addedUpCheckIn+"','"+addedUpCheckInDate+"') ";
            count6[0]++;


            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(addDocSalary_Query);
                addCheckInMessage.setText("New Nurse has been added with the id: "+ Arrays.toString(count6));
                addCheckInMessage.setVisible(true);
                PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                pauseTime.setOnFinished(event -> addCheckInMessage.setVisible(false));
                pauseTime.play();
            } catch (SQLException ex) {

                ex.printStackTrace();

            }



        });
        addOrder.setOnAction(e->{
            Integer addOrderPatientID = Integer.parseInt(orderPatientIDText.getText());
            Integer addOrderMedicineID = Integer.parseInt(orderMedicineIDText.getText());
            Double addOrderQuantity = Double.parseDouble(orderAmountText.getText());

            String addDocSalary_Query = "Insert into simplyhealthy.Order"+
                    "(order_id, patient_id, medicine_id, order_quantity)"+
                    "values("+count7[0]+","+addOrderPatientID+","+addOrderMedicineID+",'"+addOrderQuantity+"') ";
            count7[0]++;


            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(addDocSalary_Query);
                addOrderMessage.setText("New Nurse has been added with the id: "+ Arrays.toString(count7));
                addOrderMessage.setVisible(true);
                PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                pauseTime.setOnFinished(event -> addOrderMessage.setVisible(false));
                pauseTime.play();
            } catch (SQLException ex) {

                ex.printStackTrace();

            }


        });

        deleteDoctor.setOnAction(e->{
        int doctorId = Integer.parseInt(docIDText.getText());
        String DELETE_QUERY= "Delete from simplyHealthy.doctor where doctor_id = "+doctorId;
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate(DELETE_QUERY);
                if (rowsAffected > 0) {
                    statusMessage.setText("Doctor with ID: " + doctorId+ " has successfully been deleted");
                    statusMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> statusMessage.setVisible(false));

                    pauseTime.play();
                } else {
                    statusMessage.setText("Doctor with ID: " + doctorId+ " does not exist");
                    statusMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> statusMessage.setVisible(false));
                    pauseTime.play();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        });
        deletePatient.setOnAction(e->{
            Integer patientId = Integer.parseInt(deletePatientText.getText());
            String delQuery = "Delete from simplyhealthy.patient where patient_id = " + patientId;
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate(delQuery);
                if (rowsAffected > 0) {
                    delPatMessage.setText("Patient with ID: " + patientId+ " has successfully been deleted");
                    delPatMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> delPatMessage.setVisible(false));

                    pauseTime.play();
                } else {
                    delPatMessage.setText("Patient with ID: " + patientId+ " does not exist");
                    delPatMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> delPatMessage.setVisible(false));
                    pauseTime.play();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        });

        deleteNurse.setOnAction(e->{
            Integer nurseID = Integer.parseInt(delNurseText.getText());
            String delNurseQuery = "Delete from simplyhealthy.nurse where nurse_id = " + nurseID;
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate(delNurseQuery);
                if (rowsAffected > 0) {
                    delNurMessage.setText("Nurse with ID: " + nurseID+ " has successfully been deleted");
                    delNurMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> delNurMessage.setVisible(false));

                    pauseTime.play();
                } else {
                    delNurMessage.setText("Nurse with ID: " + nurseID+ " does not exist");
                    delNurMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> delNurMessage.setVisible(false));
                    pauseTime.play();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        });

        deleteNurseSalary.setOnAction(e->{
            Integer nurseSalaryID = Integer.parseInt(delNurSalaryText.getText());
            String delNurseSalQuery = "Delete from simplyhealthy.nurse_salaryHistory where nurseSalary_id = " + nurseSalaryID;
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate(delNurseSalQuery);
                if (rowsAffected > 0) {
                    delNurSalMessage.setText("Nurse Salary with ID: " + nurseSalaryID+ " has successfully been deleted");
                    delNurSalMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> delNurSalMessage.setVisible(false));

                    pauseTime.play();
                } else {
                    delNurSalMessage.setText("Nurse Salary with ID: " + nurseSalaryID+ " does not exist");
                    delNurSalMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> delNurSalMessage.setVisible(false));
                    pauseTime.play();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        });

        deleteDoctorSalary.setOnAction(e->{
            Integer doctorSalaryID = Integer.parseInt(delDocSalaryText.getText());
            String delDocSalQuery = "Delete from simplyhealthy.doctor_salaryHistory where doctorSalary_id = " + doctorSalaryID;
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate(delDocSalQuery);
                if (rowsAffected > 0) {
                    delDocSalMessage.setText("Nurse Salary with ID: " + doctorSalaryID+ " has successfully been deleted");
                    delDocSalMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> delDocSalMessage.setVisible(false));

                    pauseTime.play();
                } else {
                    delDocSalMessage.setText("Nurse Salary with ID: " + doctorSalaryID+ " does not exist");
                    delDocSalMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> delDocSalMessage.setVisible(false));
                    pauseTime.play();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        });

        deleteOrder.setOnAction(e->{
            Integer OrderID = Integer.parseInt(delOrderText.getText());
            String delOrderQuery = "Delete from simplyhealthy.order where order_id = " + OrderID;
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate(delOrderQuery);
                if (rowsAffected > 0) {
                    delOrderMessage.setText("Nurse Salary with ID: " + OrderID+ " has successfully been deleted");
                    delOrderMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> delOrderMessage.setVisible(false));

                    pauseTime.play();
                } else {
                    delOrderMessage.setText("Order with ID: " + OrderID+ " does not exist");
                    delOrderMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> delOrderMessage.setVisible(false));
                    pauseTime.play();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        });
        deleteCheckin.setOnAction(e->{
            Integer checkInID = Integer.parseInt(delCheckInText.getText());
            String delDocSalQuery = "Delete from simplyhealthy.checkIn where checkIn_id = " + checkInID;
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate(delDocSalQuery);
                if (rowsAffected > 0) {
                    delCheckInMessage.setText("Check-In with ID: " + checkInID+ " has successfully been deleted");
                    delCheckInMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> delCheckInMessage.setVisible(false));

                    pauseTime.play();
                } else {
                    delCheckInMessage.setText("Check-In with ID: " + checkInID+ " does not exist");
                    delCheckInMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> delCheckInMessage.setVisible(false));
                    pauseTime.play();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        });
        deleteMedicine.setOnAction(e->{
            Integer medicineID = Integer.parseInt(delMedicineText.getText());
            String delMedicineQuery = "Delete from simplyhealthy.medicine where medicine_id = " + medicineID;
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate(delMedicineQuery);
                if (rowsAffected > 0) {
                    delMedicineMessage.setText("Medicine with ID: " + medicineID+ " has successfully been deleted");
                    delMedicineMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> delMedicineMessage.setVisible(false));

                    pauseTime.play();
                } else {
                    delMedicineMessage.setText("Nurse Salary with ID: " + medicineID+ " does not exist");
                    delMedicineMessage.setVisible(true);
                    PauseTransition pauseTime = new PauseTransition(Duration.seconds(3));
                    pauseTime.setOnFinished(event -> delMedicineMessage.setVisible(false));
                    pauseTime.play();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        });
        updateDoctor.setOnAction(e->{
            Integer updateDoc = Integer.parseInt(updateDocText.getText());
            String updateDocFnameInfo = updateDocFnameText.getText();
            String updateDocLnameInfo = updateDocLNameText.getText();
            String updateDocphoneInfo = updateDocPhoneText.getText();
            String updateDocphoneInfo1 = updateDocPhoneText1.getText();
            String updateDocphoneInfo2 = updateDocPhoneText2.getText();
            Integer updateDocYOEInfo = Integer.parseInt(updateDocYOEText.getText());
            String updateDocEDUInfo = updateDocHighestEDUTEXT.getText();

            String updateDocQuery = "update simplyhealthy.doctor set " +
                    "doctor_fname = '" +updateDocFnameInfo +"',"+
                    "doctor_lname ='" +updateDocLnameInfo+"',"+
                    "doctor_phone='" +updateDocphoneInfo+ "-" +updateDocphoneInfo1+"-"+updateDocphoneInfo2+"',"+
                    "doctor_YOE='"+updateDocYOEInfo+"',"+
                    "doctor_highestEDU='"+updateDocEDUInfo+"'"+
                    "where doctor_id = " +updateDoc;
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate(updateDocQuery);
                if (rowsAffected > 0) {
                    System.out.println("Doctor with ID " + updateDoc + " updated successfully.");
                } else {
                    System.out.println("No doctor found with ID " + updateDoc + ".");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        });
        updatePatient.setOnAction(e->{
            Integer updatedPatId = Integer.parseInt(updatePatientIdText.getText());
            String updatePatFname = updatePatientFnameText.getText();
            String updatePatLname = updatePatientLnameText.getText();
            String updatePatPhone = updatePatientPhoneText.getText();
            String updatePatePhone1 = updatePatientPhoneText1.getText();
            String updatePatePhone2 = updatePatientPhoneText2.getText();
            String updatePatGender = updatePatientGenderText.getText();

            String updatePatQuery = "update simplyhealthy.patient set" +
                    "patient_fname= '"+updatePatFname+","+
                    "patient_lname = '"+updatePatLname+","+
                    "patient_phone= '"+updatePatPhone+"-"+updatePatePhone1+"-"+updatePatePhone2+"',"+
                    "patient_gender='"+updatePatientGenderText+"'"+
                    "where doctor_id ="+ updatedPatId;
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate(updatePatQuery);
                if (rowsAffected > 0) {
                    System.out.println("Doctor with ID " + updatedPatId + " updated successfully.");
                } else {
                    System.out.println("No doctor found with ID " + updatedPatId + ".");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
  });
        updateNurse.setOnAction(e->{
            Integer updateNurseId = Integer.parseInt(updateNurseIDText.getText());


        });




        TextArea textArea = new TextArea();

        getPatients.setOnAction(e->{
            String getQuery = "Select * from simplyhealthy.patient";
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();) {
           ResultSet set = stmt.executeQuery(getQuery);
           String textAreaText = "";
           while(set.next()){
               textAreaText += "Patient ID" + set.getInt("patient_id")
                       +"Patient First Name" + set.getString("patient_fname")
                       +"Patient Last Name" + set.getString("patient_lname")
                       +"Patient Phone"+ set.getString("patient_phone")
                       +"Patient Gender"+ set.getString("patient_Gender")
                        + "\n";
           }
           textArea.setText(textAreaText);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }

        });

        getDoctors.setOnAction(e->{
            String getQuery = "Select * from simplyhealthy.Doctor";
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();) {
                ResultSet set = stmt.executeQuery(getQuery);
                String textAreaText = "";
                while(set.next()) {
                    textAreaText += "Doctor ID" + set.getInt("doctor_id")
                            + "Doctor First Name" + set.getString("doctor_fname")
                            + "Doctor Last Name" + set.getString("doctor_lname")
                            + "Doctor Phone" + set.getString("doctor_phone")
                            + "Doctor Years of Experience" + set.getInt("doctor_YOE")
                            + "Doctor Highest Education" + set.getString("doctor_highestEdu")
                            + "Doctor Specialization" + set.getString("doctor_specialization")
                            + "\n";
                }
                textArea.setText(textAreaText);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }

        });

        getNurses.setOnAction(e->{
            String getQuery = "select * from simplyhealthy.nurse";
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();) {
                ResultSet set = stmt.executeQuery(getQuery);
                String textAreaText = "";
                while(set.next()) {
                    textAreaText += "Nurse ID" + set.getInt("nurse_id")
                    + "First Name" + set.getString("nurse_fname")
                    + "Last Name" +set.getString("nurse_lname")
                    + "Primary Shift Start"+set.getString("nurse_shiftstart")
                    + "Primary Shift End" + set.getString("nurse_shiftend")
                    + "Phone Number" + set.getString("nurse_phone")
                    + "Primary Doctor ID" + set.getInt("doctor_id")
                    + "\n";
                }
                textArea.setText(textAreaText);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        getDoctorSalaries.setOnAction(e->{
            String getQuery = "select * from simplyhealthy.doctor_salaryhistory";
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();) {
                ResultSet set = stmt.executeQuery(getQuery);
                String textAreaText = "";
                while(set.next()) {
                    textAreaText += "Doctor Salary History ID" + set.getInt("doctorsalary_id")
                            + "Doctor ID" + set.getString("doctor_id")
                            + "Doctor Salary Start" +set.getString("doctorsalary_start")
                            + "Doctor Salary End"+set.getString("doctorsalary_end")
                            + "Doctor Salary Amount" + set.getString("doctorsalary_amount")
                            + "\n";
                }
                textArea.setText(textAreaText);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        getMedicine.setOnAction(e->{
            String getQuery = "select * from simplyhealthy.medicine";
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();) {
                ResultSet set = stmt.executeQuery(getQuery);
                String textAreaText = "";
                while(set.next()) {
                    textAreaText += "Medicine ID" + set.getInt("medicine_id")
                            + "Name" + set.getString("Medicine_id")
                            + "Treatment" +set.getString("medicine_treatment")
                            + "\n";
                }
                textArea.setText(textAreaText);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        getCheckIns.setOnAction(e->{
            String getQuery = "select * from simplyhealthy.checkin";
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();) {
                ResultSet set = stmt.executeQuery(getQuery);
                String textAreaText = "";
                while(set.next()) {
                    textAreaText += "Check-In ID" + set.getInt("CheckIn_id")
                            + "Doctor ID" + set.getString("doctor_id")
                            + "patient ID" +set.getString("patient_Id")
                            + "Check-In Time"+set.getString("CheckIn_time")
                            +"Check-In Date"+set.getString("CheckIn_Date")
                            + "\n";
                }
                textArea.setText(textAreaText);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }

        });

        getNurseSalaries.setOnAction(e->{
            String getQuery = "select * from simplyhealthy.nurse_salaryHistory";
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();) {
                ResultSet set = stmt.executeQuery(getQuery);
                String textAreaText = "";
                while(set.next()) {
                    textAreaText += "" + set.getInt("nurseSalary_id")+
                            " "+ set.getInt("nurse_id")+
                            " " +set.getString("nurseSalary_start")+
                            " " +set.getString("nurseSalary_end")+
                            " " +set.getDouble("nurseSalary_amount")+
                            "\n";
                }
                textArea.setText(textAreaText);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        getOrders.setOnAction(e->{
            String getQuery = "select * from simplyhealthy.order";
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();) {
                ResultSet set = stmt.executeQuery(getQuery);
                String textAreaText = "";
                while(set.next()) {
                    textAreaText += "Order ID" + set.getInt("order_id")
                            + "Patient ID" + set.getString("patient_id")
                            + "Medicine ID" +set.getString("medicine_Id")

                            + "\n";
                }
                textArea.setText(textAreaText);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }

        });
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

        ) {
            /**
             String sql = "CREATE DATABASE SimplyHealthy";
             stmt.executeUpdate(sql);
             System.out.println("Database created successfully...");
             **/
            /**
             String sql = "Create table simplyhealthy.doctor" +
             "(doctor_id INTEGER not NULL UNIQUE, "+
             "doctor_Fname VARCHAR (45) not NULL, "+
             "doctor_Lname VARCHAR (45) not NULL, "+
             "doctor_phone VARCHAR (90), not NULL "+
             "doctor_YOE INTEGER not NULL,"+
             "doctor_highestEdu varchar (60) not NULL,"+
             "doctor_specialization varchar (45) not NULL,"+
             "Primary Key (doctor_id))";

             stmt.executeUpdate(sql);
             System.out.println("table created successfully...");
             **/
            /**
             String sql = "Create table simplyhealthy.doctor_SalaryHistory" +
             "(doctorSalary_id INTEGER not NULL UNIQUE,"+
             "doctor_id INTEGER not NULL, "+
             "doctorSalary_start date not NULL, "+
             "doctorSalary_end date not NULL, "+
             "doctorSalary_amount INTEGER not NULL, "+
             "Primary Key (doctorSalary_id),"+
             "FOREIGN KEY (doctor_id) REFERENCES simplyhealthy.doctor(doctor_id))";
             stmt.executeUpdate(sql);
             System.out.println("table created successfully...");
             **/
/**
 String sql = "create table simplyhealthy.nurse"+
 "(nurse_id INTEGER not NULL UNIQUE,"+
 "nurse_Fname VARCHAR (45) not NULL, "+
 "nurse_Lname VARCHAR (45) not NULL, "+
 "nurse_shiftstart Varchar(20) not NULL, "+
 "nurse_shiftend Varchar(20) not NULL, "+
 "nurse_phone VARCHAR(15) not NULL,"+
 "doctor_id INTEGER not NULL,"+
 "PRIMARY KEY (nurse_id),"+
 "FOREIGN KEY (doctor_id) REFERENCES simplyhealthy.doctor(doctor_id))";

 stmt.executeUpdate(sql);
 System.out.println("table created successfully...");
**/
/**
 String sql = "Create table simplyhealthy.nurse_SalaryHistory" +
 "(nurseSalary_id INTEGER not NULL UNIQUE,"+
 "nurse_id INTEGER not NULL, "+
 "nurseSalary_start date not NULL, "+
 "nurseSalary_end date not NULL, "+
 "nurseSalary_amount INTEGER not NULL, "+
 "Primary Key (nurseSalary_id),"+
 "FOREIGN KEY (nurse_id) REFERENCES simplyhealthy.nurse(nurse_id))";
 stmt.executeUpdate(sql);
 System.out.println("table created successfully...");
**/
/**
 String sql = "Create table simplyhealthy.patient"+
 "(patient_id INTEGER not NULL UNIQUE,"+
 "patient_Fname VARCHAR(45) not NULL,"+
 "patient_Lname VARCHAR (45) not NULL,"+
 "patient_phone VARCHAR(90) not NULL,"+
 "patient_gender varchar(20),"+
 "PRIMARY KEY (patient_id))";

 stmt.executeUpdate(sql);
 System.out.println("table created successfully...");

 **/
/**
 String sql = "Create table simplyhealthy.CheckIn"+
 "(CheckIn_id INTEGER not NULL UNIQUE,"+
 "doctor_id INTEGER not NULL,"+
 "patient_id INTEGER not NULL,"+
 "CheckIn_time Varchar(10) not NULL,"+
 "CheckIn_date date not NULL,"+
 "PRIMARY KEY (checkin_id),"+
 "FOREIGN KEY (doctor_id) REFERENCES simplyhealthy.doctor(doctor_id),"+
 "FOREIGN KEY (patient_id) REFERENCES simplyhealthy.patient(patient_id))";
 stmt.executeUpdate(sql);
 System.out.println("table created successfully...");
**/

/**
 String sql = "Create table simplyhealthy.medicine"+
 "(medicine_id INTEGER not NULL UNIQUE,"+
 "medicine_name VARCHAR(100) not NULL,"+
 "medicine_description VARCHAR (255) not NULL,"+
 "medicine_treatment VARCHAR(100) not NULL,"+
 "PRIMARY KEY (medicine_id))";
 stmt.executeUpdate(sql);
 System.out.println("table created successfully...");
 **/

/**
            String sql = "Create table simplyhealthy.order"+
                    "(order_id INTEGER not NULL UNIQUE,"+
                    "patient_id INTEGER not NULL,"+
                    "medicine_id INTEGER not NULL,"+
                    "order_quantity Double not NULL,"+
                    "PRIMARY KEY (order_id),"+
                    "FOREIGN KEY(patient_id) REFERENCES simplyhealthy.patient(patient_id), "+
                    "FOREIGN KEY(medicine_id) REFERENCES simplyhealthy.medicine(medicine_id))";
            stmt.executeUpdate(sql);
            System.out.println("table created successfully...");
   **/
/**
            String sql = "INSERT INTO simplyhealthy.doctor (doctor_id, doctor_Fname, doctor_Lname, doctor_phone, doctor_YOE, doctor_highestEdu, doctor_specialization) " +
                    " VALUES (1, 'Zachary', 'Muhammed', '7988-367-3447', 10, 'Doctorate of Medicine', 'Neurologist'),"+
                    " (2, 'Rotri', 'Machalat', '7988-956-4312', 17,'Doctorate of Philosophy','immunologist'),"+
                    " (3, 'Klay', 'Dyan','7988-864-9871',6,'Fellowship of Nephrology','Nephrologist'),"+
                    " (4, 'Karyn', 'Karson','7988-810-4358',22,'Doctorate of Osteopathic Medicine','General Doctor'),"+
                    " (5,'Gilies','Isiah','7988-967-8001',28,'Doctorate of Medicine','Neurologist'),"+
                    "(6, 'Emerie','Mandy','7988-111-6341',21,'Doctorate of Medicine','General Doctor'),"+
                    " (7, 'Tetty', 'Tshlia','7988-955-1210',15,'Doctorate of Osteopathic Medicine','Dermatologist'),"+
                    " (9,'Tarina', 'Leonard','7988-667-0955',4,'Doctorate of Pharmacy','Pharmacist'),"+
                    "(10, 'Kieran', 'Burgundy','7988-123-953',37,'Doctorate of Medicine','Internal Medicine Doctor'),"+
                    " (11, 'Jolyon','Gabe','7988-239-9410',22,'Doctorate of Philosophy', 'General Doctor')";

            stmt.executeUpdate(sql);
**/
/**
            String sql = "Insert INTO simplyhealthy.nurse (nurse_id, nurse_fname, nurse_lname, nurse_shiftstart, nurse_shiftend, nurse_phone, doctor_id)"+
            "VALUES(1,'Beau','Wiley','9:00 am', '6:00 pm', '7988-233-6294',1),"+
            "(2, 'Jaynie', 'Briggs', '5:00 pm', '2:00 am', '7988-678-4597',1),"+
            "(3,'Rexanne','Foster','1:00 am', '10:00 am', '7988-105-0671',1),"+
            "(4,'Pleasance','Evelyn','9:00 am', '6:00 pm', '7988-164-8573',2),"+
            "(5,'Sullivan','Malcom','5:00 pm', '2:00 am','7988-653-0032',2),"+
            "(6,'Marla','Lottie','1:00 am', '10:00 am','7988-675-5632',2),"+
            "(7,'Carly','Deirdre','9:00 am', '6:00 pm','7988-555-3256',3),"+
            "(8,'Maya', 'Royce','5:00 pm', '2:00 am','7988-124-5734',3),"+
            "(9,'Tera','Paislee','1:00 am', '10:00 am','7988-604-2056',3),"+
            "(10,'Sherisse','Delta','9:00 am', '6:00 pm','7988-780-3455',4),"+
             "(11,'Kristine','Trent','5:00 pm', '2:00 am','7988-657-8076',4),"+
             "(12,'Orinda','Scotty','1:00 am', '10:00 am','7988-453-8700',4),"+
             "(13, 'Delores','Trenton','9:00 am', '6:00 pm','7988-708-3423',5),"+
             "(14, 'Durward','Kevin','5:00 pm', '2:00 am','7988-777-5676',5),"+
             "(15,'Maverick', 'Tory','1:00 am', '10:00 am','7988-656-4523',5),"+
             "(16,'Raynard', 'Gerald','9:00 am', '6:00 pm','7988-789-4354',6),"+
             "(17,'Janie','Melva','5:00 pm', '2:00 am','7988-565-5644',6),"+
             "(18,'Katharyn', 'Bryony','1:00 am', '10:00 am','7988-909-1211',6),"+
             "(19,'Helena','Sofia','9:00 am', '6:00 pm','7988-510-9065',7),"+
             "(20,'Carolann','Averill','5:00 pm', '2:00 am','7988-897-7606',7),"+
             "(21,'Summer','Ariella','1:00 am', '10:00 am','7988-394-4354',7),"+
             "(22,'Clover','Stacia','9:00 am', '6:00 pm','7988-766-5676',9),"+
             "(23,'Eleanor', 'Lou','5:00 pm', '2:00 am','7988-987-3467',9),"+
             "(24,'Dewey','Edie','1:00 am', '10:00 am','7988-755-0988',9),"+
             "(25,'Dale','Rowina','9:00 am', '6:00 pm', '7988-998-8822',10),"+
             "(26,'Natasha','Lavone','5:00 pm', '2:00 am','7988-659-8178',10),"+
             "(27,'Simon','Hunter','1:00 am', '10:00 am','7988-678-3240',10),"+
             "(28,'Maeve','Haylie','9:00 am', '6:00 pm', '7988-443-9713',11),"+
             "(29,'Gemma', 'Walker','5:00 pm', '2:00 am', '7988-956-2343',11),"+
             "(30, 'Tyrell', 'Jacki','1:00 am', '10:00 am','7988-528-9732',11)";
             stmt.executeUpdate(sql);

**/
/**
             String sql = "Insert INTO simplyhealthy.nurse_salaryhistory (nursesalary_id, nurse_id, nursesalary_start, nursesalary_end, nursesalary_amount)"+
            "VALUES(1,1,'2023-01-01','2023-12-31',50000.00),"+
            "(2,2,'2023-01-01','2023-12-31',55000.00),"+
            "(3,3,'2023-01-01','2023-12-31',60000.00),"+
            "(4,4,'2023-01-01','2023-12-31',47000.00),"+
            "(5,5,'2023-01-01','2023-12-31',53500.25),"+
            "(6,6,'2023-01-01','2023-12-31',58000.50),"+
            "(7,7,'2023-01-01','2023-12-31',52250.00),"+
            "(8,8,'2023-01-01','2023-12-31',63000.00),"+
            "(9,9,'2023-01-01','2023-12-31',65000.00),"+
            "(10,10,'2023-01-01','2023-12-31',53000.75),"+
            "(11,11,'2023-01-01','2023-12-31',57000.00),"+
            "(12,12,'2023-01-01','2023-12-31',70000.00),"+
            "(13,13,'2023-01-01','2023-12-31',46000.00),"+
            "(14,14,'2023-01-01','2023-12-31',54000.00),"+
            "(15,15,'2023-01-01','2023-12-31',59000.00),"+
            "(16,16,'2023-01-01','2023-12-31',49000.00),"+
            "(17,17,'2023-01-01','2023-12-31',58000.00),"+
            "(18,18,'2023-01-01','2023-12-31',57250.50),"+
            "(19,19,'2023-01-01','2023-12-31',52500.00),"+
            "(20,20,'2023-01-01','2023-12-31',63000.00),"+
            "(21,21,'2023-01-01','2023-12-31',63000.00),"+
            "(22,22,'2023-01-01','2023-12-31',54000.00),"+
            "(23,23,'2023-01-01','2023-12-31',52500.00),"+
            "(24,24,'2023-01-01','2023-12-31',65000.00),"+
            "(25,25,'2023-01-01','2023-12-31',48000.00),"+
            "(26,26,'2023-01-01','2023-12-31',50000.00),"+
            "(27,27,'2023-01-01','2023-12-31',57000.00),"+
            "(28,28,'2023-01-01','2023-12-31',50000.00),"+
            "(29,29,'2023-01-01','2023-12-31',65000.00),"+
            "(30,30,'2023-01-01','2023-12-31',67000.00),"+
            "(31,1,'2024-01-01','2024-12-31',53000.00),"+
            "(32,2,'2024-01-01','2024-12-31',59000.00),"+
            "(33,3,'2024-01-01','2024-12-31',62500.00),"+
            "(34,4,'2024-01-01','2024-12-31',50000.00),"+
            "(35,5,'2024-01-01','2024-12-31',56000.25),"+
            "(36,6,'2024-01-01','2024-12-31',61000.50),"+
            "(37,7,'2024-01-01','2024-12-31',55250.00),"+
            "(38,8,'2024-01-01','2024-12-31',66000.00),"+
            "(39,9,'2024-01-01','2024-12-31',65000.00),"+
            "(40,10,'2024-01-01','2024-12-31',56000.00),"+
            "(41,11,'2024-01-01','2024-12-31',59000.00),"+
            "(42,12,'2024-01-01','2024-12-31',71000.00),"+
            "(43,13,'2024-01-01','2024-12-31',48000.00),"+
            "(44,14,'2024-01-01','2024-12-31',60000.00),"+
            "(45,15,'2024-01-01','2024-12-31',67000.00),"+
            "(46,16,'2024-01-01','2024-12-31',56000.00),"+
            "(47,17,'2024-01-01','2024-12-31',58500.00),"+
            "(48,18,'2024-01-01','2024-12-31',64750.00),"+
            "(49,19,'2024-01-01','2024-12-31',55500.00),"+
            "(50,20,'2024-01-01','2024-12-31',70000.00),"+
            "(51,21,'2024-01-01','2024-12-31',68000.00),"+
            "(52,22,'2024-01-01','2024-12-31',58000.00),"+
            "(53,23,'2024-01-01','2024-12-31',59000.00),"+
            "(54,24,'2024-01-01','2024-12-31',65500.00),"+
            "(55,25,'2024-01-01','2024-12-31',52000.00),"+
            "(56,26,'2024-01-01','2024-12-31',55000.00),"+
            "(57,27,'2024-01-01','2024-12-31',60000.00),"+
            "(58,28,'2024-01-01','2024-12-31',54500.00),"+
            "(59,29,'2024-01-01','2024-12-31',70000.00),"+
            "(60,30,'2024-01-01','2024-12-31',70000.00)";
             stmt.executeUpdate(sql);
 **/
/**
            String sql = "Insert INTO simplyhealthy.order (order_id, patient_id, medicine_id, order_quantity)"+
                    "values(1,1,7,3),"+
                    "(2,2,4,5),"+
                    "(3,4,4,1),"+
                    "(4,4,7,2.5),"+
                    "(5,7,4,2),"+
                    "(6,8,1,3)";
            stmt.executeUpdate(sql);
 **/
/**
             String sql = "Insert INTO simplyhealthy.doctor_salaryhistory (doctorsalary_id, doctor_id, doctorsalary_start, doctorsalary_end, doctorsalary_amount)"+
            "VALUES(1,1,'2023-01-01','2023-12-31',135000.00),"+
            "(2,2,'2023-01-01','2023-12-31',120000.00),"+
            "(3,3,'2023-01-01','2023-12-31',137000.00),"+
            "(4,4,'2023-01-01','2023-12-31',160000.00),"+
            "(5,5,'2023-01-01','2023-12-31',123000.25),"+
            "(6,6,'2023-01-01','2023-12-31',120000.00),"+
            "(7,7,'2023-01-01','2023-12-31',150000.50),"+
            "(8,9,'2023-01-01','2023-12-31', 135000.00),"+
            "(9,10,'2023-01-01','2023-12-31',140000.00),"+
            "(10,11,'2023-01-01','2023-12-31',130000.00),"+
            "(11,1,'2024-01-01','2024-12-31',143000.00),"+
            "(12,2,'2024-01-01','2024-12-31',130000.00),"+
            "(13,3,'2024-01-01','2024-12-31',145000.00),"+
            "(14,4,'2024-01-01','2024-12-31',165000.00),"+
            "(15,5,'2024-01-01','2024-12-31',130000.00),"+
            "(16,6,'2024-01-01','2024-12-31',128000.00),"+
            "(17,7,'2024-01-01','2024-12-31',135000.00),"+
            "(18,9,'2024-01-01','2024-12-31',175000.00),"+
            "(19,10,'2024-01-01','2024-12-31',140000.00),"+
            "(20,11,'2024-01-01','2024-12-31',150000.00)";
             stmt.executeUpdate(sql);
 **/

/**
             String sql = "Insert INTO simplyhealthy.patient (patient_id, patient_fname, patient_lname, patient_phone, patient_gender)"+
            "VALUES(1,'Merilyn','Gerri','7988-544-3232','Female'),"+
            "(2,'Jeffery','Kelan','7988-333-2154','Male'),"+
            "(3,'Gallagher', 'Colby','7988-173-4727','Female'),"+
            "(4,'Zanna','Rebeckah','7988-319-3728','Female'),"+
            "(5,'Landen','Cassandra','7988-941-9645','Male'),"+
            "(6,'Kelsey','Kimber','7988-563-2858','Female'),"+
            "(7,'Ursula','Jamison','7988-322-1231','NULL'),"+
            "(8,'Jaylyn','Dionne','7988-002-9706','Male'),"+
            "(9,'Greer','Jenessa','7988-355-9043','Male'),"+
            "(10,'Austin','Tetty','7988-130-5450','Male')";
        stmt.executeUpdate(sql);
 **/
/**
             String sql = "Insert INTO simplyhealthy.checkIn (Checkin_id, doctor_id, patient_id, checkin_time, checkin_date)"+
            "VALUES(1,6,1,'9:30 am','2023-01-01'),"+
            "(2,4,2,'9:45 am','2023-01-01'),"+
            "(3,5,3,'9:55 am','2023-01-01'),"+
            "(4,1,4,'10:10 am','2023-01-01'),"+
            "(5,6,5,'10:30 am','2023-01-01'),"+
            "(6,7,6,'10:35 am','2023-01-01'),"+
            "(7,5,7,'10:50 am','2023-01-01'),"+
            "(8,4,8,'11:30 am','2023-01-01'),"+
            "(9,1,9,'11:30 am','2023-01-01'),"+
            "(10,7,10,'11:35 am','2023-01-01')";
             stmt.executeUpdate(sql);
 **/

/**
        String sql = "Insert Into simplyhealthy.medicine (medicine_id, medicine_name, medicine_description, medicine_treatment)"+
                "values (1, 'Omeprazole', 'Proton pump inhibitor', 'Heartburn, Peptic Ucer , Heliobacter Pylori Infection'),"+
                "(2, 'Amlodipine', 'Calcium channel blockers', 'High Blood Pressure, chronic Stable Angina'),"+
                " (3, 'Metoprolol', 'Beta blockers', 'Chronic Stable Angina,Heart Failure, Heart Attack'),"+
                " (4, 'Metformin', 'Lowers sugar production in the liver', 'Diabetes'),"+
                " (5, 'Atorvastatin', 'Blocks enzymes in the liver', 'High Cholesterol'),"+
                "(6, 'Amoxicillin', 'Penicillin antibiotic', 'Bacterial Infections'),"+
                "(7, 'Cetirizine', 'Blocks actions of the histamine', 'Allergy Symptoms'),"+
                "(8, 'Benzonatate', 'Non-Narcotic number' ,'Cough')";
                stmt.executeUpdate(sql);
 **/

            System.out.println("Inserted record successfully");
        }


        catch (SQLException e) {
            e.printStackTrace();
        }
        Tab tab = new Tab("Tab");
        Tab tab1 = new Tab("Tab 1");
        Tab tab2 = new Tab("Tab 2");
        Tab tab3 = new Tab("tab 3");
        Tab tab4 = new Tab("tab 4");
        Tab tab5 = new Tab("tab 5");
        Tab tab6 = new Tab("tab 6");
        Tab tab7 = new Tab("tab 7");
        Tab tab8 = new Tab("tab 8");

        BorderPane borderPane = new BorderPane();
        BorderPane borderPane1 = new BorderPane();
        BorderPane borderPane2 = new BorderPane();
        BorderPane borderPane3 = new BorderPane();
        BorderPane borderPane4 = new BorderPane();
        BorderPane borderPane5 = new BorderPane();
        BorderPane borderPane6 = new BorderPane();
        BorderPane borderPane7 = new BorderPane();
        BorderPane borderPane8 = new BorderPane();



        tab2.setContent(borderPane2);
        tab3.setContent(borderPane3);
        tab4.setContent(borderPane4);
        tab5.setContent(borderPane5);
        tab6.setContent(borderPane6);
        tab7.setContent(borderPane7);
        tab8.setContent(borderPane8);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(tab,tab1,tab2,tab4,tab5,tab6,tab7,tab8,tab3);
        MyVBox myVBox = new MyVBox();

        Region spacer = new Region();
        Region spacer1 = new Region();
        Region spacer2 = new Region();
        Region spacer3 = new Region();
        Region spacer4 = new Region();
        Region spacer5 = new Region();
        Region spacer6 = new Region();
        Region spacer7 = new Region();
        Region spacer8 = new Region();
        Region spacer9 = new Region();
        Region spacer10 = new Region();
        Region spacer11 = new Region();
        Region spacer12 = new Region();
        Region spacer13 = new Region();
        Region spacer14 = new Region();
        Region spacer15 = new Region();
        Region spacer16 = new Region();
        Region spacer17 = new Region();
        Region spacer18 = new Region();
        Region spacer19 = new Region();
        Region spacer20 = new Region();
        Region spacer21 = new Region();
        Region spacer22 = new Region();
        Region spacer23 = new Region();
        Region spacer24 = new Region();
        Region spacer25 = new Region();
        Region spacer26 = new Region();
        Region spacer27 = new Region();
        Region spacer28 = new Region();
        Region spacer29 = new Region();
        Region spacer30 = new Region();
        Region spacer31 = new Region();
        spacer.setMinHeight(20);
        spacer1.setMinHeight(20);
        spacer2.setMinHeight(20);
        spacer3.setMinHeight(5);
        spacer4.setMinHeight(5);
        spacer5.setMinHeight(5);
        spacer6.setMinHeight(20);
        spacer7.setMinHeight(5);
        spacer8.setMinHeight(20);
        spacer9.setMinHeight(5);
        spacer10.setMinHeight(20);
        spacer11.setMinHeight(20);
        spacer12.setMinHeight(5);
        spacer13.setMinHeight(5);
        spacer14.setMinHeight(20);
        spacer15.setMinHeight(5);
        spacer16.setMinHeight(20);
        spacer17.setMinHeight(5);
        spacer18.setMinHeight(20);
        spacer19.setMinHeight(5);
        spacer20.setMinHeight(20);
        spacer21.setMinHeight(5);
        spacer22.setMinHeight(20);
        spacer23.setMinHeight(5);
        spacer24.setMinHeight(20);
        spacer25.setMinHeight(5);
        spacer26.setMinHeight(20);
        spacer27.setMinHeight(5);
        spacer28.setMinHeight(20);
        spacer29.setMinHeight(5);
        spacer30.setMinHeight(20);
        spacer31.setMinHeight(5);
        MyGridPane gridPane = new MyGridPane();
        gridPane.setAlignment(Pos.CENTER);
        MyText dashText = new MyText("-");
        MyText dashText1 = new MyText("-");
        gridPane.addphone(docPhoneText,dashText,docPhoneText1,dashText1,docPhoneText2);

        MyText dashText2 = new MyText("-");
        MyText dashText3 = new MyText("-");
        MyGridPane gridPane1 = new MyGridPane();
        gridPane1.addphone(patPhoneText,dashText2,patPhoneText1,dashText3,patPhoneText2);
        gridPane1.setAlignment(Pos.CENTER);

        MyGridPane updateDocphoneGridPane = new MyGridPane();
        MyText dashText14= new MyText("-");
        MyText dashText15= new MyText("-");
        updateDocphoneGridPane.addphone(updateDocPhoneText,dashText14,updateDocPhoneText1,dashText15,updateDocPhoneText2);
        updateDocphoneGridPane.setAlignment(Pos.CENTER);
        myVBox.getChildren().addAll(title,spacer, docFName, fNameText, docLname, lNameText, docPhone,gridPane,docYOE,docYOEText,docEdu,docEduText,docSpecialization,docSpecializationText,spacer3, addDoctor,showMessage,spacer2,docID,docIDText,spacer4,deleteDoctor,statusMessage,updateDocID,updateDocText,updateDocFName,updateDocFnameText,updateDocLName,updateDocLNameText,updateDocPhone,updateDocphoneGridPane, updateDocYOE,updateDocYOEText,updateDocHighestEDU,updateDocHighestEDUTEXT,updateDoctor);
        ScrollPane scrollPane = new ScrollPane(myVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);


        MyText dashText4 = new MyText("-");
        MyText dashText5 = new MyText("-");
        MyGridPane gridPane2 = new MyGridPane();

        gridPane2.addphone(nursePhoneText,dashText4,nursePhoneText1,dashText5,nursePhoneText2);
        gridPane2.setAlignment(Pos.CENTER);
        MyVBox myPatientVbox = new MyVBox();

        MyGridPane patientPhoneGridPane = new MyGridPane();
        MyText dashText16 = new MyText("-");
        MyText dashText17 = new MyText("-");
        patientPhoneGridPane.addphone(updatePatientPhoneText,dashText16,updatePatientPhoneText1,dashText17,updatePatientPhoneText2);
        patientPhoneGridPane.setAlignment(Pos.CENTER);
        myPatientVbox.getChildren().addAll(title1,spacer1,patFName,patFnameText,patLName,patLnameText,patPhone,gridPane1,patGender,patGenderText,spacer5,addPatient,displayMessage,spacer6,patID,deletePatientText,spacer7,deletePatient,delPatMessage,updatePatientId,updatePatientIdText,updatePatientFname,updatePatientFnameText,updatePatientLname,updatePatientLnameText,updatePatientPhone,patientPhoneGridPane,updatePatientGender,updatePatientGenderText,updatePatient);

        ScrollPane scrollPane1 = new ScrollPane(myPatientVbox);
        scrollPane1.setFitToWidth(true);
        scrollPane1.setFitToHeight(true);

        MyText colonText9 = new MyText(":");
        MyText colonText10 = new MyText(":");
        MyGridPane nurseStartGridPane = new MyGridPane();
        nurseStartGridPane.addTime(nurseShiftStartText,colonText9,nurseShiftStartText1);
        nurseStartGridPane.setAlignment(Pos.CENTER);

        MyGridPane nurseEndGridPane = new MyGridPane();
        nurseEndGridPane.addTime(nurseShiftEndText,colonText10,nurseShiftEndText1);
        nurseEndGridPane.setAlignment(Pos.CENTER);

        MyText title2 = new MyText("Simply Healthy Page Three");
        MyVBox nurseVbox = new MyVBox();
        nurseVbox.getChildren().addAll(title2,spacer2,nurseFname,nurseFnameText,nurseLname,nurseLnameText,shiftStart,nurseStartGridPane,nurseShiftEnd,nurseEndGridPane,nursePhone,gridPane2,nurseDoctor,nurseDoctorText,spacer9,addNurse,spacer20,delNurse,delNurseText,spacer21,deleteNurse);
        borderPane2.setTop(nurseVbox);

        MyGridPane gridPane3 = new MyGridPane();
        MyGridPane gridPane4 = new MyGridPane();
        gridPane3.addFour(getDoctors,getPatients,getNurses,getNurseSalaries);
        gridPane4.addFour(getDoctorSalaries,getCheckIns,getMedicine,getOrders);
        gridPane3.setAlignment(Pos.CENTER);
        gridPane4.setAlignment(Pos.CENTER);

        MyVBox getterVbox = new MyVBox();

        textArea.setMinHeight(500);
        getterVbox.getChildren().addAll(gridPane3,gridPane4,textArea);

        borderPane3.setTop(getterVbox);


        MyVBox doctorSalaryVBox = new MyVBox();
        MyGridPane gridPane5 = new MyGridPane();
        MyGridPane gridPane6 = new MyGridPane();
        gridPane5.setAlignment(Pos.CENTER);
        gridPane6.setAlignment(Pos.CENTER);
        MyText colonText = new MyText("-");
        MyText colonText1 = new MyText("-");
        MyText colonText2 = new MyText("-");
        MyText colonText3 = new MyText("-");


        gridPane5.addphone(docSalaryStartText,colonText,docSalaryStartText1,colonText1,docSalaryStartText2);
        gridPane6.addphone(docSalaryEndText,colonText2,docSalaryEndText1,colonText3,docSalaryEndText2);
        doctorSalaryVBox.getChildren().addAll(title3,spacer10,docDocSalaryId,docDocSalaryIdText,docSalaryStart,gridPane5,docSalaryEnd,gridPane6,docSalaryAmount,docSalaryAmountText,spacer13,addDoctorSalary,spacer22,delDocSalary,delDocSalaryText,spacer23,deleteDoctorSalary);
        borderPane4.setTop(doctorSalaryVBox);
        MyText colonText4 = new MyText("-");
        MyText colonText5 = new MyText("-");
        MyText colonText6 = new MyText("-");
        MyText colonText7 = new MyText("-");
        MyGridPane gridPane7 = new MyGridPane();
        MyGridPane gridPane8 = new MyGridPane();
        gridPane7.addphone(nurSalaryStartText,colonText4,nurSalaryStartText1,colonText5,nurSalaryStartText2);
        gridPane8.addphone(nurseSalaryEndText,colonText6,nurseSalaryEndText1,colonText7,nurseSalaryEndText2);
        gridPane7.setAlignment(Pos.CENTER);
        gridPane8.setAlignment(Pos.CENTER);
        MyVBox myNurseVBox = new MyVBox();
        myNurseVBox.getChildren().addAll(title4,spacer11,nurSalaryHistory,nurSalaryHistoryText,nurSalaryStart,gridPane7,nurSalaryEnd,gridPane8,nurSalaryAmount,nurSalaryAmountText,spacer12,addNurseSalary,spacer24,delNurSalary,delNurSalaryText,spacer25,deleteNurseSalary);
        borderPane5.setTop(myNurseVBox);

        MyVBox myMedicineVbox = new MyVBox();
        myMedicineVbox.getChildren().addAll(title5,spacer14,medicineName,medicineNameText,medicineDescription,medicineDescriptionText,medicineTreatment,medicineTreatmentText,spacer15,addMedicine,spacer26,delMedicine,delMedicineText,spacer27,deleteMedicine);
        borderPane6.setTop(myMedicineVbox);

        MyVBox myCheckInVBox = new MyVBox();
        MyGridPane checkInTimeGridPane = new MyGridPane();
        MyText colonText11 = new MyText(":");
        MyText dashText12 = new MyText("-");
        MyText dashText13 = new MyText("-");
        checkInTimeGridPane.addTime(checkInTimeText,colonText11, checkInTImeText1);
        checkInTimeGridPane.setAlignment(Pos.CENTER);

        MyGridPane checkInDateGridPane = new MyGridPane();
        checkInDateGridPane.setAlignment(Pos.CENTER);
        checkInDateGridPane.addphone(checkInDateText,dashText12,checkInDateText1,dashText13,checkInDateText2);
        myCheckInVBox.getChildren().addAll(title6,spacer16,checkInDoc,checkInDocText,checkInPat,checkInPatText,checkInTime,checkInTimeGridPane,checkInDate,checkInDateGridPane,spacer17,addCheckIn,spacer28,delCheckIn,delCheckInText,spacer29,deleteCheckin);
        borderPane7.setTop(myCheckInVBox);

        MyVBox myOrderVBox = new MyVBox();
        myOrderVBox.getChildren().addAll(title7,spacer18,orderPatientID,orderPatientIDText,orderMedicineID,orderMedicineIDText,orderAmount,orderAmountText,spacer19,addOrder,spacer30, delOrder,delOrderText,spacer31,deleteOrder);
        borderPane8.setTop(myOrderVBox);
        tab.setContent(scrollPane);
        tab1.setContent(scrollPane1);

        Scene scene = new Scene( tabPane, 800, 750);






        primaryStage.setTitle("SQL Query Button");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
