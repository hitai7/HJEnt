package com.hyo.smart.domain;

import java.util.Arrays;

public class ButtonDTO {

	private String type;
	private String[] buttons;
	
	public ButtonDTO(String[] buttons) {
		this.type = "buttons"; // Fixed value.
		this.buttons = buttons;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String[] getButtons() {
		return buttons;
	}
	public void setButtons(String[] buttons) {
		this.buttons = buttons;
	}	
	
	@Override
	public String toString() {
		return "ButtonDTO [type=" + type + ", buttons=" + Arrays.toString(buttons) + "]";
	}
}
