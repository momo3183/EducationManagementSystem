//学生个人信息类
package student;

public class Student {
	private Long stuid;//学号
	private String name;//姓名
	private String gender;//性别
	private String b;//出生日期
	private String region;//地区
	private String mz;//民族
	private String college;//学院
	private String className;//班级
	private String phone;//电话
	private String email;//邮箱


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
