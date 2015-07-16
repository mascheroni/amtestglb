package com.almundo.automation.entities;

/**
 * 
 * @author zenen.morales
 *
 */
public class Value {

	private int quantity;
	private String code;
	private String name;

	public Value(int quantity, String code, String name) {
		super();
		this.quantity = quantity;
		this.code = code;
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
