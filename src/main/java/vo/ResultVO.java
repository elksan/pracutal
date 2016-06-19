package vo;

import ninja.Result;

/**
 * Created by Diego on 14-06-2016.
 */
public class ResultVO {

	private String redirect;
	private Object result;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
}
