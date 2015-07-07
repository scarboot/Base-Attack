package base_attack;

public class MobSpawner implements Updateable {
	
	private final Game game;
	private final WaveStatsCalculator calc;
	private double timePassed;
	private double timePerSpawn;
	
	public MobSpawner(Game game) {
		this.game = game;
		this.calc = new WaveStatsCalculator(10, 2, 10, 1.5, 5, 2.5);
	}

	@Override
	public void update(double t) {
		calc.update(t);
		
		if(calc.isSpawning())
			updateSpawning(t);
		
	}
	
	private void updateSpawning(double t) {
		
		while(timePassed >= timePerSpawn) { //HUGE LAGS => while
			
			timePassed -= timePerSpawn;
			spawnMob();
			
		}
		
	}

	private void spawnMob() {
		// TODO
	}

	private void onNewWaveStart() {
		timePerSpawn = calc.time / calc.mobs;
		timePassed = timePerSpawn/2; //FIRST MOBS SPAWNS EARLIER, LAST ONE DOES NOT SPAWN TOO LATE
	}
	
	public Game getGame() {
		return game;
	}
	
	private class WaveStatsCalculator implements Updateable {
		
		private int waves = -1;
		
		private boolean spawning = false;
		private double time;
		
		private double spawningTime, pauseTime, mobs;
		
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
			
			if(spawning && time > spawningTime && !mobsLeft()) { //SPAWNING ENDS
				
				spawning = false; //WAITING START
				time = 0; //RESET TIMER
				
			} else if(!spawning && time > pauseTime) { //WAITING ENDS
				
				spawning = true; //SPAWNING START
				waves++; //NEST WAVE
				time = 0; //RESET TIMER
				calcTimes(); //CALC NEW TIMES
				onNewWaveStart(); //TRIGGER EVENT
				
			}
			
		}

		private boolean mobsLeft() {
			return !getGame().getMap().getMobs().isEmpty();
		}

		public void calcTimes() {
			spawningTime	= baseTime	+ timePlus	*waves;
			pauseTime		= basePause	+ pausePlus	*waves;
			mobs			= baseMobs	+ mobsPlus	*waves;
		}
		
		public boolean isSpawning() {
			return spawning;
		}
		
	}

}
