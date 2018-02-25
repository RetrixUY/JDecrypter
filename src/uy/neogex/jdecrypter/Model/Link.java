/*
 * Copyright (C) 2018 Neogex Studios
 * 
 * Author: Agustin Rojas
 *
 */
package uy.neogex.jdecrypter.Model;

public class Link {
	private String Url;
	private String Number; 
	
	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public Link(int Number, String Url) {
		this.Url = Url;
		this.Number = "" + Number;
	}
}
