package project;

import javafx.beans.property.SimpleStringProperty;

import java.util.LinkedList;
import java.util.List;

public class data {
	private SimpleStringProperty data1 = new SimpleStringProperty("");
	private SimpleStringProperty data2 = new SimpleStringProperty("");
	private SimpleStringProperty data3 = new SimpleStringProperty("");
	private SimpleStringProperty data4 = new SimpleStringProperty("");
	private SimpleStringProperty data5 = new SimpleStringProperty("");
	private SimpleStringProperty data6 = new SimpleStringProperty("");
	private SimpleStringProperty data7 = new SimpleStringProperty("");
	private SimpleStringProperty data8 = new SimpleStringProperty("");
	private SimpleStringProperty data9 = new SimpleStringProperty("");
	private SimpleStringProperty data10 = new SimpleStringProperty("");
	private SimpleStringProperty data11 = new SimpleStringProperty("");
	private SimpleStringProperty data12 = new SimpleStringProperty("");
	private SimpleStringProperty data13 = new SimpleStringProperty("");
	private SimpleStringProperty data14 = new SimpleStringProperty("");
	
	private SimpleStringProperty data15 = new SimpleStringProperty();
	
	
	private List<String> email = new LinkedList<String>();
	
	public String getData1() {
		return data1.get();
	}
	
	public SimpleStringProperty data1Property() {
		return data1;
	}
	
	public void setData1(String data1) {
		this.data1.set(data1);
	}
	
	public String getData2() {
		return data2.get();
	}
	
	public SimpleStringProperty data2Property() {
		return data2;
	}
	
	public void setData2(String data2) {
		this.data2.set(data2);
	}
	
	public String getData3() {
		return data3.get();
	}
	
	public SimpleStringProperty data3Property() {
		return data3;
	}
	
	public void setData3(String data3) {
		this.data3.set(data3);
	}
	
	public String getData4() {
		return data4.get();
	}
	
	public SimpleStringProperty data4Property() {
		return data4;
	}
	
	public void setData4(String data4) {
		this.data4.set(data4);
	}
	
	public String getData5() {
		return data5.get();
	}
	
	public SimpleStringProperty data5Property() {
		return data5;
	}
	
	public void setData5(String data5) {
		this.data5.set(data5);
	}
	
	public String getData6() {
		return data6.get();
	}
	
	public SimpleStringProperty data6Property() {
		return data6;
	}
	
	public void setData6(String data6) {
		this.data6.set(data6);
	}
	
	public String getData7() {
		return data7.get();
	}
	
	public SimpleStringProperty data7Property() {
		return data7;
	}
	
	public void setData7(String data7) {
		this.data7.set(data7);
	}
	
	public String getData8() {
		return data8.get();
	}
	
	public SimpleStringProperty data8Property() {
		return data8;
	}
	
	public void setData8(String data8) {
		this.data8.set(data8);
	}
	
	public String getData9() {
		return data9.get();
	}
	
	public SimpleStringProperty data9Property() {
		return data9;
	}
	
	public void setData9(String data9) {
		this.data9.set(data9);
	}
	
	public String getData10() {
		return data10.get();
	}
	
	public SimpleStringProperty data10Property() {
		return data10;
	}
	
	public void setData10(String data10) {
		this.data10.set(data10);
	}
	
	public String getData11() {
		return data11.get();
	}
	
	public SimpleStringProperty data11Property() {
		return data11;
	}
	
	public void setData11(String data11) {
		this.data11.set(data11);
	}
	
	public String getData12() {
		return data12.get();
	}
	
	public SimpleStringProperty data12Property() {
		return data12;
	}
	
	public void setData12(String data12) {
		this.data12.set(data12);
	}
	
	public String getData13() {
		return data13.get();
	}
	
	public SimpleStringProperty data13Property() {
		return data13;
	}
	
	public void setData13(String data13) {
		this.data13.set(data13);
	}
	
	public String getData14() {
		return data14.get();
	}
	
	public SimpleStringProperty data14Property() {
		return data14;
	}
	
	public void setData14(String data14) {
		this.data14.set(data14);
	}
	
	public String getData15() {
		return data15.get();
	}
	
	public SimpleStringProperty data15Property() {
		return data15;
	}
	
	public void setData15(String data15) {
		this.data15.set(data15);
	}
	
	
	public List<String> getEmail() {
		return email;
	}
	
	public void setEmail(List<String> email) {
		this.email = email;
	}
	
	 data(String... args ) {
		for(String temp:args)
			email.add(temp);
		set();
	}
	
	data(List<String> list) {
		for (int i = 0; i < list.size();++i)
			email.add(list.get(i));
		set();
	}
	public void setValue(String str,int n){
		switch (n){
			case 1:
				setData1(str);
				break;
			case 2:
				setData2(str);
				break;
			case 3:
				setData3(str);
				break;
			case 4:
				setData4(str);
				break;
			case 5:
				setData5(str);
				break;
			case 6:
				setData6(str);
				break;
			case 7:
				setData7(str);
				break;
			case 8:
				setData8(str);
				break;
			case 9:
				setData9(str);
				break;
			case 10:
				setData10(str);
				break;
			case 11:
				setData11(str);
				break;
			case 12:
				setData12(str);
				break;
			case 13:
				setData13(str);
				break;
			case 14:
				setData14(str);
				break;
			case 15:
				setData15(str);
				break;
				
			default:
				break;
		}
	}
	
	public String getValue(int n){
		switch (n){
			case 1:
				return getData1();
			case 2:
				return getData2();
			case 3:
				return getData3();
			case 4:
				return getData4();
			case 5:
				return getData5();
			case 6:
				return getData6();
			case 7:
				return getData7();
			case 8:
				return getData8();
			case 9:
				return getData9();
			case 10:
				return getData10();
			case 11:
				return getData11();
			case 12:
				return getData12();
			case 13:
				return getData13();
			case 14:
				return getData14();
			case 15:
				return getData15();
			default:break;
		}
		return null;
	}
	
	private void set(){
		if (!email.isEmpty())
			for(int i = 0; i <email.size(); ++i){
			setValue(email.get(i),i +1);
			}
	}
	
	@Override
	public String toString() {
		return data1.toString() + " "+ data2 + " "+ data3 + " "+ data4+ " " + data5+ " " + data6;
	}
}
