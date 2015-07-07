package base_attack;

public class MobSpawner implements Updateable {
	
	private final WaveStatsCalculator calc;
	
	public MobSpawner() {
		calc = new WaveStatsCalculator(10, 2, 10, 1.5, 5, 2.5);
	}

	@Override
	public void update(double t) {
		
	}
	
	private class WaveStatsCalculator implements Updateable {
		
		private int waves = -1;
		
		private boolean spawning = false;
		private double time = 0;
		
		private double spawningTime, pauseTime;
		
		private final int baseMobs, mobsPlus;
		private final double baseTime, timePlus, basePause, pausePlus;
		
		public WaveStatsCalculator(int baseMobs, int mobsPlus, double baseTime, double timePlus, double basePause, double pausePlus) {
			
			this.baseMobs = baseMobs;
			this.mobsPlus = mobsPlus;
			this.baseTime = baseTime;
			this.timePlus = timePlus;
			this.basePause = basePause;
			this.pausePlus = pausePlus;
			
			this.pauseTime = basePause;
			
		}

		@Override
		public void update(double t) {
			
			time += t;
			
			if(spawning && time > spawningTime)
				spawning = false;
			else if(!spawning && time > pauseTime) {
				
				spawning = true;
				waves++;
				calcTimes();
				
			}
			
		}

		public void calcTimes() {
			//spawningTime = baseS
		}
		
	}

}
