package application.model;

public class Account {
	private int id;
	private String accountName;
	private String accountUri;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(String accountName, String accountUri) {
		super();
		this.accountName = accountName;
		this.accountUri = accountUri;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountUri() {
		return accountUri;
	}

	public void setAccountUri(String accountUri) {
		this.accountUri = accountUri;
	}
	
}
