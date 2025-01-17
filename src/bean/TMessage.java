package bean;

/**
 * 
 * data 存放 数据库查询出来的数据
 * 
 * @param <T>
 * 
 */

public class TMessage<T> {

	private int code;

	private String message;

	private T data;

	public int getCode() {

		return code;

	}

	public void setCode(int code) {

		this.code = code;

	}

	public String getMessage() {

		return message;

	}

	public void setMessage(String message) {

		this.message = message;

	}

	public T getData() {

		return data;

	}

	public void setData(T data) {

		this.data = data;

	}

}
