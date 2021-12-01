package objects;

import javafx.scene.control.Button;
import main.model.Request;

public class RequestButton extends Button {
	private Request r;
	
	public RequestButton(Request r) {
		this.r = r;
	}
	
	public Request getRequest() {
		return r;
	}
	
	public void setRequest(Request r) {
		this.r = r;
	}
}
