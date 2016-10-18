package com.test0;

abstract class Event {
	private long evtTime;
	public Event(long eventTime) {
		evtTime = eventTime;
	}
	public boolean ready() {
		return System.currentTimeMillis() >= evtTime;
	}
	abstract public void action();
	abstract public String description();
}

class EventSet {
	private Event[] events = new Event[100];
	private int index = 0;
	private int next = 0;
	public void add(Event e) {
		if(index >= events.length)
			return;
		events[index++] = e;
	}
	public Event geteNext() {
		boolean looped = false;
		int start = next;
		do{
			next = (next + 1) % events.length;
			if(start == next) looped = true;
			if((next == (start + 1) % events.length) && looped)
				return null;
		}while(events[next] == null);
		return events[next];
	}
	public void removeCurrent() {
		events[next] = null;
	}
}

public class Demo9 {
	private EventSet es = new EventSet();
	public void add(Event e) { es.add(e); }
	public void run() {
		Event event;
		while((event = es.geteNext()) != null) {
			if(event.ready()) {
				event.action();
				System.out.println(event.description());
				es.removeCurrent();
			}
		}
	}
}
