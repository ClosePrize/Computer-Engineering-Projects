package BankProjectNYP;

public interface Person {
	// Kullanıcı giriş yaparken id veya username girebilsin
	void setName(String name);
	void setSurname(String surname);
	void setId(String id);
	void setUsername(String username);
	void setPassword(String newPassword);
	void changePassword(String newPassword);
}
