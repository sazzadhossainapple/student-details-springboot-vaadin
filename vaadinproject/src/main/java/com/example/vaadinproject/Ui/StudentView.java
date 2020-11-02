package com.example.vaadinproject.Ui;

import com.example.vaadinproject.Service.StudentService;
import com.example.vaadinproject.model.Student;
import com.example.vaadinproject.repository.StudentRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Route("student")
//@Theme(value = Lumo.class,variant = Lumo.DARK)
//@Theme(value = Material.class)
@Theme(value = Lumo.class)
public class StudentView extends VerticalLayout {
    private StudentService studentService;
    private Binder<Student> studentBinder;
   private Grid <Student> studentGrid;
    public  StudentView(StudentService studentService) {
        this.studentService = studentService;
      // studentBean = new Student();
       studentBinder = new Binder<>();

        TextField idField = new TextField("Student ID","Student ID 13 digit ");
        TextField nameField = new TextField("Student Name","Student Full Name");
        DatePicker datePicker = new DatePicker("Date of Birth");
        Button submitButton = new Button("Submit");


        FormLayout formLayout = new FormLayout();
        formLayout.add(idField,nameField,datePicker,submitButton);
        //studentBinder.bind(nameField,Student::getName,Student::setName);
        studentBinder
                .forField(idField)
                .asRequired()
                .withValidator(Id -> Id.length()==13,"IDs must be a 13 digits long")
                .withConverter(new StringToLongConverter("Id must be a Number"))
                .withValidator(Id -> Id > 999,"Id must be 4 digit number")
                //.bind(Student::getId,Student::setId);
                 .bind(student -> student.getId(),(student,id)-> student.setId(id));



        studentBinder
                .forField(nameField)
                .asRequired()
                .withValidator(name -> name.length() >= 3,"Name should be at least 3 letter long")
                //.withValidator(name -> name.length() <=10," Name cannot be more than 10 letter long")
                .withValidator(name -> !name.contains("_"),"Name cannot underscore")
                .bind(Student::getName,Student::setName);
        studentBinder
                .forField(datePicker)
                .asRequired()
                .withValidator(dob -> DAYS.between(dob,LocalDate.now()) >17 *365,"Students should be at least 18 years old")
                .bind(Student::getDob,Student::setDob);

        studentGrid =new Grid<>();
        studentGrid
                .addColumn(Student::getId)
                .setWidth("150px")
                .setFlexGrow(0)
                .setHeader(getStyleHeader("Student ID"));

        studentGrid
                .addColumn(Student::getName)
                .setFlexGrow(1)
                .setHeader(getStyleHeader("Student Name"));
        studentGrid
                .addColumn(Student::getDob)
                .setFlexGrow(1)
                .setHeader(getStyleHeader("Date of Birth"));

        studentGrid
                .addComponentColumn(student -> getEditCloumn(student))
                .setWidth("50px")
                .setFlexGrow(0);

        studentGrid
                .addComponentColumn(student -> getDeleteCloumn(student))
                .setWidth("50px")
                .setFlexGrow(0);

        studentGrid.setItems(studentService.findAll());

        add(formLayout,studentGrid);

        submitButton.addClickListener(event ->{
            //long id = Long.parseLong(idField.getValue());
            //String name = nameField.getValue();
            //LocalDate dateOfBirth = datePicker.getValue();

           // Student student = new Student(id,name,dateOfBirth);
            Student student = new Student();
            try {
                studentBinder.writeBean(student);
                Notification.show(student.toString());
                //System.out.println(" Days" + DAYS.between(LocalDate.now(),student.getDob()));
                Student savedStudent = studentService.save(student);
               studentGrid.setItems(studentService.findAll());
               Notification.show("Saved " + savedStudent.getName());
            } catch (ValidationException e) {
                Notification.show(e.getMessage());
                System.err.println(" Days" + DAYS.between(LocalDate.now(),student.getDob()));
                e.printStackTrace();
            }catch (Exception e)
            {
                Notification.show(e.getMessage());
            }




        });
        //studentGrid.addItemClickListener(event -> {
           //Student selectStudent = event.getItem();
           //idField.setValue("" +selectStudent.getId());
          // nameField.setValue("" +selectStudent.getName());
          // datePicker.setValue(selectStudent.getDob());

            /*try {
                studentBean = selectStudent;
                studentBinder.writeBean(selectStudent);
            } catch (ValidationException e) {
                e.printStackTrace();
            }
*/
           // studentBinder.readBean(selectStudent);
            //submitButton.setText("update");
       // });

    }

    private Component getDeleteCloumn(Student student) {
        Button button = new Button();
        //studentBinder.readBean(student);
        button.setIcon(VaadinIcon.FILE_REMOVE.create());
        button.getElement().setProperty("title","This is a delete button!");
        button.addClickListener(event -> {studentService.delete(student);
        studentGrid.setItems(studentService.findAll());});

        return button;
    }

    private Component getEditCloumn(Student student) {

        Button button = new Button();
        //studentBinder.readBean(student);
        button.setIcon(VaadinIcon.EDIT.create());
        button.addClickListener(event -> studentBinder.readBean(student));

        return button;
    }

    private Component getStyleHeader(String text) {
        H4 header = new H4(text);
        header
                .getStyle()
                //.set("background-color","blue")
                .set("border-with","3px");
        return header;
    }
}
