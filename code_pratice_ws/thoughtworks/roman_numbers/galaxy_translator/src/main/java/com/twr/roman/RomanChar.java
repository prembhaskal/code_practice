package com.twr.roman;

public enum RomanChar {

	I ('I', 1),
	V ('V', 5),
	X ('X', 10),
	L ('L', 50),
	C ('C', 100),
	D ('D', 500),
	M ('M', 1000);

	private int value;
	private char name;

	private RomanChar(char name, int value) {
		this.name = name;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public char getName() {
		return name;
	}
}
