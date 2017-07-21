package cn.zch.ssm.exception;

/**
 * @author zhangchenghao
 *
 */
public class BusinessException extends Exception {

	private String msg;

	
	public BusinessException() {
		super();
	}
	public BusinessException(String msg) {
		super();
		this.msg = msg;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
