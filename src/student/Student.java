//ѧ��������Ϣ��
package student;

public class Student {
	private Long stuid;//ѧ��
	private String name;//����
	private String gender;//�Ա�
	private String b;//��������
	private String region;//����
	private String mz;//����
	private String college;//ѧԺ
	private String className;//�༶
	private String phone;//�绰
	private String email;//����


	public Student(){

	}
	public Student(Long studid, String name, String gender, String b, String region, String mz, String college, String className, String phone, String email){
		this.stuid=studid;
		this.name=name;
		this.gender=gender;
		this.b=b;
		this.region=region;
		this.mz=mz;
		this.college=college;
		this.className=className;
		this.phone=phone;
		this.email=email;

	}

	public Long getStuid() {
		return stuid;
	}

	public void setStuid(Long stuid) {
		this.stuid = stuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
