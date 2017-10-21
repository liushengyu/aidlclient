package com.example.newleaning;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

	private String name;
	private int age;
	
	public Student(){}
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int describeContents() {
		
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(name);
		arg0.writeInt(age);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	public static final Parcelable.Creator<Student> CREATOR =new Creator<Student>() {
		
		@Override
		public Student[] newArray(int arg0) {
			
			
			return new Student[arg0];
		}
		
		@Override
		public Student createFromParcel(Parcel arg0) {
			String name=arg0.readString();
			int age=arg0.readInt();
			return new Student(name,age);
		}
	};
}
