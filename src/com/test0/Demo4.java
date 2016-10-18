package com.test0;

interface Monster {
	void menace();
}

interface DangerousMonster extends Monster {
	void destroy();
}

interface Lethal {
	void kill();
}

class DragonZilla implements DangerousMonster {

	public void menace() {
		
	}

	public void destroy() {
		
	}
	
}

interface Vampire extends DangerousMonster, Lethal {
	void drinkBlood();
}

public class Demo4 {
	static void u(Monster b) { b.menace(); }
	static void v(DangerousMonster d) {
		d.menace();
		d.destroy();
	}
	
	public static void main(String[] args) {
		DragonZilla if2 = new DragonZilla();
		u(if2);
		v(if2);
	}
}
