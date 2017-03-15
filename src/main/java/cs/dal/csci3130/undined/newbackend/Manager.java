package cs.dal.csci3130.undined.newbackend;

public class Manager extends User {
	
	String R_ID;

	public Manager() {
		this.setRole("manager");
	}
	public String getR_ID() {
		return R_ID;
	}

	public void setR_ID(String r_ID) {
		R_ID = r_ID;
	}
	
}
