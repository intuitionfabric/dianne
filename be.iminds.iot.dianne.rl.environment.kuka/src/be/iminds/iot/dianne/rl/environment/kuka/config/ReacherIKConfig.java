/*******************************************************************************
 * DIANNE  - Framework for distributed artificial neural networks
 * Copyright (C) 2015  iMinds - IBCN - UGent
 *
 * This file is part of DIANNE.
 *
 * DIANNE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *     Tim Verbelen, Steven Bohez
 *******************************************************************************/
package be.iminds.iot.dianne.rl.environment.kuka.config;

public class ReacherIKConfig {

	public enum Difficulty {
		FIXED,
		WORKSPACE,
		RANDOM
	}
	
	/**
	 * Difficulty
	 */
	public Difficulty difficulty = Difficulty.RANDOM;
	
	/**
	 * Ignore what comes in as action.
	 * 
	 * Just grip on the location given by the simulator as a baseline.
	 */
	public boolean baseline = false;
	
	/**
	 * Height on which to hover before gripping
	 */
	public float hoverHeight = 0.15f;
	
	/**
	 * Height on which to grip
	 */
	public float gripHeight = 0.085f;
}
