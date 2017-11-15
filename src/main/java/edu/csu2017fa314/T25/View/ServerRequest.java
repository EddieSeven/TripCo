package edu.csu2017fa314.T25.View;

import java.util.ArrayList;

public class ServerRequest {
    private String request = "";
    private String description = "";
	private ArrayList<String> idList = null;
	private int optimization = 0;
	private boolean miles = true;

    public ServerRequest(String request, String description) {
        this.request = request;
        this.description = description;
    }

	public ServerRequest(String request, ArrayList<String> ids, int opt, boolean isM) {
		this.request = request;
		this.idList = ids;
		this.optimization = opt;
		this.miles = isM;
	}

	public ArrayList<String> getIDList() {
		return idList;
	}

	public void setIDList(ArrayList<String> ids) {
		idList = ids;
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

