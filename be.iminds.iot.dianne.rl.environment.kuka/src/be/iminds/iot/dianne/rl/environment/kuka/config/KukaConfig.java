package be.iminds.iot.dianne.rl.environment.kuka.config;

public class KukaConfig {

	/**
	 * Use internal simulation state instead of sensor input as state representation
	 */
	public boolean simState = false;
	
	/**
	 * Append simulation state to the observations
	 */
	public boolean appendSimState = false;
	
	/**
	 * Use relative can position (to the youbot) in simulation state
	 */
	public boolean relativeCanState = false;
	
	/**
	 * Energy penalization, discouraging the policy to go full speed on the motors. Energy is calculated as the norm of the action tensor.
	 */
	public float energyPenalization = 0f;
	
	/**
	 * Velocity penalization, discouraging the policy to go full speed on the motors.
	 */
	public float velocityPenalization = 0f;
	
	/**
	 * White noise to be added to the range sensor (e.g. to compensate for perfect simulation)
	 */
	public float rangeSensorNoise = 0f;
	
	/**
	 * Number of scan points to evaluate for a range sensor
	 */
	public int scanPoints = 512;
	
	/**
	 * Show laser beams in environment
	 */
	public boolean showLaser = false;
	
	/**
	 * Number of sensors to activate in the environment
	 */
	public int environmentSensors = 0;
	
	/**
	 * Ms to wait at initialization time before regarding this as failure
	 */
	public int timeout = 100000;
	
	/**
	 * Maximum simulator initialization retries.
	 */
	public int maxRetries = 10;
	
	/**
	 * Let the environment have control of time and tick each iteration
	 */
	public boolean tick = true;
}
