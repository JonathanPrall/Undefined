package cs.dal.csci3130.undined.domain;

import org.apache.commons.beanutils.BeanUtils;

public class Admin extends User {
	
	public Admin() {
		this.setRole("admin");
	}
	public Admin(Long id, String password, String role, String firstName, String lastName, String email, String phone) {
		super(id, password, role, firstName, lastName, email, phone);
	}
	
	@Override
	public Admin clone() throws CloneNotSupportedException {
		try {
			return (Admin) BeanUtils.cloneBean(this);
		} catch (Exception ex) {
			throw new CloneNotSupportedException();
		}
	}
}
