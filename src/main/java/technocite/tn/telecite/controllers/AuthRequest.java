package technocite.tn.telecite.controllers;

import lombok.Data;

@Data
public class AuthRequest {
    public AuthRequest() {
		super();
	}
	public AuthRequest(String password, String email) {
		super();
		this.password = password;
		this.email = email;
	}
	private String password;
    private String email;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "AuthRequest [password=" + password + ", email=" + email + "]";
	}
	
    
}
