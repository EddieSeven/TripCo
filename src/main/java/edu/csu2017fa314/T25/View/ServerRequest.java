package edu.csu2017fa314.T25.View;

public class ServerRequest {
    private String request = "";
    private String description = "";
	private int optimization = 0;
	private boolean miles = true;

    public ServerRequest(String request, String description, int opt, boolean m) {
        this.request = request;
        this.description = description;
		optimization = opt;
		miles = m;
    }

    public String getRequest() {
        return request;
    }

    public void setQuery(String request) {
        this.request = request;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public int getOptimization() {
		return optimization;
	}

	public void setOptimization(int opt) {
		optimization = opt;
	}

	public boolean getMiles() {
		return miles;
	}

	public void setMiles(boolean m) {
		miles = m;
	}
	
    @Override
    public String toString() {
        return "Request{" +
                "request='" + request + '\'' +
                ", description='" + description + '\'' +
				", miles='" + miles + '\'' + 
				", optimization='" + optimization + '\'' +
                '}';
    }

}

