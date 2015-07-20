package base_attack;

import java.util.Random;

public class MobSpawner implements Updateable {

	private static final MobCreator defaultMobCreator = new MobCreator(Minion.class, 1);
	
	private static final MobCreator[] mobCreators = new MobCreator[]{
		new MobCreator(Tank.class, 0.2),
		new MobCreator(Racer.class, 0.2)
	};
	
	private final Random r = new Random("BaseAttack".hashCode());
	
	private final Game game;
	private final WaveStatsCalculator calc;
	private double timePassed;
	private double timePerSpawn;
	private int toSpawn;
	
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
		
		while(toSpawn > 0 && timePassed >= timePerSpawn) { //HUGE LAGS => while
			
			timePassed -= timePerSpawn;
			spawnMob();
			
		}
		
	}

	private void spawnMob() {
		
		MobCreator creator = defaultMobCreator;
		
//		if(getWave() > 0)
			for(MobCreator c: mobCreators)
				
				if(c.getValue() <= toSpawn && c.spawn(r)) {
					
					creator = c;
					break;
					
				}
		
		final Mob mob = creator.create(getGame().getMap().getMobPath());
		
		toSpawn -= creator.getValue();
		
		getGame().getMap().getMobs().add(mob);
		
	}

	private void onNewWaveStart() {
		timePerSpawn = calc.spawningTime / calc.mobs;
		timePassed = timePerSpawn/2; //FIRST MOBS SPAWNS EARLIER, LAST ONE DOES NOT SPAWN TOO LATE
		toSpawn = calc.mobs;
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
