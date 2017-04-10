package cs.dal.csci3130.undined.backend;

import org.apache.commons.beanutils.BeanUtils;

public class Manager extends User {
	
	String R_ID;
	
	public Manager() {
		this.setRole("manager");
	}
	
	public Manager(Long id, String password, String role, String firstName, String lastName, String email, String phone, String R_ID) {
		super(id, password, role, firstName, lastName, email, phone);
		this.R_ID = R_ID;
	}
	
	public String getR_ID() {
		return R_ID;
	}

	public void setR_ID(String r_ID) {
		R_ID = r_ID;
	}
	
	@Override
	public Manager clone() throws CloneNotSupportedException {
		try {
			return (Manager) BeanUtils.cloneBean(this);
		} catch (Exception ex) {
			throw new CloneNotSupportedException();
		}
	}
}
