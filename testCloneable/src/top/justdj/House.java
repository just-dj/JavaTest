/*
  Created by IntelliJ IDEA.
  User: 霜
  Date: 18.3.30
  Time: 14:55
*/

package top.justdj;

import java.lang.reflect.Field;

public class House implements Cloneable ,Comparable<Object>{
	
	String name;
	private String info;
	
	
	public House() {
		super();
	}
	
	public House(String name,String info) {
		this.name = name;
		this.info = info;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.name == ( (House)obj).name;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}
	
	@Override
	public int compareTo(Object o) {
		return 0;
	}
	
	@Override
	public String toString() {
		StringBuffer sb =new StringBuffer();
		Field[] fields = this.getClass().getDeclaredFields();
		
		for (Field a:fields){
			try{
				sb.append(a.getName() + " : " + a.get(this) + "\n");
			}catch (IllegalAccessException e){
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
