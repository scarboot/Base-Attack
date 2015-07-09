package base_attack;

public class Cooldown implements Updateable {
	
	private double cooldown;
	private double time;
	
	public Cooldown(double cooldown) {
		this.cooldown = cooldown;
	}

	@Override
	public void update(double t) {
		
		if(ready())
			return;
		
		time += t;
		
	}

	public boolean ready() {
		return time >= cooldown;
	}
	
	public void use() {
		
		if(ready())
			time = 0;
		else
			throw new IllegalStateException(time + " / " + cooldown);
		
	}
	
	public double getTime() {
		return time;
	}
	
	public double getCooldown() {
		return cooldown;
	}
	
	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}

}
