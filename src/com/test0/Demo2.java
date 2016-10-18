package com.test0;

class Note {
	private int value;
	private Note(int val) { value = val; }
	public static final Note
	middleC = new Note(0),
	cSharp = new Note(1),
	cFlat = new Note(2);
}

class Instrument {
	public void play(Note n) {
		System.out.println("Instrument.play()");
	}
}

class Wind extends Instrument {
	public void play(Note n) {
		System.out.println("Wind.play()");
	}
}

public class Demo2 {
	public static void tune(Instrument i) {
		i.play(Note.cSharp);
	}
	
	public static void main(String[] args) {
		Wind wind = new Wind();
		tune(wind);
	}
}
