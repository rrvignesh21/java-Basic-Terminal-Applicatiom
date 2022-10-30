import java.util.*;

class PersonDetails{
	protected String name;
	protected int age;
	protected String address;
	protected String phoneNumber;
	void setName(String name){
		this.name = name;
	}
	public void setAge(int age){
		this.age = age;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setphoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	public String getName(){
		return this.name;
	}
	public int getAge(){
		return this.age;
	}
	public String getAddress(){
		return this.address;
	}
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
}

class Patient extends PersonDetails{
	int id;
	String disease;
	String symptoms;
	String prescription;
	String treatment;
	int doctorId;
	Patient(){
		this.treatment = "";
		this.prescription = "";
	}
	public void setId(int id){
		this.id = id;
	}
	public void setDisease(String disease){
		this.disease = disease;
	}
	public void setSymptoms(String symptoms){
		this.symptoms = symptoms;
	}
	public void setTreatment(String treatment){
		this.treatment = this.treatment + "\n" + treatment;	
	}
	public void setPrescription(String prescription){
		this.prescription = this.prescription+"\n" + prescription;
	}
	public void setDoctorId(int id){
		this.doctorId = id;
	}
	public int getId(){
		return this.id;
	}
	public String getDisease(){
		return this.disease;
	}
	public String getSymptoms(){
		return this.symptoms;
	}
	public String getTreatment(){
		return this.treatment;	
	}
	public String getPrescription(){
		return this.prescription;
	}
	public int getDoctorId(){
		return this.doctorId;
	}		
	public void displayPatientDetail(){
		System.out.println("Patient id : " + this.id+"\tPatient Name : " + this.name+"\nPatient Age : "+this.age+"\tPatient Phone Number : "+this.phoneNumber + "\nPatient Address : "+this.address+"\n");
		System.out.println("Disease : "+this.disease + "\tSymptoms : "+this.symptoms + "\nPrescription : " + this.prescription +"\ntreatment : "+ this.treatment+"\n");
	}
}

class Doctor extends PersonDetails{
	int id;
	String speciality;
	int availability[] = new int[2];
	int patientCount;
	public void setId(int id){
		this.id = id;
	}
	public void setSpeciality(String speciality){
		this.speciality = speciality;
	}	
	public void setAvailability(int from,int to){
		this.availability[0] = from;
		this.availability[1] = to;		
	}
	public int getId(){
		return this.id;
	}
	public void setpatientCount(){
		patientCount++;
	} 
	public int getpatientCount(){
		return patientCount;
	} 
	public String getSpecality(){
		return this.speciality;
	}
	public int availibleFrom(){
		return this.availability[0];
	}
	public int availibleTo(){
		return this.availability[1];
	}
	public void displayDoctorDetail(){
		System.out.println(this.id+"\tDoctor Name : " + this.name+"\tDoctor Speciality : "+this.speciality+"\tFrom : "+
		this.availability[0] +"\tTo : "+this.availability[1]+"\n");
	} 
}


class HospitalManagement{
		private ArrayList<Doctor> doctorlist = new ArrayList<Doctor> ();
		private ArrayList<Patient> patientRecord = new ArrayList<Patient>();

		public void addNewPatient(Patient newPatient){
			patientRecord.add(newPatient);
		}

		public boolean setpatientDetails(){
			Scanner scan = new Scanner(System.in);
			String patientName;
			int patientAge;
			String patientAddress;
			String patientPhoneNumber;
			String symptoms;
			Patient newPatient = new Patient();
			newPatient.setId(this.patientRecord.size() + 1);
			System.out.println("Enter Patient Name : ");
			patientName = scan.nextLine();
			newPatient.setName(patientName);
			System.out.println("Enter Patient Age : ");
			patientAge = scan.nextInt();
			newPatient.setAge(patientAge);
			scan.nextLine();
			System.out.println("Enter Patient Address : ");
			patientAddress = scan.nextLine();			
			newPatient.setAddress(patientAddress);
			System.out.println("Enter Patient phone Number : ");
			patientPhoneNumber = scan.nextLine();
			if(this.checkPhoneNumber(patientPhoneNumber)){
				newPatient.setphoneNumber(patientPhoneNumber);
			}
			else{
				System.out.println("Invalid Phone Number");
				return false;
			}
			//newPatient.setphoneNumber(patientPhoneNumber);
			System.out.println("Enter Patient Symptoms : ");
			symptoms = scan.nextLine();
			newPatient.setSymptoms(symptoms);
			this.addNewPatient(newPatient);					
			return true;
		}		

		public void displayAllPatient(){
			for(Patient temp : patientRecord){
				temp.displayPatientDetail();
			}
		}

		public void addDoctor(){
			Scanner scan = new Scanner(System.in);
			String doctorName;
			int doctorAge;
			String doctorAddress;
			String doctorPhoneNumber;
			Doctor doctor = new Doctor();
			doctor.setId(this.doctorlist.size() + 1);
			System.out.println("Enter Doctor Name : ");
			doctorName = scan.nextLine();
			doctor.setName(doctorName);
			System.out.println("Enter Doctor Age : ");
			doctorAge = scan.nextInt();
			doctor.setAge(doctorAge);
			scan.nextLine();
			System.out.println("Enter Patient Address : ");
			doctorAddress = scan.nextLine();			
			doctor.setAddress(doctorAddress);
			System.out.println("Enter Doctor phone Number : ");
			doctorPhoneNumber = scan.nextLine();
			if(this.checkPhoneNumber(doctorPhoneNumber)){
			doctor.setphoneNumber(doctorPhoneNumber);
			}
			else{
				System.out.println("Invalid Phone Number");
			}
			System.out.println("Enter Doctor's specality : ");
			String specality = scan.nextLine();
			doctor.setSpeciality(specality);
			System.out.println("Enter Doctor's avalibilty[1.Day/2.evening/3.Night] : ");
			int choice = scan.nextInt();
			if(choice == 1){
				doctor.setAvailability(6,16);
			}
			else if(choice == 2){
				doctor.setAvailability(16,22);
			}
			else if(choice == 3){
				doctor.setAvailability(22,6);
			}
			doctorlist.add(doctor);				
		}

		public void displayAllDoctors(){
			for(Doctor temp : doctorlist){
				temp.displayDoctorDetail();
			}
		}

		public boolean setAppointment(int id,int time,int patientId){
			Doctor currentDoctor = new Doctor();
			currentDoctor = doctorlist.get(id-1);
			if(currentDoctor.getpatientCount() == 20){
				System.out.println("Enough Patient For Today");
				return false;
			}			
			if(currentDoctor.availibleFrom() <= time && currentDoctor.availibleTo() >= time){
				doctorlist.get(id-1).setpatientCount();	
				patientRecord.get(patientId - 1).setDoctorId(id);
				return true;
			}
			else{
				System.out.println("This Doctor is Not Availaible at given time");	
				return false;
			}
		}

		public boolean checkPhoneNumber(String phoneNumber){
			if(phoneNumber.length() != 10){
				return false;
			}
			for(int i = 0;i<phoneNumber.length();i++){
				if(phoneNumber.charAt(i) >= 48 && phoneNumber.charAt(i) <= 57){
					continue;			
				}
				else{
					return false;
				}
			}
			return true;
		}
 
		public boolean patientLoginCheck(int id,String phonenumber){
			if(id > patientRecord.size()){
				return false;
			}
			else{
				if(phonenumber.equals(patientRecord.get(id-1).getPhoneNumber())){
					return true;
				}
				else{
					return false;
				}
			}
		}

		public boolean doctorLoginCheck(int id,String phonenumber){
			if(id > doctorlist.size()){
				return false;
			}
			else{
				if(phonenumber.equals(doctorlist.get(id-1).getPhoneNumber())){
					return true;
				}
				else{
					return false;
				}
			}
		}

		public void specificDoctorsPatient(int id){
			for(Patient temp : patientRecord){
				if(temp.getDoctorId() == id){
					temp.displayPatientDetail();
				}
			}
		}

		public void updateMedicalDetailOfPatient(int patientid,String disease,String symptoms,String prescription,String treatment){
			patientRecord.get(patientid - 1).setDisease(disease);
			patientRecord.get(patientid - 1).setSymptoms(symptoms);
			patientRecord.get(patientid - 1).setPrescription(prescription);
			patientRecord.get(patientid - 1).setTreatment(treatment);
		}
	
		public void getSpecificPatientDetails(int patientId){
			patientRecord.get(patientId - 1).displayPatientDetail();
		}
}


class Q2{

	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		HospitalManagement hospitalmanage = new HospitalManagement();
		boolean flag = true;
		while(flag){
		System.out.println("1.New Patient\n2.Already regitered\n3.Admin\n4.Doctor\n5.Exit");
		int option = scan.nextInt();

		if(option == 1){
			if(hospitalmanage.setpatientDetails()){
				System.out.println("Registerd Successfully");
			}			
			
		}
		else if(option == 2){
			System.out.println("Enter Id : ");
			int patientId = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter PhoneNumber : ");
			String patientphoneNumber = scan.nextLine();
			if(hospitalmanage.patientLoginCheck(patientId,patientphoneNumber)){
				boolean loginzone = true;
				while(loginzone){
				System.out.println("1.Book a Appointment\n2.Display details\n3.logout");
				int suboption = scan.nextInt();
				if(suboption == 1){
				hospitalmanage.displayAllDoctors();
				int option1 = scan.nextInt();
				System.out.println("Enter visiting time : ");
				int time = scan.nextInt();
					if(hospitalmanage.setAppointment(option1,time,patientId)){	
						System.out.println("Appointment is Successfull");					
					}
			
					else{
						System.out.println("Appointment is unsuccessfull");
					}
				}
				else if(suboption == 2){
					hospitalmanage.getSpecificPatientDetails(patientId);
				}
				else if(suboption == 3){
					loginzone = false;
				}
			}
		}
			else{

				System.out.println("Invalid login.Try again");
			}	
		}
		else if(option == 3){
			System.out.println("1.Add Doctor\n2.Display All Patient Details\n");
			int suboption2 = scan.nextInt();
			if(suboption2 == 1){
				System.out.println("Add New Doctor");
				hospitalmanage.addDoctor();
			}
			else if(suboption2 == 2){
				hospitalmanage.displayAllPatient();	
			}
		}
		else if(option == 4){
			System.out.println("Enter Id : ");
			int doctorId = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter PhoneNumber : ");
			String doctorphoneNumber = scan.nextLine();
			if(hospitalmanage.doctorLoginCheck(doctorId,doctorphoneNumber)){
				System.out.println("Your Patients ");
				hospitalmanage.specificDoctorsPatient(doctorId);
				System.out.println("Enter patient id you want to update medical details : ");
				int patientid = scan.nextInt();
				scan.nextLine();
				System.out.println("Enter patient symptoms : ");
	 			String symptoms = scan.nextLine();
				System.out.println("Enter patient disease : ");
				String disease = scan.nextLine();	
				System.out.println("Enter patient prescription : ");
	 			String prescription = scan.nextLine();
				System.out.println("Enter patient treatments : ");
				String treatment = scan.nextLine();		
				hospitalmanage.updateMedicalDetailOfPatient(patientid,disease,symptoms,prescription,treatment);
			}
			else{

				System.out.println("Invalid login.Try again");
			}	
		}
		else if(option == 5){
			flag = false;
		}
		else{
			flag = false;
		}
	}
	}
}