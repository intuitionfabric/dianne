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
package be.iminds.iot.dianne.rl.agent.config;

public class AgentConfig {

	/**
	 * The strategy with which actions are chosen
	 */
	public String strategy = "GreedyActionStrategy";
	
	/**
	 * Tag of fetched weights
	 */
	public String[] tag;
	
	/**
	 * Clear experience pool at start
	 */
	public boolean clear = false;

	/**
	 * Output intermediate results to the console
	 */
	public boolean trace = false;

	/**
	 * Interval to publish intermediate results
	 */
	public int traceInterval = 1;

	/**
	 * Trace rewards of all actions
	 */
	public boolean traceReward = false;
	
	/**
	 * Max number of actions before terminating anyhow
	 */
	public int maxActions = -1;

	/**
	 * Tag parameters that got best reward in a sequence
	 */
	public boolean tagBest = true;
}
