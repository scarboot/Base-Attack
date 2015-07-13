package base_attack;

public class MobSpawner implements Updateable {
	
	private final Game game;
	private final WaveStatsCalculator calc;
	private double timePassed;
	private double timePerSpawn;
	private int spawned = 0;
	
	public MobSpawner(Game game) {
		this.game = game;
		this.calc = new WaveStatsCalculator(
				5, -1, 
				10, 3.75/* not 4 => gets faster with every wave*/, 
				5, 0.5);
		calc.start();
	}

	@Override
	public void update(double t) {
		
		calc.update(t);
		
		if(calc.isSpawning())
			updateSpawning(t);
		
	}
	
	private void updateSpawning(double t) {
		
		timePassed += t;
		
		while(spawned < calc.mobs && timePassed >= timePerSpawn) { //HUGE LAGS => while
			
			timePassed -= timePerSpawn;
			spawnMob();
			
		}
		
	}

	private void spawnMob() {
		
		getGame().getMap().getMobs().add(new Minion(getGame().getMap().getMobPath()));
		spawned++;
		
	}

	private void onNewWaveStart() {
		timePerSpawn = calc.spawningTime / calc.mobs;
		timePassed = timePerSpawn/2; //FIRST MOBS SPAWNS EARLIER, LAST ONE DOES NOT SPAWN TOO LATE
		spawned = 0;
	}
	
	public Game getGame() {
		return game;
	}

	public int getWave() {
		return calc.waves;
	}

	public int getMobs() {
		return calc.mobs;
	}

	public String formatTime() {
		
		if(calc.spawning)
			return null;
		
		final double aim = calc.spawning ? calc.spawningTime : calc.pauseTime;
		
		final int time = (int) (aim - calc.runningTimer);
		
		if(time < 0)
			throw new IllegalStateException();
		
		final int minutes = time/60;
		final int seconds = time%60;
		
		return String.format("%02d:%02d", minutes, seconds);
		
	}

	public boolean isPause() {
		return !calc.spawning;
	}
	
	private class WaveStatsCalculator implements Updateable {
		
		private int waves = -1;
		
		private boolean spawning = false;
		private double runningTimer, spawningTime, pauseTime;
		private int mobs;
		
		private final int baseMobs, mobsPlus;
		private final double baseTime, timePlus, basePause, pausePlus;
		
		public WaveStatsCalculator(int baseMobs, int mobsPlus, double baseTime, double timePlus, double basePause, double pausePlus) {
			
			this.baseMobs = baseMobs;
			this.mobsPlus = mobsPlus;
			this.baseTime = baseTime;
			this.timePlus = timePlus;
			this.basePause = basePause;
			this.pausePlus = pausePlus;
			
		}
		
//		public WaveStatsCalculator(int baseMobs, int mobsPlus, double timePerMob, double basePause, double pausePlus) {
//			this(baseMobs, mobsPlus, baseMobs*timePerMob, mobsPlus*timePerMob, basePause, pausePlus);			
//		}

		@Override
		public void update(double t) {
			
			if(waves == -1)
				throw new IllegalStateException();
			
			runningTimer += t;
			
			if(spawning && runningTimer >= spawningTime && !mobsLeft()) { //SPAWNING ENDS
				
				spawning = false; //WAITING START
				nextWave();
				
			} else if(!spawning && runningTimer >= pauseTime) { //WAITING ENDS
				
				spawning = true; //SPAWNING START
				runningTimer = 0; //RESET TIMER
				
			}
			
		}
		
		public void start() {
			
			if(waves != -1)
				throw new IllegalStateException();
			
			nextWave();

		}
		
		private void nextWave() {
			waves++; //NEST WAVE
			runningTimer = 0; //RESET TIMER
			calcTimes(); //CALC NEW TIMES
			onNewWaveStart(); //TRIGGER EVENT
		}

		private boolean mobsLeft() {
			return !getGame().getMap().getMobs().isEmpty();
		}

		public void calcTimes() {
			
			spawningTime	= baseTime	+ timePlus	*waves;
			
			pauseTime		= basePause	+ pausePlus	*waves;
			
			if(mobsPlus != -1)
				mobs			= baseMobs	+ mobsPlus	*waves;
			else
				mobs			= baseMobs	+ waves*waves;
			
		}
		
		public boolean isSpawning() {
			return spawning;
		}
		
	}

}
