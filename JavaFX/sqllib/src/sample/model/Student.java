package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class Student {
	private SimpleStringProperty id ;
	private SimpleStringProperty name;
	private SimpleStringProperty sex;
	private SimpleStringProperty major;
	private SimpleStringProperty cla;
	private SimpleStringProperty telephone;
	private SimpleStringProperty state;
	private  SimpleStringProperty instructor;
	
	public Student(String id, String name, String sex, String major, String cla, String telephone, String state, String instructor) {
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.sex = new SimpleStringProperty(sex);
		this.major = new SimpleStringProperty(major);
		this.cla = new SimpleStringProperty(cla);
		this.telephone = new SimpleStringProperty(telephone);
		this.state = new SimpleStringProperty(state);
		this.instructor = new SimpleStringProperty(instructor);
	}
	
	public String getId() {
		return id.get();
	}
	
	public SimpleStringProperty idProperty() {
		return id;
	}
	
	public void setId(String id) {
		this.id.set(id);
	}
	
	public String getName() {
		return name.get();
	}
	
	public SimpleStringProperty nameProperty() {
		return name;
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getSex() {
		return sex.get();
	}
	
	public SimpleStringProperty sexProperty() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex.set(sex);
	}
	
	public String getmajor() {
		return major.get();
	}
	
	public SimpleStringProperty majorProperty() {
		return major;
	}
	
	public void setmajor(String major) {
		this.major.set(major);
	}
	
	public String getCla() {
		return cla.get();
	}
	
	public SimpleStringProperty claProperty() {
		return cla;
	}
	
	public void setCla(String cla) {
		this.cla.set(cla);
	}
	
	public String getTelephone() {
		return telephone.get();
	}
	
	public SimpleStringProperty telephoneProperty() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone.set(telephone);
	}
	
	public String getState() {
		return state.get();
	}
	
	public SimpleStringProperty stateProperty() {
		return state;
	}
	
	public void setState(String state) {
		this.state.set(state);
	}
	
	public String getInstructor() {
		return instructor.get();
	}
	
	public SimpleStringProperty instructorProperty() {
		return instructor;
	}
	
	public void setInstructor(String instructor) {
		this.instructor.set(instructor);
	}
}
